package me.alanfoster.jbpm.solution.model;

import java.io.Serializable;

/**
 * Represents the base class of a Product.
 *
 * NOTE :: This class is serializable for persistence reasons.
 */
public abstract class Product implements Serializable {
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

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                '}';
    }
}
