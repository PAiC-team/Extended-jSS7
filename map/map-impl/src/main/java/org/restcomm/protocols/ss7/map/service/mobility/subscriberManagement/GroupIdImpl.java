
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.GroupId;
import org.restcomm.protocols.ss7.map.primitives.TbcdStringWithFiller;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class GroupIdImpl extends TbcdStringWithFiller implements GroupId {

    public GroupIdImpl() {
        super(3, 3, "GroupId");
    }

    public GroupIdImpl(String data) {
        super(3, 3, "GroupId", data);
    }

    @Override
    public String getGroupId() {
        return this.data;
    }

}
