package be.intecbrussel.repository;

import be.intecbrussel.config.JpaConfig;
import be.intecbrussel.model.Beer;
import be.intecbrussel.model.Brewer;
import be.intecbrussel.model.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;

public class BeerRepository {

    public void createBeer(Beer beer) {
        EntityManager em = JpaConfig.getEntityManager();
        em.getTransaction().begin();
        em.persist(beer);
        em.getTransaction().commit();
    }
    public void findById(Long id){
        EntityManager em = JpaConfig.getEntityManager();
        em.getTransaction().begin();
        Beer beer = em.find(Beer.class, id);
        em.getTransaction().commit();
    }

    public List<Beer> findAll(){
        EntityManager em = JpaConfig.getEntityManager();
        em.getTransaction().begin();
        List<Beer> beer = em.createQuery( "select b from Beer b").getResultList();
        em.getTransaction().commit();
        return beer;
    }
    public void update(Beer beer) {
        EntityManager em = JpaConfig.getEntityManager();
        em.getTransaction().begin();
        beer = em.merge(beer);
        em.getTransaction().commit();
    }
    public void delete(Long id) {
        EntityManager em = JpaConfig.getEntityManager();
        em.getTransaction().begin();
        Beer beer = em.find(Beer.class, id);
        beer = em.merge(beer);
        em.getTransaction().commit();
    }
    public List<Beer> findBeersByCategory(long categoryId){
        EntityManager em = JpaConfig.getEntityManager();
        Category category= em.find(Category.class,categoryId);
        return category.getBeers();
    }

    public List<Beer> findBeersByBrewer(long brewerId){
        EntityManager em = JpaConfig.getEntityManager();
        Brewer brewer = em.find(Brewer.class,brewerId);
        return brewer.getBeers();
    }

    public List<Beer> findBeersCheaperThan(double maxPrice){
        EntityManager em = JpaConfig.getEntityManager();
        return em.createQuery("Select b from beer b where b.price <= :maxPrice", Beer.class)
                .setParameter("maxPrice", maxPrice)
                .getResultList();
    }


}
