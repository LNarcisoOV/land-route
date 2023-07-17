package com.landroute.service;

import com.landroute.model.dto.LandRouteDTO;

public interface RoutingService {

    LandRouteDTO loadRoute(String origin, String destination);

}
