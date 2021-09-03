
package org.restcomm.protocols.ss7.cap.EsiBcsm;

import java.io.IOException;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentException;
import org.restcomm.protocols.ss7.cap.api.EsiBcsm.CollectedInfoSpecificInfo;
import org.restcomm.protocols.ss7.cap.api.EsiBcsm.DpSpecificInfoAlt;
import org.restcomm.protocols.ss7.cap.api.EsiBcsm.OServiceChangeSpecificInfo;
import org.restcomm.protocols.ss7.cap.api.EsiBcsm.TServiceChangeSpecificInfo;
import org.restcomm.protocols.ss7.cap.primitives.SequenceBase;
import org.restcomm.protocols.ss7.inap.api.INAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;

/**
*
* @author sergey vetyutnev
*
*/
public class DpSpecificInfoAltImpl extends SequenceBase implements DpSpecificInfoAlt {

    private static final String O_SERVICE_CHANGE_SPECIFIC_INFO = "oServiceChangeSpecificInfo";
    private static final String COLLECTED_INFO_SPECIFIC_INFO = "collectedInfoSpecificInfo";
    private static final String T_SERVICE_CHANGE_SPECIFIC_INFO = "tServiceChangeSpecificInfo";

    public static final int _ID_oServiceChangeSpecificInfo = 0;
    public static final int _ID_tServiceChangeSpecificInfo = 1;
    public static final int _ID_collectedInfoSpecificInfo = 2;

    private OServiceChangeSpecificInfo oServiceChangeSpecificInfo;
    private CollectedInfoSpecificInfo collectedInfoSpecificInfo;
    private TServiceChangeSpecificInfo tServiceChangeSpecificInfo;

    public DpSpecificInfoAltImpl() {
        super("DpSpecificInfoAlt");
    }

    public DpSpecificInfoAltImpl(OServiceChangeSpecificInfo oServiceChangeSpecificInfo, CollectedInfoSpecificInfo collectedInfoSpecificInfo,
            TServiceChangeSpecificInfo tServiceChangeSpecificInfo) {
        super("DpSpecificInfoAlt");
        this.oServiceChangeSpecificInfo = oServiceChangeSpecificInfo;
        this.collectedInfoSpecificInfo = collectedInfoSpecificInfo;
        this.tServiceChangeSpecificInfo = tServiceChangeSpecificInfo;
    }

    @Override
    public OServiceChangeSpecificInfo getOServiceChangeSpecificInfo() {
        return oServiceChangeSpecificInfo;
    }

    @Override
    public CollectedInfoSpecificInfo getCollectedInfoSpecificInfo() {
        return collectedInfoSpecificInfo;
    }

    @Override
    public TServiceChangeSpecificInfo getTServiceChangeSpecificInfo() {
        return tServiceChangeSpecificInfo;
    }

    @Override
    protected void _decode(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException, IOException, AsnException, MAPParsingComponentException,
            INAPParsingComponentException {

        this.oServiceChangeSpecificInfo = null;
        this.collectedInfoSpecificInfo = null;
        this.tServiceChangeSpecificInfo = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                case _ID_oServiceChangeSpecificInfo:
                    this.oServiceChangeSpecificInfo = new OServiceChangeSpecificInfoImpl();
                    ((OServiceChangeSpecificInfoImpl) this.oServiceChangeSpecificInfo).decodeAll(ais);
                    break;
                case _ID_tServiceChangeSpecificInfo:
                    this.tServiceChangeSpecificInfo = new TServiceChangeSpecificInfoImpl();
                    ((TServiceChangeSpecificInfoImpl) this.tServiceChangeSpecificInfo).decodeAll(ais);
                    break;
                case _ID_collectedInfoSpecificInfo:
                    this.collectedInfoSpecificInfo = new CollectedInfoSpecificInfoImpl();
                    ((CollectedInfoSpecificInfoImpl) this.collectedInfoSpecificInfo).decodeAll(ais);
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
        if (this.oServiceChangeSpecificInfo != null) {
            ((OServiceChangeSpecificInfoImpl) this.oServiceChangeSpecificInfo).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_oServiceChangeSpecificInfo);
        }
        if (this.tServiceChangeSpecificInfo != null) {
            ((TServiceChangeSpecificInfoImpl) this.tServiceChangeSpecificInfo).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_tServiceChangeSpecificInfo);
        }
        if (this.collectedInfoSpecificInfo != null) {
            ((CollectedInfoSpecificInfoImpl) this.collectedInfoSpecificInfo).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_collectedInfoSpecificInfo);
        }
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.oServiceChangeSpecificInfo != null) {
            sb.append("oServiceChangeSpecificInfo=");
            sb.append(oServiceChangeSpecificInfo);
            sb.append(", ");
        }
        if (this.tServiceChangeSpecificInfo != null) {
            sb.append("tServiceChangeSpecificInfo=");
            sb.append(tServiceChangeSpecificInfo);
            sb.append(", ");
        }
        if (this.collectedInfoSpecificInfo != null) {
            sb.append("collectedInfoSpecificInfo=");
            sb.append(collectedInfoSpecificInfo);
            sb.append(", ");
        }

        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<DpSpecificInfoAltImpl> DP_SPECIFIC_INFO_ALT_XML = new XMLFormat<DpSpecificInfoAltImpl>(DpSpecificInfoAltImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, DpSpecificInfoAltImpl dpSpecificInfoAlt) throws XMLStreamException {
            dpSpecificInfoAlt.oServiceChangeSpecificInfo = xml.get(O_SERVICE_CHANGE_SPECIFIC_INFO, OServiceChangeSpecificInfoImpl.class);
            dpSpecificInfoAlt.collectedInfoSpecificInfo = xml.get(COLLECTED_INFO_SPECIFIC_INFO, CollectedInfoSpecificInfoImpl.class);
            dpSpecificInfoAlt.tServiceChangeSpecificInfo = xml.get(T_SERVICE_CHANGE_SPECIFIC_INFO, TServiceChangeSpecificInfoImpl.class);
        }

        @Override
        public void write(DpSpecificInfoAltImpl dpSpecificInfoAlt, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            if (dpSpecificInfoAlt.oServiceChangeSpecificInfo != null)
                xml.add((OServiceChangeSpecificInfoImpl) dpSpecificInfoAlt.oServiceChangeSpecificInfo, O_SERVICE_CHANGE_SPECIFIC_INFO,
                        OServiceChangeSpecificInfoImpl.class);
            if (dpSpecificInfoAlt.collectedInfoSpecificInfo != null)
                xml.add((CollectedInfoSpecificInfoImpl) dpSpecificInfoAlt.collectedInfoSpecificInfo, COLLECTED_INFO_SPECIFIC_INFO,
                        CollectedInfoSpecificInfoImpl.class);
            if (dpSpecificInfoAlt.tServiceChangeSpecificInfo != null)
                xml.add((TServiceChangeSpecificInfoImpl) dpSpecificInfoAlt.tServiceChangeSpecificInfo, T_SERVICE_CHANGE_SPECIFIC_INFO,
                        TServiceChangeSpecificInfoImpl.class);
        }
    };

}
