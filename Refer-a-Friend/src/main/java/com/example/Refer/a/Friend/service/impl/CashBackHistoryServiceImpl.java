package com.example.Refer.a.Friend.service.impl;
import com.example.Refer.a.Friend.Constants.AppConstants;
import com.example.Refer.a.Friend.entity.CashBackHistory;
import com.example.Refer.a.Friend.entity.User;
import com.example.Refer.a.Friend.repo.CashBackHistoryRepo;
import com.example.Refer.a.Friend.repo.UsedReferralCodeRepo;
import com.example.Refer.a.Friend.service.CashBackHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.List;

@Service
public class CashBackHistoryServiceImpl implements CashBackHistoryService{
    @Autowired
    CashBackHistoryRepo cashBackHistoryRepo;

    @Autowired
    UsedReferralCodeRepo usedReferralCodeRepo;

    private Timestamp timestamp(){
        return new Timestamp(System.currentTimeMillis());
    }
    @Override
    public String addCashBackRequest(User referredUser,User referralUser) {
        CashBackHistory cashBackHistory=new CashBackHistory();
        cashBackHistory.setCbAmount(AppConstants.CASH_BACK_AMOUNT);
        cashBackHistory.setCreatedAt(timestamp());
        cashBackHistory.setReferralCode(referralUser.getReferralCode());
        cashBackHistory.setReferredUser(referredUser.getId());
        cashBackHistory.setReferralUser(referralUser.getId());
        cashBackHistory.setStatus(AppConstants.REQ_CREATION_STATUS);

        cashBackHistory.setReferralCode(referralUser.getReferralCode());
        cashBackHistoryRepo.save(cashBackHistory);
        return "Saved cashback history successfully";
    }

    @Override
    public List<CashBackHistory> cashBackHistory(int userId) {
        List<CashBackHistory> cashBackHistoryList= cashBackHistoryRepo.findByReferredUser(userId);
        List<CashBackHistory> cashBackHistoryFinal=cashBackHistoryRepo.findByReferralUser(userId);
        cashBackHistoryFinal.addAll(cashBackHistoryList);
        return cashBackHistoryFinal;
    }

    @Scheduled(cron = "0 00 04 * * ?")
    public void acceptPendingRequest() {
        System.out.println("Fetching pending requests");
        List<CashBackHistory> pendingReq = cashBackHistoryRepo.findByStatus(AppConstants.REQ_CREATION_STATUS);
        List<CashBackHistory> failedReq = cashBackHistoryRepo.findByStatus(AppConstants.REQ_REJECTED_STATUS);

        if (!pendingReq.isEmpty()) {
            for (CashBackHistory cashbackTxn1 : pendingReq) {
                cashbackTxn1.setStatus(AppConstants.REQ_ACCEPTED_STATUS);
                cashbackTxn1.setUpdatedAt(timestamp());
                cashBackHistoryRepo.save(cashbackTxn1);

            }

        }
        if (!failedReq.isEmpty()) {
            for (CashBackHistory cashbackTxn1 : failedReq) {
                cashbackTxn1.setStatus(AppConstants.REQ_ACCEPTED_STATUS);
                cashbackTxn1.setUpdatedAt(timestamp());
                cashBackHistoryRepo.save(cashbackTxn1);
            }

        }

    }


}
