package be.intecbrussel;

import be.intecbrussel.model.Beer;
import be.intecbrussel.service.BeerService;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Logger;

public class MainApp {
    public static final Logger logger = Logger.getLogger(MainApp.class.getName());
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        BeerService beerService = new BeerService();

        //Hoofdmenu
        while (true) {
            logger.info(" ===  Hoofdmenu  === ");
            logger.info("1. Manage Brewers");
            logger.info("2. Manage Categories");
            logger.info("3. Manage Beers");
            logger.info("4. Exit");
            logger.info("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3: HandleBeer(scanner, beerService);
                    break;
                case 4:
                    break;
            }
        }
    }

    //Beer submenu
    public static void HandleBeer (Scanner scanner, BeerService beerService) throws SQLException {

            logger.info("\n ===  Beer Submenu  === ");
            logger.info("1. Add Beer");
            logger.info("2. View All Beers");
            logger.info("3. Find Beer by ID");
            logger.info("4. Update Beer");
            logger.info("5. Delete Beer");
            logger.info("6. Find Beers by Category");
            logger.info("7. Find Beers by Brewer");
            logger.info("8. Find Beers cheaper than X");
            logger.info("9. Back");
            logger.info("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    logger.info("Enter beer name: ");
                    String name = scanner.nextLine();
                    scanner.nextLine();
                    logger.info("Enter beer alcoholpercentage: ");
                    int alcoholPercentage = scanner.nextInt();
                    scanner.nextLine();
                    logger.info("Enter beer price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine();
                    Beer beer = new Beer(name, alcoholPercentage, price);
                    beerService.addBeer(beer);
            }
    }
        }




