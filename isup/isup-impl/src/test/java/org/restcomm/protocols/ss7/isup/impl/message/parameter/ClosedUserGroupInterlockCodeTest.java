package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import java.lang.reflect.InvocationTargetException;

import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.AbstractISUPParameter;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.ClosedUserGroupInterlockCodeImpl;
import org.testng.annotations.Test;

/**
 * Start time:12:21:06 2009-04-23<br>
 * Project: mobicents-isup-stack<br>
 * Class to test BCI
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public class ClosedUserGroupInterlockCodeTest extends ParameterHarness {

    public ClosedUserGroupInterlockCodeTest() {
        super();
        super.badBodies.add(getBadBody1());
        super.badBodies.add(getBadBody2());
        
        super.goodBodies.add(getBody1());
    }

    private byte[] getBadBody1() {

        byte[] body = new byte[]{0x0E};
        return body;
     }
    private byte[] getBadBody2() {

        byte[] body = new byte[5];
        return body;
     }
    private byte[] getBody1() {

        byte[] body = new byte[]{
                0x12,
                0x63,
                (byte) 0xA8,
                0x33
        };
        return body;
     }

    @Test(groups = { "functional.encode", "functional.decode", "parameter" })
    public void testBody1EncodedValues() throws SecurityException, NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException, ParameterException {
        ClosedUserGroupInterlockCodeImpl at = new ClosedUserGroupInterlockCodeImpl(getBody1());

        String[] methodNames = { "getNiDigits","getBinaryCode" };
        Object[] expectedValues = { new byte[]{1,2,6,3}, 43059 };

        super.testValues(at, methodNames, expectedValues);
    }

    
    public AbstractISUPParameter getTestedComponent() throws ParameterException {
        return new org.restcomm.protocols.ss7.isup.impl.message.parameter.ClosedUserGroupInterlockCodeImpl();
    }

}
