package com.scheduler.bookservice.converters;

import com.scheduler.bookservice.domain.Book;
import com.scheduler.bookservice.domain.BookDTO;

public class BookConverter {

    private BookConverter() {
    }

    public static Book bookDTOtoBook(BookDTO bookDTO) {
        return Book.builder()
                .title(bookDTO.getTitle())
                .build();
    }
}
