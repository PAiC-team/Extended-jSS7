
package org.restcomm.protocols.ss7.tcapAnsi.asn;

import static org.testng.Assert.*;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp.Invoke;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp.OperationCode;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp.Parameter;
import org.restcomm.protocols.ss7.tcapAnsi.asn.TcapFactory;
import org.testng.annotations.Test;

/**
 *
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
@Test(groups = { "asn" })
public class InvokeTest {

    private byte[] data1 = new byte[] { (byte) 233, 41, (byte) 207, 1, 0, (byte) 209, 2, 9, 53, (byte) 242, 32, (byte) 159, 105, 0, (byte) 159, 116, 0,
            (byte) 159, (byte) 129, 0, 1, 8, (byte) 136, 5, 22, 25, 50, 4, 0, (byte) 159, (byte) 129, 65, 1, 1, (byte) 159, (byte) 129, 67, 5, 34, 34, 34, 34,
            34 };    

    private byte[] data2 = new byte[] { -19, 9, -49, 2, 20, 10, -48, 1, -13, -14, 0 };    

    private byte[] data3 = new byte[] { -19, 7, -49, 0, -48, 1, -13, -14, 0 };    

    private byte[] parData = new byte[] { -97, 105, 0, -97, 116, 0, -97, -127, 0, 1, 8, -120, 5, 22, 25, 50, 4, 0, -97, -127, 65, 1, 1, -97, -127, 67, 5, 34,
            34, 34, 34, 34 };

    @Test(groups = { "functional.decode" })
    public void testDecode() throws Exception {

        // 1
        AsnInputStream ais = new AsnInputStream(this.data1);
        int tag = ais.readTag();
        assertEquals(tag, Invoke._TAG_INVOKE_LAST);
        assertEquals(ais.getTagClass(), Tag.CLASS_PRIVATE);

        Invoke inv = TcapFactory.createComponentInvoke();
        inv.decode(ais);

        assertFalse(inv.isNotLast());
        assertEquals((long) inv.getInvokeId(), 0);
        assertNull(inv.getCorrelationId());
        assertEquals((long) inv.getOperationCode().getPrivateOperationCode(), 2357);
        Parameter p = inv.getParameter();
        assertEquals(p.getTag(), 18);
        assertEquals(p.getTagClass(), Tag.CLASS_PRIVATE);
        assertFalse(p.isPrimitive());
        assertEquals(p.getData(), parData);

        // 2
        ais = new AsnInputStream(this.data2);
        tag = ais.readTag();
        assertEquals(tag, Invoke._TAG_INVOKE_NOT_LAST);
        assertEquals(ais.getTagClass(), Tag.CLASS_PRIVATE);

        inv = TcapFactory.createComponentInvoke();
        inv.decode(ais);

        assertTrue(inv.isNotLast());
        assertEquals((long) inv.getInvokeId(), 20);
        assertEquals((long) inv.getCorrelationId(), 10);
        assertEquals((long) inv.getOperationCode().getNationalOperationCode(), -13);
        p = inv.getParameter();
        assertEquals(p.getTag(), 18);
        assertEquals(p.getTagClass(), Tag.CLASS_PRIVATE);
        assertFalse(p.isPrimitive());
        assertEquals(p.getData(), new byte[0]);

        // 3
        ais = new AsnInputStream(this.data3);
        tag = ais.readTag();
        assertEquals(tag, Invoke._TAG_INVOKE_NOT_LAST);
        assertEquals(ais.getTagClass(), Tag.CLASS_PRIVATE);

        inv = TcapFactory.createComponentInvoke();
        inv.decode(ais);

        assertTrue(inv.isNotLast());
        assertNull(inv.getInvokeId());
        assertNull(inv.getCorrelationId());
        assertEquals((long) inv.getOperationCode().getNationalOperationCode(), -13);
        p = inv.getParameter();
        assertEquals(p.getTag(), 18);
        assertEquals(p.getTagClass(), Tag.CLASS_PRIVATE);
        assertFalse(p.isPrimitive());
        assertEquals(p.getData(), new byte[0]);
    }

    @Test(groups = { "functional.encode" })
    public void testEncode() throws Exception {

        // 1
        Invoke inv = TcapFactory.createComponentInvoke();
        inv.setInvokeId(0L);
        inv.setNotLast(false);
        OperationCode oc = TcapFactory.createOperationCode();
        oc.setPrivateOperationCode(2357L);
        inv.setOperationCode(oc);
        Parameter p = TcapFactory.createParameterSet();
        p.setData(parData);
        inv.setParameter(p);

        AsnOutputStream aos = new AsnOutputStream();
        inv.encode(aos);
        byte[] encodedData = aos.toByteArray();
        byte[] expectedData = data1;
        assertEquals(encodedData, expectedData);

        // 2
        inv = TcapFactory.createComponentInvoke();
        inv.setInvokeId(20L);
        inv.setCorrelationId(10L);
        inv.setNotLast(true);
        oc = TcapFactory.createOperationCode();
        oc.setNationalOperationCode(-13L);
        inv.setOperationCode(oc);
        p = TcapFactory.createParameterSet();
        inv.setParameter(p);

        aos = new AsnOutputStream();
        inv.encode(aos);
        encodedData = aos.toByteArray();
        expectedData = data2;
        assertEquals(encodedData, expectedData);

        // 3
        inv = TcapFactory.createComponentInvoke();
        inv.setNotLast(true);
        oc = TcapFactory.createOperationCode();
        oc.setNationalOperationCode(-13L);
        inv.setOperationCode(oc);
        p = TcapFactory.createParameterSet();
        inv.setParameter(p);

        aos = new AsnOutputStream();
        inv.encode(aos);
        encodedData = aos.toByteArray();
        expectedData = data3;
        assertEquals(encodedData, expectedData);
    }

}
