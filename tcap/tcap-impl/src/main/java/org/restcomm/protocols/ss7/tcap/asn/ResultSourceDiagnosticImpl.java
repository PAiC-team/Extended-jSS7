
package org.restcomm.protocols.ss7.tcap.asn;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.tcap.asn.comp.PAbortCauseType;

/**
 * @author baranowb
 * @author sergey vetyutnev
 *
 */
public class ResultSourceDiagnosticImpl implements ResultSourceDiagnostic {

    private DialogServiceProviderType providerType;
    private DialogServiceUserType userType;

    /*
     * (non-Javadoc)
     *
     * @seeorg.restcomm.protocols.ss7.tcap.asn.ResultSourceDiagnostic# getDialogServiceProviderType()
     */
    public DialogServiceProviderType getDialogServiceProviderType() {
        return providerType;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.ResultSourceDiagnostic# getDialogServiceUserType()
     */
    public DialogServiceUserType getDialogServiceUserType() {
        return userType;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.ResultSourceDiagnostic# setDialogServiceProviderType
     * (org.restcomm.protocols.ss7.tcap.asn.DialogServiceProviderType)
     */
    public void setDialogServiceProviderType(DialogServiceProviderType dialogServiceProviderType) {
        this.providerType = dialogServiceProviderType;
        this.userType = null;
    }

    /*
     * (non-Javadoc)
     *
     * @seeorg.restcomm.protocols.ss7.tcap.asn.ResultSourceDiagnostic# setDialogServiceUserType
     * (org.restcomm.protocols.ss7.tcap.asn.DialogServiceUserType)
     */
    public void setDialogServiceUserType(DialogServiceUserType dialogServiceUserType) {
        this.userType = dialogServiceUserType;
        this.providerType = null;
    }

    public String toString() {
        return "ResultSourceDiagnostic[providerType=" + providerType + ", userType=" + userType + "]";
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.Encodable#decode(org.mobicents.protocols .asn.AsnInputStream)
     */
    public void decode(AsnInputStream asnInputStream) throws ParseException {
        try {
            AsnInputStream localAis = asnInputStream.readSequenceStream();

            // int make read whole thing?
            int tag = localAis.readTag();
            if (localAis.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC)
                throw new ParseException(PAbortCauseType.IncorrectTxPortion, null,
                        "Error while decoding AARE-apdu.result-dource-diagnostic sequence part: bad tag class: tagClass="
                                + localAis.getTagClass());

            switch (tag) {
                case _TAG_U:
                    AsnInputStream localAis2 = localAis.readSequenceStream();
                    tag = localAis2.readTag();
                    if (tag != Tag.INTEGER || localAis2.getTagClass() != Tag.CLASS_UNIVERSAL)
                        throw new ParseException(PAbortCauseType.IncorrectTxPortion, null,
                                "Error while decoding AARE-apdu.result-dource-diagnostic integer part: bad tag or tag class class: tagClass="
                                        + localAis.getTagClass() + ", tag=" + tag);
                    long t = localAis2.readInteger();
                    this.userType = DialogServiceUserType.getFromInt(t);
                    break;

                case _TAG_P:
                    localAis2 = localAis.readSequenceStream();
                    tag = localAis2.readTag();
                    if (tag != Tag.INTEGER || localAis2.getTagClass() != Tag.CLASS_UNIVERSAL)
                        throw new ParseException(PAbortCauseType.IncorrectTxPortion, null,
                                "Error while decoding AARE-apdu.result-dource-diagnostic integer part: bad tag or tag class class: tagClass="
                                        + localAis.getTagClass() + ", tag=" + tag);
                    t = localAis2.readInteger();
                    this.providerType = DialogServiceProviderType.getFromInt(t);
                    break;

                default:
                    throw new ParseException(PAbortCauseType.IncorrectTxPortion, null,
                            "Error while decoding AARE-apdu.result-dource-diagnostic sequence part: bad tag: tag=" + tag);
            }
        } catch (IOException e) {
            throw new ParseException(PAbortCauseType.BadlyFormattedTxPortion, null,
                    "IOException while decoding ResultSourceDiagnostic: " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new ParseException(PAbortCauseType.BadlyFormattedTxPortion, null,
                    "AsnException while decoding ResultSourceDiagnostic: " + e.getMessage(), e);
        }

        // tag can have on of two values =
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.Encodable#encode(org.mobicents.protocols .asn.AsnOutputStream)
     */
    public void encode(AsnOutputStream asnOutputStream) throws EncodeException {
        if (this.userType == null && this.providerType == null)
            throw new EncodeException("Error encoding ResultSourceDiagnostic: Value not set");

        try {
            asnOutputStream.writeTag(Tag.CLASS_CONTEXT_SPECIFIC, false, _TAG);
            int pos = asnOutputStream.StartContentDefiniteLength();

            if (this.userType != null) {
                asnOutputStream.writeTag(Tag.CLASS_CONTEXT_SPECIFIC, false, _TAG_U);
                int pos2 = asnOutputStream.StartContentDefiniteLength();
                asnOutputStream.writeInteger(this.userType.getType());
                asnOutputStream.FinalizeContent(pos2);
            } else {
                asnOutputStream.writeTag(Tag.CLASS_CONTEXT_SPECIFIC, false, _TAG_P);
                int pos2 = asnOutputStream.StartContentDefiniteLength();
                asnOutputStream.writeInteger(this.providerType.getType());
                asnOutputStream.FinalizeContent(pos2);
            }

            asnOutputStream.FinalizeContent(pos);

        } catch (IOException e) {
            throw new EncodeException("IOException while encoding ResultSourceDiagnostic: " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new EncodeException("AsnException while encoding ResultSourceDiagnostic: " + e.getMessage(), e);
        }

    }

}
