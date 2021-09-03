
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.LongGroupId;
import org.restcomm.protocols.ss7.map.primitives.TbcdStringWithFiller;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class LongGroupIdImpl extends TbcdStringWithFiller implements LongGroupId {

    public LongGroupIdImpl() {
        super(4, 4, "LongGroupId");
    }

    public LongGroupIdImpl(String data) {
        super(4, 4, "LongGroupId", data);
    }

    @Override
    public String getLongGroupId() {
        return this.data;
    }
}
