package com.transflower.listeners;

public class EmailNotificationListener implements NotificationListener {

     @Override
    public void onUnderBalance(double balance) {
        System.out.println("Email: Low balance in Account "
                + balance);
    }

    @Override
    public void onOverBalance(double balance) {
       System.out.println("Email: High balance in Account "
                + balance);
    }
    
}
