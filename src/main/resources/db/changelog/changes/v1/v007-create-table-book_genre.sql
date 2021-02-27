CREATE TABLE IF NOT EXISTS bookcrossing_service.book_genre
(
    book_id  BIGINT REFERENCES bookcrossing_service.book (id),
    genre_id BIGINT REFERENCES bookcrossing_service.genre (id),
    PRIMARY KEY (book_id, genre_id)
)