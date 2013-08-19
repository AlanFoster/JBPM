package com.example.jbpm.model;

import java.io.Serializable;

/**
 * Represents a Widget product.
 *
 * NOTE :: This class is serializable, as it is a requirement of the JBPM persistence mechanism.
 * All Product subclasses have been adorned with this implements interface for demonstration purposes.
 */
public class Widget extends Product implements Serializable {
    public Widget() {
        super("Widget");
    }
}
