package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import java.lang.reflect.InvocationTargetException;

import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.AbstractISUPParameter;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.CallOfferingTreatmentIndicatorsImpl;
import org.testng.annotations.Test;

/**
 * Start time:12:21:06 2009-04-23<br>
 * Project: mobicents-isup-stack<br>
 * Class to test BCI
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public class CallOfferingTreatmentIndicatorsTest extends ParameterHarness {

    public CallOfferingTreatmentIndicatorsTest() {
        super();

        super.goodBodies.add(getBody1());
    }

    private byte[] getBody1() {

       byte[] body = new byte[]{
               CallOfferingTreatmentIndicatorsImpl._CTBOI_COA,
               CallOfferingTreatmentIndicatorsImpl._CTBOI_NO_INDICATION
       };
       return body;
    }

    @Test(groups = { "functional.encode", "functional.decode", "parameter" })
    public void testBody1EncodedValues() throws SecurityException, NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException, ParameterException {
        CallOfferingTreatmentIndicatorsImpl at = new CallOfferingTreatmentIndicatorsImpl(getBody1());

        String[] methodNames = { "getCallOfferingTreatmentIndicators" };
        Object[] expectedValues = { new byte[]{CallOfferingTreatmentIndicatorsImpl._CTBOI_COA,
                CallOfferingTreatmentIndicatorsImpl._CTBOI_NO_INDICATION} };

        super.testValues(at, methodNames, expectedValues);
    }

    
    public AbstractISUPParameter getTestedComponent() throws ParameterException {
        return new org.restcomm.protocols.ss7.isup.impl.message.parameter.CallOfferingTreatmentIndicatorsImpl();
    }

}
