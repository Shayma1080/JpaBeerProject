package be.intecbrussel.service;

import be.intecbrussel.config.JpaConfig;
import be.intecbrussel.model.Beer;
import be.intecbrussel.model.Brewer;
import be.intecbrussel.repository.BrewerRepository;
import jakarta.persistence.EntityManager;

public class BrewerService {
    public static final BrewerRepository brewerRepository = new BrewerRepository();

    public void addBrewer(Brewer brewer) {
        EntityManager em = JpaConfig.getEntityManager();
        brewerRepository.createBrewer(brewer);
        em.close();
    }

    public void getByIdBrewer(Long id) {
        EntityManager em = JpaConfig.getEntityManager();
        brewerRepository.findById(id);
        em.close();
    }


    public void getAllBrewers() {
        EntityManager em = JpaConfig.getEntityManager();
        brewerRepository.findAll();
        em.close();
    }

    public void updateBrewer(Brewer brewer) {
        EntityManager em = JpaConfig.getEntityManager();
        brewerRepository.update(brewer);
    }

    public void removeBrewer(Long id) {
        EntityManager em = JpaConfig.getEntityManager();
        brewerRepository.delete(id);
        em.close();
    }

    public void getBrewerByName(String name) {
        EntityManager em = JpaConfig.getEntityManager();
        brewerRepository.findBrewersByName(name);
        em.close();
    }

    public void getAllBrewersWithBeerCount() {
        EntityManager em = JpaConfig.getEntityManager();
        brewerRepository.findAllBrewersWithBeerCount();
        em.close();
    }

}
