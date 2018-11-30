package com.scheduler.bookservice.service.books.implementations;

import com.scheduler.bookservice.converters.BookConverter;
import com.scheduler.bookservice.converters.BookDTOConverter;
import com.scheduler.bookservice.domain.Book;
import com.scheduler.bookservice.domain.BookDTO;
import com.scheduler.bookservice.exceptions.BookAlreadyExistsException;
import com.scheduler.bookservice.repository.BookCRUDrepository;
import com.scheduler.bookservice.service.books.BookService;
import com.scheduler.bookservice.service.cover.BookCover;
import com.scheduler.bookservice.service.cover.BookCoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private final BookCRUDrepository bookRepository;
    private final BookCoverService bookCoverService;

    @Autowired
    public BookServiceImpl(BookCRUDrepository bookRepository, BookCoverService bookCoverService) {
        this.bookRepository = bookRepository;
        this.bookCoverService = bookCoverService;
    }

    @Override
    public BookCover saveBook(BookDTO book) {
        if (bookRepository.getByTitle(book.getTitle()).size() > 0) {
            throw new BookAlreadyExistsException(book.getTitle());
        }
        Book savedBook = bookRepository.save(BookConverter.bookDTOtoBook(book));
        return bookCoverService.getProposedBookCover(savedBook);
    }
}
