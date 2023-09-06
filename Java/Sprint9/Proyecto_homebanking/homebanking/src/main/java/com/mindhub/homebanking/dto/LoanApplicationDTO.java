package com.mindhub.homebanking.dto;

public class LoanApplicationDTO {
    private Long loanId;
    private Double amount;
    private Integer payments;
    private String accountNumberDest;

    public Long getLoanId() {
        return loanId;
    }

    public Double getAmount() {
        return amount;
    }

    public Integer getPayments() {
        return payments;
    }

    public String getAccountNumberDest() {
        return accountNumberDest;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setPayments(Integer payments) {
        this.payments = payments;
    }

    public void setAccountNumberDest(String accountNumberDest) {
        this.accountNumberDest = accountNumberDest;
    }
}
