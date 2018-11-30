package com.scheduler.bookservice.service.books;

import com.scheduler.bookservice.domain.BookDTO;
import com.scheduler.bookservice.service.cover.BookCover;

public interface BookService {

    BookCover saveBook(BookDTO book);
}
