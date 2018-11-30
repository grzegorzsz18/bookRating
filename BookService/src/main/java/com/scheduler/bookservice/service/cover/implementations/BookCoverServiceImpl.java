package com.scheduler.bookservice.service.cover.implementations;

import com.scheduler.bookservice.domain.Book;
import com.scheduler.bookservice.repository.BooksCoverRepository;
import com.scheduler.bookservice.service.cover.BookCover;
import com.scheduler.bookservice.service.cover.BookCoverFinderService;
import com.scheduler.bookservice.service.cover.BookCoverService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class BookCoverServiceImpl implements BookCoverService {

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
            //TODO logowanie
        }
        return bookCover;
    }

    @Override
    public void confirmBookCover(String id, String uri) {
        if (uri.isEmpty()) {
            try {
                BookCover book = this.booksCoverRepository.readCoverFromParking(id);
                this.booksCoverRepository.saveBookCover(book);
                this.booksCoverRepository.removeCoverFromParking(id);
            } catch (IOException e) {
                e.printStackTrace();
                //TODO logowanie
            }
        } else {
            getBookCoverFromUri(uri).ifPresent((BookCover book) -> {
                try {
                    book.setBook(Book.builder()
                            .id(id)
                            .build());
                    this.booksCoverRepository.saveBookCover(book);
                } catch (IOException e) {
                    e.printStackTrace();
                    // TODO logowanie
                }
            });
        }
    }

    private Optional<BookCover> getBookCoverFromUri(String uri) {
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
