package cg_app.customException;

public class InvalidContactNumberException extends Exception {
    public InvalidContactNumberException(String message) {
        super(message);
    }
}
