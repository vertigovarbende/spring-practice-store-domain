-- BS_CATEGORIES
INSERT INTO BS_CATEGORIES (id, name, description, status, created_at, created_by, updated_at, updated_by)
VALUES ('350e8400-e29b-41d4-a716-446655440000', 'Fiction', 'Books about fantasy and science fiction', 'ACTIVE',
        '2023-01-01 00:00:00', 'admin', '2023-01-01 00:00:00', 'admin');
INSERT INTO BS_CATEGORIES (id, name, description, status, created_at, created_by, updated_at, updated_by)
VALUES ('350e8400-e29b-41d4-a716-446655440001', 'Non-Fiction', 'Books about non-fiction', 'INACTIVE',
        '2023-01-01 00:00:00', 'admin', '2023-01-01 00:00:00', 'admin');


-- BS_BOOKS
INSERT INTO BS_BOOKS (id, title, isbn_10, isbn_13, publisher, publication_date, edition, language, page_count,
                      category_id,
                      status)
VALUES ('550e8400-e29b-41d4-a716-446655440000', 'The Hobbit', '0345339681', '9780345339683', 'George Allen & Unwin',
        '1937-09-21', '1st', 'English', 310, '350e8400-e29b-41d4-a716-446655440000', 'AVAILABLE');
INSERT INTO BS_BOOKS (id, title, isbn_10, isbn_13, publisher, publication_date, edition, language, page_count,
                      category_id,
                      status)
VALUES ('550e8400-e29b-41d4-a716-446655440001', 'The Lord of the Rings', '0618640150', '9780618640157',
        'George Allen & Unwin', '1954-07-29', '1st', 'English', 1178, '350e8400-e29b-41d4-a716-446655440000', 'AVAILABLE');
INSERT INTO BS_BOOKS (id, title, isbn_10, isbn_13, publisher, publication_date, edition, language, page_count,
                      category_id,
                      status)
VALUES ('550e8400-e29b-41d4-a716-446655440002', 'The Hitchhiker''s Guide to the Galaxy', '0345391802', '9780345391803',
        'Pan Books', '1979-10-12', '1st', 'English', 224, '350e8400-e29b-41d4-a716-446655440000', 'AVAILABLE');
INSERT INTO BS_BOOKS (id, title, isbn_10, isbn_13, publisher, publication_date, edition, language, page_count,
                      category_id,
                      status)
VALUES ('550e8400-e29b-41d4-a716-446655440003', 'The Alchemist', '0061122416', '9780061122415', 'HarperOne',
        '1988-06-01', '1st', 'English', 208, '350e8400-e29b-41d4-a716-446655440001', 'AVAILABLE');
INSERT INTO BS_BOOKS (id, title, isbn_10, isbn_13, publisher, publication_date, edition, language, page_count,
                      category_id,
                      status)
VALUES ('550e8400-e29b-41d4-a716-446655440004', 'The Da Vinci Code', '0307474275', '9780307474278', 'Doubleday',
        '2003-03-18', '1st', 'English', 480, '350e8400-e29b-41d4-a716-446655440001', 'AVAILABLE');
INSERT INTO BS_BOOKS (id, title, isbn_10, isbn_13, publisher, publication_date, edition, language, page_count,
                      category_id,
                      status)
VALUES ('550e8400-e29b-41d4-a716-446655440005', 'The Grapes of Wrath', '0140281622', '9780140281620',
        'The Viking Press', '1939-04-14', '1st', 'English', 464, '350e8400-e29b-41d4-a716-446655440001', 'AVAILABLE');
INSERT INTO BS_BOOKS (id, title, isbn_10, isbn_13, publisher, publication_date, edition, language, page_count,
                      category_id,
                      status)
VALUES ('550e8400-e29b-41d4-a716-446655440006', 'The Little Prince', '0156012197', '9780156012195',
        'Éditions Gallimard', '1943-04-06', '1st', 'English', 96, '350e8400-e29b-41d4-a716-446655440000', 'AVAILABLE');


-- BS_AUTHORS
INSERT INTO BS_AUTHORS
(id, first_name, middle_name, last_name, pen_name, title, suffix, email, phone, website_url, twitter_handle,
 instagram_handle, linkedin_url, agent_contact, preferred_contact_method, is_available_for_events, status, is_verified)
VALUES ('450e8400-e29b-41d4-a716-446655440000', 'John', 'M.', 'Smith', NULL, 'Dr.', NULL, 'john.smith@example.com',
        '123-456-7890', NULL, '@johnsmith',
        '@johnsmith', NULL, NULL, 'EMAIL', TRUE, 'ACTIVE', TRUE);

INSERT INTO BS_AUTHORS
(id, first_name, middle_name, last_name, pen_name, title, suffix, email, phone, website_url, twitter_handle,
 instagram_handle, linkedin_url, agent_contact, preferred_contact_method, is_available_for_events, status, is_verified)
