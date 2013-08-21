package me.alanfoster.jbpm.solution;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

/**
 * Created with IntelliJ IDEA.
 * User: alan
 * Date: 21/08/13
 * Time: 17:40
 * To change this template use File | Settings | File Templates.
 */
public class VolatileSessionManager implements ISessionManager {
    private KnowledgeBase knowledgeBase;

    public VolatileSessionManager() {
        knowledgeBase = newKnowledgeBase();
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
        StatefulKnowledgeSession statefulKnowledgeSession = knowledgeBase.newStatefulKnowledgeSession();
        return statefulKnowledgeSession;
    }
}
