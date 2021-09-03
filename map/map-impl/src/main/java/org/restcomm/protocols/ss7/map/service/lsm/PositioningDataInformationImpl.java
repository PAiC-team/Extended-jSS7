
package org.restcomm.protocols.ss7.map.service.lsm;

import org.restcomm.protocols.ss7.map.api.service.lsm.PositioningDataInformation;
import org.restcomm.protocols.ss7.map.primitives.OctetStringBase;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class PositioningDataInformationImpl extends OctetStringBase implements PositioningDataInformation {

    public PositioningDataInformationImpl() {
        super(2, 10, "PositioningDataInformation");
    }

    public PositioningDataInformationImpl(byte[] data) {
        super(2, 10, "PositioningDataInformation", data);
    }

    public byte[] getData() {
        return data;
    }

}
