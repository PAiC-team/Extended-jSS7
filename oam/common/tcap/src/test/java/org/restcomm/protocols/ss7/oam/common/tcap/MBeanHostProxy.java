
package org.restcomm.protocols.ss7.oam.common.tcap;

import javax.management.MBeanServer;

import org.restcomm.protocols.ss7.oam.common.jmx.MBeanHost;
import org.restcomm.protocols.ss7.oam.common.jmx.MBeanLayer;
import org.restcomm.protocols.ss7.oam.common.jmx.MBeanType;

/**
*
* @author sergey vetyutnev
*
*/
public class MBeanHostProxy implements MBeanHost {

    @Override
    public String getAgentId() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setAgentId(String val) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String getDomainName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setDomainName(String domainName) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getRmiPort() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setRmiPort(int val) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public MBeanServer getMBeanServer() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void start() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void registerMBean(MBeanLayer layer, MBeanType type, String name, Object bean) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Object unregisterMBean(MBeanLayer layer, MBeanType type, String name) {
        // TODO Auto-generated method stub
        return null;
    }

}
