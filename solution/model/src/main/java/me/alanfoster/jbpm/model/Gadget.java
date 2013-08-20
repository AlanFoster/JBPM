package me.alanfoster.jbpm.model;

import java.io.Serializable;

/**
 * Represents a Gadget product.
 *
 * NOTE :: This class is serializable, as it is a requirement of the JBPM persistence mechanism.
 * All Product subclasses have been adorned with this implements interface for demonstration purposes.
 */
public class Gadget extends Product implements Serializable {
    public Gadget() {
        super("Gadget");
    }
}
