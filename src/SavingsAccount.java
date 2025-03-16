// -------------------------------------------------------
// Assignment 2
// Written by: Steve Banh 1971537
// For “Programming 2” Section 02 – Winter 2025
// --------------------------------------------------------
/**
 * This class is a child class of BankAccount. It has an extra attribute
 * (interestRate) and method (applyInterest)
 * **/
public class SavingsAccount extends BankAccount{

    //CLASS VARIABLES
    protected double interestRate;

    //CONSTRUCTOR
    public SavingsAccount(String accountNumber, double balance, double interestRate){
        super(accountNumber, balance);
        this.interestRate = interestRate;
    }

    //GETTERS AND SETTERS
    public double getInterestRate() {
        return interestRate;
    }
    public void setInterestRate(double interestRate){
        this.interestRate = interestRate;
    }

    //METHODS
    public void applyInterest(double interestRate){
        double interest = balance * interestRate / 100;
        balance += interest;
    }
    @Override
    public void displayAccountInfo() {
        System.out.println("SAVING ACCOUNT");
        System.out.println("Account number: " + getAccountNumber());
        System.out.printf("Balance: $%.2f\n", getBalance());
        System.out.printf("Interest rate: %.2f%%\n", getInterestRate());
        System.out.println();//skip to the next line
    }
}
