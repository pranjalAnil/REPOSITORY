package com.example.Refer.a.Friend.service.impl;

import com.example.Refer.a.Friend.Constants.AppConstants;
import com.example.Refer.a.Friend.entity.ReferFriend;
import com.example.Refer.a.Friend.payloads.Transaction;
import com.example.Refer.a.Friend.entity.User;
import com.example.Refer.a.Friend.exceptions.*;
import com.example.Refer.a.Friend.payloads.UserInputDTO;
import com.example.Refer.a.Friend.payloads.UserOutputDto;
import com.example.Refer.a.Friend.repo.ContactsRepo;
import com.example.Refer.a.Friend.repo.ReferFriendRepo;
import com.example.Refer.a.Friend.repo.UserRepo;
import com.example.Refer.a.Friend.service.CashBackHistoryService;
import com.example.Refer.a.Friend.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    @Autowired
    private ReferFriendRepo referFriendRepo;


    @Override
    public UserOutputDto OnboardUser(UserInputDTO userInputDTO) {
        if (userInputDTO.getPassword().equals(userInputDTO.getConfirmPassword())) {
            User referral = new User();
            BeanUtils.copyProperties(userInputDTO, referral);
            try {
                String uuId = UUID.randomUUID().toString();
                String code = uuId.replace("-", "").substring(0, 6);
                referral.setReferralCode(code);

            } catch (CodeGenerationError codeGenerationError) {
                throw new CodeGenerationError();
            }

            int status = 1;
            if (userInputDTO.getReferredCode() != null) {
                referral.setReferred(true);

                ReferFriend referFriend = referFriendRepo.findByReferralCode(userInputDTO.getReferredCode()).orElseThrow(
                        () -> new ResourceNotFoundException("Referral user", "referral code", userInputDTO.getReferredCode())
                );
                User referee = userRepo.findById(referFriend.getRefereeId()).orElseThrow(
                        () -> new ResourceNotFoundException("user", "UserId", "" + referFriend.getRefereeId())
                );

                if (referFriend.getReferredContact().equals(userInputDTO.getMobileNumber())) {
                    try {
                        userRepo.save(referral);
                    } catch (DataIntegrityViolationException e) {
                        throw new DataIntegrityViolationException("Mobile number must be unique. This number is already in use: " + userInputDTO.getMobileNumber());
                    }

                    cashBackHistoryService.addCashBackRequest(referral, referee);

                    UserOutputDto userOutputDto = new UserOutputDto();
                    BeanUtils.copyProperties(referral, userOutputDto);
                    referFriend.setStatus(AppConstants.REQ_ACCEPTED_STATUS);
                    referFriendRepo.save(referFriend);
                    return userOutputDto;
                } else {
                    throw new AccessDenied();
                }

            } else {
                try {
                    userRepo.save(referral);
                } catch (DataIntegrityViolationException e) {
                    throw new DataIntegrityViolationException("Mobile number must be unique. This number is already in use: " + userInputDTO.getMobileNumber());
                }
                UserOutputDto userOutputDto = new UserOutputDto();
                BeanUtils.copyProperties(referral, userOutputDto);
                return userOutputDto;

            }


        } else {
            throw new WrongConfirmationPassword();
        }
    }

    @Override
    public String contactPermission(boolean permission, int userId) {
        User user = userRepo.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("user", "userId", "" + userId)
        );

        if (permission) {
            user.setContactsPermission(true);
            userRepo.save(user);
            return "contacts permitted";

        }
        return "you will not be able to access your contacts";

    }

    @Override
    public List<String> getContactsNotOnBoarded(int userId) {
        User user = userRepo.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("user", "UserID", "" + userId)
        );
        if (user.isContactsPermission()) {
            try {
                List<String> contactsList = contactsRepo.findAllMobileNumbers();
                System.out.println(contactsList);
                List<String> userList = userRepo.findAllMobileNumbers();
                System.out.println(userList);
                List<String> finalList = new ArrayList<>();
                for (String con : contactsList) {
                    if (userList.contains(con)) {
                        System.out.println("present" + con);


                    } else {
                        System.out.println("not present" + con);
                        finalList.add(con);
                    }

                }

                return finalList;
            } catch (ContactAccessIssue contactAccessIssue) {
                throw new ContactAccessIssue();
            }
        } else {
            throw new AccessDenied("Access Denied");
        }
    }

    @Override
    public Transaction transaction(Transaction transaction) {
        User user=userRepo.findById(transaction.getUser()).orElseThrow(
                ()->new ResourceNotFoundException("User","userId",transaction.getUser()+"")
        );
        user.setTransactionAmount(transaction.getAmount()+user.getTransactionAmount());
        user.setFirstTransaction(true);
        userRepo.save(user);
        return transaction;
    }




}
