package com.scheduler.bookservice.service.cover.implementations;

import com.scheduler.bookservice.domain.Book;
import com.scheduler.bookservice.repository.BooksCoverRepository;
import com.scheduler.bookservice.service.cover.BookCover;
import com.scheduler.bookservice.utils.BookImageUrl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookCoverServiceImplTest {

    @InjectMocks
    private BookCoverServiceImpl bookCoverService;
    @Mock
    private BooksCoverRepository booksCoverRepository;
    @Mock
    private BooksCoverDownloader booksCoverDownloader;

    @Test
    public void shouldConfirmBookCoverEmptyUrl() throws IOException {
        //given
        BookImageUrl url = new BookImageUrl();
        url.setUrl("");
        String id = "id";
        BookCover bookCover = BookCover.builder()
                .book(Book.builder()
                        .id(id)
                        .build())
                .image(new byte[2])
                .build();
        when(booksCoverRepository.readCoverFromParking(anyString())).thenReturn(bookCover);

        //when
        bookCoverService.confirmBookCover(id, url);

        verify(booksCoverRepository).readCoverFromParking(id);
        verify(booksCoverRepository).saveBookCover(bookCover);
        verify(booksCoverRepository).removeCoverFromParking(id);
    }

    @Test
    public void shouldConfirmBookCoverNotEmptyUrl() throws IOException {
        //given
        BookImageUrl url = new BookImageUrl();
        url.setUrl("url");
        byte[] img = new byte[1];
        img[0] = 16;
        String id = "id";
        BookCover bookCover = BookCover.builder()
                .book(Book.builder()
                        .id(id)
                        .build())
                .image(img)
                .build();
        when(booksCoverDownloader.downloadImageFromURL(url)).thenReturn(img);

        //when
        bookCoverService.confirmBookCover(id, url);

        verify(booksCoverRepository).saveBookCover(bookCover);
    }

}