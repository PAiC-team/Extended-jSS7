
package org.restcomm.protocols.ss7.isup.impl.message;

import org.restcomm.protocols.ss7.isup.impl.message.AbstractISUPMessage;
import org.restcomm.protocols.ss7.isup.message.ISUPMessage;
import org.restcomm.protocols.ss7.isup.message.ReleaseCompleteMessage;
import org.restcomm.protocols.ss7.isup.message.parameter.CauseIndicators;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Start time:15:07:07 2009-07-17<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public class RLCTest extends MessageHarness {

    protected byte[] getDefaultBody() {
        // FIXME: for now we strip MTP part
        byte[] message = {

        0x0C, (byte) 0x0B, 0x10, 0x00

        };

        return message;
    }

    protected ISUPMessage getDefaultMessage() {
        return super.messageFactory.createRLC();
    }

    @Test(groups = { "functional.encode", "functional.decode", "message" })
    public void testCauseIndicators() throws Exception {
        final CauseIndicators causeIndicators = super.parameterFactory.createCauseIndicators();
        causeIndicators.setCauseValue(CauseIndicators._CV_ALL_CLEAR);
        final ReleaseCompleteMessage releaseCompleteMessage = super.messageFactory.createRLC(1);
        releaseCompleteMessage.setCauseIndicators(causeIndicators);
        releaseCompleteMessage.setSls(2);
        final byte[] encoded = ((AbstractISUPMessage) releaseCompleteMessage).encode();
        final AbstractISUPMessage msg = (AbstractISUPMessage) getDefaultMessage();
        msg.decode(encoded, messageFactory,parameterFactory);
        final ReleaseCompleteMessage decodedRLC = (ReleaseCompleteMessage) msg;
        Assert.assertNotNull(decodedRLC.getCauseIndicators(), "No Cause Indicators present!");
        Assert.assertEquals(decodedRLC.getCauseIndicators().getCauseValue(), CauseIndicators._CV_ALL_CLEAR, "Wrong CauseValue");
    }
}
