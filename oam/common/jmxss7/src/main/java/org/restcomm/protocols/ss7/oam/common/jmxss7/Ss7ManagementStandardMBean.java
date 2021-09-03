
package org.restcomm.protocols.ss7.oam.common.jmxss7;

import javax.management.MBeanAttributeInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanParameterInfo;
import javax.management.NotCompliantMBeanException;
import javax.management.StandardMBean;

import org.restcomm.protocols.ss7.oam.common.jmx.ManagementMBean;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class Ss7ManagementStandardMBean extends StandardMBean {

    public Ss7ManagementStandardMBean(Ss7Management impl, Class<ManagementMBean> intf) throws NotCompliantMBeanException {
        super(impl, intf);
    }

    @Override
    public MBeanInfo getMBeanInfo() {

        MBeanAttributeInfo[] attributes = new MBeanAttributeInfo[] { new MBeanAttributeInfo("AgentId", String.class.getName(),
                "The agent identifier of the MBeanServer to retrieve", true, false, false), };

        MBeanParameterInfo[] signString = new MBeanParameterInfo[] { new MBeanParameterInfo("val", String.class.getName(),
                "Index number or value") };

        MBeanOperationInfo[] operations = new MBeanOperationInfo[] {};

        return new MBeanInfo(ManagementMBean.class.getName(), "Ss7 Management", attributes, null, operations, null);

    }

}
