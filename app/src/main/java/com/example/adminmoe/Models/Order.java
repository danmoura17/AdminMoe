package com.example.adminmoe.Models;

public class Order {
    private String costumerName;
    private Double totalValue;

    public Order() {

    }

    public Order(String costumerName, Double totalValue) {
        this.costumerName = costumerName;
        this.totalValue = totalValue;
    }

    public String getCostumerName() {
        return costumerName;
    }

    public void setCostumerName(String costumerName) {
        this.costumerName = "Cliente: " + costumerName;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }
}
