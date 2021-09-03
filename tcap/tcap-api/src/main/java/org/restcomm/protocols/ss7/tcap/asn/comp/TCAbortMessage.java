
package org.restcomm.protocols.ss7.tcap.asn.comp;

import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.tcap.asn.DialogPortion;
import org.restcomm.protocols.ss7.tcap.asn.Encodable;

/**
 * This message represents Abort messages (P and U). According to Q.773:<br>
 *
 * <pre>
 * Abort ::= SEQUENCE {
 * dtid DestTransactionID,
 * reason CHOICE
 * { p-abortCause P-AbortCause,
 * u-abortCause DialoguePortion } OPTIONAL
 * }
 * </pre>
 *
 * Cryptic ASN say:
 * <ul>
 * <li><b>TC-U-Abort</b> - if abort APDU is present</li>
 * <li><b>TC-P-Abort</b> - if P-Abort-Cause is present</li>
 * </ul>
 *
 * @author baranowb
 *
 */
public interface TCAbortMessage extends Encodable {

    int _TAG = 0x07;
    boolean _TAG_PC_PRIMITIVE = false;
    int _TAG_CLASS = Tag.CLASS_APPLICATION;

    int _TAG_P = 0x0A;
    boolean _TAG_P_PC_PRIMITIVE = true;
    int _TAG_CLASS_P = Tag.CLASS_APPLICATION;

    int _TAG_DTX = 0x09;
    boolean _TAG_DTX_PC_PRIMITIVE = true;
    int _TAG_CLASS_DTX = Tag.CLASS_APPLICATION;

    // mandatory
    byte[] getDestinationTransactionId();

    void setDestinationTransactionId(byte[] destinationTransactionId);

    // optionals
    PAbortCauseType getPAbortCause();

    void setPAbortCause(PAbortCauseType providerAbortCause);

    DialogPortion getDialogPortion();

    void setDialogPortion(DialogPortion dialogPortion);

    // External getUserDefinedReason();
    //
    // void setUserDefinedReason(External dp);
}
