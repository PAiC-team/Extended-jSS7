package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import java.lang.reflect.InvocationTargetException;

import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.AbstractISUPParameter;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.ApplicationTransportImpl;
import org.testng.annotations.Test;

/**
 * Start time:12:21:06 2009-04-23<br>
 * Project: mobicents-isup-stack<br>
 * Class to test BCI
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public class ApplicationTransportTest extends ParameterHarness {

    public ApplicationTransportTest() {
        super();

        super.badBodies.add(new byte[1]);
        super.badBodies.add(new byte[3]);

        super.goodBodies.add(getBody1());
        super.goodBodies.add(getBody2());
        super.goodBodies.add(getBody3());
    }

    private byte[] getBody1() {

       byte[] body = new byte[]{(byte)0x81};
       return body;
    }

    @Test(groups = { "functional.encode", "functional.decode", "parameter" })
    public void testBody1EncodedValues() throws SecurityException, NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException, ParameterException {
        ApplicationTransportImpl at = new ApplicationTransportImpl(getBody1());

        String[] methodNames = { "getApplicationContextIdentifier","isSendNotificationIndicator","isReleaseCallIndicator","isSegmentationIndicator",
                "getAPMSegmentationIndicator","getSegmentationLocalReference", "getEncapsulatedApplicationInformation" };
        Object[] expectedValues = { new Byte((byte)1),null,null,null,null,null,null };

        super.testValues(at, methodNames, expectedValues);
    }

    private byte[] getBody2() {
        byte[] body = new byte[]{
          //ACI
          5,
          //SNI,RCI (1 0)
          0x02,
          //SI,APM indicator (1 00 01 01)
          0x45,
          //Segmentation reference
          (byte)(0x80 | 6)
        };
        return body;
    }

    @Test(groups = { "functional.encode", "functional.decode", "parameter" })
    public void testBody2EncodedValues() throws SecurityException, NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException, ParameterException {
        ApplicationTransportImpl at = new ApplicationTransportImpl(getBody2());

        String[] methodNames = { "getApplicationContextIdentifier","isSendNotificationIndicator","isReleaseCallIndicator","isSegmentationIndicator",
                "getAPMSegmentationIndicator","getSegmentationLocalReference", "getEncapsulatedApplicationInformation" };
        Object[] expectedValues = { new Byte((byte)5),Boolean.TRUE,Boolean.FALSE,Boolean.TRUE,new Byte((byte)5),new Byte((byte)6),null };

        super.testValues(at, methodNames, expectedValues);
    }

    private byte[] getBody3() {
        byte[] body = new byte[]{
          //ACI
          2,
          //SNI,RCI (0 1)
          0x01,
          //SI,APM indicator (0 00 01 01)
          0x05,
          //Segmentation reference
          (byte)7,
          // information, we dont parse it now
          (byte)1,
          (byte)2,
          (byte)3,
          (byte)4,
          (byte)8,
          (byte)9,
        };
        return body;
    }

    @Test(groups = { "functional.encode", "functional.decode", "parameter" })
    public void testBody3EncodedValues() throws SecurityException, NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException, ParameterException {
        ApplicationTransportImpl at = new ApplicationTransportImpl(getBody3());

        String[] methodNames = { "getApplicationContextIdentifier","isSendNotificationIndicator","isReleaseCallIndicator","isSegmentationIndicator",
                "getAPMSegmentationIndicator","getSegmentationLocalReference", "getEncapsulatedApplicationInformation" };
        Object[] expectedValues = { new Byte((byte)2),Boolean.FALSE,Boolean.TRUE,Boolean.FALSE,new Byte((byte)5),new Byte((byte)7),new byte[]{(byte)1,
            (byte)2,
            (byte)3,
            (byte)4,
            (byte)8,
            (byte)9} };

        super.testValues(at, methodNames, expectedValues);
    }
    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.isup.messages.parameters.ParameterHarness#getTestedComponent ()
     */

    public AbstractISUPParameter getTestedComponent() throws ParameterException {
        return new org.restcomm.protocols.ss7.isup.impl.message.parameter.ApplicationTransportImpl();
    }

}
