
package org.restcomm.protocols.ss7.sccp.impl.message;

import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.Util;
import org.restcomm.protocols.ss7.sccp.LongMessageRuleType;
import org.restcomm.protocols.ss7.sccp.SccpProtocolVersion;
import org.restcomm.protocols.ss7.sccp.impl.SccpStackImpl;
import org.restcomm.protocols.ss7.sccp.impl.message.EncodingResultData;
import org.restcomm.protocols.ss7.sccp.impl.message.MessageFactoryImpl;
import org.restcomm.protocols.ss7.sccp.impl.message.SccpConnRlcMessageImpl;
import org.restcomm.protocols.ss7.sccp.impl.parameter.LocalReferenceImpl;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class SccpConnRlcMessageTest {

    private Logger logger;
    private SccpStackImpl stack = new SccpStackImpl("SccpConnRlcMessageTestStack", null);
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

    public byte[] getDataRlc() {
        return new byte[]{ 0x05, 0x00, 0x00, 0x02, 0x00, 0x00, 0x03 };
    }

    @Test(groups = {"SccpMessage", "functional.decode"})
    public void testDecode() throws Exception {
        ByteArrayInputStream buf = new ByteArrayInputStream(this.getDataRlc());
        int type = buf.read();
        SccpConnRlcMessageImpl testObjectDecoded = (SccpConnRlcMessageImpl) messageFactory.createMessage(type, 1, 2, 0, buf, SccpProtocolVersion.ITU, 0);

        assertNotNull(testObjectDecoded);
        assertEquals(testObjectDecoded.getDestinationLocalReferenceNumber().getValue(), 2);
        assertEquals(testObjectDecoded.getSourceLocalReferenceNumber().getValue(), 3);
    }

    @Test(groups = {"SccpMessage", "functional.encode"})
    public void testEncode() throws Exception {
        SccpConnRlcMessageImpl original = new SccpConnRlcMessageImpl(0, 0);
        original.setDestinationLocalReferenceNumber(new LocalReferenceImpl(2));
        original.setSourceLocalReferenceNumber(new LocalReferenceImpl(3));

        EncodingResultData encoded = original.encode(stack, LongMessageRuleType.LONG_MESSAGE_FORBBIDEN, 272, logger, false, SccpProtocolVersion.ITU);

        assertEquals(encoded.getSolidData(), this.getDataRlc());
    }
}
