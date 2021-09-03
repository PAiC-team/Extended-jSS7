package org.restcomm.protocols.ss7.isup.impl.message;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.restcomm.protocols.ss7.isup.impl.message.AbstractISUPMessage;
import org.restcomm.protocols.ss7.isup.message.ISUPMessage;
import org.restcomm.protocols.ss7.isup.message.NetworkResourceManagementMessage;
import org.restcomm.protocols.ss7.isup.message.parameter.EchoControlInformation;
import org.restcomm.protocols.ss7.isup.message.parameter.MessageCompatibilityInformation;
import org.testng.annotations.Test;

/**
 * Start time:09:26:46 2009-04-22<br>
 * Project: mobicents-isup-stack<br>
 * Test for ACM
 *
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public class NRMTest extends MessageHarness {

    @Test(groups = { "functional.encode", "functional.decode", "message" })
    public void testTwo_Params() throws Exception {

        byte[] message = getDefaultBody();


        NetworkResourceManagementMessage msg =  super.messageFactory.createNRM();
        ((AbstractISUPMessage) msg).decode(message, messageFactory,parameterFactory);

       

        assertNotNull(msg.getMessageCompatibilityInformation());
        MessageCompatibilityInformation mcis = msg.getMessageCompatibilityInformation();
        assertNotNull(mcis.getMessageCompatibilityInstructionIndicators());
        assertEquals(mcis.getMessageCompatibilityInstructionIndicators().length,2);
        assertNotNull(mcis.getMessageCompatibilityInstructionIndicators()[0]);
        assertNotNull(mcis.getMessageCompatibilityInstructionIndicators()[1]);
        assertEquals(mcis.getMessageCompatibilityInstructionIndicators()[0].getBandInterworkingIndicator(),2);
        assertEquals(mcis.getMessageCompatibilityInstructionIndicators()[1].getBandInterworkingIndicator(),0);
        
        assertNotNull(msg.getEchoControlInformation());
        assertEquals(msg.getEchoControlInformation().getIncomingEchoControlDeviceInformationIndicator(),2);
        assertEquals(msg.getEchoControlInformation().getIncomingEchoControlDeviceInformationRequestIndicator(),1);
        assertEquals(msg.getEchoControlInformation().getOutgoingEchoControlDeviceInformationIndicator(),1);
        assertEquals(msg.getEchoControlInformation().getOutgoingEchoControlDeviceInformationRequestIndicator(),0);
    }

    protected byte[] getDefaultBody() {
        byte[] message = {
                // CIC
                0x0C, (byte) 0x0B,
                NetworkResourceManagementMessage.MESSAGE_CODE, 
                    //pointer
                    0x01,
                    //MCI
                    MessageCompatibilityInformation._PARAMETER_CODE,
                        //len
                         0x02,
                            0x42, 
                            (byte) 0x81,
                    EchoControlInformation._PARAMETER_CODE,
                    //len
                    0x01,
                    0x49,
                0x00
                
        };
        return message;
    }

    protected ISUPMessage getDefaultMessage() {
        return super.messageFactory.createNRM();
    }
}
