// -------------------------------------------------------
// Assignment 2
// Written by: Steve Banh 1971537
// For “Programming 2” Section 02 – Winter 2025
// --------------------------------------------------------
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This is the main class that provides a menu interface for users to interact with the
 * banking system. The program has a main method that allows the user to perform one of
 * the available operations for a customer. It can be registering a customer into our bank,
 * add a bank account for the customer, display their information, deposit their money
 * into a checking or savings account, or withdraw money from their checking account.
 *
 * Firstly, the program has a method called registerCustomer that allows the user to record a
 * customer's information into our banking system. It records information such as a unique
 * customer ID and the customer's name.
 *
 * Secondly, the program has a method called addAccountForCustomer that allows the user to
 * link a banking account either checking or savings to a specific customer. The user will
 * have to specify the customer initial balance for either one of their accounts,
 * his/her overdraft limit for a checking account, and his/her interest rate for a savings account.
 *
 * Thirdly, the program has a displayCustomerInfo method which is to simply print information
 * such as customer ID, name, and all the different banking accounts associated under a
 * specific customer.
 *
 * Fourthly, the program has a depositTransaction method that will allow the user to deposit an
 * amount on either the customer's checking or savings account. It should update the balance of the
 * customer's bank account.
 *
 * Lastly, the program has a withdrawal method that allows the user to help the customer withdraw
 * their money. The method handles the overdraft problem where if a customer tries to take out
 * more than the authorized amount, the program informs the user that the customer cannot
 * withdraw more money because they have exceeded their overdraft limit.
 */
public class Main {

    //CLASS VARIABLES
    private static Scanner input = new Scanner(System.in);
    private static Bank bank = new Bank();


    //METHODS
    //add a customer into the arraylist in bank class
    public static void registerCustomer(){
        System.out.println("Please enter customer ID:");
        String cID = input.nextLine();

        //check if customer id is already in the arraylist
        if(bank.findCustomerByID(cID) != null){
            System.out.println("Customer ID (" + cID + ") is already registered.");
            return;
        }

        System.out.println("Please enter customer full name: ");
        String cName;
        //force the user to only enter alphabets for name
        while(true){
            try{
                cName = input.nextLine();
                if(!cName.matches("[a-zA-Z ]+"))
                    throw new IllegalArgumentException("Name cannot contain special characters or numbers");
                break;
            }catch (IllegalArgumentException iAE){
                System.out.println(iAE.getMessage());
                System.out.println("Please try again");
            }
        }
        bank.addCustomer(new Customer(cID, cName));
    }

