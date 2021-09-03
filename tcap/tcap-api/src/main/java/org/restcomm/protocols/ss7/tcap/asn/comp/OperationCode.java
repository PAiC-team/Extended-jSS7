package org.restcomm.protocols.ss7.tcap.asn.comp;

import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.tcap.asn.Encodable;

/**
 * @author baranowb
 *
 */
public interface OperationCode extends Encodable {

    int _TAG_LOCAL = Tag.INTEGER;
    int _TAG_GLOBAL = Tag.OBJECT_IDENTIFIER;
    int _TAG_CLASS = Tag.CLASS_UNIVERSAL;
    boolean _TAG_PRIMITIVE = true;

    // it is integer, but two different tags denote this.

    // void setOperationType(OperationCodeType t);
    OperationCodeType getOperationType();

    void setLocalOperationCode(Long localOperationCode);

    void setGlobalOperationCode(long[] globalOperationCode);

    Long getLocalOperationCode();

    long[] getGlobalOperationCode();

}
