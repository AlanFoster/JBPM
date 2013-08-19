package com.example.jbpm.model;

/**
 * Represents the base class of a Product.
 */
public abstract class Product {
    private String productName;

    protected Product(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
