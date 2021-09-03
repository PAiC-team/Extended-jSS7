package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import java.lang.reflect.InvocationTargetException;

import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.AbstractISUPParameter;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.ForwardCallIndicatorsImpl;
import org.testng.annotations.Test;

/**
 * Start time:12:21:06 2009-04-23<br>
 * Project: mobicents-isup-stack<br>
 * Class to test BCI
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public class ForwardCallIndicatorsTest extends ParameterHarness {

    public ForwardCallIndicatorsTest() {
        super();

        super.badBodies.add(getBadBody1());
        super.badBodies.add(getBadBody2());
        super.goodBodies.add(getBody1());
    }

    private byte[] getBadBody1() {

        byte[] body = new byte[]{(byte) 0x81};
        return body;
     }
    
    private byte[] getBadBody2() {

        byte[] body = new byte[]{(byte) 0x81,3,3};
        return body;
     }
    
    private byte[] getBody1() {

        byte[] body = new byte[]{(byte) 0xAA,0x02};
        return body;
     }

    @Test(groups = { "functional.encode", "functional.decode", "parameter" })
    public void testBody1EncodedValues() throws SecurityException, NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException, ParameterException {
        ForwardCallIndicatorsImpl at = new ForwardCallIndicatorsImpl(getBody1());

        String[] methodNames = { "isNationalCallIdentificator",
                "getEndToEndMethodIndicator",
                "isInterworkingIndicator",
                "isEndToEndInformationIndicator",
                "isIsdnUserPartIndicator",
                "getIsdnUserPartReferenceIndicator",
                "getSccpMethodIndicator",
                "isIsdnAccessIndicator" };
        Object[] expectedValues = { false, 1,true,false,true,2,
                                     1,false};

        super.testValues(at, methodNames, expectedValues);
    }

    
    public AbstractISUPParameter getTestedComponent() throws ParameterException {
        return new org.restcomm.protocols.ss7.isup.impl.message.parameter.ForwardCallIndicatorsImpl();
    }

}