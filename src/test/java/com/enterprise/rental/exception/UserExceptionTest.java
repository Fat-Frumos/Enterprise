package com.enterprise.rental.exception;

import com.enterprise.rental.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserExceptionTest {

    @InjectMocks
    UserService service;

    @Test
    void whenDerivedExceptionThrown_thenAssertionSucceeds() {
        Exception exception = assertThrows(UserException.class, () -> {
            service.findByName("456L");
        });

        String expectedMessage = "User not found";
        String actualMessage = exception.getMessage();
        assertTrue(exception instanceof UserException);
        assertEquals(expectedMessage, actualMessage);
    }
}
