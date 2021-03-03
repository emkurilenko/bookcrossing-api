CREATE TABLE IF NOT EXISTS bookcrossing_service.book_file
(
    book_id BIGINT REFERENCES bookcrossing_service.book (id),
    file_id UUID REFERENCES bookcrossing_service.file (id),
    PRIMARY KEY (book_id, file_id)
)