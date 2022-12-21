package com.enterprise.rental.utils;

import com.enterprise.rental.service.locale.BungleEn;
import com.enterprise.rental.service.locale.BungleUa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class BungleTest {
    BungleEn bungleEn;
    BungleUa bungleUa;

    @BeforeEach
    void init() {
        bungleEn = new BungleEn();
        bungleUa = new BungleUa();
    }

    @Test
    void testGetContentsEn() {

        Object[][] contents = bungleEn.getContents();
        assertNotNull(contents);
        for (Object[] content : contents) {
            assertNotNull(content);
        }
    }

    @Test
    void testGetContentsUa() {
        Object[][] contents = bungleUa.getContents();
        assertNotNull(contents);
        for (Object[] content : contents) {
            assertNotNull(content);
        }
    }
    @Test
    void getContentsUaVsGetContentsEn() {
        Object[][] contentsUa = bungleUa.getContents();
        Object[][] contentsEn = bungleEn.getContents();

        IntStream.range(0, contentsEn.length)
                .forEach(i -> assertEquals(contentsEn[i][0], contentsUa[i][0]));
    }
}