
package org.restcomm.protocols.ss7.cap.service.sms.primitive;

import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.TPShortMessageSpecificInfo;
import org.restcomm.protocols.ss7.cap.primitives.OctetStringLength1Base;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class TPShortMessageSpecificInfoImpl extends OctetStringLength1Base implements TPShortMessageSpecificInfo {

    public TPShortMessageSpecificInfoImpl() {
        super("TPShortMessageSpecificInfo");
    }

    public TPShortMessageSpecificInfoImpl(int data) {
        super("TPShortMessageSpecificInfo", data);
    }

    @Override
    public int getData() {
        return data;
    }

}
