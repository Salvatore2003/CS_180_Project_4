import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class StoreTest {

    private Store store;
    private Product product1;
    private Product product2;

    @BeforeEach
    public void setUp() {
        store = new Store("John", "Electronics Store");
        product1 = new Product("Laptop", "Electronics Store", "High-performance gaming laptop", 10, 1500.0);
        product2 = new Product("Smartphone", "Gadget Store", "Latest Android smartphone", 20, 800.0, 5);
    }

    @Test
    public void testGetStoreName() {
        assertEquals("Electronics Store", store.getStoreName());
    }

    @Test
    public void testAddProduct() {
        store.addProduct(product1);
        assertEquals(1, store.productSize());
        store.addProduct(product2);
        assertEquals(2, store.productSize());
    }

    @Test
    public void testRemoveProduct() {
        store.addProduct(product1);
        store.addProduct(product2);
        store.removeProduct("Laptop");
        assertEquals(1, store.productSize());
    }

    @Test
    public void testRemoveProductInvalidInput() {
        store.addProduct(product1);
        store.addProduct(product2);
        assertThrows(NoSuchElementException.class, () -> store.removeProduct("Nonexistent Product"));
    }

    @Test
    public void testEditProduct() {
        store.addProduct(product1);
        store.addProduct(product2);
        Product editedProduct = new Product("Laptop", "Electronics Store", "High-performance gaming laptop", 5, 1300.0);
        store.editProduct("Laptop", editedProduct);
        assertEquals(5, store.getProducts(0).getQuantityAvailable());
        assertEquals(1300.0, store.getProducts(0).getPrice(), 0.001);
    }

    @Test
    public void testEditProductInvalidInput() {
        store.addProduct(product1);
        store.addProduct(product2);
        Product editedProduct = new Product("Nonexistent Product", "Unknown Store", "No description", 0, 0.0);
        assertThrows(NoSuchElementException.class, () -> store.editProduct("Nonexistent Product", editedProduct));
    }
}
