import java.util.ArrayList;
import java.util.Scanner;
/**
 * UserSettings
 *
 * This program is where users can change their settings. They can change any of the information attached to
 * their account in this program. They can also delete their account in the program if they no longer
 * want to keep their account.
 *
 * @author Bryce LaMarca, Lab 25
 *
 * @version 4/9/2023
 *
 */
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
    public boolean runUserSettings(Scanner scan) {
        String userInput = ""; //the users input

        boolean accountDeleted = false; //if the account is deleted turned true
        boolean runSettings = true; //if the program should keep running settings
        if (!user.getUserName().equals("admin")) {
            do {
                System.out.println("Enter a number to change your account info:");
                System.out.println("1) Username");
                System.out.println("2) Password");
                System.out.println("3) Email");
                System.out.println("4) Edit Buyer or Seller");
                System.out.println("5) Delete Account");
                System.out.println("6) Exit");
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
                      accountDeleted = deleteAccount(scan);
                      if (accountDeleted) {
                          return false;
                      }
                    case "6":
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Please enter 1, 2, 3, 4, or 5");
                }
                if (userInput.equals("6")) {
                    runSettings = false;
                }
            } while (runSettings);
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
            } while (!userInput.equals("3"));
        }
        return true;
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
                System.out.println("New username is your current username. Change your username or type exit to " +
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
            System.out.println("Enter your new email or exit to leave");
            newEmail = scan.nextLine();
            if (newEmail.contains(" ")) {
                System.out.println("Email cannot contain spaces");
            }  else if (newEmail.equals("exit")) {
                validInput = true;
                System.out.println("Exiting...");
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
        String userInput; //the input that the user enters
        do {
            if (user.isSeller()) {
                System.out.println("Would you like to become a buyer? Enter Y or N");
                userInput = scan.nextLine();
                if (userInput.equals("Y")) {
                    user.setBuyer(true);
                    user.setSeller(false);
                    System.out.println("Role has been change.");
                    StoreFront.storeData(users);
                    break;
                } else if (userInput.equals("N")) {
                    System.out.println("Role is not changed.");
                    break;
                } else {
                    System.out.println("Please enter Y or N");
                }
            }  else if (user.isBuyer()) {
                System.out.println("Would you like to become a seller? Enter Y or N");
                userInput = scan.nextLine();
                if (userInput.equals("Y")) {
                    user.setBuyer(false);
                    user.setSeller(true);
                    System.out.println("Role has been changed");
                    StoreFront.storeData(users);
                    break;
                } else if (userInput.equals("N")){
                    System.out.println("Role is not changed.");
                    break;
                } else {
                    System.out.println("Please enter Y or N");
                }
            }

        } while (true);

    }

    /** deletes a user account
     * @param scan a scanner object to take the input of an objects
     */
    public boolean deleteAccount(Scanner scan) {
        String userInput; //the users input
        Boolean userDeleted = false; //whether the user is deleted or not
        do {
            System.out.println("Confirm you want to delete your account");
            System.out.println("1) Confirm");
            System.out.println("2) Cancel");
            userInput = scan.nextLine();
            if (userInput.equals("1")) {
                for (int i = 0; i < users.size(); i++) {
                    if (users.get(i).getUserName().equals(user.getUserName())) {
                        System.out.println("Deleting account...");
                        users.remove(i);
                        userDeleted = true;
                        if (user.isSeller()) {
                            MarketPlace marketPlace = new MarketPlace(user.getUserName(), user.getUserEmail(), scan);
                            marketPlace.deleteUser(user.getUserName());
                        }

                    }
                }

                StoreFront.storeData(users);
            } else if (userInput.equals("2")) {
                System.out.println("Cancelling");
                userDeleted = false;
            } else {
                System.out.println("Please enter 1 or 2");
            }
            if (userDeleted) {
                System.out.println("User has been deleted");
                return true;
            }

        } while (!userInput.equals("1") && !userInput.equals("2"));
        return false;
    }
}