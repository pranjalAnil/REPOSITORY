package com.example.Task_x_11_FCM_Notification.controllers;
import com.example.Task_x_11_FCM_Notification.services.FcmService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final FcmService fcmService;

    public NotificationController(FcmService fcmService) {
        this.fcmService = fcmService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendNotification(
            @RequestParam String title,
            @RequestParam String body,
            @RequestParam String token) {

        String response = fcmService.sendNotification(title, body, token);
        return ResponseEntity.ok(response);
    }
}
