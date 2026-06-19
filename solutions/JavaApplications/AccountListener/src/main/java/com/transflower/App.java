package com.transflower;

import com.transflower.listeners.*;
import com.transflower.publishers.Account;
import com.transflower.services.NotificationService;
import com.transflower.services.SMSService;


public class App 
{
    public static void main( String[] args )
    {

        NotificationService smsService=new SMSService();
        Account account = new Account(5000,smsService);

        // attach listener (+=)
        account.addListener(new AccountsDepartment());
       // transactions
        account.withdraw(4500);  // triggers under balance
        account.deposit(300000); // triggers over balance
    }
}

//Would you please explain in existing code  SOLID Design principle ?
//SOLID

//S: Single Responsibility Principle
//O: Open for extension close for Modification
//L: Liskov's substition principle
//I: Interface saggrigation princple
//D: Dependency Inversion