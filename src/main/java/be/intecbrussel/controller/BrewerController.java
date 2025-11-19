package be.intecbrussel.controller;

import be.intecbrussel.MainApp;
import be.intecbrussel.config.JpaConfig;
import be.intecbrussel.model.Beer;
import be.intecbrussel.model.Brewer;
import be.intecbrussel.service.BeerService;
import be.intecbrussel.service.BrewerService;
import jakarta.persistence.EntityManager;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Logger;

public class BrewerController {
    public static final Logger logger = Logger.getLogger(MainApp.class.getName());
    public static Scanner scanner = new Scanner(System.in);
    public final static EntityManager em = JpaConfig.getEntityManager();
    public static Brewer brewer = new Brewer();
    public static BrewerService brewerService = new BrewerService();

    public static void addConctroller() throws SQLException {
        logger.info("Enter Brewer ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        logger.info("Enter brewer name: ");
        String name = scanner.nextLine();
        logger.info("Enter brewer location: ");
        String location = scanner.nextLine();
        brewer = new Brewer(name, location);
        brewerService.addBrewer(brewer);
        logger.info("Brewer added: " + brewer);
    }

    public static void viewConctroller() throws SQLException {
        logger.info("All Brewer: ");
        List<Brewer> allBrewer = brewerService.getAllBrewers();
        logger.info(allBrewer.toString());
    }

    public static void findConctroller() throws SQLException {
        logger.info("Find Brewer by ID: ");
        Long findBrewerId = scanner.nextLong();
        scanner.nextLine();
        Optional<Brewer> breweropt = brewerService.getByIdBrewer(findBrewerId);
        if(breweropt.isPresent()) {
            Brewer foundBeer = breweropt.get();
            logger.info("Found brewer by ID: " + foundBeer.getName());
        }else{
            logger.info("No brewer found with ID: " + findBrewerId);
        }
    }

    public static void updateConctroller() throws SQLException {
        logger.info("For Brewer id: ");
        Long brewerid = scanner.nextLong();
        scanner.nextLine();
        logger.info("Update Brewer location: ");
        String location = scanner.nextLine();

        Optional<Brewer> breweropt = brewerService.getByIdBrewer(brewerid);

        if(breweropt.isPresent()) {
            Brewer foundBrewer = breweropt.get();
            foundBrewer.setLocation(location);
            brewerService.updateBrewer(foundBrewer);
            logger.info("For Brewer ID: " + brewerid + " is now location updated to: " + location);
        }else{
            logger.info("No brewer found with ID: " + brewerid);
        }
    }

    public static void deleteConctroller() throws SQLException {
        logger.info("Delete Brewer by ID: ");
        Long brewerIdDelete = scanner.nextLong();
        scanner.nextLine();
        brewerService.removeBrewer(brewerIdDelete);
        logger.info("Brewer ID: " + brewerIdDelete + " is now deleted" );
    }

    public static void brewerByNameController() throws SQLException {
        logger.info("Find brewer name ");
        String  brewerName = scanner.nextLine();
        brewerService.getBrewerByName(brewerName);
        logger.info("Brewer name found: " + brewerName);
    }

    public static void allBrewersWithBeerCountController() throws SQLException {
        logger.info("All Brewers with Beer Count: ");
         List<Object[]> result = brewerService.getAllBrewersWithBeerCount();
         for(Object[] obj : result){
             Brewer brewer = (Brewer)obj[0];
             Long count = (Long)obj[1];

             logger.info("Brewer: " + brewer.getName() + " ,Beer count: " + count);
         }
    }

}
