-- Create the hibernate_sequence if it doesn't exist
CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1 INCREMENT BY 1;

-- Sample data for products table
INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available) 
VALUES (nextval('hibernate_sequence'), 'Office Suite Pro', 'Complete office productivity suite with word processing, spreadsheets, and presentations', 199.99, 'Productivity', '2023.1', '2023-01-15', 'SoftTech Inc.', 'Word processing, Spreadsheets, Presentations, Cloud storage integration', 'Windows 10 or higher, 4GB RAM, 10GB free disk space', true);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available) 
VALUES (nextval('hibernate_sequence'), 'Photo Editor Master', 'Professional photo editing software with advanced tools and filters', 149.99, 'Graphics', '4.2', '2022-11-05', 'Creative Software Ltd.', 'Layer support, Advanced filters, RAW processing, Batch editing', 'Windows 10/macOS 11 or higher, 8GB RAM, Dedicated graphics card', true);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available) 
VALUES (nextval('hibernate_sequence'), 'Database Manager Enterprise', 'Enterprise-grade database management system with advanced security features', 499.99, 'Database', '2023.2', '2023-03-20', 'DataTech Solutions', 'Advanced security, High availability, Backup and recovery, Performance monitoring', 'Windows Server 2019/Linux, 16GB RAM, 50GB free disk space', true);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available) 
VALUES (nextval('hibernate_sequence'), 'Video Editing Studio', 'Professional video editing software with timeline editing and effects', 299.99, 'Multimedia', '10.5', '2022-09-10', 'MediaSoft', 'Timeline editing, Special effects, 4K support, Audio editing', 'Windows 11/macOS 12, 16GB RAM, Dedicated graphics card, 100GB free disk space', true);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available) 
VALUES (nextval('hibernate_sequence'), 'Cloud Backup Service', 'Secure cloud backup service with automatic syncing and versioning', 9.99, 'Cloud Services', '3.0', '2023-02-01', 'CloudSafe Inc.', 'Automatic backup, File versioning, End-to-end encryption, Cross-platform support', 'Any modern web browser, Internet connection', true);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available) 
VALUES (nextval('hibernate_sequence'), 'Project Management Tool', 'Comprehensive project management software with Gantt charts and team collaboration', 19.99, 'Business', '2023.1', '2023-01-30', 'BusinessSoft', 'Gantt charts, Task management, Team collaboration, Time tracking', 'Web-based, Any modern browser', true);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available) 
VALUES (nextval('hibernate_sequence'), 'Antivirus Premium', 'Advanced antivirus and security suite with real-time protection', 79.99, 'Security', '2023.2', '2023-04-05', 'SecureTech', 'Real-time protection, Firewall, Ransomware protection, VPN', 'Windows 8/10/11, 2GB RAM, 1GB free disk space', true);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available) 
VALUES (nextval('hibernate_sequence'), 'Code IDE Professional', 'Integrated development environment for multiple programming languages', 199.99, 'Development', '2023.1', '2023-02-15', 'DevTools Inc.', 'Multi-language support, Debugging, Version control integration, Extensions', 'Windows/macOS/Linux, 8GB RAM, 2GB free disk space', true);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available) 
VALUES (nextval('hibernate_sequence'), 'Music Production Suite', 'Professional music production software with virtual instruments and mixing tools', 349.99, 'Audio', '12.0', '2022-12-10', 'AudioTech', 'Virtual instruments, Mixing console, Audio effects, MIDI support', 'Windows 10/macOS 11, 16GB RAM, Audio interface recommended', true);

INSERT INTO products (id, name, description, price, category, version, release_date, publisher, features, requirements, is_available) 
VALUES (nextval('hibernate_sequence'), 'PDF Creator Pro', 'Create, edit, and convert PDF documents with ease', 49.99, 'Productivity', '5.2', '2023-03-01', 'DocSoft', 'PDF creation, Editing, Form filling, Document conversion', 'Windows 7/8/10/11, 4GB RAM, 500MB free disk space', true);
