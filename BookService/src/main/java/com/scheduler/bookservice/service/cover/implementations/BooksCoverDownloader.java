package com.scheduler.bookservice.service.cover.implementations;

import com.scheduler.bookservice.utils.BookImageUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Service
public class BooksCoverDownloader {

    private final Logger LOGGER = LoggerFactory.getLogger(BooksCoverDownloader.class);

    byte[] downloadImageFromURL(BookImageUrl inputUrl) {
        byte[] response = new byte[0];
        try {
            URL url = new URL(inputUrl.getUrl());
            InputStream in = new BufferedInputStream(url.openStream());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int n;
            while (-1 != (n = in.read(buf))) {
                out.write(buf, 0, n);
            }
            out.close();
            in.close();
            response = out.toByteArray();
        } catch (IOException e) {
            LOGGER.error("Download image {}, {}", e, inputUrl);
        }
        return response;
    }
}
