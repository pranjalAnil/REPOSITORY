package com.example.Task_x_11_FCM_Notification.services;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class FCMService {

    public String sendNotification(String title, String body, String token) {
        try {
            Message message = Message.builder()
                    .putData("title", title)
                    .putData("body", body)
                    .setToken(token) // Device token
                    .build();

            return FirebaseMessaging.getInstance().send(message);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send FCM notification", e);
        }
    }
}
