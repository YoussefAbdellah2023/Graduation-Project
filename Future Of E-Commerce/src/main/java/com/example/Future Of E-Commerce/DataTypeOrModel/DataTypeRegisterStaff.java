package com.example.e_commercefuture.DataTypeOrModel;

public class DataTypeRegisterStaff {

    String email , password;

    public DataTypeRegisterStaff(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public DataTypeRegisterStaff() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
