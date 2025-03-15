// -------------------------------------------------------
// Assignment 2
// Written by: Steve Banh 1971537
// For “Programming 2” Section 02 – Winter 2025
// --------------------------------------------------------
/**
 * This class serves only as a blueprint for other classes
**/
public abstract class BankAccount {

    //CLASS ATTRIBUTES
    protected String accountNumber;
    protected double balance;

    //CONSTRUCTOR
    public BankAccount(String accountNumber, double balance){
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    //GETTERS AND SETTERS
    public String getAccountNumber(){
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    //METHODS
    public void deposit(double amount){
        balance += amount;
    }

    //withdraw with exception handling
    public void withdraw(double amount) throws InsufficientFundsException{
        if(amount > 0 && amount <= balance) {
            balance -= amount;
        }else if (amount > balance){
            throw new InsufficientFundsException("The amount entered exceeded your current balance. Please enter a new amount");
        }else{
            System.out.println("Invalid amount. Please enter a positive number.");
        }
    }

    public abstract void displayAccountInfo();
}
