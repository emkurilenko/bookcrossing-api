package com.bookcrossing.api.domain.repository;

import com.bookcrossing.api.domain.entity.Book;

import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends BaseCrudRepository<Book, Long> {

}
