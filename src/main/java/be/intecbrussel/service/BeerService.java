package be.intecbrussel.service;

import be.intecbrussel.config.JpaConfig;
import be.intecbrussel.model.Beer;
import be.intecbrussel.model.Brewer;
import be.intecbrussel.repository.BeerRepository;
import be.intecbrussel.repository.BrewerRepository;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static be.intecbrussel.service.BrewerService.brewerRepository;

public class BeerService {

    private static final BeerRepository beerRepository = new BeerRepository();

    public void addBeer(Beer beer) {
        EntityManager em = JpaConfig.getEntityManager();
        beerRepository.createBeer(beer);
        em.close();
    }

    public Optional<Beer> getByIdBeer(Long id) {
        EntityManager em = JpaConfig.getEntityManager();
        Beer beer = em.find(Beer.class,id);
        if(id != null){
            beerRepository.findById(id);
        }
        em.close();
        return Optional.ofNullable(beer);
    }


    public void getAllBeers() {
        EntityManager em = JpaConfig.getEntityManager();
        beerRepository.findAll();
        em.close();
    }

    public void updatebeer(Beer beer) {
        EntityManager em = JpaConfig.getEntityManager();
        beerRepository.update(beer);
    }

    public void removeBeer(Long id) {
        EntityManager em = JpaConfig.getEntityManager();
        beerRepository.delete(id);
        em.close();
    }

    public void getBeersByCategory(Long categoryId) {
        EntityManager em = JpaConfig.getEntityManager();
        beerRepository.findBeersByCategory(categoryId);
        em.close();
    }

    public void getBeersByBrewery(Long breweryId) {
        EntityManager em = JpaConfig.getEntityManager();
        BrewerRepository brewer = brewerRepository.findBrewersByName(breweryId);
        if(!(breweryId == brewer)){
            beerRepository.findBeersByBrewer(breweryId);
        }
        em.close();
    }

    public void getBeersCheaperThan(Double maxPrice) {
        EntityManager em = JpaConfig.getEntityManager();
        if(maxPrice < 0){
            beerRepository.findBeersCheaperThan(maxPrice);
        }
        em.close();
    }
}
