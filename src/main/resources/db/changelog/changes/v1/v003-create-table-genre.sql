CREATE TABLE IF NOT EXISTS bookcrossing_service.genre
(
    id         BIGSERIAL PRIMARY KEY,
    is_deleted BOOLEAN DEFAULT FALSE,
    name       VARCHAR(255)
)