
package org.restcomm.protocols.ss7.map.service.mobility.authentication;

import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.IK;
import org.restcomm.protocols.ss7.map.primitives.OctetStringBase;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class IKImpl extends OctetStringBase implements IK {

    public IKImpl(byte[] data) {
        super(16, 16, "IK", data);
    }

    public IKImpl() {
        super(16, 16, "IK");
    }

    @Override
    public byte[] getData() {
        return this.data;
    }

}
