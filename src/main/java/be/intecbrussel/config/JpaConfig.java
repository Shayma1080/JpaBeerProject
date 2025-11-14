package be.intecbrussel.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaConfig {
    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("belgium_beerdb");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void close(){
        emf.close();
    }
}
