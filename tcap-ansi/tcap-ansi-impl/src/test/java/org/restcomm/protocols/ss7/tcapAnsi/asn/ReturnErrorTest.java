
package org.restcomm.protocols.ss7.tcapAnsi.asn;

import static org.testng.Assert.*;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp.ErrorCode;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp.Parameter;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp.ReturnError;
import org.restcomm.protocols.ss7.tcapAnsi.asn.TcapFactory;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
@Test(groups = { "asn" })
public class ReturnErrorTest {

    private byte[] data1 = new byte[] { -21, 13, -49, 1, 5, -44, 1, 14, -14, 5, 1, 2, 3, 4, 5 };

    private byte[] parData = new byte[] { 1, 2, 3, 4, 5 };

    @Test(groups = { "functional.decode" })
    public void testDecode() throws Exception {

        // 1
        AsnInputStream ais = new AsnInputStream(this.data1);
        int tag = ais.readTag();
        assertEquals(tag, ReturnError._TAG_RETURN_ERROR);
        assertEquals(ais.getTagClass(), Tag.CLASS_PRIVATE);

        ReturnError re = TcapFactory.createComponentReturnError();
        re.decode(ais);

        assertEquals((long) re.getCorrelationId(), 5);
        Parameter p = re.getParameter();
        assertEquals((long) re.getErrorCode().getPrivateErrorCode(), 14);
        assertEquals(p.getTag(), 18);
        assertEquals(p.getTagClass(), Tag.CLASS_PRIVATE);
        assertFalse(p.isPrimitive());
        assertEquals(p.getData(), parData);
    }

    @Test(groups = { "functional.encode" })
    public void testEncode() throws Exception {

        // 1
        ReturnError re = TcapFactory.createComponentReturnError();
        re.setCorrelationId(5L);
        ErrorCode ec = TcapFactory.createErrorCode();
        ec.setPrivateErrorCode(14L);
        re.setErrorCode(ec);
        Parameter p = TcapFactory.createParameterSet();
        p.setData(parData);
        re.setParameter(p);

        AsnOutputStream aos = new AsnOutputStream();
        re.encode(aos);
        byte[] encodedData = aos.toByteArray();
        byte[] expectedData = data1;
        assertEquals(encodedData, expectedData);
    }
}
