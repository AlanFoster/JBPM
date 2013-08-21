package me.alanfoster.jbpm.processes;

import me.alanfoster.jbpm.model.Gadget;
import me.alanfoster.jbpm.model.Order;
import me.alanfoster.jbpm.model.Widget;
import me.alanfoster.jbpm.ProcessManager;
import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Basic process manager tests that do not depend on a running instance of Guvnor/MySQL.
 */
public class VolatileProcessManagerTests extends BaseProcessManagerTests {

    /**
     * Create a new knowledge base from the BPMN files within the current classpath.
     * This does not connect to a running Guvnor instance.
     */
    public KnowledgeBase createKnowledgeBase() {
        KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        knowledgeBuilder.add(ResourceFactory.newClassPathResource("WidgetsAndGadgets.bpmn"), ResourceType.BPMN2);

        KnowledgeBase knowledgeBase = knowledgeBuilder.newKnowledgeBase();
        return knowledgeBase;
    }
}
