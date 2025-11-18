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
            em.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public Optional<Beer> getByIdBeer(Long id) throws SQLException {
        try{
            Beer beer = em.find(Beer.class,id);
            if(beer.getPrice() > 0){
                beerRepository.findById(id);
            }
            em.close();
            return Optional.of(beer);

        }catch(Exception e){
            e.printStackTrace();
        }
        return Optional.empty();
    }


    public void getAllBeers() throws SQLException {
        try{
            beerRepository.findAll();
            em.close();
        }catch(Exception e){}

    }

    public void updatebeer(Beer beer) throws SQLException {
        try {

            beerRepository.update(beer);
            em.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void removeBeer(Long id) throws SQLException {
        try {
            beerRepository.delete(id);
            em.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void getBeersByCategory(Long categoryId) throws SQLException {
        try {
            beerRepository.findBeersByCategory(categoryId);
            em.close();
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
            em.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void getBeersCheaperThan(Double maxPrice) throws SQLException {
        try {
            if (maxPrice > 0) {
                beerRepository.findBeersCheaperThan(maxPrice);
            }
            em.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