VALUES ('450e8400-e29b-41d4-a716-446655440001', 'Jane', NULL, 'Doe', 'J.D.', NULL, 'PhD', 'jane.doe@example.com',
        '223-456-7890', 'https://janedoe.com',
        '@jdoe', '@janedoe', NULL, 'Agent Contact', 'PHONE', FALSE, 'RETIRED', TRUE);

INSERT INTO BS_AUTHORS
(id, first_name, middle_name, last_name, pen_name, title, suffix, email, phone, website_url, twitter_handle,
 instagram_handle, linkedin_url, agent_contact, preferred_contact_method, is_available_for_events, status, is_verified)
VALUES ('450e8400-e29b-41d4-a716-446655440002', 'Robert', 'L.', 'Brown', NULL, 'Mr.', NULL, 'robert.brown@example.com',
        '323-456-7890', NULL, '@robbrown',
        '@rbrown', 'https://linkedin.com/rbrown', NULL, 'SOCIAL_MEDIA', TRUE, 'ACTIVE', FALSE);

INSERT INTO BS_AUTHORS
(id, first_name, middle_name, last_name, pen_name, title, suffix, email, phone, website_url, twitter_handle,
 instagram_handle, linkedin_url, agent_contact, preferred_contact_method, is_available_for_events, status, is_verified)
VALUES ('450e8400-e29b-41d4-a716-446655440003', 'Emily', NULL, 'White', NULL, 'Ms.', NULL, 'emily.white@example.com',
        '423-456-7890',
        'https://emilywhite.com', '@emilywhite', '@emwhite', NULL, NULL, 'AGENT', FALSE, 'INACTIVE', FALSE);

INSERT INTO BS_AUTHORS
(id, first_name, middle_name, last_name, pen_name, title, suffix, email, phone, website_url, twitter_handle,
 instagram_handle, linkedin_url, agent_contact, preferred_contact_method, is_available_for_events, status, is_verified)
VALUES ('450e8400-e29b-41d4-a716-446655440004', 'Michael', 'J.', 'Green', 'M.Green', NULL, NULL,
        'michael.green@example.com', '523-456-7890', NULL,
        '@mgreen', NULL, NULL, 'https://linkedin.com/mgreen', 'ALL', TRUE, 'SUSPENDED', TRUE);


-- Insert relations into BS_BOOK_AUTHORS
INSERT INTO BS_BOOK_AUTHORS (book_id, author_id)
VALUES ('550e8400-e29b-41d4-a716-446655440000', '450e8400-e29b-41d4-a716-446655440000');

INSERT INTO BS_BOOK_AUTHORS (book_id, author_id)
VALUES ('550e8400-e29b-41d4-a716-446655440000', '450e8400-e29b-41d4-a716-446655440001');

INSERT INTO BS_BOOK_AUTHORS (book_id, author_id)
VALUES ('550e8400-e29b-41d4-a716-446655440001', '450e8400-e29b-41d4-a716-446655440002');

INSERT INTO BS_BOOK_AUTHORS (book_id, author_id)
VALUES ('550e8400-e29b-41d4-a716-446655440002', '450e8400-e29b-41d4-a716-446655440002');

INSERT INTO BS_BOOK_AUTHORS (book_id, author_id)
VALUES ('550e8400-e29b-41d4-a716-446655440003', '450e8400-e29b-41d4-a716-446655440003');

INSERT INTO BS_BOOK_AUTHORS (book_id, author_id)
VALUES ('550e8400-e29b-41d4-a716-446655440004', '450e8400-e29b-41d4-a716-446655440004');

INSERT INTO BS_BOOK_AUTHORS (book_id, author_id)
VALUES ('550e8400-e29b-41d4-a716-446655440005', '450e8400-e29b-41d4-a716-446655440004');

INSERT INTO BS_BOOK_AUTHORS (book_id, author_id)
VALUES ('550e8400-e29b-41d4-a716-446655440006', '450e8400-e29b-41d4-a716-446655440004');


-- Insert values into BS_BOOK_GENRES
INSERT INTO BS_BOOK_GENRES (book_id, genre)
VALUES ('550e8400-e29b-41d4-a716-446655440000', 'FANTASY'),
       ('550e8400-e29b-41d4-a716-446655440001', 'FANTASY'),
       ('550e8400-e29b-41d4-a716-446655440002', 'SCIENCE_FICTION'),
       ('550e8400-e29b-41d4-a716-446655440003', 'DRAMA'),
       ('550e8400-e29b-41d4-a716-446655440004', 'THRILLER'),
       ('550e8400-e29b-41d4-a716-446655440005', 'HISTORY'),
       ('550e8400-e29b-41d4-a716-446655440006', 'FANTASY');
