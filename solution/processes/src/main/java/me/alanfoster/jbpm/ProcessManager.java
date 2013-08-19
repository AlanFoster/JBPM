package me.alanfoster.jbpm;

import me.alanfoster.jbpm.handlers.GadgetWorkItemHandler;
import me.alanfoster.jbpm.handlers.WidgetWorkItemHandler;
import org.drools.KnowledgeBase;
import org.drools.runtime.StatefulKnowledgeSession;

/**
 * Represents a ProcessManager which will instantiate the required knowledge base
 * and StatefulSessions.
 */
public class ProcessManager {

    public ProcessManager() {

    }

    /**
     * Connect to a running Guvnor instance.
     */
    public void createKnowledgeBase() {

    }

    public void registerWorkItemHandlers(StatefulKnowledgeSession session) {
        session.getWorkItemManager().registerWorkItemHandler("WidgetService", new WidgetWorkItemHandler());
        session.getWorkItemManager().registerWorkItemHandler("GadgetService", new GadgetWorkItemHandler());
    }

    public void startProcess(String processId) {

    }
}
