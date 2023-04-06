public class User {
    private String userName;
    private String password;
    private String userEmail;
    boolean buyer;
    boolean seller;

    public User(String userName, String password, String userEmail, boolean buyer, boolean seller) {
        this.userName = userName;
        this.password = password;
        this.userEmail = userEmail;
        this.buyer = buyer;
        this.seller = seller;
    }

    public User (String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     * gets the username for the user
     * @return the username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * sets the username
     * @param userName the new username
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * sets a new password for the user
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * sets a new email for the user
     * @param userEmail the new email
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * changes buyer to true or false
     * @param buyer sets the new buyer to true or false
     */
    public void setBuyer(boolean buyer) {
        this.buyer = buyer;
    }

    /**
     * sets the seller to true or false
     * @param seller changes seller to true or false
     */
    public void setSeller(boolean seller) {
        this.seller = seller;
    }

    /**
     * gets the password
     * @return the user password
     */
    public String getPassword() {
        return password;
    }

    /**
     * gets the users email
     * @return the users email
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * returns if the user is a buyer
     * @return if buyer is true or false
     */
    public boolean isBuyer() {
        return buyer;
    }

    /**
     * returns if the user is a seller
     * @return if seller is true or false
     */
    public boolean isSeller() {
        return seller;
    }

}
