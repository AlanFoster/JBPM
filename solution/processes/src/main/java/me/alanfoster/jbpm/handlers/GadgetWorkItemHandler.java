package me.alanfoster.jbpm.handlers;

import me.alanfoster.jbpm.model.Order;
import org.drools.runtime.process.WorkItem;
import org.drools.runtime.process.WorkItemHandler;
import org.drools.runtime.process.WorkItemManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Represents an Asynchronous work item handler. That is, the work item will not be completed
 * immediately, but instead a period of time may elapse before the WorkItem is complete.
 */
public class GadgetWorkItemHandler implements WorkItemHandler {
    private static final Logger logger = LoggerFactory.getLogger(GadgetWorkItemHandler.class);
    private static final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    @Override
    public void executeWorkItem(final WorkItem workItem, final WorkItemManager workItemManager) {
        final long workItemId = workItem.getId();
        logger.info("GadgetWorkItemHandler Received request for workitem '{}'", workItemId);

        // Create a new thread to run at a later point in time to mimic an asynchronous
        // work item being completed.
        // In the real world, this may take longer than 2 seconds :)
        scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                logger.info("Completing Gadget work item handler for '{}'");

                // Extract the Order from the workitem parameters and update the order as complete
                Order order = Order.class.cast(workItem.getParameter("Order"));
                order.complete();

                // Create our new set of parameters to pass to the workItemManager
                Map<String, Object> params = new HashMap<String,Object>();
                params.put("Order", order);

                workItemManager.completeWorkItem(workItemId, params);
            }
        }, 2000, TimeUnit.MILLISECONDS);
    }

    @Override
    public void abortWorkItem(WorkItem workItem, WorkItemManager workItemManager) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
