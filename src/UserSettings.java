import java.util.ArrayList;
import java.util.Scanner;

public class UserSettings {
    User user;
    ArrayList<User> users;

    public UserSettings(User user, ArrayList<User> users) {
        this.user = user;
        this.users = users;
    }

    public UserSettings(User user) {
        this.user = user;
    }

    /**
     * allows the user to choose what to change
     *
     * @param scan Scanner object to take an input
     */
    public void runUserSettings(Scanner scan) {
        String userInput = ""; //the users input
        boolean deletedAccount = false;
        if (!user.equals("admin")) {
            do {
                System.out.println("Enter a number to change your account info:");
                System.out.println("1) Username");
                System.out.println("2) Password");
                System.out.println("3) Email");
                System.out.println("4) Edit Buyer or Seller");
                System.out.println("5) Delete Account");
                System.out.println("5) Exit");
                userInput = scan.nextLine();
                switch (userInput) {
                    case "1":
                        setUsername(scan);
                        break;
                    case "2":
                        setPassword(scan);
                        break;
                    case "3":
                        setEmail(scan);
                        break;
                    case "4":
                        changeBuyerOrSeller(scan);
                        break;
                    case "5":
                        deletedAccount = deleteAccount(scan);
                    case "6":
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Please enter 1, 2, 3, 4, or 5");
                }
            } while (!userInput.equals("5") || !userInput.equals("6"));
        } else {
            do {
                System.out.println("Enter a number to change your account info:");
                System.out.println("1) Password");
                System.out.println("2) Email");
                System.out.println("3) Exit");
                userInput = scan.nextLine();
                switch (userInput) {
                    case "1":
                        setPassword(scan);
                        break;
                    case "2":
                        setEmail(scan);
                        break;
                    case "3":
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Please enter 1, 2, or 3");
                }
            } while (!userInput.equals("5"));
        }
    }

    /**
     * lets the user set a new username
     *
     * @param scan Scanner object to take an input
     */

    public void setUsername(Scanner scan) {
        boolean validInput = false; //checks to see if the input is valid

        String newUsername; //the users new username
        do {
            System.out.println("Enter your new desired username or exit to leave.");
            newUsername = scan.nextLine();
            if (newUsername.equalsIgnoreCase("exit")) {
                System.out.println("Exiting...");
                break;
            } else if (newUsername.equals(user.getUserName())) {
                System.out.println("New username is your current username. Change your username or type exit to" +
                        "keep the same username.");
            } else if (newUsername.length() < 5) {
                System.out.println("Username must be 5 characters long.");
            } else if (newUsername.contains(" ")) {
                System.out.println("Usernames cannot contain spaces.");
            } else {
                validInput = true;
                for (int i = 0; i < users.size(); i++) {
                    if (users.get(i).getUserName().equals(newUsername)) {
                        System.out.println("That username already exist");
                        validInput = false;
                        break;
                    }
                }
            }
            if (validInput) {
                user.setUserName(newUsername);
                StoreFront.storeData(users);
                System.out.println("Username has been successfully changed");
            }
        } while (!validInput);
    }

    /**
     * lets user set a new password
     *
     * @param scan Scanner object to take an input
     */
    public void setPassword(Scanner scan) {
        String newPassword; //the users new password
        boolean validInput = false; //checks to make sure there is a valid input
        do {
            System.out.println("Enter your new password or exit to leave");
            newPassword = scan.nextLine();
            if (newPassword.contains(" ")) {
                System.out.println("Password cannot contain spaces");
            } else if (newPassword.length() >= 5) {
                user.setPassword(newPassword);
                StoreFront.storeData(users);
                System.out.println("Password successfully changed");
                validInput = true;
            } else if (newPassword.equals("exit")) {
                validInput = true;
                System.out.println("Exiting...");
            } else {
                System.out.println("Password must be 5 characters");
            }
        } while (!validInput);
    }

    /**
     * makes a new email for the user
     *
     * @param scan Scanner object to take an input
     */
    public void setEmail(Scanner scan) {
        String newEmail; //the users new username
        boolean validInput = false; //checks to make sure there is a valid input
        do {
            System.out.println("Enter your new email");
            newEmail = scan.nextLine();
            if (newEmail.contains(" ")) {
                System.out.println("Email cannot contain spaces");
            } else {
                user.setUserEmail(newEmail);
                StoreFront.storeData(users);
                System.out.println("Email successfully changed");
                validInput = true;
            }
        } while (!validInput);
    }

    /**
     * change if the user is a buyer or a seller
     *
     * @param scan Scanner object to take an input
     */
    public void changeBuyerOrSeller(Scanner scan) {
        String changeRole; //checks if the user enters Y or N to change role
        boolean validInput = false; //checks to make sure there is a valid input
        do {
            if (user.isBuyer()) {
                System.out.println("Would you like to change to being a seller? Enter Y or N.");
                changeRole = scan.nextLine();
                if (changeRole.equals("Y")) {
                    user.setBuyer(false);
                    user.setSeller(true);
                    StoreFront.storeData(users);
                    validInput = true;
                    System.out.println("Role successfully changed");

                } else {
                    System.out.println("Please enter Y or N.");
                }
            } else {
                System.out.println("Would you like to change to being a buyer? Enter Y or N.");
                changeRole = scan.nextLine();
                if (changeRole.equals("Y")) {
                    user.setBuyer(true);
                    user.setSeller(false);
                    StoreFront.storeData(users);
                    validInput = true;
                    System.out.println("Role successfully changed");
                } else {
                    System.out.println("Please enter Y or N.");
                }
            }
        } while (!validInput);
    }

    /** deletes a user account
     * @param scan a scanner object to take the input of an objects
     */
    public boolean deleteAccount(Scanner scan) {
        String userInput; //the users input
        System.out.println("Confirm you want to delete your account");
        System.out.println("1) Confirm");
        System.out.println("2) Cancel");
        userInput = scan.nextLine();
        if (userInput.equals("1")) {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUserName().equals(user.getUserName())) {
                    System.out.println("Deleting account...");
                    users.remove(i);
                    return true;
                }
            }
        }
        System.out.println("Cancelling...");
        return false;
    }
}
