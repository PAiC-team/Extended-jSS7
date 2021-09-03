
package org.restcomm.protocols.ss7.cap.service.sms.primitive;

import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.TPProtocolIdentifier;
import org.restcomm.protocols.ss7.cap.primitives.OctetStringLength1Base;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class TPProtocolIdentifierImpl extends OctetStringLength1Base implements TPProtocolIdentifier {

    public TPProtocolIdentifierImpl() {
        super("TPProtocolIdentifier");
    }

    public TPProtocolIdentifierImpl(int data) {
        super("TPProtocolIdentifier", data);
    }

    @Override
    public int getData() {
        return data;
    }

}
