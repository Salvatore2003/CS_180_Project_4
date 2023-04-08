import java.util.ArrayList;
import java.util.Scanner;

public class UserMessages {
    User user;
    ArrayList<User> users;
    Scanner scan;
    public UserMessages(User user, ArrayList<User> users, Scanner scan) {
        this.user = user;
        this.users = users;
    }
    public void runMessages(){
        String userInput = "";
        do {
            System.out.println("What would you like to do?");
            System.out.println("1) Message a user");
            System.out.println("2) Read a message");
            System.out.println("3) Edit a message");
            System.out.println("4) Delete a message");
            System.out.println("5) Exit");
            userInput = scan.nextLine();
            switch (userInput){
                case "1":
                    break;
                case "2":
                    break;
                case"3":
                    break;
                case"4":
                    break;
                case"5":
                    break;
            }

        }while (!userInput.equals("5"));
    }
    public static void messageUser() {
        
    }
}
