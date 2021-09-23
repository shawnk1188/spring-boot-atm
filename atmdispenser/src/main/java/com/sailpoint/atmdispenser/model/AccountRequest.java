package com.sailpoint.atmdispenser.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class AccountRequest {
   
    private BigDecimal amount;
    private String accountType;

}
