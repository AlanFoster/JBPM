package me.alanfoster.jbpm.processes;

import bitronix.tm.TransactionManagerServices;
import me.alanfoster.jbpm.ProcessManager;
import me.alanfoster.jbpm.model.Gadget;
import me.alanfoster.jbpm.model.Order;
import me.alanfoster.jbpm.model.Widget;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.Environment;
import org.drools.runtime.EnvironmentName;
import org.jbpm.process.audit.JPAProcessInstanceDbLog;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Tests for Persisted Processes.
 *
 * Note, these tests assume that an application has already been deployed to Guvnor,
 * and that a MySQL database exists.
 */
public class PersistedProcessManagerTests /*extends BaseProcessManagerTests*/ {



/*    @Ignore
    @Test
    public void testWidget() throws Exception {

    }*/


    /**
     * Create a new knowledge base from the BPMN files within the current classpath.
     * This does not connect to a running Guvnor instance.
     */
    @Test
    public void createKnowledgeBase() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("me.alanfoster.jbpm.persistenceunit");

        Environment environment = KnowledgeBaseFactory.newEnvironment();
        environment.set(EnvironmentName.ENTITY_MANAGER_FACTORY, entityManagerFactory);
        environment.set(EnvironmentName.TRANSACTION_MANAGER, TransactionManagerServices.getTransactionManager());

        JPAProcessInstanceDbLog.setEnvironment(environment);

        KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        knowledgeBuilder.add(ResourceFactory.newClassPathResource("WidgetsAndGadgets.bpmn"), ResourceType.BPMN2);

        KnowledgeBase knowledgeBase = knowledgeBuilder.newKnowledgeBase();


        ProcessManager processManager = new ProcessManager(knowledgeBase, environment);

        Order widgetOrder = getWidgetOrder();
        processManager.startWidgetAndGadgetsProcess(widgetOrder);

        Assert.assertTrue(
                "The widget order should be completed",
                widgetOrder.isCompleted());
    }





    private Order getWidgetOrder() {
        return new Order(new Widget());
    }

    private Order getGadgetOrder() {
        return new Order(new Gadget());
    }

}
