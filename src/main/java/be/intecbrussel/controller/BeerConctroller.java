package be.intecbrussel.controller;

import be.intecbrussel.MainApp;
import be.intecbrussel.config.JpaConfig;
import be.intecbrussel.model.Beer;
import be.intecbrussel.service.BeerService;
import be.intecbrussel.service.JsonService;
import jakarta.persistence.EntityManager;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Logger;

public class BeerConctroller {
    public static final Logger logger = Logger.getLogger(MainApp.class.getName());
    public static Scanner scanner = new Scanner(System.in);
    public final static EntityManager em = JpaConfig.getEntityManager();
    public static Beer beer = new Beer();
    public static BeerService beerService = new BeerService();

    public static void addConctroller() throws SQLException {
        logger.info("Enter Beer ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        logger.info("Enter beer name: ");
        String name = scanner.nextLine();
        logger.info("Enter beer alcoholpercentage: ");
        int alcoholPercentage = scanner.nextInt();
        scanner.nextLine();
        logger.info("Enter beer price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        beer = new Beer(name, alcoholPercentage, price);
        beerService.addBeer(beer);
    }

    public static void viewConctroller() throws SQLException {
        logger.info("All beers: ");
        List<Beer> allBiers = beerService.getAllBeers();
        logger.info(allBiers.toString());
    }

    public static void findConctroller() throws SQLException {
        logger.info("Find beer by ID: ");
        Long findBeerId = scanner.nextLong();
        scanner.nextLine();
        Optional<Beer> beeropt = beerService.getByIdBeer(findBeerId);
        if(beeropt.isPresent()) {
            Beer foundBeer = beeropt.get();
            logger.info("Found beer by ID: " + foundBeer.getName());
        }else{
            logger.info("No beer found with ID: " + findBeerId);
        }
    }

    public static void updateConctroller() throws SQLException {
        logger.info("For beer id: ");
        Long beerid = scanner.nextLong();
        scanner.nextLine();
        logger.info("Update beer price: ");
        double newPrice = scanner.nextDouble();
        scanner.nextLine();

        Optional<Beer> bieropt = beerService.getByIdBeer(beerid);

        if(bieropt.isPresent()) { // is id present in java code --> ja
            Beer foundBeer = bieropt.get(); // maak object van
            foundBeer.setPrice(newPrice); // verander prijs van object
            beerService.updatebeer(foundBeer); // update object meet nieuw prijs
            logger.info("For Beer ID: " + beerid + " is now price updated to: " + newPrice);
        }

    }

    public static void deleteConctroller() throws SQLException {
        logger.info("Delete beer by ID: ");
        Long beerIdDelete = scanner.nextLong();
        scanner.nextLine();
        beerService.removeBeer(beerIdDelete);
        logger.info("Beer ID: " + beerIdDelete + " is now deleted" );
    }

    public static void beersByCategoryController() throws SQLException {
        logger.info("Find beer by CategoryID: ");
        Long beerCategoryIdDelete = scanner.nextLong();
        scanner.nextLine();
        beerService.getBeersByCategory(beerCategoryIdDelete);
    }

    public static void beersByBreweryController() throws SQLException {
        logger.info("Find beer by BrewerID: ");
        Long beerBrewerId = scanner.nextLong();
        scanner.nextLine();
        beerService.getBeersByBrewery(beerBrewerId);
    }

    public static void beerCheaperThanConctroller() throws SQLException {
        logger.info("Find beer cheaper than: ");
        double priceCheaper = scanner.nextDouble();
        scanner.nextLine();
        beerService.getBeersCheaperThan(priceCheaper);
    }

    public static void exportBeersController() throws SQLException {
        JsonService jsonService = new JsonService();
        BeerService beerService = new BeerService();
        var beers = beerService.getAllBeers();
        jsonService.exportBeers(beers,"beers.json");
    }
}
