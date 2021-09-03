
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.AgeIndicator;
import org.restcomm.protocols.ss7.map.primitives.OctetStringBase;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class AgeIndicatorImpl extends OctetStringBase implements AgeIndicator {

    public AgeIndicatorImpl() {
        super(1, 6, "AgeIndicator");
    }

    public AgeIndicatorImpl(byte[] data) {
        super(1, 6, "AgeIndicator", data);
    }

    public byte[] getData() {
        return data;
    }

}
