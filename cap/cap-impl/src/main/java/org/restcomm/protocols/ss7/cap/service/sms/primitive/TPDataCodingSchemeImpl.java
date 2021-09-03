
package org.restcomm.protocols.ss7.cap.service.sms.primitive;

import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.TPDataCodingScheme;
import org.restcomm.protocols.ss7.cap.primitives.OctetStringLength1Base;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class TPDataCodingSchemeImpl extends OctetStringLength1Base implements TPDataCodingScheme {

    public TPDataCodingSchemeImpl() {
        super("TPDataCodingScheme");
    }

    public TPDataCodingSchemeImpl(int data) {
        super("TPDataCodingScheme", data);
    }

    @Override
    public int getData() {
        return data;
    }

}
