package com.scheduler.bookservice.service.cover;

import com.scheduler.bookservice.domain.Book;

public interface BookCoverService {

    BookCover getProposedBookCover(Book book);

    void confirmBookCover(String id, String uri);
}
