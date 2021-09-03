
package org.restcomm.protocols.ss7.cap.service.gprs.primitive;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.AOCGPRS;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.CAMELSCIGPRSBillingChargingCharacteristics;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.PDPID;
import org.restcomm.protocols.ss7.cap.primitives.SequenceBase;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class CAMELSCIGPRSBillingChargingCharacteristicsImpl extends SequenceBase implements CAMELSCIGPRSBillingChargingCharacteristics {

    public static final int _ID_aocGPRS = 0;
    public static final int _ID_pdpID = 1;

    private AOCGPRS aocGPRS;
    private PDPID pdpID;

    public CAMELSCIGPRSBillingChargingCharacteristicsImpl() {
        super("CAMELSCIGPRSBillingChargingCharacteristics");
    }

    public CAMELSCIGPRSBillingChargingCharacteristicsImpl(AOCGPRS aocGPRS, PDPID pdpID) {
        super("CAMELSCIGPRSBillingChargingCharacteristics");
        this.aocGPRS = aocGPRS;
        this.pdpID = pdpID;
    }

    @Override
    public AOCGPRS getAOCGPRS() {
        return this.aocGPRS;
    }

    @Override
    public PDPID getPDPID() {
        return this.pdpID;
    }

    @Override
    protected void _decode(AsnInputStream asnIS, int length) throws CAPParsingComponentException, IOException, AsnException,
            MAPParsingComponentException {

        this.aocGPRS = null;
        this.pdpID = null;

        AsnInputStream ais = asnIS.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                    case _ID_aocGPRS:
                        if (ais.isTagPrimitive())
                            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".aocGPRS: Parameter is primitive", CAPParsingComponentExceptionReason.MistypedParameter);
                        this.aocGPRS = new AOCGPRSImpl();
                        ((AOCGPRSImpl) this.aocGPRS).decodeAll(ais);
                        break;
                    case _ID_pdpID:
                        if (!ais.isTagPrimitive())
                            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".pdpID: Parameter is not primitive",
                                    CAPParsingComponentExceptionReason.MistypedParameter);
                        this.pdpID = new PDPIDImpl();
                        ((PDPIDImpl) this.pdpID).decodeAll(ais);
                        break;
                    default:
                        ais.advanceElement();
                        break;
                }
            } else {
                ais.advanceElement();
            }
        }

        if (this.aocGPRS == null)
            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": aocGPRS is mandatory but not found", CAPParsingComponentExceptionReason.MistypedParameter);
    }

    @Override
    public void encodeData(AsnOutputStream asnOs) throws CAPException {

        if (this.aocGPRS == null)
            throw new CAPException("Error while encoding " + _PrimitiveName + ": aocGPRS must not be null");

        ((AOCGPRSImpl) this.aocGPRS).encodeAll(asnOs, Tag.CLASS_CONTEXT_SPECIFIC, _ID_aocGPRS);

        if (this.pdpID != null)
            ((PDPIDImpl) this.pdpID).encodeAll(asnOs, Tag.CLASS_CONTEXT_SPECIFIC, _ID_pdpID);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName + " [");

        if (this.aocGPRS != null) {
            sb.append("aocGPRS=");
            sb.append(this.aocGPRS.toString());
            sb.append(", ");
        }

        if (this.pdpID != null) {
            sb.append("pdpID=");
            sb.append(this.pdpID.toString());
            sb.append(" ");
        }

        sb.append("]");

        return sb.toString();
    }

}
