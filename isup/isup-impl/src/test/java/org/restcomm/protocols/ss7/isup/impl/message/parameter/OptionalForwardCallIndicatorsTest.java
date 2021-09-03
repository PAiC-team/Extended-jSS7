package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.AbstractISUPParameter;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.OptionalForwardCallIndicatorsImpl;
import org.testng.annotations.Test;

/**
 * Start time:16:20:47 2009-04-26<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public class OptionalForwardCallIndicatorsTest extends ParameterHarness {

    private static final int _TURN_ON = 1;
    private static final int _TURN_OFF = 0;

    public OptionalForwardCallIndicatorsTest() {
        super();
        super.goodBodies.add(new byte[] { 7 });
        super.badBodies.add(new byte[] { 8, 8 });
    }

    @Test(groups = { "functional.encode", "functional.decode", "parameter" })
    public void testBody1EncodedValues() throws SecurityException, NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException, IOException, ParameterException {
        OptionalForwardCallIndicatorsImpl bci = new OptionalForwardCallIndicatorsImpl(getBody(
                OptionalForwardCallIndicatorsImpl._CUGCI_CUG_CALL_OAL, OptionalForwardCallIndicatorsImpl._SSI_ADDITIONAL_INFO,
                OptionalForwardCallIndicatorsImpl._CLIRI_REQUESTED));

        String[] methodNames = { "getClosedUserGroupCallIndicator", "isSimpleSegmentationIndicator",
                "isConnectedLineIdentityRequestIndicator" };
        Object[] expectedValues = { OptionalForwardCallIndicatorsImpl._CUGCI_CUG_CALL_OAL,
                OptionalForwardCallIndicatorsImpl._SSI_ADDITIONAL_INFO, OptionalForwardCallIndicatorsImpl._CLIRI_REQUESTED };
        super.testValues(bci, methodNames, expectedValues);
    }

    private byte[] getBody(int i, boolean _SSI, boolean _CLIRI) {

        byte v = (byte) i;
        v |= ((_SSI ? _TURN_ON : _TURN_OFF) << 2);
        v |= ((_CLIRI ? _TURN_ON : _TURN_OFF) << 7);
        return new byte[] { (byte) v };
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.isup.messages.parameters.ParameterHarness#getTestedComponent ()
     */

    public AbstractISUPParameter getTestedComponent() throws ParameterException {
        return new OptionalForwardCallIndicatorsImpl(new byte[1]);
    }

}
