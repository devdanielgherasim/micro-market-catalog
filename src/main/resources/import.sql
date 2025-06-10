-- Sample data for products table
-- Productivity Software
INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (1, 'Office Suite Pro', 'Complete office productivity suite with word processing, spreadsheets, and presentations', 199.99, 'Productivity', '2023.1', '2023-01-15', 'SoftTech Inc.', 'Word processing, Spreadsheets, Presentations, Cloud storage integration', 'Windows 10 or higher, 4GB RAM, 10GB free disk space', true);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (2, 'PDF Creator Pro', 'Create, edit, and convert PDF documents with ease', 49.99, 'Productivity', '5.2', '2023-03-01', 'DocSoft', 'PDF creation, Editing, Form filling, Document conversion', 'Windows 7/8/10/11, 4GB RAM, 500MB free disk space', true);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (3, 'Text Editor Plus', 'Lightweight text editor with syntax highlighting for programmers', 29.99, 'Productivity', '3.5', '2023-02-10', 'CodeTools Inc.', 'Syntax highlighting, Multiple tabs, Auto-save, Themes', 'Windows/macOS/Linux, 2GB RAM, 100MB free disk space', true);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (4, 'Email Manager Pro', 'Professional email client with advanced organization features', 39.99, 'Productivity', '2023.2', '2023-04-15', 'CommSoft', 'Email filtering, Calendar integration, Contact management, Multi-account support', 'Windows 10/11, 4GB RAM, 1GB free disk space', true);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (5, 'Note Taking App', 'Digital notebook with organization and syncing capabilities', 19.99, 'Productivity', '4.0', '2023-01-20', 'NoteWorks', 'Cloud sync, Tags, Search, Rich text formatting', 'Any platform with web browser, 2GB RAM', false);

-- Graphics and Design
INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (6, 'Photo Editor Master', 'Professional photo editing software with advanced tools and filters', 149.99, 'Graphics', '4.2', '2022-11-05', 'Creative Software Ltd.', 'Layer support, Advanced filters, RAW processing, Batch editing', 'Windows 10/macOS 11 or higher, 8GB RAM, Dedicated graphics card', true);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (7, 'Vector Graphics Studio', 'Professional vector graphics editor for illustrations and designs', 129.99, 'Graphics', '2023.1', '2023-03-10', 'DesignWorks', 'Vector editing, Typography tools, Asset library, Export options', 'Windows 10/macOS 11, 8GB RAM, 2GB free disk space', true);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (8, '3D Modeling Pro', 'Professional 3D modeling and rendering software', 299.99, 'Graphics', '5.0', '2023-02-20', '3D Systems Inc.', '3D modeling, Texturing, Animation, Rendering', 'Windows 10/11, 16GB RAM, Dedicated graphics card, 10GB free disk space', true);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (9, 'UI Design Toolkit', 'Comprehensive UI/UX design toolkit for web and mobile apps', 89.99, 'Graphics', '2.5', '2023-01-25', 'UX Solutions', 'Component library, Prototyping, Design systems, Collaboration', 'Windows/macOS, 8GB RAM, 2GB free disk space', false);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (10, 'Icon Creator', 'Specialized tool for creating and editing icons and small graphics', 39.99, 'Graphics', '3.1', '2022-12-15', 'IconWorks', 'Vector editing, Pixel-perfect tools, Export formats, Template library', 'Windows/macOS, 4GB RAM, 500MB free disk space', true);

