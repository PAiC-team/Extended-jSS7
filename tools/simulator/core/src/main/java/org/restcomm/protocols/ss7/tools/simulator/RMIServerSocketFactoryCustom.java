
package org.restcomm.protocols.ss7.tools.simulator;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.RMISocketFactory;

/**
*
* @author sergey vetyutnev
*
*/
public class RMIServerSocketFactoryCustom extends RMISocketFactory {
//    private final InetAddress localAddress;
    private final int preferredPort;

    public RMIServerSocketFactoryCustom(final int preferredPort) {
        this.preferredPort = preferredPort;
    }

    public Socket createSocket(String host, int port) throws IOException {
        return new Socket(host, port);
    }

    public ServerSocket createServerSocket(int port) throws IOException {
        port = (port == 0 ? preferredPort : port);
        return new ServerSocket(port);
    }

    public int hashCode() {
        return RMIServerSocketFactoryCustom.class.hashCode();
    }
}
