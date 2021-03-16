CREATE TABLE IF NOT EXISTS bookcrossing_service.user
(
    id         BIGSERIAL PRIMARY KEY,
    is_deleted BOOLEAN DEFAULT FALSE,
    login      VARCHAR(255),
    password   VARCHAR(255),
    email      VARCHAR(64),
    first_name VARCHAR(64),
    last_name  VARCHAR(64),
    hobbies    TEXT,
    photo_id   UUID REFERENCES bookcrossing_service.file (id)
)