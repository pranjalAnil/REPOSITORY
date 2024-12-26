package com.example.Refer.a.Friend.service.impl;
import com.example.Refer.a.Friend.Constants.AppConstants;
import com.example.Refer.a.Friend.entity.CashBackHistoryReferral;
import com.example.Refer.a.Friend.entity.CashBackHistoryReferred;
import com.example.Refer.a.Friend.entity.User;
import com.example.Refer.a.Friend.exceptions.ResourceNotFoundException;
import com.example.Refer.a.Friend.payloads.CashBackSummary;
import com.example.Refer.a.Friend.repo.CashBackHistoryReferralRepo;
import com.example.Refer.a.Friend.repo.CashBackHistoryReferredRepo;
import com.example.Refer.a.Friend.repo.UsedReferralCodeRepo;
import com.example.Refer.a.Friend.repo.UserRepo;
import com.example.Refer.a.Friend.service.CashBackHistoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.List;


@Service
public class CashBackHistoryServiceImpl implements CashBackHistoryService {
    @Autowired
    CashBackHistoryReferredRepo cashBackHistoryReferredRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    CashBackHistoryReferralRepo cashBackHistoryReferralRepo;

    @Autowired
    UsedReferralCodeRepo usedReferralCodeRepo;


    private Timestamp timestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    @Override
    public String addCashBackRequest(User referral, User referee) {

        CashBackHistoryReferred cashBackHistoryReferred = new CashBackHistoryReferred();
        CashBackHistoryReferral cashBackHistoryReferral=new CashBackHistoryReferral();

        cashBackHistoryReferred.setCbAmount(AppConstants.CASH_BACK_AMOUNT_flat);
        cashBackHistoryReferred.setCreatedAt(timestamp());
        cashBackHistoryReferred.setReferralCode(referee.getReferralCode());
        cashBackHistoryReferred.setReferredUser(referral.getId());
        cashBackHistoryReferred.setReferralUser(referee.getId());
        cashBackHistoryReferred.setStatus(AppConstants.REQ_CREATION_STATUS);
        cashBackHistoryReferred.setReferralCode(referee.getReferralCode());

        BeanUtils.copyProperties(cashBackHistoryReferred,cashBackHistoryReferral);
        cashBackHistoryReferral.setCbAmount(10);
        cashBackHistoryReferredRepo.save(cashBackHistoryReferred);
        cashBackHistoryReferralRepo.save(cashBackHistoryReferral);
        return "Saved cashback history successfully";
    }

    @Override
    public List<CashBackHistoryReferred> cashBackHistory(int userId) {
        List<CashBackHistoryReferred> cashBackHistoryReferredList = cashBackHistoryReferredRepo.findByReferredUser(userId);
        List<CashBackHistoryReferral> cashBackHistoryReferralFinal = cashBackHistoryReferralRepo.findByReferralUser(userId);

        for(CashBackHistoryReferral cashBackHistoryReferral:cashBackHistoryReferralFinal){
            CashBackHistoryReferred cashBackHistoryReferred1=new CashBackHistoryReferred();
            BeanUtils.copyProperties(cashBackHistoryReferral,cashBackHistoryReferred1);
            cashBackHistoryReferredList.add(cashBackHistoryReferred1);
        }
        return cashBackHistoryReferredList;
    }

    @Override
    public CashBackSummary getCashBackSummary(int userId) {
        List<CashBackHistoryReferred> cashBackHistoryReferred = cashBackHistoryReferredRepo.findByReferredUser(userId);
        List<CashBackHistoryReferral> cashBackHistoryReferral = cashBackHistoryReferralRepo.findByReferralUser(userId);
        cashBackHistoryReferral.removeIf(c -> !(c.getStatus().equals(AppConstants.REQ_ACCEPTED_STATUS)));
        double totalAmount = 0;
        int noOfCashBack = 0;
        for (CashBackHistoryReferral cashBackHistoryReferral1 : cashBackHistoryReferral) {
            totalAmount += cashBackHistoryReferral1.getCbAmount();
            noOfCashBack++;
        }

        cashBackHistoryReferred.removeIf(c -> !(c.getStatus().equals(AppConstants.REQ_ACCEPTED_STATUS)));
        for (CashBackHistoryReferred cashBackHistoryReferred1 : cashBackHistoryReferred) {
            totalAmount += cashBackHistoryReferred1.getCbAmount();
            noOfCashBack++;
        }

        CashBackSummary cashBackSummary = new CashBackSummary();
        cashBackSummary.setNoOfCashBack(noOfCashBack);
        cashBackSummary.setTotalAmount(totalAmount);
        return cashBackSummary;
    }


