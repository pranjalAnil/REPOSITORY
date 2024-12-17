package com.example.Refer.a.Friend.repo;

import com.example.Refer.a.Friend.entity.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContactsRepo extends JpaRepository<Contacts,Integer> {
    @Query("SELECT u.mobileNumber FROM Contacts u")
    List<String> findAllMobileNumbers();
}
