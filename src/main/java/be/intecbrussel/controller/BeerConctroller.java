package be.intecbrussel.controller;

import be.intecbrussel.MainApp;
import be.intecbrussel.config.JpaConfig;
import be.intecbrussel.model.Beer;
import be.intecbrussel.service.BeerService;
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
        beerService.getByIdBeer(findBeerId);
    }

    public static void updateConctroller() throws SQLException {
        logger.info("For beer id: ");
        Long beerid = scanner.nextLong();
        scanner.nextLine();
        logger.info("Update beer price: ");
        double newPrice = scanner.nextDouble();
        scanner.nextLine();
        Optional<Beer> bieropt = beerService.getByIdBeer(beerid);
        beer.setPrice(newPrice);
        beerService.updatebeer(beer);
    }
}
