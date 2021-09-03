
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

/**
*
<code>
Bits
3 2 1
In MS to network direction:
0 0 0 Subscribed reliability class
In network to MS direction:
0 0 0 Reserved
In MS to network direction and in network to MS direction:
0 0 1 Unused. If received, it shall be interpreted as '010' (Note)
0 1 0 Unacknowledged GTP; Acknowledged LLC and RLC, Protected data
0 1 1 Unacknowledged GTP and LLC; Acknowledged RLC, Protected data
1 0 0 Unacknowledged GTP, LLC, and RLC, Protected data
1 0 1 Unacknowledged GTP, LLC, and RLC, Unprotected data
1 1 1 Reserved
All other values are interpreted as Unacknowledged GTP and LLC; Acknowledged RLC, Protected data in this version of
the protocol.
NOTE: this value was allocated in earlier versions of the protocol.
Delay class, octet 3 (see 3GPP TS 22.060 and 3GPP TS 23.107)
</code>
*
* @author sergey vetyutnev
*
*/
public enum QoSSubscribed_ReliabilityClass {
    subscribedReliabilityClass_Reserved(0), unused_1(1), unacknowledgedGtp_AcknowledgedLlcAndRlc_ProtectedData(2), unacknowledgedGtpAndLlc_AcknowledgedRlc_ProtectedData(
            3), unacknowledgedGtpLlcAndRlc_ProtectedData(4), unacknowledgedGtpLlcAndRlc_UnprotectedData(5), reserved_6(6), reserved_7(7);

    private int code;

    private QoSSubscribed_ReliabilityClass(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static QoSSubscribed_ReliabilityClass getInstance(int code) {
        switch (code) {
        case 0:
            return QoSSubscribed_ReliabilityClass.subscribedReliabilityClass_Reserved;
        case 1:
            return QoSSubscribed_ReliabilityClass.unused_1;
        case 2:
            return QoSSubscribed_ReliabilityClass.unacknowledgedGtp_AcknowledgedLlcAndRlc_ProtectedData;
        case 3:
            return QoSSubscribed_ReliabilityClass.unacknowledgedGtpAndLlc_AcknowledgedRlc_ProtectedData;
        case 4:
            return QoSSubscribed_ReliabilityClass.unacknowledgedGtpLlcAndRlc_ProtectedData;
        case 5:
            return QoSSubscribed_ReliabilityClass.unacknowledgedGtpLlcAndRlc_UnprotectedData;
        case 6:
            return QoSSubscribed_ReliabilityClass.reserved_6;
        default:
            return QoSSubscribed_ReliabilityClass.reserved_7;
        }
    }

}
