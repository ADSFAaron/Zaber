package com.example.zaber;

import java.util.ArrayList;

public class CustomerInformation {

    private String customerEmail;
    private String customerPassword;
    private ArrayList<String> merchandise = new ArrayList<>(); //add items
    private String orderStatus; //false or store
    private String money;
    private String number;

    public CustomerInformation() {
        orderStatus = "false";
        money = "0";
        number = "0";
    }

    // created getter and setter methods
    // for all our variables.
    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public String getorderStatus() {
        return orderStatus;
    }

    public void setorderStatus(String val) {
        this.orderStatus = val;
    }

    public void addSingleItem(String item) {
        this.merchandise.add(item);
    }

    public void setMerchandise(ArrayList<String> all) {
        this.merchandise = all;
    }

    public ArrayList<String> getMerchandise() {
        return this.merchandise;
    }

    public Integer getMoney() {
        return Integer.parseInt(money);
    }

    public void addMoney(Integer val) {
        Integer total = Integer.parseInt(this.money) + val;
        this.money = Integer.toString(total);
    }

    public void setMoney(Integer val) {
        this.money = Integer.toString(val);
    }

    public Integer getNumber() {
        return Integer.parseInt(number);
    }

    public void setNumber(Integer val) {
        this.number = Integer.toString(val);
    }
}
