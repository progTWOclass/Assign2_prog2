// -------------------------------------------------------
// Assignment 2
// Written by: Steve Banh 1971537
// For “Programming 2” Section 2 – Winter 2025
// --------------------------------------------------------
public class InsufficientFundsException extends Exception {

    public InsufficientFundsException() {
        super("Cannot withdraw. Your balance is $0.");
    }

    public InsufficientFundsException(String message){
        super(message);
    }
}
