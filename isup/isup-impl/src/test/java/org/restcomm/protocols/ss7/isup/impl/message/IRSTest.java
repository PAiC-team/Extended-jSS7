package org.restcomm.protocols.ss7.isup.impl.message;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.restcomm.protocols.ss7.isup.impl.message.AbstractISUPMessage;
import org.restcomm.protocols.ss7.isup.message.ISUPMessage;
import org.restcomm.protocols.ss7.isup.message.IdentificationResponseMessage;
import org.restcomm.protocols.ss7.isup.message.parameter.MCIDResponseIndicators;
import org.restcomm.protocols.ss7.isup.message.parameter.MessageCompatibilityInformation;
import org.testng.annotations.Test;

/**
 * Start time:09:26:46 2009-04-22<br>
 * Project: mobicents-isup-stack<br>
 * Test for ACM
 *
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public class IRSTest extends MessageHarness {

    @Test(groups = { "functional.encode", "functional.decode", "message" })
    public void testTwo_Params() throws Exception {

        byte[] message = getDefaultBody();


        IdentificationResponseMessage msg = super.messageFactory.createIRS();
        ((AbstractISUPMessage) msg).decode(message, messageFactory,parameterFactory);

        assertNotNull(msg.getMCIDResponseIndicators());
        assertEquals(msg.getMCIDResponseIndicators().isHoldingProvidedIndicator(), true);
        assertEquals(msg.getMCIDResponseIndicators().isMcidIncludedIndicator(), false);

        assertNotNull(msg.getMessageCompatibilityInformation());
        MessageCompatibilityInformation mcis = msg.getMessageCompatibilityInformation();
        assertNotNull(mcis.getMessageCompatibilityInstructionIndicators());
        assertEquals(mcis.getMessageCompatibilityInstructionIndicators().length,2);
        assertNotNull(mcis.getMessageCompatibilityInstructionIndicators()[0]);
        assertNotNull(mcis.getMessageCompatibilityInstructionIndicators()[1]);
        assertEquals(mcis.getMessageCompatibilityInstructionIndicators()[0].getBandInterworkingIndicator(),2);
        assertEquals(mcis.getMessageCompatibilityInstructionIndicators()[1].getBandInterworkingIndicator(),0);
    }

    protected byte[] getDefaultBody() {
        byte[] message = {
                // CIC
                0x0C, (byte) 0x0B,
                IdentificationResponseMessage.MESSAGE_CODE, 
                    //pointer
                    0x01,
                    MCIDResponseIndicators._PARAMETER_CODE,
                        //len
                        0x01,
                            0x02,
                    //MCI
                    MessageCompatibilityInformation._PARAMETER_CODE,
                        //len
                         0x02,
                            0x42, 
                            (byte) 0x81,
                0x00
                
        };
        return message;
    }

    protected ISUPMessage getDefaultMessage() {
        return super.messageFactory.createIRS();
    }
}
