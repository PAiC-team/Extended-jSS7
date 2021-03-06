
package org.restcomm.protocols.ss7.sccp.impl.message;

import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.Util;
import org.restcomm.protocols.ss7.sccp.LongMessageRuleType;
import org.restcomm.protocols.ss7.sccp.SccpProtocolVersion;
import org.restcomm.protocols.ss7.sccp.impl.SccpStackImpl;
import org.restcomm.protocols.ss7.sccp.impl.message.EncodingResultData;
import org.restcomm.protocols.ss7.sccp.impl.message.MessageFactoryImpl;
import org.restcomm.protocols.ss7.sccp.impl.message.SccpConnRsrMessageImpl;
import org.restcomm.protocols.ss7.sccp.impl.parameter.LocalReferenceImpl;
import org.restcomm.protocols.ss7.sccp.impl.parameter.ResetCauseImpl;
import org.restcomm.protocols.ss7.sccp.parameter.ResetCauseValue;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class SccpConnRsrMessageTest {

    private Logger logger;
    private SccpStackImpl stack = new SccpStackImpl("SccpConnRsrMessageTestStack", null);
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

    public byte[] getDataRsr() {
        return new byte[] { 0x0D, 0x00, 0x00, 0x01, 0x00, 0x00, 0x02, 0x09 };
    }

    @Test(groups = { "SccpMessage", "functional.decode" })
    public void testDecode() throws Exception {
        // ---- CR no optional params
        ByteArrayInputStream buf = new ByteArrayInputStream(this.getDataRsr());
        int type = buf.read();
        SccpConnRsrMessageImpl testObjectDecoded = (SccpConnRsrMessageImpl) messageFactory.createMessage(type, 1, 2, 0, buf, SccpProtocolVersion.ITU, 0);

        assertNotNull(testObjectDecoded);
        assertEquals(testObjectDecoded.getDestinationLocalReferenceNumber().getValue(), 1);
        assertEquals(testObjectDecoded.getSourceLocalReferenceNumber().getValue(), 2);
        assertEquals(testObjectDecoded.getResetCause().getValue(), ResetCauseValue.ACCESS_OPERATIONAL);
    }

    @Test(groups = { "SccpMessage", "functional.encode" })
    public void testEncode() throws Exception {
        SccpConnRsrMessageImpl original = new SccpConnRsrMessageImpl(0, 0);
        original.setDestinationLocalReferenceNumber(new LocalReferenceImpl(1));
        original.setSourceLocalReferenceNumber(new LocalReferenceImpl(2));
        original.setResetCause(new ResetCauseImpl(ResetCauseValue.ACCESS_OPERATIONAL));

        EncodingResultData encoded = original.encode(stack,LongMessageRuleType.LONG_MESSAGE_FORBBIDEN, 272, logger, false, SccpProtocolVersion.ITU);

        assertEquals(encoded.getSolidData(), this.getDataRsr());
    }
}
