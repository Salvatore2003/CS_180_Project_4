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
        if (testUser instanceof User &&
                userName.equals(testUser.getUserName()) && password.equals(testUser.getPassword())) {
            return true;
        }
        throw new InvalidLogin("Username or Password is Incorrect");
    }
}
