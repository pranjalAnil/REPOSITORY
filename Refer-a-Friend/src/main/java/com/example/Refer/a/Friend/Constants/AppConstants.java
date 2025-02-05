package com.example.Refer.a.Friend.Constants;

public class AppConstants {
    public static final String REQ_CREATION_STATUS="PENDING";
    public static final String REQ_ACCEPTED_STATUS="SUCCESS";
    public static final String REQ_REJECTED_STATUS="FAILED";

    public static double refereeCashBackCalculation(double firstTransactionAmount,double per){
        return (per*firstTransactionAmount)/100;
    }
}
