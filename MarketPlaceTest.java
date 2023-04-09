import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

class MarketPlaceTest {
    private MarketPlace marketPlace;

    @BeforeEach
    void setUp() {
        String user = "testUser";
        String email = "testUser@example.com";
        Scanner scan = new Scanner(System.in);
        marketPlace = new MarketPlace(user, email, scan);
    }

    @Test
    void createAndDeleteStoreFile() {
        String user = "testUser";
        String storeName = "testStore";
        String fileName = user + "_" + storeName;

        // Test file creation
        assertTrue(marketPlace.createFile(user, storeName));

        // Check if the file exists
        File file = new File(fileName);
        assertTrue(file.exists());

        // Test file deletion
        assertTrue(marketPlace.deleteStore(fileName));

        // Check if the file was deleted
        assertFalse(file.exists());
    }
}
