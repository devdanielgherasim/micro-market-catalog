# Catalog Microservice

Product catalog microservice for the microservices dissertation project, built with Java 21 and Quarkus 3.18.3. It owns the `Product` entity (CRUD + search) and exposes it over a REST API on port **8088**. The `orders` service calls catalog synchronously to fetch product information for order line items; catalog (like `orders`) fire-and-forget logs its write operations to the `audit` service and degrades gracefully (logs a warning, never fails the caller) if audit is unreachable.

## Tech stack

Confirmed from `pom.xml` (Quarkus BOM 3.18.3, Java 21):

- `quarkus-rest` (JAX-RS / RESTEasy Reactive, used imperatively) + `quarkus-hibernate-orm-rest-data-panache`
- `quarkus-hibernate-orm` + `quarkus-hibernate-orm-panache` (Panache active-record entities)
- `quarkus-jdbc-postgresql`
- `quarkus-hibernate-validator` (Bean Validation on entities/DTOs)
- `quarkus-smallrye-openapi` (OpenAPI/Swagger UI) + `quarkus-smallrye-health` (health checks)
- `quarkus-oidc` + `quarkus-keycloak-authorization` (Keycloak-backed auth; catalog carries the policy-enforcer extension)
- `quarkus-rest-client` + `quarkus-rest-client-jackson` (outbound REST client to the audit service)
- `quarkus-jackson` + `jackson-datatype-jsr310`, `quarkus-arc`, `quarkus-opentelemetry`, `quarkus-container-image-docker`, `slf4j-api`
- Test scope: `quarkus-junit5`, `quarkus-junit5-mockito`, `rest-assured`, `testcontainers` (`postgresql` module)

## Domain model

The only entity is `Product` (`entities/Product.java`, table `products`, extends `PanacheEntity`). Its actual fields are:

- `id` (inherited `Long` from `PanacheEntity`)
- `name` — `String`, `@NotBlank`, not nullable
- `description` — `String`, column length 2000
- `price` — `BigDecimal`, `@NotNull @Positive`, not nullable
- `category` — `String`, not nullable
- `isAvailable` — `boolean` (column `is_available`, not nullable, defaults to `true`)

There is no `version`, `releaseDate`, `publisher`, `features`, or `requirements` field on the entity — an earlier revision of this README described those but they do not exist in the current code. `ProductDTO`, `ProductCreateDTO`, and `ProductUpdateDTO` mirror the same five business fields (the DTO serializes `isAvailable` as JSON property `available`).

## API endpoints

All routes are under `/api/products` (`controllers/ProductController.java`), JSON in/out, `@PermitAll` on every method (open at the HTTP layer; see Auth below for what OIDC/Keycloak is actually configured to do):

- `GET /api/products?page=&size=` — paginated list of all products
- `GET /api/products/{id}` — get by id (404 if missing)
- `POST /api/products` — create (validated `ProductCreateDTO`)
- `PUT /api/products/{id}` — update (validated `ProductUpdateDTO`)
- `DELETE /api/products/{id}` — delete
- `GET /api/products/category/{category}?page=&size=` — paginated search by category
- `GET /api/products/search?name=&page=&size=` — paginated name search
- `GET /api/products/price?min=&max=&page=&size=` — paginated price-range search
- `GET /api/products/available?page=&size=` — paginated available-only list

Pagination is hand-rolled via `utils/PaginationUtil` and `utils/PageResponse`, not Panache's built-in paging. There is no publisher-based search endpoint despite what an earlier version of this document claimed.

### Outbound call: audit logging

`clients/AuditServiceClient` is a MicroProfile REST client (`@RegisterRestClient(configKey = "audit-service")`) that POSTs to `/api/audit` on the audit service, with Keycloak token propagation via `clients/AuditServiceTokenRequestFilter`. `services/AuditService` wraps every call in a try/catch — `ProcessingException` (audit unreachable) and any other exception are caught and logged, never propagated, so a failing audit write never blocks or fails the primary product operation.

## Local development

```shell
./mvnw quarkus:dev
```

Starts Quarkus in dev mode with hot reload; Dev UI at <http://localhost:8088/q/dev/>.

The app expects PostgreSQL at **`localhost:5433`** by default (see `application.properties`), overridable via environment variables:

- `DB_HOST` (default `localhost`), `DB_PORT` (default `5433`), `DB_NAME` (default `microservices1691716`)
- `DB_USERNAME` (default `postgres`), `DB_PASSWORD` (default a committed lab-scope fallback, see Known notes below)
- `HTTP_PORT` (default `8088`)

