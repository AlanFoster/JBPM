package me.alanfoster.jbpm.processes;

import me.alanfoster.jbpm.ProcessManager;
import me.alanfoster.jbpm.model.Gadget;
import me.alanfoster.jbpm.model.Order;
import me.alanfoster.jbpm.model.Widget;
import org.drools.KnowledgeBase;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: alan
 * Date: 20/08/13
 * Time: 23:24
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseProcessManagerTests {

    @Ignore
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


    @Ignore
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
     *
     * @return Either a transacted or non-transacted knowledge base implementation.
     */
    public abstract KnowledgeBase createKnowledgeBase();
}
