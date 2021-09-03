
package org.restcomm.protocols.ss7.map.primitives;

import static org.testng.Assert.*;

import org.restcomm.protocols.ss7.map.api.primitives.GSNAddressAddressType;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class GSNAddressAddressTypeTest {

    private int getEncodedData() {
        return 4;
    }

    private int getEncodedData2() {
        return 80;
    }

    @Test(groups = { "functional.decode", "primitives" })
    public void testDecode() throws Exception {

        int firstByte = getEncodedData();
        GSNAddressAddressType asc = GSNAddressAddressType.getFromGSNAddressFirstByte(firstByte);

        assertEquals(asc, GSNAddressAddressType.IPv4);


        firstByte = getEncodedData2();
        asc = GSNAddressAddressType.getFromGSNAddressFirstByte(firstByte);

        assertEquals(asc, GSNAddressAddressType.IPv6);
    }

    @Test(groups = { "functional.encode", "primitives" })
    public void testEncode() throws Exception {

        GSNAddressAddressType asc = GSNAddressAddressType.IPv4;
        int firstByte = asc.createGSNAddressFirstByte();
        assertEquals(firstByte, getEncodedData());

        asc = GSNAddressAddressType.IPv6;
        firstByte = asc.createGSNAddressFirstByte();
        assertEquals(firstByte, getEncodedData2());
    }

}
