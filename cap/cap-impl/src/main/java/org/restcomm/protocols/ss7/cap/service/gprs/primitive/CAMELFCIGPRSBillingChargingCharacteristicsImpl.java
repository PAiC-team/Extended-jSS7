
package org.restcomm.protocols.ss7.cap.service.gprs.primitive;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.CAMELFCIGPRSBillingChargingCharacteristics;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.FCIBCCCAMELsequence1Gprs;
import org.restcomm.protocols.ss7.cap.primitives.SequenceBase;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class CAMELFCIGPRSBillingChargingCharacteristicsImpl extends SequenceBase implements CAMELFCIGPRSBillingChargingCharacteristics {

    public static final int _ID_fcIBCCCAMELsequence1 = 0;

    private FCIBCCCAMELsequence1Gprs fcIBCCCAMELsequence1;

    public CAMELFCIGPRSBillingChargingCharacteristicsImpl() {
        super("CAMELFCIGPRSBillingChargingCharacteristics");
    }

    public CAMELFCIGPRSBillingChargingCharacteristicsImpl(FCIBCCCAMELsequence1Gprs fcIBCCCAMELsequence1) {
        super("CAMELFCIGPRSBillingChargingCharacteristics");
        this.fcIBCCCAMELsequence1 = fcIBCCCAMELsequence1;
    }

    @Override
    public FCIBCCCAMELsequence1Gprs getFCIBCCCAMELsequence1() {
        return this.fcIBCCCAMELsequence1;
    }

    @Override
    protected void _decode(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException, IOException, AsnException,
            MAPParsingComponentException {

        this.fcIBCCCAMELsequence1 = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                    case _ID_fcIBCCCAMELsequence1:
                        if (ais.isTagPrimitive())
                            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".fcIBCCCAMELsequence1: Parameter is primitive",
                                    CAPParsingComponentExceptionReason.MistypedParameter);
                        this.fcIBCCCAMELsequence1 = new FCIBCCCAMELsequence1GprsImpl();
                        ((FCIBCCCAMELsequence1GprsImpl) this.fcIBCCCAMELsequence1).decodeAll(ais);
                        break;
                    default:
                        ais.advanceElement();
                        break;
                }
            } else {
                ais.advanceElement();
            }
        }

        if (this.fcIBCCCAMELsequence1 == null)
            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": fcIBCCCAMELsequence1 is mandatory but not found", CAPParsingComponentExceptionReason.MistypedParameter);

    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws CAPException {

        if (this.fcIBCCCAMELsequence1 == null)
            throw new CAPException("Error while encoding " + _PrimitiveName + ": fcIBCCCAMELsequence1 must not be null");

        ((FCIBCCCAMELsequence1GprsImpl) this.fcIBCCCAMELsequence1).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC,
                _ID_fcIBCCCAMELsequence1);

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName + " [");

        if (this.fcIBCCCAMELsequence1 != null) {
            sb.append("fcIBCCCAMELsequence1=");
            sb.append(this.fcIBCCCAMELsequence1.toString());
        }

        sb.append("]");

        return sb.toString();
    }

}
