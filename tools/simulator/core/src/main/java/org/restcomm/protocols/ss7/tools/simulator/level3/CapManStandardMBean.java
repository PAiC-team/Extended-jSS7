
package org.restcomm.protocols.ss7.tools.simulator.level3;

import javax.management.MBeanAttributeInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanParameterInfo;
import javax.management.NotCompliantMBeanException;
import javax.management.StandardMBean;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class CapManStandardMBean extends StandardMBean {

    public CapManStandardMBean(CapMan impl, Class<CapManMBean> intf) throws NotCompliantMBeanException {
        super(impl, intf);
    }

    @Override
    public MBeanInfo getMBeanInfo() {

        MBeanAttributeInfo[] attributes = new MBeanAttributeInfo[] {
        // new MBeanAttributeInfo("LocalSsn", int.class.getName(), "Local Ssn", true, true, false),
        // new MBeanAttributeInfo("RemoteSsn", int.class.getName(), "Remote Ssn", true, true, false),
        new MBeanAttributeInfo("RemoteAddressDigits", String.class.getName(),
                "Remote address digits. If empty ROUTING_BASED_ON_DPC_AND_SSN is used for CalledPartyAddress (remoteSpc from SCCP), "
                        + "if not empty ROUTING_BASED_ON_GLOBAL_TITLE is used (address and Ssn from CAP)", true, true, false), };

        MBeanParameterInfo[] signString = new MBeanParameterInfo[] { new MBeanParameterInfo("val", String.class.getName(),
                "Index number or value") };

        MBeanOperationInfo[] operations = new MBeanOperationInfo[] {};

        return new MBeanInfo(CapMan.class.getName(), "Cap Management", attributes, null, operations, null);
    }

}
