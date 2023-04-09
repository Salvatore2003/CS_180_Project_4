import java.util.ArrayList;
public class Messages{
    ArrayList<Message> inbox;
    public Messages(ArrayList<Message> inbox) {
        this.inbox = inbox;
    }
    
    public void printInbox() {
        if(inbox.size() == 0) {
            System.out.println("Inbox empty!");
            return;
        }
        System.out.println("You have " + inbox.size() + " messages.");
        for(int i = 0;i < inbox.size();i++) {
            System.out.println(inbox.get(i).getSubject());
        }
    }
    
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
    
    public void editMessage(Message oldMessage, String newSubject, String newMessage, User sender, User recipient) {
        try {
            ArrayList<Message> si = sender.getInbox();
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
            System.out.println("Message edited successfully.");
        } catch (Exception e) {
            System.out.println("Error editing message.");
            return;
        }
    }
    
    public void deleteMessage(Message delMessage, User sender) {
        ArrayList<Message> si = sender.getInbox();
        for(int i = 0;i < si.size();i++) {
            if(si.get(i).equals(delMessage)) {
                si.remove(i);
            }
        }
    }
}
