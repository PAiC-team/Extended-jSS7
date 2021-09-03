
package org.restcomm.protocols.ss7.oam.common.jmx;

import javax.management.MBeanServer;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface MBeanHost {

    String getAgentId();

    void setAgentId(String val);

    String getDomainName();

    void setDomainName(String domainName);

    int getRmiPort();

    void setRmiPort(int val);

    MBeanServer getMBeanServer();

    void start();

    void stop();

    void registerMBean(MBeanLayer layer, MBeanType type, String name, Object bean);

    Object unregisterMBean(MBeanLayer layer, MBeanType type, String name);

}
