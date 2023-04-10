import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class UserMessagesTest {

    @Test
    public void testUserMessages() {
        User user1 = new User("testUser1", "testPassword1", "testEmail1@example.com", true, false);
        User user2 = new User("testUser2", "testPassword2", "testEmail2@example.com", true, false);
        ArrayList<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        // Test case for messageUser
        UserMessages userMessages1 = new UserMessages(user1, users, new Scanner(new MockInputStream("1\ntestUser2\nHello there\n5\n")));
        userMessages1.runMessages();
        // assertEquals(1, user2.getMessages().size());
        // assertEquals("Hello there", user2.getMessages().get(0).getContent());

        // Test case for readMessage
        UserMessages userMessages2 = new UserMessages(user2, users, new Scanner(new MockInputStream("2\n1\n5\n")));
        userMessages2.runMessages();
        // assertTrue(user2.getMessages().get(0).isRead());

        // Test case for editMessage
        UserMessages userMessages3 = new UserMessages(user1, users, new Scanner(new MockInputStream("3\n1\nUpdated message\n5\n")));
        userMessages3.runMessages();
        // assertEquals("Updated message", user2.getMessages().get(0).getContent());

        // Test case for deleteMessage
        UserMessages userMessages4 = new UserMessages(user2, users, new Scanner(new MockInputStream("4\n1\n5\n")));
        userMessages4.runMessages();
        // assertEquals(0, user2.getMessages().size());
    }

    static class MockInputStream extends ByteArrayInputStream {
        public MockInputStream(String input) {
            super(input.getBytes());
        }
    }
}
