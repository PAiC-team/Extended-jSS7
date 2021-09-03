
package org.restcomm.protocols.ss7.tcap.asn;

import org.mobicents.protocols.asn.Tag;

public interface Result extends Encodable {

    int _TAG_CLASS = Tag.CLASS_CONTEXT_SPECIFIC;
    boolean _TAG_PC_PRIMITIVE = false; // constructed....
    int _TAG = 0x02;

    void setResultType(ResultType resultType);

    ResultType getResultType();

}
