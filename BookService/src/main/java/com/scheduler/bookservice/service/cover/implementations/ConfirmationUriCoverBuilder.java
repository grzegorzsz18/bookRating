package com.scheduler.bookservice.service.cover.implementations;

import com.scheduler.bookservice.configuration.ConfigReader;
import com.scheduler.bookservice.service.cover.BookCover;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationUriCoverBuilder {

    private final ConfigReader configReader;

    private final String HOST;
    private final String PORT;
    private final String PROTOCOL;
    private final String ENDPOINT;
    private final String KEY;

    public ConfirmationUriCoverBuilder(ConfigReader configReader) {
        this.configReader = configReader;
        this.KEY = configReader.getConfirmationUri().getKey();
        this.ENDPOINT = configReader.getConfirmationUri().getEndpoint();
        this.PROTOCOL = configReader.getConfirmationUri().getProtocol();
        this.PORT = configReader.getConfirmationUri().getPort();
        this.HOST = configReader.getConfirmationUri().getHost();

    }

    public String buildUri(BookCover bookCover) {
        return PROTOCOL + "://" + HOST + ":" + PORT + "/" + ENDPOINT + "/" + KEY + "/" + bookCover.getBook().getId();
    }
}
