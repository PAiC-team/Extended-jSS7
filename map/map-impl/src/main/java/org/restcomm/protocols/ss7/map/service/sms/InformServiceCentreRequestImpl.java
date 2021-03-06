
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
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.sms.InformServiceCentreRequest;
import org.restcomm.protocols.ss7.map.api.service.sms.MWStatus;
import org.restcomm.protocols.ss7.map.primitives.ISDNAddressStringImpl;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class InformServiceCentreRequestImpl extends SmsMessageImpl implements InformServiceCentreRequest {

    protected static final int _TAG_AdditionalAbsentSubscriberDiagnosticSM = 0;

    private ISDNAddressString storedMSISDN;
    private MWStatus mwStatus;
    private MAPExtensionContainer extensionContainer;
    private Integer absentSubscriberDiagnosticSM;
    private Integer additionalAbsentSubscriberDiagnosticSM;

    public InformServiceCentreRequestImpl() {
    }

    public InformServiceCentreRequestImpl(ISDNAddressString storedMSISDN, MWStatus mwStatus,
            MAPExtensionContainer extensionContainer, Integer absentSubscriberDiagnosticSM,
            Integer additionalAbsentSubscriberDiagnosticSM) {
        this.storedMSISDN = storedMSISDN;
        this.mwStatus = mwStatus;
        this.extensionContainer = extensionContainer;
        this.absentSubscriberDiagnosticSM = absentSubscriberDiagnosticSM;
        this.additionalAbsentSubscriberDiagnosticSM = additionalAbsentSubscriberDiagnosticSM;
    }

    public MAPMessageType getMessageType() {
        return MAPMessageType.InformServiceCentre_Request;
    }

    public int getOperationCode() {
        return MAPOperationCode.informServiceCentre;
    }

    public ISDNAddressString getStoredMSISDN() {
        return this.storedMSISDN;
    }

    public MWStatus getMwStatus() {
        return this.mwStatus;
    }

    public MAPExtensionContainer getExtensionContainer() {
        return this.extensionContainer;
    }

    public Integer getAbsentSubscriberDiagnosticSM() {
        return this.absentSubscriberDiagnosticSM;
    }

    public Integer getAdditionalAbsentSubscriberDiagnosticSM() {
        return this.additionalAbsentSubscriberDiagnosticSM;
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
            throw new MAPParsingComponentException("IOException when decoding InformServiceCentreRequest: " + e.getMessage(),
                    e, MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding InformServiceCentreRequest: " + e.getMessage(),
                    e, MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    public void decodeData(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException {
        try {
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding InformServiceCentreRequest: " + e.getMessage(),
                    e, MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding InformServiceCentreRequest: " + e.getMessage(),
                    e, MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    private void _decode(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {
        this.storedMSISDN = null;
        this.mwStatus = null;
        this.extensionContainer = null;
        this.absentSubscriberDiagnosticSM = null;
        this.additionalAbsentSubscriberDiagnosticSM = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_UNIVERSAL) {

                switch (tag) {
                    case Tag.STRING_OCTET:
                        // storedMSISDN
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException(
                                    "Error while decoding informServiceCentreRequest: Parameter storedMSISDN is not primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);
                        storedMSISDN = new ISDNAddressStringImpl();
                        ((ISDNAddressStringImpl) storedMSISDN).decodeAll(ais);
                        break;

                    case Tag.STRING_BIT:
                        // mw-Status
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException(
                                    "Error while decoding informServiceCentreRequest: Parameter mw-Status is not primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);
                        mwStatus = new MWStatusImpl();
                        ((MWStatusImpl) mwStatus).decodeAll(ais);
                        break;

                    case Tag.SEQUENCE:
                        // extensionContainer
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException(
                                    "Error while decoding reportSMDeliveryStatusRequest: Parameter extensionContainer is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);
                        extensionContainer = new MAPExtensionContainerImpl();
                        ((MAPExtensionContainerImpl) extensionContainer).decodeAll(ais);
                        break;

                    case Tag.INTEGER:
                        // absentSubscriberDiagnosticSM
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException(
                                    "Error while decoding informServiceCentreRequest: Parameter absentSubscriberDiagnosticSM is not primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);
                        absentSubscriberDiagnosticSM = (int) ais.readInteger();
                        break;

                    default:
                        ais.advanceElement();
                        break;
                }
            } else if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {

                switch (tag) {
                    case InformServiceCentreRequestImpl._TAG_AdditionalAbsentSubscriberDiagnosticSM:
                        // additionalAbsentSubscriberDiagnosticSM
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException(
                                    "Error while decoding informServiceCentreRequest: Parameter deliveryOutcomeIndicator is not primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);
                        additionalAbsentSubscriberDiagnosticSM = (int) ais.readInteger();
                        break;

                    default:
                        ais.advanceElement();
                        break;
                }
            } else {
                ais.advanceElement();
            }
        }
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
            throw new MAPException("AsnException when encoding InformServiceCentreRequest: " + e.getMessage(), e);
        }
    }

    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        if (this.storedMSISDN != null)
            ((ISDNAddressStringImpl) this.storedMSISDN).encodeAll(asnOutputStream);
        if (this.mwStatus != null)
            ((MWStatusImpl) this.mwStatus).encodeAll(asnOutputStream);
        if (this.extensionContainer != null)
            ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOutputStream);
        try {
            if (this.absentSubscriberDiagnosticSM != null)
                asnOutputStream.writeInteger(this.absentSubscriberDiagnosticSM);
            if (this.additionalAbsentSubscriberDiagnosticSM != null)
                asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC,
                        InformServiceCentreRequestImpl._TAG_AdditionalAbsentSubscriberDiagnosticSM,
                        this.additionalAbsentSubscriberDiagnosticSM);
        } catch (IOException e) {
            throw new MAPException("IOException when encoding InformServiceCentreRequest: " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding InformServiceCentreRequest: " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("InformServiceCentreRequest [");

        if (this.getMAPDialog() != null) {
            sb.append("DialogId=").append(this.getMAPDialog().getLocalDialogId());
        }

        if (this.storedMSISDN != null) {
            sb.append(", storedMSISDN=");
            sb.append(this.storedMSISDN.toString());
        }
        if (this.mwStatus != null) {
            sb.append(", mwStatus=");
            sb.append(this.mwStatus.toString());
        }
        if (this.extensionContainer != null) {
            sb.append(", extensionContainer=");
            sb.append(this.extensionContainer.toString());
        }
        if (this.absentSubscriberDiagnosticSM != null) {
            sb.append(", absentSubscriberDiagnosticSM=");
            sb.append(this.absentSubscriberDiagnosticSM.toString());
        }
        if (this.additionalAbsentSubscriberDiagnosticSM != null) {
            sb.append(", additionalAbsentSubscriberDiagnosticSM=");
            sb.append(this.additionalAbsentSubscriberDiagnosticSM.toString());
        }

        sb.append("]");

        return sb.toString();
    }

}
