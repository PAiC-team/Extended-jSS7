
package org.restcomm.protocols.ss7.cap.service.gprs.primitive;

import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.AccessPointName;
import org.restcomm.protocols.ss7.cap.primitives.OctetStringBase;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class AccessPointNameImpl extends OctetStringBase implements AccessPointName {

    public AccessPointNameImpl() {
        super(3, 102, "AccessPointName");
    }

    public AccessPointNameImpl(byte[] data) {
        super(3, 102, "AccessPointName", data);
    }

    @Override
    public byte[] getData() {
        return data;
    }

}
