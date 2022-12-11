package com.enterprise.rental.service;

import com.enterprise.rental.dao.mapper.UserMapper;
import com.enterprise.rental.entity.Session;
import com.enterprise.rental.entity.User;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.UUID;

public class SecurityService {
    private final UserService userService;
    private final Session session;

    public SecurityService(UserService userService) {
        this.userService = userService;
        this.session = new Session();
    }

    private void addSalt(User user) {
        String salt = generateUUID();
        user.setSalt(salt);
        String encoded = encode(user.getPassword(), salt);
        user.setPassword(encoded);
    }

    private String generateToken(Long id) {
        User userFromDb = userService.getById(id).orElseThrow();
        Map<User, String> userTokens = session.getUserTokens();
        userTokens.put(userFromDb, generateUUID());
        session.setUserTokens(userTokens);
        session.setExpired(true);
        return session.getToken();
    }

    private String encode(String rawPassword, String salt) {
        return DigestUtils.sha256Hex((rawPassword + salt).getBytes(StandardCharsets.UTF_8));
    }

    private void register(User user) {
        generateToken(user.getUserId());
        addSalt(user);
        userService.save(user);
    }

    public void setUserToken(HttpServletRequest request, HttpServletResponse response, Session session) {
        UserMapper ROW_MAPPER = new UserMapper();
        User user = ROW_MAPPER.userMapper(request).orElseThrow();
        session.setUser(user);

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user-token")) {
                session.setToken((cookie.getValue()));
            } else {
                cookie = new Cookie("user-token", generateUUID());
                cookie.setMaxAge(300);
                session.getUser().setActive(true);
                session.setToken(cookie.getValue());
                response.addCookie(cookie);
            }
        }
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
