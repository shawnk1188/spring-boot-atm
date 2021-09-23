package com.sailpoint.atmdispenser.entity;

import java.util.ArrayList;
import java.util.List;

import com.sailpoint.atmdispenser.model.AccountRequest;
import com.sailpoint.atmdispenser.model.AccountResponse;
import com.sailpoint.atmdispenser.model.UserOption;
import com.sailpoint.atmdispenser.utils.ApplicationConstants;

import org.springframework.stereotype.Component;
import java.math.BigDecimal;


@Component
public class UserAccountRepository extends AccountRepository{

    private BigDecimal defaultSavingsBalance = new BigDecimal(10000000000000.00);
    private BigDecimal defaultCheckingBalance = new BigDecimal(20000000000000.00);

    
    public List<UserOption> accountInfo(){
        List<UserOption> userOptionList = new ArrayList<>();
        UserOption option1 = new UserOption();
        option1.setOption("Savings Account");
        option1.setUrl("/atm/account/balance");

        UserOption option2 = new UserOption();
        option2.setOption("Savings Account");
        option2.setUrl("/atm/account/balance");
        userOptionList.add(option1);
        userOptionList.add(option2);
        return userOptionList;
    }


    public List<UserOption> userOptions(){
        List<UserOption> optionsList = new ArrayList<>();
        UserOption option1 = new UserOption();
        option1.setOption("View Balance");
        option1.setUrl("/atm/account/balance");
        UserOption option2 = new UserOption();
        option2.setOption("Deposit Amount");
        option2.setUrl("/atm/account/deposit");
        UserOption option3 = new UserOption();
        option3.setOption("WithDraw Amount");
        option3.setUrl("/atm/account/withDraw");
        optionsList.add(option1);
        optionsList.add(option2);
        optionsList.add(option3);
        return optionsList;
    }

    public BigDecimal viewCurrentBalance(String option){
        if(option.equalsIgnoreCase(ApplicationConstants.SAVINGS)){
            return defaultSavingsBalance;
        }else if(option.equalsIgnoreCase(ApplicationConstants.CHECKING)){
            return defaultCheckingBalance;
        }
        return new BigDecimal(0);
    }

    public AccountResponse depositAmount(AccountRequest request){
        AccountResponse response = new AccountResponse();
        if(request.getAccountType().equalsIgnoreCase(ApplicationConstants.SAVINGS)){
            response.setAccountType(ApplicationConstants.SAVINGS);
            response.setCurrentBalance(defaultSavingsBalance.add(request.getAmount()));
            response.setStatus("Amount Has Been Successfully Deposited to your Savings Account");
            return response;
        }else if(request.getAccountType().equalsIgnoreCase(ApplicationConstants.CHECKING)){
            response.setAccountType(ApplicationConstants.CHECKING);
            response.setCurrentBalance(defaultCheckingBalance.add(request.getAmount()));
            response.setStatus("Amount Has Been Successfully Deposited to your Checking Account");
            return response;
        }else{
            return response;
        }
    }

    public AccountResponse withDrawAmount(AccountRequest request){
        AccountResponse response = new AccountResponse();
        response.setAccountType(request.getAccountType());
        if(request.getAccountType().equalsIgnoreCase(ApplicationConstants.SAVINGS)){
            if(checkBalance(defaultSavingsBalance, request.getAmount())==1 
            || checkBalance(defaultSavingsBalance, request.getAmount())==0){
                response.setCurrentBalance(defaultSavingsBalance.subtract(request.getAmount()));
                response.setStatus("Amount Has Been Successfully WithDrawn to your Savings Account");
            }else {
                response.setCurrentBalance(defaultSavingsBalance);
                response.setStatus("In Sufficient Amount In Your Account"); 
            }           
            return response;
        }else if(request.getAccountType().equalsIgnoreCase(ApplicationConstants.CHECKING)){
            if(checkBalance(defaultCheckingBalance, request.getAmount())==1 
            || checkBalance(defaultCheckingBalance, request.getAmount())==0){
                response.setCurrentBalance(defaultCheckingBalance.subtract(request.getAmount()));
                response.setStatus("Amount Has Been Successfully WithDrawn to your Checking Account");
            }else {
                response.setCurrentBalance(defaultCheckingBalance);
                response.setStatus("In Sufficient Amount In Your Account"); 
            }     
            return response;
        }else{
            return response;
        }
    }

    public int checkBalance(BigDecimal currentBalance, BigDecimal withDrawAmount){
        return currentBalance.compareTo(withDrawAmount);
    }

}
