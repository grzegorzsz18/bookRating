package com.scheduler.bookservice.service.cover;

import com.scheduler.bookservice.domain.Book;
import com.scheduler.bookservice.utils.ConfirmationUrl;

public interface BookCoverService {

    BookCover getProposedBookCover(Book book);

    void confirmBookCover(String id, ConfirmationUrl uri);
}
