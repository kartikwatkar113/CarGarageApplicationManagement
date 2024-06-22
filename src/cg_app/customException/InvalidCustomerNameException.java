package cg_app.customException;

public class InvalidCustomerNameException extends Exception {
    public InvalidCustomerNameException(String message) {
        super(message);
    }
}
