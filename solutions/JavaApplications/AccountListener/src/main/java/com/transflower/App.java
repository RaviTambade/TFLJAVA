package com.transflower;

import com.transflower.listeners.*;
import com.transflower.publishers.Account;

public class App 
{
    public static void main( String[] args )
    {
         Account account = new Account(5000);

        // attach listener (+=)
        account.addListener(new AccountsDepartment());
        account.addListener(new EmailNotificationListener());
        account.addListener(new SMSNotificationListener());

        // transactions
        account.withdraw(4500);  // triggers under balance
        account.deposit(300000); // triggers over balance
    }
}


//Would you please explain in existing code  SOLID Design principle ?
