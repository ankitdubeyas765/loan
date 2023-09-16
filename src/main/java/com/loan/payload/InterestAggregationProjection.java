package com.loan.payload;

public interface InterestAggregationProjection {
    double getInterestRate();
    double getTotalRemainingAmount();
    double getTotalInterest();
    double getTotalPenalty();
}
