package com.scheduler.bookservice.service.cover;

import com.scheduler.bookservice.domain.Book;
import com.scheduler.bookservice.utils.BookImageUrl;

public interface BookCoverService {

    BookCover getProposedBookCover(Book book);

    void confirmBookCover(String id, BookImageUrl uri);
}
