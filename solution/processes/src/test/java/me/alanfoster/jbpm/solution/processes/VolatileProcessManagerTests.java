package me.alanfoster.jbpm.solution.processes;

import me.alanfoster.jbpm.solution.ISessionManager;
import me.alanfoster.jbpm.solution.VolatileSessionManager;
import org.junit.Ignore;

/**
 * Basic process manager tests that do not depend on a running instance of Guvnor/MySQL.
 */
public class VolatileProcessManagerTests extends BaseProcessManagerTests {
    @Override
    public ISessionManager createSessionManager() {
        return new VolatileSessionManager();
    }
}
