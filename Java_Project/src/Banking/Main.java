package Banking;
import java.util.*;
import Banking.*;
 
public class Main {
    public static void main(String[] args) {
        Account acc123 = new Account(60000);
        acc123.deposit(15000);
        float currentBalance123 = acc123.getBalance();
        System.out.println(acc123);

    }
}
    