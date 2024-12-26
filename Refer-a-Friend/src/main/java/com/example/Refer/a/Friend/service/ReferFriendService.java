package com.example.Refer.a.Friend.service;

import com.example.Refer.a.Friend.entity.ReferFriend;
import com.example.Refer.a.Friend.payloads.InviteMessage;
import java.util.List;

public interface ReferFriendService {
    public InviteMessage inviteFriend(String mobileNumber,int refereeId);
    public InviteMessage reInviteFriend(int inviteId);
    public List<ReferFriend> getPendingRequest(int userId);
}
