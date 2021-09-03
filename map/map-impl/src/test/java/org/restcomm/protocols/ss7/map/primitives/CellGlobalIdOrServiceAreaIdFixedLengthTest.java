
package org.restcomm.protocols.ss7.map.primitives;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import javolution.xml.XMLObjectReader;
import javolution.xml.XMLObjectWriter;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.map.primitives.CellGlobalIdOrServiceAreaIdFixedLengthImpl;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class CellGlobalIdOrServiceAreaIdFixedLengthTest {

    public byte[] getData() {
        return new byte[] { 4, 7, 82, (byte) 240, 16, 17, 92, 13, 5 };
    };

    public byte[] getDataVal() {
        return new byte[] { 82, (byte) 240, 16, 17, 92, 13, 5 };
    };

    public byte[] getData2() {
        return new byte[] { 4, 7, 16, 97, 66, 1, 77, 1, (byte) 188 };
    };

    @Test(groups = { "functional.decode", "primitives" })
    public void testDecode() throws Exception {

        byte[] data = this.getData();

        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();

        CellGlobalIdOrServiceAreaIdFixedLengthImpl prim = new CellGlobalIdOrServiceAreaIdFixedLengthImpl();
        prim.decodeAll(asn);

        assertNotNull(prim.getData());
        assertTrue(Arrays.equals(getDataVal(), prim.getData()));

        assertEquals(prim.getMCC(), 250);
        assertEquals(prim.getMNC(), 1);
        assertEquals(prim.getLac(), 4444);
        assertEquals(prim.getCellIdOrServiceAreaCode(), 3333);

        data = this.getData2();

        asn = new AsnInputStream(data);
        tag = asn.readTag();

        prim = new CellGlobalIdOrServiceAreaIdFixedLengthImpl();
        prim.decodeAll(asn);

        assertNotNull(prim.getData());

        assertEquals(prim.getMCC(), 11);
        assertEquals(prim.getMNC(), 246);
        assertEquals(prim.getLac(), 333);
        assertEquals(prim.getCellIdOrServiceAreaCode(), 444);
    }

    @Test(groups = { "functional.decode", "primitives" })
    public void testEncode() throws Exception {

        CellGlobalIdOrServiceAreaIdFixedLengthImpl prim = new CellGlobalIdOrServiceAreaIdFixedLengthImpl(250, 1, 4444, 3333);

        AsnOutputStream asn = new AsnOutputStream();
        prim.encodeAll(asn);

        assertTrue(Arrays.equals(asn.toByteArray(), this.getData()));

        prim = new CellGlobalIdOrServiceAreaIdFixedLengthImpl(getDataVal());

        asn = new AsnOutputStream();
        prim.encodeAll(asn);

        assertTrue(Arrays.equals(asn.toByteArray(), this.getData()));

        prim = new CellGlobalIdOrServiceAreaIdFixedLengthImpl(11, 246, 333, 444);

        asn = new AsnOutputStream();
        prim.encodeAll(asn);

        assertTrue(Arrays.equals(asn.toByteArray(), this.getData2()));
    }

    @Test(groups = { "functional.xml.serialize", "primitives" })
    public void testXMLSerialize() throws Exception {

        CellGlobalIdOrServiceAreaIdFixedLengthImpl original = new CellGlobalIdOrServiceAreaIdFixedLengthImpl(250, 1, 4444, 3333);

        // Writes the area to a file.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLObjectWriter writer = XMLObjectWriter.newInstance(baos);
        // writer.setBinding(binding); // Optional.
        writer.setIndentation("\t"); // Optional (use tabulation for indentation).
        writer.write(original, "cellGlobalIdOrServiceAreaIdFixedLength", CellGlobalIdOrServiceAreaIdFixedLengthImpl.class);
        writer.close();

        byte[] rawData = baos.toByteArray();
        String serializedEvent = new String(rawData);

        System.out.println(serializedEvent);

        ByteArrayInputStream bais = new ByteArrayInputStream(rawData);
        XMLObjectReader reader = XMLObjectReader.newInstance(bais);
        CellGlobalIdOrServiceAreaIdFixedLengthImpl copy = reader.read("cellGlobalIdOrServiceAreaIdFixedLength",
                CellGlobalIdOrServiceAreaIdFixedLengthImpl.class);

        assertEquals(copy.getMCC(), original.getMCC());
        assertEquals(copy.getMNC(), original.getMNC());
        assertEquals(copy.getLac(), original.getLac());
        assertEquals(copy.getCellIdOrServiceAreaCode(), original.getCellIdOrServiceAreaCode());

    }
}
