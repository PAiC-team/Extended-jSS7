
package org.restcomm.protocols.ss7.map.service.lsm;

import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.service.lsm.AddGeographicalInformation;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.TypeOfShape;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class AddGeographicalInformationImpl extends ExtGeographicalInformationImpl implements AddGeographicalInformation {

    public AddGeographicalInformationImpl() {
        super(1, 91, "AddGeographicalInformation");
    }

    public AddGeographicalInformationImpl(byte[] data) {
        super(1, 91, "AddGeographicalInformation", data);
    }

    public AddGeographicalInformationImpl(TypeOfShape typeOfShape, double latitude, double longitude, double uncertainty,
            double uncertaintySemiMajorAxis, double uncertaintySemiMinorAxis, double angleOfMajorAxis, int confidence,
            int altitude, double uncertaintyAltitude, int innerRadius, double uncertaintyRadius, double offsetAngle,
            double includedAngle) throws MAPException {
        super(1, 91, "AddGeographicalInformation");

        initData(typeOfShape, latitude, longitude, uncertainty, uncertaintySemiMajorAxis, uncertaintySemiMinorAxis,
                angleOfMajorAxis, confidence, altitude, uncertaintyAltitude, innerRadius, uncertaintyRadius, offsetAngle,
                includedAngle);
    }

    // TODO: add processing missed: TypeOfShape.EllipsoidPointWithAltitude

}
