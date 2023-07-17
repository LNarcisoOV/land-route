package com.landroute.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.landroute.model.Country;

@Configuration
public class RoutingConfiguration {

    private static final String COUTRIES_JSON_FILE_PATH =
            "/raw.githubusercontent.com_mledoze_countries_master_countries.json";

    @Bean
    List<Country> loadContries() {
        List<Country> countries = new ArrayList<Country>();
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Country>> typeReference = new TypeReference<List<Country>>() {};
        InputStream inputStream = TypeReference.class.getResourceAsStream(COUTRIES_JSON_FILE_PATH);
        try {
            countries = mapper.readValue(inputStream, typeReference);
        } catch (IOException e) {
            System.out.println("Unable to load countries: " + e.getMessage());
        }
        return countries;
    }
}
