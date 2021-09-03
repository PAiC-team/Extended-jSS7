
package org.restcomm.protocols.ss7.map.service.oam;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceDepth;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceDepthList;
import org.restcomm.protocols.ss7.map.primitives.SequenceBase;

/**
*
* @author sergey vetyutnev
*
*/
public class TraceDepthListImpl extends SequenceBase implements TraceDepthList {

    public static final int _ID_mscSTraceDepth = 0;
    public static final int _ID_mgwTraceDepth = 1;
    public static final int _ID_sgsnTraceDepth = 2;
    public static final int _ID_ggsnTraceDepth = 3;
    public static final int _ID_rncTraceDepth = 4;
    public static final int _ID_bmscTraceDepth = 5;
    public static final int _ID_mmeTraceDepth = 6;
    public static final int _ID_sgwTraceDepth = 7;
    public static final int _ID_pgwTraceDepth = 8;
    public static final int _ID_eNBTraceDepth = 9;

    private TraceDepth mscSTraceDepth;
    private TraceDepth mgwTraceDepth;
    private TraceDepth sgsnTraceDepth;
    private TraceDepth ggsnTraceDepth;
    private TraceDepth rncTraceDepth;
    private TraceDepth bmscTraceDepth;
    private TraceDepth mmeTraceDepth;
    private TraceDepth sgwTraceDepth;
    private TraceDepth pgwTraceDepth;
    private TraceDepth enbTraceDepth;

    public TraceDepthListImpl() {
        super("TraceDepthList");
    }

    public TraceDepthListImpl(TraceDepth mscSTraceDepth, TraceDepth mgwTraceDepth, TraceDepth sgsnTraceDepth, TraceDepth ggsnTraceDepth,
            TraceDepth rncTraceDepth, TraceDepth bmscTraceDepth, TraceDepth mmeTraceDepth, TraceDepth sgwTraceDepth, TraceDepth pgwTraceDepth,
            TraceDepth enbTraceDepth) {
        super("TraceDepthList");

        this.mscSTraceDepth = mscSTraceDepth;
        this.mgwTraceDepth = mgwTraceDepth;
        this.sgsnTraceDepth = sgsnTraceDepth;
        this.ggsnTraceDepth = ggsnTraceDepth;
        this.rncTraceDepth = rncTraceDepth;
        this.bmscTraceDepth = bmscTraceDepth;
        this.mmeTraceDepth = mmeTraceDepth;
        this.sgwTraceDepth = sgwTraceDepth;
        this.pgwTraceDepth = pgwTraceDepth;
        this.enbTraceDepth = enbTraceDepth;
    }

    @Override
    public TraceDepth getMscSTraceDepth() {
        return mscSTraceDepth;
    }

    @Override
    public TraceDepth getMgwTraceDepth() {
        return mgwTraceDepth;
    }

    @Override
    public TraceDepth getSgsnTraceDepth() {
        return sgsnTraceDepth;
    }

    @Override
    public TraceDepth getGgsnTraceDepth() {
        return ggsnTraceDepth;
    }

    @Override
    public TraceDepth getRncTraceDepth() {
        return rncTraceDepth;
    }

    @Override
    public TraceDepth getBmscTraceDepth() {
        return bmscTraceDepth;
    }

    @Override
    public TraceDepth getMmeTraceDepth() {
        return mmeTraceDepth;
    }

    @Override
    public TraceDepth getSgwTraceDepth() {
        return sgwTraceDepth;
    }

    @Override
    public TraceDepth getPgwTraceDepth() {
        return pgwTraceDepth;
    }

    @Override
    public TraceDepth getEnbTraceDepth() {
        return enbTraceDepth;
    }

