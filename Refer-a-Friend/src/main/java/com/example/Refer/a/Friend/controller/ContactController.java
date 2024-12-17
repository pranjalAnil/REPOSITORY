package com.example.Refer.a.Friend.controller;

import com.example.Refer.a.Friend.entity.Contacts;
import com.example.Refer.a.Friend.exceptions.AccessDenied;
import com.example.Refer.a.Friend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contacts")
public class ContactController {
    @Autowired
    private UserService userService;
    @PutMapping("user/{userId}/permission/{contactsPermission}")
    public ResponseEntity<String> giveContactPermission(@PathVariable boolean contactsPermission,@PathVariable int userId){
        return new ResponseEntity<>(userService.contactPermission(contactsPermission,userId),HttpStatus.OK);

    }
    @GetMapping("/getContacts/{userId}")
    public ResponseEntity<?>accessContactsWhoAreNotOnBHIM(@PathVariable int userId){
            return new ResponseEntity<>(userService.getContactsNotOnBoarded(userId), HttpStatus.OK);

    }
//    @PostMapping("/add")
//    public ResponseEntity<?>addContacts(@RequestBody Contacts contacts){
//        return new ResponseEntity<>()
//    }


}
