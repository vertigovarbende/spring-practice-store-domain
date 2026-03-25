CREATE TABLE IF NOT EXISTS BS_BOOKS (
    id              VARCHAR(255) PRIMARY KEY,
    title           VARCHAR(255),
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by      VARCHAR(255),
    updated_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by      VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS BS_CUSTOMERS (
    id              VARCHAR(255) PRIMARY KEY,
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by      VARCHAR(255),
    updated_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by      VARCHAR(255)
);


INSERT INTO BS_BOOKS (id, title)
VALUES ('550e8400-e29b-41d4-a716-446655440000', 'The Hobbit');
INSERT INTO BS_BOOKS (id, title)
VALUES ('550e8400-e29b-41d4-a716-446655440001', 'The Lord of the Rings');
INSERT INTO BS_BOOKS (id, title)
VALUES ('550e8400-e29b-41d4-a716-446655440002', 'The Hitchhiker''s Guide to the Galaxy');
INSERT INTO BS_BOOKS (id, title)
VALUES ('550e8400-e29b-41d4-a716-446655440003', 'The Alchemist');
INSERT INTO BS_BOOKS (id, title)
VALUES ('550e8400-e29b-41d4-a716-446655440004', 'The Da Vinci Code');
INSERT INTO BS_BOOKS (id, title)
VALUES ('550e8400-e29b-41d4-a716-446655440005', 'The Grapes of Wrath');
INSERT INTO BS_BOOKS (id, title)
VALUES ('550e8400-e29b-41d4-a716-446655440006', 'The Little Prince');
    






