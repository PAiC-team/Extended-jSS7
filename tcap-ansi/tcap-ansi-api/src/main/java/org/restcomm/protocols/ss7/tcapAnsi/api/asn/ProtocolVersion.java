
package org.restcomm.protocols.ss7.tcapAnsi.api.asn;

public interface ProtocolVersion extends Encodable {
    int _TAG_PROTOCOL_VERSION = 26;

    int _TAG_T1_114_1996 = 0x01;
    int _TAG_T1_114_2000 = 0x02;

    boolean isSupportedVersion();

    boolean isT1_114_1996Supported();

    boolean isT1_114_2000Supported();

}
