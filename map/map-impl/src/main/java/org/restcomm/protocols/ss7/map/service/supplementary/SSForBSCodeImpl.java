
package org.restcomm.protocols.ss7.map.service.supplementary;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.BasicServiceCode;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSCode;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSForBSCode;
import org.restcomm.protocols.ss7.map.primitives.SequenceBase;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.BasicServiceCodeImpl;

/**
*
* @author sergey vetyutnev
*
*/
public class SSForBSCodeImpl extends SequenceBase implements SSForBSCode {

    public static final int _ID_longFTNSupported = 4;

    private SSCode ssCode;
    private BasicServiceCode basicService;
    private boolean longFtnSupported;

    public SSForBSCodeImpl() {
        super("SSForBSCode");
    }

    public SSForBSCodeImpl(SSCode ssCode, BasicServiceCode basicService, boolean longFtnSupported) {
        super("SSForBSCode");

        this.ssCode = ssCode;
        this.basicService = basicService;
        this.longFtnSupported = longFtnSupported;
    }

    @Override
    public SSCode getSsCode() {
        return ssCode;
    }

    @Override
    public BasicServiceCode getBasicService() {
        return basicService;
    }

    @Override
    public boolean getLongFtnSupported() {
        return longFtnSupported;
    }

    @Override
    protected void _decode(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {
        this.ssCode = null;
        this.basicService = null;
        this.longFtnSupported = false;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        int num = 0;
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            switch (num) {
            case 0:
                // ssCode
                if (ais.getTagClass() != Tag.CLASS_UNIVERSAL || !ais.isTagPrimitive() || tag != Tag.STRING_OCTET)
                    throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                            + ".ssCode: Parameter 0 bad tag or tag class or not primitive", MAPParsingComponentExceptionReason.MistypedParameter);
                this.ssCode = new SSCodeImpl();
                ((SSCodeImpl) this.ssCode).decodeAll(ais);
                break;

            default:
                if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                    switch (tag) {

                    case BasicServiceCodeImpl._TAG_bearerService:
                    case BasicServiceCodeImpl._TAG_teleservice:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".basicService: Parameter is not primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);
                        this.basicService = new BasicServiceCodeImpl();
                        ((BasicServiceCodeImpl) this.basicService).decodeAll(ais);
                        break;
                    case _ID_longFTNSupported:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".longFtnSupported: Parameter is not primitive", MAPParsingComponentExceptionReason.MistypedParameter);
                        ais.readNull();
                        this.longFtnSupported = true;
                        break;

                    default:
                        ais.advanceElement();
                        break;
                    }
                } else {
                    ais.advanceElement();
                }
                break;
            }

            num++;
        }

        if (num < 1)
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ": Needs at least 1 mandatory parameter, found " + num,
                    MAPParsingComponentExceptionReason.MistypedParameter);
    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        try {

            if (this.ssCode == null)
                throw new MAPException("ssCode parameter must not be null");

            ((SSCodeImpl) this.ssCode).encodeAll(asnOutputStream);

            if (this.basicService != null)
                ((BasicServiceCodeImpl) this.basicService).encodeAll(asnOutputStream);

            if (this.longFtnSupported)
                asnOutputStream.writeNull(Tag.CLASS_CONTEXT_SPECIFIC, _ID_longFTNSupported);

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

        if (this.ssCode != null) {
            sb.append("ssCode=");
            sb.append(ssCode);
            sb.append(", ");
        }
        if (this.basicService != null) {
            sb.append("basicService=");
            sb.append(basicService);
            sb.append(", ");
        }
        if (this.longFtnSupported) {
            sb.append("longFtnSupported, ");
        }

        sb.append("]");

        return sb.toString();
    }

}
