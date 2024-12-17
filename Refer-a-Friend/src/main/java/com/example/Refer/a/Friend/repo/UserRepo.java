package com.example.Refer.a.Friend.repo;

import com.example.Refer.a.Friend.entity.CashBackHistory;
import com.example.Refer.a.Friend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {
   Optional<User> findByReferralCode(String referralCode);
   @Query("SELECT u.mobileNumber FROM User u")
   List<String> findAllMobileNumbers();

}
