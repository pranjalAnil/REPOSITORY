package com.example.Project.services.impl;

import com.example.Project.config.AppConstant;
import com.example.Project.entities.User;
import com.example.Project.exception.ResourceNotFoundException;
import com.example.Project.payloads.UserDto;
import com.example.Project.repositories.UserRepo;
import com.example.Project.services.UserServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserServices {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = dtoToUser(userDto);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoleList(Set.of("ROLE_USER"));
        User savedUser = userRepo.save(user);
        return userToDto(savedUser);
    }

    @Override
    public UserDto update(UserDto userDto, Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());

        if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
            user.setPassword(userDto.getPassword()); // Hash this in a real app
        }

        User updatedUser = userRepo.save(user); // Persist changes
        return userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId));
        return userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> userList = userRepo.findAll();
        return userList.stream().map(this::userToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId));
        this.userRepo.delete(user);
    }

    private User dtoToUser(UserDto userDto) {
//        User user = new User();
//        user.setId(userDto.getId()); // Be cautious with this when creating new users
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword()); // Hash this in a real app
//        user.setAbout(userDto.getAbout());
        return modelMapper.map(userDto,User.class);
    }

    private UserDto userToDto(User user) {
//        UserDto userDto = new UserDto();
//        userDto.setId(user.getId()); // Ensure the ID is included
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword()); // Be cautious with exposing this
//        userDto.setAbout(user.getAbout());
//        return userDto;

        return modelMapper.map(user,UserDto.class);
    }
}
