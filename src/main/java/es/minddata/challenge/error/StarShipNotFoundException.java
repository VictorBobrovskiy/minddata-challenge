package es.minddata.challenge.error;

public class StarShipNotFoundException extends RuntimeException {
    public StarShipNotFoundException(String message) {
        super(message);
    }
}