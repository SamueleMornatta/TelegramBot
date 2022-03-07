/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telegrambot;

/**
 *
 * @author samue
 */
public class Place {
    String building,amenity,road,town,county,state,poscode,country,country_code,house_number,village;
    double lat, lon;

    public Place() {
    }

    public Place(String building, String amenity, String road, String town, String county, String state, String poscode, String country, String country_code, String house_number, String village, int lat, int lon) {
        this.building = building;
        this.amenity = amenity;
        this.road = road;
        this.town = town;
        this.county = county;
        this.state = state;
        this.poscode = poscode;
        this.country = country;
        this.country_code = country_code;
        this.house_number = house_number;
        this.village = village;
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "building: " + building + "\namenity: " + amenity + "\nroad: " + road + "\ntown: " + town + "\ncounty: " + county + "\nstate: " + state + "\nposcode: " + poscode + "\ncountry: " + country + "\ncountry_code: " + country_code + "\nhouse_number: " + house_number + "\nvillage: " + village;
    }
    

    public String getBuilding() {
        return building;
    }

    public String getAmenity() {
        return amenity;
    }

    public String getRoad() {
        return road;
    }

    public String getTown() {
        return town;
    }

    public String getCounty() {
        return county;
    }

    public String getState() {
        return state;
    }

    public String getPoscode() {
        return poscode;
    }

    public String getCountry() {
        return country;
    }

    public String getCountry_code() {
        return country_code;
    }

    public String getHouse_number() {
        return house_number;
    }

    public String getVillage() {
        return village;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public void setAmenity(String amenity) {
        this.amenity = amenity;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPoscode(String poscode) {
        this.poscode = poscode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public void setHouse_number(String house_number) {
        this.house_number = house_number;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
    
}
