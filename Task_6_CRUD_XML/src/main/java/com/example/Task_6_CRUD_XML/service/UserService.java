package com.example.Task_6_CRUD_XML.service;

import com.example.Task_6_CRUD_XML.payload.UserDto;

import java.util.List;

public interface UserService {
    public UserDto addUser(UserDto userDto);
    public UserDto updateUser(UserDto userDto,int id);
    public List<UserDto> getAllUsers();
    public String deleteUser(int id);
}
