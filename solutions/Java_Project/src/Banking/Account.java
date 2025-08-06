import  java.util.*;
public class Account {
    // data member
    private float balance = 5000;

    // Getter and Setter methods
    public void setBalance(float amount) {
        this.balance = amount;
    }

    public float getBalance() {
        return this.balance;
    }

    // Constructor overloading
    public Account(int i) {
        this.balance = i;
    }

    public Account(float amount) {
        this.balance = amount;
    }

    // Withdraw method with exception handling
    public void withdraw(float amount) {
        if (amount == 0) {
            throw new IllegalArgumentException("Amount cannot be zero");
        }
        this.balance -= amount;
    }

    // Deposit method
    public void deposit(float amount) {
        this.balance += amount;
    }
 
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Account other = (Account) obj;
        return  (this.balance== other.balance) ? true: false;
    }
   /*
    @Override
    public int hashCode() {
        return Objects.hash(balance);
    }
 */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Account acct=new Account(balance);
        return acct;

    } 

    @Override 
    public String toString() {
        
        //return super.toString();
        return "Account Balance="+this.balance;
    }
     public static void main(String[] args)  throws CloneNotSupportedException {
       
       try{
        Account acc123 = new Account(60000);
        acc123.deposit(15000);
        float currentBalance123 = acc123.getBalance();
        
        //Account acct321=acct123;
        Account acc321=(Account)acc123.clone();
    
        acc123.deposit(15000);
        System.out.println(acc123);
        System.out.println(acc321);

       }
       catch(Exception ee){
        System.out.println(ee.getMessage());
       }
    }
}