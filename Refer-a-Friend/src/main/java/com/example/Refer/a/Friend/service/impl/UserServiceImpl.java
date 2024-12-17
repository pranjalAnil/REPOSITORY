package com.example.Refer.a.Friend.service.impl;
import com.example.Refer.a.Friend.entity.User;
import com.example.Refer.a.Friend.exceptions.AccessDenied;
import com.example.Refer.a.Friend.exceptions.CodeGenerationError;
import com.example.Refer.a.Friend.exceptions.ResourceNotFoundException;
import com.example.Refer.a.Friend.payloads.UserInputDTO;
import com.example.Refer.a.Friend.payloads.UserOutputDto;
import com.example.Refer.a.Friend.repo.ContactsRepo;
import com.example.Refer.a.Friend.repo.UserRepo;
import com.example.Refer.a.Friend.service.CashBackHistoryService;
import com.example.Refer.a.Friend.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CashBackHistoryService cashBackHistoryService;
    @Autowired
    private ContactsRepo contactsRepo;

    @Override
    public UserOutputDto OnboardUser(UserInputDTO userInputDTO) {
        if (userInputDTO.getPassword().equals(userInputDTO.getConfirmPassword())) {
            User referredUser = new User();
            BeanUtils.copyProperties(userInputDTO, referredUser);
            try {
                String uuId = UUID.randomUUID().toString();
                String code = uuId.replace("-", "").substring(0, 6);
                referredUser.setReferralCode(code);

            }catch (CodeGenerationError codeGenerationError){
                throw new CodeGenerationError();
            }

            List<User> userList = userRepo.findAll();
            int status = 1;

                if (userInputDTO.getReferredCode() != null) {
                    User referalUser=userRepo.findByReferralCode(userInputDTO.getReferredCode()).orElseThrow(
                            ()->new ResourceNotFoundException("Referral user","referral code",userInputDTO.getReferredCode())
                    );

                    userRepo.save(referredUser);
                    cashBackHistoryService.addCashBackRequest(referredUser,referalUser);

                    UserOutputDto userOutputDto = new UserOutputDto();
                    BeanUtils.copyProperties(referredUser, userOutputDto);

                    return userOutputDto;

                } else {
                    userRepo.save(referredUser);
                    UserOutputDto userOutputDto = new UserOutputDto();
                    BeanUtils.copyProperties(referredUser, userOutputDto);
                    return userOutputDto;

                }


        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public String contactPermission(boolean permission, int userId) {
        User user=userRepo.findById(userId).orElseThrow(
                ()->new ResourceNotFoundException("user","userId",""+userId)
        );

        if (permission){
            user.setContactsPermission(true);
            userRepo.save(user);
            return "contacts permitted";

        }
        return "you will not be able to access your contacts";

    }

    @Override
    public List<String> getContactsNotOnBoarded(int userId) {
        User user= userRepo.findById(userId).orElseThrow(
                ()->new ResourceNotFoundException("user","UserID",""+userId)
        );
        if(user.isContactsPermission()) {
            List<String> contactsList = contactsRepo.findAllMobileNumbers();
            System.out.println(contactsList);
            List<String> userList = userRepo.findAllMobileNumbers();
            System.out.println(userList);
            List<String> finalList =new ArrayList<>();
            for (String con:contactsList){
                if(userList.contains(con)){
                    System.out.println("present"+con);


                }else {
                    System.out.println("not present"+con);
                    finalList.add(con);
                }

            }

            return finalList;
        }else {
            throw new AccessDenied("Access Denied");
        }
    }


}
