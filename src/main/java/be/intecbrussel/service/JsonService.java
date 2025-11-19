package be.intecbrussel.service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import be.intecbrussel.model.Beer;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import static be.intecbrussel.MainApp.logger;

public class JsonService {
    private final ObjectMapper mapper = new ObjectMapper();

    public void exportBeers(List<Beer> beers, String name){
        try {
            mapper.writeValue(new File("beers.json"), beers);
            logger.info("Beers export to: " + name);
        } catch (IOException e) {
            logger.warning("Export faildes: " + e.getMessage());
        }
    }

    public List<Beer> importBeers(String name){
        try{
            return mapper.readValue(new File(name), new TypeReference<List<Beer>>() {});

        }catch(Exception e){
            logger.warning("Import faildes: " + e.getMessage());
            return Collections.emptyList();
        }
    }

}
