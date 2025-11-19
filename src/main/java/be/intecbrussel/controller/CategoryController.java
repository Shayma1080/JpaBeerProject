package be.intecbrussel.controller;

import be.intecbrussel.MainApp;
import be.intecbrussel.config.JpaConfig;
import be.intecbrussel.model.Brewer;
import be.intecbrussel.model.Category;
import be.intecbrussel.service.BrewerService;
import be.intecbrussel.service.CategoryService;
import jakarta.persistence.EntityManager;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Logger;

public class CategoryController {

    public static final Logger logger = Logger.getLogger(MainApp.class.getName());
    public static Scanner scanner = new Scanner(System.in);
    public final static EntityManager em = JpaConfig.getEntityManager();
    public static Category category = new Category();
    public static CategoryService categoryService = new CategoryService();

    public static void addConctroller() throws SQLException {
        logger.info("Enter Category ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        logger.info("Enter Category name: ");
        String name = scanner.nextLine();
        logger.info("Enter Category discription: ");
        String description = scanner.nextLine();
        category = new Category(name, description);
        categoryService.addCategory(category);
        logger.info("Category added: " + category);
    }

    public static void viewConctroller() throws SQLException {
        logger.info("All Category: ");
        List<Category> allCategory = categoryService.getAllCategories();
        logger.info(allCategory.toString());
    }

    public static void findConctroller() throws SQLException {
        logger.info("Find Category by ID: ");
        Long findCategoryId = scanner.nextLong();
        scanner.nextLine();
        Optional<Category> Categoryopt = categoryService.getByIdCategory(findCategoryId);
        if(Categoryopt.isPresent()) {
            Category foundCategory = Categoryopt.get();
            logger.info("Found Category by ID: " + foundCategory.getName());
        }else{
            logger.info("No Category found with ID: " + findCategoryId);
        }
    }

    public static void updateConctroller() throws SQLException {
        logger.info("For Category id: ");
        Long categoryId = scanner.nextLong();
        scanner.nextLine();
        logger.info("Update Category discription: ");
        String discription = scanner.nextLine();

        Optional<Category> categoryopt = categoryService.getByIdCategory(categoryId);

        if(categoryopt.isPresent()) {
            Category foundCategory = categoryopt.get();
            foundCategory.setDescription(discription);
            categoryService.updateCategory(foundCategory);
            logger.info("For Category ID: " + categoryId + " is now description updated to: " + discription);
        }else{
            logger.info("No Category found with ID: " + categoryId);
        }
    }

    public static void deleteConctroller() throws SQLException {
        logger.info("Delete Category by ID: ");
        Long categoryIdDelete = scanner.nextLong();
        scanner.nextLine();
        categoryService.removeCategory(categoryIdDelete);
        logger.info("Category ID: " + categoryIdDelete + " is now deleted" );
    }

    public static void categoryByNameController() throws SQLException {
        logger.info("Find Category name ");
        String  brewerName = scanner.nextLine();
        categoryService.getCategoryByName(brewerName);
        logger.info("Category name found: " + brewerName);
    }
}
