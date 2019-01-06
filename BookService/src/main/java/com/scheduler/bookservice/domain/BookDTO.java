package com.scheduler.bookservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {

    private String userId;
    private String title;
    private String author;

    public static BookDTO from(Book book){
        return BookDTO.builder()
                .author(book.getAuthor())
                .title(book.getTitle())
                .userId(book.getUserId())
                .build();
    }

}
