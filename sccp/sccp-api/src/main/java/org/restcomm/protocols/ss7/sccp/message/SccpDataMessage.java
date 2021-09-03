package org.restcomm.protocols.ss7.sccp.message;

import org.restcomm.protocols.ss7.sccp.parameter.HopCounter;
import org.restcomm.protocols.ss7.sccp.parameter.Importance;
import org.restcomm.protocols.ss7.sccp.parameter.ProtocolClass;
import org.restcomm.protocols.ss7.sccp.parameter.Segmentation;

/**
 *
 * This interface represents a SCCP message for connectionless data transfer (UDT, XUDT and LUDT)
 *
 * @author sergey vetyutnev
 *
 */
public interface SccpDataMessage extends SccpAddressedMessage {

    ProtocolClass getProtocolClass();

    HopCounter getHopCounter();

    byte[] getData();

    Segmentation getSegmentation();

    Importance getImportance();

    void setProtocolClass(ProtocolClass v);

    void setHopCounter(HopCounter hopCounter);

    void setData(byte[] data);

    void setImportance(Importance p);

}
