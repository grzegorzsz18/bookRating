package com.scheduler.bookservice.repository.implementations;

import com.scheduler.bookservice.domain.Book;
import com.scheduler.bookservice.repository.BooksCoverRepository;
import com.scheduler.bookservice.service.cover.BookCover;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class BooksCoverAsFileRepository implements BooksCoverRepository {
    @Override
    public void saveBookCover(BookCover book) throws IOException {
        Path path = Paths.get("covers/" + book.getBook().getTitle());
        Files.write(path, book.getImage());
    }

    @Override
    public BookCover readBookCover(Book book) {
        return null;
    }
}
