package com.scheduler.bookservice.controller;

import com.scheduler.bookservice.domain.BookDTO;
import com.scheduler.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PutMapping
    public ResponseEntity saveBook(@RequestBody BookDTO book) {
        return new ResponseEntity<>(this.bookService.saveBook(book), HttpStatus.CREATED);
    }

}
