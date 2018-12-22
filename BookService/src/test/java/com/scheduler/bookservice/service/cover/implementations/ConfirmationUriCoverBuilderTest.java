package com.scheduler.bookservice.service.cover.implementations;

import com.scheduler.bookservice.configuration.ConfigReader;
import com.scheduler.bookservice.domain.Book;
import com.scheduler.bookservice.service.cover.BookCover;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ConfigReader.class })
public class ConfirmationUriCoverBuilderTest {

    @Autowired
    ConfigReader configReader;
    ConfirmationUriCoverBuilder confirmationUriCoverBuilder;

    @Before
    public void before() {
        confirmationUriCoverBuilder = new ConfirmationUriCoverBuilder(configReader);
    }

    @Test
    public void shouldBuildUri() {

        final Book book = Book.builder()
                .id("test")
                .build();
        BookCover bookCover = BookCover.builder()
                .book(book)
                .build();
        String expectedResult = "http://localhost:8060/book/confirmationCover/test";
        Assertions.assertThat(confirmationUriCoverBuilder.buildUri(bookCover)).isEqualToIgnoringCase(expectedResult);

    }

}