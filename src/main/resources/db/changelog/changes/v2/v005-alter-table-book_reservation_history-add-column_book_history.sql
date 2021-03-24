ALTER TABLE bookcrossing_service.book_reservation_history
    ADD COLUMN book_history_id BIGINT REFERENCES bookcrossing_service.book_history (id)