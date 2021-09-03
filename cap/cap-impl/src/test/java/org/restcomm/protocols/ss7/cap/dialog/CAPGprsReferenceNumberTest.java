package org.restcomm.protocols.ss7.cap.dialog;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.dialog.CAPGprsReferenceNumberImpl;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class CAPGprsReferenceNumberTest {

    public byte[] getData() {
        return new byte[] { 48, 13, (byte) 160, 3, 2, 1, 100, (byte) 161, 6, 2, 4, 9, 0, 23, 103 };
    }

    @Test(groups = { "functional.decode", "primitives" })
    public void testDecode() throws Exception {

        byte[] data = this.getData();
        AsnInputStream ais = new AsnInputStream(data);
        CAPGprsReferenceNumberImpl elem = new CAPGprsReferenceNumberImpl();
        int tag = ais.readTag();
        elem.decodeAll(ais);
        assertNotNull(elem.getDestinationReference());
        assertNotNull(elem.getOriginationReference());
        assertEquals((int) elem.getDestinationReference(), 100);
        assertEquals((int) elem.getOriginationReference(), 151000935);

    }

    @Test(groups = { "functional.encode", "primitives" })
    public void testEncode() throws Exception {

        CAPGprsReferenceNumberImpl elem = new CAPGprsReferenceNumberImpl(100, 151000935);
        AsnOutputStream aos = new AsnOutputStream();
        elem.encodeAll(aos, Tag.CLASS_UNIVERSAL, Tag.SEQUENCE);
        assertTrue(Arrays.equals(aos.toByteArray(), this.getData()));

    }
}
