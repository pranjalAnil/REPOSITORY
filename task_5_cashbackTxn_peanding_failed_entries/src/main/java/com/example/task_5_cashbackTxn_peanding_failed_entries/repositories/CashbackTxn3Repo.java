package com.example.task_5_cashbackTxn_peanding_failed_entries.repositories;

import com.example.task_5_cashbackTxn_peanding_failed_entries.entities.CashbackTxn1;
import com.example.task_5_cashbackTxn_peanding_failed_entries.entities.CashbackTxn3;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CashbackTxn3Repo extends JpaRepository<CashbackTxn3,Integer> {
    List<CashbackTxn3> findByStatus(String status);
}
