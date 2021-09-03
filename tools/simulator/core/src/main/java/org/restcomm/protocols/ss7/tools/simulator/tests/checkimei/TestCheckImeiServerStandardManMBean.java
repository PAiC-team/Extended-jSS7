
package org.restcomm.protocols.ss7.tools.simulator.tests.checkimei;

import javax.management.MBeanAttributeInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanParameterInfo;
import javax.management.NotCompliantMBeanException;
import javax.management.StandardMBean;

/**
 * @author mnowa
 *
 */
public class TestCheckImeiServerStandardManMBean extends StandardMBean {

    public TestCheckImeiServerStandardManMBean(TestCheckImeiServerMan impl, Class<TestCheckImeiServerManMBean> intf) throws NotCompliantMBeanException {
        super(impl, intf);
    }

    @Override
    public MBeanInfo getMBeanInfo() {

        MBeanAttributeInfo[] attributes = new MBeanAttributeInfo[] {

                new MBeanAttributeInfo("AutoEquipmentStatus", EquipmentStatusType.class.getName(), "EquipmentStatus parameter to be automatically send in CheckImeiResponse", true, true, false),
                new MBeanAttributeInfo("AutoEquipmentStatus_Value", String.class.getName(), "EquipmentStatus parameter to be automatically send in CheckImeiResponse", true, false, false),
                new MBeanAttributeInfo("OneNotificationFor100Dialogs", boolean.class.getName(),
                        "If true there will be only one notification per every 100 sent dialogs", true, true, true),
                new MBeanAttributeInfo("CurrentRequestDef", String.class.getName(), "Definition of the current request Dialog",
                        true, false, false),

        };

        MBeanParameterInfo[] putAutoEquipmentStatusParam = new MBeanParameterInfo[] { new MBeanParameterInfo("val", String.class.getName(), "EquipmentStatus value") };

        MBeanOperationInfo[] operations = new MBeanOperationInfo[] {
                new MBeanOperationInfo(
                        "putAutoEquipmentStatus",
                        "EquipmentStatus parameter to be automatically send in CheckImeiResponse: "
                                + "0:whiteListed,1:blackListed,2:greyListed",
                        putAutoEquipmentStatusParam, Void.TYPE.getName(), MBeanOperationInfo.ACTION),
                new MBeanOperationInfo("closeCurrentDialog", "Closing the current dialog", null, String.class.getName(), MBeanOperationInfo.ACTION)
        };
        return new MBeanInfo(TestCheckImeiServerMan.class.getName(), "CheckImeiServer test parameters management", attributes, null, operations, null);
    }
}
