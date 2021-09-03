
package org.restcomm.protocols.ss7.map.service.mobility.authentication;

import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.CK;
import org.restcomm.protocols.ss7.map.primitives.OctetStringBase;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class CKImpl extends OctetStringBase implements CK {

    public CKImpl(byte[] data) {
        super(16, 16, "CK", data);
    }

    public CKImpl() {
        super(16, 16, "CK");
    }

    @Override
    public byte[] getData() {
        return this.data;
    }

}
