import java.util.*;

/**
 * Utility class containing all the methods to add, remove, and edit messages as well as a
 * method that prints the inbox and outbox of the user this class is assigned to.
 */
public class Messages{
    public Messages() {
    }
    
    /**
     * Prints the subjects of the emails in a user's inbox, or prints
     * "Empty Inbox!" if the user's inbox contains no mail
     */
    public void printInbox(User user) {
        ArrayList<Message> inbox = user.getInbox();
        if(inbox.size() == 0) {
            System.out.println("Empty Inbox!");
            return;
        }
        System.out.println("You have " + inbox.size() + " messages.");
        for(int i = 0;i < inbox.size();i++) {
            System.out.println(inbox.get(i).getSubject());
        }
    }
    
    public void printOutbox(User user) {
        ArrayList<Message> outbox = user.getOutbox();
        if(outbox.size() == 0) {
            System.out.println("Empty Inbox!");
            return;
        }
        System.out.println("You have " + outbox.size() + " messages.");
        for(int i = 0;i < outbox.size();i++) {
            System.out.println(outbox.get(i).getSubject());
        }
    }
    
    /**
     * Sends a message to a recipient's outbox containing a subject and a message, 
     * then adds the same message to the sender's outbox. Does not allow buyers
     * to message buyers or sellers to message sellers.
     */
    public void sendMessage(String subject, String message, User sender, User recipient) {
        if(sender.isBuyer() && recipient.isBuyer()) {
            System.out.println("You cannot message another buyer!");
            return;
        }
        if(sender.isSeller() && recipient.isSeller()) {
            System.out.println("You cannot message another seller!");
            return;
        }
        
        try {
            ArrayList<Message> recInbox = recipient.getInbox();
            recInbox.add(new Message(subject, message, sender, recipient));
            recipient.setInbox(recInbox);
            System.out.println("Message sent successfully.");            
        } catch (Exception e) {
            System.out.println("Error sending message.");
        }
    }
    
    /**
     * Edits a message in both the sender's outbox and the recipient's inbox
     */
    public void editMessage(Message oldMessage, String newSubject, String newMessage, User sender, User recipient) {
        try {
            ArrayList<Message> si = sender.getOutbox();
            ArrayList<Message> ri = recipient.getInbox();
            int userIndex = -1;
            for(int i = 0;i < si.size();i++) {
                if(si.get(i).equals(oldMessage)) {
                    userIndex = i;
                }
            }
            int recipientIndex = -1;
            for(int i = 0;i < ri.size();i++) {
                if(si.get(i).equals(oldMessage)) {
                    recipientIndex = i;
                }
            }
            si.get(userIndex).setSubject(newSubject);
            si.get(userIndex).setMessage(newMessage);
            ri.get(recipientIndex).setSubject(newSubject);
            ri.get(recipientIndex).setMessage(newMessage);
            sender.setOutbox(si);
            recipient.setInbox(ri);
            System.out.println("Message edited successfully.");
        } catch (Exception e) {
            System.out.println("Error editing message.");
            return;
        }
    }
    
    /**
     * Deletes a message from the sender's outbox while not deleting it from
     * the recipient's inbox
     */
    public void deleteMessage(Message delMessage, User sender) {
        ArrayList<Message> si = sender.getOutbox();
        for(int i = 0;i < si.size();i++) {
            if(si.get(i).equals(delMessage)) {
                si.remove(i);
                sender.setOutbox(si);
                System.out.println("Message deleted.");
                return;
            }
        }
        System.out.println("Message not found.");
    }
}
