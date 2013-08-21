package me.alanfoster.jbpm.solution.processes;

import me.alanfoster.jbpm.solution.ISessionManager;
import me.alanfoster.jbpm.solution.PersistedSessionManager;
import org.junit.Ignore;

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
