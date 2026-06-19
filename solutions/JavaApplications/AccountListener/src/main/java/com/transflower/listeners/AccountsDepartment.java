package com.transflower.listeners;

//Handler
public class AccountsDepartment implements AccountListener {

    @Override
    public void onUnderBalance(double balance) {
        System.out.println(" Department! Current Balance: " + balance);
    }

    @Override
    public void onOverBalance(double balance) {
        System.out.println("Department! Current Balance: " + balance);
    }
}