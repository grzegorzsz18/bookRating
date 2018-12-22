package com.scheduler.bookservice.service.cover.implementations;

import com.google.api.services.customsearch.model.Result;
import com.scheduler.bookservice.domain.Book;
import com.scheduler.bookservice.service.cover.BookCover;
import com.scheduler.bookservice.service.cover.BookCoverFinderService;
import com.scheduler.bookservice.utils.BookImageUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class BookCoverGoogleFinderService implements BookCoverFinderService {

    private final Logger LOGGER = LoggerFactory.getLogger(BookCoverGoogleFinderService.class);
    private final BooksCoverURLBuilder booksCoverURLBuilder;
    private final BooksCoverDownloader booksCoverDownloader;

    public BookCoverGoogleFinderService(BooksCoverURLBuilder booksCoverURLBuilder, BooksCoverDownloader booksCoverDownloader) {
        this.booksCoverURLBuilder = booksCoverURLBuilder;
        this.booksCoverDownloader = booksCoverDownloader;
    }

    @Override
    public Optional<BookCover> findBookCover(Book book) throws IOException {
        List<Result> resultsLst = booksCoverURLBuilder.searchURLForBook(book);
        if (resultsLst != null && !resultsLst.isEmpty()) {
            BookImageUrl url = this.choseURLForBook(resultsLst);
            LOGGER.info("Find book url {}", url);
            return Optional.of(BookCover.builder()
                    .image(booksCoverDownloader.downloadImageFromURL(url))
                    .book(book)
                    .build());
        }
        return Optional.empty();
    }

    private BookImageUrl choseURLForBook(List<Result> urlList) {
        return urlList.isEmpty()? BookImageUrl.empty() : BookImageUrl.from(urlList.get(0).getLink());
    }
}

