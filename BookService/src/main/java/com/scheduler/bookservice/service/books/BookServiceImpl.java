package com.scheduler.bookservice.service.books;

import com.scheduler.bookservice.converters.BookConverter;
import com.scheduler.bookservice.converters.BookDTOConverter;
import com.scheduler.bookservice.domain.Book;
import com.scheduler.bookservice.domain.BookDTO;
import com.scheduler.bookservice.exceptions.BookAlreadyExistsException;
import com.scheduler.bookservice.repository.BookCRUDrepository;
import com.scheduler.bookservice.service.cover.BookCover;
import com.scheduler.bookservice.service.cover.CoverFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BookServiceImpl implements BookService {

    private final BookCRUDrepository bookRepository;
    private final CoverFinderService coverFinderService;

    @Autowired
    public BookServiceImpl(BookCRUDrepository bookRepository,
                           CoverFinderService coverFinderService) {
        this.bookRepository = bookRepository;
        this.coverFinderService = coverFinderService;
    }

    @Override
    public BookDTO saveBook(BookDTO book) throws IOException {
        coverFinderService.findBookCover(BookConverter.bookDTOtoBook(book));
        if(bookRepository.getByTitle(book.getTitle()).size()>0){
            throw new BookAlreadyExistsException(book.getTitle());
        }

        return BookDTOConverter.bookToBookDTO(bookRepository.save(BookConverter.bookDTOtoBook(book)));
    }
}
