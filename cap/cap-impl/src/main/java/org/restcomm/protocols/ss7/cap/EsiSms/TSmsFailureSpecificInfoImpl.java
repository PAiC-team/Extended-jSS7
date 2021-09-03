
package org.restcomm.protocols.ss7.cap.EsiSms;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.cap.api.EsiSms.TSmsFailureSpecificInfo;
import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.MTSMSCause;
import org.restcomm.protocols.ss7.cap.primitives.SequenceBase;
import org.restcomm.protocols.ss7.cap.service.sms.primitive.MTSMSCauseImpl;
import org.restcomm.protocols.ss7.inap.api.INAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class TSmsFailureSpecificInfoImpl extends SequenceBase implements TSmsFailureSpecificInfo {

    public static final int _ID_failureCause = 0;

    private MTSMSCause failureCause;

    public TSmsFailureSpecificInfoImpl() {
        super("TSmsFailureSpecificInfo");
    }

    public TSmsFailureSpecificInfoImpl(MTSMSCause failureCause) {
        super("TSmsFailureSpecificInfo");
        this.failureCause = failureCause;
    }

    @Override
    public MTSMSCause GetFailureCause() {
        return this.failureCause;
    }

    @Override
    protected void _decode(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException, IOException,
            AsnException, MAPParsingComponentException, INAPParsingComponentException {
        this.failureCause = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {

                case _ID_failureCause:
                    if (!ais.isTagPrimitive())
                        throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".failureCause: Parameter is not  primitive",
                                CAPParsingComponentExceptionReason.MistypedParameter);
                    this.failureCause = new MTSMSCauseImpl();
                    ((MTSMSCauseImpl) this.failureCause).decodeAll(ais);
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

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws CAPException {
        if (this.failureCause != null)
            ((MTSMSCauseImpl) this.failureCause).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_failureCause);

    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.failureCause != null) {
            sb.append("failureCause=");
            sb.append(failureCause.toString());
        }

        sb.append("]");

        return sb.toString();
    }

}
