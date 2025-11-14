package be.intecbrussel.service;

import be.intecbrussel.config.JpaConfig;
import be.intecbrussel.model.Brewer;
import be.intecbrussel.model.Category;
import be.intecbrussel.repository.CategoryRepository;
import jakarta.persistence.EntityManager;

public class CategoryService {

    public static final CategoryRepository categoryRepository = new CategoryRepository();

    public void addCategory(Category category) {
        EntityManager em = JpaConfig.getEntityManager();
        categoryRepository.createCategory(category);
        em.close();
    }

    public void getByIdCategory(Long id) {
        EntityManager em = JpaConfig.getEntityManager();
        categoryRepository.findById(id);
        em.close();
    }


    public void getAllCategories() {
        EntityManager em = JpaConfig.getEntityManager();
        categoryRepository.findAll();
        em.close();
    }

    public void updateCategory(Category category) {
        EntityManager em = JpaConfig.getEntityManager();
        categoryRepository.update(category);
    }

    public void removeCategory(Long id) {
        EntityManager em = JpaConfig.getEntityManager();
        categoryRepository.delete(id);
        em.close();
    }

    public void getCategoryByName(String name) {
        EntityManager em = JpaConfig.getEntityManager();
        categoryRepository.findCategoryByName(name);
        em.close();
    }

}
