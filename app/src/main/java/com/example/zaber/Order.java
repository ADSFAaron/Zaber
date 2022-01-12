package com.example.zaber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Order {

    int no;                 // 訂單編號
    String user;            // 確認是哪個使用者訂的
    ArrayList<String> items;         // 餐點名稱
    ArrayList<String> additional;    // 餐點的附註
    int total;              // 總共花費
    Date placeOrderTime;    // 下訂餐點時間
    OrderStatus status;     // 目前訂單狀態

    public Order(int no, String user, ArrayList<String> items, ArrayList<String> additional, int total, Date placeOrderTime, OrderStatus status) {
        this.no = no;
        this.user = user;
        this.items = items;
        this.additional = additional;
        this.total = total;
        this.placeOrderTime = placeOrderTime;
        this.status = status;
    }
    public Order(){}

    public int getNo() {
        return no;
    }
    public String getUser() {
        return user;
    }

    public ArrayList<String> getAdditional() {
        //List<String> additionalList = new ArrayList<String>(Arrays.asList(additional)); //new ArrayList is only needed if you absolutely need an ArrayList
        return additional;
    }

    public ArrayList<String> getItems() {
        //List<String> itemList = new ArrayList<String>(Arrays.asList(items)); //new ArrayList is only needed if you absolutely need an ArrayList
        return items;
    }

    public int getTotal() {
        return total;
    }

    public Date getPlaceOrderTime() {
        return placeOrderTime;
    }

    public OrderStatus getStatus() {
        return status;
    }
}
