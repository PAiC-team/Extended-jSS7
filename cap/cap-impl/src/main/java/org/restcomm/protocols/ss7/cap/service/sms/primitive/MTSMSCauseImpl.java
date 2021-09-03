
package org.restcomm.protocols.ss7.cap.service.sms.primitive;

import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.MTSMSCause;
import org.restcomm.protocols.ss7.cap.primitives.OctetStringLength1Base;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class MTSMSCauseImpl extends OctetStringLength1Base implements MTSMSCause {

    public MTSMSCauseImpl() {
        super("MTSMSCause");
    }

    public MTSMSCauseImpl(int data) {
        super("MTSMSCause", data);
    }

    @Override
    public int getData() {
        return data;
    }

}
