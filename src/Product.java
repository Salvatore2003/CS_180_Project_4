public class Product {
    String productName;
    String storeName;
    String description;
    int quantityAvailable;
    double price;
    int quantitySold;
    //change

    /**
     * It is the object class for a product
     *
     * @version 2022-07-25
     * @author Purdue CS
     */
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

    public String getProductName(){
        return productName;
    }

    public String getStoreName(){
        return storeName;
    }

    public String getDescription(){
        return description;
    }

    public int getQuantityAvailable(){
        return quantityAvailable;
    }

    public double getPrice(){
        return price;
    }

    public int getQuantitySold(){
        return quantitySold;
    }
}
