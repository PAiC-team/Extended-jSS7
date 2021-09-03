
package org.restcomm.protocols.ss7.tcap.asn;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.tcap.asn.comp.PAbortCauseType;
import org.restcomm.protocols.ss7.tcap.asn.comp.TCAbortMessage;

/**
 * @author amit bhayani
 * @author baranowb
 * @author sergey vetyutnev
 *
 */
public class TCAbortMessageImpl implements TCAbortMessage {

    private static final String _OCTET_STRING_ENCODE = "US-ASCII";

    private byte[] destTxId;
    private PAbortCauseType type;
    private DialogPortion dp;

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.comp.TCAbortMessage# getDestinationTransactionId()
     */
    public byte[] getDestinationTransactionId() {
        return destTxId;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.comp.TCAbortMessage#getDialogPortion ()
     */
    public DialogPortion getDialogPortion() {
        return dp;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.comp.TCAbortMessage#getPAbortCause()
     */
    public PAbortCauseType getPAbortCause() {
        return type;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.comp.TCAbortMessage# setDestinationTransactionId()
     */
    public void setDestinationTransactionId(byte[] destinationTransactionId) {
        this.destTxId = destinationTransactionId;

    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.comp.TCAbortMessage#setDialogPortion(org.restcomm.protocols.ss7.tcap.asn.DialogPortion)
     */
    public void setDialogPortion(DialogPortion dialogPortion) {
        this.dp = dialogPortion;
        this.type = null;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.comp.TCAbortMessage#setPAbortCause(org.restcomm.protocols.ss7.tcap.asn.comp.PAbortCauseType)
     */
    public void setPAbortCause(PAbortCauseType providerAbortCauseType) {
        this.type = providerAbortCauseType;
        this.dp = null;

    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.Encodable#decode(org.mobicents.protocols.asn.AsnInputStream)
     */
    public void decode(AsnInputStream asnInputStream) throws ParseException {
        try {
            AsnInputStream localAis = asnInputStream.readSequenceStream();
            int tag = localAis.readTag();
            if (tag != _TAG_DTX || localAis.getTagClass() != Tag.CLASS_APPLICATION)
                throw new ParseException(PAbortCauseType.IncorrectTxPortion, null,
                        "Error decoding TC-Abort: Expected DestinationTransactionId, found tag: " + tag);
            this.destTxId = localAis.readOctetString();

            if (localAis.available() == 0)
                return;
            tag = localAis.readTag();
            if (localAis.getTagClass() != Tag.CLASS_APPLICATION)
                throw new ParseException(PAbortCauseType.IncorrectTxPortion, null,
                        "Error decoding TC-Abort: DialogPortion and P-AbortCause portion must has tag class CLASS_APPLICATION");

            switch (tag) {
                case DialogPortion._TAG:
                    if (localAis.isTagPrimitive())
                        throw new ParseException(PAbortCauseType.IncorrectTxPortion, null,
                                "Error decoding TC-End: DialogPortion must be constructive");
                    this.dp = TcapFactory.createDialogPortion(localAis);
                    break;

                case _TAG_P:
                    // primitive?
                    this.type = PAbortCauseType.getFromInt((int) localAis.readInteger());
                    break;

                default:
                    throw new ParseException(PAbortCauseType.IncorrectTxPortion, null,
                            "Error decoding TC-Abort: bad tag while parsing DialogPortion and P-AbortCause portion: " + tag);
            }

            if (localAis.available() > 0)
                throw new ParseException(PAbortCauseType.IncorrectTxPortion, null, "Error decoding TC-Abort: too mych data");

        } catch (IOException e) {
            throw new ParseException(PAbortCauseType.BadlyFormattedTxPortion, null, "IOException while decoding TC-Abort: "
                    + e.getMessage(), e);
        } catch (AsnException e) {
            throw new ParseException(PAbortCauseType.BadlyFormattedTxPortion, null, "AsnException while decoding TC-Abort: "
                    + e.getMessage(), e);
        }

    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.Encodable#encode(org.mobicents.protocols.asn.AsnOutputStream)
     */
    public void encode(AsnOutputStream asnOutputStream) throws EncodeException {
        try {
            asnOutputStream.writeTag(Tag.CLASS_APPLICATION, false, _TAG);
            int pos = asnOutputStream.StartContentDefiniteLength();

            asnOutputStream.writeOctetString(Tag.CLASS_APPLICATION, _TAG_DTX, this.destTxId);

            if (this.type != null)
                asnOutputStream.writeInteger(Tag.CLASS_APPLICATION, _TAG_P, this.type.getType());
            else if (this.dp != null)
                this.dp.encode(asnOutputStream);

            asnOutputStream.FinalizeContent(pos);

        } catch (IOException e) {
            throw new EncodeException("IOException while encoding TC-Abort: " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new EncodeException("AsnException while encoding TC-Abort: " + e.getMessage(), e);
        }

    }

}
