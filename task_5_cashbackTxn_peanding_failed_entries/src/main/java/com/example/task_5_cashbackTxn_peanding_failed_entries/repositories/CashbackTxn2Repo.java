package com.example.task_5_cashbackTxn_peanding_failed_entries.repositories;

import com.example.task_5_cashbackTxn_peanding_failed_entries.entities.CashbackTxn1;
import com.example.task_5_cashbackTxn_peanding_failed_entries.entities.CashbackTxn2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CashbackTxn2Repo extends JpaRepository<CashbackTxn2,Integer> {
    List<CashbackTxn2> findByStatus(String status);
}
