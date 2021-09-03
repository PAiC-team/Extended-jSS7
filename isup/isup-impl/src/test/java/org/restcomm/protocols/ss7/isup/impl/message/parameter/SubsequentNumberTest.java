package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.AbstractISUPParameter;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.SubsequentNumberImpl;
import org.testng.annotations.Test;

/**
 * Start time:14:11:03 2009-04-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public class SubsequentNumberTest extends ParameterHarness {

    /**
     * @throws IOException
     */
    public SubsequentNumberTest() throws IOException {
        super.badBodies.add(new byte[1]);

        super.goodBodies.add(getBody(false, getSixDigits()));
        super.goodBodies.add(getBody(true, getFiveDigits()));
        // This will fail, cause this body has APRI allowed, so hardcoded body
        // does nto match encoded body :)
        // super.goodBodies.add(getBody2());
    }

    @Test(groups = { "functional.encode", "functional.decode", "parameter" })
    public void testBody1EncodedValues() throws SecurityException, NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException, IOException, ParameterException {
        SubsequentNumberImpl bci = new SubsequentNumberImpl(getBody(false, getSixDigits()));

        String[] methodNames = { "isOddFlag", "getAddress" };
        Object[] expectedValues = { false, getSixDigitsString() };
        super.testValues(bci, methodNames, expectedValues);
    }

    @Test(groups = { "functional.encode", "functional.decode", "parameter" })
    public void testBody2EncodedValues() throws SecurityException, NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException, IOException, ParameterException {
        SubsequentNumberImpl bci = new SubsequentNumberImpl(getBody(true, getFiveDigits()));

        String[] methodNames = { "isOddFlag", "getAddress" };
        Object[] expectedValues = { true, getFiveDigitsString() };
        super.testValues(bci, methodNames, expectedValues);
    }

    private byte[] getBody(boolean isODD, byte[] digits) throws IOException {
        int b = 0;
        if (isODD) {
            b |= 0x01 << 7;
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bos.write(b);
        bos.write(digits);

        return bos.toByteArray();
    }

    public AbstractISUPParameter getTestedComponent() {
        return new SubsequentNumberImpl();
    }

}
