package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import static org.testng.Assert.fail;

import java.io.IOException;

import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.AbstractISUPParameter;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.NetworkManagementControlsImpl;
import org.testng.annotations.Test;

/**
 * Start time:13:20:04 2009-04-26<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public class NetworkManagementControlsTest extends ParameterHarness {

    public NetworkManagementControlsTest() {
        super();

        super.goodBodies.add(new byte[1]);
        super.goodBodies.add(new byte[] { 0x0E });
        super.goodBodies.add(new byte[] { 0x0E, 32, 45, 0x0A });
    }

    @Test(groups = { "functional.encode", "functional.decode", "parameter" })
    public void testBody1EncodedValues() throws IOException, ParameterException {

        boolean[] bools = new boolean[] { true, true, false, true, false, true, true };
        NetworkManagementControlsImpl eci = new NetworkManagementControlsImpl(getBody1(bools));
        byte[] encoded = eci.encode();
        for (int index = 0; index < encoded.length; index++) {
            if (bools[index] != eci.isTARControlEnabled(encoded[index])) {
                fail("Failed to get TAR bits, at index: " + index);
            }

            if (index == encoded.length - 1) {
                if (((encoded[index] >> 7) & 0x01) != 1) {
                    fail("Last byte must have MSB turned on to indicate last byte, this one does not.");
                }
            }
        }

    }

    private byte[] getBody1(boolean[] tarEnabled) {
        boolean[] bools = new boolean[] { true, true, false, true, false, true, true };
        NetworkManagementControlsImpl eci = new NetworkManagementControlsImpl();
        byte[] b = new byte[tarEnabled.length];
        for (int index = 0; index < tarEnabled.length; index++) {
            b[index] = eci.createTAREnabledByte(tarEnabled[index]);
        }
        return b;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.isup.messages.parameters.ParameterHarness#getTestedComponent ()
     */

    public AbstractISUPParameter getTestedComponent() throws ParameterException {
        return new NetworkManagementControlsImpl(new byte[1]);
    }

}
