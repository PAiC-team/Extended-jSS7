
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.APNOIReplacement;
import org.restcomm.protocols.ss7.map.primitives.OctetStringBase;

/**
 *
 * @author daniel bichara
 *
 */
public class APNOIReplacementImpl extends OctetStringBase implements APNOIReplacement {

    public APNOIReplacementImpl() {
        super(9, 100, "APNOIReplacement");
    }

    public APNOIReplacementImpl(byte[] data) {
        super(9, 100, "APNOIReplacement", data);
    }

    public byte[] getData() {
        return data;
    }

}
