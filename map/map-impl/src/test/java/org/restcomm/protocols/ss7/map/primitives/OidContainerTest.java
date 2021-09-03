
package org.restcomm.protocols.ss7.map.primitives;

import static org.testng.Assert.assertEquals;

import org.restcomm.protocols.ss7.map.primitives.OidContainer;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class OidContainerTest {

    private long[] getSourceData() {
        return new long[] { 1, 205, 3 };
    }

    private String getString() {
        return "1.205.3";
    }

    @Test(groups = { "functional.decode", "primitives" })
    public void testDecode() throws Exception {

        OidContainer oid = new OidContainer();
        oid.parseSerializedData(getString());

        assertEquals(oid.getData(), getSourceData());
    }

    @Test(groups = { "functional.encode", "primitives" })
    public void testEncode() throws Exception {

        OidContainer oid = new OidContainer(getSourceData());
        String s1 = oid.getSerializedData();

        assertEquals(s1, getString());
    }

}
