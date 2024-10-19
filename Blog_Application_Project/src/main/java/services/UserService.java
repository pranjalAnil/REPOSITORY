package services;

import payloads.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser (UserDto userDto);
    UserDto update(UserDto userDto, Integer userId);
    UserDto getUserById(Integer userid);
    List<UserDto>getAllUsers();
    void deleteUser(Integer userId);


}
