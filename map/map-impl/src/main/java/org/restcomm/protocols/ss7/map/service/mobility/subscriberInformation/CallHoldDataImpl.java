
package org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.CallHoldData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtSSStatus;
import org.restcomm.protocols.ss7.map.primitives.SequenceBase;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.ExtSSStatusImpl;

import java.io.IOException;

/**
 * Created by vsubbotin on 26/05/16.
 */
public class CallHoldDataImpl extends SequenceBase implements CallHoldData {

    private static final int _TAG_EXT_SS_STATUS = 1;
    private static final int _TAG_NOTIFICATION_TO_CSE = 2;

    private ExtSSStatus ssStatus;
    private boolean notificationToCSE;

    public CallHoldDataImpl() {
        super("CallHoldData");
    }

    public CallHoldDataImpl(ExtSSStatus ssStatus, boolean notificationToCSE) {
        super("CallHoldData");
        this.ssStatus = ssStatus;
        this.notificationToCSE = notificationToCSE;
    }

    public ExtSSStatus getSsStatus() {
        return this.ssStatus;
    }

    public boolean getNotificationToCSE() {
        return this.notificationToCSE;
    }

    @Override
    protected void _decode(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {
        this.ssStatus = null;
        this.notificationToCSE = false;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0) {
                break;
            }

            int tag = ais.readTag();
            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                    case _TAG_EXT_SS_STATUS:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter ssStatus is not primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        this.ssStatus = new ExtSSStatusImpl();
                        ((ExtSSStatusImpl)this.ssStatus).decodeAll(ais);
                        break;
                    case _TAG_NOTIFICATION_TO_CSE:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter notificationToCSE is not primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        ais.readNull();
                        this.notificationToCSE = Boolean.TRUE;
                        break;
                    default:
                        ais.advanceElement();
                        break;
                }
            } else {
                ais.advanceElement();
            }
        }

        if (this.ssStatus == null) {
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + "ssStatus is mandatory but it is absent",
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        if (this.ssStatus == null) {
            throw new MAPException("Error while encoding " + _PrimitiveName
                    + " the mandatory parameter ssStatus is not defined");
        }

        ((ExtSSStatusImpl)this.ssStatus).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_EXT_SS_STATUS);

        if (this.notificationToCSE) {
            try {
                asnOutputStream.writeNull(Tag.CLASS_CONTEXT_SPECIFIC, _TAG_NOTIFICATION_TO_CSE);
            } catch (IOException e) {
                throw new MAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
            } catch (AsnException e) {
                throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.ssStatus != null) {
            sb.append("ssStatus=");
            sb.append(this.ssStatus);
        }
        if (this.notificationToCSE)
            sb.append(", notificationToCSE");

        sb.append("]");
        return sb.toString();
    }
}
