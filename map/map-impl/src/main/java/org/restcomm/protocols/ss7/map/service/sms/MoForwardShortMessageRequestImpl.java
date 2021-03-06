
package org.restcomm.protocols.ss7.map.service.sms;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPMessageType;
import org.restcomm.protocols.ss7.map.api.MAPOperationCode;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.sms.MoForwardShortMessageRequest;
import org.restcomm.protocols.ss7.map.api.service.sms.SM_RP_DA;
import org.restcomm.protocols.ss7.map.api.service.sms.SM_RP_OA;
import org.restcomm.protocols.ss7.map.api.service.sms.SmsSignalInfo;
import org.restcomm.protocols.ss7.map.primitives.IMSIImpl;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class MoForwardShortMessageRequestImpl extends SmsMessageImpl implements MoForwardShortMessageRequest {

    private SM_RP_DA sm_RP_DA;
    private SM_RP_OA sm_RP_OA;
    private SmsSignalInfoImpl sm_RP_UI;
    private MAPExtensionContainer extensionContainer;
    private IMSI imsi;

    protected String _PrimitiveName = "MoForwardShortMessageRequest";

    public MoForwardShortMessageRequestImpl() {
    }

    public MoForwardShortMessageRequestImpl(SM_RP_DA sm_RP_DA, SM_RP_OA sm_RP_OA, SmsSignalInfo sm_RP_UI,
            MAPExtensionContainer extensionContainer, IMSI imsi) {
        this.sm_RP_DA = sm_RP_DA;
        this.sm_RP_OA = sm_RP_OA;
        this.sm_RP_UI = (SmsSignalInfoImpl) sm_RP_UI;
        this.extensionContainer = extensionContainer;
        this.imsi = imsi;
    }

    public MAPMessageType getMessageType() {
        return MAPMessageType.moForwardSM_Request;
    }

    public int getOperationCode() {
        return MAPOperationCode.mo_forwardSM;
    }

    public SM_RP_DA getSM_RP_DA() {
        return this.sm_RP_DA;
    }

    public SM_RP_OA getSM_RP_OA() {
        return this.sm_RP_OA;
    }

    public SmsSignalInfo getSM_RP_UI() {
        return this.sm_RP_UI;
    }

    public MAPExtensionContainer getExtensionContainer() {
        return this.extensionContainer;
    }

    public IMSI getIMSI() {
        return this.imsi;
    }

    public int getTag() throws MAPException {
        return Tag.SEQUENCE;
    }

    public int getTagClass() {
        return Tag.CLASS_UNIVERSAL;
    }

    public boolean getIsPrimitive() {
        return false;
    }

    public void decodeAll(AsnInputStream asnInputStream) throws MAPParsingComponentException {
        try {
            int length = asnInputStream.readLength();
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    public void decodeData(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException {
        try {
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    private void _decode(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {
        this.sm_RP_DA = null;
        this.sm_RP_OA = null;
        this.sm_RP_UI = null;
        this.extensionContainer = null;
        this.imsi = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        int num = 0;
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            switch (num) {
                case 0:
                    // SM_RP_DA
                    if (ais.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC || !ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ": Parameter 0 bad tag class or not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.sm_RP_DA = new SM_RP_DAImpl();
                    ((SM_RP_DAImpl) this.sm_RP_DA).decodeAll(ais);
                    break;

                case 1:
                    // SM_RP_OA
                    if (ais.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC || !ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ": Parameter 1 bad tag class or not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.sm_RP_OA = new SM_RP_OAImpl();
                    ((SM_RP_OAImpl) this.sm_RP_OA).decodeAll(ais);
                    break;

                case 2:
                    // sm-RP-UI
                    if (ais.getTagClass() != Tag.CLASS_UNIVERSAL || !ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ": Parameter 2 bad tag class or not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    if (tag != Tag.STRING_OCTET)
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ": Parameter 2 tag must be STRING_OCTET, found: " + tag,
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.sm_RP_UI = new SmsSignalInfoImpl();
                    this.sm_RP_UI.decodeAll(ais);
                    break;

                default:
                    if (tag == Tag.SEQUENCE && ais.getTagClass() == Tag.CLASS_UNIVERSAL) {
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter extensionContainer is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);
                        this.extensionContainer = new MAPExtensionContainerImpl();
                        ((MAPExtensionContainerImpl) this.extensionContainer).decodeAll(ais);
                    } else if (tag == Tag.STRING_OCTET && ais.getTagClass() == Tag.CLASS_UNIVERSAL) {
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter imsi is not primitive", MAPParsingComponentExceptionReason.MistypedParameter);
                        this.imsi = new IMSIImpl();
                        ((IMSIImpl) this.imsi).decodeAll(ais);
                    } else {
                        ais.advanceElement();
                    }
                    break;
            }

            num++;
        }

        if (num < 3)
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": Needs at least 3 mandatory parameters, found " + num,
                    MAPParsingComponentExceptionReason.MistypedParameter);
    }

    public void encodeAll(AsnOutputStream asnOutputStream) throws MAPException {
        this.encodeAll(asnOutputStream, Tag.CLASS_UNIVERSAL, Tag.SEQUENCE);
    }

    public void encodeAll(AsnOutputStream asnOutputStream, int tagClass, int tag) throws MAPException {

        try {
            asnOutputStream.writeTag(tagClass, false, tag);
            int pos = asnOutputStream.StartContentDefiniteLength();
            this.encodeData(asnOutputStream);
            asnOutputStream.FinalizeContent(pos);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {

        if (this.sm_RP_DA == null || this.sm_RP_OA == null || this.sm_RP_UI == null)
            throw new MAPException("sm_RP_DA,sm_RP_OA and sm_RP_UI must not be null");

        ((SM_RP_DAImpl) this.sm_RP_DA).encodeAll(asnOutputStream);
        ((SM_RP_OAImpl) this.sm_RP_OA).encodeAll(asnOutputStream);
        this.sm_RP_UI.encodeAll(asnOutputStream);

        if (this.extensionContainer != null)
            ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOutputStream);
        if (this.imsi != null)
            ((IMSIImpl) this.imsi).encodeAll(asnOutputStream);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.getMAPDialog() != null) {
            sb.append("DialogId=").append(this.getMAPDialog().getLocalDialogId());
        }

        if (this.sm_RP_DA != null) {
            sb.append(", sm_RP_DA=");
            sb.append(this.sm_RP_DA.toString());
        }
        if (this.sm_RP_OA != null) {
            sb.append(", sm_RP_OA=");
            sb.append(this.sm_RP_OA.toString());
        }
        if (this.sm_RP_UI != null) {
            sb.append(", sm_RP_UI=[");
            sb.append(this.sm_RP_UI.toString());
            sb.append("]");
        }
        if (this.extensionContainer != null) {
            sb.append(", extensionContainer=");
            sb.append(this.extensionContainer.toString());
        }
        if (this.imsi != null) {
            sb.append(", imsi=");
            sb.append(this.imsi.toString());
        }

        sb.append("]");

        return sb.toString();
    }
}
