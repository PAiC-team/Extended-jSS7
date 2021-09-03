
package org.restcomm.protocols.ss7.sccp.impl.message;

import java.io.InputStream;

import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.sccp.LongMessageRuleType;
import org.restcomm.protocols.ss7.sccp.SccpProtocolVersion;
import org.restcomm.protocols.ss7.sccp.impl.SccpStackImpl;
import org.restcomm.protocols.ss7.sccp.message.ParseException;
import org.restcomm.protocols.ss7.sccp.message.SccpMessage;
import org.restcomm.protocols.ss7.sccp.parameter.ParameterFactory;

/**
 *
 * @author kulikov
 * @author baranowb
 * @author sergey vetyutnev
 */
public abstract class SccpMessageImpl implements SccpMessage {

    protected boolean isMtpOriginated;
    protected boolean isIncoming;
    protected int type;
    protected int localOriginSsn = -1;
    protected final int maxDataLen;

    // These are MTP3 signaling information set when message is received from MTP3
    protected int incomingOpc;
    protected int incomingDpc;
    protected int sls;
    // These are MTP3 signaling information that will be set into a MTP3 message when sending to MTP3
    protected int outgoingDpc = -1;
    protected int networkId;

    protected SccpMessageImpl(int maxDataLen, int type, int sls, int localSsn) {
        this.isMtpOriginated = false;
        this.type = type;
        this.localOriginSsn = localSsn;
        this.incomingOpc = -1;
        this.incomingDpc = -1;
        this.sls = sls;
        this.maxDataLen = maxDataLen;
    }

    protected SccpMessageImpl(int maxDataLen, int type, int incomingOpc, int incomingDpc, int incomingSls, int networkId) {
        this.isMtpOriginated = true;
        this.isIncoming = true;
        this.type = type;
        this.incomingOpc = incomingOpc;
        this.incomingDpc = incomingDpc;
        this.sls = incomingSls;
        this.maxDataLen = maxDataLen;
        this.networkId = networkId;
    }

    public int getSls() {
        return this.sls;
    }

    public void setSls(int sls) {
        this.sls = sls;
    }

    public int getIncomingOpc() {
        return this.incomingOpc;
    }

    public void setIncomingOpc(int opc) {
        this.incomingOpc = opc;
    }

    public int getIncomingDpc() {
        return this.incomingDpc;
    }

    public int getOutgoingDpc() {
        return this.outgoingDpc;
    }

    public void setIncomingDpc(int dpc) {
        this.incomingDpc = dpc;
    }

    public void setOutgoingDpc(int dpc) {
        this.outgoingDpc = dpc;
    }

    public int getType() {
        return this.type;
    }

    public boolean getIsMtpOriginated() {
        return this.isMtpOriginated;
    }

    public boolean getIsIncoming() {
        return this.isIncoming;
    }

    public void setIsIncoming(boolean incoming) {
        isIncoming = incoming;
    }

    public int getOriginLocalSsn() {
        return this.localOriginSsn;
    }

    public int getNetworkId() {
        return this.networkId;
    }

    public void setNetworkId(int networkId) {
        this.networkId = networkId;
    }

    public abstract void decode(InputStream in, ParameterFactory factory, SccpProtocolVersion sccpProtocolVersion) throws ParseException;

    public abstract EncodingResultData encode(SccpStackImpl sccpStackImpl, LongMessageRuleType longMessageRuleType, int maxMtp3UserDataLength, Logger logger,
            boolean removeSPC, SccpProtocolVersion sccpProtocolVersion) throws ParseException;

}
