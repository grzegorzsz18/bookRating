package com.scheduler.bookservice.repository.implementations;

import com.scheduler.bookservice.converters.BookConverter;
import com.scheduler.bookservice.domain.Book;
import com.scheduler.bookservice.repository.BooksCoverRepository;
import com.scheduler.bookservice.service.cover.BookCover;
import org.springframework.stereotype.Service;

@Service
public class BooksCoverAsFileRepository implements BooksCoverRepository {
    @Override
    public void saveBookCover(BookCover book) {

    }

    @Override
    public BookCover readBookCover(Book book) {
        return null;
    }
}
