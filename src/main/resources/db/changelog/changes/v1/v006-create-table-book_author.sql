CREATE TABLE IF NOT EXISTS bookcrossing_service.book_author
(
    book_id   BIGINT REFERENCES bookcrossing_service.book (id),
    author_id BIGINT REFERENCES bookcrossing_service.author (id),
    PRIMARY KEY (book_id, author_id)
)