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

import static be.intecbrussel.service.BrewerService.brewerRepository;

public class BeerService {

    private static final BeerRepository beerRepository = new BeerRepository();
    public final EntityManager em = JpaConfig.getEntityManager();

    public void addBeer(Beer beer) throws SQLException {
        try{
            beerRepository.createBeer(beer);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public Optional<Beer> getByIdBeer(Long id) throws SQLException {
        try{
           return beerRepository.findById(id);

        }catch(Exception e){
            e.printStackTrace();
        }
        return Optional.empty();
    }


    public List<Beer> getAllBeers() throws SQLException {
        try{
            beerRepository.findAll();
        }catch(Exception e){
            e.printStackTrace();
        }
        return beerRepository.findAll();
    }


    public void updatebeer(Beer beer) throws SQLException {
        try {
            beerRepository.update(beer);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void removeBeer(Long id) throws SQLException {
        try {
            beerRepository.delete(id);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void getBeersByCategory(Long categoryId) throws SQLException {
        try {
            beerRepository.findBeersByCategory(categoryId);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void getBeersByBrewery(Long breweryId) throws SQLException {
        try {
            Brewer brewery = new Brewer();
            Brewer brewer = em.find(Brewer.class, breweryId);
            if (!brewer.getName().equals(brewery.getName())) {
                beerRepository.findBeersByBrewer(breweryId);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void getBeersCheaperThan(double maxPrice) throws SQLException {
        try {
            if (maxPrice > 0) {
                beerRepository.findBeersCheaperThan(maxPrice);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
