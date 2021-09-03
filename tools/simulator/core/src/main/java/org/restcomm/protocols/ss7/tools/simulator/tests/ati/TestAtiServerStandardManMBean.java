package org.restcomm.protocols.ss7.tools.simulator.tests.ati;

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
public class TestAtiServerStandardManMBean extends StandardMBean {

    public TestAtiServerStandardManMBean(TestAtiServerMan impl, Class<TestAtiServerManMBean> intf) throws NotCompliantMBeanException {
        super(impl, intf);
    }

    @Override
    public MBeanInfo getMBeanInfo() {

        MBeanAttributeInfo[] attributes = new MBeanAttributeInfo[] {
                new MBeanAttributeInfo("ATIReaction", ATIReaction.class.getName(), "ATI response type", true, true, false),
                new MBeanAttributeInfo("ATIReaction_Value", String.class.getName(), "ATI response type", true, false, false), };

        MBeanParameterInfo[] signString = new MBeanParameterInfo[] { new MBeanParameterInfo("val", String.class.getName(), "Index number or value") };

//        MBeanParameterInfo[] performATIParam = new MBeanParameterInfo[] { new MBeanParameterInfo("msg", String.class.getName(), "Message text"),
//                new MBeanParameterInfo("address", String.class.getName(), "SubscriberIdentity: IMSI or MSISDN"), };

        MBeanOperationInfo[] operations = new MBeanOperationInfo[] {
//                new MBeanOperationInfo("performAtiRequest", "Send ATI request", performATIParam, String.class.getName(), MBeanOperationInfo.ACTION),
//                new MBeanOperationInfo("closeCurrentDialog", "Closing the current dialog", null, String.class.getName(), MBeanOperationInfo.ACTION),

                new MBeanOperationInfo("putATIReaction", "ATI response type: "
                        + "1:ReturnSuccess,3:ReturnSystemFailureError,4:ReturnDataMissingError,5:ReturnUnknownSubscriberError",
                        signString, Void.TYPE.getName(), MBeanOperationInfo.ACTION),
                };

        return new MBeanInfo(TestAtiServerMan.class.getName(), "AtiServer test parameters management", attributes, null, operations, null);
    }

}
