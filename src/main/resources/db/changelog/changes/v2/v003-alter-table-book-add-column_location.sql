ALTER TABLE bookcrossing_service.book
    ADD COLUMN location_id BIGINT REFERENCES bookcrossing_service.location_book (id)