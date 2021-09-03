package org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPMessageType;
import org.restcomm.protocols.ss7.cap.api.CAPOperationCode;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.cap.api.primitives.SendingSideID;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.SendChargingInformationRequest;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.SCIBillingChargingCharacteristics;
import org.restcomm.protocols.ss7.cap.primitives.CAPExtensionsImpl;
import org.restcomm.protocols.ss7.cap.primitives.SendingSideIDImpl;
import org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive.SCIBillingChargingCharacteristicsImpl;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class SendChargingInformationRequestImpl extends CircuitSwitchedCallMessageImpl implements SendChargingInformationRequest {

    public static final int _ID_sCIBillingChargingCharacteristics = 0;
    public static final int _ID_partyToCharge = 1;
    public static final int _ID_extensions = 2;

    public static final String _PrimitiveName = "SendChargingInformationRequestIndication";

    private SCIBillingChargingCharacteristics sciBillingChargingCharacteristics;
    private SendingSideID partyToCharge;
    private CAPExtensions extensions;

    public SendChargingInformationRequestImpl() {
    }

    public SendChargingInformationRequestImpl(SCIBillingChargingCharacteristics sciBillingChargingCharacteristics,
            SendingSideID partyToCharge, CAPExtensions extensions) {
        this.sciBillingChargingCharacteristics = sciBillingChargingCharacteristics;
        this.partyToCharge = partyToCharge;
        this.extensions = extensions;
    }

    @Override
    public CAPMessageType getMessageType() {
        return CAPMessageType.sendChargingInformation_Request;
    }

    @Override
    public int getOperationCode() {
        return CAPOperationCode.sendChargingInformation;
    }

    @Override
    public SCIBillingChargingCharacteristics getSCIBillingChargingCharacteristics() {
        return sciBillingChargingCharacteristics;
    }

    @Override
    public SendingSideID getPartyToCharge() {
        return partyToCharge;
    }

    @Override
    public CAPExtensions getExtensions() {
        return extensions;
    }

    @Override
    public int getTag() throws CAPException {
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
    public void decodeAll(AsnInputStream asnInputStream) throws CAPParsingComponentException {

        try {
            int length = asnInputStream.readLength();
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new CAPParsingComponentException("IOException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    CAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new CAPParsingComponentException("AsnException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    CAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    @Override
    public void decodeData(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException {

        try {
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new CAPParsingComponentException("IOException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    CAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new CAPParsingComponentException("AsnException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    CAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    private void _decode(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException, IOException, AsnException {

        this.sciBillingChargingCharacteristics = null;
        this.partyToCharge = null;
        this.extensions = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                    case _ID_sCIBillingChargingCharacteristics:
                        this.sciBillingChargingCharacteristics = new SCIBillingChargingCharacteristicsImpl();
                        ((SCIBillingChargingCharacteristicsImpl) this.sciBillingChargingCharacteristics).decodeAll(ais);
                        break;
                    case _ID_partyToCharge:
                        AsnInputStream ais2 = ais.readSequenceStream();
                        ais2.readTag();
                        this.partyToCharge = new SendingSideIDImpl();
                        ((SendingSideIDImpl) this.partyToCharge).decodeAll(ais2);
                        break;
                    case _ID_extensions:
                        this.extensions = new CAPExtensionsImpl();
                        ((CAPExtensionsImpl) this.extensions).decodeAll(ais);
                        break;

                    default:
                        ais.advanceElement();
                        break;
                }
            } else {
                ais.advanceElement();
            }
        }

        if (this.sciBillingChargingCharacteristics == null || this.partyToCharge == null)
            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": sciBillingChargingCharacteristics and partyToCharge are mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);
    }

    @Override
    public void encodeAll(AsnOutputStream asnOutputStream) throws CAPException {
        this.encodeAll(asnOutputStream, this.getTagClass(), this.getTag());
    }

    @Override
    public void encodeAll(AsnOutputStream asnOutputStream, int tagClass, int tag) throws CAPException {

        try {
            asnOutputStream.writeTag(tagClass, this.getIsPrimitive(), tag);
            int pos = asnOutputStream.StartContentDefiniteLength();
            this.encodeData(asnOutputStream);
            asnOutputStream.FinalizeContent(pos);
        } catch (AsnException e) {
            throw new CAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws CAPException {

        if (this.sciBillingChargingCharacteristics == null || this.partyToCharge == null)
            throw new CAPException("Error while encoding " + _PrimitiveName
                    + ": sciBillingChargingCharacteristics and partyToCharge must not be null");

        try {

            ((SCIBillingChargingCharacteristicsImpl) this.sciBillingChargingCharacteristics).encodeAll(asnOutputStream,
                    Tag.CLASS_CONTEXT_SPECIFIC, _ID_sCIBillingChargingCharacteristics);

            asnOutputStream.writeTag(Tag.CLASS_CONTEXT_SPECIFIC, false, _ID_partyToCharge);
            int pos = asnOutputStream.StartContentDefiniteLength();
            ((SendingSideIDImpl) this.partyToCharge).encodeAll(asnOutputStream);
            asnOutputStream.FinalizeContent(pos);

            if (this.extensions != null)
                ((CAPExtensionsImpl) this.extensions).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_extensions);

        } catch (AsnException e) {
            throw new CAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");
        this.addInvokeIdInfo(sb);

        if (this.sciBillingChargingCharacteristics != null) {
            sb.append(", sciBillingChargingCharacteristics=");
            sb.append(sciBillingChargingCharacteristics.toString());
        }
        if (this.partyToCharge != null) {
            sb.append(", partyToCharge=");
            sb.append(partyToCharge.toString());
        }
        if (this.extensions != null) {
            sb.append(", extensions=");
            sb.append(extensions.toString());
        }

        sb.append("]");

        return sb.toString();
    }
}
