package com.scheduler.bookservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Document
public class Book {

    private String id;
    private String userId;
    private String title;
    private String author;

    public static Book empty(){
        return new Book();
    }

    public static Book from(BookDTO bookDTO){
        return Book.builder()
                .author(bookDTO.getAuthor())
                .title(bookDTO.getTitle())
                .userId(bookDTO.getUserId())
                .build();
    }
}
