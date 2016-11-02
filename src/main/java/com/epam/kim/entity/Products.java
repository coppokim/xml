package com.epam.kim.entity;

public class Products {
    private byte id;
    private int price;
    private String name;

    public Products(byte id, int price, String name) {
        this.id = id;
        this.price = price;
        this.name = name;
    }

    public byte getId() {
        return id;
    }

    public void setId(byte id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
