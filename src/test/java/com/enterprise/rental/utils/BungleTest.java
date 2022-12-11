package com.enterprise.rental.utils;

import com.enterprise.rental.utils.locale.BungleEn;
import com.enterprise.rental.utils.locale.BungleUa;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BungleTest {

    @Test
    void getContents() {
        BungleEn bungle = new BungleEn();
        Object[][] contents = bungle.getContents();
        System.out.println(contents);
        assertNotNull(contents);
        for (Object[] content : contents) {
            assertNotNull(content);
        }
    }

        @Test
        void getContentsUa() {
            BungleUa bungle = new BungleUa();
            Object[][] contents = bungle.getContents();
            System.out.println(contents);
            assertNotNull(contents);
            for (Object[] content : contents) {
                assertNotNull(content);
            }
        }
}