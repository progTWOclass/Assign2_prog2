// -------------------------------------------------------
// Assignment 2
// Written by: Steve Banh 1971537
// For “Programming 2” Section 02 – Winter 2025
// --------------------------------------------------------
/**
 * This class is a child class of BankAccount. It has an extra
 * attribute (overDraftLimit), and it overrides the withdrawal method
 * to have its own logic
 **/
public class CheckingAccount extends BankAccount{

    //CLASS VARIABLES
    protected double overdraftLimit;

    //CONSTRUCTOR
    public CheckingAccount(String accountNumber, double balance, double overdraftLimit) {
        super(accountNumber, balance);
        this.overdraftLimit = overdraftLimit;
    }

    //GETTERS AND SETTERS
    public double getOverdraftLimit() {
        return overdraftLimit;
    }
    public void setOverdraftLimit(int overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    //METHODS
    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if(amount > 0 && balance - amount >= -overdraftLimit) {
            balance -= amount;
        }else{
            throw new InsufficientFundsException("The amount entered exceeded your overdraft limit");
        }
    }

    @Override
    public void displayAccountInfo() {
        System.out.println("CHECKING ACCOUNT");
        System.out.println("Account number: " + getAccountNumber());
        System.out.printf("Balance: %.2f\n", getBalance());
        System.out.printf("Overdraft limit: %.2f\n", getOverdraftLimit());
        System.out.println();//skip to the next line
    }
}
