
package org.restcomm.protocols.ss7.map.service.supplementary;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.map.api.service.supplementary.SSStatus;
import org.restcomm.protocols.ss7.map.primitives.OctetStringLength1Base;

/**
 *
 * @author sergey vetyutnev
 * @author amit bhayani
 *
 */
public class SSStatusImpl extends OctetStringLength1Base implements SSStatus {

    private static final String DATA = "data";
    private static final String Q_BIT = "qBit";
    private static final String P_BIT = "pBit";
    private static final String R_BIT = "rBit";
    private static final String A_BIT = "aBit";

    public static final int _mask_QBit = 0x08;
    public static final int _mask_PBit = 0x04;
    public static final int _mask_RBit = 0x02;
    public static final int _mask_ABit = 0x01;

    public SSStatusImpl() {
        super("SSStatus");
    }

    public SSStatusImpl(int data) {
        super("SSStatus", data);
    }

    public SSStatusImpl(boolean qBit, boolean pBit, boolean rBit, boolean aBit) {
        super("SSStatus", (qBit ? _mask_QBit : 0) + (pBit ? _mask_PBit : 0) + (rBit ? _mask_RBit : 0) + (aBit ? _mask_ABit : 0));
    }

    public int getData() {
        return data;
    }

    public boolean getQBit() {
        return (this.data & _mask_QBit) != 0;
    }

    public boolean getPBit() {
        return (this.data & _mask_PBit) != 0;
    }

    public boolean getRBit() {
        return (this.data & _mask_RBit) != 0;
    }

    public boolean getABit() {
        return (this.data & _mask_ABit) != 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.getQBit()) {
            sb.append("QBit");
            sb.append(", ");
        }
        if (this.getPBit()) {
            sb.append("PBit");
            sb.append(", ");
        }
        if (this.getRBit()) {
            sb.append("RBit");
            sb.append(", ");
        }
        if (this.getABit()) {
            sb.append("ABit");
            sb.append(", ");
        }
        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<SSStatusImpl> SS_STATUS_XML = new XMLFormat<SSStatusImpl>(SSStatusImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, SSStatusImpl ssCode) throws XMLStreamException {
            ssCode.data = xml.get(DATA, Integer.class);
            xml.get(Q_BIT, Boolean.class);
            xml.get(P_BIT, Boolean.class);
            xml.get(R_BIT, Boolean.class);
            xml.get(A_BIT, Boolean.class);

        }

        @Override
        public void write(SSStatusImpl ssCode, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            xml.add(ssCode.getData(), DATA, Integer.class);
            xml.add(ssCode.getQBit(), Q_BIT, Boolean.class);
            xml.add(ssCode.getPBit(), P_BIT, Boolean.class);
            xml.add(ssCode.getRBit(), R_BIT, Boolean.class);
            xml.add(ssCode.getABit(), A_BIT, Boolean.class);
        }
    };

}
