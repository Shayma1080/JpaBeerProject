package be.intecbrussel.repository;

import be.intecbrussel.config.JpaConfig;
import be.intecbrussel.model.Brewer;
import be.intecbrussel.model.Category;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class CategoryRepository {

    public void createCategory(Category category) {
        EntityManager em = JpaConfig.getEntityManager();
        em.getTransaction().begin();
        em.persist(category);
        em.getTransaction().commit();
    }
    public Optional<Category> findById(Long id){
        EntityManager em = JpaConfig.getEntityManager();
        Category category = em.find(Category.class, id);
        em.close();
        return Optional.ofNullable(category);
    }

    public List<Category> findAll(){
        EntityManager em = JpaConfig.getEntityManager();
        List<Category> category = em.createQuery( "select c from Category c").getResultList();
        em.close();
        return category;
    }
    public void update(Category category) {
        EntityManager em = JpaConfig.getEntityManager();
        em.getTransaction().begin();
        category = em.merge(category);
        em.getTransaction().commit();
    }
    public void delete(Long id) {
        EntityManager em = JpaConfig.getEntityManager();
        em.getTransaction().begin();
        Category category = em.find(Category.class, id);
        em.merge(category);
        em.remove(category);
        em.getTransaction().commit();
        em.close();
    }
    public void findCategoryByName(String name){
        EntityManager em = JpaConfig.getEntityManager();
        Category category = em.createQuery("Select c from Category c where c.name=:name", Category.class)
                .setParameter("name", name)
                .getSingleResult();
        em.close();

    }

}
