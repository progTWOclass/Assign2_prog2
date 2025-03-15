// -------------------------------------------------------
// Assignment 2
// Written by: Steve Banh 1971537
// For “Programming 2” Section 02 – Winter 2025
// --------------------------------------------------------
import java.util.ArrayList;

/**
 * this class represents customers in our banking system,
 * and it holds a list of banking accounts that is associated with
 * the customer
 */
public class Customer {

    //CLASS VARIABLES
    private String customerID;
    private String name;
    private ArrayList<BankAccount> accountObjects;

    //CONSTRUCTOR
    public Customer(String customerID, String name){
        this.customerID = customerID;
        this.name = name;
        this.accountObjects = new ArrayList<>();
    }

    //GETTERS AND SETTERS
    public String getCustomerID(){
        return customerID;
    }
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<BankAccount> getAccountObjects() {
        return accountObjects;
    }
    public void setAccountObjects(ArrayList<BankAccount> accountObjects) {
        this.accountObjects = new ArrayList<>(accountObjects);
    }

    //METHODS
    public void addAccount(BankAccount account){
        accountObjects.add(account);
        System.out.println("Account added successfully");
    }

    //looking for a specific account in the customer's list of accounts
    public BankAccount getAccount(String accountNumber){
        for(BankAccount accounts : accountObjects){
            if(accounts.getAccountNumber().equals(accountNumber)){
                return accounts;
            }
        }
        return null;
    }

    public void displayCustomerInfo(){
        System.out.println("Customer name: " + getName() + " (ID: " + getCustomerID() + ")");
        System.out.println("Customer bank accounts: ");
        for(BankAccount customerAccount: accountObjects){
            customerAccount.displayAccountInfo();
        }
    }
}
