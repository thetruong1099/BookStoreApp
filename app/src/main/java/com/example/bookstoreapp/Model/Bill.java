package com.example.bookstoreapp.Model;

import java.util.ArrayList;

public class Bill {
    private String fullName;
    private String address;
    private String phone;
    private String tongTien;
    private String trangThai;
    private ArrayList<ShoppingCard> sachDaMua;

    public Bill() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTongTien() {
        return tongTien;
    }

    public void setTongTien(String tongTien) {
        this.tongTien = tongTien;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public ArrayList<ShoppingCard> getSachDaMua() {
        return sachDaMua;
    }

    public void setSachDaMua(ArrayList<ShoppingCard> sachDaMua) {
        this.sachDaMua = sachDaMua;
    }
}
