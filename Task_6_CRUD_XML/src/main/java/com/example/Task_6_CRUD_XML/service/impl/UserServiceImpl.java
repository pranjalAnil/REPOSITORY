package com.example.Task_6_CRUD_XML.service.impl;

import com.example.Task_6_CRUD_XML.entity.User;
import com.example.Task_6_CRUD_XML.exception.ResourceNotFoundException;
import com.example.Task_6_CRUD_XML.payload.UserDto;
import com.example.Task_6_CRUD_XML.repository.UserRepo;
import com.example.Task_6_CRUD_XML.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepo;
    @Override
    public UserDto addUser(UserDto userDto) {
        User user=new User();
        BeanUtils.copyProperties(userDto,user);
        user= userRepo.save(user);
        BeanUtils.copyProperties(user,userDto);
        return userDto;

    }

    @Override
    public UserDto updateUser(UserDto userDto, int id) {
        User user=userRepo.findById(id).orElseThrow(()->
                new ResourceNotFoundException("User","ID",id)
        );
        user.setUserName(userDto.getUserName());
        user.setMobileNumber(userDto.getMobileNumber());
        User user1= userRepo.save(user);
        BeanUtils.copyProperties(user1,userDto);
        return userDto;

    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users=userRepo.findAll();
        List<UserDto> userDtos=users.stream().map(user -> {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user, userDto);
            return userDto;
        }).collect(Collectors.toList());

        return userDtos;
    }

    @Override
    public String deleteUser(int id) {
        User user=userRepo.findById(id).orElseThrow(()->
                new ResourceNotFoundException("User","ID",id)
        );
        userRepo.delete(user);
        return "User Deleted";
    }
}
