package com.example.Project.services.impl;
import com.example.Project.entities.User;
import com.example.Project.exception.EmailExists;
import com.example.Project.exception.EmailNotValid;
import com.example.Project.exception.ResourceNotFoundException;
import com.example.Project.payloads.UserDto;
import com.example.Project.repositories.UserRepo;
import com.example.Project.services.UserServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;
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
        int status=0;

        List<User> userList= userRepo.findAll();
        if(userDto.getEmail().endsWith(".com")) {
            for (User user1 : userList) {
                if (Objects.equals(user1.getEmail(), user.getEmail())) {
                    status = 1;
                    break;

                }
            }
            if (status == 0) {
                List<String> role = new ArrayList<>();
                role.add("ROLE_NORMAL_USER");
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                user.setRole(role);
                User savedUser = userRepo.save(user);
                return userToDto(savedUser);
            } else {
                throw new EmailExists("email", userDto.getEmail());

            }
        }else {

            throw new EmailNotValid(user.getEmail());

        }

    }


    @Override
    public UserDto update(UserDto userDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        int status=0;
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", " email " + email, 0));



        List<User> userList= userRepo.findAll();

        if(userDto.getEmail().endsWith(".com")) {
            if(!Objects.equals(user.getEmail(),userDto.getEmail())) {


                for (User user1 : userList) {

                    if (Objects.equals(user1.getEmail(), userDto.getEmail())) {
                        status = 1;
                        break;

                    }
                }
            }
            else {
                status=0;
            }


            if (status == 0) {


                List<String> role = new ArrayList<>();
                role.add("ROLE_NORMAL_USER");
                user.setRole(role);
                user.setName(userDto.getName());
                user.setEmail(userDto.getEmail());
                user.setAbout(userDto.getAbout());

                if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
                    user.setPassword(passwordEncoder.encode(userDto.getPassword()));
                }

                User updatedUser = userRepo.save(user);
                return userToDto(updatedUser);
            } else {
                throw new EmailExists("email", userDto.getEmail());

            }
        }else {

            throw new EmailNotValid(userDto.getEmail());


        }

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
    public void deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", " email "+email, 0));
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
