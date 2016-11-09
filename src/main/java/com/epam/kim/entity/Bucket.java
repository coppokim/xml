package com.epam.kim.entity;

import java.util.ArrayList;
import java.util.List;

public class Bucket {
    private List<Product> productList = new ArrayList<>();
    private int id;
    private int buyerId;



    public Bucket() {
    }

    public Bucket(List<Product> productList, int id, int buyerId) {
        this.productList = productList;
        this.id = id;
        this.buyerId = buyerId;
    }

    public void addProduct(Product product){
        this.productList.add(product);
    }

    public void setProductList(List<Product> productList) {

        this.productList = productList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public List<Product> getProductList() {
        return productList;
    }


    @Override
    public String toString() {
        return "Bucket{" +
                "productList=" + productList +
                ", id=" + id +
                ", buyerId=" + buyerId +
                '}';
    }
}
