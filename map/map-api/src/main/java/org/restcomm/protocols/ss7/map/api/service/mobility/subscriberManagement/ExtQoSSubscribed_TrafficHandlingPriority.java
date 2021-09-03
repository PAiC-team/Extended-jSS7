
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

/**
*
<code>
Traffic handling priority, octet 11 (see 3GPP TS 23.107 [81])
Bits
2 1
In MS to network direction:
0 0     Subscribed traffic handling priority
In network to MS direction:
0 0     Reserved
In MS to network direction and in network to MS direction:
0 1     Priority level 1
1 0     Priority level 2
1 1     Priority level 3
The Traffic handling priority value is ignored if the Traffic Class is Conversational class, Streaming class or Background class.
</code>
*
* @author sergey vetyutnev
*
*/
public enum ExtQoSSubscribed_TrafficHandlingPriority {
    subscribedTrafficHandlingPriority_Reserved(0), priorityLevel_1(1), priorityLevel_2(2), priorityLevel_3(3);

    private int code;

    private ExtQoSSubscribed_TrafficHandlingPriority(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static ExtQoSSubscribed_TrafficHandlingPriority getInstance(int code) {
        switch (code) {
        case 0:
            return ExtQoSSubscribed_TrafficHandlingPriority.subscribedTrafficHandlingPriority_Reserved;
        case 1:
            return ExtQoSSubscribed_TrafficHandlingPriority.priorityLevel_1;
        case 2:
            return ExtQoSSubscribed_TrafficHandlingPriority.priorityLevel_2;
        case 3:
            return ExtQoSSubscribed_TrafficHandlingPriority.priorityLevel_3;
        default:
            return null;
        }
    }

}
