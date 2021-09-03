
package org.restcomm.protocols.ss7.map.service.mobility.authentication;

import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.KSI;
import org.restcomm.protocols.ss7.map.primitives.OctetStringLength1Base;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class KSIImpl extends OctetStringLength1Base implements KSI {

    public KSIImpl() {
        super("KSI");
    }

    public KSIImpl(int data) {
        super("KSI", data);
    }

    @Override
    public int getData() {
        return this.data;
    }

}
