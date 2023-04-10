import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MarketPlaceTest {
    private MarketPlace marketPlace;
    private String user;
    private String storeName;
    private String fileName;
    private Product testProduct;

    @Before
    public void setup() {
        marketPlace = new MarketPlace();
        user = "testuser";
        storeName = "teststore";
        fileName = user + "_" + storeName;
        testProduct = new Product("testProduct", storeName, "testDescription", 10, 50.0, 5);
    }

    @Test
    public void testCreateFile() {
        File file = new File(fileName);

        // Delete the file if it already exists
        if (file.exists()) {
            file.delete();
        }

        // Test creating a new file
        boolean result = marketPlace.createFile(user, storeName);
        assertTrue("File should be created successfully", result);
        assertTrue("File should exist after creation", file.exists());

        // Test trying to create the same file again
        result = marketPlace.createFile(user, storeName);
        assertFalse("File should not be created if it already exists", result);
        assertTrue("File should still exist after attempting to create again", file.exists());

        // Clean up: delete the test file
        file.delete();
    }

    @Test
    public void testWriteAndReadFile() throws IOException {
        ArrayList<Product> products = new ArrayList<>();
        products.add(testProduct);

        // Create file and write test data
        marketPlace.createFile(user, storeName);
        marketPlace.writeFile(fileName, products);

        // Read data from file
        ArrayList<Product> readProducts = marketPlace.readFile(fileName, storeName);

        // Verify the read data
        assertEquals("Size of read products list should match written products list", products.size(), readProducts.size());
        Product readProduct = readProducts.get(0);
        assertEquals("Product name should match", testProduct.getProductName(), readProduct.getProductName());
        assertEquals("Product description should match", testProduct.getDescription(), readProduct.getDescription());
        assertEquals("Product quantity available should match", testProduct.getQuantityAvailable(), readProduct.getQuantityAvailable());
        assertEquals("Product price should match", testProduct.getPrice(), readProduct.getPrice(), 0.001);
        assertEquals("Product quantity sold should match", testProduct.getQuantitySold(), readProduct.getQuantitySold());

        // Clean up: delete the test file
        Files.deleteIfExists(Paths.get(fileName));
    }

    @Test
    public void testDeleteStore() {
        // Create a file for the test store
        marketPlace.createFile(user, storeName);
        File file = new File(fileName);

        // Test deleting the store
        boolean result = marketPlace.deleteStore(fileName);
        assertTrue("File should be deleted successfully", result);
        assertFalse("File should not exist after deletion", file.exists());
    }

    @Test
    public void testDeleteUser() {
        // Create a file for the test user and store
        marketPlace.createFile(user, storeName);
        File file = new File(fileName);

        // Verify that the file was created
        assertTrue("File should exist before deletion", file.exists());

        // Test deleting all files of the user
        marketPlace.deleteUser(user);

        // Verify that the file was deleted
        assertFalse("File should not exist after deletion", file.exists());
    }
}

