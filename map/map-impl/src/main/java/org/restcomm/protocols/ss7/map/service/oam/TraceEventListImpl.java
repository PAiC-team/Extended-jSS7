
package org.restcomm.protocols.ss7.map.service.oam;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.service.oam.BMSCEventList;
import org.restcomm.protocols.ss7.map.api.service.oam.GGSNEventList;
import org.restcomm.protocols.ss7.map.api.service.oam.MGWEventList;
import org.restcomm.protocols.ss7.map.api.service.oam.MMEEventList;
import org.restcomm.protocols.ss7.map.api.service.oam.MSCSEventList;
import org.restcomm.protocols.ss7.map.api.service.oam.PGWEventList;
import org.restcomm.protocols.ss7.map.api.service.oam.SGSNEventList;
import org.restcomm.protocols.ss7.map.api.service.oam.SGWEventList;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceEventList;
import org.restcomm.protocols.ss7.map.primitives.SequenceBase;

/**
*
* @author sergey vetyutnev
*
*/
public class TraceEventListImpl extends SequenceBase implements TraceEventList {

    public static final int _ID_mscSList = 0;
    public static final int _ID_mgwList = 1;
    public static final int _ID_sgsnList = 2;
    public static final int _ID_ggsnList = 3;
    public static final int _ID_bmscList = 4;
    public static final int _ID_mmeList = 5;
    public static final int _ID_sgwList = 6;
    public static final int _ID_pgwList = 7;

    private MSCSEventList mscSList;
    private MGWEventList mgwList;
    private SGSNEventList sgsnList;
    private GGSNEventList ggsnList;
    private BMSCEventList bmscList;
    private MMEEventList mmeList;
    private SGWEventList sgwList;
    private PGWEventList pgwList;

    public TraceEventListImpl() {
        super("TraceEventList");
    }

    public TraceEventListImpl(MSCSEventList mscSList, MGWEventList mgwList, SGSNEventList sgsnList, GGSNEventList ggsnList, BMSCEventList bmscList,
            MMEEventList mmeList, SGWEventList sgwList, PGWEventList pgwList) {
        super("TraceEventList");

        this.mscSList = mscSList;
        this.mgwList = mgwList;
        this.sgsnList = sgsnList;
        this.ggsnList = ggsnList;
        this.bmscList = bmscList;
        this.mmeList = mmeList;
        this.sgwList = sgwList;
        this.pgwList = pgwList;
    }

    @Override
    public MSCSEventList getMscSList() {
        return mscSList;
    }

    @Override
    public MGWEventList getMgwList() {
        return mgwList;
    }

    @Override
    public SGSNEventList getSgsnList() {
        return sgsnList;
    }

    @Override
    public GGSNEventList getGgsnList() {
        return ggsnList;
    }

    @Override
    public BMSCEventList getBmscList() {
        return bmscList;
    }

    @Override
    public MMEEventList getMmeList() {
        return mmeList;
    }

    @Override
    public SGWEventList getSgwList() {
        return sgwList;
    }

    @Override
    public PGWEventList getPgwList() {
        return pgwList;
    }

    @Override
    protected void _decode(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {
        this.mscSList = null;
        this.mgwList = null;
        this.sgsnList = null;
        this.ggsnList = null;
        this.bmscList = null;
        this.mmeList = null;
        this.sgwList = null;
        this.pgwList = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);

        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {

                switch (tag) {
                case _ID_mscSList:
                    if (!ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + " mscSList: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.mscSList = new MSCSEventListImpl();
                    ((MSCSEventListImpl) this.mscSList).decodeAll(ais);
                    break;
                case _ID_mgwList:
                    if (!ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + " mgwList: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.mgwList = new MGWEventListImpl();
                    ((MGWEventListImpl) this.mgwList).decodeAll(ais);
                    break;
                case _ID_sgsnList:
                    if (!ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + " mgwList: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.sgsnList = new SGSNEventListImpl();
                    ((SGSNEventListImpl) this.sgsnList).decodeAll(ais);
                    break;
                case _ID_ggsnList:
                    if (!ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + " ggsnList: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.ggsnList = new GGSNEventListImpl();
                    ((GGSNEventListImpl) this.ggsnList).decodeAll(ais);
                    break;
                case _ID_bmscList:
                    if (!ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + " bmscList: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.bmscList = new BMSCEventListImpl();
                    ((BMSCEventListImpl) this.bmscList).decodeAll(ais);
                    break;
                case _ID_mmeList:
                    if (!ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + " mmeList: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.mmeList = new MMEEventListImpl();
                    ((MMEEventListImpl) this.mmeList).decodeAll(ais);
                    break;
                case _ID_sgwList:
                    if (!ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + " sgwList: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.sgwList = new SGWEventListImpl();
                    ((SGWEventListImpl) this.sgwList).decodeAll(ais);
                    break;
                case _ID_pgwList:
                    if (!ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + " pgwList: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.pgwList = new PGWEventListImpl();
                    ((PGWEventListImpl) this.pgwList).decodeAll(ais);
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
        if (this.mscSList != null)
            ((MSCSEventListImpl) this.mscSList).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_mscSList);
        if (this.mgwList != null)
            ((MGWEventListImpl) this.mgwList).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_mgwList);
        if (this.sgsnList != null)
            ((SGSNEventListImpl) this.sgsnList).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_sgsnList);
        if (this.ggsnList != null)
            ((GGSNEventListImpl) this.ggsnList).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_ggsnList);
        if (this.bmscList != null)
            ((BMSCEventListImpl) this.bmscList).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_bmscList);
        if (this.mmeList != null)
            ((MMEEventListImpl) this.mmeList).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_mmeList);
        if (this.sgwList != null)
            ((SGWEventListImpl) this.sgwList).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_sgwList);
        if (this.pgwList != null)
            ((PGWEventListImpl) this.pgwList).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_pgwList);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.mscSList != null) {
            sb.append("mscSList=");
            sb.append(this.mscSList);
            sb.append(", ");
        }
        if (this.mgwList != null) {
            sb.append("mgwList=");
            sb.append(this.mgwList);
            sb.append(", ");
        }
        if (this.sgsnList != null) {
            sb.append("sgsnList=");
            sb.append(this.sgsnList);
            sb.append(", ");
        }
        if (this.ggsnList != null) {
            sb.append("ggsnList=");
            sb.append(this.ggsnList);
            sb.append(", ");
        }
        if (this.bmscList != null) {
            sb.append("bmscList=");
            sb.append(this.bmscList);
            sb.append(", ");
        }
        if (this.mmeList != null) {
            sb.append("mmeList=");
            sb.append(this.mmeList);
            sb.append(", ");
        }
        if (this.sgwList != null) {
            sb.append("sgwList=");
            sb.append(this.sgwList);
            sb.append(", ");
        }
        if (this.pgwList != null) {
            sb.append("pgwList=");
            sb.append(this.pgwList);
            sb.append(", ");
        }

        sb.append("]");
        return sb.toString();
    }

}
