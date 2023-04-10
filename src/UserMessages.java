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
    /**
     * Case 1: Asks the user for a username they would like to message. Attempts to find a user
     * with that username, if it is unable to then it prints an error message and breaks.
     * Case 2: Prompts the user with their inbox, asks them to enter the subject of the message
     * they want to read. If they enter an invalid subject, prints an error message and breaks.
     * Case 3: Prompts the user with their outbox, asks them to enter the subject of the 
     * message they want to edit. If they enter an invalid subject, prints an error message
     * and breaks.
     * Case 4: Prompts them with their inbox and outbox, asks them to enter the subject of the 
     * message they want to delete. If they enter an invalid subject, prints an error message and
     * breaks.
     * Case 5: Breaks
     */
    public void runMessages(){
        Messages m = new Messages();
        String userInput = "";
        do {
            System.out.println("What would you like to do?");
            System.out.println("1) Message a user");
            System.out.println("2) Read a message");
            System.out.println("3) Edit a message");
            System.out.println("4) Delete a message");
            System.out.println("5) Exit");
            userInput = scan.nextLine();
            ArrayList<Message> userInbox = user.getInbox();
            ArrayList<Message> userOutbox = user.getOutbox();
            switch (userInput){
                case "1":
                    System.out.println("Enter the user you would like to message.");
                    String username = scan.nextLine();
                    User recipient = null;
                    for(int i = 0;i < users.size();i++) {
                        if(username.equals(users.get(i).getUserName())) {
                            recipient = users.get(i);
                        }
                    }
                    if(recipient == null) {
                        System.out.println("User not found!");
                        break;
                    }
                    System.out.println("Enter the subject of the message.");
                    String subject = scan.nextLine();
                    System.out.println("Enter the message you would like to send.");
                    String message = scan.nextLine();
                    m.sendMessage(subject, message, user, recipient);
                case "2":
                    if(userInbox.size() == 0) {
                        System.out.println("You do not have any mail!");
                        break;
                    }
                    m.printInbox(user);
                    System.out.println("Which message do you want to read?");
                    userInput = scan.nextLine().toLowerCase();
                    for(int i = 0;i < userInbox.size();i++) {
                        if(userInbox.get(i).getSubject().toLowerCase().equals(userInput)) {
                            System.out.println(userInbox.get(i).getSubject());
                            System.out.println(userInbox.get(i).getMessage());
                            System.out.println("From " + userInbox.get(i).getRecipient());
                            break;
                        }
                    }
                case"3":
                    if(userInbox.size() == 0) {
                        System.out.println("You do not have any mail!");
                        break;
                    }
                    m.printOutbox(user);
                    System.out.println("Which message do you want to edit?");
                    userInput = scan.nextLine().toLowerCase();
                    for(int i = 0;i < userOutbox.size();i++) {
                        if(userOutbox.get(i).getSubject().toLowerCase().equals(userInput)) {
                            System.out.println("What would you like the new subject to be?");
                            String newSubject = scan.nextLine();
                            System.out.println("What would you like the new message to be?");
                            String newMessage = scan.nextLine();
                            m.editMessage(userOutbox.get(i), newSubject, newMessage, userOutbox.get(i).getSender(), userOutbox.get(i).getRecipient());
                            break;
                        }
                    }
                    System.out.println("Message does not exist!");
                case"4":
                    if(userInbox.size() == 0) {
                        System.out.println("You do not have any mail!");
                        break;
                    }
                    m.printInbox(user);
                    m.printOutbox(user);
                    System.out.println("Which message would you like to delete?");
                    userInput = scan.nextLine();
                    for(int i = 0;i < userInbox.size();i++) {
                        if(userInbox.get(i).getSubject().toLowerCase().equals(userInput)) {
                            m.deleteMessage(userInbox.get(i), user);
                            System.out.println("Message deleted from inbox.");
                        }
                        if(userOutbox.get(i).getSubject().toLowerCase().equals(userInput)) {
                            m.deleteMessage(userOutbox.get(i), user);
                            System.out.println("Message deleted from outbox.");
                        }
                    }
                case"5":
                    break;
            }

        }while (!userInput.equals("5"));
    }
}
