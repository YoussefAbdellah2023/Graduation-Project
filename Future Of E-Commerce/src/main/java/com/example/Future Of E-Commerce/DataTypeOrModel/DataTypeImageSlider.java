package com.example.e_commercefuture.DataTypeOrModel;

public class DataTypeImageSlider {

    private String image;
    private String price;
    private String id;

    public DataTypeImageSlider(String image, String price, String id) {
        this.image = image;
        this.price = price;
        this.id = id;
    }

    public DataTypeImageSlider() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
