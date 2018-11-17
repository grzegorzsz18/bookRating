package com.scheduler.bookservice.service.cover;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.customsearch.Customsearch;
import com.google.api.services.customsearch.model.Result;
import com.google.api.services.customsearch.model.Search;
import com.scheduler.bookservice.configuration.Config;
import com.scheduler.bookservice.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CustomSearchBuilder {
    private static String SEARCH_TYPE = "image";
    private static String FILE_TYPE = "png";
    private final Config config;

    @Autowired
    public CustomSearchBuilder(Config config) {
        this.config = config;
    }

    List<Result> buildCustomSearch(Book book) throws IOException {


        Customsearch customsearch = new Customsearch(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
            public void initialize(HttpRequest httpRequest) {
                try {
                    httpRequest.setConnectTimeout(config.getConnectionTimeout());
                    httpRequest.setReadTimeout(config.getConnectionTimeout());

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        Customsearch.Cse.List list = customsearch.cse().list(book.getTitle());
        list.setKey(this.config.getApplicationKey());
        list.setCx(this.config.getApplicationCx());
        list.setSearchType(SEARCH_TYPE);
        list.setFileType(FILE_TYPE);
        Search results = list.execute();
        return results.getItems();
    }
}
