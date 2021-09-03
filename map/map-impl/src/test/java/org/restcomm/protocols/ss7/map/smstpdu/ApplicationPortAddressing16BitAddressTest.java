
package org.restcomm.protocols.ss7.map.smstpdu;

import static org.testng.Assert.*;

import org.restcomm.protocols.ss7.map.smstpdu.ApplicationPortAddressing16BitAddressImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class ApplicationPortAddressing16BitAddressTest {

    private byte[] getData() {
        return new byte[] { 0x3e, (byte) 0x94, 0, 1 };
    }

    @Test(groups = { "functional.decode", "smstpdu" })
    public void testDecode() throws Exception {

        ApplicationPortAddressing16BitAddressImpl dcs = new ApplicationPortAddressing16BitAddressImpl(getData());
        assertEquals(dcs.getDestinationPort(), 16020);
        assertEquals(dcs.getOriginatorPort(), 1);
    }

    @Test(groups = { "functional.encode", "smstpdu" })
    public void testEncode() throws Exception {

        ApplicationPortAddressing16BitAddressImpl dcs = new ApplicationPortAddressing16BitAddressImpl(16020, 1);
        assertEquals(dcs.getEncodedInformationElementData(), getData());
    }

}
