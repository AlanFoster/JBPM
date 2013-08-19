JBPM Example
============

TOOD Add summary of process + technologies

Overview
=========
Represents a simple JBPM5 business process which uses the persistence mechanism.

TODO Add completed JBPM workflow picture

Work Item Handlers
------------------

- Widgets
    - Synchronous

- Gadgets
    - Asynchronous

Pre-Requisites
--------------

To run these examples you will require the following tools; Your version numbers may differ, however I have provided mine
at the time of writing.

- [Ant](http://ant.apache.org/) 1.9.2
- [Maven](http://maven.apache.org/) 3.1.0
- [JBPM Installer](http://sourceforge.net/projects/jbpm/files/jBPM%205/jbpm-5.4.0.Final/) - 5.4.0.Final
- [Eclipse](http://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/juno/SR2/eclipse-jee-juno-SR2-win32.zip) JEE Juno SR2
    - BPMN 2.0 Modeler Plugin

Running The demo
----------------

###Application Startup

After you have installed all of the pre-requisites.

Run the JBPM instance

    cd jbpm-install
    ant install.demo.noeclipse
    ant start.demo.noeclipse

If you are having issues with the installer, be sure to look at the readme under `${JBPM_INSTALL_HOME}/install.html`

TODO ...

Useful Resources
=========

- [JBPM Community Training](http://salaboy.com/2011/01/24/announcing-jbpm5-community-training/)
- [JBPM5 Developer Guide](http://www.packtpub.com/jboss-business-process-management-5-jave-developer-guide/book)