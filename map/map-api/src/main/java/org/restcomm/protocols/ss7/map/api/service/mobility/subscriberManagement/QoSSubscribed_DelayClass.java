
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

/**
*
<code>
Bits
6 5 4
In MS to network direction:
0 0 0 Subscribed delay class
In network to MS direction:
0 0 0 Reserved
In MS to network direction and in network to MS direction:
0 0 1 Delay class 1
0 1 0 Delay class 2
0 1 1 Delay class 3
1 0 0 Delay class 4 (best effort)
1 1 1 Reserved
All other values are interpreted as Delay class 4 (best effort) in this version
of the protocol.
</code>
*
* @author sergey vetyutnev
*
*/
public enum QoSSubscribed_DelayClass {
    subscribedDelayClass_Reserved(0), delay_Class_1(1), delay_Class_2(2), delay_Class_3(3), delay_Class_4_bestEffort(4), reserved(7);

    private int code;

    private QoSSubscribed_DelayClass(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static QoSSubscribed_DelayClass getInstance(int code) {
        switch (code) {
        case 0:
            return QoSSubscribed_DelayClass.subscribedDelayClass_Reserved;
        case 1:
            return QoSSubscribed_DelayClass.delay_Class_1;
        case 2:
            return QoSSubscribed_DelayClass.delay_Class_2;
        case 3:
            return QoSSubscribed_DelayClass.delay_Class_3;
        case 4:
            return QoSSubscribed_DelayClass.delay_Class_4_bestEffort;
        default:
            return QoSSubscribed_DelayClass.reserved;
        }
    }

}
