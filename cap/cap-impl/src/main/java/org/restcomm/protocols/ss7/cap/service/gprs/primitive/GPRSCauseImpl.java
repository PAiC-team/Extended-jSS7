
package org.restcomm.protocols.ss7.cap.service.gprs.primitive;

import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.GPRSCause;
import org.restcomm.protocols.ss7.cap.primitives.OctetStringLength1Base;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class GPRSCauseImpl extends OctetStringLength1Base implements GPRSCause {

    public GPRSCauseImpl() {
        super("GPRSCause");
    }

    public GPRSCauseImpl(int data) {
        super("GPRSCause", data);
    }

    @Override
    public int getData() {
        return data;
    }

}
