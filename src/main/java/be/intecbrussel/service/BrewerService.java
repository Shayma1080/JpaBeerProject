package be.intecbrussel.service;

import be.intecbrussel.config.JpaConfig;
import be.intecbrussel.model.Beer;
import be.intecbrussel.model.Brewer;
import be.intecbrussel.model.Category;
import be.intecbrussel.repository.BrewerRepository;
import jakarta.persistence.EntityManager;

import java.sql.SQLException;

public class BrewerService {
    public static final BrewerRepository brewerRepository = new BrewerRepository();
    public final EntityManager em = JpaConfig.getEntityManager();

    public void addBrewer(Brewer brewer) throws SQLException {
        try {
            brewerRepository.createBrewer(brewer);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void getByIdBrewer(Long id) throws SQLException {
        try {
            brewerRepository.findById(id);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }


    public void getAllBrewers() throws SQLException {
        try {
            brewerRepository.findAll();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void updateBrewer(Brewer brewer) throws SQLException {
        try {
            brewerRepository.update(brewer);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void removeBrewer(Long id) throws SQLException {
        try {
            Category category = new Category();
            if (id != category.getId()) {
                brewerRepository.delete(id);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void getBrewerByName(String name) throws SQLException {
        try {
            brewerRepository.findBrewersByName(name);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void getAllBrewersWithBeerCount() throws SQLException {
        try {
            brewerRepository.findAllBrewersWithBeerCount();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

}