`quarkus.hibernate-orm.database.generation` defaults to `drop-and-create` (overridable via `HIBERNATE_GENERATION`), so **every dev-mode restart drops and recreates the schema**, then reloads `src/main/resources/import.sql` (configurable via `HIBERNATE_LOAD_SCRIPT`) — don't rely on data surviving a restart.

## API documentation

- Swagger UI: <http://localhost:8088/swagger-ui>
- OpenAPI spec: <http://localhost:8088/openapi>
- Liveness: <http://localhost:8088/health/live>
- Readiness: <http://localhost:8088/health/ready>

## Testing

```shell
./mvnw test
```

Requires a working Docker daemon — the suite uses Testcontainers to spin up a real `postgres:16-alpine` container per test class (`PostgresTestResource`, a `QuarkusTestResourceLifecycleManager`), pointing the datasource at the container, forcing `drop-and-create` with no load script, and stubbing `quarkus.rest-client.audit-service.url` to an unreachable address. Actual test classes found under `src/test/java/cloud/microservices/catalog/`:

- `repositories/ProductRepositoryTest` — `@QuarkusTest`, exercises `ProductRepository` count/filter queries (by category, name-contains, price range, availability) against the containerized DB.
- `controllers/ProductControllerTest` — `@QuarkusTest` + REST Assured, drives the full create/read/update/delete flow through the real HTTP endpoints, with `AuditService` mocked out via `@InjectMock`.
- `controllers/ProductControllerIT` — `@QuarkusIntegrationTest` smoke test that runs against the packaged artifact and asserts `/health/ready` returns 200.

## Build

```shell
./mvnw package                                                            # JVM build -> target/quarkus-app/quarkus-run.jar
./mvnw clean package -Dnative -Dquarkus.native.container-build=true       # native build via container (what CI runs)
```

## CI/CD

CI runs on GitHub Actions (`.github/workflows/ci.yml`; migrated from GitLab CI,
see `Sources/plans/2026-07-08-gitlab-to-github-migration.md`). Three jobs:

- **`test`**: runs `./mvnw test` on a GitHub-hosted runner, publishes JUnit
  results as a workflow artifact.
- **`build-and-push-native`**: logs into the cloud container registry via the
  shared `cloud-registry-login` composite action (OIDC, no static cloud
  credentials), runs `./build.sh` (native image build + push), then resolves
  the pushed image reference/digest via the shared `resolve-image-ref`
  composite action.
- **`security-scan-gate`** and **`image-supply-chain`**: call the reusable
  workflows in `devdanielgherasim/micro-market-utilities` — CodeQL (with a
  HIGH/CRITICAL severity gate), gitleaks, and dependency-review; then Trivy
  image scan, Syft SBOM, cosign keyless sign+verify, and a
  `repository_dispatch` trigger into the `deployment` repo's promotion
  workflow.

`build.sh` is cloud-provider-aware (`CLOUD_PROVIDER` = `aws`/`azure`/`gcp`, default `aws`): it resolves/logs into the right registry (ECR, ACR, or Artifact Registry, including OIDC-based login paths) unless a caller already exported `CONTAINER_REGISTRY_NAME`/performed login, then runs `mvn clean package -Dnative` with `-Dquarkus.container-image.{build,push}=true` pointed at that registry, tagged with `CI_COMMIT_SHA`.

## Auth

Uses `quarkus-oidc` against the shared `microservices` Keycloak realm (`quarkus.oidc.client-id=catalog-service`, realm URL defaults under `KEYCLOAK_URL`/`TOKEN_ISSUER` env vars). Catalog also depends on `quarkus-keycloak-authorization` (confirmed in `pom.xml`), though `quarkus.keycloak.policy-enforcer.enable=false` in `application.properties` and every `/api/products/*` path is configured `permission.public.policy=permit` for all methods — so today authorization is effectively open at the product endpoints; only `/*` in general falls back to `authenticated`, and `/health/*` is explicitly public. Token propagation is enabled (`quarkus.oidc.token-propagation.enabled=true`) so an incoming user's token is forwarded on the outbound call to the audit service.

## Known inconsistencies

- `quarkus.datasource.password` in `application.properties` has a committed fallback default (`${DB_PASSWORD:oRncHiOovwJAVOXK}`). This is an accepted lab-scope default used across all three services, not something to rotate from here — see the workspace-level `CLAUDE.md` for the full list of known cross-repo drift.
- This README previously documented `Product` fields (`version`, `releaseDate`, `publisher`, `features`, `requirements`) and a publisher-search endpoint that never existed in the code; both have been removed above to match the actual entity and controller.
