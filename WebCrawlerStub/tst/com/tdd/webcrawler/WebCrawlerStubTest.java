package com.tdd.webcrawler;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WebCrawlerStubTest {

    @Test
    public void testWebCrawler() {
        WebCrawlerStub wc = new WebCrawlerStub();
        String content = wc.retrieve("https://www.naver.com");
        assertNotNull(content);
    }
}