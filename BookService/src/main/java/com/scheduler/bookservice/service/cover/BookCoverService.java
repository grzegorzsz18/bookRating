package com.scheduler.bookservice.service.cover;

import com.scheduler.bookservice.domain.BookDTO;

public interface BookCoverService {

    void saveCoverForBook(BookDTO book);
}
