
package org.restcomm.protocols.ss7.map.service.lsm;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.map.MAPParameterFactoryImpl;
import org.restcomm.protocols.ss7.map.api.MAPParameterFactory;
import org.restcomm.protocols.ss7.map.api.primitives.AddressNature;
import org.restcomm.protocols.ss7.map.api.primitives.DiameterIdentity;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan;
import org.restcomm.protocols.ss7.map.primitives.DiameterIdentityImpl;
import org.restcomm.protocols.ss7.map.service.lsm.ServingNodeAddressImpl;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class ServingNodeAddressTest {

    MAPParameterFactory MAPParameterFactory = new MAPParameterFactoryImpl();

    public byte[] getDataMsc() {
        return new byte[] { -128, 5, -111, 49, 117, 9, 0 };
    }

    public byte[] getDataSgsn() {
        return new byte[] { -127, 5, -111, 49, 117, 9, 0 };
    }

    public byte[] getDataMme() {
        return new byte[] { -126, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
    }

    public byte[] getDataDiameterIdentity() {
        return new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
    }

    @Test(groups = { "functional.decode", "service.lsm" })
    public void testDecode() throws Exception {
        byte[] data = getDataMsc();

        AsnInputStream asn = new AsnInputStream(data);
        int tag = asn.readTag();
        assertEquals(tag, ServingNodeAddressImpl._TAG_mscNumber);

        ServingNodeAddressImpl impl = new ServingNodeAddressImpl();
        impl.decodeAll(asn);

        ISDNAddressString isdnAdd = impl.getMscNumber();
        assertEquals(isdnAdd.getAddressNature(), AddressNature.international_number);
        assertEquals(isdnAdd.getNumberingPlan(), NumberingPlan.ISDN);
        assertTrue(isdnAdd.getAddress().equals("13579000"));
        assertNull(impl.getSgsnNumber());
        assertNull(impl.getMmeNumber());

        data = getDataSgsn();

        asn = new AsnInputStream(data);
        tag = asn.readTag();
        assertEquals(tag, ServingNodeAddressImpl._TAG_sgsnNumber);

        impl = new ServingNodeAddressImpl();
        impl.decodeAll(asn);

        isdnAdd = impl.getSgsnNumber();
        assertEquals(isdnAdd.getAddressNature(), AddressNature.international_number);
        assertEquals(isdnAdd.getNumberingPlan(), NumberingPlan.ISDN);
        assertTrue(isdnAdd.getAddress().equals("13579000"));
        assertNull(impl.getMscNumber());
        assertNull(impl.getMmeNumber());

        data = getDataMme();

        asn = new AsnInputStream(data);
        tag = asn.readTag();
        assertEquals(tag, ServingNodeAddressImpl._TAG_mmeNumber);

        impl = new ServingNodeAddressImpl();
        impl.decodeAll(asn);

        DiameterIdentity di = impl.getMmeNumber();
        assertTrue(Arrays.equals(di.getData(), getDataDiameterIdentity()));
        assertNull(impl.getMscNumber());
        assertNull(impl.getSgsnNumber());
    }

    @Test(groups = { "functional.encode", "service.lsm" })
    public void testEncode() throws Exception {
        byte[] data = getDataMsc();

        ISDNAddressString isdnAdd = MAPParameterFactory.createISDNAddressString(AddressNature.international_number,
                NumberingPlan.ISDN, "13579000");
        ServingNodeAddressImpl impl = new ServingNodeAddressImpl(isdnAdd, true);

        AsnOutputStream asnOS = new AsnOutputStream();
        impl.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        assertTrue(Arrays.equals(data, encodedData));

        data = getDataSgsn();

        impl = new ServingNodeAddressImpl(isdnAdd, false);

        asnOS = new AsnOutputStream();
        impl.encodeAll(asnOS);

        encodedData = asnOS.toByteArray();
        assertTrue(Arrays.equals(data, encodedData));

        data = getDataMme();

        DiameterIdentity di = new DiameterIdentityImpl(getDataDiameterIdentity());
        impl = new ServingNodeAddressImpl(di);

        asnOS = new AsnOutputStream();
        impl.encodeAll(asnOS);

        encodedData = asnOS.toByteArray();
        assertTrue(Arrays.equals(data, encodedData));
    }
}
