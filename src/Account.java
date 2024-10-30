public class Account {

    public double balance;
    public String accountNumber;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public boolean withDraw(double amount) {
        if (amount < 0) {
            return false;
        }
        if (amount > this.balance) {
            System.out.println("Insufficient funds");
            return false;
        }
        this.balance -= amount;
        return true;
    }

    public boolean deposit(double amount) {
        if (amount < 0) {
            return false;
        }
        this.balance += amount;
        return true;
    }

    public void getOverview() {
        System.out.println("-----------------------------------");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: " + balance);
        System.out.println("-----------------------------------");
    }
}
