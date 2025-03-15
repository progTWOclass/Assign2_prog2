// -------------------------------------------------------
// Assignment 2
// Written by: Steve Banh 1971537
// For “Programming 2” Section 2 – Winter 2025
// --------------------------------------------------------
/**
*This class is a custom exception that is in charge of handling 
*problems with the customer's overdraft limit
**/
public class InsufficientFundsException extends Exception {

    public InsufficientFundsException() {
        super("Cannot withdraw. Your balance is $0.");
    }

    public InsufficientFundsException(String message){
        super(message);
    }
}
