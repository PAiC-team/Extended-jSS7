
package org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive;

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
import org.restcomm.protocols.ss7.cap.api.primitives.ErrorTreatment;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.CollectedDigits;
import org.restcomm.protocols.ss7.cap.primitives.SequenceBase;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.ByteArrayContainer;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class CollectedDigitsImpl extends SequenceBase implements CollectedDigits {

    public static final int _ID_minimumNumberOfDigits = 0;
    public static final int _ID_maximumNumberOfDigits = 1;
    public static final int _ID_endOfReplyDigit = 2;
    public static final int _ID_cancelDigit = 3;
    public static final int _ID_startDigit = 4;
    public static final int _ID_firstDigitTimeOut = 5;
    public static final int _ID_interDigitTimeOut = 6;
    public static final int _ID_errorTreatment = 7;
    public static final int _ID_interruptableAnnouncementIndicator = 8;
    public static final int _ID_voiceInformation = 9;
    public static final int _ID_voiceBack = 10;

    private static final String MINIMUM_NB_OF_DIGITS = "minimumNumberOfDigits";
    private static final String MAXIMUM_NB_OF_DIGITS = "maximumNumberOfDigits";
    private static final String END_OF_REPLY_DIGIT = "endOfReplyDigit";
    private static final String CANCEL_DIGIT = "cancelDigit";
    private static final String START_DIGIT = "startDigit";
    private static final String FIRST_DIGIT_TIME_OUT = "firstDigitTimeOut";
    private static final String INTER_DIGIT_TIME_OUT = "interDigitTimeOut";
    private static final String ERROR_TREATMENT = "errorTreatment";
    private static final String INTERRUPTABLE_ANN_IND = "interruptableAnnouncementIndicator";
    private static final String VOICE_INFORMATION = "voiceInformation";
    private static final String VOICE_BACK = "voiceBack";

    private Integer minimumNumberOfDigits;
    private int maximumNumberOfDigits;
    private byte[] endOfReplyDigit;
    private byte[] cancelDigit;
    private byte[] startDigit;
    private Integer firstDigitTimeOut;
    private Integer interDigitTimeOut;
    private ErrorTreatment errorTreatment;
    private Boolean interruptableAnnouncementIndicator;
    private Boolean voiceInformation;
    private Boolean voiceBack;

    public CollectedDigitsImpl() {
        super("CollectedDigits");
    }

    public CollectedDigitsImpl(Integer minimumNumberOfDigits, int maximumNumberOfDigits, byte[] endOfReplyDigit, byte[] cancelDigit,
            byte[] startDigit, Integer firstDigitTimeOut, Integer interDigitTimeOut, ErrorTreatment errorTreatment,
            Boolean interruptableAnnouncementIndicator, Boolean voiceInformation, Boolean voiceBack) {
        super("CollectedDigits");

        this.minimumNumberOfDigits = minimumNumberOfDigits;
        this.maximumNumberOfDigits = maximumNumberOfDigits;
        this.endOfReplyDigit = endOfReplyDigit;
        this.cancelDigit = cancelDigit;
        this.startDigit = startDigit;
        this.firstDigitTimeOut = firstDigitTimeOut;
        this.interDigitTimeOut = interDigitTimeOut;
        this.errorTreatment = errorTreatment;
        this.interruptableAnnouncementIndicator = interruptableAnnouncementIndicator;
        this.voiceInformation = voiceInformation;
        this.voiceBack = voiceBack;
    }

    @Override
    public Integer getMinimumNumberOfDigits() {
        return minimumNumberOfDigits;
    }

    @Override
    public int getMaximumNumberOfDigits() {
        return maximumNumberOfDigits;
    }

    @Override
    public byte[] getEndOfReplyDigit() {
        return endOfReplyDigit;
    }

    @Override
    public byte[] getCancelDigit() {
        return cancelDigit;
    }

    @Override
    public byte[] getStartDigit() {
        return startDigit;
    }

    @Override
    public Integer getFirstDigitTimeOut() {
        return firstDigitTimeOut;
    }

    @Override
    public Integer getInterDigitTimeOut() {
        return interDigitTimeOut;
    }

    @Override
    public ErrorTreatment getErrorTreatment() {
        return errorTreatment;
    }

    @Override
    public Boolean getInterruptableAnnouncementIndicator() {
        return interruptableAnnouncementIndicator;
    }

    @Override
    public Boolean getVoiceInformation() {
        return voiceInformation;
    }

    @Override
    public Boolean getVoiceBack() {
        return voiceBack;
    }

    protected void _decode(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException, IOException, AsnException {
        this.minimumNumberOfDigits = null;
        this.maximumNumberOfDigits = -1;
        this.endOfReplyDigit = null;
        this.cancelDigit = null;
        this.startDigit = null;
        this.firstDigitTimeOut = null;
        this.interDigitTimeOut = null;
        this.errorTreatment = null;
        this.interruptableAnnouncementIndicator = null;
        this.voiceInformation = null;
        this.voiceBack = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                    case _ID_minimumNumberOfDigits:
                        this.minimumNumberOfDigits = (int) ais.readInteger();
                        break;
                    case _ID_maximumNumberOfDigits:
                        this.maximumNumberOfDigits = (int) ais.readInteger();
                        break;
                    case _ID_endOfReplyDigit:
                        this.endOfReplyDigit = ais.readOctetString();
                        break;
                    case _ID_cancelDigit:
                        this.cancelDigit = ais.readOctetString();
                        break;
                    case _ID_startDigit:
                        this.startDigit = ais.readOctetString();
                        break;
                    case _ID_firstDigitTimeOut:
                        this.firstDigitTimeOut = (int) ais.readInteger();
                        break;
                    case _ID_interDigitTimeOut:
                        this.interDigitTimeOut = (int) ais.readInteger();
                        break;
                    case _ID_errorTreatment:
                        int i1 = (int) ais.readInteger();
                        this.errorTreatment = ErrorTreatment.getInstance(i1);
                        break;
                    case _ID_interruptableAnnouncementIndicator:
                        this.interruptableAnnouncementIndicator = ais.readBoolean();
                        break;
                    case _ID_voiceInformation:
                        this.voiceInformation = ais.readBoolean();
                        break;
                    case _ID_voiceBack:
                        this.voiceBack = ais.readBoolean();
                        break;

                    default:
                        ais.advanceElement();
                        break;
                }
            } else {
                ais.advanceElement();
            }
        }

        if (maximumNumberOfDigits == -1)
            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": maximumNumberOfDigits is mandatory but not found", CAPParsingComponentExceptionReason.MistypedParameter);
    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws CAPException {

        try {
            if (this.minimumNumberOfDigits != null) {
                if (this.minimumNumberOfDigits < 1 || this.minimumNumberOfDigits > 30)
                    throw new CAPException("Error while encoding " + _PrimitiveName
                            + ": minimumNumberOfDigits must have value from 1 to 30");
                asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _ID_minimumNumberOfDigits, this.minimumNumberOfDigits);
            }

            if (this.maximumNumberOfDigits < 1 || this.maximumNumberOfDigits > 30)
                throw new CAPException("Error while encoding " + _PrimitiveName
                        + ": maximumNumberOfDigits must have value from 1 to 30");
            asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _ID_maximumNumberOfDigits, this.maximumNumberOfDigits);

            if (this.endOfReplyDigit != null) {
                if (this.endOfReplyDigit.length < 1 || this.endOfReplyDigit.length > 2)
                    throw new CAPException("Error while encoding " + _PrimitiveName
                            + ": endOfReplyDigit length must be from 1 to 2");
                asnOutputStream.writeOctetString(Tag.CLASS_CONTEXT_SPECIFIC, _ID_endOfReplyDigit, this.endOfReplyDigit);
            }
            if (this.cancelDigit != null) {
                if (this.cancelDigit.length < 1 || this.cancelDigit.length > 2)
                    throw new CAPException("Error while encoding " + _PrimitiveName
                            + ": cancelDigit length must be from 1 to 2");
                asnOutputStream.writeOctetString(Tag.CLASS_CONTEXT_SPECIFIC, _ID_cancelDigit, this.cancelDigit);
            }
            if (this.startDigit != null) {
                if (this.startDigit.length < 1 || this.startDigit.length > 2)
                    throw new CAPException("Error while encoding " + _PrimitiveName + ": startDigit length must be from 1 to 2");
                asnOutputStream.writeOctetString(Tag.CLASS_CONTEXT_SPECIFIC, _ID_startDigit, this.startDigit);
            }
            if (this.firstDigitTimeOut != null) {
                if (this.firstDigitTimeOut < 1 || this.firstDigitTimeOut > 127)
                    throw new CAPException("Error while encoding " + _PrimitiveName
                            + ": firstDigitTimeOut must have value from 1 to 127");
                asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _ID_firstDigitTimeOut, this.firstDigitTimeOut);
            }
            if (this.interDigitTimeOut != null) {
                if (this.interDigitTimeOut < 1 || this.interDigitTimeOut > 127)
                    throw new CAPException("Error while encoding " + _PrimitiveName
                            + ": interDigitTimeOut must have value from 1 to 127");
                asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _ID_interDigitTimeOut, this.interDigitTimeOut);
            }
            if (this.errorTreatment != null)
                asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _ID_errorTreatment, this.errorTreatment.getCode());
            if (this.interruptableAnnouncementIndicator != null)
                asnOutputStream.writeBoolean(Tag.CLASS_CONTEXT_SPECIFIC, _ID_interruptableAnnouncementIndicator, this.interruptableAnnouncementIndicator);
            if (this.voiceInformation != null)
                asnOutputStream.writeBoolean(Tag.CLASS_CONTEXT_SPECIFIC, _ID_voiceInformation, this.voiceInformation);
            if (this.voiceBack != null)
                asnOutputStream.writeBoolean(Tag.CLASS_CONTEXT_SPECIFIC, _ID_voiceBack, this.voiceBack);

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

        if (this.minimumNumberOfDigits != null) {
            sb.append("minimumNumberOfDigits=");
            sb.append(this.minimumNumberOfDigits);
        }
        sb.append(", maximumNumberOfDigits=");
        sb.append(this.maximumNumberOfDigits);
        if (this.endOfReplyDigit != null) {
            sb.append(", endOfReplyDigit=[");
            sb.append(printDataArr(this.endOfReplyDigit));
            sb.append("]");
        }
        if (this.cancelDigit != null) {
            sb.append(", cancelDigit=[");
            sb.append(printDataArr(this.cancelDigit));
            sb.append("]");
        }
        if (this.startDigit != null) {
            sb.append(", startDigit=[");
            sb.append(printDataArr(this.startDigit));
            sb.append("]");
        }
        if (this.firstDigitTimeOut != null) {
            sb.append(", firstDigitTimeOut=");
            sb.append(this.firstDigitTimeOut);
        }
        if (this.interDigitTimeOut != null) {
            sb.append(", interDigitTimeOut=");
            sb.append(this.interDigitTimeOut);
        }
        if (this.errorTreatment != null) {
            sb.append(", errorTreatment=");
            sb.append(this.errorTreatment.toString());
        }
        if (this.interruptableAnnouncementIndicator != null) {
            sb.append(", interruptableAnnouncementIndicator=");
            sb.append(this.interruptableAnnouncementIndicator);
        }
        if (this.voiceInformation != null) {
            sb.append(", voiceInformation=");
            sb.append(this.voiceInformation);
        }
        if (this.voiceBack != null) {
            sb.append(", voiceBack=");
            sb.append(this.voiceBack);
        }

        sb.append("]");

        return sb.toString();
    }

    private String printDataArr(byte[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int b : arr) {
            sb.append(b);
            sb.append(", ");
        }

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<CollectedDigitsImpl> COLLECTED_DIGITS_XML = new XMLFormat<CollectedDigitsImpl>(
            CollectedDigitsImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, CollectedDigitsImpl collectedDigits)
                throws XMLStreamException {
            int vali = xml.getAttribute(MINIMUM_NB_OF_DIGITS, -1);
            if (vali != -1)
                collectedDigits.minimumNumberOfDigits = vali;
            collectedDigits.maximumNumberOfDigits = xml.getAttribute(MAXIMUM_NB_OF_DIGITS, 1);

            vali = xml.getAttribute(FIRST_DIGIT_TIME_OUT, -1);
            if (vali != -1)
                collectedDigits.firstDigitTimeOut = vali;
            vali = xml.getAttribute(INTER_DIGIT_TIME_OUT, -1);
            if (vali != -1)
                collectedDigits.interDigitTimeOut = vali;

            String vals = xml.getAttribute(ERROR_TREATMENT, "");
            if (vals != null && vals.length() > 0)
                collectedDigits.errorTreatment = Enum.valueOf(ErrorTreatment.class, vals);

            vals = xml.getAttribute(INTERRUPTABLE_ANN_IND, "");
            if (vals != null && vals.length() > 0)
                collectedDigits.interruptableAnnouncementIndicator = Boolean.valueOf(vals);
            vals = xml.getAttribute(VOICE_INFORMATION, "");
            if (vals != null && vals.length() > 0)
                collectedDigits.voiceInformation = Boolean.valueOf(vals);
            vals = xml.getAttribute(VOICE_BACK, "");
            if (vals != null && vals.length() > 0)
                collectedDigits.voiceBack = Boolean.valueOf(vals);

            ByteArrayContainer bc = xml.get(END_OF_REPLY_DIGIT, ByteArrayContainer.class);
            if (bc != null) {
                collectedDigits.endOfReplyDigit = bc.getData();
            }
            bc = xml.get(CANCEL_DIGIT, ByteArrayContainer.class);
            if (bc != null) {
                collectedDigits.cancelDigit = bc.getData();
            }
            bc = xml.get(START_DIGIT, ByteArrayContainer.class);
            if (bc != null) {
                collectedDigits.startDigit = bc.getData();
            }
        }

        @Override
        public void write(CollectedDigitsImpl collectedDigits, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {
            if (collectedDigits.minimumNumberOfDigits != null)
                xml.setAttribute(MINIMUM_NB_OF_DIGITS, collectedDigits.minimumNumberOfDigits);
            xml.setAttribute(MAXIMUM_NB_OF_DIGITS, collectedDigits.maximumNumberOfDigits);

            if (collectedDigits.firstDigitTimeOut != null)
                xml.setAttribute(FIRST_DIGIT_TIME_OUT, (int) collectedDigits.firstDigitTimeOut);
            if (collectedDigits.interDigitTimeOut != null)
                xml.setAttribute(INTER_DIGIT_TIME_OUT, (int)collectedDigits.interDigitTimeOut);

            if (collectedDigits.errorTreatment != null)
                xml.setAttribute(ERROR_TREATMENT, collectedDigits.errorTreatment.toString());

            if (collectedDigits.interruptableAnnouncementIndicator != null)
                xml.setAttribute(INTERRUPTABLE_ANN_IND, (boolean)collectedDigits.interruptableAnnouncementIndicator);
            if (collectedDigits.voiceInformation != null)
                xml.setAttribute(VOICE_INFORMATION, (boolean)collectedDigits.voiceInformation);
            if (collectedDigits.voiceBack != null)
                xml.setAttribute(VOICE_BACK, (boolean)collectedDigits.voiceBack);

            if (collectedDigits.endOfReplyDigit != null) {
                ByteArrayContainer bac = new ByteArrayContainer(collectedDigits.endOfReplyDigit);
                xml.add(bac, END_OF_REPLY_DIGIT, ByteArrayContainer.class);
            }
            if (collectedDigits.cancelDigit != null) {
                ByteArrayContainer bac = new ByteArrayContainer(collectedDigits.cancelDigit);
                xml.add(bac, CANCEL_DIGIT, ByteArrayContainer.class);
            }
            if (collectedDigits.startDigit != null) {
                ByteArrayContainer bac = new ByteArrayContainer(collectedDigits.startDigit);
                xml.add(bac, START_DIGIT, ByteArrayContainer.class);
            }
        }
    };
}
