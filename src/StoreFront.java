import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StoreFront {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<User> users = new ArrayList<>();
        String userInput;
        User signedInUser;
        boolean siteUp = true;
        while (siteUp) {
            do {
                System.out.println("1) Create an account\n2) Sign in");
                userInput = scan.nextLine();
                if (!userInput.equals("1") && !userInput.equals("2")) {
                    System.out.println("Please enter a valid input of 1 or 2");
                }
            } while (!userInput.equals("1") && !userInput.equals("2"));

            if (userInput.equals("1")) {
                User newUser = createAccount(users, scan);
                if (newUser != null) {
                    users.add(newUser);
                } else {
                    System.out.println("No user created");
                }
            }

            if (userInput.equals("2")) {
                signedInUser = login(users, scan);
                if (signedInUser != null) {
                    userInterface(scan, signedInUser);
                }
            }
        }
    }

    //this is how the account is created
    public static User createAccount(ArrayList<User> users, Scanner scan) {

        String newUserName = null;
        boolean validInput = false;
        boolean userName5Char = true;
        String password = "";
        String userEmail = "";

        while (!validInput) {
            System.out.println("Enter a username:");
            newUserName = scan.nextLine();
            if (newUserName.equals("exit")) {
                System.out.println("Exiting...");
                return null;
            }
            if (newUserName.length() < 5) {
                userName5Char = false;
            }
            if (userName5Char) {
                validInput = true;
                for (int i = 0; i < users.size(); i++) {
                    if (users.get(i).getUserName().equals(newUserName)) {
                        System.out.println("Username is taken. Try another or type exit to leave");
                        validInput = false;
                        break;
                    }
                }
            }
        }
        validInput = false;
        while (!validInput) {
            System.out.println("Please enter a password:");
            password = scan.nextLine();
            if (password.equals("exit")) {
                System.out.println("Exiting...");
                return null;
            } else if (password.length() < 5) {
                System.out.println("The password must be 5 character long. Try another or type exit to leave.");
            } else {
                validInput = true;
            }
        }
        validInput = false;
        System.out.println("Enter your email");
        while (!validInput) {
            try {
                int userResponse;
                userEmail = scan.nextLine();
                System.out.println("Verify Email: (Enter 1, 2, or 3)");
                System.out.println("1) Confirm Email");
                System.out.println("2) Change Email");
                System.out.println("3) Exit");
                userResponse = scan.nextInt();
                scan.nextLine();
                switch (userResponse) {
                    case 1:
                        System.out.println("Email verified!");
                        validInput = true;
                        break;
                    case 2:
                        System.out.println("Enter a different email.");
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        return null;
                    default:
                        throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter 1, 2, or 3.");
            }
        }
        validInput = false;
        while (!validInput) {
            try {
                System.out.println("Are you a seller or buyer?");
                System.out.println("1) Buyer");
                System.out.println("2) Seller");
                System.out.println("3) exit");
                int userInput = scan.nextInt();
                scan.nextLine();
                switch (userInput) {
                    case 1:
                        return new User(newUserName, password, userEmail, true, false);
                    case 2:
                        return new User(newUserName, password, userEmail, false, true);
                    case 3:
                        System.out.println("Exiting...");
                        return null;
                    default:
                        throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter 1, 2, or 3.");
            }
        }
        return null;
    }

    //this is how the user logs in
    public static User login(ArrayList<User> users, Scanner scan) {
        String checkUsername;
        int indexOfUser = 0;
        boolean validUsername = false;
        boolean validPassword = false;
        System.out.println("Enter your username.");
        while (!validUsername) {
            checkUsername = scan.nextLine();
            if (checkUsername.equals("exit")) {
                return null;
            }
            try {
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
        System.out.println("Please enter your password.");
        while (!validPassword) {
            try {
                String checkPassword = scan.nextLine();
                if (checkPassword.equals(users.get(indexOfUser).getPassword())) {
                    return users.get(indexOfUser);
                } else if (checkPassword.equals("exit")) {
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
    //this is the user interface
    public static void userInterface(Scanner scan, User user) {
        boolean signOut = false;
        int userInput;
        while (!signOut) {
            System.out.println("Enter the number to access your desire feature Features:");
            System.out.println("1) Calendar");
            System.out.println("2) Messages");
            System.out.println("3) Marketplace");
            System.out.println("4) Settings");
            System.out.println("5) Logout");
            try {
                userInput = scan.nextInt();
                scan.nextLine();
                switch (userInput) {
                    case 1:
                        //implement run calendar
                        break;
                    case 2:
                        //implement run message
                        break;
                    case 3:
                        //implement run marketplace
                        break;
                    case 4:
                        //implement settings
                        break;
                    case 5:
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
}