import java.util.ArrayList;
import java.util.IllformedLocaleException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;

public class MarketPlace {
    Scanner scan;
    String user;
    String email;


    public MarketPlace(String user, String email, Scanner scan) {
        this.user = user;
        this.email = email;
        this.scan = scan;
    }

    public void runSeller() {
        ArrayList<Store> stores = new ArrayList<>();
        int userInput = 0;
        do {
            try {
                System.out.println("Select one of the following:");
                System.out.println("1) Edit Store");
                System.out.println("2) Create Store");
                System.out.println("3) View Sales");
                System.out.println("4) Exit");
                userInput = scan.nextInt();

                scan.nextLine();
                if (userInput == 1) {
                    editStore(stores);
                } else if (userInput == 2) {
                    System.out.println("Please enter the name of the store you want to create");
                    String storeName = scan.nextLine();
                    Store store = new Store(user, storeName);
                    stores.add(store);
                    System.out.println("Store has been created");
                } else if (userInput == 3) {
                    viewSales(stores);
                } else if (userInput == 4) {

                } else {
                    throw new InvalidNumber("Please enter 1, 2, 3, or 4");
                }
            }catch (InvalidNumber e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Please enter 1, 2, 3, or 4");
                scan.nextLine();
            }

        } while (!(userInput == 4));
    }

    public void editStore(ArrayList<Store> stores) {
        System.out.println("Please enter the name of the store that you would like to edit");
        String storeName = scan.nextLine();
        Store store1 = new Store();
        for (int i = 0; i < stores.size(); i++) {
            if (stores.get(i).getStoreName().equals(storeName)) {
                store1 = stores.get(i);
            }
        }

        int userInput = 0;
        ArrayList<Product> products = new ArrayList<>();
        do {
            try {
                System.out.println("Select one of the following:");
                System.out.println("1) Add Product");
                System.out.println("2) Edit Product");
                System.out.println("3) Delete Product");
                System.out.println("4) Back to Menu");
                userInput = scan.nextInt();
                scan.nextLine();
                if (userInput == 1) {
                    products = takeInput(store1.getStoreName());
                    for (int i = 0; i < products.size(); i++) {
                        store1.addProduct(products.get(i));
                    }
                } else if (userInput == 2) {
                    System.out.println("Please enter the name of the product to be edited");
                    String productName1 = scan.nextLine();
                    Product product = takeInputEdit(store1.getStoreName());
                    store1.editProduct(productName1, product);

                } else if (userInput == 3) {
                    System.out.println("Please enter the name of the product to be deleted");
                    String productName1 = scan.nextLine();
                    store1.removeProduct(productName1);

                } else if (userInput == 4) {

                } else {
                    throw new InvalidNumber("Please enter 1, 2, 3, or 4");
                }
            }catch (InvalidNumber e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Please enter 1, 2, 3, or 4");
                scan.nextLine();
            }
        } while (!(userInput == 4));

    }

    public void viewSales(ArrayList<Store> stores){
        for (int i = 0; i < stores.size(); i++) {
            System.out.println(stores.get(i).getStoreName());
            System.out.println("------------------");
            for (int j = 0; i < stores.get(i).productSize(); j++) {
                System.out.println("Product Name:");
                System.out.println(stores.get(i).getProducts(j).productName);
                System.out.println("Quantity Sold:");
                System.out.println(stores.get(i).getProducts(j).quantitySold);
            }
        }
    }

    public ArrayList<Product> takeInput(String storeName) {
        ArrayList<Product> products = new ArrayList<>();
        int userInput = 0;
        String productName;
        String description;
        int quantityAvailable;
        double price;
        int quantitySold;
        String fileName;
        String[] split;
        do {
            System.out.println("How would you like to enter the Product information");
            System.out.println("1) Manually for one product");
            System.out.println("2) Input file for multiple products");
            userInput = scan.nextInt();
            scan.nextLine();

            if (userInput == 1) {
                System.out.println("What is the name of the Product?");
                productName = scan.nextLine();
                System.out.println("What is the description of the Product?");
                description = scan.nextLine();
                System.out.println("What is the quantity available of the Product?");
                quantityAvailable = scan.nextInt();
                System.out.println("What is the price of the Product?");
                price = scan.nextDouble();
                System.out.println("What is the quantity sold of the Product?");
                quantitySold = scan.nextInt();
                Product product = new Product(productName, storeName, description, quantityAvailable, price, quantitySold);
                products.add(product);
            }

            if (userInput == 2) {
                System.out.println("What is the name of the File?");
                fileName = scan.nextLine();
                try {
                    File input = new File(fileName);
                    FileReader fr = new FileReader(input);
                    BufferedReader bfr = new BufferedReader(fr);
                    String infoLine = bfr.readLine();
                    while (infoLine != null) {
                        split = infoLine.split(",", 5);
                        productName = split[0];
                        description = split[1];
                        quantityAvailable = Integer.parseInt(split[2]);
                        price = Double.parseDouble(split[3]);
                        quantitySold = Integer.parseInt(split[4]);
                        Product product = new Product(productName, storeName, description, quantityAvailable, price, quantitySold);
                        products.add(product);
                        infoLine = bfr.readLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } while (!(userInput == 1 || userInput == 2));

        return products;
    }

    public Product takeInputEdit(String storeName) {
        String productName;
        String description;
        int quantityAvailable;
        double price;
        int quantitySold;

        System.out.println("What is the name of the new Product?");
        productName = scan.nextLine();
        System.out.println("What is the description of the new Product?");
        description = scan.nextLine();
        System.out.println("What is the quantity available of the new Product?");
        quantityAvailable = scan.nextInt();
        System.out.println("What is the price of the new Product?");
        price = scan.nextDouble();
        System.out.println("What is the quantity sold of the new Product?");
        quantitySold = scan.nextInt();
        Product product = new Product(productName, storeName, description, quantityAvailable, price, quantitySold);

        return product;

    }
}
