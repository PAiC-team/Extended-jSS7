
package org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation;

import jakarta.xml.bind.DatatypeConverter;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.TAId;
import org.restcomm.protocols.ss7.map.primitives.OctetStringBase;
import org.restcomm.protocols.ss7.map.primitives.TbcdString;

import java.io.IOException;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class TAIdImpl extends OctetStringBase implements TAId {

    private static final String MCC = "mcc";
    private static final String MNC = "mnc";
    private static final String TAC = "tac";

    private static final String DATA = "data";
    private static final String DEFAULT_VALUE = null;

    public TAIdImpl() {
        super(5, 5, "TAId");
    }

    public TAIdImpl(byte[] data) {
        super(5, 5, "TAId", data);
    }

    public byte[] getData() {
        return data;
    }

    public void setData(int mcc, int mnc, int tac) throws MAPException {
        if (mcc < 1 || mcc > 999)
            throw new MAPException("Bad MCC value");
        if (mnc < 0 || mnc > 999)
            throw new MAPException("Bad MNC value");

        this.data = new byte[5];

        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        if (mcc < 100)
            sb.append("0");
        if (mcc < 10)
            sb.append("0");
        sb.append(mcc);

        if (mnc < 100) {
            if (mnc < 10)
                sb2.append("0");
            sb2.append(mnc);
        } else {
            sb.append(mnc % 10);
            sb2.append(mnc / 10);
        }

        AsnOutputStream asnOs = new AsnOutputStream();
        TbcdString.encodeString(asnOs, sb.toString());
        System.arraycopy(asnOs.toByteArray(), 0, this.data, 0, 2);

        asnOs = new AsnOutputStream();
        TbcdString.encodeString(asnOs, sb2.toString());
        System.arraycopy(asnOs.toByteArray(), 0, this.data, 2, 1);

        data[3] = (byte) (tac / 256);
        data[4] = (byte) (tac % 256);
    }

    public int getMCC() throws MAPException {
        if (data == null)
            throw new MAPException("Data must not be empty");
        if (data.length != 5)
            throw new MAPException("Data length must equal 5");

        AsnInputStream ansIS = new AsnInputStream(data);
        String res = null;
        try {
            res = TbcdString.decodeString(ansIS, 3);
        } catch (IOException e) {
            throw new MAPException("IOException when decoding TrackingAreaId: " + e.getMessage(), e);
        } catch (MAPParsingComponentException e) {
            throw new MAPException("MAPParsingComponentException when decoding TrackingAreaId: " + e.getMessage(), e);
        }

        if (res.length() < 5 || res.length() > 6)
            throw new MAPException("Decoded TbcdString must be equal 5 or 6");

        String sMcc = res.substring(0, 3);

        return Integer.parseInt(sMcc);
    }

    public int getMNC() throws MAPException {
        if (data == null)
            throw new MAPException("Data must not be empty");
        if (data.length != 5)
            throw new MAPException("Data length must equal 5");

        AsnInputStream ansIS = new AsnInputStream(data);
        String res = null;
        try {
            res = TbcdString.decodeString(ansIS, 3);
        } catch (IOException e) {
            throw new MAPException("IOException when decoding TrackingAreaId: " + e.getMessage(), e);
        } catch (MAPParsingComponentException e) {
            throw new MAPException("MAPParsingComponentException when decoding TrackingAreaId: " + e.getMessage(), e);
        }

        if (res.length() < 5 || res.length() > 6)
            throw new MAPException("Decoded TbcdString must be equal 5 or 6");

        String sMnc;
        if (res.length() == 5) {
            sMnc = res.substring(3);
        } else {
            sMnc = res.substring(4) + res.substring(3, 4);
        }

        return Integer.parseInt(sMnc);
    }

    public int getTAC() throws MAPException {
        if (data == null)
            throw new MAPException("Data must not be empty");
        if (data.length != 5)
            throw new MAPException("Data length must be equal to 5");

        int tac = (data[3] & 0xFF) * 256 + (data[4] & 0xFF);
        return tac;
    }

    @Override
    public String toString() {

        int mcc = 0;
        int mnc = 0;
        int tac = 0;
        boolean correctData = false;

        try {
            mcc = this.getMCC();
            mnc = this.getMNC();
            tac = this.getTAC();
            correctData = true;
        } catch (MAPException e) {
        }

        StringBuilder sb = new StringBuilder();
        sb.append(this._PrimitiveName);
        sb.append(" [");
        if (correctData) {
            sb.append(MCC+"=");
            sb.append(mcc);
            sb.append(", "+MNC+"=");
            sb.append(mnc);
            sb.append(", "+TAC+"=");
            sb.append(tac);
        } else {
            sb.append("Data=");
            sb.append(this.printDataArr());
        }
        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<TAIdImpl> TA_ID_XML = new XMLFormat<TAIdImpl>(TAIdImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, TAIdImpl taId) throws XMLStreamException {
            String s = xml.getAttribute(DATA, DEFAULT_VALUE);
            if (s != null) {
                taId.data = DatatypeConverter.parseHexBinary(s);
            }
        }

        @Override
        public void write(TAIdImpl taId, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            if (taId.data != null) {
                xml.setAttribute(DATA, DatatypeConverter.printHexBinary(taId.data));
            }
        }
    };

}
