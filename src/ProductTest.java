import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ProductTest {

    private Product product1;
    private Product product2;

    @BeforeEach
    public void setUp() {
        product1 = new Product("Laptop", "Electronics Store", "High-performance gaming laptop", 10, 1500.0);
        product2 = new Product("Smartphone", "Gadget Store", "Latest Android smartphone", 20, 800.0, 5);
    }

    @Test
    public void testGetProductName() {
        assertEquals("Laptop", product1.getProductName());
        assertEquals("Smartphone", product2.getProductName());
    }

    @Test
    public void testGetStoreName() {
        assertEquals("Electronics Store", product1.getStoreName());
        assertEquals("Gadget Store", product2.getStoreName());
    }

    @Test
    public void testGetDescription() {
        assertEquals("High-performance gaming laptop", product1.getDescription());
        assertEquals("Latest Android smartphone", product2.getDescription());
    }

    @Test
    public void testGetQuantityAvailable() {
        assertEquals(10, product1.getQuantityAvailable());
        assertEquals(20, product2.getQuantityAvailable());
    }

    @Test
    public void testGetPrice() {
        assertEquals(1500.0, product1.getPrice(), 0.001);
        assertEquals(800.0, product2.getPrice(), 0.001);
    }

    @Test
    public void testGetQuantitySold() {
        assertEquals(0, product1.getQuantitySold());
        assertEquals(5, product2.getQuantitySold());
    }
}
