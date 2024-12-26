package com.example.Refer.a.Friend.service.impl;

import com.example.Refer.a.Friend.entity.User;
import com.example.Refer.a.Friend.payloads.Notification;
import com.example.Refer.a.Friend.repo.UserRepo;
import org.springframework.scheduling.annotation.Scheduled;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class NotificationService {
    UserRepo userRepo;

    public Notification notification(int userId,String name){
        Notification notification=new Notification();
        notification.setTitle("Get Cashback"+userId);
        notification.setContent("Dear" +name+" Complete Your First Transaction");
        System.out.println(notification);
        return notification;

    }

    @Scheduled(cron = "0 60 * * * ?")
    public void sendNotification(){
        System.out.println("Fetching entries for sending notification");
        LocalDateTime cutoffDate = LocalDateTime.now().minusHours(24);
        Timestamp cutoffTimestamp = Timestamp.valueOf(cutoffDate);

        LocalDateTime cutoffDate1 = LocalDateTime.now().minusHours(23);
        Timestamp cutoffTimestamp1 = Timestamp.valueOf(cutoffDate);

        List<User> userList=userRepo.findByTimestampBetween(cutoffTimestamp,cutoffTimestamp);
        for (User user:userList){
            notification(user.getId(),user.getName());
        }

        System.out.println("Fetching entries for sending notification");
        LocalDateTime cutoffDate2 = LocalDateTime.now().minusHours(47);
        Timestamp cutoffTimestamp2 = Timestamp.valueOf(cutoffDate);

        LocalDateTime cutoffDate3= LocalDateTime.now().minusHours(48);
        Timestamp cutoffTimestamp3 = Timestamp.valueOf(cutoffDate);

        List<User> userList1=userRepo.findByTimestampBetween(cutoffTimestamp2, cutoffTimestamp2);
        for (User user:userList){
            notification(user.getId(),user.getName());
        }



    }
}
