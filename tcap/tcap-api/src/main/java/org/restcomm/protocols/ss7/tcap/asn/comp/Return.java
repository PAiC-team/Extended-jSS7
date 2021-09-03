package org.restcomm.protocols.ss7.tcap.asn.comp;

import org.mobicents.protocols.asn.Tag;

/**
 * Super interface for Returns.
 *
 * @author baranowb
 * @author amit bhayani
 *
 */
public interface Return extends Component {

    int _TAG_IID = 0x02;
    boolean _TAG_IID_PC_PRIMITIVE = true;
    int _TAG_IID_CLASS = Tag.CLASS_UNIVERSAL;

    // opt all
    void setOperationCode(OperationCode operationCode);

    OperationCode getOperationCode();

    void setParameter(Parameter parameter);

    Parameter getParameter();

}