-- Database and Data Management
INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (11, 'Database Manager Enterprise', 'Enterprise-grade database management system with advanced security features', 499.99, 'Database', '2023.2', '2023-03-20', 'DataTech Solutions', 'Advanced security, High availability, Backup and recovery, Performance monitoring', 'Windows Server 2019/Linux, 16GB RAM, 50GB free disk space', true);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (12, 'SQL Client Pro', 'Advanced SQL client for database management and query optimization', 149.99, 'Database', '4.5', '2023-02-05', 'QueryTech', 'Query builder, Performance analysis, Schema visualization, Data export', 'Windows/macOS/Linux, 8GB RAM, 1GB free disk space', true);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (13, 'Data Visualization Tool', 'Interactive data visualization software for business intelligence', 199.99, 'Database', '2023.1', '2023-01-10', 'VisualData Inc.', 'Interactive dashboards, Chart types, Data connectors, Export options', 'Windows 10/11, 8GB RAM, 2GB free disk space', true);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (14, 'NoSQL Database System', 'Scalable NoSQL database system for modern applications', 399.99, 'Database', '3.0', '2023-04-01', 'NoSQL Systems', 'Horizontal scaling, Document storage, JSON support, High performance', 'Linux/Windows Server, 16GB RAM, 10GB free disk space', false);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (15, 'ETL Processing Tool', 'Enterprise ETL tool for data integration and transformation', 299.99, 'Database', '2.5', '2022-11-20', 'DataFlow Inc.', 'Data mapping, Transformation, Scheduling, Monitoring', 'Windows Server/Linux, 16GB RAM, 5GB free disk space', true);
-- Multimedia
INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (16, 'Video Editing Studio', 'Professional video editing software with timeline editing and effects', 299.99, 'Multimedia', '10.5', '2022-09-10', 'MediaSoft', 'Timeline editing, Special effects, 4K support, Audio editing', 'Windows 11/macOS 12, 16GB RAM, Dedicated graphics card, 100GB free disk space', true);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (17, 'Music Production Suite', 'Professional music production software with virtual instruments and mixing tools', 349.99, 'Multimedia', '12.0', '2022-12-10', 'AudioTech', 'Virtual instruments, Mixing console, Audio effects, MIDI support', 'Windows 10/macOS 11, 16GB RAM, Audio interface recommended', true);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (18, 'Screen Recording Pro', 'Professional screen recording and video capture software', 79.99, 'Multimedia', '5.0', '2023-03-15', 'CaptureTech', 'HD recording, Editing tools, Export options, Webcam overlay', 'Windows 10/11, 8GB RAM, 2GB free disk space', true);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (19, 'Audio Editor Professional', 'Advanced audio editing and mastering software', 129.99, 'Multimedia', '4.2', '2023-02-25', 'SoundLab', 'Multi-track editing, Effects, Noise reduction, Format conversion', 'Windows/macOS, 8GB RAM, 2GB free disk space', false);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (20, 'Media Converter Ultimate', 'Comprehensive media conversion tool for all audio and video formats', 49.99, 'Multimedia', '3.5', '2023-01-05', 'ConvertWorks', 'Format conversion, Batch processing, Presets, Device profiles', 'Windows/macOS, 4GB RAM, 1GB free disk space', true);

-- Cloud Services
INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (21, 'Cloud Backup Service', 'Secure cloud backup service with automatic syncing and versioning', 9.99, 'Cloud Services', '3.0', '2023-02-01', 'CloudSafe Inc.', 'Automatic backup, File versioning, End-to-end encryption, Cross-platform support', 'Any modern web browser, Internet connection', true);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (22, 'Cloud Storage Premium', 'Premium cloud storage solution with advanced sharing and collaboration', 14.99, 'Cloud Services', '2023.1', '2023-01-15', 'StorageCloud', 'File sharing, Collaboration tools, Version history, Mobile access', 'Any device with internet connection', true);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (23, 'Cloud Computing Platform', 'Scalable cloud computing platform for businesses', 99.99, 'Cloud Services', '2023.2', '2023-03-10', 'CloudCompute', 'Virtual machines, Containers, Serverless functions, Load balancing', 'Web browser, Internet connection', true);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (24, 'Cloud Database Service', 'Managed database service in the cloud with automatic scaling', 79.99, 'Cloud Services', '4.0', '2023-02-20', 'CloudData Inc.', 'Automatic scaling, Backup, High availability, Performance monitoring', 'Web browser, Internet connection', false);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (25, 'Cloud Development Environment', 'Cloud-based development environment for remote coding', 19.99, 'Cloud Services', '2.5', '2023-04-05', 'DevCloud', 'Code editor, Terminal, Git integration, Extensions', 'Modern web browser, Internet connection', true);

