
package org.restcomm.protocols.ss7.cap.service.gprs.primitive;

import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.PDPAddress;
import org.restcomm.protocols.ss7.cap.primitives.OctetStringBase;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class PDPAddressImpl extends OctetStringBase implements PDPAddress {

    public PDPAddressImpl() {
        super(1, 63, "PDPAddress");
    }

    public PDPAddressImpl(byte[] data) {
        super(1, 63, "PDPAddress", data);
    }

    @Override
    public byte[] getData() {
        return data;
    }

}
