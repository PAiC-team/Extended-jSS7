package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import java.lang.reflect.InvocationTargetException;

import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.AbstractISUPParameter;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.AutomaticCongestionLevelImpl;
import org.testng.annotations.Test;

/**
 * Start time:12:21:06 2009-04-23<br>
 * Project: mobicents-isup-stack<br>
 * Class to test BCI
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public class AutomaticCongestionLevelTest extends ParameterHarness {

    public AutomaticCongestionLevelTest() {
        super();

        super.badBodies.add(new byte[2]);


        super.goodBodies.add(getBody1());
    }

    private byte[] getBody1() {

       byte[] body = new byte[]{(byte)0x02};
       return body;
    }

    @Test(groups = { "functional.encode", "functional.decode", "parameter" })
    public void testBody1EncodedValues() throws SecurityException, NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException, ParameterException {
        AutomaticCongestionLevelImpl at = new AutomaticCongestionLevelImpl(getBody1());

        String[] methodNames = { "getAutomaticCongestionLevel" };
        Object[] expectedValues = { AutomaticCongestionLevelImpl._CONGESTION_LEVE_2_EXCEEDED };

        super.testValues(at, methodNames, expectedValues);
    }

    
    public AbstractISUPParameter getTestedComponent() throws ParameterException {
        return new org.restcomm.protocols.ss7.isup.impl.message.parameter.AutomaticCongestionLevelImpl();
    }

}
