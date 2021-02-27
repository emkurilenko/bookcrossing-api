package com.bookcrossing.api.service.wrapper;

import com.bookcrossing.api.domain.dto.BookDTO;
import com.bookcrossing.api.domain.entity.Book;
import com.bookcrossing.api.service.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookReactiveServiceWrapper extends BaseReactiveBaseServiceWrapper<BookDTO, Book, Long> {

    @Autowired
    public BookReactiveServiceWrapper(BaseService<BookDTO, Book, Long> service) {
        super(service);
    }
}
