package org.restcomm.protocols.ss7.isup.impl.message;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.restcomm.protocols.ss7.isup.impl.message.AbstractISUPMessage;
import org.restcomm.protocols.ss7.isup.message.ISUPMessage;
import org.restcomm.protocols.ss7.isup.message.ResumeMessage;
import org.restcomm.protocols.ss7.isup.message.parameter.CallReference;
import org.testng.annotations.Test;

/**
 * Start time:09:26:46 2009-04-22<br>
 * Project: mobicents-isup-stack<br>
 * Test for ACM
 *
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public class RESTest extends MessageHarness {

    @Test(groups = { "functional.encode", "functional.decode", "message" })
    public void testTwo_Params() throws Exception {

        byte[] message = getDefaultBody();


        ResumeMessage msg =  super.messageFactory.createRES();
        ((AbstractISUPMessage) msg).decode(message, messageFactory,parameterFactory);

        assertNotNull(msg.getSuspendResumeIndicators());
        assertEquals(msg.getSuspendResumeIndicators().isSuspendResumeIndicator(), true);
        
        assertNotNull(msg.getCallReference());
        assertEquals(msg.getCallReference().getCallIdentity(), 1436);
        assertEquals(msg.getCallReference().getSignalingPointCode(), 9743);
    }

    protected byte[] getDefaultBody() {
        byte[] message = {
                // CIC
                0x0C, (byte) 0x0B,
                ResumeMessage.MESSAGE_CODE, 
                    //suspend/resume
                    0x01,
                    //pointer
                    0x01,
                    CallReference._PARAMETER_CODE,
                        //len
                        0x05,
                            0x00,
                            0x05,
                            (byte) 0x9c,
                            0x0f,
                            0x26,
                0x00
                
        };
        return message;
    }

    protected ISUPMessage getDefaultMessage() {
        return super.messageFactory.createRES();
    }
}
