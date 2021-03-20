CREATE TABLE IF NOT EXISTS bookcrossing_service.base_task
(
    id          BIGSERIAL PRIMARY KEY,
    is_deleted  BOOLEAN DEFAULT FALSE,
    name        VARCHAR(64),
    status      VARCHAR(64),
    description TEXT
)