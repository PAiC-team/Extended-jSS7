
package org.restcomm.protocols.ss7.cap.service.gprs.primitive;

import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.PDPID;
import org.restcomm.protocols.ss7.cap.primitives.OctetStringLength1Base;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class PDPIDImpl extends OctetStringLength1Base implements PDPID {

    public PDPIDImpl() {
        super("PDPID");
    }

    public PDPIDImpl(int data) {
        super("PDPID", data);
    }

    @Override
    public int getId() {
        return data;
    }

}
