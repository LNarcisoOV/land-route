package com.landroute.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.landroute.model.Country;
import com.landroute.model.dto.LandRouteDTO;
import com.landroute.service.RoutingService;

@Service
public class RoutingServiceImpl implements RoutingService {

    @Autowired
    private List<Country> countries;

    @Override
    public LandRouteDTO loadRoute(String origin, String destination) {
        Country originCountry =
                countries.stream().filter(c -> c.getCca3().equals(origin)).findFirst().get();

        Country destinationCountry =
                countries.stream().filter(c -> c.getCca3().equals(destination)).findFirst().get();

        LandRouteDTO landRouteDTO = new LandRouteDTO();
        
        List<String> borders = originCountry.getBorders()
        .stream()
        .filter(b -> destinationCountry.getBorders().contains(b))
        .collect(Collectors.toList());
        
        if(originCountry.equals(destinationCountry) || CollectionUtils.isEmpty(borders)) {
            throw new IllegalArgumentException("Empty border list.");
        }

        landRouteDTO.getRoute().add(originCountry.getCca3());
        landRouteDTO.getRoute().addAll(borders);
        landRouteDTO.getRoute().add(destinationCountry.getCca3());
        
        return landRouteDTO;
    }

}
