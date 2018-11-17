package com.scheduler.bookservice.service.cover.implementations;

import com.scheduler.bookservice.domain.BookDTO;
import com.scheduler.bookservice.repository.BooksCoverRepository;
import com.scheduler.bookservice.service.cover.BookCover;
import com.scheduler.bookservice.service.cover.BookCoverFinderService;
import com.scheduler.bookservice.service.cover.BookCoverService;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BookCoverServiceImpl implements BookCoverService {

    private final BookCoverFinderService bookCoverFinderService;
    private final BooksCoverRepository booksCoverRepository;

    public BookCoverServiceImpl(BookCoverFinderService bookCoverFinderService, BooksCoverRepository booksCoverRepository) {
        this.bookCoverFinderService = bookCoverFinderService;
        this.booksCoverRepository = booksCoverRepository;
    }

    @Override
    public void saveCoverForBook(BookDTO book) {
        try {
            BookCover bookCover = bookCoverFinderService.findBookCover(book);
            booksCoverRepository.saveBookCover(bookCover);
        } catch (IOException e) {
            //TODO logowanie
        }
    }
}
