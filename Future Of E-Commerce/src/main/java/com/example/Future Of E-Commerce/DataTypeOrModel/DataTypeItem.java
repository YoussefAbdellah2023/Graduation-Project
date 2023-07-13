package com.example.e_commercefuture.DataTypeOrModel;

public class DataTypeItem {
    private String itemName, description, detail,price, image, itemType, itemId, dateSaved;

    public DataTypeItem() {
    }

    public DataTypeItem(String itemName, String description, String detail, String price, String image, String itemType, String itemId, String dateSaved) {
        this.itemName = itemName;
        this.description = description;
        this.detail = detail;
        this.price = price;
        this.image = image;
        this.itemType = itemType;
        this.itemId = itemId;
        this.dateSaved = dateSaved;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getDateSaved() {
        return dateSaved;
    }

    public void setDateSaved(String dateSaved) {
        this.dateSaved = dateSaved;
    }
}
