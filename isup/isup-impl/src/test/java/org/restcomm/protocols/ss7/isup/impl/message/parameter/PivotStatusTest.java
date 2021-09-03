package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import java.lang.reflect.InvocationTargetException;

import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.AbstractISUPParameter;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.PivotStatusImpl;
import org.testng.annotations.Test;

/**
 * Start time:12:21:06 2009-04-23<br>
 * Project: mobicents-isup-stack<br>
 * Class to test BCI
 * 
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public class PivotStatusTest extends ParameterHarness {

    public PivotStatusTest() {
        super();
//
//        super.badBodies.add(new byte[0]);
//        super.badBodies.add(new byte[]{(byte) 0x80,12});

        super.goodBodies.add(getBody1());
    }

    private byte[] getBody1() {
        //FIXME: ATM PivotStatus does not take into account extension bit, each byte is considered a separate status.
        //byte[] body = new byte[] { 0,1, (byte)(0x80 |2) };
        byte[] body = new byte[] { 0,1 ,2 };
        return body;
    }

    @Test(groups = { "functional.encode", "functional.decode", "parameter" })
    public void testBody1EncodedValues() throws SecurityException, NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException, ParameterException {
        PivotStatusImpl at = new PivotStatusImpl(getBody1());
        final String[] getterMethodNames = new String[]{"getStatus"};
        final Object[][] expectedValues = new Object[][]{
                new Object[]{(byte)0},
                new Object[]{(byte)1},
                new Object[]{(byte)2}
        };
        testValues(at, "getStatus", getterMethodNames, expectedValues);
    }
    public AbstractISUPParameter getTestedComponent() throws ParameterException {
        return new org.restcomm.protocols.ss7.isup.impl.message.parameter.PivotStatusImpl();
    }
    
}
