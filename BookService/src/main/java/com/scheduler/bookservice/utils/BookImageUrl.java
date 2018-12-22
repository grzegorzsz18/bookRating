package com.scheduler.bookservice.utils;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookImageUrl {
    private String url;

    public boolean isNotEmpty() {
        return url != null && !url.isEmpty();
    }

    public static BookImageUrl empty(){
        return new BookImageUrl();
    }

    public static BookImageUrl from(String url){
        return BookImageUrl.builder().url(url).build();
    }
}
