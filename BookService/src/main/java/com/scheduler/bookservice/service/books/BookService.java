package com.scheduler.bookservice.service.books;

import com.scheduler.bookservice.domain.BookDTO;

import java.io.IOException;

public interface BookService {

    BookDTO saveBook(BookDTO book) throws IOException;
}
