package com.enterprise.user.dao;

import com.enterprise.user.entity.User;

import java.util.List;

interface UserRepository {
    public List<User> findAll();
}
