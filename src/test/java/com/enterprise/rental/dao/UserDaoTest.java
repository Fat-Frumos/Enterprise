package com.enterprise.rental.dao;

import com.enterprise.rental.dao.jdbc.JdbcUserDao;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.service.UserService;
import com.enterprise.rental.service.impl.DefaultUserService;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static com.enterprise.rental.entity.Role.USER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserDaoTest {

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpSession session = mock(HttpSession.class);
    JdbcUserDao mockDao = mock(JdbcUserDao.class);
    UserService service = new DefaultUserService(mockDao);

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
                .role(USER.role())
                .build();


        when(request.getParameter("login")).thenReturn("name");
        when(request.getParameter("password")).thenReturn("password");
        when(service.findByName("Bob")).thenReturn(Optional.of(bob));
        when(request.getSession()).thenReturn(session);
        Optional<User> optionalUser = service.findByName("Bob");

        User user = optionalUser.get();

        assertEquals(bob, user);
    }
}