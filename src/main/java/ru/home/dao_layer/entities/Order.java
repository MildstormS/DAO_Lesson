package ru.home.dao_layer.entities;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private int id;
    private Date date;
    private int customerId;
    private String productName;
    private int amount;

    public Order(int id, Date date, int customerId, String productName, int amount) {
        this.id = id;
        this.date = date;
        this.customerId = customerId;
        this.productName = productName;
        this.amount = amount;
    }
}
