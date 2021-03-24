CREATE TABLE IF NOT EXISTS bookcrossing_service.book_history
(
    id           BIGSERIAL PRIMARY KEY,
    is_deleted   BOOLEAN     DEFAULT FALSE,
    created_date timestamptz DEFAULT now(),
    user_id      BIGINT REFERENCES bookcrossing_service.user (id),
    book_id      BIGINT REFERENCES bookcrossing_service.book (id),
    status       VARCHAR(64)
)