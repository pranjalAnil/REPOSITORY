package com.example.task_5_cashbackTxn_peanding_failed_entries.repositories;

import com.example.task_5_cashbackTxn_peanding_failed_entries.entities.CashbackTxn1;
import com.example.task_5_cashbackTxn_peanding_failed_entries.entities.CashbackTxn4;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CashbackTxn4Repo extends JpaRepository<CashbackTxn4,Integer> {
    List<CashbackTxn4> findByStatus(String status);
}
