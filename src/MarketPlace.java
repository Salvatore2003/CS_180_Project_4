import java.util.ArrayList;
import java.util.IllformedLocaleException;
import java.util.InputMismatchException;
import java.util.Scanner;

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
                scan.nextLine();
                userInput = scan.nextInt();
                scan.nextLine();
                if (userInput == 1) {
                    System.out.println("execute 1");
                } else if (userInput == 2) {
                    System.out.println("execute 2");
                } else if (userInput == 3) {
                    System.out.println("execute 3");
                } else if (userInput == 4) {
                    System.out.println("execute 4");
                } else {
                    throw new InputMismatchException();
                }

            } catch (InputMismatchException e) {
                System.out.println("Please enter 1, 2, 3, or 4");

            }

        } while (!(userInput == 4));
    }
}
