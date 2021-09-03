
package org.restcomm.protocols.ss7.map.service.lsm;

import org.restcomm.protocols.ss7.map.api.service.lsm.UtranGANSSpositioningData;
import org.restcomm.protocols.ss7.map.primitives.OctetStringBase;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class UtranGANSSpositioningDataImpl extends OctetStringBase implements UtranGANSSpositioningData {

    public UtranGANSSpositioningDataImpl() {
        super(1, 9, "UtranGANSSpositioningData");
    }

    public UtranGANSSpositioningDataImpl(byte[] data) {
        super(1, 9, "UtranGANSSpositioningData", data);
    }

    public byte[] getData() {
        return data;
    }

}
