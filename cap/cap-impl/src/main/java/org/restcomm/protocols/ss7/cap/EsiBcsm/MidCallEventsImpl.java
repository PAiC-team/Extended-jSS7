
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
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.cap.api.EsiBcsm.MidCallEvents;
import org.restcomm.protocols.ss7.cap.api.isup.Digits;
import org.restcomm.protocols.ss7.cap.isup.DigitsImpl;
import org.restcomm.protocols.ss7.cap.primitives.CAPAsnPrimitive;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;

/**
*
* @author sergey vetyutnev
*
*/
public class MidCallEventsImpl implements MidCallEvents, CAPAsnPrimitive {
    private static final String DTMF_DIGITS_COMPLETED = "dtmfDigitsCompleted";
    private static final String DTMF_DIGITS_TIME_OUT = "dtmfDigitsTimeOut";

    public static final int _ID_dTMFDigitsCompleted = 3;
    public static final int _ID_dTMFDigitsTimeOut = 4;

    public static final String _PrimitiveName = "MidCallEvents";

    private Digits dtmfDigitsCompleted;
    private Digits dtmfDigitsTimeOut;

    public MidCallEventsImpl() {
    }

    public MidCallEventsImpl(Digits dtmfDigits, boolean isDtmfDigitsCompleted) {
        if (isDtmfDigitsCompleted)
            dtmfDigitsCompleted = dtmfDigits;
        else
            dtmfDigitsTimeOut = dtmfDigits;
    }

    @Override
    public Digits getDTMFDigitsCompleted() {
        return dtmfDigitsCompleted;
    }

    @Override
    public Digits getDTMFDigitsTimeOut() {
        return dtmfDigitsTimeOut;
    }


    @Override
    public int getTag() throws CAPException {

        if (dtmfDigitsCompleted != null) {
            return _ID_dTMFDigitsCompleted;
        } else if (dtmfDigitsTimeOut != null) {
            return _ID_dTMFDigitsTimeOut;
        }

        throw new CAPException("Error while encoding " + _PrimitiveName + ": no choice is specified");
    }

    @Override
    public int getTagClass() {
        return Tag.CLASS_CONTEXT_SPECIFIC;
    }

    @Override
    public boolean getIsPrimitive() {
        return true;
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
        }
    }

    private void _decode(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException, MAPParsingComponentException,
            IOException, AsnException {

        this.dtmfDigitsCompleted = null;
        this.dtmfDigitsTimeOut = null;

        int tag = asnInputStream.getTag();

        if (asnInputStream.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
            switch (tag) {
            case _ID_dTMFDigitsCompleted:
                this.dtmfDigitsCompleted = new DigitsImpl();
                this.dtmfDigitsCompleted.setIsGenericDigits();
                ((DigitsImpl) this.dtmfDigitsCompleted).decodeData(asnInputStream, length);
                break;
            case _ID_dTMFDigitsTimeOut:
                this.dtmfDigitsTimeOut = new DigitsImpl();
                this.dtmfDigitsTimeOut.setIsGenericDigits();
                ((DigitsImpl) this.dtmfDigitsTimeOut).decodeData(asnInputStream, length);
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
        if (dtmfDigitsCompleted != null) {
            ((DigitsImpl) dtmfDigitsCompleted).encodeData(asnOutputStream);
            return;
        } else if (dtmfDigitsTimeOut != null) {
            ((DigitsImpl) dtmfDigitsTimeOut).encodeData(asnOutputStream);
            return;
        }

        throw new CAPException("Error while encoding " + _PrimitiveName + ": no choice is specified");
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (dtmfDigitsCompleted != null) {
            sb.append("dtmfDigitsCompleted=[");
            sb.append(dtmfDigitsCompleted.toString());
            sb.append("]");
        } else if (dtmfDigitsTimeOut != null) {
            sb.append("dtmfDigitsTimeOut=[");
            sb.append(dtmfDigitsTimeOut.toString());
            sb.append("]");
        }

        sb.append("]");

        return sb.toString();
    }

    protected static final XMLFormat<MidCallEventsImpl> MID_CALL_EVENTS_XML = new XMLFormat<MidCallEventsImpl>(MidCallEventsImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, MidCallEventsImpl midCallEvents) throws XMLStreamException {
            midCallEvents.dtmfDigitsCompleted = xml.get(DTMF_DIGITS_COMPLETED, DigitsImpl.class);
            if (midCallEvents.dtmfDigitsCompleted != null)
                midCallEvents.dtmfDigitsCompleted.setIsGenericDigits();
            midCallEvents.dtmfDigitsTimeOut = xml.get(DTMF_DIGITS_TIME_OUT, DigitsImpl.class);
            if (midCallEvents.dtmfDigitsTimeOut != null)
                midCallEvents.dtmfDigitsTimeOut.setIsGenericDigits();
        }

        @Override
        public void write(MidCallEventsImpl midCallEvents, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            if (midCallEvents.dtmfDigitsCompleted != null) {
                xml.add((DigitsImpl) midCallEvents.dtmfDigitsCompleted, DTMF_DIGITS_COMPLETED, DigitsImpl.class);
            }
            if (midCallEvents.dtmfDigitsTimeOut != null) {
                xml.add((DigitsImpl) midCallEvents.dtmfDigitsTimeOut, DTMF_DIGITS_TIME_OUT, DigitsImpl.class);
            }
        }
    };

}
