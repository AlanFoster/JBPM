package me.alanfoster.jbpm.model;

import java.io.Serializable;

/**
 * Represents a basic order which will pass through the system.
 * An order may have a Product associated with it, and a state of whether or not the order has been fulfilled.
 *
 * NOTE :: This class is serializable, as it is a requirement of the JBPM persistence mechanism.
 */
public class Order implements Serializable {
    private boolean completed;
    private Product product;

    public Order() {
    }

    public Order(Product product) {
        this.product = product;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void complete() {
        this.completed = true;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Order{" +
                "completed=" + completed +
                ", product=" + product +
                '}';
    }
}
