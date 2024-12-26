package com.example.Refer.a.Friend.repo;
import com.example.Refer.a.Friend.entity.CashBackHistoryReferral;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface CashBackHistoryReferralRepo extends JpaRepository<CashBackHistoryReferral,Integer> {
    List<CashBackHistoryReferral> findByReferralUser(int userId);
    List<CashBackHistoryReferral> findByStatus(String status);
}
