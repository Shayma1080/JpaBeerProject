package be.intecbrussel.service;

import be.intecbrussel.config.JpaConfig;
import be.intecbrussel.model.Brewer;
import be.intecbrussel.model.Category;
import be.intecbrussel.repository.CategoryRepository;
import jakarta.persistence.EntityManager;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static be.intecbrussel.MainApp.logger;

public class CategoryService {

    public static final CategoryRepository categoryRepository = new CategoryRepository();
    public final EntityManager em = JpaConfig.getEntityManager();

    public void addCategory(Category category) throws SQLException {
        try {
            categoryRepository.createCategory(category);
        }catch(Exception e) {
            logger.warn("Category already exists");
        }
    }

    public Optional<Category> getByIdCategory(Long id) throws SQLException {
        try {
            return categoryRepository.findById(id);
        }catch(Exception e) {
            logger.warn("Category ID not found");
            return Optional.empty();
        }
    }


    public List<Category> getAllCategories() throws SQLException {
        try {
            return categoryRepository.findAll();
        }catch(Exception e) {
            logger.warn("No Category found");
            return null;
        }
    }

    public void updateCategory(Category category) throws SQLException {
        try {
            categoryRepository.update(category);
        }catch(Exception e) {
            logger.warn("Category update failed");
        }
    }

    public void removeCategory(Long id) throws SQLException {
        try {
            categoryRepository.delete(id);
        }catch(Exception e) {
            logger.warn("Category remove failed");
        }
    }

    public void getCategoryByName(String name) throws SQLException {
        try {
            categoryRepository.findCategoryByName(name);
        }catch(Exception e) {
            logger.warn("Category name not found");
        }
    }

}
