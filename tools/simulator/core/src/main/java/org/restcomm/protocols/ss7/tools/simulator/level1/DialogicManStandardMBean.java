
package org.restcomm.protocols.ss7.tools.simulator.level1;

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
public class DialogicManStandardMBean extends StandardMBean {

    public DialogicManStandardMBean(DialogicMan impl, Class<DialogicManMBean> intf) throws NotCompliantMBeanException {
        super(impl, intf);
    }

    @Override
    public MBeanInfo getMBeanInfo() {

        MBeanAttributeInfo[] attributes = new MBeanAttributeInfo[] {
                new MBeanAttributeInfo("SourceModuleId", int.class.getName(), "Source module Id", true, true, false),
                new MBeanAttributeInfo("DestinationModuleId", int.class.getName(), "Destination module Id", true, true, false), };

        MBeanParameterInfo[] signString = new MBeanParameterInfo[] { new MBeanParameterInfo("val", String.class.getName(),
                "Index number or value") };

        MBeanOperationInfo[] operations = new MBeanOperationInfo[] {};

        return new MBeanInfo(DialogicMan.class.getName(), "Dialogic Management", attributes, null, operations, null);

    }
}
