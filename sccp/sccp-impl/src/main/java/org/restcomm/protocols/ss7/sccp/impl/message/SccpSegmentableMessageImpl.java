package org.restcomm.protocols.ss7.sccp.impl.message;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.restcomm.protocols.ss7.sccp.impl.SccpStackImpl;
import org.restcomm.protocols.ss7.sccp.impl.parameter.SegmentationImpl;
import org.restcomm.protocols.ss7.sccp.parameter.HopCounter;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.sccp.parameter.Segmentation;

/**
 *
 * This interface represents a SCCP message for connectionless data transfer (UDT, XUDT and LUDT)
 *
 * @author sergey vetyutnev
 *
 */
public abstract class SccpSegmentableMessageImpl extends SccpAddressedMessageImpl {

    protected byte[] data;
    protected SegmentationImpl segmentation;

    protected boolean isFullyReceived;
    protected int remainingSegments;
    protected ByteArrayOutputStream buffer;

    protected SccpStackImpl.MessageReassemblyProcess mrp;

    protected SccpSegmentableMessageImpl(int maxDataLen, int type, int outgoingSls, int localSsn,
            SccpAddress calledParty, SccpAddress callingParty, byte[] data, HopCounter hopCounter) {
        super(maxDataLen,type, outgoingSls, localSsn, calledParty, callingParty, hopCounter);

        this.data = data;
        this.isFullyReceived = true;
    }

    protected SccpSegmentableMessageImpl(int maxDataLen, int type, int incomingOpc, int incomingDpc,
            int incomingSls, int networkId) {
        super(maxDataLen,type, incomingOpc, incomingDpc, incomingSls, networkId);
    }

    public Segmentation getSegmentation() {
        return segmentation;
    }

    public boolean getIsFullyReceived() {
        return this.isFullyReceived;
    }

    public int getRemainingSegments() {
        return remainingSegments;
    }

    public byte[] getData() {
        return this.data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public void setReceivedSingleSegment() {
        this.isFullyReceived = true;
    }

    public void setReceivedFirstSegment() {
        if (this.segmentation == null)
            // this can not occur
            return;

        this.remainingSegments = this.segmentation.getRemainingSegments();
        this.buffer = new ByteArrayOutputStream(this.data.length * (this.remainingSegments + 1));
        try {
            this.buffer.write(this.data);
        } catch (IOException e) {
            // this can not occur
            e.printStackTrace();
        }
    }

    public void setReceivedNextSegment(SccpSegmentableMessageImpl nextSegement) {
        try {
            this.buffer.write(nextSegement.data);
        } catch (IOException e) {
            // this can not occur
            e.printStackTrace();
        }

        if (--this.remainingSegments == 0) {
            this.data = this.buffer.toByteArray();
            this.isFullyReceived = true;
        }
    }

    public void cancelSegmentation() {
        this.remainingSegments = -1;
        this.isFullyReceived = false;
    }

    public SccpStackImpl.MessageReassemblyProcess getMessageReassemblyProcess() {
        return mrp;
    }

    public void setMessageReassemblyProcess(SccpStackImpl.MessageReassemblyProcess mrp) {
        this.mrp = mrp;
    }
}
