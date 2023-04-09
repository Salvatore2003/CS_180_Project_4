import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InvalidLoginTest {

    @Test
    public void testInvalidLoginException() {
        InvalidLogin invalidLogin = new InvalidLogin("Invalid username or password");

        assertNotNull(invalidLogin);
        assertEquals("Invalid username or password", invalidLogin.getMessage());
    }

    @Test
    public void testInvalidLoginExceptionHandling() {
        assertThrows(InvalidLogin.class, () -> {
            throwInvalidLoginException();
        });
    }

    private void throwInvalidLoginException() throws InvalidLogin {
        throw new InvalidLogin("Invalid username or password");
    }
}
