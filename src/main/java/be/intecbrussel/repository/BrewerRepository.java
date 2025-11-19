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
        em.close();
    }
    public Optional<Brewer> findById(Long id){
        EntityManager em = JpaConfig.getEntityManager();
        Brewer brewer = em.find(Brewer.class, id);
        em.close();
        return Optional.ofNullable(brewer);
    }

    public List<Brewer> findAll(){
        EntityManager em = JpaConfig.getEntityManager();
        List<Brewer> brewer = em.createQuery( "select b from Brewer b").getResultList();
        em.close();
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
        Brewer brewer = em.find(Brewer.class, id);

        if(brewer != null){
            em.getTransaction().begin();
            em.merge(brewer);
            em.remove(brewer);
            em.getTransaction().commit();
        }
        em.close();
    }
    public List<Brewer> findBrewersByName(String name){
        EntityManager em = JpaConfig.getEntityManager();
        List<Brewer> brewerName = em.createQuery("select b from Brewer b where b.name = :name")
                .setParameter("name", name)
                .getResultList();
        em.close();
        return brewerName;

    }

    public List<Object[]> findAllBrewersWithBeerCount(){
        EntityManager em = JpaConfig.getEntityManager();
        return em.createQuery("select b, count(beer) from Brewer b left join b.beers beer group by b",Object[].class)
                .getResultList();
    }

}