-- Business Software
INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (26, 'Project Management Tool', 'Comprehensive project management software with Gantt charts and team collaboration', 19.99, 'Business', '2023.1', '2023-01-30', 'BusinessSoft', 'Gantt charts, Task management, Team collaboration, Time tracking', 'Web-based, Any modern browser', true);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (27, 'CRM System Pro', 'Professional customer relationship management system', 49.99, 'Business', '5.0', '2023-03-15', 'SalesTech', 'Contact management, Sales pipeline, Reporting, Email integration', 'Web-based, Modern browser', true);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (28, 'Accounting Software', 'Comprehensive accounting and financial management software', 99.99, 'Business', '2023.2', '2023-02-10', 'FinanceTech', 'Bookkeeping, Invoicing, Tax preparation, Financial reporting', 'Windows 10/11, 8GB RAM, 2GB free disk space', true);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (29, 'HR Management System', 'Complete human resources management solution', 79.99, 'Business', '4.5', '2023-01-20', 'HRSoft', 'Employee records, Time tracking, Performance management, Payroll', 'Web-based, Modern browser', false);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (30, 'Business Intelligence Suite', 'Advanced business intelligence and analytics platform', 199.99, 'Business', '3.0', '2023-04-01', 'DataInsight', 'Data analysis, Reporting, Dashboards, Predictive analytics', 'Windows/macOS, 16GB RAM, 5GB free disk space', true);

-- Security Software
INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (31, 'Antivirus Premium', 'Advanced antivirus and security suite with real-time protection', 79.99, 'Security', '2023.2', '2023-04-05', 'SecureTech', 'Real-time protection, Firewall, Ransomware protection, VPN', 'Windows 8/10/11, 2GB RAM, 1GB free disk space', true);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (32, 'Password Manager Pro', 'Secure password management solution with encryption', 29.99, 'Security', '5.1', '2023-02-15', 'PassSafe', 'Password storage, Generator, Auto-fill, Encryption', 'Windows/macOS/Linux/Mobile, 2GB RAM', true);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (33, 'Network Security Monitor', 'Advanced network monitoring and security tool', 149.99, 'Security', '4.0', '2023-03-10', 'NetSecure', 'Traffic monitoring, Intrusion detection, Alerts, Reporting', 'Windows Server/Linux, 8GB RAM, 2GB free disk space', true);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (34, 'Data Encryption Tool', 'File and disk encryption software for sensitive data', 59.99, 'Security', '3.5', '2023-01-25', 'CryptoSoft', 'File encryption, Disk encryption, Secure deletion, Key management', 'Windows/macOS, 4GB RAM, 1GB free disk space', false);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (35, 'Secure VPN Service', 'Virtual private network service for secure browsing', 12.99, 'Security', '2023.1', '2023-02-05', 'PrivacyNet', 'Global servers, No-logs policy, Kill switch, Multi-device', 'Windows/macOS/Linux/Mobile, 1GB RAM', true);

