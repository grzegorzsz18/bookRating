package com.scheduler.bookservice.repository;

import com.scheduler.bookservice.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookCRUDrepository extends MongoRepository<Book, String> {

    List<Book> getByTitle(String title);

}
