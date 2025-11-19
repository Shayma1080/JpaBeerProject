package be.intecbrussel.service;

import be.intecbrussel.config.JpaConfig;
import be.intecbrussel.model.Beer;
import be.intecbrussel.model.Brewer;
import be.intecbrussel.model.Category;
import be.intecbrussel.repository.BrewerRepository;
import jakarta.persistence.EntityManager;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static be.intecbrussel.MainApp.logger;

public class BrewerService {
    public static final BrewerRepository brewerRepository = new BrewerRepository();
    public final EntityManager em = JpaConfig.getEntityManager();

    public void addBrewer(Brewer brewer) throws SQLException {
        try {
            brewerRepository.createBrewer(brewer);
        }catch(Exception e) {
            logger.warning("Brewer creation failed");
        }
    }

    public Optional<Brewer> getByIdBrewer(Long id) throws SQLException {
        try {
           return brewerRepository.findById(id);
        }catch(Exception e) {
            logger.warning("Brewer getting id not found");
            return Optional.empty();
        }
    }


    public List<Brewer> getAllBrewers() throws SQLException {
        try {
            return brewerRepository.findAll();
        }catch(Exception e) {
            logger.warning("Brewer getting all brewers not found");
            return null;
        }

    }

    public void updateBrewer(Brewer brewer) throws SQLException {
        try {
            brewerRepository.update(brewer);
        }catch(Exception e) {
            logger.info("Brewer update failed");
        }
    }

    public void removeBrewer(Long id) throws SQLException {
        try {
            brewerRepository.delete(id);
        }catch(Exception e) {
            logger.warning("Brewer with id " + id + " not found");
        }
    }

    public void getBrewerByName(String name) throws SQLException {
        try {
            brewerRepository.findBrewersByName(name);
        }catch(Exception e) {
            logger.warning("Brewer name not found: " + name);
        }
    }

    public List<Object[]> getAllBrewersWithBeerCount() throws SQLException {
        try {
            return brewerRepository.findAllBrewersWithBeerCount();
        }catch(Exception e) {
            logger.warning("Brewer count not found");
            return null;
        }
    }

}
