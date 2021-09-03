
package org.restcomm.protocols.ss7.map.service.mobility.authentication;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.CK;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.IK;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.KSI;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.UMTSSecurityContextData;
import org.restcomm.protocols.ss7.map.primitives.SequenceBase;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class UMTSSecurityContextDataImpl extends SequenceBase implements UMTSSecurityContextData {

    private CK ck;
    private IK ik;
    private KSI ksi;

    public UMTSSecurityContextDataImpl() {
        super("UMTSSecurityContextData");
    }

    public UMTSSecurityContextDataImpl(CK ck, IK ik, KSI ksi) {
        super("UMTSSecurityContextData");
        this.ck = ck;
        this.ik = ik;
        this.ksi = ksi;
    }

    @Override
    public CK getCK() {
        return this.ck;
    }

    @Override
    public IK getIK() {
        return this.ik;
    }

    @Override
    public KSI getKSI() {
        return this.ksi;
    }

    @Override
    protected void _decode(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {
        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        int num = 0;
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();
            switch (num) {
                case 0:
                    if (tag != Tag.STRING_OCTET || ais.getTagClass() != Tag.CLASS_UNIVERSAL || !ais.isTagPrimitive()) {
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".ck: Parameter 0 bad tag or tag class or not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);

                    }
                    this.ck = new CKImpl();
                    ((CKImpl) this.ck).decodeAll(ais);
                    break;
                case 1:
                    if (tag != Tag.STRING_OCTET || ais.getTagClass() != Tag.CLASS_UNIVERSAL || !ais.isTagPrimitive()) {
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".ik: Parameter 1 bad tag or tag class or not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);

                    }
                    this.ik = new IKImpl();
                    ((IKImpl) this.ik).decodeAll(ais);
                    break;
                case 2:
                    if (tag != Tag.STRING_OCTET || ais.getTagClass() != Tag.CLASS_UNIVERSAL || !ais.isTagPrimitive()) {
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".ck: Parameter 2 bad tag or tag class or not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);

                    }
                    this.ksi = new KSIImpl();
                    ((KSIImpl) this.ksi).decodeAll(ais);
                    break;
                default:
                    ais.advanceElement();
                    break;
            }
            num++;
        }
        if (num < 3)
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": Needs at least 3 mandatory parameters, found " + num,
                    MAPParsingComponentExceptionReason.MistypedParameter);

    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        if (this.ck == null || this.ik == null || this.ksi == null) {
            throw new MAPException("Error while encoding " + _PrimitiveName
                    + " the mandatory parameter ck,ik or ksi is not defined");
        }
        if (this.ck != null)
            ((CKImpl) this.ck).encodeAll(asnOutputStream);

        if (this.ik != null)
            ((IKImpl) this.ik).encodeAll(asnOutputStream);

        if (this.ksi != null)
            ((KSIImpl) this.ksi).encodeAll(asnOutputStream);

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName + " [");

        if (this.ck != null) {
            sb.append("ck=");
            sb.append(this.ck.toString());
            sb.append(", ");
        }

        if (this.ik != null) {
            sb.append("ik=");
            sb.append(this.ik.toString());
            sb.append(", ");
        }

        if (this.ksi != null) {
            sb.append("ksi=");
            sb.append(this.ksi.toString());
            sb.append(", ");
        }

        sb.append("]");

        return sb.toString();

    }

}
