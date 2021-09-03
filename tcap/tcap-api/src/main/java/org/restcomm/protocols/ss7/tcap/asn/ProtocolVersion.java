package org.restcomm.protocols.ss7.tcap.asn;

import org.mobicents.protocols.asn.Tag;

public interface ProtocolVersion extends Encodable {

    int _TAG_PROTOCOL_VERSION = 0x00;
    int _TAG_PROTOCOL_VERSION_CLASS = Tag.CLASS_CONTEXT_SPECIFIC;
    boolean _TAG_PROTOCOL_VERSION_PRIMITIVE = true;

    boolean isSupportedVersion();

}