    @Scheduled(cron = "0 0 4 * * ?")
    public void acceptPendingRequest() {

        System.out.println("Fetching pending requests");
        List<CashBackHistoryReferred> pendingReq = cashBackHistoryReferredRepo.findByStatus(AppConstants.REQ_CREATION_STATUS);
        List<CashBackHistoryReferred> failedReq = cashBackHistoryReferredRepo.findByStatus(AppConstants.REQ_REJECTED_STATUS);

        if (!pendingReq.isEmpty()) {
            for (CashBackHistoryReferred cashbackTxn1 : pendingReq) {

                User user = userRepo.findById(cashbackTxn1.getReferredUser()).orElseThrow(
                        () -> new ResourceNotFoundException("user", "userId", "" + cashbackTxn1.getReferralUser())
                );
                if (user.isFirstTransaction() && user.getTransactionAmount() >= 150) {
                    cashbackTxn1.setStatus(AppConstants.REQ_ACCEPTED_STATUS);
                    cashbackTxn1.setUpdatedAt(timestamp());
                    cashBackHistoryReferredRepo.save(cashbackTxn1);
                }

            }

        }
        if (!failedReq.isEmpty()) {
            for (CashBackHistoryReferred cashbackTxn1 : failedReq) {

                User user = userRepo.findById(cashbackTxn1.getReferredUser()).orElseThrow(
                        () -> new ResourceNotFoundException("user", "userId", "" + cashbackTxn1.getReferralUser())
                );
                if (user.isFirstTransaction()) {
                    cashbackTxn1.setStatus(AppConstants.REQ_ACCEPTED_STATUS);
                    cashbackTxn1.setUpdatedAt(timestamp());
                    cashBackHistoryReferredRepo.save(cashbackTxn1);
                }

            }
        }


        List<CashBackHistoryReferral> pendingReqReferral = cashBackHistoryReferralRepo.findByStatus(AppConstants.REQ_CREATION_STATUS);
        List<CashBackHistoryReferral> failedReqReferral = cashBackHistoryReferralRepo.findByStatus(AppConstants.REQ_REJECTED_STATUS);


        if (!pendingReq.isEmpty()) {
            for (CashBackHistoryReferral cashbackTxn1 : pendingReqReferral) {

                User referred = userRepo.findById(cashbackTxn1.getReferredUser()).orElseThrow(
                        () -> new ResourceNotFoundException("user", "userId", "" + cashbackTxn1.getReferralUser())
                );
                User referee = userRepo.findById(cashbackTxn1.getReferralUser()).orElseThrow(
                        () -> new ResourceNotFoundException("user", "userId", "" + cashbackTxn1.getReferralUser())
                );
                double cbAmount_current= AppConstants.refereeCashBackCalculation(referred.getTransactionAmount());
                if(cbAmount_current>150){
                    cbAmount_current=150;
                }
                if (referred.isFirstTransaction()) {
                    List<CashBackHistoryReferral> cashBackHistoryReferralList = cashBackHistoryReferralRepo.findByReferralUser(referee.getId());
                    double cbAmount = 0;
                    if (referee.isReferred()) {
                        List<CashBackHistoryReferred> cashBackHistoryReferredList = cashBackHistoryReferredRepo.findByReferredUser(referred.getId());
                        cashBackHistoryReferredList.removeIf(c -> !(c.getStatus().equals(AppConstants.REQ_ACCEPTED_STATUS)));
                        for (CashBackHistoryReferred cashBackHistoryReferred : cashBackHistoryReferredList) {
                            cbAmount += cashBackHistoryReferred.getCbAmount();
                        }
                    }
                    cashBackHistoryReferralList.removeIf(c -> !(c.getStatus().equals(AppConstants.REQ_ACCEPTED_STATUS)));
                    for (CashBackHistoryReferral cashBackHistoryReferral : cashBackHistoryReferralList) {
                        cbAmount += cashBackHistoryReferral.getCbAmount();
                    }

                    if (cbAmount < AppConstants.MAX_CB_CAP && cbAmount_current <= AppConstants.MAX_CB_CAP) {
                        cashbackTxn1.setCbAmount(cbAmount_current);
                        cashbackTxn1.setStatus(AppConstants.REQ_ACCEPTED_STATUS);
                        cashbackTxn1.setUpdatedAt(timestamp());
                        cashBackHistoryReferralRepo.save(cashbackTxn1);
                    } else {
                        cashbackTxn1.setCbAmount(0);
                        cashbackTxn1.setStatus(AppConstants.REQ_ACCEPTED_STATUS);
                        cashbackTxn1.setUpdatedAt(timestamp());
                        cashBackHistoryReferralRepo.save(cashbackTxn1);

                    }
                }

            }

        }
        if (!failedReqReferral.isEmpty()) {
            for (CashBackHistoryReferral cashbackTxn1 : failedReqReferral) {
                User referred = userRepo.findById(cashbackTxn1.getReferredUser()).orElseThrow(
                        () -> new ResourceNotFoundException("user", "userId", "" + cashbackTxn1.getReferralUser())
                );
                User referee = userRepo.findById(cashbackTxn1.getReferralUser()).orElseThrow(
                        () -> new ResourceNotFoundException("user", "userId", "" + cashbackTxn1.getReferralUser())
                );
                double cbAmount_current= AppConstants.refereeCashBackCalculation(referred.getTransactionAmount());
                if(cbAmount_current>AppConstants.ONE_TIME_CASHBACK_CAP){
                    cbAmount_current=AppConstants.ONE_TIME_CASHBACK_CAP;
                }
                if (referred.isFirstTransaction()) {
                    List<CashBackHistoryReferral> cashBackHistoryReferralList = cashBackHistoryReferralRepo.findByReferralUser(referee.getId());
                    double cbAmount = 0;
                    if (referee.isReferred()) {
                        List<CashBackHistoryReferred> cashBackHistoryReferredList = cashBackHistoryReferredRepo.findByReferredUser(referred.getId());
                        cashBackHistoryReferredList.removeIf(c -> !(c.getStatus().equals(AppConstants.REQ_ACCEPTED_STATUS)));
                        for (CashBackHistoryReferred cashBackHistoryReferred : cashBackHistoryReferredList) {
                            cbAmount += cashBackHistoryReferred.getCbAmount();
                        }
                    }
                    cashBackHistoryReferralList.removeIf(c -> !(c.getStatus().equals(AppConstants.REQ_ACCEPTED_STATUS)));
                    for (CashBackHistoryReferral cashBackHistoryReferral : cashBackHistoryReferralList) {
                        cbAmount += cashBackHistoryReferral.getCbAmount();
                    }


                    if (cbAmount < AppConstants.MAX_CB_CAP && cbAmount_current <= AppConstants.MAX_CB_CAP) {
                        cashbackTxn1.setCbAmount(cbAmount_current);
                        cashbackTxn1.setStatus(AppConstants.REQ_ACCEPTED_STATUS);
                        cashbackTxn1.setUpdatedAt(timestamp());
                        cashBackHistoryReferralRepo.save(cashbackTxn1);
                    } else {
                        cashbackTxn1.setCbAmount(0);
                        cashbackTxn1.setStatus(AppConstants.REQ_ACCEPTED_STATUS);
                        cashbackTxn1.setUpdatedAt(timestamp());
                        cashBackHistoryReferralRepo.save(cashbackTxn1);
                    }
                }

            }
        }


    }

}





