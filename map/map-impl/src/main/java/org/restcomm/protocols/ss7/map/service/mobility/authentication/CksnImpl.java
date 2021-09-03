
package org.restcomm.protocols.ss7.map.service.mobility.authentication;

import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.Cksn;
import org.restcomm.protocols.ss7.map.primitives.OctetStringLength1Base;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class CksnImpl extends OctetStringLength1Base implements Cksn {

    public CksnImpl(int data) {
        super("Cksn", data);
    }

    public CksnImpl() {
        super("Cksn");
    }

    @Override
    public int getData() {
        return this.data;
    }

}
