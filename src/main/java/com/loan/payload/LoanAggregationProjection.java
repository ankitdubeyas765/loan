package com.loan.payload;

public interface LoanAggregationProjection {
    String getLenderId();
    double getTotalRemainingAmount();
    double getTotalInterest();
    double getTotalPenalty();
}

