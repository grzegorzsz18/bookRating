package com.scheduler.bookservice.service.cover;

import com.scheduler.bookservice.domain.Book;

import java.io.IOException;
import java.util.Optional;

public interface BookCoverFinderService {
    Optional<BookCover> findBookCover(Book book) throws IOException;
}
