package com.scheduler.bookservice.service.cover.implementations;

import com.google.api.services.customsearch.model.Result;
import com.scheduler.bookservice.domain.Book;
import com.scheduler.bookservice.service.cover.BookCover;
import com.scheduler.bookservice.utils.BookImageUrl;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookCoverGoogleFinderServiceTest {

    @Mock
    private BooksCoverURLBuilder booksCoverURLBuilder;
    @Mock
    private BooksCoverDownloader booksCoverDownloader;
    @InjectMocks
    private BookCoverGoogleFinderService bookCoverGoogleFinderService;


    @Test
    public void shouldReturnBookCover() throws IOException {
        //given
        Result result = new Result();
        result.setLink("sample link");
        List<Result> results = Collections.singletonList(result);
        byte[] bookImage = new byte[1];
        bookImage[0] = 1;
        Book book = Book.builder()
                .id("1")
                .author("auth")
                .title("title")
                .build();
        BookCover bookCover = BookCover.builder()
                .book(book)
                .image(bookImage)
                .build();

        //when
        when(booksCoverURLBuilder.searchURLForBook(any(Book.class))).thenReturn(results);
        when(booksCoverDownloader.downloadImageFromURL(any(BookImageUrl.class))).thenReturn(bookImage);

        //then
        Assertions.assertThat(bookCoverGoogleFinderService.findBookCover(book)).get().isEqualToComparingFieldByField(bookCover);
    }

    @Test
    public void shouldReturnEmpty() throws IOException {
        //given
        List<Result> results = Collections.emptyList();
        byte[] bookImage = new byte[1];
        bookImage[0] = 1;
        Book book = Book.builder()
                .id("1")
                .author("auth")
                .title("title")
                .build();

        //when
        when(booksCoverURLBuilder.searchURLForBook(any(Book.class))).thenReturn(results);

        //then
        Assertions.assertThat(bookCoverGoogleFinderService.findBookCover(book)).isEmpty();

    }
}