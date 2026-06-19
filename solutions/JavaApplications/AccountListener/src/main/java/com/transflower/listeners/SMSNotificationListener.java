package com.transflower.listeners;

public class SMSNotificationListener implements NotificationListener {
    

     @Override
    public void onUnderBalance(double balance) {
        System.out.println("SMS: Low balance in Account "
                + balance);
    }

    @Override
    public void onOverBalance(double balance) {
       System.out.println("SMS: High balance in Account "
                + balance);
    }
}
