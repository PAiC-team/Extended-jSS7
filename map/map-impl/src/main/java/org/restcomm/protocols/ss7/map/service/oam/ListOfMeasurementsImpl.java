
package org.restcomm.protocols.ss7.map.service.oam;

import org.restcomm.protocols.ss7.map.api.service.oam.ListOfMeasurements;
import org.restcomm.protocols.ss7.map.primitives.OctetStringBase;

/**
*
* @author sergey vetyutnev
*
*/
public class ListOfMeasurementsImpl extends OctetStringBase implements ListOfMeasurements {

    public ListOfMeasurementsImpl() {
        super(4, 4, "ListOfMeasurements");
    }

    public ListOfMeasurementsImpl(byte[] data) {
        super(4, 4, "ListOfMeasurements", data);
    }

    public byte[] getData() {
        return data;
    }

}
