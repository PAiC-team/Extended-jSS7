
package org.restcomm.protocols.ss7.map.service.mobility.locationManagement;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.LMSI;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.IMSIWithLMSI;
import org.restcomm.protocols.ss7.map.primitives.IMSIImpl;
import org.restcomm.protocols.ss7.map.primitives.LMSIImpl;
import org.restcomm.protocols.ss7.map.primitives.SequenceBase;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class IMSIWithLMSIImpl extends SequenceBase implements IMSIWithLMSI {

    private IMSI imsi;
    private LMSI lmsi;

    public IMSIWithLMSIImpl() {
        super("IMSIWithLMSI");
    }

    public IMSIWithLMSIImpl(IMSI imsi, LMSI lmsi) {
        super("IMSIWithLMSI");
        this.imsi = imsi;
        this.lmsi = lmsi;
    }

    protected void _decode(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {
        this.imsi = null;
        this.lmsi = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);

        int num = 0;
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            switch (num) {
                case 0:
                    if (tag != Tag.STRING_OCTET && ais.getTagClass() != Tag.CLASS_UNIVERSAL || !ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ": Parameter imsi has bad tag class or is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.imsi = new IMSIImpl();
                    ((IMSIImpl) this.imsi).decodeAll(ais);
                    break;
                case 1:
                    if (tag != Tag.STRING_OCTET && ais.getTagClass() != Tag.CLASS_UNIVERSAL || !ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ": Parameter lmsi has bad tag class or is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.lmsi = new LMSIImpl();
                    ((LMSIImpl) this.lmsi).decodeAll(ais);
                    break;
                default:
                    ais.advanceElement();
                    break;
            }
            num++;
        }
    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        if (this.imsi == null || this.lmsi == null) {
            throw new MAPException("Error while decoding " + _PrimitiveName + " : lmsi or lmsi is null");
        }

        ((IMSIImpl) this.imsi).encodeAll(asnOutputStream);
        ((LMSIImpl) this.lmsi).encodeAll(asnOutputStream);
    }

    @Override
    public IMSI getImsi() {
        return this.imsi;
    }

    @Override
    public LMSI getLmsi() {
        return this.lmsi;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.imsi != null) {
            sb.append("imsi=");
            sb.append(imsi.toString());
            sb.append(", ");
        }

        if (this.lmsi != null) {
            sb.append("lmsi=");
            sb.append(lmsi.toString());
            sb.append(", ");
        }

        sb.append("]");

        return sb.toString();
    }
}
