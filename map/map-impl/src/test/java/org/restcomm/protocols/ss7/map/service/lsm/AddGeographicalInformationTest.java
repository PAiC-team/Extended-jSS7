
package org.restcomm.protocols.ss7.map.service.lsm;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.TypeOfShape;
import org.restcomm.protocols.ss7.map.service.lsm.AddGeographicalInformationImpl;
import org.testng.annotations.Test;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class AddGeographicalInformationTest {

    private byte[] getEncodedData_EllipsoidPointWithUncertaintyCircle() {
        return new byte[] { 4, 8, 16, 92, 113, -57, -106, 11, 97, 7 };
    }

    @Test(groups = { "functional.decode", "lsm" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData_EllipsoidPointWithUncertaintyCircle();
        AsnInputStream asn = new AsnInputStream(rawData);
        int tag = asn.readTag();
        AddGeographicalInformationImpl impl = new AddGeographicalInformationImpl();
        impl.decodeAll(asn);

        assertEquals(impl.getTypeOfShape(), TypeOfShape.EllipsoidPointWithUncertaintyCircle);
        assertTrue(Math.abs(impl.getLatitude() - 65) < 0.01);
        assertTrue(Math.abs(impl.getLongitude() - (-149)) < 0.01); // -31
        assertTrue(Math.abs(impl.getUncertainty() - 9.48) < 0.01);
    }

    @Test(groups = { "functional.encode", "lsm" })
    public void testEncode() throws Exception {

        AddGeographicalInformationImpl impl = new AddGeographicalInformationImpl(
                TypeOfShape.EllipsoidPointWithUncertaintyCircle, 65, -149, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        // TypeOfShape typeOfShape, double latitude, double longitude, double uncertainty, double uncertaintySemiMajorAxis,
        // double uncertaintySemiMinorAxis, double angleOfMajorAxis, int confidence, int altitude, double uncertaintyAltitude,
        // int innerRadius,
        // double uncertaintyRadius, double offsetAngle, double includedAngle
        AsnOutputStream asnOS = new AsnOutputStream();
        impl.encodeAll(asnOS);
        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData_EllipsoidPointWithUncertaintyCircle();
        assertTrue(Arrays.equals(rawData, encodedData));
    }

    // TODO: add processing missed: TypeOfShape.Polygon, TypeOfShape.EllipsoidPointWithAltitude

}
