/**import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Scanner;

public class UserMessagesTest {
    private User user1;
    private User user2;
    private ArrayList<User> users;
    private Scanner scan;
    private UserMessages userMessages;
    
    @Before
    public void setUp() {
        user1 = new User("Alice");
        user2 = new User("Bob");
        users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        scan = new Scanner(System.in);
        userMessages = new UserMessages(user1, users, scan);
    }
    
    @Test
    public void testRunMessages() {
        // Test that user can exit
        String input = "5\n";
        System.setIn(input.getBytes());
        userMessages.runMessages();
        assertEquals("5", input.trim());
        
        // Test that user can message a user
        input = "1\nBob\nHello Bob!\n";
        System.setIn(input.getBytes());
        userMessages.runMessages();
        assertEquals("1", input.split("\n")[0]);
        assertEquals("Bob", input.split("\n")[1]);
        assertEquals("Hello Bob!", input.split("\n")[2]);
        
        // Test that user can read a message
        input = "2\n";
        System.setIn(input.getBytes());
        userMessages.runMessages();
        assertEquals("2", input.trim());
        
        // Test that user can edit a message
        input = "3\n1\nNew message\n";
        System.setIn(input.getBytes());
        userMessages.runMessages();
        assertEquals("3", input.split("\n")[0]);
        assertEquals("1", input.split("\n")[1]);
        assertEquals("New message", input.split("\n")[2]);
        
        // Test that user can delete a message
        input = "4\n1\n";
        System.setIn(input.getBytes());
        userMessages.runMessages();
        assertEquals("4", input.split("\n")[0]);
        assertEquals("1", input.split("\n")[1]);
    }
    
    @Test
    public void testMessageUser() {
        // Test that message is sent to the correct user
        String message = "Hello Bob!";
        UserMessages.messageUser(user1, user2, message);
        assertEquals(1, user2.getMessages().size());
        assertEquals(message, user2.getMessages().get(0).getMessage());
        
        // Test that message is added to existing messages for user
        message = "How are you?";
        UserMessages.messageUser(user2, user1, message);
        assertEquals(2, user1.getMessages().size());
        assertEquals(message, user1.getMessages().get(1).getMessage());
    }
}*/
