package com.enterprise.rental.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileInputStream;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class DataExceptionTest {

    @InjectMocks
    Properties properties;

    @Test
    void whenDerivedExceptionThrown_thenAssertionSucceeds() {
        Exception exception = assertThrows(DataException.class, () -> {

            FileInputStream inputStream = new FileInputStream("file.txt");
            properties.load(inputStream);

        });

        String expectedMessage = "Cannot close connection";
        String actualMessage = exception.getMessage();
        assertTrue(exception instanceof CarNotFoundException);
        assertEquals(expectedMessage, actualMessage);
    }
}