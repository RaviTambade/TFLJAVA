package com.transflower.publishers;
 

import java.util.*;

import com.transflower.listeners.*;
import com.transflower.publishers.operations.*;
import com.transflower.services.NotificationService;
import com.transflower.services.SMSService;

//Publisher
public class Account implements DepositOperation, WithdrawOperation {
    
    private double balance;

    private List<AccountListener> listeners=new  ArrayList<>();
    private NotificationService service;

    public double getBalance() {
        return balance;
    }

    public Account(double amount,NotificationService notificationService){
        this.service=notificationService;
        balance=amount;
    }


    @Override
    public void deposit(double amount){
        balance+=amount;
        checkBalance();
    }

    @Override
    public void withdraw(double amount){
       balance-=amount; 
       checkBalance();
    }

    private void checkBalance(){

        if (balance < 1000) {
            for (AccountListener l : listeners) {
                //trigger , raise, fire These are supposed generate event
                l.onUnderBalance(balance);
                service.send("Amount is less than  minimum balance policy");
            }
        }
        if (balance > 250000) {
            for (AccountListener l : listeners) {
                l.onOverBalance(balance);
                   service.send("Amount is greater than  Taxable income policy");
     
            }
        }
    }

    // Register listener
    public void addListener(AccountListener listener) {
        listeners.add(listener);
    }   
}
