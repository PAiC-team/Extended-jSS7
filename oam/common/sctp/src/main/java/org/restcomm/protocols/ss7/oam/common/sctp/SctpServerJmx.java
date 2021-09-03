
package org.restcomm.protocols.ss7.oam.common.sctp;

import java.util.List;

import org.mobicents.protocols.api.Association;
import org.mobicents.protocols.api.IpChannelType;
import org.mobicents.protocols.api.Server;

/**
 *
 * @author sergey vetyutnev
 * @author Amit Bhayani
 *
 */
public class SctpServerJmx implements SctpServerJmxMBean {

    private final Server wrappedServer;
    private final SctpManagementJmx sctpManagement;

    SctpServerJmx(SctpManagementJmx sctpManagement, Server server) {
        super();
        this.wrappedServer = server;
        this.sctpManagement = sctpManagement;
    }

    @Override
    public String getName() {
        return this.wrappedServer.getName();
    }

    @Override
    public String getHostAddress() {
        return this.wrappedServer.getHostAddress();
    }

    @Override
    public int getHostport() {
        return this.wrappedServer.getHostport();
    }

    @Override
    public IpChannelType getIpChannelType() {
        return this.wrappedServer.getIpChannelType();
    }

    @Override
    public String[] getExtraHostAddresses() {
        return this.wrappedServer.getExtraHostAddresses();
    }

    @Override
    public String getServerDescription() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isStarted() {
        return this.wrappedServer.isStarted();
    }

    @Override
    public List<Association> getAnonymAssociations() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<String> getAssociations() {
        return this.wrappedServer.getAssociations();
    }

    @Override
    public int getMaxConcurrentConnectionsCount() {
        return this.wrappedServer.getMaxConcurrentConnectionsCount();
    }

    @Override
    public boolean isAcceptAnonymousConnections() {
        return this.wrappedServer.isAcceptAnonymousConnections();
    }

    @Override
    public void setMaxConcurrentConnectionsCount(int val) {
        this.wrappedServer.setMaxConcurrentConnectionsCount(val);
    }
}
