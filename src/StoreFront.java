import java.util.ArrayList;
import java.util.Scanner;

public class StoreFront {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<User> users = new ArrayList<>();
        String userInput;
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
        }
    }

    public static User createAccount(ArrayList<User> users, Scanner scan) {
        String newUserName = null;
        boolean validInput = false;
        boolean userName5Char = true;
        String password;
        while (!validInput) {
            System.out.println("Enter a username:");
            newUserName = scan.nextLine();
            if (newUserName.equals("exit")) {
                return null;
            }
            if (newUserName.length() < 5) {
                System.out.println("Username must be at least 5 characters long. Try another or type exit to leave");
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
                break;
            }
            if (password.length() < 5) {
                System.out.println("The password must be 5 character long. Try another or type exit to leave.");
            } else {
                return new User(newUserName, password);
            }
        }
        return null;
    }
}
