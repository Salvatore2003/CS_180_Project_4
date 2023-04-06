import java.util.ArrayList;
import java.util.Scanner;

public class UserSettings {
    User user;
    ArrayList<User> users;

    public UserSettings(User user, ArrayList<User> users) {
        this.user = user;
        this.users = users;
    }

    /**
     * allows the user to choose what to change
     * @param scan Scanner object to take an input
     */
    public void runUserSettings(Scanner scan) {
        String userInput = "";
        do {
            System.out.println("Enter a number to change your account info:");
            System.out.println("1) Username");
            System.out.println("2) Password");
            System.out.println("3) Email");
            System.out.println("4) Edit Buyer or Seller");
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
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Please enter 1, 2, 3, 4, or 5");
            }
        } while (!userInput.equals("5"));
    }

    /**
     * lets the user set a new username
     * @param scan Scanner object to take an input
     */

    public void setUsername(Scanner scan) {
        boolean validInput = false;

        String newUsername;
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
     * @param scan Scanner object to take an input
     */
    public void setPassword(Scanner scan) {
        String newPassword;
        boolean validInput = false;
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
     * @param scan Scanner object to take an input
     */
    public void setEmail(Scanner scan) {
        String newEmail;
        boolean validInput = false;
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
     * @param scan Scanner object to take an input
     */
    public void changeBuyerOrSeller(Scanner scan) {
        String changeRole;
        boolean validInput = false;
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
}
