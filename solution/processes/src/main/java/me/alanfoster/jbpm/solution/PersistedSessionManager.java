package me.alanfoster.jbpm.solution;

import bitronix.tm.TransactionManagerServices;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.persistence.jpa.JPAKnowledgeService;
import org.drools.runtime.Environment;
import org.drools.runtime.EnvironmentName;
import org.drools.runtime.StatefulKnowledgeSession;
import org.jbpm.process.audit.JPAProcessInstanceDbLog;
import org.jbpm.process.audit.JPAWorkingMemoryDbLogger;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created with IntelliJ IDEA.
 * User: alan
 * Date: 21/08/13
 * Time: 17:40
 * To change this template use File | Settings | File Templates.
 */
public class PersistedSessionManager implements ISessionManager {
    private Environment environment;
    private KnowledgeBase knowledgeBase;

    public PersistedSessionManager(){
        environment = newEnvironment();
        knowledgeBase = newKnowledgeBase();
    }

    public Environment newEnvironment() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("me.alanfoster.jbpm.solution.persistenceunit");

        Environment environment = KnowledgeBaseFactory.newEnvironment();
        environment.set(EnvironmentName.ENTITY_MANAGER_FACTORY, entityManagerFactory);
        environment.set(EnvironmentName.TRANSACTION_MANAGER, TransactionManagerServices.getTransactionManager());

        JPAProcessInstanceDbLog.setEnvironment(environment);

        return environment;
    }

    // TODO extract into strategy pattern
    public KnowledgeBase newKnowledgeBase() {
        KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        knowledgeBuilder.add(ResourceFactory.newClassPathResource("WidgetsAndGadgets.bpmn"), ResourceType.BPMN2);

        KnowledgeBase knowledgeBase = knowledgeBuilder.newKnowledgeBase();
        return knowledgeBase;
    }

    @Override
    public StatefulKnowledgeSession newStatefulKnowledgeSession() {
        StatefulKnowledgeSession statefulKnowledgeSession = JPAKnowledgeService.newStatefulKnowledgeSession(knowledgeBase, null, environment);
        JPAWorkingMemoryDbLogger jpaWorkingMemoryDbLogger = new JPAWorkingMemoryDbLogger(statefulKnowledgeSession);
        return statefulKnowledgeSession;
    }
}
