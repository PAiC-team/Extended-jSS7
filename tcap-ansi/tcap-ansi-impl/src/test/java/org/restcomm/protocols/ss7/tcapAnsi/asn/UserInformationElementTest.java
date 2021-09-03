
package org.restcomm.protocols.ss7.tcapAnsi.asn;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.EncodeException;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.UserInformationElement;
import org.restcomm.protocols.ss7.tcapAnsi.asn.UserInformationElementImpl;
import org.testng.annotations.Test;

/**
 *
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
@Test(groups = { "asn" })
public class UserInformationElementTest {

    private byte[] data = new byte[] { 40, 18, 6, 7, 4, 0, 0, 1, 1, 1, 1, -96, 7, 1, 2, 3, 4, 5, 6, 7 };

    private byte[] dataValue = new byte[] { 1, 2, 3, 4, 5, 6, 7 };

    @Test(groups = { "functional.decode" })
    public void testDecode() throws Exception {

        AsnInputStream asin = new AsnInputStream(data);
        int tag = asin.readTag();
        assertEquals(UserInformationElement._TAG_EXTERNAL, tag);
        assertEquals(Tag.CLASS_UNIVERSAL, asin.getTagClass());
        assertFalse(asin.isTagPrimitive());

        UserInformationElement userInformationElement = new UserInformationElementImpl();
        userInformationElement.decode(asin);

        assertTrue(userInformationElement.isOid());

        assertTrue(Arrays.equals(new long[] { 0, 4, 0, 0, 1, 1, 1, 1 }, userInformationElement.getOidValue()));

        assertFalse(userInformationElement.isInteger());

        assertTrue(userInformationElement.isAsn());
        assertEquals(dataValue, userInformationElement.getEncodeType());

    }

    @Test(groups = { "functional.encode" })
    public void testUserInformationEncode() throws IOException, EncodeException {

        UserInformationElement userInformationElement = new UserInformationElementImpl();

        userInformationElement.setOid(true);
        userInformationElement.setOidValue(new long[] { 0, 4, 0, 0, 1, 1, 1, 1 });

        userInformationElement.setAsn(true);
        userInformationElement.setEncodeType(dataValue);

        AsnOutputStream asnos = new AsnOutputStream();
        userInformationElement.encode(asnos);

        byte[] userInfData = asnos.toByteArray();

        assertTrue(Arrays.equals(data, userInfData));

    }

}
