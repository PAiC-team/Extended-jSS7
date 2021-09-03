package org.restcomm.protocols.ss7.isup.impl.message;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.fail;

import org.restcomm.protocols.ss7.isup.impl.message.AbstractISUPMessage;
import org.restcomm.protocols.ss7.isup.message.CircuitGroupQueryResponseMessage;
import org.restcomm.protocols.ss7.isup.message.ISUPMessage;
import org.restcomm.protocols.ss7.isup.message.parameter.CallReference;
import org.restcomm.protocols.ss7.isup.message.parameter.CircuitStateIndicator;
import org.restcomm.protocols.ss7.isup.message.parameter.RangeAndStatus;
import org.testng.annotations.Test;

/**
 * Start time:09:26:46 2009-04-22<br>
 * Project: mobicents-isup-stack<br>
 * Test for CQR
 *
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public class CQRTest extends MessageHarness {

    @Test(groups = { "functional.encode", "functional.decode", "message" })
    public void testTwo_Params() throws Exception {
        byte[] message = getDefaultBody();
        // CircuitGroupQueryResponseMessage grs=new CircuitGroupQueryResponseMessageImpl(this,message);
        CircuitGroupQueryResponseMessage grs = super.messageFactory.createCQR();
        ((AbstractISUPMessage) grs).decode(message, messageFactory,parameterFactory);

        try {
            RangeAndStatus RS = (RangeAndStatus) grs.getParameter(RangeAndStatus._PARAMETER_CODE);
            assertNotNull(RS, "Range And Status retrun is null, it shoul not be");
            if (RS == null)
                return;
            byte range = RS.getRange();
            assertEquals(range, 0x01, "Range is wrong,");
            byte[] b = RS.getStatus();
            assertNull(b, "RangeAndStatus.getRange() is not null");

        } catch (Exception e) {
            e.printStackTrace();
            fail("Failed on get parameter[" + CallReference._PARAMETER_CODE + "]:" + e);
        }
        try {
            CircuitStateIndicator CSI = (CircuitStateIndicator) grs.getParameter(CircuitStateIndicator._PARAMETER_CODE);
            assertNotNull(CSI, "Circuit State Indicator return is null, it should not be");
            if (CSI == null)
                return;
            assertNotNull(CSI.getCircuitState(), "CircuitStateIndicator getCircuitState return is null, it should not be");
            byte[] circuitState = CSI.getCircuitState();
            assertEquals(circuitState.length, 3, "CircuitStateIndicator.getCircuitState() length is nto correct");
            assertEquals(CSI.getMaintenanceBlockingState(circuitState[0]), 1,
                    "CircuitStateIndicator.getCircuitState()[0] value is not correct");
            assertEquals(CSI.getMaintenanceBlockingState(circuitState[1]), 2,
                    "CircuitStateIndicator.getCircuitState()[1] value is not correct");
            assertEquals(CSI.getMaintenanceBlockingState(circuitState[2]), 3,
                    "CircuitStateIndicator.getCircuitState()[2] value is not correct");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Failed on get parameter[" + CallReference._PARAMETER_CODE + "]:" + e);
        }

    }

    protected byte[] getDefaultBody() {
        // FIXME: for now we strip MTP part
        byte[] message = {

        0x0C, (byte) 0x0B, CircuitGroupQueryResponseMessage.MESSAGE_CODE

        , 0x02 // ptr to variable part
                , 0x03

                // no optional, so no pointer
                // RangeAndStatus._PARAMETER_CODE
                , 0x01, 0x01
                // CircuitStateIndicator
                , 0x03, 0x01, 0x02, 0x03

        };

        return message;
    }

    protected ISUPMessage getDefaultMessage() {
        return super.messageFactory.createCQR();
    }
}
