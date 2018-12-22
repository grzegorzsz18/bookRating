package com.scheduler.bookservice.repository.implementations;

import com.scheduler.bookservice.domain.Book;
import com.scheduler.bookservice.repository.BooksCoverRepository;
import com.scheduler.bookservice.service.cover.BookCover;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class BooksCoverAsFileRepository implements BooksCoverRepository {

    public static final String PARKED_COVERS = "parked_covers/";
    public static final String SAVE_COVER = "saved_covers/";

    @Override
    public void saveBookCover(BookCover book) throws IOException {
        Path path = Paths.get(SAVE_COVER + book.getBook().getId());
        Files.write(path, book.getImage());
    }

    @Override
    public BookCover readBookCover(Book book) throws IOException {
        File fi = new File(SAVE_COVER + book.getId());
        return BookCover.builder()
                .book(book)
                .image(Files.readAllBytes(fi.toPath()))
                .build();
    }

    @Override
    public void parkBookCover(BookCover book) throws IOException {
        Path path = Paths.get(PARKED_COVERS + book.getBook().getId());
        Files.write(path, book.getImage());
    }

    @Override
    public void removeCoverFromParking(String id) throws IOException {

    }

    @Override
    public BookCover readCoverFromParking(String id) throws IOException {
        File fi = new File(PARKED_COVERS + id);
        return BookCover.builder()
                .book(Book.builder()
                        .id(id)
                        .build())
                .image(Files.readAllBytes(fi.toPath()))
                .build();
    }
}
