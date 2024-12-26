package com.example.Refer.a.Friend.controller;

import com.example.Refer.a.Friend.entity.ReferFriend;
import com.example.Refer.a.Friend.payloads.InviteMessage;
import com.example.Refer.a.Friend.service.ReferFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/refer")
public class ReferFriendController {
    @Autowired
    ReferFriendService referFriendService;

    @PostMapping("invite/contact/{mobileNumber}/referee/{userId}")
    public ResponseEntity<InviteMessage> inviteFriend(@PathVariable String mobileNumber,@PathVariable int userId){
        return new ResponseEntity<>(referFriendService.inviteFriend(mobileNumber,userId),HttpStatus.OK);

    }
    @PutMapping("reInvite/{inviteId}")
    public ResponseEntity<InviteMessage> reInviteFriend(@PathVariable int inviteId){
        return new ResponseEntity<>(referFriendService.reInviteFriend(inviteId),HttpStatus.OK);
    }
    @GetMapping("/getPendingReq/{userId}")
    public ResponseEntity<List<ReferFriend>>pendingRequest(@PathVariable int userId){
        return new ResponseEntity<>(referFriendService.getPendingRequest(userId),HttpStatus.OK);
    }
}
