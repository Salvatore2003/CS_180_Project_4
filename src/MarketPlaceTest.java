import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MarketPlaceTest {
    MarketPlace marketPlace = new MarketPlace();

    @Test
    void testCreateFile(@TempDir Path tempDir) {
        String user = "testUser";
        String storeName = "testStore";

        marketPlace.setDirectory(tempDir.toString());
        assertTrue(marketPlace.createFile(user, storeName));
        assertTrue(marketPlace.fileExists(user + "_" + storeName));
    }

    @Test
    void testCreateFile_invalidUserOrStoreName(@TempDir Path tempDir) {
        String invalidUser = "test/User";
        String invalidStoreName = "test/Store";

        marketPlace.setDirectory(tempDir.toString());
        assertThrows(IllegalArgumentException.class, () -> marketPlace.createFile(invalidUser, invalidStoreName));
    }

    @Test
    void testAddItemToFile(@TempDir Path tempDir) {
        String user = "testUser";
        String storeName = "testStore";
        String itemName = "itemName";
        double price = 10.0;
        int quantity = 5;

        marketPlace.setDirectory(tempDir.toString());
        marketPlace.createFile(user, storeName);
        assertTrue(marketPlace.addItemToFile(user + "_" + storeName, itemName, price, quantity));

        ArrayList<String> expectedContent = new ArrayList<>();
        expectedContent.add(itemName + "," + price + "," + quantity);

        assertEquals(expectedContent, marketPlace.readFile(user + "_" + storeName));
    }

    @Test
    void testAddItemToFile_invalidFile(@TempDir Path tempDir) {
        String fileName = "nonExistentStore";
        String itemName = "itemName";
        double price = 10.0;
        int quantity = 5;

        marketPlace.setDirectory(tempDir.toString());
        assertFalse(marketPlace.addItemToFile(fileName, itemName, price, quantity));
    }

    @Test
    void testEditItemInFile(@TempDir Path tempDir) {
        String user = "testUser";
        String storeName = "testStore";
        String itemName = "itemName";
        double price = 10.0;
        int quantity = 5;
        String newDescription = "New description";

        marketPlace.setDirectory(tempDir.toString());
        marketPlace.createFile(user, storeName);
        marketPlace.addItemToFile(user + "_" + storeName, itemName, price, quantity);

        assertTrue(marketPlace.editItemInFile(user + "_" + storeName, itemName, newDescription, price, quantity));

        ArrayList<String> expectedContent = new ArrayList<>();
        expectedContent.add(itemName + "," + newDescription + "," + price + "," + quantity);

        assertEquals(expectedContent, marketPlace.readFile(user + "_" + storeName));
    }

    @Test
    void testEditItemInFile_invalidFile(@TempDir Path tempDir) {
        String fileName = "nonExistentStore";
        String itemName = "itemName";
        double price = 10.0;
        int quantity = 5;
        String newDescription = "New description";

        marketPlace.setDirectory(tempDir.toString());
        assertFalse(marketPlace.editItemInFile(fileName, itemName, newDescription, price, quantity));
    }

    @Test
    void testDeleteItemInFile(@TempDir Path tempDir) {
        String user = "testUser";
        String storeName = "testStore";
        String itemName = "itemName";
        double price = 10.0;
        int quantity = 5;

       marketPlace.setDirectory(tempDir.toString());
    marketPlace.createFile(user, storeName);
    marketPlace.addItemToFile(user + "_" + storeName, itemName, price, quantity);

    assertTrue(marketPlace.deleteItemInFile(user + "_" + storeName, itemName));

    ArrayList<String> expectedContent = new ArrayList<>();
    assertEquals(expectedContent, marketPlace.readFile(user + "_" + storeName));
}

    @Test
    void testDeleteItemInFile_invalidFile(@TempDir Path tempDir) {
        String fileName = "nonExistentStore";
        String itemName = "itemName";

        marketPlace.setDirectory(tempDir.toString());
        assertFalse(marketPlace.deleteItemInFile(fileName, itemName));
}
  
}
