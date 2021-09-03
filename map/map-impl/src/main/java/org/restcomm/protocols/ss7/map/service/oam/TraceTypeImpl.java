
package org.restcomm.protocols.ss7.map.service.oam;

import org.restcomm.protocols.ss7.map.api.service.oam.BssRecordType;
import org.restcomm.protocols.ss7.map.api.service.oam.HlrRecordType;
import org.restcomm.protocols.ss7.map.api.service.oam.MscRecordType;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceType;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceTypeInvokingEvent;
import org.restcomm.protocols.ss7.map.primitives.OctetStringLength1Base;

/**
*
* @author sergey vetyutnev
*
*/
public class TraceTypeImpl extends OctetStringLength1Base implements TraceType {

    public TraceTypeImpl() {
        super("TraceType");
    }

    public TraceTypeImpl(int data) {
        super("TraceType", data);
    }

    public TraceTypeImpl(BssRecordType bssRecordType, MscRecordType mscRecordType, TraceTypeInvokingEvent traceTypeInvokingEvent, boolean priorityIndication) {
        super("TraceType");

        int mscRecordTypeInt = 3;
        if (mscRecordType != null)
            mscRecordTypeInt = mscRecordType.getCode();
        int bssRecordTypeInt = 3;
        if (bssRecordType != null)
            bssRecordTypeInt = bssRecordType.getCode();
        int traceTypeInvokingEventInt = 0;
        if (traceTypeInvokingEvent != null)
            traceTypeInvokingEventInt = traceTypeInvokingEvent.getCode();
        this.data = ((bssRecordTypeInt << 4) | (mscRecordTypeInt << 2) | traceTypeInvokingEventInt | (priorityIndication ? 0x80 : 0x00));
    }

    public TraceTypeImpl(HlrRecordType hlrRecordType, TraceTypeInvokingEvent traceTypeInvokingEvent, boolean priorityIndication) {
        super("TraceType");

        int hlrRecordTypeInt = 3;
        if (hlrRecordType != null)
            hlrRecordTypeInt = hlrRecordType.getCode();
        int traceTypeInvokingEventInt = 0;
        if (traceTypeInvokingEvent != null)
            traceTypeInvokingEventInt = traceTypeInvokingEvent.getCode();
        this.data = ((hlrRecordTypeInt << 2) | traceTypeInvokingEventInt | (priorityIndication ? 0x80 : 0x00));
    }


    public int getData() {
        return data;
    }

    @Override
    public boolean isPriorityIndication() {
        if ((this.data & 0x80) != 0)
            return true;
        else
            return false;
    }

    @Override
    public BssRecordType getBssRecordType() {
        int code = (this.data >> 4) & 0x03;
        return BssRecordType.getInstance(code);
    }

    @Override
    public MscRecordType getMscRecordType() {
        int code = (this.data >> 2) & 0x03;
        return MscRecordType.getInstance(code);
    }

    @Override
    public HlrRecordType getHlrRecordType() {
        int code = (this.data >> 2) & 0x03;
        return HlrRecordType.getInstance(code);
    }

    @Override
    public TraceTypeInvokingEvent getTraceTypeInvokingEvent() {
        int code = this.data& 0x03;
        return TraceTypeInvokingEvent.getInstance(code);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        sb.append("bssRecordType=");
        sb.append(this.getBssRecordType());
        sb.append(", ");

        sb.append("mscRecordType=");
        sb.append(this.getMscRecordType());
        sb.append(", ");

        sb.append("hlrRecordType=");
        sb.append(this.getHlrRecordType());
        sb.append(", ");

        sb.append("traceTypeInvokingEvent=");
        sb.append(this.getTraceTypeInvokingEvent());
        sb.append(", ");

        if (isPriorityIndication()) {
            sb.append("priorityIndication, ");
        }

        sb.append("]");

        return sb.toString();
    }

}
