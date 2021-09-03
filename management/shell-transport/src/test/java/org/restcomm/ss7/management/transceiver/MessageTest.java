package org.restcomm.ss7.management.transceiver;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.nio.ByteBuffer;

import org.restcomm.ss7.management.transceiver.Message;
import org.restcomm.ss7.management.transceiver.MessageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MessageTest {
    private MessageFactory messageFactory = new MessageFactory();

    public MessageTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void testMessage() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(8192);

        Message msg = messageFactory.createMessage("Some message");
        msg.encode(buffer);

        buffer.flip();

        Message msg1 = messageFactory.createMessage(buffer);

        assertEquals(msg.toString(), msg1.toString());
    }
}
