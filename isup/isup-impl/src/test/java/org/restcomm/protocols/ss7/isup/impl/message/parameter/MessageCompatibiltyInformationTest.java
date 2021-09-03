package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import java.lang.reflect.InvocationTargetException;

import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.AbstractISUPParameter;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.MessageCompatibilityInformationImpl;
import org.restcomm.protocols.ss7.isup.message.parameter.MessageCompatibilityInstructionIndicator;
import org.testng.annotations.Test;

/**
 * Start time:12:21:06 2009-04-23<br>
 * Project: mobicents-isup-stack<br>
 * Class to test BCI
 * 
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public class MessageCompatibiltyInformationTest extends ParameterHarness {

    public MessageCompatibiltyInformationTest() {
        super();

        super.badBodies.add(new byte[1]);
        super.badBodies.add(new byte[3]);

        super.goodBodies.add(getBody1());
        super.goodBodies.add(getBody2());
    }

    private byte[] getBody1() {

        byte[] body = new byte[] { (byte) 0x81 };
        return body;
    }

    @Test(groups = { "functional.encode", "functional.decode", "parameter" })
    public void testBody1EncodedValues() throws SecurityException, NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException, ParameterException {
        MessageCompatibilityInformationImpl at = new MessageCompatibilityInformationImpl(getBody1());
        final String[] getterMethodNames = new String[]{"isTransitAtIntermediateExchangeIndicator",
                "isReleaseCallIndicator",
                "isSendNotificationIndicator",
                "isDiscardMessageIndicator",
                "isPassOnNotPossibleIndicator",
                "getBandInterworkingIndicator"};
        final Object[][] expectedValues = new Object[][]{
                new Object[]{true,false,false,false,false,0}
        };
        testValues(at, "getMessageCompatibilityInstructionIndicators", getterMethodNames, expectedValues);
    }

    private byte[] getBody2() {

        byte[] body = new byte[] { 0x42, (byte) 0x81 };
        return body;
    }
    @Test(groups = { "functional.encode", "functional.decode", "parameter" })
    public void testBody2EncodedValues() throws SecurityException, NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException, ParameterException {
        MessageCompatibilityInformationImpl at = new MessageCompatibilityInformationImpl(getBody2());
        final String[] getterMethodNames = new String[]{"isTransitAtIntermediateExchangeIndicator",
                "isReleaseCallIndicator",
                "isSendNotificationIndicator",
                "isDiscardMessageIndicator",
                "isPassOnNotPossibleIndicator",
                "getBandInterworkingIndicator"};
        final Object[][] expectedValues = new Object[][]{
                new Object[]{false,true,false,false,false,2},
                new Object[]{true,false,false,false,false,0}
        };
        testValues(at, "getMessageCompatibilityInstructionIndicators", getterMethodNames, expectedValues);
    }
    public AbstractISUPParameter getTestedComponent() throws ParameterException {
        return new org.restcomm.protocols.ss7.isup.impl.message.parameter.MessageCompatibilityInformationImpl();
    }
    
}
