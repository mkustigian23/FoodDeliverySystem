/**
 * Documentation
 *
 * class represents a custom exception that is thrown when a user login attempt fails because of
 * invalid credentials
 */

public class InvalidLoginException extends Exception {
    /**
     * Class and method created to throw an exception when a login is not validated
     *
     * @param message Message explaining why login failed
     */
    public InvalidLoginException(String message) {
        super(message);
    }
}

