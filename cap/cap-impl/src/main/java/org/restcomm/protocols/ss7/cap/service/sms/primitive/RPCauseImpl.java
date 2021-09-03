
package org.restcomm.protocols.ss7.cap.service.sms.primitive;

import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.RPCause;
import org.restcomm.protocols.ss7.cap.primitives.OctetStringLength1Base;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class RPCauseImpl extends OctetStringLength1Base implements RPCause {

    public RPCauseImpl() {
        super("RPCause");
    }

    public RPCauseImpl(int data) {
        super("RPCause", data);
    }

    @Override
    public int getData() {
        return data;
    }

}
