package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.AbstractISUPParameter;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.TerminatingNetworkRoutingNumberImpl;
import org.testng.annotations.Test;

/**
 * Start time:21:30:13 2009-04-26<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public class TerminatingNetworkRoutingNumberTest extends ParameterHarness {

    public TerminatingNetworkRoutingNumberTest() throws IOException {
        super();
        super.goodBodies.add(getBody(false, TerminatingNetworkRoutingNumberImpl._NPI_ISDN,
                TerminatingNetworkRoutingNumberImpl._NAI_NATIONAL_SN, getSixDigits(), getSixDigits().length));
        super.goodBodies.add(getBody(false, TerminatingNetworkRoutingNumberImpl._NPI_ISDN,
                TerminatingNetworkRoutingNumberImpl._NAI_NATIONAL_SN, null, -1));
        super.goodBodies.add(getBody(false, TerminatingNetworkRoutingNumberImpl._NPI_ISDN,
                TerminatingNetworkRoutingNumberImpl._NAI_NATIONAL_SN, null, 0));

        super.badBodies.add(getBody(false, TerminatingNetworkRoutingNumberImpl._NPI_ISDN,
                TerminatingNetworkRoutingNumberImpl._NAI_NATIONAL_SN, new byte[8], 8));
        // The diff is that this is odd - only 15 digits :)
        super.goodBodies.add(getBody(true, TerminatingNetworkRoutingNumberImpl._NPI_ISDN,
                TerminatingNetworkRoutingNumberImpl._NAI_NATIONAL_SN, new byte[8], 8));
    }

    @Test(groups = { "functional.encode", "functional.decode", "parameter" })
    public void testBody1EncodedValues() throws SecurityException, NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException, IOException, ParameterException {
        TerminatingNetworkRoutingNumberImpl bci = new TerminatingNetworkRoutingNumberImpl(getBody(false,
                TerminatingNetworkRoutingNumberImpl._NPI_ISDN, TerminatingNetworkRoutingNumberImpl._NAI_NATIONAL_SN,
                getSixDigits(), getSixDigits().length));

        String[] methodNames = { "isOddFlag", "getNumberingPlanIndicator", "getNatureOfAddressIndicator", "getAddress",
                "getTnrnLengthIndicator" };
        Object[] expectedValues = { false, TerminatingNetworkRoutingNumberImpl._NPI_ISDN,
                TerminatingNetworkRoutingNumberImpl._NAI_NATIONAL_SN, getSixDigitsString(), 4 };
        super.testValues(bci, methodNames, expectedValues);
    }

    private byte[] getBody(boolean isODD, int npiIsdn, int naiNationalSn, byte[] sixDigits, int tnrL) throws IOException {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int v = 0;
        if (isODD)
            v |= 0x80;
        v |= npiIsdn << 4;
        // v |= sixDigits.length + 1;
        v |= tnrL + 1;

        bos.write(v);
        if (tnrL != -1)
            bos.write(naiNationalSn);
        if (sixDigits != null && sixDigits.length > 0)
            bos.write(sixDigits);

        return bos.toByteArray();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.isup.messages.parameters.ParameterHarness#getTestedComponent ()
     */

    public AbstractISUPParameter getTestedComponent() {
        return new TerminatingNetworkRoutingNumberImpl("10", 1, 1);
    }

}
