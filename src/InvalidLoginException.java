public class InvalidLoginException extends Exception {
    // Class and method created to throw an exception when a login is not validated

    /**
     *
     * @param message
     */
    public InvalidLoginException(String message) {
        super(message);
    }
}

