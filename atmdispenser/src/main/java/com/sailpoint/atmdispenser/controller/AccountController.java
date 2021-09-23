package com.sailpoint.atmdispenser.controller;

import com.sailpoint.atmdispenser.model.AccountRequest;
import com.sailpoint.atmdispenser.model.AccountResponse;
import com.sailpoint.atmdispenser.service.DashBoardService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/atm/account")
public class AccountController {
    private final DashBoardService dashBoardService;
    

    @GetMapping("/balance")
    public ResponseEntity<String> getBalance(@RequestParam("accountType") String accountType){
        return ResponseEntity.ok().body(dashBoardService.currentBalance(accountType));
    } 

  
    @PutMapping("/deposit")
    public ResponseEntity<AccountResponse> depositAmount(@RequestBody AccountRequest accountRequest){
        return ResponseEntity.ok().body(dashBoardService.depositAmountToAccount(accountRequest));
    }

    @PutMapping("/withDraw")
    public ResponseEntity<AccountResponse> withdrawAmount(@RequestBody AccountRequest accountRequest){
        return ResponseEntity.ok().body(dashBoardService.withDrawAmountToAccount(accountRequest));
    }
  
}
