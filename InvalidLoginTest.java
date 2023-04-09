import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InvalidLoginTest {
    @Test
    void invalidLoginExceptionTest() {
        String expectedMessage = "Invalid login exception test";
        InvalidLogin exception = new InvalidLogin(expectedMessage);

        // Check if the exception has the expected message
        assertEquals(expectedMessage, exception.getMessage());

        // Check if the exception is an instance of Exception
        assertTrue(exception instanceof Exception);
    }
}
