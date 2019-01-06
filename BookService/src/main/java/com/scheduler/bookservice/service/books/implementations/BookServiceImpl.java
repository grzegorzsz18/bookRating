package com.scheduler.bookservice.service.books.implementations;

import com.scheduler.bookservice.domain.Book;
import com.scheduler.bookservice.domain.BookDTO;
import com.scheduler.bookservice.exceptions.BookAlreadyExistsException;
import com.scheduler.bookservice.repository.BookCRUDrepository;
import com.scheduler.bookservice.service.books.BookService;
import com.scheduler.bookservice.service.cover.BookCover;
import com.scheduler.bookservice.service.cover.BookCoverService;
import com.scheduler.bookservice.service.externalApi.AuthorizationExternalApi;
import com.scheduler.bookservice.service.externalApi.AuthorizationExternalApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookCRUDrepository bookRepository;
    private final BookCoverService bookCoverService;
    private final AuthorizationExternalApi authorizationExternalApi;

    @Autowired
    public BookServiceImpl(BookCRUDrepository bookRepository, BookCoverService bookCoverService,
                           AuthorizationExternalApiService externalApi) {
        this.bookRepository = bookRepository;
        this.bookCoverService = bookCoverService;
        this.authorizationExternalApi = externalApi;
    }

    @Override
    public BookCover saveBook(BookDTO book) {
        if (bookRepository.getByTitle(book.getTitle()).size() > 0) {
            throw new BookAlreadyExistsException(book.getTitle());
        }
        Book savedBook = bookRepository.save(Book.from(book));
        return bookCoverService.getProposedBookCover(savedBook);
    }

    @Override
    public List<BookDTO> getBooksByUserLogin(String userLogin) {
        Optional<String> userId = authorizationExternalApi.getUserId(userLogin);
        if (!userId.isPresent()) {
            return Collections.emptyList();
        }
        return bookRepository.getByUserId(userId.get())
                .stream()
                .map(BookDTO::from)
                .collect(Collectors.toList());
    }

}
