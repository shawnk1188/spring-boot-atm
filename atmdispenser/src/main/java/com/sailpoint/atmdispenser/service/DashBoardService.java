package com.sailpoint.atmdispenser.service;

import java.util.List;
import java.util.Scanner;

import com.sailpoint.atmdispenser.config.ApplicationConfig;
import com.sailpoint.atmdispenser.entity.UserAccountRepository;
import com.sailpoint.atmdispenser.model.AccountRequest;
import com.sailpoint.atmdispenser.model.AccountResponse;
import com.sailpoint.atmdispenser.model.UserOption;
import com.sailpoint.atmdispenser.utils.ApplicationConstants;
import com.sailpoint.atmdispenser.utils.ValidationUtils;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashBoardService implements ApplicationRunner{

    private final ApplicationConfig applicationConfig;
    private final ValidationUtils validator;
    private final UserAccountRepository userAccountRepository;

    public void viewAtmDashBoard(){
        boolean validPin = false;
        System.out.println("ATM DashBoard");
        System.out.println("**************************");
        Scanner input = new Scanner(System.in);
        System.out.println("Please Enter Your PIN:");
        for(int i=0;i<applicationConfig.getPinValidationRetry();i++){
            if(!validPin){
                validPin = this.enterInputFromConsoleAndValidatePin(input.nextLine());
             }else{
                break;
            }                
        }
        if(!validPin){
            System.out.println("There Have Been Multiple Failed Attempts Please Retry Re-Starting the Application");
        }else{
            selectAccountForWithDrawal(input);
            input.close();
        }
    }

    private boolean enterInputFromConsoleAndValidatePin(String pin){
        boolean response =false;
        response=validator.validatePin(pin);
        return response;
    }

    private void selectAccountForWithDrawal(Scanner input){
        System.out.println("\n");
        System.out.println("***********************");
        System.out.println("Select An Account:");
        System.out.println("1.Savings");
        System.out.println("2.Checking");
        System.out.println("***********************");
        System.out.println("\n");
        while(input.hasNext()){
        int option = input.nextInt();
        switch(option){
            case 1: 
                    userAccountRepository.userActionsOptions(input);
                    break;
            case 2: 
                    userAccountRepository.userActionsOptions(input);
                    break;
            default:
                    System.out.println("Please Select A Valid Option");
                    input.nextLine();
                    selectAccountForWithDrawal(input);
        }
    }
    }

    public List<UserOption> accountInfo(){
        return userAccountRepository.accountInfo();
    }

    public List<UserOption> userOptions(){
        return userAccountRepository.userOptions();
    }

    public AccountResponse depositAmountToAccount(AccountRequest request){
        return userAccountRepository.depositAmount(request);
    }

    public AccountResponse withDrawAmountToAccount(AccountRequest request){
        return userAccountRepository.withDrawAmount(request);
    }
    

    public String currentBalance(String option){
      StringBuilder currentBalance = new StringBuilder();
       currentBalance.append("Your Current");
       currentBalance.append(" ");
       currentBalance.append(option);
       currentBalance.append(" Balance is : ");
       currentBalance.append(userAccountRepository.viewCurrentBalance(option));
    return currentBalance.toString();
    }
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(!applicationConfig.getInterfaceValue().equalsIgnoreCase(ApplicationConstants.REST)){
            viewAtmDashBoard();
      }
   }
}
