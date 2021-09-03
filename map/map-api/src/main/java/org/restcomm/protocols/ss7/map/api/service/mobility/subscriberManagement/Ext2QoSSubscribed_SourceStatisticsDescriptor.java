
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

/**
*
<code>
Source Statistics Descriptor, octet 14 (see 3GPP TS 23.107 [81])
Bits
4 3 2 1
In MS to network direction
0 0 0 0     unknown
0 0 0 1     speech
The network shall consider all other values as unknown.
In network to MS direction
Bits 4 to 1 of octet 14 are spare and shall be coded all 0.
The Source Statistics Descriptor value is ignored if the Traffic Class is Interactive class or Background class.
</code>
*
* @author sergey vetyutnev
*
*/
public enum Ext2QoSSubscribed_SourceStatisticsDescriptor {
    unknown(0), speech(1);

    private int code;

    private Ext2QoSSubscribed_SourceStatisticsDescriptor(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static Ext2QoSSubscribed_SourceStatisticsDescriptor getInstance(int code) {
        switch (code) {
        case 0:
            return Ext2QoSSubscribed_SourceStatisticsDescriptor.unknown;
        case 1:
            return Ext2QoSSubscribed_SourceStatisticsDescriptor.speech;
        default:
            return Ext2QoSSubscribed_SourceStatisticsDescriptor.unknown;
        }
    }

}
