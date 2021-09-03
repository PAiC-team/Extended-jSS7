
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
import org.restcomm.protocols.ss7.map.api.service.sms.AlertReason;
import org.restcomm.protocols.ss7.map.api.service.sms.ReadyForSMRequest;
import org.restcomm.protocols.ss7.map.primitives.IMSIImpl;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;

/**
*
* @author sergey vetyutnev
*
*/
public class ReadyForSMRequestImpl extends SmsMessageImpl implements ReadyForSMRequest {

    protected static final int _TAG_imsi = 0;
    protected static final int _TAG_additionalAlertReasonIndicator = 1;

    public static final String _PrimitiveName = "ReadyForSMRequest";

    private IMSI imsi;
    private AlertReason alertReason;
    private boolean alertReasonIndicator;
    private MAPExtensionContainer extensionContainer;
    private boolean additionalAlertReasonIndicator;

    public ReadyForSMRequestImpl() {
    }

    public ReadyForSMRequestImpl(IMSI imsi, AlertReason alertReason, boolean alertReasonIndicator, MAPExtensionContainer extensionContainer,
            boolean additionalAlertReasonIndicator) {
        this.imsi = imsi;
        this.alertReason = alertReason;
        this.alertReasonIndicator = alertReasonIndicator;
        this.extensionContainer = extensionContainer;
        this.additionalAlertReasonIndicator = additionalAlertReasonIndicator;
    }

    @Override
    public MAPMessageType getMessageType() {
        return MAPMessageType.readyForSM_Request;
    }

    @Override
    public int getOperationCode() {
        return MAPOperationCode.readyForSM;
    }

    @Override
    public IMSI getImsi() {
        return imsi;
    }

    @Override
    public AlertReason getAlertReason() {
        return alertReason;
    }

    @Override
    public boolean getAlertReasonIndicator() {
        return alertReasonIndicator;
    }

    @Override
    public MAPExtensionContainer getExtensionContainer() {
        return extensionContainer;
    }

    @Override
    public boolean getAdditionalAlertReasonIndicator() {
        return additionalAlertReasonIndicator;
    }

    @Override
    public int getTag() throws MAPException {
        return Tag.SEQUENCE;
    }

    @Override
    public int getTagClass() {
        return Tag.CLASS_UNIVERSAL;
    }

    @Override
    public boolean getIsPrimitive() {
        return false;
    }

    @Override
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

    @Override
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
        this.imsi = null;
        this.alertReason = null;
        this.alertReasonIndicator = false;
        this.extensionContainer = null;
        this.additionalAlertReasonIndicator = false;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        int num = 0;
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_UNIVERSAL) {
                switch (tag) {
                case Tag.ENUMERATED:
                    if (!ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ".alertReason: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    int i1 = (int)ais.readInteger();
                    this.alertReason = AlertReason.getInstance(i1);
                    break;
                case Tag.NULL:
                    if (!ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ".alertReasonIndicator: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    ais.readNull();
                    this.alertReasonIndicator = true;
                    break;
                case Tag.SEQUENCE:
                    if (ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".extensionContainer: Parameter extensionContainer is primitive", MAPParsingComponentExceptionReason.MistypedParameter);
                    this.extensionContainer = new MAPExtensionContainerImpl();
                    ((MAPExtensionContainerImpl) this.extensionContainer).decodeAll(ais);
                    break;

                default:
                    ais.advanceElement();
                    break;
                }
            } else if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                case _TAG_imsi:
                    if (!ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ".imsi: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.imsi = new IMSIImpl();
                    ((IMSIImpl) this.imsi).decodeAll(ais);
                    break;
                case _TAG_additionalAlertReasonIndicator:
                    if (!ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ".additionalAlertReasonIndicator: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    ais.readNull();
                    this.additionalAlertReasonIndicator = true;
                    break;

                default:
                    ais.advanceElement();
                    break;
                }
            } else {
                ais.advanceElement();
            }

            num++;
        }

        if (this.imsi == null)
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ": Parameter imsi is mandator but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);
        if (this.alertReason == null)
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ": Parameter alertReason is mandator but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);
    }

    @Override
    public void encodeAll(AsnOutputStream asnOutputStream) throws MAPException {
        this.encodeAll(asnOutputStream, this.getTagClass(), this.getTag());
    }

    @Override
    public void encodeAll(AsnOutputStream asnOutputStream, int tagClass, int tag) throws MAPException {
        try {
            asnOutputStream.writeTag(tagClass, this.getIsPrimitive(), tag);
            int pos = asnOutputStream.StartContentDefiniteLength();
            this.encodeData(asnOutputStream);
            asnOutputStream.FinalizeContent(pos);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        try {
            if (this.imsi == null)
                throw new MAPException("IMSI parameter must not be null");
            if (this.alertReason == null)
                throw new MAPException("alertReason parameter must not be null");

            ((IMSIImpl) this.imsi).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_imsi);
            asnOutputStream.writeInteger(Tag.CLASS_UNIVERSAL, Tag.ENUMERATED, this.alertReason.getCode());

            if (this.alertReasonIndicator)
                asnOutputStream.writeNull();
            if (this.extensionContainer != null)
                ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOutputStream);

            if (this.additionalAlertReasonIndicator)
                asnOutputStream.writeNull(Tag.CLASS_CONTEXT_SPECIFIC, _TAG_additionalAlertReasonIndicator);

        } catch (IOException e) {
            throw new MAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.imsi != null) {
            sb.append("imsi=");
            sb.append(imsi);
            sb.append(", ");
        }
        if (this.alertReason != null) {
            sb.append("alertReason");
            sb.append(alertReason);
            sb.append(", ");
        }
        if (this.alertReasonIndicator) {
            sb.append("alertReasonIndicator, ");
        }
        if (this.extensionContainer != null) {
            sb.append("extensionContainer=");
            sb.append(extensionContainer);
            sb.append(", ");
        }
        if (this.additionalAlertReasonIndicator) {
            sb.append("additionalAlertReasonIndicator, ");
        }

        sb.append("]");

        return sb.toString();
    }

}
