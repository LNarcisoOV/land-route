package com.landroute.controller;

import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.landroute.model.dto.LandRouteDTO;
import com.landroute.service.RoutingService;

@RestController
@RequestMapping("/routing")
public class RoutingController {
    
    @Autowired
    private RoutingService routingService;
    
    @GetMapping("/{origin}/{destination}")
    public ResponseEntity<LandRouteDTO> routing(@PathVariable("origin") String origin, @PathVariable("destination") String destination) {
        try {
            final LandRouteDTO landRouteDTO = routingService.loadRoute(origin, destination);
            return new ResponseEntity<>(landRouteDTO, HttpStatus.OK);
        } catch (NoSuchElementException noSuchElementException) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IllegalArgumentException illegalArgumentException) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } 
    }
}
