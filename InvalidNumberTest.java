import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InvalidNumberTest {
    @Test
    void invalidNumberExceptionTest() {
        String expectedMessage = "Invalid number exception test";
        InvalidNumber exception = new InvalidNumber(expectedMessage);

        // Check if the exception has the expected message
        assertEquals(expectedMessage, exception.getMessage());

        // Check if the exception is an instance of Exception
        assertTrue(exception instanceof Exception);
    }
}
