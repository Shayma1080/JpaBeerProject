package be.intecbrussel.repository;

import be.intecbrussel.config.JpaConfig;
import be.intecbrussel.model.Brewer;
import be.intecbrussel.model.Category;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CategoryRepository {

    public void createCategory(Category category) {
        EntityManager em = JpaConfig.getEntityManager();
        em.getTransaction().begin();
        em.persist(category);
        em.getTransaction().commit();
    }
    public void findById(Long id){
        EntityManager em = JpaConfig.getEntityManager();
        em.getTransaction().begin();
        Category category = em.find(Category.class, id);
        em.getTransaction().commit();
    }

    public List<Category> findAll(){
        EntityManager em = JpaConfig.getEntityManager();
        em.getTransaction().begin();
        List<Category> category = em.createQuery( "select c from Category c").getResultList();
        em.getTransaction().commit();
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
        category = em.merge(category);
        em.getTransaction().commit();
    }
    public void findCategoryByName(String name){
        EntityManager em = JpaConfig.getEntityManager();
        Category category = em.find(Category.class,name);
    }

}
