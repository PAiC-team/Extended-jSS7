
package org.restcomm.protocols.ss7.map.service.lsm;

import org.restcomm.protocols.ss7.map.api.service.lsm.GeranGANSSpositioningData;
import org.restcomm.protocols.ss7.map.primitives.OctetStringBase;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class GeranGANSSpositioningDataImpl extends OctetStringBase implements GeranGANSSpositioningData {

    public GeranGANSSpositioningDataImpl() {
        super(2, 10, "GeranGANSSpositioningData");
    }

    public GeranGANSSpositioningDataImpl(byte[] data) {
        super(2, 10, "GeranGANSSpositioningData", data);
    }

    public byte[] getData() {
        return data;
    }

}
