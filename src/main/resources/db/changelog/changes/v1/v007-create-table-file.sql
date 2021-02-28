CREATE TABLE IF NOT EXISTS bookcrossing_service.file
(
    id          UUID PRIMARY KEY,
    is_deleted BOOLEAN DEFAULT FALSE,
    name        VARCHAR(255),
    content_type VARCHAR(64),
    content     BYTEA
)