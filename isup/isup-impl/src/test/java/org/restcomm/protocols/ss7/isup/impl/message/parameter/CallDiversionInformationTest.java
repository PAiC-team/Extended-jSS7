package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.AbstractISUPParameter;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.CallDiversionInformationImpl;
import org.testng.annotations.Test;

/**
 * Start time:13:37:14 2009-04-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>ca </a>
 */
public class CallDiversionInformationTest extends ParameterHarness {

    public CallDiversionInformationTest() {
        super();

        super.badBodies.add(new byte[0]);
        super.badBodies.add(new byte[2]);

        super.goodBodies.add(getBody1());

    }

    private byte[] getBody1() {
        // Notif sub options : 010 - presentation allowed
        // redirect reason : 0100 - deflection during alerting
        // whole : 00100010
        return new byte[] { 0x22 };
    }

    @Test(groups = { "functional.encode", "functional.decode", "parameter" })
    public void testBody1EncodedValues() throws ParameterException {
        CallDiversionInformationImpl cdi = new CallDiversionInformationImpl(getBody1());
        String[] methodNames = { "getNotificationSubscriptionOptions", "getRedirectingReason" };
        Object[] expectedValues = { cdi._NSO_P_A_WITH_RN, cdi._REDIRECTING_REASON_DDA };
        super.testValues(cdi, methodNames, expectedValues);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.isup.messages.parameters.ParameterHarness#getTestedComponent()
     */

    public AbstractISUPParameter getTestedComponent() throws ParameterException {
        return new CallDiversionInformationImpl(new byte[1]);
    }

}
