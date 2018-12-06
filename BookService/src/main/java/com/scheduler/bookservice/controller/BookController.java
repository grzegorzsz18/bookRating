package com.scheduler.bookservice.controller;

import com.scheduler.bookservice.domain.BookDTO;
import com.scheduler.bookservice.service.books.BookService;
import com.scheduler.bookservice.service.books.implementations.BookServiceImpl;
import com.scheduler.bookservice.service.cover.BookCoverService;
import com.scheduler.bookservice.utils.ConfirmationUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

    private final BookService bookServiceImpl;
    private final BookCoverService bookCoverService;

    @Autowired
    public BookController(BookServiceImpl bookServiceImpl, BookCoverService bookCoverService) {
        this.bookServiceImpl = bookServiceImpl;
        this.bookCoverService = bookCoverService;
    }

    @PutMapping
    public ResponseEntity saveBook(@RequestBody BookDTO book) {
        return new ResponseEntity<>(this.bookServiceImpl.saveBook(book), HttpStatus.CREATED);
    }

    @PostMapping(path = "confirmationCover/{id}")
    public ResponseEntity confirmCover(@PathVariable("id") String id, @RequestBody ConfirmationUrl uri){
        bookCoverService.confirmBookCover(id, uri);
        return new ResponseEntity(HttpStatus.CREATED);
    }

}
