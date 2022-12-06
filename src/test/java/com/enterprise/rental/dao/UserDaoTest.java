//package com.enterprise.rental.dao;
//
//import com.enterprise.rental.dao.jdbc.DbManager;
//import com.enterprise.rental.entity.User;
//import com.enterprise.rental.exception.DataException;
//import com.enterprise.rental.service.UserService;
//import org.junit.jupiter.api.Test;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Optional;
//
//import static com.enterprise.rental.dao.jdbc.Constants.FILTER_USER_BY_ID_SQL;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class UserDaoTest {
//
//    @Test
//    void testLoginService() throws SQLException {
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        UserDao service = mock(UserDao.class);
//        HttpSession session = mock(HttpSession.class);
//
//        User bob = new User.Builder()
//                .userId(11L)
//                .name("Bob")
//                .password("password")
//                .passport("passport")
//                .salt("salt")
//                .language("ua")
//                .email("email@i.ua")
//                .active(true)
//                .role("user")
//                .build();
//
//        when(request.getParameter("login")).thenReturn("name");
//        when(request.getParameter("password")).thenReturn("password");
//        when(service.findByName("Bob").get()).thenReturn(bob);
//        when(request.getSession()).thenReturn(session);
//
//        UserService userService = new UserService(service);
//
//        Optional<User> optionalUser = userService.findByName(request.getParameter("name"));
//
//        System.out.println(optionalUser);
//
//        verify(session).setAttribute("user", bob);
//    }
//
//
//    @Test
//    void findUserByIdTest() throws DataException, SQLException {
//        User bob = new User.Builder()
//                .userId(11L)
//                .name("Bob")
//                .password("password")
//                .passport("passport")
//                .language("ua")
//                .email("email@i.ua")
//                .active(true)
//                .role("user")
//                .build();
//
//        ResultSet resultSet = mock(ResultSet.class);
//        DbManager manager = mock(DbManager.class);
//
//        when(resultSet.next())
//                .thenReturn(true)
//                .thenReturn(false);
//
//        String name = resultSet.getString("name");
//        String email = resultSet.getString("email");
//        String role = resultSet.getString("role");
//        String password = resultSet.getString("password");
//        String language = resultSet.getString("language");
////        boolean active = resultSet.getBoolean("active");
////        boolean closed = resultSet.getBoolean("closed");
//
//
//        when(name).thenReturn(bob.getName());
////        when(role).thenReturn(bob.getRole());
//        when(email).thenReturn(bob.getEmail());
//        when(password).thenReturn(bob.getPassword());
//        when(language).thenReturn(bob.getLanguage());
////        when(active).thenReturn(bob.isActive());
////        when(closed).thenReturn(bob.isClosed());
//
//        PreparedStatement statement = mock(PreparedStatement.class);
//        when(statement.executeQuery()).thenReturn(resultSet);
//
//        Connection connection = mock(Connection.class);
//        when(connection.prepareStatement(FILTER_USER_BY_ID_SQL)).thenReturn(statement);
//
//        when(manager.getConnection()).thenReturn(connection);
//
//        UserDao userDAO = mock(UserDao.class);
//        when(DbManager.getInstance().getConnection()).thenReturn(connection);
//
//        when(userDAO.findById(bob.getUserId())).thenCallRealMethod();
////        when(userDAO.createUser(resultSet)).thenCallRealMethod();
////        when(userDAO.getRole(bob.getId())).thenReturn(bob.getRole());
//
//        User actualUser = userDAO.findById(bob.getUserId()).get();
//        assertEquals(bob, actualUser);
//    }
//
//}