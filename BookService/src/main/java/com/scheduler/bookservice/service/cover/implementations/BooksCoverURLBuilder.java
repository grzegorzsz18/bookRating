package com.scheduler.bookservice.service.cover.implementations;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.customsearch.Customsearch;
import com.google.api.services.customsearch.model.Result;
import com.google.api.services.customsearch.model.Search;
import com.scheduler.bookservice.configuration.ConfigReader;
import com.scheduler.bookservice.domain.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class BooksCoverURLBuilder {
    private static final String SEARCH_TYPE = "image";
    private static final String FILE_TYPE = "png";
    private final ConfigReader configReader;
    private static final String QUERY = " książka";

    @Autowired
    public BooksCoverURLBuilder(ConfigReader configReader) {
        this.configReader = configReader;
    }

    List<Result> searchURLForBook(Book book) throws IOException {


        Customsearch customsearch = new Customsearch(new NetHttpTransport(), new JacksonFactory(), httpRequest -> {
            try {
                httpRequest.setConnectTimeout(configReader.getConnectionTimeout());
                httpRequest.setReadTimeout(configReader.getConnectionTimeout());

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        Customsearch.Cse.List list = customsearch.cse().list(generateQuery(book));
        list.setKey(this.configReader.getApplicationKey());
        list.setCx(this.configReader.getApplicationCx());
        list.setSearchType(SEARCH_TYPE);
        list.setFileType(FILE_TYPE);
        Search results = list.execute();
        return results.getItems();
    }

    private String generateQuery(Book book){
        return book.getTitle() + " " + book.getAuthor() + QUERY;
    }
}
