package org.restcomm.protocols.ss7.map.dialog;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.map.api.dialog.MAPProviderAbortReason;
import org.restcomm.protocols.ss7.map.dialog.MAPProviderAbortInfoImpl;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerTest;
import org.testng.annotations.Test;

/**
 *
 * @author amit bhayani
 *
 */
public class MAPProviderAbortInfoTest {

    private byte[] getDataFull() {
        return new byte[] { -91, 44, 10, 1, 1, 48, 39, -96, 32, 48, 10, 6, 3, 42, 3, 4, 11, 12, 13, 14, 15, 48, 5, 6, 3, 42, 3,
                6, 48, 11, 6, 3, 42, 3, 5, 21, 22, 23, 24, 25, 26, -95, 3, 31, 32, 33 };
    }

    @Test(groups = { "functional.decode", "dialog" })
    public void testDecode() throws Exception {
        // The raw data is from last packet of long ussd-abort from msc2.txt
        byte[] data = new byte[] { (byte) 0xA5, 0x03, (byte) 0x0A, 0x01, 0x00 };

        AsnInputStream asnIs = new AsnInputStream(data);
        int tag = asnIs.readTag();
        assertEquals(tag, 5);

        MAPProviderAbortInfoImpl mapProviderAbortInfo = new MAPProviderAbortInfoImpl();
        mapProviderAbortInfo.decodeAll(asnIs);

        MAPProviderAbortReason reason = mapProviderAbortInfo.getMAPProviderAbortReason();

        assertNotNull(reason);

        assertEquals(reason, MAPProviderAbortReason.abnormalDialogue);

        data = this.getDataFull();
        asnIs = new AsnInputStream(data);
        tag = asnIs.readTag();
        assertEquals(tag, 5);

        mapProviderAbortInfo = new MAPProviderAbortInfoImpl();
        mapProviderAbortInfo.decodeAll(asnIs);
        reason = mapProviderAbortInfo.getMAPProviderAbortReason();

        assertNotNull(reason);
        assertEquals(reason, MAPProviderAbortReason.invalidPDU);
        assertTrue(MAPExtensionContainerTest.CheckTestExtensionContainer(mapProviderAbortInfo.getExtensionContainer()));

    }

    @Test(groups = { "functional.encode", "dialog" })
    public void testEncode() throws Exception {

        MAPProviderAbortInfoImpl mapProviderAbortInfo = new MAPProviderAbortInfoImpl();
        mapProviderAbortInfo.setMAPProviderAbortReason(MAPProviderAbortReason.invalidPDU);

        AsnOutputStream asnOS = new AsnOutputStream();

        mapProviderAbortInfo.encodeAll(asnOS);

        byte[] data = asnOS.toByteArray();

        // System.out.println(dump(data, data.length, false));

        assertTrue(Arrays.equals(new byte[] { (byte) 0xA5, 0x03, (byte) 0x0A, 0x01, 0x01 }, data));

        mapProviderAbortInfo = new MAPProviderAbortInfoImpl();
        mapProviderAbortInfo.setMAPProviderAbortReason(MAPProviderAbortReason.invalidPDU);
        mapProviderAbortInfo.setExtensionContainer(MAPExtensionContainerTest.GetTestExtensionContainer());

        asnOS = new AsnOutputStream();
        mapProviderAbortInfo.encodeAll(asnOS);

        data = asnOS.toByteArray();
        assertTrue(Arrays.equals(this.getDataFull(), data));

    }

}
