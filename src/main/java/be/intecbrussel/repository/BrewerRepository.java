package be.intecbrussel.repository;

import be.intecbrussel.config.JpaConfig;
import be.intecbrussel.model.Beer;
import be.intecbrussel.model.Brewer;
import be.intecbrussel.model.Category;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class BrewerRepository {

    public void createBrewer(Brewer brewer) {
        EntityManager em = JpaConfig.getEntityManager();
        em.getTransaction().begin();
        em.persist(brewer);
        em.getTransaction().commit();
    }
    public void findById(Long id){
        EntityManager em = JpaConfig.getEntityManager();
        em.getTransaction().begin();
        Brewer brewer = em.find(Brewer.class, id);
        em.getTransaction().commit();
    }

    public List<Brewer> findAll(){
        EntityManager em = JpaConfig.getEntityManager();
        em.getTransaction().begin();
        List<Brewer> brewer = em.createQuery( "select b from Brewer b").getResultList();
        em.getTransaction().commit();
        return brewer;
    }
    public void update(Brewer brewer) {
        EntityManager em = JpaConfig.getEntityManager();
        em.getTransaction().begin();
        brewer = em.merge(brewer);
        em.getTransaction().commit();
    }
    public void delete(Long id) {
        EntityManager em = JpaConfig.getEntityManager();
        em.getTransaction().begin();
        Brewer brewer = em.find(Brewer.class, id);
        brewer = em.merge(brewer);
        em.getTransaction().commit();
    }
    public Optional<Brewer> findBrewersByName(String name){
        EntityManager em = JpaConfig.getEntityManager();
        Brewer brewer = em.find(Brewer.class,name);
        return Optional.ofNullable(brewer);
    }

    public List<Object[]> findAllBrewersWithBeerCount(){
        EntityManager em = JpaConfig.getEntityManager();
        return em.createQuery("select b, count(beer) from Brewer b left join b.beers beer group by b",Object[].class)
                .getResultList();
    }

}
