package com.scheduler.bookservice.service.cover;

import com.scheduler.bookservice.domain.BookDTO;

import java.io.IOException;

public interface BookCoverFinderService {
    BookCover findBookCover(BookDTO book) throws IOException;
}
