package be.intecbrussel;

import be.intecbrussel.config.JpaConfig;
import be.intecbrussel.controller.BeerConctroller;
import be.intecbrussel.model.Beer;
import be.intecbrussel.service.BeerService;
import jakarta.persistence.EntityManager;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class MainApp {
    public static final Logger logger = Logger.getLogger(MainApp.class.getName());
    public static Scanner scanner = new Scanner(System.in);
    public final static EntityManager em = JpaConfig.getEntityManager();

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
                case 3:
                    HandleBeer(scanner, beerService);
                    break;
                case 4:
                    logger.info("GoodBy! ");
                    return;
            }
        }
    }

    //Beer submenu
    public static void HandleBeer(Scanner scanner, BeerService beerService) throws SQLException {
        Beer beer = new Beer();

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
            case 1: BeerConctroller.addConctroller();
                break;
            case 2: BeerConctroller.viewConctroller();
                break;
            case 3: BeerConctroller.findConctroller();
                break;
            case 4: BeerConctroller.findConctroller();
                break;
            case 5:
                logger.info("Delete beer by ID: ");
                Long beerIdDelete = scanner.nextLong();
                scanner.nextLine();
                beerService.removeBeer(beerIdDelete);
                break;
            case 6:
                logger.info("Find beer by Category: ");
                Long beerCategoryIdDelete = scanner.nextLong();
                scanner.nextLine();
                beerService.getBeersByCategory(beerCategoryIdDelete);
                break;
            case 7:
                logger.info("Find beer by Brewer: ");
                Long beerBrewerIdDelete = scanner.nextLong();
                scanner.nextLine();
                beerService.getBeersByBrewery(beerBrewerIdDelete);
                break;
            case 8:
                logger.info("Find beer cheaper than X: ");
                double priceCheaper = scanner.nextDouble();
                scanner.nextLine();
                beerService.getBeersCheaperThan(priceCheaper);
                break;
            case 9:
                logger.info("Back");
                MainApp.main(null);
                break;
        }
    }
}




