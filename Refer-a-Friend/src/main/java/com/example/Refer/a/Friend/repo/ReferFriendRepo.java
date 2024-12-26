package com.example.Refer.a.Friend.repo;

import com.example.Refer.a.Friend.entity.ReferFriend;
import com.example.Refer.a.Friend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReferFriendRepo extends JpaRepository<ReferFriend,Integer> {
    Optional<ReferFriend> findByReferralCode(String referralCode);
    List<ReferFriend> findByStatus(String status);

}
