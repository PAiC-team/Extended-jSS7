
package org.restcomm.protocols.ss7.oam.common.sctp;

import org.mobicents.protocols.api.Association;
import org.mobicents.protocols.api.Management;
import org.mobicents.protocols.api.Server;

/**
 *
 * @author sergey vetyutnev
 * @author amit bhayani
 *
 */
public interface SctpManagementJmxMBean extends Management {
    Server addSctpServer(String serverName, String hostAddress, int port, String ipChannelType,
            boolean acceptAnonymousConnections, int maxConcurrentConnectionsCount, String extraHostAddresses) throws Exception;

    Server modifySctpServer(String serverName, String hostAddress, String port, String ipChannelType,
            String acceptAnonymousConnections, String maxConcurrentConnectionsCount, String extraHostAddresses) throws Exception;

    Association addSctpAssociation(String hostAddress, int hostPort, String peerAddress, int peerPort, String assocName,
            String ipChannelType, String extraHostAddresses) throws Exception;

    Association modifySctpAssociation(String hostAddress, String hostPort, String peerAddress, String peerPort, String assocName,
            String ipChannelType, String extraHostAddresses) throws Exception;

    Association addSctpServerAssociation(String peerAddress, int peerPort, String serverName, String assocName,
            String ipChannelType) throws Exception;

    Association modifySctpServerAssociation(String peerAddress, String peerPort, String serverName, String assocName,
            String ipChannelType) throws Exception;

}
