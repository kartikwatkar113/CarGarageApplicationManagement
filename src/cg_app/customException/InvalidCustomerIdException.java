package cg_app.customException;

public class InvalidCustomerIdException extends Exception {
    public InvalidCustomerIdException(String message) {
        super(message);
    }
}

