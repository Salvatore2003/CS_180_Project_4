public class Product {
    String productName;
    String storeName;
    String description;
    int quantityAvailable;
    double price;
    int quantitySold;
    //change
    public Product(String productName, String storeName, String description, int quantityAvailable, double price){
        this.productName = productName;
        this.storeName = storeName;
        this.description = description;
        this.quantityAvailable = quantityAvailable;
        this.price = price;
        quantitySold = 0;
    }

    public Product(String productName, String storeName, String description, int quantityAvailable, double price, int quantitySold){
        this.productName = productName;
        this.storeName = storeName;
        this.description = description;
        this.quantityAvailable = quantityAvailable;
        this.price = price;
        this.quantitySold = quantitySold;
    }
}
