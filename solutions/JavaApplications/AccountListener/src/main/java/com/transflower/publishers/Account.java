package com.transflower.publishers;
import java.util.*;

import com.transflower.listeners.*;

//Publisher
public class Account {
    
    private double balance;

    private List<AccountListener> listeners=new  ArrayList<>();

    public double getBalance() {
        return balance;
    }

    public Account(double amount){
        balance=amount;
    }

    public void deposit(double amount){
        balance+=amount;
        checkBalance();
    }

    public void withdraw(double amount){
       balance-=amount; 
       checkBalance();
    }

    private void checkBalance(){

        if (balance < 1000) {
            for (AccountListener l : listeners) {
                //trigger , raise, fire These are supposed generate event
                l.onUnderBalance(balance);
            }
        }
        if (balance > 250000) {
            for (AccountListener l : listeners) {
                l.onOverBalance(balance);
            }
        }
    }

    // Register listener
    public void addListener(AccountListener listener) {
        listeners.add(listener);
    }   
}
