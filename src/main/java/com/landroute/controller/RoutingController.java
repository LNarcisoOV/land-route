package com.landroute.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/routing")
public class RoutingController {
    
    @GetMapping("/{origin}/{destination}")
    public void routing(@PathVariable("origin") String origin, @PathVariable("destination") String destination) {
       
    }

}
