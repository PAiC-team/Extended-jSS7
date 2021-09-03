package org.restcomm.protocols.ss7.isup.impl.message;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.restcomm.protocols.ss7.isup.impl.message.AbstractISUPMessage;
import org.restcomm.protocols.ss7.isup.message.ConfusionMessage;
import org.restcomm.protocols.ss7.isup.message.ISUPMessage;
import org.restcomm.protocols.ss7.isup.message.parameter.CauseIndicators;
import org.testng.annotations.Test;

/**
 * Start time:09:26:46 2009-04-22<br>
 * Project: mobicents-isup-stack<br>
 * Test for ACM
 *
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public class CNFTest extends MessageHarness {

    @Test(groups = { "functional.encode", "functional.decode", "message" })
    public void testTwo_Params() throws Exception {

        byte[] message = getDefaultBody();


        ConfusionMessage msg =  super.messageFactory.createCNF();
        ((AbstractISUPMessage) msg).decode(message, messageFactory,parameterFactory);

        assertNotNull(msg.getCauseIndicators());
        assertEquals(msg.getCauseIndicators().getCauseValue(), CauseIndicators._CV_ALL_CLEAR);
        
        assertEquals(msg.getCauseIndicators().getLocation(), CauseIndicators._LOCATION_USER);
        assertEquals(msg.getCauseIndicators().getCodingStandard(), CauseIndicators._CODING_STANDARD_ITUT);
    }

    protected byte[] getDefaultBody() {
        byte[] message = {
                // CIC
                0x0C, (byte) 0x0B,
                ConfusionMessage.MESSAGE_CODE, 
                    //pointer
                    0x01,
                        //CauseIndicators._PARAMETER_CODE,
                        //len
                        0x02,
                            (byte) 0x80,
                            (byte) 0x90,
                
        };
        return message;
    }

    protected ISUPMessage getDefaultMessage() {
        return super.messageFactory.createCNF();
    }
}
