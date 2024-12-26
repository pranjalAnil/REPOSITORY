package com.example.Refer.a.Friend.service.impl;
import com.example.Refer.a.Friend.Constants.AppConstants;
import com.example.Refer.a.Friend.entity.ReferFriend;
import com.example.Refer.a.Friend.entity.User;
import com.example.Refer.a.Friend.exceptions.AlreadyExists;
import com.example.Refer.a.Friend.exceptions.ResourceNotFoundException;
import com.example.Refer.a.Friend.exceptions.UnableToAccess;
import com.example.Refer.a.Friend.payloads.InviteMessage;
import com.example.Refer.a.Friend.repo.ReferFriendRepo;
import com.example.Refer.a.Friend.repo.UserRepo;
import com.example.Refer.a.Friend.service.ReferFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class ReferFriendServiceImpl implements ReferFriendService {
    @Autowired
    ReferFriendRepo referFriendRepo;

    @Autowired
    UserRepo userRepo;



    private InviteMessage inviteMessageGeneration(String mobileNumber,String referralCode){
        InviteMessage inviteMessage=new InviteMessage();
        inviteMessage.setTitle("CLICK ON SIGNUP WITH BLOW REFERRAL CODE AND GET CASHBACK**");
        inviteMessage.setContent("Hello "+mobileNumber+" Sign up using my referral code and enjoy exciting cashback!" +
                "Click here to join now: [App Link]\n" +
                "\n" +
                " Don’t miss out! Use my referral code: "+referralCode+
                "\n" +
                "Let’s start earning together! " +
                "\n");
        inviteMessage.setReferralCode(referralCode);
        return inviteMessage;
    }

    @Override
    public InviteMessage inviteFriend(String mobileNumber,int refereeId) {
        try {
            ReferFriend referFriend = new ReferFriend();
            User user = userRepo.findById(refereeId).orElseThrow(
                    () -> new ResourceNotFoundException("user", "userId", "" + refereeId)
            );
            List<String> mobileNumber1=userRepo.findAllMobileNumbers();
            System.out.println(mobileNumber1);
            if(!mobileNumber1.contains(mobileNumber)) {
                referFriend.setReferredContact(mobileNumber);
                referFriend.setStatus(AppConstants.REQ_CREATION_STATUS);
                referFriend.setRefereeId(refereeId);
                referFriend.setReferralCode(user.getReferralCode());
                referFriendRepo.save(referFriend);
                System.out.println(user + "Saved");
                String old = user.getReferralCode();
                user.setReferralCode(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6));
                userRepo.save(user);
                System.out.println(user);
                return inviteMessageGeneration(mobileNumber, old);
            }else {
                throw new AlreadyExists();
            }
        }
        catch (UnableToAccess unableToAccess){
            throw new UnableToAccess();
        }
    }



    @Override
    public InviteMessage reInviteFriend(int inviteId) {
        try {
            ReferFriend referFriend = referFriendRepo.findById(inviteId).orElseThrow(
                    () -> new ResourceNotFoundException("ReInvite", "inviteId", inviteId + "")
            );

            if (referFriend.getStatus().equals(AppConstants.REQ_CREATION_STATUS)) {
                User user = userRepo.findById(referFriend.getRefereeId()).orElseThrow(
                        () -> new ResourceNotFoundException("user", "userId", "" + referFriend)
                );
                referFriend.setReferralCode(user.getReferralCode());
                referFriendRepo.save(referFriend);
                System.out.println(user + "Saved");
                String old = user.getReferralCode();
                user.setReferralCode(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6));
                userRepo.save(user);
                System.out.println(user);
                return inviteMessageGeneration(referFriend.getReferredContact(), old);
            } else {
                throw new AlreadyExists();
            }
        }
        catch (UnableToAccess unableToAccess){
            throw new UnableToAccess();
        }
    }

    @Override
    public List<ReferFriend> getPendingRequest(int userId) {
        List<ReferFriend> listOfPendingReq=referFriendRepo.findByStatus(AppConstants.REQ_CREATION_STATUS);
        listOfPendingReq.removeIf(l-> (l.getRefereeId()!=userId));
        return listOfPendingReq;
    }



}
