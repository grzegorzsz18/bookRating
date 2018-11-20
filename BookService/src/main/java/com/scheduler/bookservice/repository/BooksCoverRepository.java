package com.scheduler.bookservice.repository;

import com.scheduler.bookservice.converters.BookConverter;
import com.scheduler.bookservice.domain.Book;
import com.scheduler.bookservice.service.cover.BookCover;

import java.io.IOException;

public interface BooksCoverRepository {

    void saveBookCover(BookCover book) throws IOException;
    BookCover readBookCover(Book book);
}
