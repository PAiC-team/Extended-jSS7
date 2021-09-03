package org.restcomm.protocols.ss7.tcap.asn.comp;

import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.tcap.asn.Encodable;

/**
 * @author baranowb
 *
 */
public interface Component extends Encodable {

    int _COMPONENT_TAG = 0x0C;
    boolean _COMPONENT_TAG_PC_PRIMITIVE = false;
    int _COMPONENT_TAG_CLASS = Tag.CLASS_APPLICATION;

    // this is doubled by each interface,
    void setInvokeId(Long invokeId);

    Long getInvokeId();

    ComponentType getType();

}
