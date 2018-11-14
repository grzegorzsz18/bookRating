package com.scheduler.bookservice.service;

import com.scheduler.bookservice.converters.BookConverter;
import com.scheduler.bookservice.converters.BookDTOConverter;
import com.scheduler.bookservice.domain.BookDTO;
import com.scheduler.bookservice.repository.BookCRUDrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {


    BookCRUDrepository bookRepository;

    @Autowired
    public BookService(BookCRUDrepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDTO saveBook(BookDTO book) {
        return BookDTOConverter.bookToBookDTO(bookRepository.save(BookConverter.bookDTOtoBook(book)));
    }
}
