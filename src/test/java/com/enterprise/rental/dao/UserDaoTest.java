package com.enterprise.rental.dao;

import com.enterprise.rental.dao.jdbc.DbManager;
import com.enterprise.rental.dao.jdbc.JdbcUserDao;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.exception.DataException;
import com.enterprise.rental.service.UserService;
import com.enterprise.rental.service.impl.DefaultUserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static com.enterprise.rental.dao.jdbc.Constants.FILTER_USER_BY_ID_SQL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class UserDaoTest {
    @Mock
    DataSource mockDataSource;
    @Mock
    Connection mockConn;
    @Mock
    PreparedStatement preparedStatement;
    @Mock
    ResultSet mockResultSet;
    int userId = 100;
    HttpServletRequest request = mock(HttpServletRequest.class);
    UserDao service = mock(UserDao.class);
    HttpSession session = mock(HttpSession.class);

    @Test
    void testLoginService() {

        //TODO
        UserDao userDao = new JdbcUserDao();
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

        List<User> listClient = userDao.findAll();
        assertNotNull(listClient);
        assertEquals(11, listClient.size());

        when(request.getParameter("login")).thenReturn("name");
        when(request.getParameter("password")).thenReturn("password");
        when(service.findByName("Bob").get()).thenReturn(bob);
        when(request.getSession()).thenReturn(session);

        UserService userService = new DefaultUserService(service);

        Optional<User> optionalUser = userService.findByName(request.getParameter("name"));

        System.out.println(optionalUser);

        verify(session).setAttribute("user", bob);
    }


    @Test
    void findUserByIdTest() throws DataException, SQLException {
        User bob = new User.Builder()
                .userId(11L)
                .name("Bob")
                .password("password")
                .passport("passport")
                .language("ua")
                .email("email@i.ua")
                .active(true)
                .role("user")
                .build();

        ResultSet resultSet = mock(ResultSet.class);
        DbManager manager = mock(DbManager.class);

        when(resultSet.next())
                .thenReturn(true)
                .thenReturn(false);

        String name = resultSet.getString("name");
        String email = resultSet.getString("email");
        String role = resultSet.getString("role");
        String password = resultSet.getString("password");
        String language = resultSet.getString("language");
//        boolean active = resultSet.getBoolean("active");
//        boolean closed = resultSet.getBoolean("closed");


        when(name).thenReturn(bob.getName());
//        when(role).thenReturn(bob.getRole());
        when(email).thenReturn(bob.getEmail());
        when(password).thenReturn(bob.getPassword());
        when(language).thenReturn(bob.getLanguage());
//        when(active).thenReturn(bob.isActive());
//        when(closed).thenReturn(bob.isClosed());

        PreparedStatement statement = mock(PreparedStatement.class);
        when(statement.executeQuery()).thenReturn(resultSet);

        Connection connection = mock(Connection.class);
        when(connection.prepareStatement(FILTER_USER_BY_ID_SQL)).thenReturn(statement);

        when(manager.getConnection()).thenReturn(connection);

        UserDao userDAO = mock(UserDao.class);
        when(DbManager.getInstance().getConnection()).thenReturn(connection);

        when(userDAO.findById(bob.getUserId())).thenCallRealMethod();
//        when(userDAO.createUser(resultSet)).thenCallRealMethod();
//        when(userDAO.getRole(bob.getId())).thenReturn(bob.getRole());

        User actualUser = userDAO.findById(bob.getUserId()).get();
        assertEquals(bob, actualUser);
    }
    @Test
    public void testCreateWithNoExceptions() throws SQLException {
        when(mockDataSource.getConnection()).thenReturn(mockConn);
        when(mockDataSource.getConnection(anyString(), anyString())).thenReturn(mockConn);
        doNothing().when(mockConn).commit();
        when(mockConn.prepareStatement(anyString(), anyInt())).thenReturn(preparedStatement);
        doNothing().when(preparedStatement).setString(anyInt(), anyString());
        when(preparedStatement.execute()).thenReturn(Boolean.TRUE);
        when(preparedStatement.getGeneratedKeys()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        when(mockResultSet.getInt(100)).thenReturn(userId);

        UserDao userDao = new JdbcUserDao();

        //verify and assert
        verify(mockConn, times(1)).prepareStatement(anyString(), anyInt());
        verify(preparedStatement, times(6)).setString(anyInt(), anyString());
        verify(preparedStatement, times(1)).execute();
        verify(mockConn, times(1)).commit();
        verify(mockResultSet, times(2)).next();
        verify(mockResultSet, times(1)).getInt(100);
    }
}