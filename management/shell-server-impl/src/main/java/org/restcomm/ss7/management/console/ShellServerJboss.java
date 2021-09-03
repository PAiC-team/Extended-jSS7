
package org.restcomm.ss7.management.console;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.security.plugins.JaasSecurityManager;
import org.restcomm.protocols.ss7.scheduler.Scheduler;

/**
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public class ShellServerJboss extends ShellServer {

    private org.jboss.security.plugins.JaasSecurityManager jaasSecurityManager = null;

    public ShellServerJboss(Scheduler scheduler, List<ShellExecutor> shellExecutors) throws IOException {
        super(scheduler, shellExecutors);
    }

    @Override
    protected void startSecurityManager(InitialContext initialContext, String securityDomain) throws NamingException {
        this.jaasSecurityManager = (JaasSecurityManager) initialContext.lookup(securityDomain);
    }

    @Override
    protected void putPrincipal(Map map, Principal principal) {
        map.put("principal", this.jaasSecurityManager.getPrincipal(principal));
    }

    @Override
    protected boolean isAuthManagementLoaded() {
        return jaasSecurityManager != null;
    }

    @Override
    protected boolean isValid(Principal principal, Object credential) {
        return this.jaasSecurityManager.isValid(principal, credential);
    }

    @Override
    protected String getLocalSecurityDomain() {
        return this.jaasSecurityManager.getSecurityDomain();
    }

}
