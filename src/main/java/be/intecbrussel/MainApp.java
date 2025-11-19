package be.intecbrussel;

import be.intecbrussel.config.JpaConfig;
import be.intecbrussel.controller.BeerConctroller;
import be.intecbrussel.controller.BrewerController;
import be.intecbrussel.controller.CategoryController;
import be.intecbrussel.model.Beer;
import be.intecbrussel.model.Brewer;
import be.intecbrussel.model.Category;
import be.intecbrussel.service.BeerService;
import be.intecbrussel.service.BrewerService;
import be.intecbrussel.service.CategoryService;
import jakarta.persistence.EntityManager;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.apache.log4j.xml.DOMConfigurator;
import org.apache.log4j.Logger;

public class MainApp {

    public static final Logger logger = Logger.getLogger(MainApp.class.getName());
    public static Scanner scanner = new Scanner(System.in);
    public final static EntityManager em = JpaConfig.getEntityManager();

    public static void main(String[] args) throws SQLException {
        DOMConfigurator.configure(MainApp.class.getClassLoader().getResource("bluelogger.xml"));
        logger.info("Logger initialized in blue!");

        BeerService beerService = new BeerService();
        BrewerService brewerService = new BrewerService();
        CategoryService categoryService = new CategoryService();

        //Hoofdmenu
        while (true) {
            logger.info("\n ===  Hoofdmenu  === ");
            logger.info("1. Manage Brewers");
            logger.info("2. Manage Categories");
            logger.info("3. Manage Beers");
            logger.info("4. Exit");
            logger.info("\nEnter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: HandleBrewer(scanner, brewerService);
                    break;
                case 2: HandleCategory(scanner,categoryService);
                    break;
                case 3: HandleBeer(scanner, beerService);
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
        logger.info("9. Export beers to Json");
        logger.info("10. Back");
        logger.info("\nEnter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1: BeerConctroller.addConctroller();
                break;
            case 2: BeerConctroller.viewConctroller();
                break;
            case 3: BeerConctroller.findConctroller();
                break;
            case 4: BeerConctroller.updateConctroller();
                break;
            case 5: BeerConctroller.deleteConctroller();
                break;
            case 6:BeerConctroller.beersByCategoryController();
                break;
            case 7: BeerConctroller.beersByBreweryController();
            case 8: BeerConctroller.beerCheaperThanConctroller();
                break;
            case 9 : BeerConctroller.exportBeersController();
                break;
            case 10:
                logger.info("Back");
                MainApp.main(null);
                break;
        }
    }

    //Brewer submenu
    public static void HandleBrewer(Scanner scanner, BrewerService brewerService) throws SQLException {
        Brewer brewer = new Brewer();

        logger.info("\n ===  Brewer Submenu  === ");
        logger.info("1. Add Brewer");
        logger.info("2. View All Brwers");
        logger.info("3. Find Brewer by ID");
        logger.info("4. Update Brewer");
        logger.info("5. Delete Brewer");
        logger.info("6. Find Brewer by name");
        logger.info("7. Find Brewer with Beer count");
        logger.info("8. Back");
        logger.info("\nEnter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1: BrewerController.addConctroller();
                break;
            case 2: BrewerController.viewConctroller();
                break;
            case 3: BrewerController.findConctroller();
                break;
            case 4: BrewerController.updateConctroller();
                break;
            case 5: BrewerController.deleteConctroller();
                break;
           case 6: BrewerController.brewerByNameController();
                break;
            case 7: BrewerController.allBrewersWithBeerCountController();
                break;
            case 8:
                logger.info("Back");
                MainApp.main(null);
                break;
        }
    }

    //Category submenu
    public static void HandleCategory(Scanner scanner, CategoryService categoryService) throws SQLException {
        Category category = new Category();

        logger.info("\n ===  Category Submenu  === ");
        logger.info("1. Add Category");
        logger.info("2. View All Category");
        logger.info("3. Find Category by ID");
        logger.info("4. Update Category");
        logger.info("5. Delete Category");
        logger.info("6. Find Category by name");
        logger.info("7. Back");
        logger.info("\nEnter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1: CategoryController.addConctroller();
                break;
            case 2: CategoryController.viewConctroller();
                break;
            case 3: CategoryController.findConctroller();
                break;
            case 4: CategoryController.updateConctroller();
                break;
            case 5: CategoryController.deleteConctroller();
                break;
            case 6: CategoryController.categoryByNameController();
                break;
            case 7:
                logger.info("Back");
                MainApp.main(null);
                break;
        }
    }
}




