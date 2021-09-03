package org.restcomm.protocols.ss7.sccp.impl.message;

import org.restcomm.protocols.ss7.sccp.SccpProtocolVersion;
import org.restcomm.protocols.ss7.sccp.impl.parameter.AbstractParameter;
import org.restcomm.protocols.ss7.sccp.impl.parameter.ReturnCauseImpl;
import org.restcomm.protocols.ss7.sccp.message.ParseException;
import org.restcomm.protocols.ss7.sccp.message.SccpMessage;
import org.restcomm.protocols.ss7.sccp.message.SccpNoticeMessage;
import org.restcomm.protocols.ss7.sccp.parameter.HopCounter;
import org.restcomm.protocols.ss7.sccp.parameter.Importance;
import org.restcomm.protocols.ss7.sccp.parameter.ReturnCause;
import org.restcomm.protocols.ss7.sccp.parameter.ReturnCauseValue;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;

/**
 *
 * This class represents SCCP a connectionless notice message (UDTS, XUDTS and LUDTS)
 *
 * @author Oleg Kulikov
 * @author baranowb
 * @author sergey vetyutnev
 *
 */
public class SccpNoticeMessageImpl extends SccpDataNoticeTemplateMessageImpl implements SccpNoticeMessage {

    protected ReturnCause returnCause;

    /**
     * Create a SCCP-User originated message
     *
     * @param maxDataLen
     * @param type
     * @param returnCause
     * @param calledParty
     * @param callingParty
     * @param data
     * @param hopCounter
     * @param importance
     */
    protected SccpNoticeMessageImpl(int maxDataLen, int type, ReturnCause returnCause, SccpAddress calledParty,
            SccpAddress callingParty, byte[] data, HopCounter hopCounter, Importance importance) {
        super(maxDataLen, type, 0, -1, calledParty, callingParty, data, hopCounter, importance);

        this.returnCause = returnCause;
    }

    /**
     * Create a MTP3 originated message
     *
     * @param maxDataLen
     * @param type
     * @param incomingOpc
     * @param incomingDpc
     * @param incomingSls
     */
    protected SccpNoticeMessageImpl(int maxDataLen, int type, int incomingOpc, int incomingDpc, int incomingSls, int networkId) {
        super(maxDataLen, type, incomingOpc, incomingDpc, incomingSls, networkId);
    }

    public ReturnCause getReturnCause() {
        return this.returnCause;
    }

    public void setReturnCause(ReturnCause rc) {
        this.returnCause = rc;
    }

    public boolean getReturnMessageOnError() {
        return false;
    }

    public void clearReturnMessageOnError() {
    }

    public boolean getSccpCreatesSls() {
        return true;
    }

    @Override
    protected boolean getSecondParameterPresent() {
        return this.returnCause != null;
    }

    @Override
    protected byte[] getSecondParameterData(boolean removeSPC, SccpProtocolVersion sccpProtocolVersion) throws ParseException {
        return ((AbstractParameter) this.returnCause).encode(removeSPC, sccpProtocolVersion);
    }

    @Override
    protected void setSecondParameterData(int data, final SccpProtocolVersion sccpProtocolVersion) throws ParseException {
        this.returnCause = new ReturnCauseImpl(ReturnCauseValue.getInstance(data));
    }

    @Override
    protected boolean getIsProtocolClass1() {
        return false;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Sccp Msg [Type=");
        switch (this.type) {
            case SccpMessage.MESSAGE_TYPE_UDTS:
                sb.append("UDTS");
                break;
            case SccpMessage.MESSAGE_TYPE_XUDTS:
                sb.append("XUDTS");
                break;
            case SccpMessage.MESSAGE_TYPE_LUDTS:
                sb.append("LUDTS");
                break;
            default:
                sb.append(this.type);
                break;
        }
        sb.append(" networkId=").append(this.networkId).append(" sls=").append(this.sls).append(" returnCause=").append(this.returnCause)
                .append(" incomingOpc=").append(this.incomingOpc).append(" incomingDpc=").append(this.incomingDpc).append(" outgoingDpc=")
                .append(this.outgoingDpc).append(" CallingAddress(").append(this.callingParty).append(") CalledParty(").append(this.calledParty).append(")");
        sb.append(" DataLen=");
        if (this.data != null)
            sb.append(this.data.length);
        return sb.toString();
    }
}
