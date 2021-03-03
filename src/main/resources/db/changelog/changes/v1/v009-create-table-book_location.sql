CREATE TABLE IF NOT EXISTS bookcrossing_service.location_book
(
    id         BIGSERIAL PRIMARY KEY,
    is_deleted BOOLEAN DEFAULT FALSE,
    latitude   DOUBLE PRECISION,
    longitude  DOUBLE PRECISION,
    book_id    BIGINT REFERENCES bookcrossing_service.book (id)
);