    //add an account (checking or savings) to an existing customer
    public static void addAccountForCustomer(){
        System.out.println("Please enter customer ID: ");
        String cID;
        Customer customer;

        //check if the customer entered is registered into the arraylist
        cID = input.nextLine();
        customer = bank.findCustomerByID(cID);
        if (customer == null) {
            System.out.println("Customer ID (" + cID + ") is not registered.");
            return;
        }

        System.out.println("Please enter account type (enter 1 for checking, 2 for savings): ");
        int accountType;
        //force the user to only enter 1 or 2
        while(true){
            try{
                accountType = input.nextInt();
                if(accountType == 1 || accountType == 2)
                    break;
                else{
                    throw new IllegalArgumentException("Invalid. Please enter 1 (for checking) or 2 (for savings)");
                }
            }catch(InputMismatchException e){
                System.out.println("Not a number. Please enter 1 (for checking) or 2 (for savings)");
                input.next();//clear invalid input
            }catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        input.nextLine();//clear the buffer

        if(accountType == 1) {
            System.out.println("Please enter an account number: ");
            String accountNum;
            //force the user to only enter numbers for checking account
            while (true) {
                try {
                    accountNum = input.nextLine();
                    if (accountNum.matches("[a-zA-Z \\W]+"))
                        throw new IllegalArgumentException("Account must only contain numbers");
                    break;
                } catch (IllegalArgumentException iAE) {
                    System.out.println(iAE.getMessage());
                    System.out.println("Please try again");
                }
            }

            System.out.println("Please enter your balance: ");
            double balancechecking = 0;
            boolean chcekingAnswer = false;
            //force the user to only enter a positive numbers and non-alphabetical
            while(!chcekingAnswer){
                try{
                    balancechecking = input.nextDouble();
                    if(balancechecking < 0)
                        throw new IllegalArgumentException("Cannot enter a negative balance");
                    chcekingAnswer = true;
                }catch(InputMismatchException iME){
                    System.out.println("Invalid. Please enter a number.");
                    input.next();//clear invalid input
                }catch(IllegalArgumentException iAE){
                    System.out.println(iAE.getMessage());
                    System.out.println("Please try again");
                }
            }

            System.out.println("Please enter overdraft limit: ");
            double limit = 0;
            boolean overdraftAnswer = false;
            //force the user to only enter positive numbers
            while(!overdraftAnswer){
                try{
                    limit = input.nextDouble();
                    if(limit < 0)
                        throw new IllegalArgumentException("please enter a positive number");
                    overdraftAnswer = true;
                }catch(InputMismatchException iME){
                    System.out.println("Invalid. Please enter a number.");
                    input.next();
                }catch(IllegalArgumentException iAE){
                    System.out.println(iAE.getMessage());
                    System.out.println("Please try again");
                }
            }
            CheckingAccount checking = new CheckingAccount(accountNum, balancechecking, limit);
            customer.addAccount(checking);
        }else{
            System.out.println("Please enter account number: ");
            String accountNum;

            //force the user to only enter positive numbers
            while (true) {
                try {
                    accountNum = input.nextLine();
                    if (accountNum.matches("[a-zA-Z \\W]+"))
                        throw new IllegalArgumentException("Account must only contain numbers");
                    break;
                } catch (IllegalArgumentException iAE) {
                    System.out.println(iAE.getMessage());
                    System.out.println("Please try again");
                }
            }
            System.out.println("Please enter your balance: ");
            double balanceSaving = 0;
            boolean savingAnswer = false;

            //force the user to only enter positive numbers
            while(!savingAnswer){
                try{
                    balanceSaving = input.nextDouble();
                    if(balanceSaving < 0)
                        throw new IllegalArgumentException("Cannot enter a negative balance");
                    savingAnswer = true;
                }catch(InputMismatchException iME){
                    System.out.println("Invalid. Please enter a number.");
                    input.next();//clear invalid input
                }catch(IllegalArgumentException iAE){
                    System.out.println(iAE.getMessage());
                    System.out.println("Please try again");
                }
            }

            System.out.println("Please enter interest rate: ");
            double rate = 0;
            boolean acceptableRate = false;

            //force the user to only enter positive numbers
            while(!acceptableRate){
                try{
                    rate = input.nextDouble();
                    if(rate < 0)
                        throw new IllegalArgumentException("Cannot enter a negative interest rate");
                    acceptableRate = true;
                }catch(InputMismatchException iME){
                    System.out.println("Invalid. Please enter a number.");
                    input.next();//clear invalid input
                }catch(IllegalArgumentException iAE){
                    System.out.println(iAE.getMessage());
                    System.out.println("Please try again");
                }
            }
            SavingsAccount saving = new SavingsAccount(accountNum, balanceSaving, rate);
            customer.addAccount(saving);
            saving.applyInterest(rate);
        }
    }

    //display information of a specific customer by finding their ID
    public static void displayCustomerInfo(){
        System.out.println("Please enter customer ID: ");
        String cID = input.nextLine();
        Customer customer = bank.findCustomerByID(cID);
        if(customer != null){
            customer.displayCustomerInfo();
        }else{
            System.out.println("Customer ID (" + cID + ") is not registered in our system");
        }
    }

    //allow a specific user to deposit an amount into their checking or savings
    public static void depositTransaction(){
        System.out.println("Please enter customer ID: ");
        String cID = input.nextLine();
        Customer customer = bank.findCustomerByID(cID);

        if(customer == null) {
            System.out.println("Customer ID (" + cID + ") is not registered");
            return;
        }

        System.out.println("Please enter the account number you wish to deposit into: ");
        String accountNum;
        while (true) {
            try {
                accountNum = input.nextLine();
                if (accountNum.matches("[a-zA-Z \\W]+"))
                    throw new IllegalArgumentException("Account must be a number");
                break;
            } catch (IllegalArgumentException iAE) {
                System.out.println(iAE.getMessage());
                System.out.println("Please try again");
            }
        }

        BankAccount account = customer.getAccount(accountNum);
        if(account == null){
            System.out.println("Customer ID (" + cID + ") does not have account number (" + accountNum +")");
            return;
        }

        //check the type of account
        String accountType = "Unknown";
        if(account instanceof SavingsAccount){
            accountType = "Savings account";
        } else if (account instanceof CheckingAccount) {
            accountType = "Checking account";
        }

        System.out.println("Please enter an amount to deposit into your account: ");
        double depoAmount = 0;
        while(true) {
            try {
                depoAmount = input.nextDouble();
                if (depoAmount < 0) {
                    throw new IllegalArgumentException("Cannot enter a negative amount");
                }
                account.deposit(depoAmount);
                break;
            } catch(InputMismatchException iME){
                System.out.println("Invalid. Please enter a number");
                input.next();//clear invalid input
            }catch (IllegalArgumentException iAE) {
                System.out.println(iAE.getMessage());
                System.out.println("Please try again");
            }
        }
        System.out.printf("you have deposited $%.2f into your %s, account number: %s\n", depoAmount, accountType, account.getAccountNumber());
        System.out.printf("Your new balance: $%.2f\n", account.getBalance());
    }

    //allow a specific user to withdraw an amount from their checking account
    public static void withdrawalTransaction(){
        System.out.println("Please enter customer ID: ");
        String cID = input.nextLine();
        Customer customer = bank.findCustomerByID(cID);

        if(customer == null) {
            System.out.println("Customer ID (" + cID + ") is not registered");
            return;
        }

        System.out.println("Please enter account number you wish to withdraw from: ");
        String accountNum;
        while (true) {
            try {
                accountNum = input.nextLine();
                if (accountNum.matches("[a-zA-Z \\W]+"))
                    throw new IllegalArgumentException("Account must be a number");
                break;
            } catch (IllegalArgumentException iAE) {
                System.out.println(iAE.getMessage());
                System.out.println("Please try again");
            }
        }

        BankAccount account = customer.getAccount(accountNum);
        if(account == null){
            System.out.println("Customer ID (" + cID + ") does not have account number (" + accountNum +")");
            return;
        }

        //check the type of account
        String accountType = "Unknown";
        if(account instanceof SavingsAccount){
            accountType = "Savings account";
        } else if (account instanceof CheckingAccount) {
            accountType = "Checking account";
        }

        if(accountType.equals("Savings account")){
            System.out.println("Cannot withdraw from savings account");
            return;
        }

        System.out.println("Please enter an amount you wish to withdraw from your " + accountType);
        double withdrawAmount = 0;
        while(true) {
            try {
                withdrawAmount = input.nextDouble();
                if (withdrawAmount < 0) {
                    throw new IllegalArgumentException("Cannot enter a negative amount");
                }
                account.withdraw(withdrawAmount);
                break;
            }catch (InsufficientFundsException iFE){
                System.out.println(iFE.getMessage());
                System.out.printf("Your current balance: $%.2f\n", account.getBalance());
                return;
            }catch(InputMismatchException iME){
                System.out.println("Invalid. Please enter a number");
                input.next();//clear invalid input
            }catch (IllegalArgumentException iAE) {
                System.out.println(iAE.getMessage());
                System.out.println("Please try again");
            }
        }
        System.out.printf("you have withdrawn $%.2f from your checking account, account number: %s\n", withdrawAmount, account.getAccountNumber());
        System.out.printf("Your new balance: $%.2f\n", account.getBalance());
    }

    public static void main(String[] args) {
        boolean continueLoop = true;
        while(continueLoop) {
            System.out.println(""" 
                    \n===== Online Banking System =====
                    1. Register a new customer
                    2. Add an account to a customer
                    3. Display customer information
                    4. Deposit money
                    5. Withdraw money
                    6. Exit
                    """);
            System.out.println("please enter your choice:");
            try {
                int commandChoice = input.nextInt();
                input.nextLine();//clears the buffer
                switch (commandChoice) {
                    case 1:
                        registerCustomer();
                        break;//exit the switch statement
                    case 2:
                        addAccountForCustomer();
                        break;
                    case 3:
                        displayCustomerInfo();
                        break;
                    case 4:
                        depositTransaction();
                        break;
                    case 5:
                        withdrawalTransaction();
                        break;
                    case 6:
                        System.out.println("Thank you for using our banking system \nGoodbye");
                        continueLoop = false;//stops the while loop
                        break;
                    default:
                        System.out.println("Invalid command");
                }
            }catch (InputMismatchException iME){
                System.out.println("Invalid command. PLease try again");
                input.next();
            }
        }
    }
}
