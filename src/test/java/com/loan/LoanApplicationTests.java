package com.loan;

import com.loan.entity.Loan;
import com.loan.repository.LoanRepository;
import com.loan.service.LoanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class LoanApplicationTests {

	@Autowired
	private LoanService loanService;

	@MockBean
	private LoanRepository loanRepository;

	@Test
	public void testAddLoan() {
		Loan mockLoan = new Loan();

		mockLoan.setLoanId("L1");
		mockLoan.setCustomerId("C1");
		mockLoan.setLenderId("LEN1");
		mockLoan.setAmount(30000.0);
		mockLoan.setRemainingAmount(30000.0);
		mockLoan.setPaymentDate(LocalDate.of(2023, 6, 15));
		mockLoan.setInterestPerDay(1.0);
		mockLoan.setDueDate(LocalDate.of(2023, 7, 15));
		mockLoan.setPenaltyPerDay(0.01);
		mockLoan.setCancelled(false);

		when(loanRepository.save(mockLoan)).thenReturn(mockLoan);

		Loan addedLoan = loanService.addLoan(mockLoan);

		assertNotNull(addedLoan);
		assertEquals("L1", addedLoan.getLoanId());



	}
}

