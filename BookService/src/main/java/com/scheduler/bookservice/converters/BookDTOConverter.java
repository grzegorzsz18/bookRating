package com.scheduler.bookservice.converters;

import com.scheduler.bookservice.domain.Book;
import com.scheduler.bookservice.domain.BookDTO;

public class BookDTOConverter {

    private BookDTOConverter() {
    }

    public static BookDTO bookToBookDTO(Book book) {
        return BookDTO.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .build();
    }
}

