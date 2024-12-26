package com.example.Refer.a.Friend.repo;

import com.example.Refer.a.Friend.entity.CashBackHistoryReferred;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CashBackHistoryReferredRepo extends JpaRepository<CashBackHistoryReferred,Integer> {
    List<CashBackHistoryReferred> findByReferredUser(int userId);
    List<CashBackHistoryReferred> findByStatus(String status);

}
