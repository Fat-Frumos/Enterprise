package com.enterprise.rental.dao;

import com.enterprise.rental.entity.User;
import com.enterprise.rental.service.UserService;
import com.enterprise.rental.service.impl.DefaultUserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserDaoTest {

    HttpServletRequest request = mock(HttpServletRequest.class);
    @Mock
    UserDao service = mock(UserDao.class);
    HttpSession session = mock(HttpSession.class);

    @Test
    void testDaoService() {

        User bob = new User.Builder()
                .userId(11L)
                .name("Bob")
                .password("password")
                .passport("passport")
                .salt("salt")
                .language("ua")
                .email("email@i.ua")
                .active(true)
                .role("user")
                .build();


        when(request.getParameter("login")).thenReturn("name");
        when(request.getParameter("password")).thenReturn("password");
        when(service.findByName("Bob")).thenReturn(Optional.of(bob));
        when(request.getSession()).thenReturn(session);

        UserService userService = new DefaultUserService(service);
        Optional<User> optionalUser = userService.getByName("Bob");

        User user = optionalUser.get();

//        verify(session).setAttribute("user", bob);
        assertEquals(bob, user);
    }

}