package Day1;

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
        this.balance = 10000;
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

    // Destructor (handled in Java with finalize)
    @Override
    protected void finalize() throws Throwable {
        System.out.println("Account object is being destroyed.");
        super.finalize();
    }
}
