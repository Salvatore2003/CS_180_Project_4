import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InvalidNumberTest {

    @Test
    public void testInvalidNumberException() {
        InvalidNumber invalidNumber = new InvalidNumber("Invalid number input");

        assertNotNull(invalidNumber);
        assertEquals("Invalid number input", invalidNumber.getMessage());
    }

    @Test
    public void testInvalidNumberExceptionHandling() {
        assertThrows(InvalidNumber.class, () -> {
            throwInvalidNumberException();
        });
    }

    private void throwInvalidNumberException() throws InvalidNumber {
        throw new InvalidNumber("Invalid number input");
    }
}
