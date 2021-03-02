CREATE TABLE IF NOT EXISTS bookcrossing_service.location_file
(
    location_id BIGINT REFERENCES bookcrossing_service.location_book (id),
    file_id     UUID REFERENCES bookcrossing_service.file (id),
    PRIMARY KEY (location_id, file_id)
)