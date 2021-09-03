
package org.restcomm.protocols.ss7.map.service.lsm;

import org.restcomm.protocols.ss7.map.api.service.lsm.UtranPositioningDataInfo;
import org.restcomm.protocols.ss7.map.primitives.OctetStringBase;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class UtranPositioningDataInfoImpl extends OctetStringBase implements UtranPositioningDataInfo {

    public UtranPositioningDataInfoImpl() {
        super(3, 11, "UtranPositioningDataInfo");
    }

    public UtranPositioningDataInfoImpl(byte[] data) {
        super(3, 11, "UtranPositioningDataInfo", data);
    }

    public byte[] getData() {
        return data;
    }

}
