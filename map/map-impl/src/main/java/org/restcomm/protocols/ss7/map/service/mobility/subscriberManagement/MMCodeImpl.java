
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.MMCode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.MMCodeValue;
import org.restcomm.protocols.ss7.map.primitives.OctetStringLength1Base;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class MMCodeImpl extends OctetStringLength1Base implements MMCode {

    public MMCodeImpl() {
        super("MMCode");
    }

    public MMCodeImpl(MMCodeValue value) {
        super("MMCode", value != null ? value.getCode() : 0);
    }

    @Override
    public MMCodeValue getMMCodeValue() {
        return MMCodeValue.getInstance(data & 0xFF);
    }

}
