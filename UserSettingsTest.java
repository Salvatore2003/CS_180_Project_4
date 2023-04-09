import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class UserSettingsTest {
    private User user1, user2, user3;
    private ArrayList<User> users;
    private ByteArrayOutputStream output;

    @BeforeEach
    public void setUp() {
        user1 = new User("johnDoe", "password123", "john@example.com", true, false);
        user2 = new User("janeDoe", "password456", "jane@example.com", false, true);
        user3 = new User("admin", "adminPassword", "admin@example.com", true, true);
        users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    @Test
    public void testSetUsername() {
        UserSettings userSettings = new UserSettings(user1, users);
        String input = "newUser\nexit\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scan = new Scanner(System.in);
        userSettings.setUsername(scan);
        assertEquals("newUser", user1.getUserName());
        assertTrue(output.toString().contains("Username has been successfully changed"));
    }

    @Test
    public void testSetPassword() {
        UserSettings userSettings = new UserSettings(user1, users);
        String input = "newPass123\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scan = new Scanner(System.in);
        userSettings.setPassword(scan);
        assertEquals("newPass123", user1.getPassword());
        assertTrue(output.toString().contains("Password successfully changed"));
    }

    @Test
    public void testSetEmail() {
        UserSettings userSettings = new UserSettings(user1, users);
        String input = "newemail@example.com\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scan = new Scanner(System.in);
        userSettings.setEmail(scan);
        assertEquals("newemail@example.com", user1.getUserEmail());
        assertTrue(output.toString().contains("Email successfully changed"));
    }

    @Test
    public void testChangeBuyerOrSeller() {
        UserSettings userSettings = new UserSettings(user1, users);
        String input = "Y\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scan = new Scanner(System.in);
        userSettings.changeBuyerOrSeller(scan);
        assertFalse(user1.isBuyer());
        assertTrue(user1.isSeller());
        assertTrue(output.toString().contains("Role has been changed"));
    }

    @Test
    public void testDeleteAccount() {
        UserSettings userSettings = new UserSettings(user1, users);
        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scan = new Scanner(System.in);
        assertTrue(userSettings.deleteAccount(scan));
        assertFalse(users.contains(user1));
        assertTrue(output.toString().contains("User has been deleted"));
    }
}
