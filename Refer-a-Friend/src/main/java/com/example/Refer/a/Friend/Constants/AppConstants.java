package com.example.Refer.a.Friend.Constants;

public class AppConstants {
    public static final String REQ_CREATION_STATUS="PENDING";
    public static final String REQ_ACCEPTED_STATUS="SUCCESS";
    public static final String REQ_REJECTED_STATUS="FAILED";
    public static final int CASH_BACK_AMOUNT_flat =50;
    public static final int ONE_TIME_CASHBACK_CAP=150;
    public static final int MAX_CB_CAP=2000;

    public static double refereeCashBackCalculation(double firstTransactionAmount){
        return 0.2*firstTransactionAmount;
    }
}
