package com.example.task_5_cashbackTxn_peanding_failed_entries.repositories;

import com.example.task_5_cashbackTxn_peanding_failed_entries.entities.CashbackTxn1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CashbackTxn1Repo extends JpaRepository<CashbackTxn1,Integer> {
    List<CashbackTxn1> findByStatus(String status);
}
