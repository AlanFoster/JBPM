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
 * Basic process manager tests.
 *
 * Note, these tests assume that an application has already been deployed to Guvnor,
 * and that a MySQL database exists.
 */
public class ProcessManagerTests {

    @Test
    public void testWidget() throws Exception {
        KnowledgeBase knowledgeBase = createKnowledgeBase();
        ProcessManager processManager = new ProcessManager(knowledgeBase);

        Order widgetOrder = getWidgetOrder();
        processManager.startWidgetAndGadgetsProcess(widgetOrder);

        Assert.assertTrue(
                "The widget order should be completed",
                widgetOrder.isCompleted());
    }


    @Test
    public void testGadget() throws Exception {
        KnowledgeBase knowledgeBase = createKnowledgeBase();
        ProcessManager processManager = new ProcessManager(knowledgeBase);

        Order gadgetOrder = getGadgetOrder();
        processManager.startWidgetAndGadgetsProcess(gadgetOrder);

        // The Gadget service will be performed asynchronously, and we should wait for it to complete
        Thread.sleep(3000);

        Assert.assertTrue(
                "The gadget order should be completed",
                gadgetOrder.isCompleted());
    }



    private Order getWidgetOrder() {
        return new Order(new Widget());
    }

    private Order getGadgetOrder() {
        return new Order(new Gadget());
    }

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
