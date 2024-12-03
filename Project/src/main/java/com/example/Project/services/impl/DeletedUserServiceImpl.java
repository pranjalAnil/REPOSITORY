package com.example.Project.services.impl;

import com.example.Project.entities.DeletedUsers;
import com.example.Project.entities.User;
import com.example.Project.repositories.DeletedUsersRepo;
import com.example.Project.services.DeletedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeletedUserServiceImpl  implements DeletedUserService {
    @Autowired
    private DeletedUsersRepo deletedUsersRepo;

    private Timestamp timestamp(){
        return new Timestamp(System.currentTimeMillis());
    }

    @Override
    public String deleteUser(User user) {

        DeletedUsers deletedUsers=new DeletedUsers();
        deletedUsers.setEmail(user.getEmail());
        deletedUsers.setRole(user.getRole());
        deletedUsers.setName(user.getName());
        deletedUsers.setPassword(user.getPassword());
        deletedUsers.setTimestamp(timestamp());
        deletedUsersRepo.save(deletedUsers);
        return "Your account will permanently deleted after 30 days";
    }
    @Scheduled(cron = "0 0 01 * * ?")
    public void deleteAccountPermanently(){
        LocalDateTime cutoffDate = LocalDateTime.now().minusDays(30);
        Timestamp cutoffTimestamp = Timestamp.valueOf(cutoffDate);
        List<DeletedUsers> deletedUsersList= deletedUsersRepo.findByTimestampBefore(cutoffTimestamp);
        deletedUsersRepo.deleteAll(deletedUsersList);
        System.out.println("account deleted permanently");

    }

}
