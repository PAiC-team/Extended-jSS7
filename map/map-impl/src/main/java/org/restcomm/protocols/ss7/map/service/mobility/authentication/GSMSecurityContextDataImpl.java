
package org.restcomm.protocols.ss7.map.service.mobility.authentication;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.Cksn;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.GSMSecurityContextData;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.Kc;
import org.restcomm.protocols.ss7.map.primitives.SequenceBase;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class GSMSecurityContextDataImpl extends SequenceBase implements GSMSecurityContextData {

    private Kc kc;
    private Cksn cksn;

    public GSMSecurityContextDataImpl() {
        super("GSMSecurityContextData");
    }

    public GSMSecurityContextDataImpl(Kc kc, Cksn cksn) {
        super("GSMSecurityContextData");
        this.kc = kc;
        this.cksn = cksn;
    }

    @Override
    public Kc getKc() {
        return this.kc;
    }

    @Override
    public Cksn getCksn() {
        return this.cksn;
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
                                + ".kc: Parameter 0 bad tag or tag class or not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);

                    }
                    this.kc = new KcImpl();
                    ((KcImpl) this.kc).decodeAll(ais);
                    break;
                case 1:
                    if (tag != Tag.STRING_OCTET || ais.getTagClass() != Tag.CLASS_UNIVERSAL || !ais.isTagPrimitive()) {
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".cksn: Parameter 1 bad tag or tag class or not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);

                    }
                    this.cksn = new CksnImpl();
                    ((CksnImpl) this.cksn).decodeAll(ais);
                    break;
                default:
                    ais.advanceElement();
                    break;
            }

            num++;
        }
        if (num < 2)
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": Needs at least 2 mandatory parameters, found " + num,
                    MAPParsingComponentExceptionReason.MistypedParameter);
    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        if (this.kc == null || this.cksn == null) {
            throw new MAPException("Error while encoding " + _PrimitiveName
                    + " the mandatory parameter kc or cksn is not defined");
        }
        if (this.kc != null)
            ((KcImpl) this.kc).encodeAll(asnOutputStream);

        if (this.cksn != null)
            ((CksnImpl) this.cksn).encodeAll(asnOutputStream);

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName + " [");

        if (this.kc != null) {
            sb.append("kc=");
            sb.append(this.kc.toString());
            sb.append(", ");
        }

        if (this.cksn != null) {
            sb.append("cksn=");
            sb.append(this.cksn.toString());
            sb.append(", ");
        }

        sb.append("]");

        return sb.toString();

    }

}
