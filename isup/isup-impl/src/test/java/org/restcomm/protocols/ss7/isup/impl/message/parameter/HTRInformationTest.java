package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.AbstractISUPParameter;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.HTRInformationImpl;
import org.restcomm.protocols.ss7.isup.message.parameter.HTRInformation;
import org.testng.annotations.Test;

/**
 * Start time:14:11:03 2009-04-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public class HTRInformationTest extends ParameterHarness {

    /**
     * @throws IOException
     */
    public HTRInformationTest() throws IOException {
        super.badBodies.add(new byte[1]);

        super.goodBodies.add(getBody(true, HTRInformation._NAI_NATIONAL_SN, HTRInformationImpl._NPI_ISDN, getFiveDigits()));

    }

    private byte[] getBody(boolean isODD, int _NAI, int _NPI, byte[] digits) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        // we will use odd number of digits, so we leave zero as MSB

        int nai = _NAI;
        if (isODD)
            nai |= 0x01 << 7;
        int bit3 = 0;

        bit3 |= _NPI << 4;

        bos.write(nai);
        bos.write(bit3);
        bos.write(digits);
        return bos.toByteArray();
    }

    @Test(groups = { "functional.encode", "functional.decode", "parameter" })
    public void testBody1EncodedValues() throws SecurityException, NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException, IOException, ParameterException {
        HTRInformationImpl bci = new HTRInformationImpl(getBody(false, HTRInformation._NAI_NATIONAL_SN,
                HTRInformationImpl._NPI_ISDN, getSixDigits()));

        String[] methodNames = { "isOddFlag", "getNatureOfAddressIndicator", "getNumberingPlanIndicator", "getAddress" };
        Object[] expectedValues = { false, HTRInformation._NAI_NATIONAL_SN, HTRInformationImpl._NPI_ISDN, getSixDigitsString() };
        super.testValues(bci, methodNames, expectedValues);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.isup.messages.parameters.ParameterHarness#getTestedComponent ()
     */

    public AbstractISUPParameter getTestedComponent() throws ParameterException {
        return new HTRInformationImpl(new byte[3]);
    }

}
