
package org.restcomm.protocols.ss7.sccp.impl.message;

import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.Util;
import org.restcomm.protocols.ss7.sccp.LongMessageRuleType;
import org.restcomm.protocols.ss7.sccp.SccpProtocolVersion;
import org.restcomm.protocols.ss7.sccp.impl.SccpStackImpl;
import org.restcomm.protocols.ss7.sccp.impl.message.EncodingResultData;
import org.restcomm.protocols.ss7.sccp.impl.message.MessageFactoryImpl;
import org.restcomm.protocols.ss7.sccp.impl.message.SccpConnAkMessageImpl;
import org.restcomm.protocols.ss7.sccp.impl.parameter.CreditImpl;
import org.restcomm.protocols.ss7.sccp.impl.parameter.LocalReferenceImpl;
import org.restcomm.protocols.ss7.sccp.impl.parameter.ReceiveSequenceNumberImpl;
import org.restcomm.protocols.ss7.sccp.impl.parameter.SequenceNumberImpl;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class SccpConnAkMessageTest {

    private Logger logger;
    private SccpStackImpl stack = new SccpStackImpl("SccpConnAkMessageTestStack", null);
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

    public byte[] getDataAk() {
        return new byte[] { 0x08, 0x00, 0x00, 0x01, 0x00, 0x64 };
    }

    @Test(groups = { "SccpMessage", "functional.decode" })
    public void testDecode() throws Exception {
        ByteArrayInputStream buf = new ByteArrayInputStream(this.getDataAk());
        int type = buf.read();
        SccpConnAkMessageImpl testObjectDecoded = (SccpConnAkMessageImpl) messageFactory.createMessage(type, 1, 2, 0, buf, SccpProtocolVersion.ITU, 0);

        assertNotNull(testObjectDecoded);
        assertEquals(testObjectDecoded.getDestinationLocalReferenceNumber().getValue(), 1);
        assertEquals(testObjectDecoded.getReceiveSequenceNumber().getNumber().getValue(), 0);
        assertEquals(testObjectDecoded.getCredit().getValue(), 100);
    }

    @Test(groups = { "SccpMessage", "functional.encode" })
    public void testEncode() throws Exception {
        SccpConnAkMessageImpl original = new SccpConnAkMessageImpl(0, 0);
        original.setDestinationLocalReferenceNumber(new LocalReferenceImpl(1));
        original.setReceiveSequenceNumber(new ReceiveSequenceNumberImpl(new SequenceNumberImpl((byte)0)));
        original.setCredit(new CreditImpl(100));


        EncodingResultData encoded = original.encode(stack,LongMessageRuleType.LONG_MESSAGE_FORBBIDEN, 272, logger, false, SccpProtocolVersion.ITU);

        assertEquals(encoded.getSolidData(), this.getDataAk());
    }
}
