CREATE TABLE IF NOT EXISTS bookcrossing_service.author
(
    id         BIGSERIAL PRIMARY KEY,
    is_deleted BOOLEAN DEFAULT FALSE,
    name       VARCHAR(64)
)