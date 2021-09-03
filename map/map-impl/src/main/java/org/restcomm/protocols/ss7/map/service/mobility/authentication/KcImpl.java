
package org.restcomm.protocols.ss7.map.service.mobility.authentication;

import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.Kc;
import org.restcomm.protocols.ss7.map.primitives.OctetStringBase;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class KcImpl extends OctetStringBase implements Kc {

    public KcImpl(byte[] data) {
        super(8, 8, "Kc", data);
    }

    public KcImpl() {
        super(8, 8, "Kc");
    }

    @Override
    public byte[] getData() {
        return this.data;
    }

}
