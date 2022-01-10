package com.example.zaber;

import java.util.Date;

public class Order {

    int no;                 // 訂單編號
    String user;            // 確認是哪個使用者訂的
    String[] items;         // 餐點名稱
    String[] additional;    // 餐點的附註
    int total;              // 總共花費
    Date placeOrderTime;    // 下訂餐點時間
    OrderStatus status;     // 目前訂單狀態

    public Order(int no, String user, String[] items, String[] additional, int total, Date placeOrderTime, OrderStatus status) {
        this.no = no;
        this.user = user;
        this.items = items;
        this.additional = additional;
        this.total = total;
        this.placeOrderTime = placeOrderTime;
        this.status = status;
    }
}
