package com.bookcrossing.api.service.wrapper;

import com.bookcrossing.api.domain.dto.BookDTO;
import com.bookcrossing.api.domain.entity.Book;
import com.bookcrossing.api.service.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookServiceWrapper extends BaseBaseServiceWrapper<BookDTO, Book, Long> {

    @Autowired
    public BookServiceWrapper(BaseService<BookDTO, Book, Long> service) {
        super(service);
    }
}
