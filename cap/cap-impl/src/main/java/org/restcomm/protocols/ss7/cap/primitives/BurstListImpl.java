
package org.restcomm.protocols.ss7.cap.primitives;

import java.io.IOException;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.cap.api.primitives.Burst;
import org.restcomm.protocols.ss7.cap.api.primitives.BurstList;
import org.restcomm.protocols.ss7.inap.api.INAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;

/**
*
* @author sergey vetyutnev
*
*/
public class BurstListImpl extends SequenceBase implements BurstList {

    public static final int _ID_warningPeriod = 0;
    public static final int _ID_bursts = 1;

    private static final String WARNING_PERIOD = "warningPeriod";
    private static final String BURSTS = "bursts";

    private Integer warningPeriod;
    private Burst bursts;

    public BurstListImpl() {
        super("BurstList");
    }

    public BurstListImpl(Integer warningPeriod, Burst burst) {
        super("BurstList");

        this.warningPeriod = warningPeriod;
        this.bursts = burst;
    }

    @Override
    public Integer getWarningPeriod() {
        return warningPeriod;
    }

    @Override
    public Burst getBursts() {
        return bursts;
    }

    @Override
    protected void _decode(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException, IOException, AsnException, MAPParsingComponentException,
            INAPParsingComponentException {

        this.warningPeriod = null;
        this.bursts = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                case _ID_warningPeriod:
                    this.warningPeriod = (int) ais.readInteger();
                    if (this.warningPeriod < 1 || this.warningPeriod > 1200)
                        throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName + ": warningPeriod must be 1..1200, received: "
                                + this.warningPeriod, CAPParsingComponentExceptionReason.MistypedParameter);
                    break;
                case _ID_bursts:
                    this.bursts = new BurstImpl();
                    ((BurstImpl) this.bursts).decodeAll(ais);
                    break;

                default:
                    ais.advanceElement();
                    break;
                }
            } else {
                ais.advanceElement();
            }
        }

        if (this.bursts == null)
            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName + ": bursts is mandatory but not found ",
                    CAPParsingComponentExceptionReason.MistypedParameter);
    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws CAPException {

        if (this.warningPeriod != null && (this.warningPeriod < 1 || this.warningPeriod > 1200))
            throw new CAPException("Error while encoding " + _PrimitiveName + ": warningPeriod must be 1..1200, supplied: " + this.warningPeriod);
        if (this.bursts == null)
            throw new CAPException("Error while encoding " + _PrimitiveName + ": bursts parameter is mandatory but is not set supplied: ");

        try {
            if (warningPeriod != null)
                asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _ID_warningPeriod, warningPeriod);
            ((BurstImpl) this.bursts).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_bursts);
        } catch (IOException e) {
            throw new CAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new CAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");
        if (this.warningPeriod != null) {
            sb.append("warningPeriod=");
            sb.append(warningPeriod);
            sb.append(", ");
        }
        if (this.bursts != null) {
            sb.append("bursts=");
            sb.append(bursts);
            sb.append(", ");
        }
        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<BurstListImpl> BURST_LIST_XML = new XMLFormat<BurstListImpl>(BurstListImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, BurstListImpl burstList) throws XMLStreamException {
            burstList.warningPeriod = xml.get(WARNING_PERIOD, Integer.class);
            burstList.bursts = xml.get(BURSTS, BurstImpl.class);
        }

        @Override
        public void write(BurstListImpl burstList, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            if (burstList.warningPeriod != null)
                xml.add(burstList.warningPeriod, WARNING_PERIOD, Integer.class);
            if (burstList.bursts != null)
                xml.add((BurstImpl) burstList.bursts, BURSTS, BurstImpl.class);
        }
    };

}
