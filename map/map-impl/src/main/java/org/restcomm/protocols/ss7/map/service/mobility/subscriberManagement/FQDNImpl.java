
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.FQDN;
import org.restcomm.protocols.ss7.map.primitives.OctetStringBase;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class FQDNImpl extends OctetStringBase implements FQDN {

    public FQDNImpl() {
        super(9, 255, "FQDN");
    }

    public FQDNImpl(byte[] data) {
        super(9, 255, "FQDN", data);
    }

    @Override
    public byte[] getData() {
        return data;
    }

}
