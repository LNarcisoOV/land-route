package com.landroute.model.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LandRouteDTO {

    List<String> route = new ArrayList<String>();

    public List<String> getRoute() {
        return route;
    }

    public void setRoute(List<String> route) {
        this.route = route;
    }

    @Override
    public int hashCode() {
        return Objects.hash(route);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LandRouteDTO other = (LandRouteDTO) obj;
        return Objects.equals(route, other.route);
    }

    @Override
    public String toString() {
        return "LandRouteDTO [route=" + route + "]";
    }
}
