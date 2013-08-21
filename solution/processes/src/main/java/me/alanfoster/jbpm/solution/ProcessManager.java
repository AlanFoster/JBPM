package me.alanfoster.jbpm.solution;

import me.alanfoster.jbpm.solution.model.Order;
import me.alanfoster.jbpm.solution.handlers.GadgetWorkItemHandler;
import me.alanfoster.jbpm.solution.handlers.WidgetWorkItemHandler;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.persistence.jpa.JPAKnowledgeService;
import org.drools.runtime.Environment;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.process.WorkflowProcessInstance;
import org.jbpm.process.audit.JPAWorkingMemoryDbLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a ProcessManager which will be the entry point for new business processes to begin.
 * This could be injected into a webservice/restful service etc.
 */
public class ProcessManager {
    private static final Logger logger = LoggerFactory.getLogger(GadgetWorkItemHandler.class);
    private ISessionManager sessionManager;

    /**
     * Creates a new instance of the process manager
     *
     * @param sessionManager Injected Session Manager which has easy access to create new stateful knowledge sessions.
     *                      Always favour Dependency injection over static access for testability.
     */
    public ProcessManager(ISessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    /**
     * @param session
     */
    public void registerWorkItemHandlers(StatefulKnowledgeSession session) {
        logger.info("Registering work item handlers...");
        session.getWorkItemManager().registerWorkItemHandler("WidgetService", new WidgetWorkItemHandler());
        session.getWorkItemManager().registerWorkItemHandler("GadgetService", new GadgetWorkItemHandler());
    }

    /**
     * @param processId The BPMN process ID to begin
     * @param processParameters The parameters to pass to the new business process instance.
     * @return The newly started WorkflowProcessInstance
     */
    private WorkflowProcessInstance startProcess(String processId, Map<String, Object> processParameters) {
        // It is considered best practice to create a new StatefulKnowledgeSession per process, for transaction purposes
        StatefulKnowledgeSession statefulKnowledgeSession = sessionManager.newStatefulKnowledgeSession();

        registerWorkItemHandlers(statefulKnowledgeSession);
        WorkflowProcessInstance workflowProcessInstance = (WorkflowProcessInstance) statefulKnowledgeSession.startProcess(processId, processParameters);

        // A knowledge runtime logger will allow us to log using our log4j settings
        KnowledgeRuntimeLoggerFactory.newConsoleLogger(statefulKnowledgeSession);

        logger.info("Created a new workflow process instance with the id '{}'", workflowProcessInstance.getId());

        return workflowProcessInstance;
    }

    /**
     * Creates a new business process for the given Order.
     *
     * @param order The order to be fulfilled, which will contain either a Widget or Gadget.
     * @return The newly started WorkflowProcessInstance
     */
    public WorkflowProcessInstance startWidgetAndGadgetsProcess(Order order) {
        HashMap<String, Object> processParameters = new HashMap<String, Object>();
        processParameters.put("Order", order);
        return startProcess("example.WidgetsAndGadgets", processParameters);
    }
}
