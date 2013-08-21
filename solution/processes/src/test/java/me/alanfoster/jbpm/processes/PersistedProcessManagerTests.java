package me.alanfoster.jbpm.processes;

import bitronix.tm.TransactionManagerServices;
import me.alanfoster.jbpm.ISessionManager;
import me.alanfoster.jbpm.PersistedSessionManager;
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
public class PersistedProcessManagerTests extends BaseProcessManagerTests {
    @Override
    public ISessionManager createSessionManager() {
        return new PersistedSessionManager();
    }
}
