package com.loan.repository;

import com.loan.entity.Loan;
import com.loan.payload.CustomerAggregationProjection;
import com.loan.payload.InterestAggregationProjection;
import com.loan.payload.LoanAggregationProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan,String> {
    List<Loan> findByCustomerId(String customerId);
    List<Loan> findByLenderId(String lenderId);
    @Query(value = "SELECT lender_id AS lenderId, " +
            "SUM(remaining_amount) AS totalRemainingAmount, " +
            "SUM(interest_per_day) AS totalInterest, " +
            "SUM(penalty_per_day) AS totalPenalty " +
            "FROM loans GROUP BY lender_id", nativeQuery = true)
    List<LoanAggregationProjection> aggregateLoansByLender();
    @Query(value = "SELECT customer_id AS customerId, " +
            "SUM(remaining_amount) AS totalRemainingAmount, " +
            "SUM(interest_per_day) AS totalInterest, " +
            "SUM(penalty_per_day) AS totalPenalty " +
            "FROM loans GROUP BY customer_id", nativeQuery = true)
    List<CustomerAggregationProjection> aggregateLoansByCustomer();
    @Query(value = "SELECT interest_per_day AS interestRate, " +
            "SUM(remaining_amount) AS totalRemainingAmount, " +
            "SUM(interest_per_day) AS totalInterest, " +
            "SUM(penalty_per_day) AS totalPenalty " +
            "FROM loans GROUP BY interest_per_day", nativeQuery = true)
    List<InterestAggregationProjection> aggregateLoansByInterest();
}
