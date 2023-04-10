import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;

/**
 * Runs the customer and seller marketplace interface
 *
 * @version 2022-07-25
 * @author Purdue CS
 */
public class MarketPlace {
    Scanner scan;
    String user;
    String email;


    public MarketPlace(String user, String email, Scanner scan) {
        this.user = user;
        this.email = email;
        this.scan = scan;
    }

    public MarketPlace() {

    }

    /**
     * All basic functions for seller
     *
     */
    public void runSeller() {
        ArrayList<Store> stores = new ArrayList<>();
        int userInput = 0;
        do {
            try {
                System.out.println("Select one of the following:");
                System.out.println("1) Edit Store");
                System.out.println("2) Create Store");
                System.out.println("3) Delete Store");
                System.out.println("4) Exit");
                userInput = scan.nextInt();

                scan.nextLine();
                if (userInput == 1) {
                    editStore();
                } else if (userInput == 2) {
                    System.out.println("Please enter the name of the store you want to create");
                    String storeName = scan.nextLine();
                    Store store = new Store(user, storeName);
                    stores.add(store);
                    if (createFile(user, storeName)) {
                        System.out.println("Store has been created");
                    }
                } else if (userInput == 3) {
                    System.out.println("Please enter the name of the store you want to delete");
                    String storeName = scan.nextLine();
                    String fileName = user + "_" + storeName;
                    System.out.println(fileName);
                    deleteStore(fileName);

                } else if (userInput == 4) {

                } else {
                    throw new InvalidNumber("Please enter 1, 2, 3, or 4");
                }
            } catch (InvalidNumber e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Please enter 1, 2, 3, or 4");
                scan.nextLine();
            }

        } while (!(userInput == 4));
    }

    /**
     * Shows customer all products of a shop of his choosing
     *
     */
    public void runCustomer() {
        System.out.println("Please enter the name of the store you want to access");
        String storeName = scan.nextLine();
        String directory = "./src/"; //Directory path must be here
        File dir = new File(directory);
        File[] files = dir.listFiles();
        ArrayList<Product> products = new ArrayList<>();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].getName().contains(storeName)) {

                    products = readFile(files[i].getName(), storeName);
                }
            }
            for(int i = 0; i < products.size(); i++) {
                System.out.printf("Product Name: %s \n", products.get(i).getProductName());
                System.out.printf("Product Description: %s \n", products.get(i).getDescription());
                System.out.printf("Product Price: %.2f \n", products.get(i).getPrice());
                System.out.println("-----------------------");
            }
        } else {
            System.out.println("No such files found in directory.");
        }
    }

    /**
     * Creates a file for a new store
     *
     */
    public boolean createFile(String user, String storeName) {
        File dir = new File("./src/");
        String fileName = user + "_" + storeName;
        File file = new File(dir, fileName);
        boolean flag;
        try {
            flag = file.createNewFile();
            if (flag) {
                System.out.println("File created successfully.");
            } else {
                System.out.println("File already exists.");
                flag = false;
            }
        } catch (IOException e) {
            System.out.println("Error occurred while creating file");
            flag = false;
        }
        return flag;
    }

    /**
     * edits a store
     *
     */
    public void editStore() {
        System.out.println("Please enter the name of the store that you would like to edit");
        String storeName = scan.nextLine();
        String fileName = user + "_" + storeName;
        ArrayList<Product> products = new ArrayList<>();
        products = readFile(fileName, storeName);

        int userInput = 0;
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
                    ArrayList<Product> productsAdd = new ArrayList<>();
                    productsAdd = takeInput(storeName);
                    products.addAll(productsAdd);
                    writeFile(fileName, products);

                } else if (userInput == 2) {
                    System.out.println("Please enter the name of the product to be edited");
                    String productName1 = scan.nextLine();
                    Product product = takeInputEdit(storeName);
                    for (int i = 0; i < products.size(); i++) {
                        if (products.get(i).productName.equals(productName1)) {
                            products.set(i, product);
                        }
                    }
                    writeFile(fileName, products);

                } else if (userInput == 3) {
                    System.out.println("Please enter the name of the product to be deleted");
                    String productName1 = scan.nextLine();
                    for (int i = 0; i < products.size(); i++) {
                        if (products.get(i).productName.equals(productName1)) {
                            products.remove(products.get(i));
                        }
                    }
                    writeFile(fileName, products);    

                } else if (userInput == 4) {

                } else {
                    throw new InvalidNumber("Please enter 1, 2, 3, or 4");
                }
            } catch (InvalidNumber e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Please enter 1, 2, 3, or 4");
                scan.nextLine();
            }
        } while (!(userInput == 4));
    }

    /**
     * writes changes in a store to the file
     *
     */
    public void writeFile(String fileName, ArrayList<Product> products) {
        String directory = "./src/"; //Directory path must be here
        File dir = new File(directory);
        File file = new File(dir, fileName);
        String infoLine;
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, false));
            for (int i = 0; i < products.size(); i++) {
                infoLine = String.format("%s,%s,%s,%d,%f,%d", products.get(i).getProductName(),
                        products.get(i).getStoreName(), products.get(i).getDescription(),
                        products.get(i).getQuantityAvailable(), products.get(i).getPrice(),
                        products.get(i).getQuantitySold());
                bw.write(infoLine);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * reads stored information from file
     *
     */
    public ArrayList<Product> readFile(String fileName, String storeName) {
        ArrayList<Product> products = new ArrayList<>();
        String productName;
        String description;
        int quantityAvailable;
        double price;
        int quantitySold;
        String[] split;

        try {
            String directory = "./src/"; //Directory path must be here
            File dir = new File(directory);
            File input = new File(dir, fileName);
            FileReader fr = new FileReader(input);
            BufferedReader bfr = new BufferedReader(fr);
            String infoLine = bfr.readLine();
            while (infoLine != null) {
                split = infoLine.split(",", 6);
                productName = split[0];
                description = split[2];
                quantityAvailable = Integer.parseInt(split[3]);
                price = Double.parseDouble(split[4]);
                quantitySold = Integer.parseInt(split[5]);
                Product product = new Product(productName, storeName, description, quantityAvailable, price, quantitySold);
                products.add(product);
                infoLine = bfr.readLine();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    /**
     * takes input from user for adding products
     *
     */
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
                        split = infoLine.split(",", 6);
                        productName = split[0];
                        description = split[2];
                        quantityAvailable = Integer.parseInt(split[3]);
                        price = Double.parseDouble(split[4]);
                        quantitySold = Integer.parseInt(split[5]);
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
    /**
     * takes input from user for just editing products
     *
     */
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

    /**
     * deletes file for the store
     *
     */
    public boolean deleteStore(String fileName) {
        String directory = "./src/"; //Directory path must be here
        File dir = new File(directory);
        File file = new File(dir, fileName);
        boolean flag = file.delete();
            if (flag) {
                System.out.println("Store deleted successfully.");
            } else {
                System.out.println("Failed to delete Store.");
            }
        return flag;
    }

    /**
     * deletes all files of the user
     *
     */
    public void deleteUser(String user) {
        String directory = "./src/"; //Directory path must be here
        File dir = new File(directory);
        File[] files = dir.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].getName().contains(user)) {
                    deleteStore(files[i].getName());
                }
            }
        } else {
            System.out.println("No such files found in directory.");
        }
    }
}
