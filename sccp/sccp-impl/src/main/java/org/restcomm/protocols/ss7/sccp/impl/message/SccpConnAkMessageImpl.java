
package org.restcomm.protocols.ss7.sccp.impl.message;

import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.sccp.LongMessageRuleType;
import org.restcomm.protocols.ss7.sccp.SccpProtocolVersion;
import org.restcomm.protocols.ss7.sccp.impl.SccpStackImpl;
import org.restcomm.protocols.ss7.sccp.impl.parameter.CreditImpl;
import org.restcomm.protocols.ss7.sccp.impl.parameter.LocalReferenceImpl;
import org.restcomm.protocols.ss7.sccp.impl.parameter.ReceiveSequenceNumberImpl;
import org.restcomm.protocols.ss7.sccp.message.ParseException;
import org.restcomm.protocols.ss7.sccp.message.SccpConnAkMessage;
import org.restcomm.protocols.ss7.sccp.parameter.Credit;
import org.restcomm.protocols.ss7.sccp.parameter.ParameterFactory;
import org.restcomm.protocols.ss7.sccp.parameter.ReceiveSequenceNumber;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class SccpConnAkMessageImpl extends SccpConnReferencedMessageImpl implements SccpConnAkMessage {

    protected ReceiveSequenceNumber receiveSequenceNumber;
    protected Credit credit;

    public SccpConnAkMessageImpl(int sls, int localSsn) {
        super(0, MESSAGE_TYPE_AK, sls, localSsn);
    }

    protected SccpConnAkMessageImpl(int incomingOpc, int incomingDpc, int incomingSls, int networkId) {
        super(0, MESSAGE_TYPE_AK, incomingOpc, incomingDpc, incomingSls, networkId);
    }

    @Override
    public ReceiveSequenceNumber getReceiveSequenceNumber() {
        return this.receiveSequenceNumber;
    }

    @Override
    public void setReceiveSequenceNumber(ReceiveSequenceNumber receiveSequenceNumber) {
        this.receiveSequenceNumber = receiveSequenceNumber;
    }

    @Override
    public Credit getCredit() {
        return this.credit;
    }

    @Override
    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    @Override
    public void decode(InputStream in, ParameterFactory factory, SccpProtocolVersion sccpProtocolVersion) throws ParseException {
        try {
            byte[] buffer = new byte[3];
            in.read(buffer);
            LocalReferenceImpl ref = new LocalReferenceImpl();
            ref.decode(buffer, factory, sccpProtocolVersion);
            destinationLocalReferenceNumber = ref;

            buffer = new byte[1];
            in.read(buffer);
            ReceiveSequenceNumberImpl sequenceNumber = new ReceiveSequenceNumberImpl();
            sequenceNumber.decode(buffer, factory, sccpProtocolVersion);
            receiveSequenceNumber = sequenceNumber;

            in.read(buffer);
            CreditImpl cred = new CreditImpl();
            cred.decode(buffer, factory, sccpProtocolVersion);
            credit = cred;
        } catch (IOException e) {
            throw new ParseException(e);
        }
    }

    @Override
    public EncodingResultData encode(SccpStackImpl sccpStackImpl, LongMessageRuleType longMessageRuleType, int maxMtp3UserDataLength, Logger logger, boolean removeSPC, SccpProtocolVersion sccpProtocolVersion) throws ParseException {
        try {
            if (type == 0) {
                return new EncodingResultData(EncodingResult.MessageTypeMissing, null, null, null);
            }
            if (destinationLocalReferenceNumber == null) {
                return new EncodingResultData(EncodingResult.DestinationLocalReferenceNumberMissing, null, null, null);
            }
            if (receiveSequenceNumber == null) {
                return new EncodingResultData(EncodingResult.ReceiveSequenceNumberMissing, null, null, null);
            }
            if (credit == null) {
                return new EncodingResultData(EncodingResult.CreditMissing, null, null, null);
            }

            // 6 is sum of 4 fixed-length field lengths
            ByteArrayOutputStream out = new ByteArrayOutputStream(6);

            byte[] dlr = ((LocalReferenceImpl) destinationLocalReferenceNumber).encode(sccpStackImpl.isRemoveSpc(), sccpStackImpl.getSccpProtocolVersion());
            byte[] sqn = ((ReceiveSequenceNumberImpl) receiveSequenceNumber).encode(sccpStackImpl.isRemoveSpc(), sccpStackImpl.getSccpProtocolVersion());
            byte[] crd = ((CreditImpl) credit).encode(sccpStackImpl.isRemoveSpc(), sccpStackImpl.getSccpProtocolVersion());

            out.write(type);
            out.write(dlr);
            out.write(sqn);
            out.write(crd);
            return new EncodingResultData(EncodingResult.Success, out.toByteArray(), null, null);
        } catch (IOException e) {
            throw new ParseException(e);
        }
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Sccp Msg [Type=AK");
        sb.append(" networkId=").append(this.networkId).append(" sls=").append(this.sls).append(" incomingOpc=").append(this.incomingOpc)
                .append(" incomingDpc=").append(this.incomingDpc).append(" outgoingDpc=").append(this.outgoingDpc)

                .append(" destinationLocalReferenceNumber=").append(this.destinationLocalReferenceNumber)
                .append(" pr=").append(this.receiveSequenceNumber)
                .append(" credit=").append(this.credit);

        sb.append("]");
        return sb.toString();
    }
}