-- Development Tools
INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (36, 'Code IDE Professional', 'Integrated development environment for multiple programming languages', 199.99, 'Development', '2023.1', '2023-02-15', 'DevTools Inc.', 'Multi-language support, Debugging, Version control integration, Extensions', 'Windows/macOS/Linux, 8GB RAM, 2GB free disk space', true);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (37, 'API Development Platform', 'Complete platform for designing, testing, and documenting APIs', 149.99, 'Development', '4.0', '2023-03-20', 'APITech', 'API design, Testing, Documentation, Mocking', 'Windows/macOS/Linux, 8GB RAM, 2GB free disk space', true);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (38, 'Mobile App Development Kit', 'Cross-platform mobile application development toolkit', 99.99, 'Development', '2023.2', '2023-01-10', 'MobileDev', 'Cross-platform, UI components, Device APIs, Testing tools', 'Windows/macOS, 16GB RAM, 5GB free disk space', true);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (39, 'Database Development Studio', 'Comprehensive database development and administration tool', 129.99, 'Development', '5.5', '2023-02-25', 'DBDev', 'Query editor, Schema design, Data modeling, Performance tuning', 'Windows/macOS/Linux, 8GB RAM, 2GB free disk space', false);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (40, 'DevOps Automation Suite', 'Integrated toolset for DevOps automation and CI/CD', 249.99, 'Development', '3.0', '2023-04-10', 'DevOpsTech', 'CI/CD pipelines, Infrastructure as code, Monitoring, Deployment', 'Windows Server/Linux, 16GB RAM, 10GB free disk space', true);

-- Education Software
INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (41, 'Language Learning Platform', 'Interactive language learning software with speech recognition', 59.99, 'Education', '2023.1', '2023-01-15', 'LinguaTech', 'Multiple languages, Speech recognition, Progress tracking, Interactive exercises', 'Windows/macOS/Mobile, 4GB RAM, 2GB free disk space', true);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (42, 'Math Education Software', 'Comprehensive mathematics education software for all levels', 49.99, 'Education', '4.5', '2023-02-20', 'EduMath', 'Interactive lessons, Problem solving, Graphing, Progress tracking', 'Windows/macOS, 4GB RAM, 1GB free disk space', true);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (43, 'Virtual Science Lab', 'Virtual laboratory for science education and experiments', 79.99, 'Education', '3.0', '2023-03-10', 'ScienceSoft', 'Physics simulations, Chemistry experiments, Biology models, Interactive learning', 'Windows 10/11, 8GB RAM, 2GB free disk space, Dedicated graphics', false);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (44, 'Coding for Kids', 'Programming education platform designed for children', 39.99, 'Education', '2.5', '2023-01-30', 'KidCode', 'Visual programming, Game creation, Step-by-step tutorials, Progress tracking', 'Windows/macOS/Tablets, 2GB RAM, 1GB free disk space', true);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (45, 'Online Course Platform', 'Platform for creating and delivering online courses', 149.99, 'Education', '2023.2', '2023-04-05', 'EduPlatform', 'Course creation, Student management, Assessments, Analytics', 'Web-based, Modern browser', true);

-- Utilities
INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (46, 'System Optimizer Pro', 'Comprehensive system optimization and cleaning tool', 39.99, 'Utilities', '10.2', '2023-03-15', 'OptimizeTech', 'Disk cleaning, Registry repair, Startup management, Performance optimization', 'Windows 7/8/10/11, 2GB RAM, 500MB free disk space', true);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (47, 'File Recovery Tool', 'Advanced file recovery and data rescue software', 59.99, 'Utilities', '5.0', '2023-02-10', 'RecoverySoft', 'File recovery, Disk scanning, Preview, Multiple file systems', 'Windows/macOS, 4GB RAM, 1GB free disk space', true);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (48, 'Disk Partition Manager', 'Disk partitioning and management utility', 29.99, 'Utilities', '4.2', '2023-01-20', 'DiskTools', 'Partition creation, Resizing, Format conversion, Bootable media', 'Windows 7/8/10/11, 2GB RAM, 500MB free disk space', false);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (49, 'Backup Utility Pro', 'Comprehensive backup and restore solution', 49.99, 'Utilities', '2023.1', '2023-04-01', 'BackupTech', 'File backup, System image, Scheduling, Cloud integration', 'Windows 8/10/11, 4GB RAM, 1GB free disk space', true);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available)
VALUES (50, 'File Compression Tool', 'Advanced file compression and archiving utility', 19.99, 'Utilities', '3.5', '2023-02-25', 'CompressTech', 'Multiple formats, Encryption, Split archives, Password protection', 'Windows/macOS/Linux, 2GB RAM, 500MB free disk space', true);