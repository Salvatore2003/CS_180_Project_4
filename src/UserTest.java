import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    private User user1;
    private User user2;

    @BeforeEach
    public void setUp() {
        user1 = new User("JohnDoe", "password123", "john.doe@example.com", true, false);
        user2 = new User("JaneDoe", "jane123", "jane.doe@example.com");
    }

    @Test
    public void testGetUserName() {
        assertEquals("JohnDoe", user1.getUserName());
        assertEquals("JaneDoe", user2.getUserName());
    }

    @Test
    public void testGetPassword() {
        assertEquals("password123", user1.getPassword());
        assertEquals("jane123", user2.getPassword());
    }

    @Test
    public void testGetUserEmail() {
        assertEquals("john.doe@example.com", user1.getUserEmail());
        assertEquals("jane.doe@example.com", user2.getUserEmail());
    }

    @Test
    public void testIsBuyer() {
        assertEquals(true, user1.isBuyer());
        assertEquals(false, user2.isBuyer());
    }

    @Test
    public void testIsSeller() {
        assertEquals(false, user1.isSeller());
        assertEquals(false, user2.isSeller());
    }

    @Test
    public void testSetUserName() {
        user1.setUserName("NewJohnDoe");
        assertEquals("NewJohnDoe", user1.getUserName());
    }

    @Test
    public void testSetPassword() {
        user1.setPassword("newPassword123");
        assertEquals("newPassword123", user1.getPassword());
    }

    @Test
    public void testSetUserEmail() {
        user1.setUserEmail("new.john.doe@example.com");
        assertEquals("new.john.doe@example.com", user1.getUserEmail());
    }

    @Test
    public void testSetBuyer() {
        user2.setBuyer(true);
        assertEquals(true, user2.isBuyer());
    }

    @Test
    public void testSetSeller() {
        user2.setSeller(true);
        assertEquals(true, user2.isSeller());
    }
}
