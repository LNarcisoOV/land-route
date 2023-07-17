package com.landroute.model;

import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {

    String cca3;
    List<String> borders;

    public String getCca3() {
        return cca3;
    }

    public List<String> getBorders() {
        return borders;
    }

    public void setCca3(String cca3) {
        this.cca3 = cca3;
    }

    public void setBorders(List<String> borders) {
        this.borders = borders;
    }

    @Override
    public int hashCode() {
        return Objects.hash(borders, cca3);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Country other = (Country) obj;
        return Objects.equals(borders, other.borders) && Objects.equals(cca3, other.cca3);
    }

    @Override
    public String toString() {
        return "Country [cca3=" + cca3 + ", borders=" + borders + "]";
    }
}
