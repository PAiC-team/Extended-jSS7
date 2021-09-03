
package org.restcomm.protocols.ss7.cap.service.gprs.primitive;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.AOCSubsequent;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.CAI_GSM0224;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.AOCGPRS;
import org.restcomm.protocols.ss7.cap.primitives.SequenceBase;
import org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive.AOCSubsequentImpl;
import org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive.CAI_GSM0224Impl;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class AOCGPRSImpl extends SequenceBase implements AOCGPRS {

    public static final int _ID_aocInitial = 0;
    public static final int _ID_aocSubsequent = 1;

    private CAI_GSM0224 aocInitial;
    private AOCSubsequent aocSubsequent;

    public AOCGPRSImpl() {
        super("AOCGPRS");
    }

    public AOCGPRSImpl(CAI_GSM0224 aocInitial, AOCSubsequent aocSubsequent) {
        super("AOCGPRS");
        this.aocInitial = aocInitial;
        this.aocSubsequent = aocSubsequent;
    }

    @Override
    public CAI_GSM0224 getAOCInitial() {
        return this.aocInitial;
    }

    @Override
    public AOCSubsequent getAOCSubsequent() {
        return this.aocSubsequent;
    }

    @Override
    protected void _decode(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException, IOException, AsnException,
            MAPParsingComponentException {

        this.aocInitial = null;
        this.aocSubsequent = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                    case _ID_aocInitial:
                        if (ais.isTagPrimitive())
                            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".aocInitial: Parameter is primitive",
                                    CAPParsingComponentExceptionReason.MistypedParameter);
                        this.aocInitial = new CAI_GSM0224Impl();
                        ((CAI_GSM0224Impl) this.aocInitial).decodeAll(ais);
                        break;
                    case _ID_aocSubsequent:
                        if (ais.isTagPrimitive())
                            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".aocSubsequent: Parameter is primitive",
                                    CAPParsingComponentExceptionReason.MistypedParameter);
                        this.aocSubsequent = new AOCSubsequentImpl();
                        ((AOCSubsequentImpl) this.aocSubsequent).decodeAll(ais);
                        break;
                    default:
                        ais.advanceElement();
                        break;
                }
            } else {
                ais.advanceElement();
            }
        }

        if (this.aocInitial == null)
            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": aocInitial is mandatory but not found", CAPParsingComponentExceptionReason.MistypedParameter);
    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws CAPException {

        if (this.aocInitial == null)
            throw new CAPException("Error while encoding " + _PrimitiveName + ": aocInitial must not be null");

        ((CAI_GSM0224Impl) this.aocInitial).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_aocInitial);

        if (this.aocSubsequent != null)
            ((AOCSubsequentImpl) this.aocSubsequent).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_aocSubsequent);

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName + " [");

        if (this.aocInitial != null) {
            sb.append("aocInitial=");
            sb.append(this.aocInitial.toString());
            sb.append(", ");
        }

        if (this.aocSubsequent != null) {
            sb.append("aocSubsequent=");
            sb.append(this.aocSubsequent.toString());
            sb.append(", ");
        }

        sb.append("]");

        return sb.toString();
    }

}
