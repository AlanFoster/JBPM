package me.alanfoster.jbpm.processes;

import me.alanfoster.jbpm.ProcessManager;
import org.junit.Test;

/**
 * Basic process manager tests.
 *
 * Note, these tests assume that an application has already been deployed to Guvnor,
 * and that a MySQL database exists.
 */
public class ProcessManagerTests {

    @Test
    public void testTrue() {
        ProcessManager processManager = new ProcessManager();
        processManager.startProcess("me.alanfoster.jbpm.WidgetsAndGadgets");
    }
}
