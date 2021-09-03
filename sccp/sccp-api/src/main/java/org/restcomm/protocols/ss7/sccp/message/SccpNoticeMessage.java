package org.restcomm.protocols.ss7.sccp.message;

import org.restcomm.protocols.ss7.sccp.parameter.Importance;
import org.restcomm.protocols.ss7.sccp.parameter.ReturnCause;
import org.restcomm.protocols.ss7.sccp.parameter.Segmentation;

/**
 *
 * This interface represents SCCP a connectionless notice message (UDTS, XUDTS and LUDTS)
 *
 * @author sergey vetyutnev
 *
 */
public interface SccpNoticeMessage extends SccpAddressedMessage {

    ReturnCause getReturnCause();

    byte[] getData();

    Segmentation getSegmentation();

    Importance getImportance();

    void setReturnCause(ReturnCause rc);

    void setData(byte[] data);

    void setImportance(Importance p);

}
