// -------------------------------------------------------
// Assignment 2
// Written by: Steve Banh 1971537
// For “Programming 2” Section 02 – Winter 2025
// --------------------------------------------------------
import java.util.ArrayList;

/**
 * This class holds a list of customers with their accounts
 */
public class Bank {
    private ArrayList<Customer> customersList;

    public Bank(){
        this.customersList = new ArrayList<>();
    }

    //METHODS
    //adding a customer into our list of customer
    public void addCustomer(Customer customer){

        customersList.add(customer);
        System.out.println("Customer added successfully");
    }

    //looking for a specific customer in the arraylist
    public Customer findCustomerByID(String customerID){
        for(Customer customers : customersList){
            if(customers.getCustomerID().equals(customerID)){
                return customers;
            }
        }
        return null;
    }
}
