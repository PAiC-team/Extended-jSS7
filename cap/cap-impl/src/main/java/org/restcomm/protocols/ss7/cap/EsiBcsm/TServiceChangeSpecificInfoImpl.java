
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
import org.restcomm.protocols.ss7.cap.api.EsiBcsm.TServiceChangeSpecificInfo;
import org.restcomm.protocols.ss7.cap.primitives.SequenceBase;
import org.restcomm.protocols.ss7.inap.api.INAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtBasicServiceCode;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.ExtBasicServiceCodeImpl;

/**
*
* @author sergey vetyutnev
*
*/
public class TServiceChangeSpecificInfoImpl extends SequenceBase implements TServiceChangeSpecificInfo {

    public static final String EXT_BASIC_SERVICE_CODE = "extBasicServiceCode";

    public static final int _ID_extBasicServiceCode = 0;

    private ExtBasicServiceCode extBasicServiceCode;

    public TServiceChangeSpecificInfoImpl() {
        super("TServiceChangeSpecificInfo");
    }

    public TServiceChangeSpecificInfoImpl(ExtBasicServiceCode extBasicServiceCode) {
        super("TServiceChangeSpecificInfo");

        this.extBasicServiceCode = extBasicServiceCode;
    }

    @Override
    public ExtBasicServiceCode getExtBasicServiceCode() {
        return extBasicServiceCode;
    }

    @Override
    protected void _decode(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException, IOException, AsnException, MAPParsingComponentException,
            INAPParsingComponentException {

        this.extBasicServiceCode = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                case _ID_extBasicServiceCode:
                    AsnInputStream ais2 = ais.readSequenceStream();
                    ais2.readTag();
                    this.extBasicServiceCode = new ExtBasicServiceCodeImpl();
                    ((ExtBasicServiceCodeImpl) this.extBasicServiceCode).decodeAll(ais2);
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

        try {
            if (this.extBasicServiceCode != null) {
                asnOutputStream.writeTag(Tag.CLASS_CONTEXT_SPECIFIC, false, _ID_extBasicServiceCode);
                int pos = asnOutputStream.StartContentDefiniteLength();
                ((ExtBasicServiceCodeImpl) this.extBasicServiceCode).encodeAll(asnOutputStream);
                asnOutputStream.FinalizeContent(pos);
            }
        } catch (AsnException e) {
            throw new CAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        } catch (MAPException e) {
            throw new CAPException("MAPException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.extBasicServiceCode != null) {
            sb.append("extBasicServiceCode=");
            sb.append(extBasicServiceCode.toString());
            sb.append(", ");
        }

        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<TServiceChangeSpecificInfoImpl> T_SERVICE_CHANGE_SPECIFIC_INFO_XML = new XMLFormat<TServiceChangeSpecificInfoImpl>(
            TServiceChangeSpecificInfoImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, TServiceChangeSpecificInfoImpl tServiceChangeSpecificInfo)
                throws XMLStreamException {
            tServiceChangeSpecificInfo.extBasicServiceCode = xml.get(EXT_BASIC_SERVICE_CODE, ExtBasicServiceCodeImpl.class);
        }

        @Override
        public void write(TServiceChangeSpecificInfoImpl tServiceChangeSpecificInfo, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {

            if (tServiceChangeSpecificInfo.extBasicServiceCode != null) {
                xml.add(((ExtBasicServiceCodeImpl) tServiceChangeSpecificInfo.extBasicServiceCode), EXT_BASIC_SERVICE_CODE, ExtBasicServiceCodeImpl.class);
            }
        }
    };

}
