package com.sailpoint.atmdispenser.service;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private BigDecimal defaultCurrentBalance = new BigDecimal(10000000000000.00);

    public void userActionsOptions(Scanner userOption){
        displayUserOptions();
        try{
        while(userOption.hasNext()){
        int n = userOption.nextInt();
        switch (n){
            case 1: 
                      System.out.println("Your Current Balance is:" + defaultCurrentBalance);
                      anotherTransaction(userOption);
                      break;
            case 2: 
                      System.out.println("Depositing Amount");
                      System.out.println("Please Enter The Amount You Would Like To Deposit");
                      BigDecimal depositAmount =userOption.nextBigDecimal();
                      defaultCurrentBalance = defaultCurrentBalance.add(depositAmount);
                      displayCurrentBalance(defaultCurrentBalance);
                      anotherTransaction(userOption);
                      break;
            case 3: 
                     System.out.println("Withdrawing Amount");
                     System.out.println("Please Enter The Amount You Would Like To Withdraw");
                     BigDecimal withDrawAmount =userOption.nextBigDecimal();
                     if(defaultCurrentBalance.compareTo(withDrawAmount)==1 || defaultCurrentBalance.compareTo(withDrawAmount)==0){
                        defaultCurrentBalance = defaultCurrentBalance.subtract(withDrawAmount);
                        displayCurrentBalance(defaultCurrentBalance);
                      }else{
                         System.out.println("Insufficient Balance");
                     }
                     anotherTransaction(userOption);
                    break;
            case 4: System.exit(0);
            default:
                   System.out.println("Please Use a Valid Option Returning to Main Menu"); 
                   userActionsOptions(userOption);
                   break;
        }
        }
    }catch(Exception e){
        if(e instanceof InputMismatchException){
            System.out.println("Please Enter a Valid Option");
            userOption.nextLine();
            userActionsOptions(userOption);
        }
    }
    }


    public void anotherTransaction(Scanner userOption){
        displayAnotherTransactionOptions();
        try{
        while(userOption.hasNext()){
            int choice = userOption.nextInt();
            switch (choice){
                case 1: userActionsOptions(userOption);
                        break;
                case 2: 
                        System.out.println("Thank You For Choosing Us");
                        System.exit(0);
                        break;
                default:
                        System.out.println("Please Choose A Valid Option");
                        anotherTransaction(userOption);
            }
        }
    }catch(Exception e){
        if(e instanceof InputMismatchException){
            System.out.println("Please Enter a Valid Option");
            userOption.nextLine();
            anotherTransaction(userOption);
        }
    }
    }

    public void displayCurrentBalance(BigDecimal currentBalance){
        System.out.println("Your Current Balance is: " + currentBalance);
    }

    public void displayAnotherTransactionOptions(){
        System.out.println("\n");
        System.out.println("*******************");
        System.out.println("If Would You Like to Make An Another Transaction Choose An Option Below:");
        System.out.println("1. YES");
        System.out.println("2. NO");
        System.out.println("*******************");
    }


    public void displayUserOptions(){
        System.out.println("\n");
        System.out.println("Please Select An Option From Below:");
        System.out.println("\n");
        System.out.println("********************************");
        System.out.println("1. View Balance");
        System.out.println("2. Deposit Amount");
        System.out.println("3. WithDraw Amount");
        System.out.println("4. Exit");
        System.out.println("********************************");
        System.out.println("\n");
    }

}
