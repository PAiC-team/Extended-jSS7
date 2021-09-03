
package org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.RAIdentity;
import org.restcomm.protocols.ss7.map.primitives.OctetStringBase;
import org.restcomm.protocols.ss7.map.primitives.TbcdString;

import java.io.IOException;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class RAIdentityImpl extends OctetStringBase implements RAIdentity {

    private static final String MCC = "mcc";
    private static final String MNC = "mnc";
    private static final String LAC = "lac";
    private static final String RAC = "rac";

    private static final int DEFAULT_INT_VALUE = 0;

    public RAIdentityImpl() {
        super(6, 6, "RAIdentity");
    }

    public RAIdentityImpl(byte[] data) {
        super(6, 6, "RAIdentity", data);
    }

    public RAIdentityImpl(int mcc, int mnc, int lac, int rac) throws MAPException {
        super(6, 6, "RAIdentity");
        this.setData(mcc, mnc, lac, rac);
    }

    public void setData(int mcc, int mnc, int lac, int rac) throws MAPException {
        if (mcc < 1 || mcc > 999)
            throw new MAPException("Bad MCC value");
        if (mnc < 0 || mnc > 999)
            throw new MAPException("Bad MNC value");

        this.data = new byte[6];

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

        data[3] = (byte) (lac / 256);
        data[4] = (byte) (lac % 256);
        data[5] = (byte) (rac % 256);
    }

    public byte[] getData() {
        return data;
    }

    public int getMCC() throws MAPException {
        if (data == null)
            throw new MAPException("Data must not be empty");
        if (data.length != 6)
            throw new MAPException("Data length must equal 6");

        AsnInputStream ansIS = new AsnInputStream(data);
        String res = null;
        try {
            res = TbcdString.decodeString(ansIS, 3);
        } catch (IOException e) {
            throw new MAPException("IOException when decoding RouteingAreaId: " + e.getMessage(), e);
        } catch (MAPParsingComponentException e) {
            throw new MAPException("MAPParsingComponentException when decoding RouteingAreaId: " + e.getMessage(), e);
        }

        if (res.length() < 5 || res.length() > 6)
            throw new MAPException("Decoded TbcdString must equal 5 or 6");

        String sMcc = res.substring(0, 3);

        return Integer.parseInt(sMcc);
    }

    public int getMNC() throws MAPException {
        if (data == null)
            throw new MAPException("Data must not be empty");
        if (data.length != 6)
            throw new MAPException("Data length must equal 6");

        AsnInputStream ansIS = new AsnInputStream(data);
        String res = null;
        try {
            res = TbcdString.decodeString(ansIS, 3);
        } catch (IOException e) {
            throw new MAPException("IOException when decoding RouteingAreaId: " + e.getMessage(), e);
        } catch (MAPParsingComponentException e) {
            throw new MAPException("MAPParsingComponentException when decoding RouteingAreaId: " + e.getMessage(), e);
        }

        if (res.length() < 5 || res.length() > 6)
            throw new MAPException("Decoded TbcdString must equal 5 or 6");

        String sMnc;
        if (res.length() == 5) {
            sMnc = res.substring(3);
        } else {
            sMnc = res.substring(4) + res.substring(3, 4);
        }

        return Integer.parseInt(sMnc);
    }

    public int getLAC() throws MAPException {
        if (data == null)
            throw new MAPException("Data must not be empty");
        if (data.length != 6)
            throw new MAPException("Data length must equal 6");

        int lac = (data[3] & 0xFF) * 256 + (data[4] & 0xFF);
        return lac;
    }

    public int getRAC() throws MAPException {
        if (data == null)
            throw new MAPException("Data must not be empty");
        if (data.length != 6)
            throw new MAPException("Data length must equal 6");

        int rac = (data[5] & 0xFF) /** 256 + (data[6] & 0xFF)*/;
        return rac;
    }

    @Override
    public String toString() {

        int mcc = 0;
        int mnc = 0;
        int lac = 0;
        int rac = 0;
        boolean correctData = false;

        try {
            mcc = this.getMCC();
            mnc = this.getMNC();
            lac = this.getLAC();
            rac = this.getRAC();
            correctData = true;
        } catch (MAPException e) {
        }

        StringBuilder sb = new StringBuilder();
        sb.append("RAI [");
        if (correctData) {
            sb.append(MCC+"=");
            sb.append(mcc);
            sb.append(", "+MNC+"=");
            sb.append(mnc);
            sb.append(", "+LAC+"=");
            sb.append(lac);
            sb.append(", "+RAC+"");
            sb.append(rac);
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
    protected static final XMLFormat<RAIdentityImpl> ROUTING_AREA_ID_XML = new XMLFormat<RAIdentityImpl>(
        RAIdentityImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml,
                         RAIdentityImpl routingAreaId) throws XMLStreamException {
            int mcc = xml.getAttribute(MCC, DEFAULT_INT_VALUE);
            int mnc = xml.getAttribute(MNC, DEFAULT_INT_VALUE);
            int lac = xml.getAttribute(LAC, DEFAULT_INT_VALUE);
            int cellId = xml.getAttribute(RAC, DEFAULT_INT_VALUE);

            try {
                routingAreaId.setData(mcc, mnc, lac, cellId);
            } catch (MAPException e) {
                throw new XMLStreamException("MAPException when deserializing RouteingAreaIdImpl", e);
            }
        }

        @Override
        public void write(RAIdentityImpl routingAreaId,
                          javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            try {
                xml.setAttribute(MCC, routingAreaId.getMCC());
                xml.setAttribute(MNC, routingAreaId.getMNC());
                xml.setAttribute(LAC, routingAreaId.getLAC());
                xml.setAttribute(RAC, routingAreaId.getRAC());
            } catch (MAPException e) {
                throw new XMLStreamException("MAPException when serializing RouteingAreaIdImpl", e);
            }
        }
    };
}