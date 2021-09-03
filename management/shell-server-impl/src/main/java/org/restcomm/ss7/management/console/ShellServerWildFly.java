
package org.restcomm.ss7.management.console;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.security.authentication.JBossCachedAuthenticationManager;
import org.restcomm.protocols.ss7.scheduler.Scheduler;

/**
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public class ShellServerWildFly extends ShellServer {

    private JBossCachedAuthenticationManager jbossAuthManagement = null;

    public ShellServerWildFly(Scheduler scheduler, List<ShellExecutor> shellExecutors) throws IOException {
        super(scheduler, shellExecutors);
    }

    @Override
    protected void startSecurityManager(InitialContext initialContext, String securityDomain) throws NamingException {
        Object jaasAuthManagement = initialContext.lookup("java:jboss/jaas/" + this.getSecurityDomain());
        logger.debug("jaas Object: "+jaasAuthManagement);
        if (jaasAuthManagement != null && jaasAuthManagement instanceof JBossCachedAuthenticationManager) {
            logger.info("jaas Object: "+jaasAuthManagement.getClass());
            this.jbossAuthManagement = (JBossCachedAuthenticationManager)jaasAuthManagement;
        }
    }

    @Override
    protected void putPrincipal(Map map, Principal principal) {
        map.put("principal", principal);
    }

    @Override
    protected boolean isAuthManagementLoaded() {
        return jbossAuthManagement != null;
    }

    @Override
    protected boolean isValid(Principal principal, Object credential) {
        return this.jbossAuthManagement.isValid(principal, credential);
    }

    @Override
    protected String getLocalSecurityDomain() {
        return this.jbossAuthManagement.getSecurityDomain();
    }

}
