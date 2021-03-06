
package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.AbstractISUPParameter;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.CallTransferNumberImpl;
import org.testng.annotations.Test;

/**
 * 
 * @author baranowb
 * 
 */
public class CallTransferNumberTest extends ParameterHarness {
    /**
     * @throws IOException
     */
    public CallTransferNumberTest() throws IOException {
        super.badBodies.add(new byte[1]);

        super.goodBodies.add(getBody1());
        super.goodBodies.add(getBody2());
    }

    private byte[] getBody1() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        // we will use odd number of digits, so we leave zero as MSB
        bos.write(CallTransferNumberImpl._NAI_NATIONAL_SN);
        bos.write((CallTransferNumberImpl._NPI_ISDN << 4) | (CallTransferNumberImpl._APRI_RESTRICTED << 2)
                | (CallTransferNumberImpl._SI_USER_PROVIDED_VERIFIED_FAILED));
        bos.write(getEightDigits());
        return bos.toByteArray();
    }

    private byte[] getBody2() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bos.write(0x80 | CallTransferNumberImpl._NAI_NRNCWCDN);
        bos.write((CallTransferNumberImpl._NPI_TELEX << 4) | (CallTransferNumberImpl._APRI_ALLOWED << 2) | (CallTransferNumberImpl._SI_NETWORK_PROVIDED));
        bos.write(getSevenDigits());
        return bos.toByteArray();
    }

    @Test(groups = { "functional.encode", "functional.decode", "parameter" })
    public void testBody1EncodedValues() throws SecurityException, NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException, IOException, ParameterException {
        CallTransferNumberImpl bci = new CallTransferNumberImpl(getBody1());

        String[] methodNames = { 
                "isOddFlag","getNatureOfAddressIndicator",
                "getNumberingPlanIndicator", "getAddressRepresentationRestrictedIndicator","getScreeningIndicator", 
                "getAddress" };
        Object[] expectedValues = {
                false,CallTransferNumberImpl._NAI_NATIONAL_SN,
                CallTransferNumberImpl._NPI_ISDN,CallTransferNumberImpl._APRI_RESTRICTED,CallTransferNumberImpl._SI_USER_PROVIDED_VERIFIED_FAILED,
                getEightDigitsString()};
        super.testValues(bci, methodNames, expectedValues);
    }

    @Test(groups = { "functional.encode", "functional.decode", "parameter" })
    public void testBody2EncodedValues() throws SecurityException, NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException, IOException, ParameterException {
        CallTransferNumberImpl bci = new CallTransferNumberImpl(getBody2());


        String[] methodNames = { 
                "isOddFlag","getNatureOfAddressIndicator",
                "getNumberingPlanIndicator", "getAddressRepresentationRestrictedIndicator","getScreeningIndicator", 
                "getAddress" };
        Object[] expectedValues = {
                true,CallTransferNumberImpl._NAI_NRNCWCDN,
                CallTransferNumberImpl._NPI_TELEX,CallTransferNumberImpl._APRI_ALLOWED,CallTransferNumberImpl._SI_NETWORK_PROVIDED,
                getSevenDigitsString()};
        super.testValues(bci, methodNames, expectedValues);
    }

    @Override
    public AbstractISUPParameter getTestedComponent() throws ParameterException {
        return new CallTransferNumberImpl();
    }
}
