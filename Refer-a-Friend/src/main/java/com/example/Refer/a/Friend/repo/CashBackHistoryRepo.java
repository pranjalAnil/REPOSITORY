package com.example.Refer.a.Friend.repo;

import com.example.Refer.a.Friend.entity.CashBackHistory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CashBackHistoryRepo extends JpaRepository<CashBackHistory,Integer> {
    List<CashBackHistory> findByReferredUser(int userId);
    List<CashBackHistory> findByReferralUser(int userId);
    List<CashBackHistory> findByStatus(String status);

}
