package com.scheduler.bookservice.service.cover.implementations;

import com.scheduler.bookservice.service.cover.BookCover;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationUriCoverBuilder {

    public static final String HOST = "localhost";
    public static final String PORT = "8060";
    public static final String PROTOCOL = "http";
    public static final String ENDPOINT = "book";
    public static final String KEY = "confirmationCover";

    public String buildUri(BookCover bookCover) {
        return PROTOCOL + "://" + HOST + ":" + PORT + "/" + ENDPOINT + "/" + KEY + "/" + bookCover.getBook().getId();
    }
}
