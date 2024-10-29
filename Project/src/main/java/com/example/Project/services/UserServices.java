package com.example.Project.services;

import com.example.Project.payloads.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserServices {
    UserDto createUser (UserDto userDto);
    UserDto update(UserDto userDto);
    UserDto getUserById(Integer userid);
    List<UserDto> getAllUsers();
    void deleteUser();
}
