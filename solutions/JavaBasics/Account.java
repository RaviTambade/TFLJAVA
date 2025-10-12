import java.io.*;
import java.lang.*;

public class Account {
    public double balance;

    public Account(double amount){   //constructor
        this.balance=amount;
    }
  
    public void deposit(double amount){  // member function
        this.balance=this.balance+amount;
    }

    public void withdraw(double amount){
       double calcuatedBalance=this.balance-amount;
       if(calcuatedBalance <=1000){
        System.out.println("Due to insufficent funds, amount could not be withdrawn");
       }
       else{
        this.balance=calcuatedBalance;
       }
    }

    public static void main(String [] args){
        Account acct123=new Account(56000);
        acct123.deposit(6000);
        System.out.println("Balance= "+ acct123.balance);
        acct123.deposit(8700);
        System.out.println("Balance= "+ acct123.balance);

        acct123.withdraw(65000);
         System.out.println("Balance= "+ acct123.balance);
    }
}
