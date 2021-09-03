
package org.restcomm.protocols.ss7.map.service.lsm;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.primitives.PlmnId;
import org.restcomm.protocols.ss7.map.api.service.lsm.RANTechnology;
import org.restcomm.protocols.ss7.map.primitives.PlmnIdImpl;
import org.restcomm.protocols.ss7.map.service.lsm.ReportingPLMNImpl;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class ReportingPLMNTest {

    private byte[] getEncodedData() {
        return new byte[] { 48, 10, -128, 3, 1, 2, 3, -127, 1, 1, -126, 0 };
    }

    private byte[] getDataPlmnId() {
        return new byte[] { 1, 2, 3 };
    }

    @Test(groups = { "functional.decode", "service.lms" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData();
        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        ReportingPLMNImpl imp = new ReportingPLMNImpl();
        imp.decodeAll(asn);

        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        assertTrue(Arrays.equals(imp.getPlmnId().getData(), getDataPlmnId()));
        assertEquals(imp.getRanTechnology(), RANTechnology.umts);
        assertTrue(imp.getRanPeriodicLocationSupport());
    }

    @Test(groups = { "functional.encode", "service.lms" })
    public void testEncode() throws Exception {

        PlmnId plmnId = new PlmnIdImpl(getDataPlmnId());
        ReportingPLMNImpl imp = new ReportingPLMNImpl(plmnId, RANTechnology.umts, true);
        // PlmnId plmnId, RANTechnology ranTechnology, boolean ranPeriodicLocationSupport

        AsnOutputStream asnOS = new AsnOutputStream();
        imp.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData();
        assertTrue(Arrays.equals(rawData, encodedData));
    }
}
