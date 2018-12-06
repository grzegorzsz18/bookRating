package com.scheduler.bookservice.utils;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfirmationUrl {
    private String url;

    public boolean isNotEmpty() {
        return url != null && !url.isEmpty();
    }

    public static ConfirmationUrl from(String url){
        return ConfirmationUrl.builder().url(url).build();
    }
}