    @Override
    protected void _decode(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {
        this.mscSTraceDepth = null;
        this.mgwTraceDepth = null;
        this.sgsnTraceDepth = null;
        this.ggsnTraceDepth = null;
        this.rncTraceDepth = null;
        this.bmscTraceDepth = null;
        this.mmeTraceDepth = null;
        this.sgwTraceDepth = null;
        this.pgwTraceDepth = null;
        this.enbTraceDepth = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);

        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {

                switch (tag) {
                case _ID_mscSTraceDepth:
                    if (!ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + " mscSTraceDepth: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    int i1 = (int)ais.readInteger();
                    this.mscSTraceDepth = TraceDepth.getInstance(i1);
                    break;
                case _ID_mgwTraceDepth:
                    if (!ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + " mgwTraceDepth: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    i1 = (int)ais.readInteger();
                    this.mgwTraceDepth = TraceDepth.getInstance(i1);
                    break;
                case _ID_sgsnTraceDepth:
                    if (!ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + " sgsnTraceDepth: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    i1 = (int)ais.readInteger();
                    this.sgsnTraceDepth = TraceDepth.getInstance(i1);
                    break;
                case _ID_ggsnTraceDepth:
                    if (!ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + " ggsnTraceDepth: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    i1 = (int)ais.readInteger();
                    this.ggsnTraceDepth = TraceDepth.getInstance(i1);
                    break;
                case _ID_rncTraceDepth:
                    if (!ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + " rncTraceDepth: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    i1 = (int)ais.readInteger();
                    this.rncTraceDepth = TraceDepth.getInstance(i1);
                    break;
                case _ID_bmscTraceDepth:
                    if (!ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + " bmscTraceDepth: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    i1 = (int)ais.readInteger();
                    this.bmscTraceDepth = TraceDepth.getInstance(i1);
                    break;
                case _ID_mmeTraceDepth:
                    if (!ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + " mmeTraceDepth: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    i1 = (int)ais.readInteger();
                    this.mmeTraceDepth = TraceDepth.getInstance(i1);
                    break;
                case _ID_sgwTraceDepth:
                    if (!ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + " sgwTraceDepth: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    i1 = (int)ais.readInteger();
                    this.sgwTraceDepth = TraceDepth.getInstance(i1);
                    break;
                case _ID_pgwTraceDepth:
                    if (!ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + " pgwTraceDepth: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    i1 = (int)ais.readInteger();
                    this.pgwTraceDepth = TraceDepth.getInstance(i1);
                    break;
                case _ID_eNBTraceDepth:
                    if (!ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + " enbTraceDepth: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    i1 = (int)ais.readInteger();
                    this.enbTraceDepth = TraceDepth.getInstance(i1);
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
    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        try {
            if (this.mscSTraceDepth != null)
                asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _ID_mscSTraceDepth, this.mscSTraceDepth.getCode());
            if (this.mgwTraceDepth != null)
                asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _ID_mgwTraceDepth, this.mgwTraceDepth.getCode());
            if (this.sgsnTraceDepth != null)
                asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _ID_sgsnTraceDepth, this.sgsnTraceDepth.getCode());
            if (this.ggsnTraceDepth != null)
                asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _ID_ggsnTraceDepth, this.ggsnTraceDepth.getCode());
            if (this.rncTraceDepth != null)
                asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _ID_rncTraceDepth, this.rncTraceDepth.getCode());
            if (this.bmscTraceDepth != null)
                asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _ID_bmscTraceDepth, this.bmscTraceDepth.getCode());
            if (this.mmeTraceDepth != null)
                asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _ID_mmeTraceDepth, this.mmeTraceDepth.getCode());
            if (this.sgwTraceDepth != null)
                asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _ID_sgwTraceDepth, this.sgwTraceDepth.getCode());
            if (this.pgwTraceDepth != null)
                asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _ID_pgwTraceDepth, this.pgwTraceDepth.getCode());
            if (this.enbTraceDepth != null)
                asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _ID_eNBTraceDepth, this.enbTraceDepth.getCode());

        } catch (IOException e) {
            throw new MAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.mscSTraceDepth != null) {
            sb.append("mscSTraceDepth=");
            sb.append(this.mscSTraceDepth);
            sb.append(", ");
        }
        if (this.mgwTraceDepth != null) {
            sb.append("mgwTraceDepth=");
            sb.append(this.mgwTraceDepth);
            sb.append(", ");
        }
        if (this.sgsnTraceDepth != null) {
            sb.append("sgsnTraceDepth=");
            sb.append(this.sgsnTraceDepth);
            sb.append(", ");
        }
        if (this.ggsnTraceDepth != null) {
            sb.append("ggsnTraceDepth=");
            sb.append(this.ggsnTraceDepth);
            sb.append(", ");
        }
        if (this.rncTraceDepth != null) {
            sb.append("rncTraceDepth=");
            sb.append(this.rncTraceDepth);
            sb.append(", ");
        }
        if (this.bmscTraceDepth != null) {
            sb.append("bmscTraceDepth=");
            sb.append(this.bmscTraceDepth);
            sb.append(", ");
        }
        if (this.mmeTraceDepth != null) {
            sb.append("mmeTraceDepth=");
            sb.append(this.mmeTraceDepth);
            sb.append(", ");
        }
        if (this.sgwTraceDepth != null) {
            sb.append("sgwTraceDepth=");
            sb.append(this.sgwTraceDepth);
            sb.append(", ");
        }
        if (this.pgwTraceDepth != null) {
            sb.append("pgwTraceDepth=");
            sb.append(this.pgwTraceDepth);
            sb.append(", ");
        }
        if (this.enbTraceDepth != null) {
            sb.append("enbTraceDepth=");
            sb.append(this.enbTraceDepth);
            sb.append(", ");
        }

        sb.append("]");
        return sb.toString();
    }

}
