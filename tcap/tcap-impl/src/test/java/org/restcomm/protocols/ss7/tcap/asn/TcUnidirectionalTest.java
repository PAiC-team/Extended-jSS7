package org.restcomm.protocols.ss7.tcap.asn;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.tcap.TCAPTestUtils;
import org.restcomm.protocols.ss7.tcap.asn.ApplicationContextName;
import org.restcomm.protocols.ss7.tcap.asn.ApplicationContextNameImpl;
import org.restcomm.protocols.ss7.tcap.asn.DialogAPDUType;
import org.restcomm.protocols.ss7.tcap.asn.DialogPortion;
import org.restcomm.protocols.ss7.tcap.asn.DialogRequestAPDU;
import org.restcomm.protocols.ss7.tcap.asn.EncodeException;
import org.restcomm.protocols.ss7.tcap.asn.ParseException;
import org.restcomm.protocols.ss7.tcap.asn.TCUniMessageImpl;
import org.restcomm.protocols.ss7.tcap.asn.TcapFactory;
import org.restcomm.protocols.ss7.tcap.asn.comp.Component;
import org.restcomm.protocols.ss7.tcap.asn.comp.Invoke;
import org.restcomm.protocols.ss7.tcap.asn.comp.OperationCode;
import org.restcomm.protocols.ss7.tcap.asn.comp.Parameter;
import org.restcomm.protocols.ss7.tcap.asn.comp.TCUniMessage;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
@Test(groups = { "asn" })
public class TcUnidirectionalTest {

    private byte[] getData() {
        return new byte[] { 97, 45, 107, 27, 40, 25, 6, 7, 0, 17, -122, 5, 1, 2, 1, -96, 14, 96, 12, -128, 2, 7, -128, -95, 6,
                6, 4, 40, 2, 3, 4, 108, 14, -95, 12, 2, 1, -128, 2, 2, 2, 79, 4, 3, 1, 2, 3 };
    }

    @Test(groups = { "functional.encode" })
    public void testEncode() throws IOException, EncodeException {

        byte[] expected = getData();

        TCUniMessageImpl tcUniMessage = new TCUniMessageImpl();

        DialogPortion dp = TcapFactory.createDialogPortion();
        dp.setUnidirectional(true);
        DialogRequestAPDU dapdu = TcapFactory.createDialogAPDURequest();
        ApplicationContextName acn = new ApplicationContextNameImpl();
        acn.setOid(new long[] { 1, 0, 2, 3, 4 });
        dapdu.setApplicationContextName(acn);
        dp.setDialogAPDU(dapdu);
        tcUniMessage.setDialogPortion(dp);

        Invoke invComp = TcapFactory.createComponentInvoke();
        invComp.setInvokeId(-128l);
        OperationCode oc = TcapFactory.createOperationCode();
        oc.setLocalOperationCode(591L);
        invComp.setOperationCode(oc);
        Parameter p = TcapFactory.createParameter();
        p.setTagClass(Tag.CLASS_UNIVERSAL);
        p.setTag(Tag.STRING_OCTET);
        p.setData(new byte[] { 1, 2, 3 });
        invComp.setParameter(p);
        tcUniMessage.setComponent(new Component[] { invComp });

        AsnOutputStream aos = new AsnOutputStream();
        tcUniMessage.encode(aos);
        byte[] data = aos.toByteArray();
        TCAPTestUtils.compareArrays(expected, data);
    }

    @Test(groups = { "functional.decode" })
    public void testDencode() throws IOException, ParseException {

        byte[] b = this.getData();

        AsnInputStream ais = new AsnInputStream(b);
        int tag = ais.readTag();
        assertEquals(TCUniMessage._TAG, tag);
        TCUniMessage tcm = TcapFactory.createTCUniMessage(ais);

        DialogPortion dp = tcm.getDialogPortion();
        Component[] comp = tcm.getComponent();

        assertNotNull(dp);
        assertNotNull(dp.getDialogAPDU());
        assertEquals(true, dp.isUnidirectional());
        assertEquals(DialogAPDUType.UniDirectional, dp.getDialogAPDU().getType());

        assertNotNull(comp);
        assertEquals(1, comp.length);
    }
}
