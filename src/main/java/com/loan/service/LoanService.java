package com.loan.service;

import com.loan.entity.Loan;
import com.loan.payload.CustomerAggregationProjection;
import com.loan.payload.InterestAggregationProjection;
import com.loan.payload.LoanAggregationProjection;
import com.loan.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;
    private static final Logger logger = LoggerFactory.getLogger(LoanService.class);

    public Loan addLoan(Loan loan) {
        return loanRepository.save(loan);
    }
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }
    public Loan getLoanById(String loanId) {
        Optional<Loan> optionalLoan = loanRepository.findById(loanId);
        if (optionalLoan.isPresent()) {
            return optionalLoan.get();
        } else {
            throw new RuntimeException("Loan not found with ID: " + loanId);
        }
    }
    public List<Loan> getLoansByCustomerId(String customerId) {
        return loanRepository.findByCustomerId(customerId);
    }
    public List<Loan> getLoansByLenderId(String lenderId) {
        return loanRepository.findByLenderId(lenderId);
    }
    public List<LoanAggregationProjection> aggregateLoansByLender() {
        return loanRepository.aggregateLoansByLender();
    }
    public List<CustomerAggregationProjection> aggregateLoansByCustomer() {
        return loanRepository.aggregateLoansByCustomer();
    }
    public List<InterestAggregationProjection> aggregateLoansByInterest() {
        return loanRepository.aggregateLoansByInterest();
    }
    @Scheduled(fixedRate = 86400000)
    public void checkDueDatesAndLogAlerts() {
        LocalDate currentDate = LocalDate.now();
        List<Loan> loans = loanRepository.findAll();

        for (Loan loan : loans) {
            if (currentDate.isAfter(loan.getDueDate())) {
                logger.warn("Loan with ID {} has crossed the due date.", loan.getLoanId());
            }
        }}
}