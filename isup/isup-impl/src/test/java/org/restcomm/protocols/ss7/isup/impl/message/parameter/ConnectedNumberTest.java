package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.AbstractISUPParameter;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.ConnectedNumberImpl;
import org.restcomm.protocols.ss7.isup.message.parameter.ConnectedNumber;
import org.testng.annotations.Test;

/**
 * Start time:14:11:03 2009-04-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public class ConnectedNumberTest extends ParameterHarness {

    /**
     * @throws IOException
     */
    public ConnectedNumberTest() throws IOException {
//        super.badBodies.add(new byte[1]);
//
        super.goodBodies.add(getBody1());
        super.goodBodies.add(getBody2());
        super.goodBodies.add(getBody3());
    }

    private byte[] getBody1() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        // we will use odd number of digits, so we leave zero as MSB
        // 0 because - _APRI_NOT_AVAILABLE
        int v = 0;
        bos.write(v);
        v = 0;
        v = ConnectedNumberImpl._APRI_NOT_AVAILABLE << 2;
        v |= ConnectedNumberImpl._SI_NETWORK_PROVIDED;
        bos.write(v & 0x7F);
        return bos.toByteArray();
    }

    private byte[] getBody2() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        // We have odd number
        int v = ConnectedNumber._NAI_SUBSCRIBER_NUMBER | (0x01 << 7);
        bos.write(v);
        v = 0;
        v = ConnectedNumberImpl._NPI_TELEX << 4;
        v |= ConnectedNumberImpl._APRI_ALLOWED << 2;
        v |= ConnectedNumberImpl._SI_NETWORK_PROVIDED;
        bos.write(v & 0x7F);
        bos.write(super.getFiveDigits());
        return bos.toByteArray();
    }

    private byte[] getBody3() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        // We have odd number
        int v = ConnectedNumber._NAI_SUBSCRIBER_NUMBER;
        bos.write(v);
        v = 0;
        v = ConnectedNumberImpl._NPI_TELEX << 4;
        v |= ConnectedNumberImpl._APRI_ALLOWED << 2;
        v |= ConnectedNumberImpl._SI_NETWORK_PROVIDED;
        bos.write(v & 0x7F);
        bos.write(super.getSixDigits());
        return bos.toByteArray();
    }

    @Test(groups = { "functional.encode", "functional.decode", "parameter" })
    public void testBody1EncodedValues() throws SecurityException, NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException, IOException, ParameterException {
        ConnectedNumberImpl bci = new ConnectedNumberImpl(getBody1());

        String[] methodNames = { "getNumberingPlanIndicator", "getAddressRepresentationRestrictedIndicator",
                "getNatureOfAddressIndicator", "getScreeningIndicator", "isOddFlag", "getAddress" };
        Object[] expectedValues = { 0, ConnectedNumberImpl._APRI_NOT_AVAILABLE,
                0, ConnectedNumberImpl._SI_NETWORK_PROVIDED, false,
                null };
        super.testValues(bci, methodNames, expectedValues);
    }

    @Test(groups = { "functional.encode", "functional.decode", "parameter" })
    public void testBody2EncodedValues() throws SecurityException, NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException, IOException, ParameterException {
        ConnectedNumberImpl bci = new ConnectedNumberImpl(getBody2());

        String[] methodNames = { "getNumberingPlanIndicator", "getAddressRepresentationRestrictedIndicator",
                "getNatureOfAddressIndicator", "getScreeningIndicator", "isOddFlag", "getAddress" };
        Object[] expectedValues = { ConnectedNumberImpl._NPI_TELEX, ConnectedNumberImpl._APRI_ALLOWED,
                ConnectedNumber._NAI_SUBSCRIBER_NUMBER, ConnectedNumberImpl._SI_NETWORK_PROVIDED, true,
                super.getFiveDigitsString() };
        super.testValues(bci, methodNames, expectedValues);
    }

    @Test(groups = { "functional.encode", "functional.decode", "parameter" })
    public void testBody3EncodedValues() throws SecurityException, NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException, IOException, ParameterException {
        ConnectedNumberImpl bci = new ConnectedNumberImpl(getBody3());

        String[] methodNames = { "getNumberingPlanIndicator", "getAddressRepresentationRestrictedIndicator",
                "getNatureOfAddressIndicator", "getScreeningIndicator", "isOddFlag", "getAddress" };
        Object[] expectedValues = { ConnectedNumberImpl._NPI_TELEX, ConnectedNumberImpl._APRI_ALLOWED,
                ConnectedNumber._NAI_SUBSCRIBER_NUMBER, ConnectedNumberImpl._SI_NETWORK_PROVIDED, false,
                super.getSixDigitsString() };
        super.testValues(bci, methodNames, expectedValues);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.isup.messages.parameters.ParameterHarness#getTestedComponent ()
     */

    public AbstractISUPParameter getTestedComponent() {
        return new ConnectedNumberImpl(0, null, 0, 0, 0);
    }

}
