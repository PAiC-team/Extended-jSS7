
package org.restcomm.protocols.ss7.map.primitives;

import static org.testng.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import javolution.xml.XMLObjectReader;
import javolution.xml.XMLObjectWriter;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.map.primitives.GlobalCellIdImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class GlobalCellIdTest {

    public byte[] getData() {
        return new byte[] { 4, 7, 82, (byte) 240, 16, 17, 92, 13, 5 };
    };

    public byte[] getDataVal() {
        return new byte[] { 82, (byte) 240, 16, 17, 92, 13, 5 };
    };

    public byte[] getData2() {
        return new byte[] { 4, 5, 82, (byte) 240, 16, 17, 92 };
    };

    @Test(groups = { "functional.decode", "primitives" })
    public void testDecode() throws Exception {

        byte[] data = this.getData();

        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();

        GlobalCellIdImpl prim = new GlobalCellIdImpl();
        prim.decodeAll(asn);

        assertNotNull(prim.getData());
        assertTrue(Arrays.equals(getDataVal(), prim.getData()));

        assertEquals(prim.getMcc(), 250);
        assertEquals(prim.getMnc(), 1);
        assertEquals(prim.getLac(), 4444);
        assertEquals(prim.getCellId(), 3333);


        data = this.getData2();

        asn = new AsnInputStream(data);
        tag = asn.readTag();

        prim = new GlobalCellIdImpl();
        prim.decodeAll(asn);

        assertNotNull(prim.getData());

        assertEquals(prim.getMcc(), 250);
        assertEquals(prim.getMnc(), 1);
        assertEquals(prim.getLac(), 4444);
        assertEquals(prim.getCellId(), 0);

    }

    @Test(groups = { "functional.decode", "primitives" })
    public void testEncode() throws Exception {

        GlobalCellIdImpl prim = new GlobalCellIdImpl(250, 1, 4444, 3333);

        AsnOutputStream asn = new AsnOutputStream();
        prim.encodeAll(asn);

        assertTrue(Arrays.equals(asn.toByteArray(), this.getData()));
    }

    @Test(groups = { "functional.xml.serialize", "primitives" })
    public void testXMLSerialize() throws Exception {

        GlobalCellIdImpl original = new GlobalCellIdImpl(250, 1, 4444, 3333);

        // Writes the area to a file.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLObjectWriter writer = XMLObjectWriter.newInstance(baos);
        // writer.setBinding(binding); // Optional.
        writer.setIndentation("\t"); // Optional (use tabulation for
                                     // indentation).
        writer.write(original, "globalCellId", GlobalCellIdImpl.class);
        writer.close();

        byte[] rawData = baos.toByteArray();
        String serializedEvent = new String(rawData);

        System.out.println(serializedEvent);

        ByteArrayInputStream bais = new ByteArrayInputStream(rawData);
        XMLObjectReader reader = XMLObjectReader.newInstance(bais);
        GlobalCellIdImpl copy = reader.read("globalCellId", GlobalCellIdImpl.class);

        assertEquals(copy.getMcc(), original.getMcc());
        assertEquals(copy.getMnc(), original.getMnc());
        assertEquals(copy.getLac(), original.getLac());
        assertEquals(copy.getCellId(), original.getCellId());

    }

}
