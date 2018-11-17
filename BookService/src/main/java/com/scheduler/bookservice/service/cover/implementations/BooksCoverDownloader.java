package com.scheduler.bookservice.service.cover.implementations;

import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class BooksCoverDownloader {

    byte[] downloadImageFromURL(String inputUrl){
        byte[] response = new byte[0];
        try{
        URL url = new URL(inputUrl);
        InputStream in = new BufferedInputStream(url.openStream());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int n;
        while (-1!=(n=in.read(buf)))
        {
            out.write(buf, 0, n);
        }
        out.close();
        in.close();
        response = out.toByteArray();
        }
        catch (MalformedURLException e){
            //TODO logger
        }
        catch (IOException e){
            //TODO logger zmienic
        }
        return response;
    }
}
