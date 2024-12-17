package com.example.Refer.a.Friend.service;
import com.example.Refer.a.Friend.payloads.UserInputDTO;
import com.example.Refer.a.Friend.payloads.UserOutputDto;

import java.util.List;


public interface UserService {
    public UserOutputDto OnboardUser(UserInputDTO userInputDTO);
    public String contactPermission(boolean permission,int userId);
    public List<String> getContactsNotOnBoarded(int userId);

}
