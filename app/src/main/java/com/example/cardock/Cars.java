package com.example.cardock;

public class Cars {
    private String model,brand,price,seat,year;

    public Cars(){
    }

    //getters
    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getPrice() {
        return price;
    }

    public String getSeat() {
        return seat;
    }

    public String getYear() {
        return year;
    }
//setters
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public void setYear(String year) {
        this.year = year;
    }
}

