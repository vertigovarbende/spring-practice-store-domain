-- =========================================================
-- V2__add_indexes.sql
-- Indexes for Book Search Strategies
-- PostgreSQL
-- =========================================================

CREATE EXTENSION IF NOT EXISTS pg_trgm;

-- =========================================================
-- TitleSearchStrategy
-- cb.like(lower(title), '%term%')
-- =========================================================

CREATE INDEX IF NOT EXISTS idx_bs_books_title_trgm
    ON BS_BOOKS USING GIN (LOWER(title) gin_trgm_ops);


-- =========================================================
-- CategorySearchStrategy
-- join book.category and cb.like(lower(category.name), '%term%')
-- =========================================================

CREATE INDEX IF NOT EXISTS idx_bs_books_category_id
    ON BS_BOOKS (category_id);

CREATE INDEX IF NOT EXISTS idx_bs_categories_name_trgm
    ON BS_CATEGORIES USING GIN (LOWER(name) gin_trgm_ops);


-- =========================================================
-- AuthorSearchStrategy
-- join book.authors and cb.like(lower(first/middle/last/pen name), '%term%')
-- =========================================================

CREATE INDEX IF NOT EXISTS idx_bs_book_authors_author_id
    ON BS_BOOK_AUTHORS (author_id);

-- PK(book_id, author_id) already helps book_id lookups,
-- but keeping this explicit is okay if you want clarity.
CREATE INDEX IF NOT EXISTS idx_bs_book_authors_book_id
    ON BS_BOOK_AUTHORS (book_id);

CREATE INDEX IF NOT EXISTS idx_bs_authors_first_name_trgm
    ON BS_AUTHORS USING GIN (LOWER(first_name) gin_trgm_ops);

CREATE INDEX IF NOT EXISTS idx_bs_authors_middle_name_trgm
    ON BS_AUTHORS USING GIN (LOWER(middle_name) gin_trgm_ops);

CREATE INDEX IF NOT EXISTS idx_bs_authors_last_name_trgm
    ON BS_AUTHORS USING GIN (LOWER(last_name) gin_trgm_ops);

CREATE INDEX IF NOT EXISTS idx_bs_authors_pen_name_trgm
    ON BS_AUTHORS USING GIN (LOWER(pen_name) gin_trgm_ops);


-- =========================================================
-- GenreSearchStrategy
-- ElementCollection table search by genre text
-- =========================================================

CREATE INDEX IF NOT EXISTS idx_bs_book_genres_genre_trgm
    ON BS_BOOK_GENRES USING GIN (LOWER(genre) gin_trgm_ops);

-- PK(book_id, genre) already helps book_id lookups,
-- but explicit index is okay if you want clarity.
CREATE INDEX IF NOT EXISTS idx_bs_book_genres_book_id
    ON BS_BOOK_GENRES (book_id);


-- =========================================================
-- Common filters / useful future indexes
-- =========================================================

CREATE INDEX IF NOT EXISTS idx_bs_books_status
    ON BS_BOOKS (status);

CREATE INDEX IF NOT EXISTS idx_bs_categories_status
    ON BS_CATEGORIES (status);

CREATE INDEX IF NOT EXISTS idx_bs_authors_status
    ON BS_AUTHORS (status);

CREATE UNIQUE INDEX IF NOT EXISTS uk_bs_categories_name_lower
    ON BS_CATEGORIES (LOWER(name));

CREATE UNIQUE INDEX IF NOT EXISTS uk_bs_authors_email_lower
    ON BS_AUTHORS (LOWER(email));

CREATE INDEX IF NOT EXISTS idx_bs_books_isbn_10
    ON BS_BOOKS (isbn_10);

CREATE INDEX IF NOT EXISTS idx_bs_books_isbn_13
    ON BS_BOOKS (isbn_13);