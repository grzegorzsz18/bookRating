package com.scheduler.bookservice.service.cover;

import com.scheduler.bookservice.domain.Book;

import java.io.IOException;

public interface CoverFinderService {
    BookCover findBookCover(Book book) throws IOException;
}
