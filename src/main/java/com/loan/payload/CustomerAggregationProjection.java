package com.loan.payload;

public interface CustomerAggregationProjection {
    String getCustomerId();
    double getTotalRemainingAmount();
    double getTotalInterest();
    double getTotalPenalty();
}
