CREATE TABLE IF NOT EXISTS BS_CATEGORIES (
    id           VARCHAR(255) PRIMARY KEY,
    name         VARCHAR(255) NOT NULL,
    description  VARCHAR(255),
    status       VARCHAR(255) NOT NULL CHECK ( status IN
                                                    ('ACTIVE', 'INACTIVE', 'DELETED') ),
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by   VARCHAR(255),
    updated_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by   VARCHAR(255)

    );


CREATE TABLE IF NOT EXISTS BS_BOOKS (
    id              VARCHAR(255) PRIMARY KEY,
    title           VARCHAR(255),
    isbn_10         VARCHAR(255),
    isbn_13         VARCHAR(255),
    publisher       VARCHAR(255),
    publication_date DATE,
    edition         VARCHAR(255),
    language        VARCHAR(255),
    page_count      INT,
    category_id     VARCHAR(255),
    status          VARCHAR(255) NOT NULL CHECK ( status IN
                                                        ('AVAILABLE', 'UNAVAILABLE', 'DELETED')),
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by      VARCHAR(255),
    updated_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by      VARCHAR(255),

    -- Foreign key constraint
    CONSTRAINT fk_bs_books_category
    FOREIGN KEY (category_id)
    REFERENCES BS_CATEGORIES(id)
    );

CREATE TABLE IF NOT EXISTS BS_AUTHORS
(
    id                       VARCHAR(255) PRIMARY KEY,
    first_name               VARCHAR(100) NOT NULL,
    middle_name              VARCHAR(100),
    last_name                VARCHAR(100) NOT NULL,
    pen_name                 VARCHAR(200),
    title                    VARCHAR(50),
    suffix                   VARCHAR(20),
    email                    VARCHAR(200) NOT NULL UNIQUE,
    phone                    VARCHAR(20),
    website_url              VARCHAR(300),
    twitter_handle           VARCHAR(100),
    instagram_handle         VARCHAR(100),
    linkedin_url             VARCHAR(300),
    agent_contact            VARCHAR(300),
    preferred_contact_method VARCHAR(20)  NOT NULL CHECK ( preferred_contact_method IN
                                                                    ('EMAIL', 'PHONE', 'AGENT', 'SOCIAL_MEDIA', 'ALL')),
    is_available_for_events  BOOLEAN   DEFAULT FALSE,
    status                   VARCHAR(255) NOT NULL CHECK ( status IN
                                                            ('ACTIVE', 'INACTIVE', 'RETIRED', 'DECEASED', 'SUSPENDED')),
    is_verified              BOOLEAN   DEFAULT FALSE,

    created_at               TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by               VARCHAR(255),
    updated_at               TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by               VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS BS_BOOK_AUTHORS
(
    book_id    VARCHAR(255) NOT NULL,
    author_id  VARCHAR(255) NOT NULL,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(255),

    -- Primary key constraint
    CONSTRAINT pk_bs_book_authors PRIMARY KEY (book_id, author_id),

    -- Foreign key constraints
    CONSTRAINT fk_bs_book_authors_book
    FOREIGN KEY (book_id)
    REFERENCES BS_BOOKS (id)
    ON DELETE CASCADE,

    CONSTRAINT fk_bs_book_authors_author
    FOREIGN KEY (author_id)
    REFERENCES BS_AUTHORS (id)
    );


CREATE TABLE IF NOT EXISTS BS_CUSTOMERS (
    id              VARCHAR(255) PRIMARY KEY,
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by      VARCHAR(255),
    updated_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by      VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS BS_BOOK_GENRES
(
    book_id    VARCHAR(255) NOT NULL,
    genre      VARCHAR(255) NOT NULL CHECK ( genre IN
                                             ('FANTASY', 'SCIENCE_FICTION', 'ROMANCE', 'HORROR', 'THRILLER', 'MYSTERY',
                                             'DRAMA', 'HISTORY')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(255),

    -- Primary key constraint
    CONSTRAINT pk_bs_book_genres PRIMARY KEY (book_id, genre),

    -- Foreign key constraints
    CONSTRAINT fk_bs_book_genres_book
    FOREIGN KEY (book_id)
    REFERENCES BS_BOOKS (id)
    ON DELETE CASCADE
    );