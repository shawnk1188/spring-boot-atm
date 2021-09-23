package com.sailpoint.atmdispenser.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class AccountResponse {
    
    private BigDecimal currentBalance;
    private String accountType;
    private String status;
}
