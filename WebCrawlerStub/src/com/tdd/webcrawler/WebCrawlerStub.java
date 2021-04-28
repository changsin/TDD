package com.tdd.webcrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * WebCrawlerStub is meant as a **starter** code for building a web crawler.
 * It needs to be refactored and properly implemented.
 * The purpose is to demonstrate how to access a website based open a url and
 * read the content from the stream and display the results.
 *
 * Note that the content contains all the gory details of html tags.
 * If you want to just get the text content, you need to parse html and strip the tags.
 */
public class WebCrawlerStub {
    public String retrieve(final String link) {
        URL url;
        InputStream is = null;
        BufferedReader br;
        StringBuilder content = new StringBuilder();

        try {
            url = new URL(link);
            is = url.openStream();  // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                content.append(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException ex) {
                // nothing to see here
            }
        }

        return content.toString();
    }
}
