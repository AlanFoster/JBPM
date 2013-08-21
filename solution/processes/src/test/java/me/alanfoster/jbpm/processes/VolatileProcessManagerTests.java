package me.alanfoster.jbpm.processes;

import me.alanfoster.jbpm.ISessionManager;
import me.alanfoster.jbpm.PersistedSessionManager;
import me.alanfoster.jbpm.VolatileSessionManager;
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
    @Override
    public ISessionManager createSessionManager() {
        return new VolatileSessionManager();
    }
}
