package com.enterprise.rental.service;

import com.enterprise.rental.entity.Car;
import com.enterprise.rental.entity.User;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface UserService extends Service<User> {
    static String addSalt(User user) {
        String salt = generateUUID();
        user.setSalt(salt);
        return salt;
    }

    static String saltedPassword(String rawPassword, String salt) {
        return DigestUtils.sha256Hex(getBytes(String.format("%s%s", rawPassword, salt)));
    }

    static byte[] getBytes(String saltedPassword) {
        return saltedPassword.getBytes(StandardCharsets.UTF_8);
    }

    static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    boolean save(User user);

    List<User> getAll();

    List<User> getAll(String query);

    List<User> getAll(Map<String, String> params);

    Optional<User> findByName(@NotNull String name);

    Optional<User> getById(long id);

    User bookCar(@NotNull Car car, @NotNull User user);

    User edit(User user);

    boolean delete(long id);

    boolean sendEmail(String name);

    void setUserToken(HttpServletRequest request, HttpServletResponse response, HttpSession session);
}
