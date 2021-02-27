CREATE TABLE IF NOT EXISTS bookcrossing_service.book
(
    id         BIGSERIAL PRIMARY KEY,
    update_at  TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    is_deleted BOOLEAN                  DEFAULT FALSE,
    code       VARCHAR(64),
    name       VARCHAR(255)
)