package org.restcomm.protocols.ss7.isup.impl.message;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.restcomm.protocols.ss7.isup.impl.message.AbstractISUPMessage;
import org.restcomm.protocols.ss7.isup.message.ISUPMessage;
import org.restcomm.protocols.ss7.isup.message.InformationMessage;
import org.restcomm.protocols.ss7.isup.message.parameter.CallReference;
import org.testng.annotations.Test;

/**
 * Start time:09:26:46 2009-04-22<br>
 * Project: mobicents-isup-stack<br>
 * Test for ACM
 *
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public class INFTest extends MessageHarness {

    @Test(groups = { "functional.encode", "functional.decode", "message" })
    public void testTwo_Params() throws Exception {

        byte[] message = getDefaultBody();


        InformationMessage msg = super.messageFactory.createINF();
        ((AbstractISUPMessage) msg).decode(message, messageFactory,parameterFactory);

        assertNotNull(msg.getInformationIndicators());
        assertEquals(msg.getInformationIndicators().isChargeInformationResponseIndicator(), true);
        assertEquals(msg.getInformationIndicators().isCallingPartysCategoryResponseIndicator(), true);
        assertEquals(msg.getInformationIndicators().isHoldProvidedIndicator(), false);
        assertEquals(msg.getInformationIndicators().isSolicitedInformationIndicator(), true);
        assertEquals(msg.getInformationIndicators().getCallingPartyAddressResponseIndicator(), 3);
        
        assertNotNull(msg.getCallReference());
        assertEquals(msg.getCallReference().getCallIdentity(), 805240);
        assertEquals(msg.getCallReference().getSignalingPointCode(), 14409);
    }

    protected byte[] getDefaultBody() {
        byte[] message = {
                // CIC
                0x0C, (byte) 0x0B,
                InformationMessage.MESSAGE_CODE,
                    //information indicators
                    (byte) 
                    0xE3,
                    (byte) 0xA0,
                    //pointer
                    0x01,
                    CallReference._PARAMETER_CODE,
                    //len
                        0x05,
                            12,
                            73,
                            120,
                            73,
                            120& 0x3F,
                    0x00
                
        };
        return message;
    }

    protected ISUPMessage getDefaultMessage() {
        return super.messageFactory.createINF();
    }
}
