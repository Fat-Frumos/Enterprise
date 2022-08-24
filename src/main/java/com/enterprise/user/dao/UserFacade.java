package com.enterprise.user.dao;

import com.enterprise.user.entity.UserDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class UserFacade {
    private UserRepository userRepository;
    private UserMapper mapper;

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(mapper::toDto).collect(toList());
//        return userRepo.findAll().stream().map(this::toDto).collect(toList());
    }
}
