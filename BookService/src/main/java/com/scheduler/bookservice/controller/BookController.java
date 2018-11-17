package com.scheduler.bookservice.controller;

import com.scheduler.bookservice.domain.BookDTO;
import com.scheduler.bookservice.service.books.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class BookController {

    private final BookServiceImpl bookServiceImpl;

    @Autowired
    public BookController(BookServiceImpl bookServiceImpl) {
        this.bookServiceImpl = bookServiceImpl;
    }

    @PutMapping
    public ResponseEntity saveBook(@RequestBody BookDTO book) throws IOException {
        return new ResponseEntity<>(this.bookServiceImpl.saveBook(book), HttpStatus.CREATED);
    }

}
