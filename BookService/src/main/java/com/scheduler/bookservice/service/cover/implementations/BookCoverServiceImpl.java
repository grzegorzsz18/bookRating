package com.scheduler.bookservice.service.cover.implementations;

import com.scheduler.bookservice.domain.Book;
import com.scheduler.bookservice.repository.BooksCoverRepository;
import com.scheduler.bookservice.service.cover.BookCover;
import com.scheduler.bookservice.service.cover.BookCoverFinderService;
import com.scheduler.bookservice.service.cover.BookCoverService;
import com.scheduler.bookservice.utils.ConfirmationUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class BookCoverServiceImpl implements BookCoverService {

    private final Logger LOGGER = LoggerFactory.getLogger(BookCoverServiceImpl.class);
    private final BookCoverFinderService bookCoverFinderService;
    private final BooksCoverRepository booksCoverRepository;
    private final ConfirmationUriCoverBuilder confirmationUriCoverBuilder;
    private final BooksCoverDownloader booksCoverDownloader;


    public BookCoverServiceImpl(BookCoverFinderService bookCoverFinderService,
                                BooksCoverRepository booksCoverRepository,
                                ConfirmationUriCoverBuilder confirmationUriCoverBuilder,
                                BooksCoverDownloader booksCoverDownloader) {
        this.bookCoverFinderService = bookCoverFinderService;
        this.booksCoverRepository = booksCoverRepository;
        this.confirmationUriCoverBuilder = confirmationUriCoverBuilder;
        this.booksCoverDownloader = booksCoverDownloader;
    }

    @Override
    public BookCover getProposedBookCover(Book book) {
        BookCover bookCover = null;
        try {
            bookCover = bookCoverFinderService.findBookCover(book).orElseGet(BookCover::empty);
            booksCoverRepository.parkBookCover(bookCover);
            bookCover = addConfirmationUri(bookCover);
        } catch (IOException e) {
            LOGGER.error("Get proposed book cover {}, {}", e, book);
        }
        return bookCover;
    }

    @Override
    public void confirmBookCover(String id, ConfirmationUrl url) {
        if (url.isNotEmpty()) {
            this.saveBook(id, url);
        } else{
            this.unparkBook(id);
        }
    }

    private void saveBook(String id, ConfirmationUrl url) {
        getBookCoverFromUri(url).ifPresent((BookCover book) -> {
            try {
                book.setBook(Book.builder()
                        .id(id)
                        .build());
                this.booksCoverRepository.saveBookCover(book);
            } catch (IOException e) {
                LOGGER.error("Save book {}, {}", e, book);
            }
        });
    }

    private void unparkBook(String id) {
        try {
            BookCover book = this.booksCoverRepository.readCoverFromParking(id);
            this.booksCoverRepository.saveBookCover(book);
            this.booksCoverRepository.removeCoverFromParking(id);
        } catch (IOException e) {
            LOGGER.error("Unpark book {}, {}", e, id);
        }
    }

    private Optional<BookCover> getBookCoverFromUri(ConfirmationUrl uri) {
        return Optional.of(BookCover.builder()
                .image(this.booksCoverDownloader.downloadImageFromURL(uri))
                .build());
    }


    private BookCover addConfirmationUri(BookCover bookCover) {
        String uri = confirmationUriCoverBuilder.buildUri(bookCover);
        bookCover.setConfirmationLink(uri);
        return bookCover;
    }
}
