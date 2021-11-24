package com.jharkhandtourism.app.model;

public class TourModel {

    public String name="";
    public String category="";
    public int image;
    public String price="";

    public TourModel(String name, String category, int image, String price) {
        this.name = name;
        this.category = category;
        this.image = image;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
