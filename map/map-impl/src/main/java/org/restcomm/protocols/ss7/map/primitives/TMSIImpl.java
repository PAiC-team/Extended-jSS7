
package org.restcomm.protocols.ss7.map.primitives;

import org.restcomm.protocols.ss7.map.api.primitives.TMSI;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class TMSIImpl extends OctetStringBase implements TMSI {

    public TMSIImpl(byte[] data) {
        super(1, 4, "TMSI", data);
    }

    public TMSIImpl() {
        super(1, 4, "TMSI");
    }

    @Override
    public byte[] getData() {
        return this.data;
    }

}
