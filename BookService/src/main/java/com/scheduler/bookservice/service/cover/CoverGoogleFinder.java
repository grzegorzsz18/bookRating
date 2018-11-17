package com.scheduler.bookservice.service.cover;

import com.scheduler.bookservice.domain.Book;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CoverGoogleFinder implements CoverFinderService {

    private final CustomSearchBuilder customSearchBuilder;

    public CoverGoogleFinder(CustomSearchBuilder customSearchBuilder) {
        this.customSearchBuilder = customSearchBuilder;
    }

    @Override
    public BookCover findBookCover(Book book) throws IOException {
        customSearchBuilder.buildCustomSearch(book);
        return null;
    }
}

