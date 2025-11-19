package be.intecbrussel.repository;

import be.intecbrussel.config.JpaConfig;
import be.intecbrussel.model.Beer;
import be.intecbrussel.model.Brewer;
import be.intecbrussel.model.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BeerRepository {

    public void createBeer(Beer beer) {
        EntityManager em = JpaConfig.getEntityManager();
        em.getTransaction().begin();
        em.persist(beer);
        em.getTransaction().commit();
        em.close();
    }
    public Optional<Beer> findById(Long id){
        EntityManager em = JpaConfig.getEntityManager();
        if(id >=0){
            Beer beer = em.find(Beer.class, id);
            return Optional.of(beer);
        }
        em.close();
        return Optional.empty();
    }

    public List<Beer> findAll(){
        EntityManager em = JpaConfig.getEntityManager();
        List<Beer> beer = em.createQuery( "select b from Beer b",Beer.class).getResultList();
        em.close();
        return beer;
    }
    public void update(Beer beer) {
        EntityManager em = JpaConfig.getEntityManager();
        em.getTransaction().begin();
        em.merge(beer);
        em.getTransaction().commit();
        em.close();
    }
    public void delete(Long id) {
        EntityManager em = JpaConfig.getEntityManager();
        em.getTransaction().begin();
        Beer beer = em.find(Beer.class, id);
        em.merge(beer);
        em.remove(beer);
        em.getTransaction().commit();
        em.close();
    }
    public List<Beer> findBeersByCategory(long categoryId){
        EntityManager em = JpaConfig.getEntityManager();
        Category category= em.find(Category.class,categoryId);
        List<Beer> beers = new ArrayList<>();
        if(category!=null){
            beers = new ArrayList<>(category.getBeers());
        }
        em.close();
        return beers;

    }

    public List<Beer> findBeersByBrewer(long brewerId){
        EntityManager em = JpaConfig.getEntityManager();
        Brewer brewer = em.find(Brewer.class,brewerId);
        List<Beer> beers = new ArrayList<>();
        if(brewer != null){
            beers = new ArrayList<>(brewer.getBeers());
        }
        em.close();
        return beers;
    }

    public List<Beer> findBeersCheaperThan(double maxPrice){
        EntityManager em = JpaConfig.getEntityManager();
        List<Beer> beers = em.createQuery("Select b from Beer b where b.price < :maxPrice", Beer.class)
                .setParameter("maxPrice", maxPrice)
                .getResultList();
        em.close();
        return beers;
    }


}
