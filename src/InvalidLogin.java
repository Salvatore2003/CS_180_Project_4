public class InvalidLogin extends Exception{
    /**
     * Invalid Login
     *
     * Exception if there is an incorrect username or password
     *
     * @author Bryce LaMarca, Lab 25
     *
     * @version 4/1/2023
     *
     */
    public InvalidLogin(String message) {
        super(message);
    }
}
