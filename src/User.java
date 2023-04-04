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


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setBuyer(boolean buyer) {
        this.buyer = buyer;
    }

    public void setSeller(boolean seller) {
        this.seller = seller;
    }

    public String getPassword() {
        return password;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public boolean isBuyer() {
        return buyer;
    }

    public boolean isSeller() {
        return seller;
    }

    public boolean checkPassword (User testUser) throws InvalidLogin {
        if (userName.equals(testUser.getUserName()) && password.equals(testUser.getPassword())) {
            return true;
        }
        throw new InvalidLogin("Username or Password is Incorrect");
    }
}
