package com.example.e_commercefuture.DataTypeOrModel;

public class DataTypeListView {

    private String name;
    private String description;
    private String price;
    private  String image;
    private String id;



    public DataTypeListView(String name, String description, String price, String image, String id) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.id = id;
    }

    public DataTypeListView() {
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setId(String id) {
        this.id = id;
    }
}
