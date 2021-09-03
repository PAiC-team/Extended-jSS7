package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;

import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.AbstractISUPParameter;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.GenericNotificationIndicatorImpl;
import org.testng.annotations.Test;

/**
 * Start time:11:34:01 2009-04-24<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public class GenericNotificationIndicatorTest extends ParameterHarness {

    public GenericNotificationIndicatorTest() {
        super();
        super.goodBodies.add(new byte[] { 67, 12, 13, 14, 15, 16, 17, (byte) (18 | (0x01 << 7)) });
        super.badBodies.add(new byte[1]);
    }

    private byte[] getBody() {
        return super.goodBodies.get(0);
    }

    @Test(groups = { "functional.encode", "functional.decode", "parameter" })
    public void testBody1EncodedValues() throws IOException, ParameterException {
        GenericNotificationIndicatorImpl eci = new GenericNotificationIndicatorImpl(getBody());
        byte[] body = getBody();
        byte[] encoded = eci.encode();
        boolean equal = Arrays.equals(body, encoded);
        assertTrue(equal, "Body index: \n" + makeCompare(body, encoded));

    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.isup.messages.parameters.ParameterHarness#getTestedComponent ()
     */

    public AbstractISUPParameter getTestedComponent() throws ParameterException {
        return new GenericNotificationIndicatorImpl(new byte[2]);
    }

}
