package be.intecbrussel.service;

import be.intecbrussel.config.JpaConfig;
import be.intecbrussel.model.Brewer;
import be.intecbrussel.model.Category;
import be.intecbrussel.repository.CategoryRepository;
import jakarta.persistence.EntityManager;

import java.sql.SQLException;

public class CategoryService {

    public static final CategoryRepository categoryRepository = new CategoryRepository();
    public final EntityManager em = JpaConfig.getEntityManager();

    public void addCategory(Category category) throws SQLException {
        try {
            categoryRepository.createCategory(category);
            em.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void getByIdCategory(Long id) throws SQLException {
        try {
            categoryRepository.findById(id);
            em.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }


    public void getAllCategories() throws SQLException {
        try {
            categoryRepository.findAll();
            em.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCategory(Category category) throws SQLException {
        try {
            categoryRepository.update(category);
            em.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void removeCategory(Long id) throws SQLException {
        try {
            categoryRepository.delete(id);
            em.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void getCategoryByName(String name) throws SQLException {
        try {
            categoryRepository.findCategoryByName(name);
            em.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

}
