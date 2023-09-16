package com.loan.controller;

import com.loan.entity.Loan;
import com.loan.payload.CustomerAggregationProjection;
import com.loan.payload.InterestAggregationProjection;
import com.loan.payload.LoanAggregationProjection;
import com.loan.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
            //http://localhost:8080/loans/add
    private LoanService loanService;
    @PostMapping("/add")
    public Loan addLoan(@RequestBody Loan loan) {
        return loanService.addLoan(loan);
    }
    //http://localhost:8080/loans
    @GetMapping
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }
    //http://localhost:8080/loans/{loanId}
    @GetMapping("/{loanId}")
    public Loan getLoanById(@PathVariable String loanId) {
        return loanService.getLoanById(loanId);
    }
    //http://localhost:8080/loans/customer/{customerId}
    @GetMapping("/customer/{customerId}")
    public List<Loan> getLoansByCustomerId(@PathVariable String customerId) {
        return loanService.getLoansByCustomerId(customerId);
    }
    //http://localhost:8080/loans/lender/{lenderId}
    @GetMapping("/lender/{lenderId}")
    public List<Loan> getLoansByLenderId(@PathVariable String lenderId) {
        return loanService.getLoansByLenderId(lenderId);
    }
    //http://localhost:8080/loans/aggregate/lender
    @GetMapping("/aggregate/lender")
    public List<LoanAggregationProjection> aggregateLoansByLender() {
        return loanService.aggregateLoansByLender();
    }
    //http://localhost:8080/loans/aggregate/customer
    @GetMapping("/aggregate/customer")
    public List<CustomerAggregationProjection> aggregateLoansByCustomer() {
        return loanService.aggregateLoansByCustomer();
    }
    //http://localhost:8080/loans/aggregate/interest
    @GetMapping("/aggregate/interest")
    public List<InterestAggregationProjection> aggregateLoansByInterest() {
        return loanService.aggregateLoansByInterest();
    }
}
