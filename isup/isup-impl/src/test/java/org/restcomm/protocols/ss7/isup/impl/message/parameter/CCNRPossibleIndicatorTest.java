package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import java.lang.reflect.InvocationTargetException;

import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.AbstractISUPParameter;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.CCSSImpl;
import org.testng.annotations.Test;

/**
 * Start time:12:21:06 2009-04-23<br>
 * Project: mobicents-isup-stack<br>
 * Class to test BCI
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public class CCNRPossibleIndicatorTest extends ParameterHarness {

    public CCNRPossibleIndicatorTest() {
        super();

        super.badBodies.add(new byte[2]);


        super.goodBodies.add(getBody1());
    }

    private byte[] getBody1() {

       byte[] body = new byte[]{(byte)0x01};
       return body;
    }

    @Test(groups = { "functional.encode", "functional.decode", "parameter" })
    public void testBody1EncodedValues() throws SecurityException, NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException, ParameterException {
        CCSSImpl at = new CCSSImpl(getBody1());

        String[] methodNames = { "isCcssCall" };
        Object[] expectedValues = { CCSSImpl._CCSS_CALL };

        super.testValues(at, methodNames, expectedValues);
    }

    
    public AbstractISUPParameter getTestedComponent() throws ParameterException {
        return new org.restcomm.protocols.ss7.isup.impl.message.parameter.CCSSImpl();
    }

}
