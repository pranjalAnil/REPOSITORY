package com.example.Refer.a.Friend.service;

import com.example.Refer.a.Friend.entity.CashBackHistory;
import com.example.Refer.a.Friend.entity.User;

import java.util.List;

public interface CashBackHistoryService {
    public String addCashBackRequest(User referredUser,User  referalUser);
    public List<CashBackHistory> cashBackHistory(int userId);
}
