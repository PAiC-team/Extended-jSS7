package org.restcomm.protocols.ss7.isup.impl.message;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.restcomm.protocols.ss7.isup.impl.message.AbstractISUPMessage;
import org.restcomm.protocols.ss7.isup.message.ISUPMessage;
import org.restcomm.protocols.ss7.isup.message.SubsequentAddressMessage;
import org.testng.annotations.Test;

/**
 * Start time:09:26:46 2009-04-22<br>
 * Project: mobicents-isup-stack<br>
 * Test for ACM
 *
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public class SAMTest extends MessageHarness {

    @Test(groups = { "functional.encode", "functional.decode", "message" })
    public void testTwo_Params() throws Exception {

        byte[] message = getDefaultBody();


        SubsequentAddressMessage msg =  super.messageFactory.createSAM();
        ((AbstractISUPMessage) msg).decode(message, messageFactory,parameterFactory);

        assertNotNull(msg.getSubsequentNumber());
        assertEquals(msg.getSubsequentNumber().isOddFlag(), false);
        assertEquals(msg.getSubsequentNumber().getAddress(), "380683");
    }

    protected byte[] getDefaultBody() {
        byte[] message = {
                // CIC
                0x0C, (byte) 0x0B,
                SubsequentAddressMessage.MESSAGE_CODE, 
                    //pointer to variable len
                    0x01,
                    //subsequent address
                    //SubsequentNumber._PARAMETER_CODE,
                    //len
                    0x04,
                        0x00,
                        (byte) 0x83,
                        0x60,
                        0x38,
                
        };
        return message;
    }

    protected ISUPMessage getDefaultMessage() {
        return super.messageFactory.createSAM();
    }
}
