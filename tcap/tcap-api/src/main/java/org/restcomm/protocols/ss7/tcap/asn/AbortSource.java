package org.restcomm.protocols.ss7.tcap.asn;

import org.mobicents.protocols.asn.Tag;

/**
 * @author baranowb
 *
 */
public interface AbortSource extends Encodable {

    // Q773 shows this is PRIMITIVE ..... but its coded in ASN the same way as Result.....
    // making it Primitive...., where is logic!
    int _TAG_CLASS = Tag.CLASS_CONTEXT_SPECIFIC;
    boolean _TAG_PC_PRIMITIVE = true; // constructed.... // specs show true
    int _TAG = 0x00;

    void setAbortSourceType(AbortSourceType abortSourceType);

    AbortSourceType getAbortSourceType();
}
