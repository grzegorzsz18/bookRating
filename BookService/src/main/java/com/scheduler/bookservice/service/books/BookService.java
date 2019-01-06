package com.scheduler.bookservice.service.books;

import com.scheduler.bookservice.domain.BookDTO;
import com.scheduler.bookservice.service.cover.BookCover;

import java.util.List;

public interface BookService {

    BookCover saveBook(BookDTO book);

    List<BookDTO> getBooksByUserLogin(String id);
}
