package com.scheduler.bookservice.service.cover;

import com.scheduler.bookservice.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCover {
    byte[] image;
    Book book;
    String confirmationLink;

    public static BookCover empty(){
        return new BookCover();
    }
}
