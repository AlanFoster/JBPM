package me.alanfoster.jbpm.handlers;

import com.example.jbpm.model.Order;
import org.drools.runtime.process.WorkItem;
import org.drools.runtime.process.WorkItemHandler;
import org.drools.runtime.process.WorkItemManager;

import java.util.HashMap;
import java.util.Map;

/**
 * This widget service can be represents a WorkItemHandler that will execute synchronously.
 * That is, the WorkItem may be completed immediately.
 */
public class WidgetWorkItemHandler implements WorkItemHandler {

    /**
     * Immediately, ie synchronously, completes the given WorkItem.
     *
     * @param workItem
     * @param workItemManager
     */
    @Override
    public void executeWorkItem(WorkItem workItem, WorkItemManager workItemManager) {
        // Extract the Order from the workitem parameters and update the order as complete
        Order order = Order.class.cast(workItem.getParameter("Order"));
        order.complete();

        // Create our new set of parameters to pass to the workItemManager
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("Order", order);

        workItemManager.completeWorkItem(workItem.getId(), params);
    }

    @Override
    public void abortWorkItem(WorkItem workItem, WorkItemManager workItemManager) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}