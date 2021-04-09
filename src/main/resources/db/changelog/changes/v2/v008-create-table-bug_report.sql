CREATE TABLE IF NOT EXISTS bookcrossing_service.bug_report
(
    id           BIGSERIAL PRIMARY KEY,
    is_deleted   BOOLEAN DEFAULT FALSE,
    created_date timestamptz,
    message      TEXT,
    email        VARCHAR(128)
)