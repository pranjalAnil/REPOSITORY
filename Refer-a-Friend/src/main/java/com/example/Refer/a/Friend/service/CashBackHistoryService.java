package com.example.Refer.a.Friend.service;

import com.example.Refer.a.Friend.entity.CashBackHistoryReferred;
import com.example.Refer.a.Friend.entity.User;
import com.example.Refer.a.Friend.payloads.CashBackSummary;

import java.util.List;

public interface CashBackHistoryService {
    public String addCashBackRequest(User referral,User  referee);
    public List<CashBackHistoryReferred> cashBackHistory(int userId);
    public CashBackSummary getCashBackSummary(int userId);
}
