CREATE TABLE IF NOT EXISTS bookcrossing_service.book_rating
(
    id           BIGSERIAL PRIMARY KEY,
    is_deleted   BOOLEAN DEFAULT FALSE,
    created_date timestamptz,
    rate         DOUBLE PRECISION,
    owner_id     BIGINT REFERENCES bookcrossing_service.user (id),
    user_id      BIGINT REFERENCES bookcrossing_service.user (id),
    book_id      BIGINT REFERENCES bookcrossing_service.book (id)
)