public class User {
    private String userName;
    private String password;
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

    public boolean checkPassword (User testUser) throws InvalidLogin {
        if (testUser instanceof User &&
                userName.equals(testUser.getUserName()) && password.equals(testUser.getPassword())) {
            return true;
        }
        throw new InvalidLogin("Username or Password is Incorrect");
    }
}
