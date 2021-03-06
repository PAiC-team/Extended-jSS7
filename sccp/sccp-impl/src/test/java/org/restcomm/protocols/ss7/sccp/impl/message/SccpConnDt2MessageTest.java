
package org.restcomm.protocols.ss7.sccp.impl.message;

import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.Util;
import org.restcomm.protocols.ss7.sccp.LongMessageRuleType;
import org.restcomm.protocols.ss7.sccp.SccpProtocolVersion;
import org.restcomm.protocols.ss7.sccp.impl.SccpStackImpl;
import org.restcomm.protocols.ss7.sccp.impl.message.EncodingResultData;
import org.restcomm.protocols.ss7.sccp.impl.message.MessageFactoryImpl;
import org.restcomm.protocols.ss7.sccp.impl.message.SccpConnDt2MessageImpl;
import org.restcomm.protocols.ss7.sccp.impl.parameter.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class SccpConnDt2MessageTest {

    private Logger logger;
    private SccpStackImpl stack = new SccpStackImpl("SccpConnDt2MessageTestStack", null);
    private MessageFactoryImpl messageFactory;

    @BeforeMethod
    public void setUp() {
        this.stack.setPersistDir(Util.getTmpTestDir());
        this.messageFactory = new MessageFactoryImpl(stack);
        this.logger = Logger.getLogger(SccpStackImpl.class.getCanonicalName());
    }

    @AfterMethod
    public void tearDown() {
    }

    public byte[] getDataDt2() {
        return new byte[] { 0x07, 0x00, 0x00, 0x01, 0x00, 0x00, 0x01, 0x05, 0x01, 0x02, 0x03, 0x04, 0x05 };
    }

    @Test(groups = { "SccpMessage", "functional.decode" })
    public void testDecode() throws Exception {
        ByteArrayInputStream buf = new ByteArrayInputStream(this.getDataDt2());
        int type = buf.read();
        SccpConnDt2MessageImpl testObjectDecoded = (SccpConnDt2MessageImpl) messageFactory.createMessage(type, 1, 2, 0, buf, SccpProtocolVersion.ITU, 0);

        assertNotNull(testObjectDecoded);
        assertEquals(testObjectDecoded.getDestinationLocalReferenceNumber().getValue(), 1);
        assertNotNull(testObjectDecoded.getSequencingSegmenting());
        assertEquals(testObjectDecoded.getUserData(), new byte[] {1, 2, 3, 4, 5});
    }

    @Test(groups = { "SccpMessage", "functional.encode" })
    public void testEncode() throws Exception {
        SccpConnDt2MessageImpl original = new SccpConnDt2MessageImpl(stack.getMaxDataMessage(), 0, 0);
        original.setDestinationLocalReferenceNumber(new LocalReferenceImpl(1));
        original.setSequencingSegmenting(new SequencingSegmentingImpl());
        original.setUserData(new byte[] {1, 2, 3, 4, 5});

        EncodingResultData encoded = original.encode(stack,LongMessageRuleType.LONG_MESSAGE_FORBBIDEN, 272, logger, false, SccpProtocolVersion.ITU);

        assertEquals(encoded.getSolidData(), this.getDataDt2());
    }
}
