package me.alanfoster.jbpm;

import org.drools.runtime.StatefulKnowledgeSession;

/**
 * This session manager interface is used to abstract the differences between a
 * JTA persisted KnowledgeBase/Session and a completely volatile Session.
 */
public interface ISessionManager {

    /**
     * Creates a new Stateful knowledge Session.
     * @return
     */
    StatefulKnowledgeSession newStatefulKnowledgeSession();

}
