package com.enterprise.rental.exception;

import com.enterprise.rental.service.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CarNotFoundExceptionTest {

    @InjectMocks
    CarService service;

    @Test
    void whenDerivedExceptionThrown_thenAssertionSucceeds() {
        Exception exception = assertThrows(CarNotFoundException.class, () -> {
            service.getById(5L);
        });

        String expectedMessage = "Car not found";
        String actualMessage = exception.getMessage();
        assertTrue(exception instanceof CarNotFoundException);
        assertEquals(expectedMessage, actualMessage);
    }
}