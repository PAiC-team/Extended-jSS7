
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.CSAllocationRetentionPriority;
import org.restcomm.protocols.ss7.map.primitives.OctetStringLength1Base;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class CSAllocationRetentionPriorityImpl extends OctetStringLength1Base implements CSAllocationRetentionPriority {

    public CSAllocationRetentionPriorityImpl() {
        super("CSAllocationRetentionPriority");
    }

    public CSAllocationRetentionPriorityImpl(int data) {
        super("CSAllocationRetentionPriority", data);
    }

    public int getData() {
        return data;
    }

}
