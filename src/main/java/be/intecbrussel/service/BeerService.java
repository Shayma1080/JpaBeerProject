package be.intecbrussel.service;

import be.intecbrussel.config.JpaConfig;
import be.intecbrussel.model.Beer;
import be.intecbrussel.model.Brewer;
import be.intecbrussel.repository.BeerRepository;
import be.intecbrussel.repository.BrewerRepository;
import jakarta.persistence.EntityManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static be.intecbrussel.MainApp.logger;
import static be.intecbrussel.service.BrewerService.brewerRepository;

public class BeerService {

    private static final BeerRepository beerRepository = new BeerRepository();
    public final EntityManager em = JpaConfig.getEntityManager();

    public void addBeer(Beer beer) throws SQLException {
        try{
            beerRepository.createBeer(beer);
        }catch(Exception e){
            logger.warning("Beer creation failed");
        }

    }

    public Optional<Beer> getByIdBeer(Long id) throws SQLException {
        try{
           return beerRepository.findById(id);

        }catch(Exception e){
            logger.warning("Beer getting failed");
            return null;
        }

    }


    public List<Beer> getAllBeers() throws SQLException {
        try{
            return beerRepository.findAll();
        }catch(Exception e){
            logger.warning("Beers getting failed");
        }
        return null;
    }


    public void updatebeer(Beer beer) throws SQLException {
        try {
            beerRepository.update(beer);
        }catch(Exception e){
            logger.warning("Beer update failed");
        }
    }

    public void removeBeer(Long id) throws SQLException {
        try {
            beerRepository.delete(id);
        }catch(Exception e){
            logger.warning("Beer ID not found");
        }
    }

    public void getBeersByCategory(Long categoryId) throws SQLException {
        try {
            List<Beer> beers = beerRepository.findBeersByCategory(categoryId);
            if(beers.isEmpty()){
                logger.info("No beers found with CategoryID: " + categoryId);
            }else{
                for(Beer c : beers){
                    logger.info("Category ID: " + c.getCategory().getId() +
                            ", Category Name: " + c.getCategory().getName() + ", Beer name: " + c.getName());
                }
            }
        }catch(Exception e){
            logger.warning("Beers categories ID not found");
        }
    }

    public void getBeersByBrewery(Long breweryId) throws SQLException {
        try {
            List<Beer> beers = beerRepository.findBeersByBrewer(breweryId);
            if (beers.isEmpty()) {
                logger.info("No beers found with brewery id " + breweryId);
            }else{
                for (Beer b : beers) {
                    logger.info("Brewer ID: " + breweryId + ", Brewer name: " + b.getBrewer().getName() + ", Beer name: " + b.getName());
                }
            }
        }catch(Exception e){
            logger.warning("Beers brewers ID not found");
        }
    }

    public void getBeersCheaperThan(double maxPrice) throws SQLException {
        try {
            if (maxPrice > 0) {
                List<Beer> cheaperBeers = beerRepository.findBeersCheaperThan(maxPrice);
                for(Beer beer : cheaperBeers) {
                    logger.info("Beer cheaper is " + beer.getName() + " , price is " + beer.getPrice());
                }
            }
        }catch(Exception e){
            logger.warning("Beers is not cheaper ");
        }
    }
}
