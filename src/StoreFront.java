import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * StoreFront
 *
 * The storefront contains the main method of the program. Users are able to make accounts and log in.
 * When making an account a number of prompts are given to successfully make an account. When logged in the user
 * can go to settings, go to messages, view or make a stores depending on role, and log out. The admin is the first
 * account made. The admin can shut down the website
 *
 * @author Bryce LaMarca, Lab 25
 *
 * @version 4/10/2023
 *
 */

public class StoreFront {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in); //scanner object for the input
        ArrayList<User> users; //the users that are stored
        users = recoverUsers();
        int adminReturn;
        if (users == null || users.size() == 0) {
            users = new ArrayList<>();
            users.add(createAdmin(scan));
            storeData(users);
        }

        String userInput; //the users input
        User signedInUser; //the user that is signe in
        boolean siteUp = true; //if the site us currently up
        while (siteUp) {
            do {
                System.out.println("1) Create an account\n2) Sign in");
                userInput = scan.nextLine();
                if (!userInput.equals("1") && !userInput.equals("2")) {
                    System.out.println("Please enter a valid input of 1 or 2");
                }
            } while (!userInput.equals("1") && !userInput.equals("2") && !userInput.equals("3"));

            switch (userInput) {
                case "1" -> {
                    User newUser = createAccount(users, scan);
                    if (newUser != null) {
                        users.add(newUser);
                        storeData(users);
                    } else {
                        System.out.println("No user created");
                    }
                }
                case "2" -> {
                    signedInUser = login(users, scan);

                    if (signedInUser != null && !signedInUser.getUserName().equals("admin")) {
                        userInterface(scan, signedInUser, users);
                    } else if (signedInUser != null && signedInUser.getUserName().equals("admin")){
                        adminReturn = adminInterface(signedInUser, scan);
                        if (adminReturn == 1) {
                            siteUp = false;
                        }
                    }
                }
            }
        }
        storeData(users);
    }


    /**
     * creates a new account for the users
     *
     * @param users a list of the current users
     * @param scan  a scanner object to take inputs
     * @return the account that is created
     */
    public static User createAccount(ArrayList<User> users, Scanner scan) {

        String newUserName = null; //the new username that is getting checked
        boolean validInput = false; //if there is a valid input
        String password = ""; //the users password
        String userEmail = ""; //the users email

        while (!validInput) {
            System.out.println("Enter a username or exit to leave:");
            newUserName = scan.nextLine();
            if (newUserName.equals("exit")) {
                System.out.println("Exiting...");
                return null;
            } else if (newUserName.length() < 5) {
                System.out.println("Username must be at least 5 characters. Try another or type exit to leave.");
            } else if (newUserName.contains(" ")) {
                System.out.println("No spaces are allowed in username. Try another or type exit to leave.");
            } else {
                validInput = true;

                for (int i = 0; i < users.size(); i++) {

                    if (users.get(i).getUserName().equals(newUserName)) {

                        System.out.println("Username is taken. Try another or type exit to leave.");
                        validInput = false;
                        break;
                    }
                }
            }
        }
        validInput = false;
        while (!validInput) {
            System.out.println("Please enter a password or exit to leave:");
            password = scan.nextLine();
            if (password.equals("exit")) {
                System.out.println("Exiting...");
                return null;
            } else if (password.length() < 5) {
                System.out.println("The password must be 5 character long. Try another or type exit to leave.");
            } else if (password.contains(" ")) {
                System.out.println("Do not use any spaces");
            } else {
                validInput = true;
            }
        }
        validInput = false;
        System.out.println("Enter your email");
        while (!validInput) {

            int userResponse; //if the user wants to confirm they email they selected
            userEmail = scan.nextLine();
            if (userEmail.contains(" ")) {
                System.out.println("Email cannot contain spaces");
            } else {
                try {
                    System.out.println("Verify Email: (Enter 1, 2, or 3)");
                    System.out.println("1) Confirm Email");
                    System.out.println("2) Change Email");
                    System.out.println("3) Exit");
                    userResponse = scan.nextInt();
                    scan.nextLine();
                    switch (userResponse) {
                        case 1 -> {
                            System.out.println("Email verified!");
                            validInput = true;
                        }
                        case 2 -> System.out.println("Enter a different email.");
                        case 3 -> {
                            System.out.println("Exiting...");
                            return null;
                        }
                        default -> throw new InputMismatchException();
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please enter 1, 2, or 3.");
                }

            }
        }
        validInput = false;
        while (!validInput) {
            try {
                System.out.println("Are you a seller or buyer?");
                System.out.println("1) Buyer");
                System.out.println("2) Seller");
                System.out.println("3) Exit");
                int userInput = scan.nextInt(); //if the user wants to be a buyer or seller. They can also exit
                scan.nextLine();
                switch (userInput) {
                    case 1 -> {
                        return new User(newUserName, password, userEmail, true, false);
                    }
                    case 2 -> {
                        return new User(newUserName, password, userEmail, false, true);
                    }
                    case 3 -> {
                        System.out.println("Exiting...");
                        return null;
                    }
                    default -> throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter 1, 2, or 3.");
            }
        }
        return null;
    }

    /**
     * logs in the user
     *
     * @param users a list of current users
     * @param scan  a Scanner object to take inputs
     * @return the user that is logged in
     */
    //this is how the user logs in
    public static User login(ArrayList<User> users, Scanner scan) {
        String checkUsername; //the username being compared to make sure there are no repeats
        int indexOfUser = 0; //what the index of the user is
        boolean validUsername = false; //if the username is valid
        boolean validPassword = false; //if there is a valid input
        System.out.println("Enter your username or exit to leave.");
        while (!validUsername) {
            checkUsername = scan.nextLine();
            if (checkUsername.equals("exit")) {
                System.out.println("Exiting...");
                return null;
            }
            try {
                if (users.size() == 0) {
                    throw new InvalidLogin("Invalid login. Try entering your username again or type exit to leave.");
                }
                for (int i = 0; i < users.size(); i++) {
                    if (users.get(i).getUserName().equals(checkUsername)) {
                        validUsername = true;
                        indexOfUser = i;
                        break;
                    }
                    if (i == users.size() - 1) {
                        throw new InvalidLogin("Invalid login. Try entering your username again or type exit to leave.");
                    }
                }
            } catch (InvalidLogin e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Please enter your password or exit to leave.");
        while (!validPassword) {
            try {
                String checkPassword = scan.nextLine();
                if (checkPassword.equals(users.get(indexOfUser).getPassword())) {
                    return users.get(indexOfUser);
                } else if (checkPassword.equals("exit")) {
                    System.out.println("Exiting...");
                    return null;
                } else {
                    throw new InvalidLogin("Invalid password. Try entering your password again or type exit to leave.");
                }
            } catch (InvalidLogin e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    /**
     * the interface for when the user logs in
     *
     * @param scan  the Scanner object to take inputs
     * @param user  the user that is logged in
     * @param users a list of all other users
     */
    public static void userInterface(Scanner scan, User user, ArrayList<User> users) {
        boolean signOut = false; //if the user has signed out
        int userInput; //the users input
        boolean accountStillUp;
        while (!signOut) {
            System.out.println("Enter the number to access your desire feature: ");
            System.out.println("1) Messages");
            System.out.println("2) Settings");
            System.out.println("3) Marketplace");
            System.out.println("4) Logout");
            try {
                userInput = scan.nextInt();
                scan.nextLine();
                switch (userInput) {
                    case 1:
                        UserMessages userMessages = new UserMessages(user, users, scan);
                        userMessages.runMessages();
                        break;
                    case 2:
                        UserSettings userSettings = new UserSettings(user, users);
                        accountStillUp = userSettings.runUserSettings(scan);
                        if (!accountStillUp) {
                            signOut = true;
                        }
                        break;
                    case 3:
                        MarketPlace marketPlace = new MarketPlace(user.getUserName(), user.getUserEmail(), scan);
                        if (user.isSeller()) {
                            marketPlace.runSeller();

                        } else {
                            marketPlace.runCustomer();
                        }
                        break;
                    case 4:
                        System.out.println("Logging out...");
                        signOut = true;
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                System.out.println("The input is not acceptable, please try again.");
            }
        }
    }

    /**
     * recovers the users from previous times the code was run
     *
     * @return the list of users that is stored
     */
    public static ArrayList<User> recoverUsers() {
        String line; //the line that is read
        String userName; //users username to be stored
        String password; //users passwords to be stored
        String email; //users email to be stored
        boolean isBuyer; //checks if user is a buyer
        boolean isSeller; //checks if the user is a seller
        ArrayList<User> users = new ArrayList<>();
        try {
            FileReader fr = new FileReader("userInfo.txt");
            BufferedReader bfr = new BufferedReader(fr);
            line = bfr.readLine();
            while (line != null) {
                userName = line.substring(0, line.indexOf(" "));
                line = line.substring(line.indexOf(" ") + 1);
                password = line.substring(0, line.indexOf(" "));
                line = (line.substring(line.indexOf(" ") + 1));
                email = line.substring(0, line.indexOf(" "));
                line = (line.substring(line.indexOf(" ") + 1));
                isBuyer = Boolean.parseBoolean(line.substring(0, line.indexOf(" ")));
                line = line.substring(line.indexOf(" ") + 1);

                isSeller = Boolean.parseBoolean(line);
                users.add(new User(userName, password, email, isBuyer, isSeller));
                line = bfr.readLine();
            }
            return users;
        } catch (FileNotFoundException e) {
            System.out.println("No users found.");

        } catch (Exception e) {
            System.out.println("Error reading the user info file.");

        }
        return null;
    }

    /**
     * stores the users so that they can be used again next time the site is up
     *
     * @param users the users that are previously used
     */

    public static void storeData(ArrayList<User> users) {
        try {
            FileWriter fw = new FileWriter("userInfo.txt"); //file writer to write the  file
            BufferedWriter bfw = new BufferedWriter(fw); //buffer reader to read the file
            for (User user : users) {
                bfw.write(user.getUserName() + " " + user.getPassword() + " " +
                        user.getUserEmail() + " " + user.isBuyer() + " " +
                        user.isSeller() + "\n");
            }
            bfw.close();
        } catch (IOException e) {
            System.out.println("File cannot be written to.");
        }
    }

    /**
     * creates the admin account for the site
     *
     * @param scan Scanner input for user input
     * @return the account for the admin
     */
    public static User createAdmin(Scanner scan) {
        String password; //the password for the admin
        String email; //the email for the admin
        System.out.println("Creating Admin...");
        System.out.println("The username for this user will be admin.");
        System.out.println("Please make a password.");
        do {
            password = scan.nextLine();
            if (password.length() < 5) {
                System.out.println("Password needs to be 5 characters. Try again.");
            }
        } while (password.length() < 5);
        System.out.println("Enter an email");
        email = scan.nextLine();
        System.out.println("Admin is not a buyer or a seller");
        return new User("admin", password, email);
    }

    public static int adminInterface(User admin, Scanner scan) {
        boolean signOut = false; //if the user has signed out
        int userInput; //the users input
        while (!signOut) {
            System.out.println("Enter the number to access your desire feature: ");
            System.out.println("1) Settings");
            System.out.println("2) Shutdown website");
            System.out.println("3) Logout");
            try {
                userInput = scan.nextInt();
                scan.nextLine();
                switch (userInput) {

                    case 1:
                        UserSettings userSettings = new UserSettings(admin);
                        userSettings.runUserSettings(scan);
                        break;
                    case 2:
                        System.out.println("Shutting down website...");
                        return 1;
                    case 3:
                        System.out.println("Signing out...");
                        signOut = true;
                    default:
                        throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                System.out.println("The input is not acceptable, please try again.");
            }
        }
        return 0;
    }
}