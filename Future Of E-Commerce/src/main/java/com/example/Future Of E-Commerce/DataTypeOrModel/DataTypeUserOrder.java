package com.example.e_commercefuture.DataTypeOrModel;

public class DataTypeUserOrder {


    private String  email, date, time, totalAmount,status , productid , username , image , countryname , locality , address , pay;

    public DataTypeUserOrder() {
    }

    DataTypeUserOrder(String email, String date, String time, String totalAmount, String status, String productid , String username , String image ,String countryname , String locality ,String address ,String pay) {
        this.email = email;
        this.date = date;
        this.time = time;
        this.totalAmount = totalAmount;
        this.status = status;
        this.productid = productid;
        this.username = username;
        this.image=image;
        this.countryname=countryname;
        this.locality=locality;
        this.address=address;
        this.pay=pay;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }
}
