package com.walidhelaoui.gomycode.Entity;

/**
 * Created by walid on 24/12/2017.
 */

public class Data {


    private int id;
    private String name;
    private String place;
    private String city;
    private float price;
    private String type;

    public Data() {
    }

    public Data(int id, String name, String place, String city, float price, String type) {
        this.id = id;
        this.name = name;
        this.place = place;
        this.city = city;
        this.price = price;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}


