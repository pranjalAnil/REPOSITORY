package com.example.task_5_cashbackTxn_peanding_failed_entries.services;

import com.example.task_5_cashbackTxn_peanding_failed_entries.Constant.AppConstants;
import com.example.task_5_cashbackTxn_peanding_failed_entries.entities.*;
import com.example.task_5_cashbackTxn_peanding_failed_entries.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class CashbackTxnServices {
    @Autowired
    CashbackTxn1Repo cashbackTxn1Repo;

    @Autowired
    CashbackTxn2Repo cashbackTxn2Repo;

    @Autowired
    CashbackTxn3Repo cashbackTxn3Repo;

    @Autowired
    CashbackTxn4Repo cashbackTxn4Repo;

    @Autowired
    CashbackTxn5Repo cashbackTxn5Repo;


    private Timestamp timestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public CashbackTxn1 cashbackTxn1Request(CashbackTxn1 cashbackTxn1) {
        cashbackTxn1.setCreatedAt(timestamp());
        cashbackTxn1.setStatus(AppConstants.REQ_CREATION_STATUS);
        cashbackTxn1.setCBELIGIBLE("ELIGIBLE");
        return cashbackTxn1Repo.save(cashbackTxn1);
    }

    public CashbackTxn2 cashbackTxn2Request(CashbackTxn2 cashbackTxn2) {
        cashbackTxn2.setCreatedAt(timestamp());
        cashbackTxn2.setStatus(AppConstants.REQ_CREATION_STATUS);
        cashbackTxn2.setCBELIGIBLE("ELIGIBLE");
        return cashbackTxn2Repo.save(cashbackTxn2);
    }

    public CashbackTxn3 cashbackTxn3Request(CashbackTxn3 cashbackTxn3) {
        cashbackTxn3.setCreatedAt(timestamp());
        cashbackTxn3.setStatus(AppConstants.REQ_CREATION_STATUS);
        cashbackTxn3.setCBELIGIBLE("ELIGIBLE");
        return cashbackTxn3Repo.save(cashbackTxn3);
    }

    public CashbackTxn4 cashbackTxn4Request(CashbackTxn4 cashbackTxn4) {
        cashbackTxn4.setCreatedAt(timestamp());
        cashbackTxn4.setStatus(AppConstants.REQ_CREATION_STATUS);
        cashbackTxn4.setCBELIGIBLE("ELIGIBLE");
        return  cashbackTxn4Repo.save(cashbackTxn4);
    }

    public CashbackTxn5 cashbackTxn5Request(CashbackTxn5 cashbackTxn5) {
        cashbackTxn5.setCreatedAt(timestamp());
        cashbackTxn5.setStatus(AppConstants.REQ_CREATION_STATUS);
        cashbackTxn5.setCBELIGIBLE("ELIGIBLE");
        return  cashbackTxn5Repo.save(cashbackTxn5);
    }

    @Scheduled(cron = "0 00 04 * * ?")
    public void acceptPendingRequest() {
        System.out.println("Fetching pending requests");
        List<CashbackTxn1> pendingReq = cashbackTxn1Repo.findByStatus(AppConstants.REQ_CREATION_STATUS);
        List<CashbackTxn1> failedReq = cashbackTxn1Repo.findByStatus(AppConstants.REQ_REJECTED_STATUS);

        if (!pendingReq.isEmpty()) {
            for (CashbackTxn1 cashbackTxn1 : pendingReq) {
                cashbackTxn1.setStatus(AppConstants.REQ_ACCEPTED_STATUS);
                cashbackTxn1.setUpdatedAt(timestamp());
                cashbackTxn1Repo.save(cashbackTxn1);

            }

        }
        if (!failedReq.isEmpty()) {
            for (CashbackTxn1 cashbackTxn1 : failedReq) {
                cashbackTxn1.setStatus(AppConstants.REQ_ACCEPTED_STATUS);
                cashbackTxn1.setUpdatedAt(timestamp());
                cashbackTxn1Repo.save(cashbackTxn1);
            }

        }

        List<CashbackTxn2> pendingReq2 = cashbackTxn2Repo.findByStatus(AppConstants.REQ_CREATION_STATUS);
        List<CashbackTxn2> failedReq2 = cashbackTxn2Repo.findByStatus(AppConstants.REQ_CREATION_STATUS);

        if (!pendingReq2.isEmpty()) {
            for (CashbackTxn2 cashbackTxn2 : pendingReq2) {
                cashbackTxn2.setStatus(AppConstants.REQ_ACCEPTED_STATUS);
                cashbackTxn2.setUpdatedAt(timestamp());
                cashbackTxn2Repo.save(cashbackTxn2);

            }

        }
        if (!failedReq2.isEmpty()) {
            for (CashbackTxn2 cashbackTxn2 : failedReq2) {
                cashbackTxn2.setStatus(AppConstants.REQ_ACCEPTED_STATUS);
                cashbackTxn2.setUpdatedAt(timestamp());
                cashbackTxn2Repo.save(cashbackTxn2);
            }

        }
        List<CashbackTxn3> pendingReq3 = cashbackTxn3Repo.findByStatus(AppConstants.REQ_CREATION_STATUS);
        List<CashbackTxn3> failedReq3 = cashbackTxn3Repo.findByStatus(AppConstants.REQ_CREATION_STATUS);

        if (!pendingReq3.isEmpty()) {
            for (CashbackTxn3 cashbackTxn3 : pendingReq3) {
                cashbackTxn3.setStatus(AppConstants.REQ_ACCEPTED_STATUS);
                cashbackTxn3.setUpdatedAt(timestamp());
                cashbackTxn3Repo.save(cashbackTxn3);

            }

        }
        if (!failedReq.isEmpty()) {
            for (CashbackTxn3 cashbackTxn3 : failedReq3) {
                cashbackTxn3.setStatus(AppConstants.REQ_ACCEPTED_STATUS);
                cashbackTxn3.setUpdatedAt(timestamp());
                cashbackTxn3Repo.save(cashbackTxn3);
            }

        }

        List<CashbackTxn4> pendingReq4 = cashbackTxn4Repo.findByStatus(AppConstants.REQ_CREATION_STATUS);
        List<CashbackTxn4> failedReq4 = cashbackTxn4Repo.findByStatus(AppConstants.REQ_CREATION_STATUS);


        if (!pendingReq4.isEmpty()) {
            for (CashbackTxn4 cashbackTxn4 : pendingReq4) {
                cashbackTxn4.setStatus(AppConstants.REQ_ACCEPTED_STATUS);
                cashbackTxn4.setUpdatedAt(timestamp());
                cashbackTxn4Repo.save(cashbackTxn4);

            }

        }
        if (!failedReq.isEmpty()) {
            for (CashbackTxn4 cashbackTxn4 : failedReq4) {
                cashbackTxn4.setStatus(AppConstants.REQ_ACCEPTED_STATUS);
                cashbackTxn4.setUpdatedAt(timestamp());
                cashbackTxn4Repo.save(cashbackTxn4);
            }

        }

        List<CashbackTxn5> pendingReq5 = cashbackTxn5Repo.findByStatus(AppConstants.REQ_CREATION_STATUS);
        List<CashbackTxn5> failedReq5 = cashbackTxn5Repo.findByStatus(AppConstants.REQ_CREATION_STATUS);

        if (!pendingReq5.isEmpty()) {
            for (CashbackTxn5 cashbackTxn5 : pendingReq5) {
                cashbackTxn5.setStatus(AppConstants.REQ_ACCEPTED_STATUS);
                cashbackTxn5.setUpdatedAt(timestamp());
                cashbackTxn5Repo.save(cashbackTxn5);

            }

        }
        if (!failedReq5.isEmpty()) {
            for (CashbackTxn5 cashbackTxn5 : failedReq5) {
                cashbackTxn5.setStatus(AppConstants.REQ_ACCEPTED_STATUS);
                cashbackTxn5.setUpdatedAt(timestamp());
                cashbackTxn5Repo.save(cashbackTxn5);
            }

        }


        System.out.println("Task Completed");


    }


}
