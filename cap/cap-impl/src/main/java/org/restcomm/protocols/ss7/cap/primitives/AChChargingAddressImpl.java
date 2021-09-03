
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
import org.restcomm.protocols.ss7.cap.api.primitives.AChChargingAddress;
import org.restcomm.protocols.ss7.inap.api.INAPException;
import org.restcomm.protocols.ss7.inap.api.INAPParsingComponentException;
import org.restcomm.protocols.ss7.inap.api.primitives.LegID;
import org.restcomm.protocols.ss7.inap.primitives.LegIDImpl;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;

/**
*
* @author sergey vetyutnev
*
*/
public class AChChargingAddressImpl implements AChChargingAddress, CAPAsnPrimitive {

    private static final String LEG_ID = "legID";
    private static final String SRF_CONNECTION = "srfConnection";

    public static final int _ID_legID = 2;
    public static final int _ID_srfConnection = 50;

    public static final String _PrimitiveName = "AChChargingAddress";

    private LegID legID;
    private int srfConnection;

    public AChChargingAddressImpl() {
    }

    public AChChargingAddressImpl(LegID legID) {
        this.legID = legID;
    }

    public AChChargingAddressImpl(int srfConnection) {
        this.srfConnection = srfConnection;
    }


    @Override
    public LegID getLegID() {
        return legID;
    }

    @Override
    public int getSrfConnection() {
        return srfConnection;
    }

    @Override
    public int getTag() throws CAPException {
        if (legID != null) {
            return _ID_legID;
        } else if (srfConnection != 0) {
            return _ID_srfConnection;
        }

        throw new CAPException("Error while encoding " + _PrimitiveName + ": no choice is specified");
    }

    @Override
    public int getTagClass() {
        return Tag.CLASS_CONTEXT_SPECIFIC;
    }

    @Override
    public boolean getIsPrimitive() {
        if (legID != null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void decodeAll(AsnInputStream asnInputStream) throws CAPParsingComponentException {
        try {
            int length = asnInputStream.readLength();
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new CAPParsingComponentException("IOException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    CAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new CAPParsingComponentException("AsnException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    CAPParsingComponentExceptionReason.MistypedParameter);
        } catch (MAPParsingComponentException e) {
            throw new CAPParsingComponentException("MAPParsingComponentException when decoding " + _PrimitiveName + ": "
                    + e.getMessage(), e, CAPParsingComponentExceptionReason.MistypedParameter);
        } catch (INAPParsingComponentException e) {
            throw new CAPParsingComponentException("INAPParsingComponentException when decoding " + _PrimitiveName + ": "
                    + e.getMessage(), e, CAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    @Override
    public void decodeData(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException {
        try {
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new CAPParsingComponentException("IOException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    CAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new CAPParsingComponentException("AsnException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    CAPParsingComponentExceptionReason.MistypedParameter);
        } catch (MAPParsingComponentException e) {
            throw new CAPParsingComponentException("MAPParsingComponentException when decoding " + _PrimitiveName + ": "
                    + e.getMessage(), e, CAPParsingComponentExceptionReason.MistypedParameter);
        } catch (INAPParsingComponentException e) {
            throw new CAPParsingComponentException("INAPParsingComponentException when decoding " + _PrimitiveName + ": "
                    + e.getMessage(), e, CAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    private void _decode(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException, MAPParsingComponentException, INAPParsingComponentException,
            IOException, AsnException {

        this.legID = null;
        this.srfConnection = 0;

        int tag = asnInputStream.getTag();

        if (asnInputStream.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
            switch (tag) {
            case _ID_legID:
                if (asnInputStream.isTagPrimitive())
                    throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName + "-legID: parameter is primitive",
                            CAPParsingComponentExceptionReason.MistypedParameter);
                AsnInputStream ais2 = asnInputStream.readSequenceStreamData(length);
                ais2.readTag();
                this.legID = new LegIDImpl();
                ((LegIDImpl) this.legID).decodeAll(ais2);
                break;
            case _ID_srfConnection:
                this.srfConnection = (int) asnInputStream.readIntegerData(length);
                if (this.srfConnection < 1 || this.srfConnection > 127)
                    throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName + "-srfConnection: possible value 1..127, received="
                            + srfConnection, CAPParsingComponentExceptionReason.MistypedParameter);
                break;

                default:
                    throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName + ": bad choice tag",
                            CAPParsingComponentExceptionReason.MistypedParameter);
            }
        } else {
            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName + ": bad choice tagClass",
                    CAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    @Override
    public void encodeAll(AsnOutputStream asnOutputStream) throws CAPException {
        this.encodeAll(asnOutputStream, this.getTagClass(), this.getTag());
    }

    @Override
    public void encodeAll(AsnOutputStream asnOutputStream, int tagClass, int tag) throws CAPException {

        try {
            asnOutputStream.writeTag(tagClass, this.getIsPrimitive(), tag);
            int pos = asnOutputStream.StartContentDefiniteLength();
            this.encodeData(asnOutputStream);
            asnOutputStream.FinalizeContent(pos);
        } catch (AsnException e) {
            throw new CAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws CAPException {
        try {
            if (legID != null) {
                ((LegIDImpl) legID).encodeAll(asnOutputStream);
                return;
            } else if (srfConnection != 0) {
                if (srfConnection < 1 || srfConnection > 127)
                    throw new CAPException("Error when encoding " + _PrimitiveName + ": srfConnection must be 1..127, supplied=" + srfConnection);

                asnOutputStream.writeIntegerData(srfConnection);
                return;
            }
        } catch (IOException e) {
            throw new CAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        } catch (INAPException e) {
            throw new CAPException("INAPException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }

        throw new CAPException("Error while encoding " + _PrimitiveName + ": no choice is specified");
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (legID != null) {
            sb.append("legID=[");
            sb.append(legID);
            sb.append("]");
        } else if (srfConnection != 0) {
            sb.append("srfConnection=[");
            sb.append(srfConnection);
            sb.append("]");
        }

        sb.append("]");

        return sb.toString();
    }

    protected static final XMLFormat<AChChargingAddressImpl> A_CH_CHARGING_ADDRESS_XML = new XMLFormat<AChChargingAddressImpl>(AChChargingAddressImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, AChChargingAddressImpl aChChargingAddress) throws XMLStreamException {
            aChChargingAddress.legID = xml.get(LEG_ID, LegIDImpl.class);
            Integer i1 = xml.get(SRF_CONNECTION, Integer.class);
            if (i1 != null)
                aChChargingAddress.srfConnection = i1;
        }

        @Override
        public void write(AChChargingAddressImpl aChChargingAddress, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            if (aChChargingAddress.legID != null) {
                xml.add((LegIDImpl) aChChargingAddress.legID, LEG_ID, LegIDImpl.class);
            }
            if (aChChargingAddress.srfConnection != 0) {
                xml.add((Integer) aChChargingAddress.srfConnection, SRF_CONNECTION, Integer.class);
            }
        }
    };

}
