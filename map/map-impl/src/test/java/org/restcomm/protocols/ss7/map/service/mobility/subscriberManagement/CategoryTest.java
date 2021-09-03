
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import static org.testng.Assert.*;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.CategoryValue;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.CategoryImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class CategoryTest {

    public byte[] getData1() {
        return new byte[] { 4, 1, 10 };
    };

    @Test(groups = { "functional.decode", "mobility.subscriberManagement" })
    public void testDecode() throws Exception {
        byte[] data = this.getData1();
        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();
        CategoryImpl prim = new CategoryImpl();
        prim.decodeAll(asn);

        assertEquals(tag, Tag.STRING_OCTET);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertEquals(prim.getCategoryValue(), CategoryValue.ordinaryCallingSubscriber);
    }

    @Test(groups = { "functional.encode", "mobility.subscriberManagement" })
    public void testEncode() throws Exception {
        CategoryImpl prim = new CategoryImpl(CategoryValue.ordinaryCallingSubscriber);

        AsnOutputStream asn = new AsnOutputStream();
        prim.encodeAll(asn);

        assertEquals(asn.toByteArray(), this.getData1());
    }

}
