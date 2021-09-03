
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.PDNType;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.PDNTypeValue;
import org.restcomm.protocols.ss7.map.primitives.OctetStringLength1Base;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class PDNTypeImpl extends OctetStringLength1Base implements PDNType {

    public PDNTypeImpl() {
        super("PDNType");
    }

    public PDNTypeImpl(int data) {
        super("PDNType", data);
    }

    public PDNTypeImpl(PDNTypeValue value) {
        super("PDNType", value != null ? value.getCode() : 0);
    }

    @Override
    public int getData() {
        return data;
    }

    public PDNTypeValue getPDNTypeValue() {
        return PDNTypeValue.getInstance(this.data);
    }

}
