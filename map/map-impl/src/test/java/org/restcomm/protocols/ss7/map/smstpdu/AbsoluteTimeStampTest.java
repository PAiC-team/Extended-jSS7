package org.restcomm.protocols.ss7.map.smstpdu;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Arrays;

import org.restcomm.protocols.ss7.map.smstpdu.AbsoluteTimeStampImpl;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class AbsoluteTimeStampTest {

    public byte[] getData() {
        return new byte[] { 112, 80, 81, 81, 0, 20, 33 };
    }

    @Test(groups = { "functional.decode", "smstpdu" })
    public void testDecode() throws Exception {

        InputStream stm = new ByteArrayInputStream(this.getData());
        AbsoluteTimeStampImpl impl = AbsoluteTimeStampImpl.createMessage(stm);
        assertEquals(impl.getYear(), 7);
        assertEquals(impl.getMonth(), 5);
        assertEquals(impl.getDay(), 15);
        assertEquals(impl.getHour(), 15);
        assertEquals(impl.getMinute(), 0);
        assertEquals(impl.getSecond(), 41);
        assertEquals(impl.getTimeZone(), 12);
    }

    @Test(groups = { "functional.encode", "smstpdu" })
    public void testEncode() throws Exception {

        AbsoluteTimeStampImpl impl = new AbsoluteTimeStampImpl(7, 5, 15, 15, 0, 41, 12);
        ByteArrayOutputStream stm = new ByteArrayOutputStream();
        impl.encodeData(stm);
        assertTrue(Arrays.equals(stm.toByteArray(), this.getData()));
    }
}
