
package org.restcomm.ss7.management.console;

/**
 * @author sergey.povarnin
 */
public interface ShellServerMBean {

    String getAddress();

    void setAddress(String address);

    int getPort();

    void setPort(int port);

    String getSecurityDomain();

    void setSecurityDomain(String securityDomain);

    int getQueueNumber();

}
