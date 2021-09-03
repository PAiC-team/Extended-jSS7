
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
import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.AudibleIndicator;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.CAMELAChBillingChargingCharacteristics;
import org.restcomm.protocols.ss7.cap.primitives.CAPAsnPrimitive;
import org.restcomm.protocols.ss7.cap.primitives.CAPExtensionsImpl;

/**
 *
 * @author sergey vetyutnev
 * @author Amit Bhayani
 * @author alerant appngin
 *
 */
public class CAMELAChBillingChargingCharacteristicsImpl implements CAMELAChBillingChargingCharacteristics, CAPAsnPrimitive {

    private static final String MAX_CALL_PERIOD_DURATION = "maxCallPeriodDuration";
    private static final String RELEASED_IF_DURATION_EXCEEDED = "releaseIfDurationExceeded";
    private static final String TARIFF_SWITCH_INTERVAL = "tariffSwitchInterval";
    private static final String AUDIBLE_INDICATOR = "audibleIndicator";
    private static final String EXTENSIONS = "extensions";
    private static final String CAP_VERSION = "capVersion";

    public static final int _ID_timeDurationCharging = 0;

    public static final int _ID_maxCallPeriodDuration = 0;
    public static final int _ID_releaseIfDurationExceeded = 1;
    public static final int _ID_tariffSwitchInterval = 2;
    public static final int _ID_audibleIndicator = 3;
    public static final int _ID_extensions = 4;
    public static final int _ID_extensions_In_ReleaseIfDurationExceeded = 10;

    public static final String _PrimitiveName = "CAMELAChBillingChargingCharacteristics";

    private byte[] data;
    private long maxCallPeriodDuration;
    private boolean releaseIfDurationExceeded;
    private Long tariffSwitchInterval;
    private AudibleIndicator audibleIndicator;
    private CAPExtensions extensions;

    private int capVersion;

    public CAMELAChBillingChargingCharacteristicsImpl() {
    }

    public CAMELAChBillingChargingCharacteristicsImpl(byte[] data) {
        this.data = data;
    }

    public CAMELAChBillingChargingCharacteristicsImpl(long maxCallPeriodDuration, boolean releaseIfDurationExceeded,
            Long tariffSwitchInterval, AudibleIndicator audibleIndicator, CAPExtensions extensions, int capVersion) {
        this.maxCallPeriodDuration = maxCallPeriodDuration;
        this.releaseIfDurationExceeded = releaseIfDurationExceeded;
        this.tariffSwitchInterval = tariffSwitchInterval;
        this.audibleIndicator = audibleIndicator;
        this.extensions = extensions;
        this.capVersion = capVersion;
    }

    @Override
    public byte[] getData() {
        return data;
    }

    @Override
    public long getMaxCallPeriodDuration() {
        return maxCallPeriodDuration;
    }

    @Override
    public boolean getReleaseIfDurationExceeded() {
        return releaseIfDurationExceeded;
    }

    @Override
    public Long getTariffSwitchInterval() {
        return tariffSwitchInterval;
    }

    @Override
    public AudibleIndicator getAudibleIndicator() {
        return audibleIndicator;
    }

    @Override
    public CAPExtensions getExtensions() {
        return extensions;
    }

    @Override
    public int getTag() throws CAPException {
        return Tag.STRING_OCTET;
    }

    @Override
    public int getTagClass() {
        return Tag.CLASS_UNIVERSAL;
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
        }
    }

    private void _decode(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException, IOException, AsnException {

        this.data = null;
        this.maxCallPeriodDuration = -1;
        this.releaseIfDurationExceeded = false;
        this.tariffSwitchInterval = null;
        this.audibleIndicator = null;
        this.extensions = null;
        this.capVersion = 0;

        this.data = asnInputStream.readOctetStringData(length);

        AsnInputStream aiss = new AsnInputStream(this.data);
        int tag = aiss.readTag();
        if (tag != _ID_timeDurationCharging || aiss.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC || aiss.isTagPrimitive())
            throw new CAPParsingComponentException("Error when decoding " + _PrimitiveName
                    + ": CAMEL-AChBillingChargingCharacteristics choice has bad tag or tagClass or is primitive, tag=" + tag
                    + ", tagClass=" + aiss.getTagClass(), CAPParsingComponentExceptionReason.MistypedParameter);

        AsnInputStream ais = aiss.readSequenceStream();
        while (true) {
            if (ais.available() == 0)
                break;

            tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                    case _ID_maxCallPeriodDuration:
                        this.maxCallPeriodDuration = ais.readInteger();
                        break;
                    case _ID_releaseIfDurationExceeded:
                        int ln = ais.readLength();
                        if (ln == 1) { // IMPLICIT - IN CAP V3 and later
                            if (this.capVersion < 3) {
                                // at least, could be 4
                                this.capVersion = 3;
                            }
                            this.releaseIfDurationExceeded = ais.readBooleanData(ln);
                        } else { // EXPLICIT - from trace - IN CAP V2
                            this.capVersion = 2;
                            AsnInputStream ais2 = ais.readSequenceStreamData(ln);
                            this.releaseIfDurationExceeded = true;
                            int num = 0;
                            while (true) {
                                if (ais2.available() == 0)
                                    break;

                                int tag2 = ais2.readTag();
                                boolean parsed = false;
                                if (num == 0) {
                                    if (tag2 == Tag.BOOLEAN && ais2.getTagClass() == Tag.CLASS_UNIVERSAL) {
                                        this.audibleIndicator = new AudibleIndicatorImpl(ais2.readBoolean());
                                        parsed = true;
                                    }
                                }
                                if (ais2.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC
                                        && tag2 == _ID_extensions_In_ReleaseIfDurationExceeded) {
                                    this.extensions = new CAPExtensionsImpl();
                                    ((CAPExtensionsImpl) this.extensions).decodeAll(ais2);
                                    parsed = true;
                                }
                                if (!parsed)
                                    ais2.advanceElement();

                                num++;
                            }
                        }
                        break;
                    case _ID_tariffSwitchInterval:
                        this.tariffSwitchInterval = ais.readInteger();
                        break;
                    case _ID_audibleIndicator: // phase3 tone or phase4 AudibleIndicator
                        if (ais.isTagPrimitive()) { // phase 3
                            this.capVersion = 3;
                            this.audibleIndicator = new AudibleIndicatorImpl(ais.readBoolean());
                        } else { // phase 4
                            this.capVersion = 4;
                            AsnInputStream ais2 = ais.readSequenceStream();
                            ais2.readTag();
                            this.audibleIndicator = new AudibleIndicatorImpl();
                            ((AudibleIndicatorImpl) this.audibleIndicator).decodeAll(ais2);
                        }
                        break;
                    case _ID_extensions:
                        if (this.capVersion < 3) {
                            this.capVersion = 3;
                        }
                        this.extensions = new CAPExtensionsImpl();
                        ((CAPExtensionsImpl) this.extensions).decodeAll(ais);
                        break;

                    default:
                        ais.advanceElement();
                        break;
                }
            } else {
                ais.advanceElement();
            }
        }

        if (this.maxCallPeriodDuration == -1)
            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": maxCallPeriodDuration is mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);
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

        if (this.data == null) {
            // encoding internal octet string
            if (this.maxCallPeriodDuration < 1 || this.maxCallPeriodDuration > 864000)
                throw new CAPException("Error while encoding " + _PrimitiveName
                        + ": maxCallPeriodDuration must be from 1 to 864000");
            if (this.tariffSwitchInterval != null && (this.tariffSwitchInterval < 1 || this.tariffSwitchInterval > 86400))
                throw new CAPException("Error while encoding " + _PrimitiveName
                        + ": tariffSwitchInterval must be from 1 to 86400");

            try {
                AsnOutputStream aos = new AsnOutputStream();
                aos.writeTag(Tag.CLASS_CONTEXT_SPECIFIC, false, _ID_timeDurationCharging);
                int pos = aos.StartContentDefiniteLength();

                aos.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _ID_maxCallPeriodDuration, this.maxCallPeriodDuration);

                if (this.capVersion >= 3) {
                    if (this.releaseIfDurationExceeded)
                        aos.writeBoolean(Tag.CLASS_CONTEXT_SPECIFIC, _ID_releaseIfDurationExceeded, true);
                } else {
                    if (this.releaseIfDurationExceeded || this.extensions != null) {
                        aos.writeTag(Tag.CLASS_CONTEXT_SPECIFIC, false, _ID_releaseIfDurationExceeded);
                        int pos2 = aos.StartContentDefiniteLength();
                        if (this.audibleIndicator != null && this.audibleIndicator.getTone() != null) {
                            aos.writeBoolean(this.audibleIndicator.getTone());
                        } else {
                            // if releaseIfDurationExceeded structure is present, always include tone value,
                            // even if it is the default false
                            aos.writeBoolean(false); // no tone
                        }
                        if (this.extensions != null) {
                            ((CAPExtensionsImpl) this.extensions).encodeAll(aos, Tag.CLASS_CONTEXT_SPECIFIC,
                                    _ID_extensions_In_ReleaseIfDurationExceeded);
                        }
                        aos.FinalizeContent(pos2);
                    }
                }

                if (this.tariffSwitchInterval != null)
                    aos.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _ID_tariffSwitchInterval, this.tariffSwitchInterval);

                if (this.audibleIndicator != null) {
                    switch (capVersion) {
                        case 3:
                            aos.writeBoolean(Tag.CLASS_CONTEXT_SPECIFIC, _ID_audibleIndicator, this.audibleIndicator.getTone());
                            break;
                        case 4:
                            aos.writeTag(Tag.CLASS_CONTEXT_SPECIFIC, false, _ID_audibleIndicator);
                            int pos2 = aos.StartContentDefiniteLength();
                            ((AudibleIndicatorImpl) this.audibleIndicator).encodeAll(aos);
                            aos.FinalizeContent(pos2);
                            // ((CAPAsnPrimitive) this.audibleIndicator).encodeAll(aos, Tag.CLASS_CONTEXT_SPECIFIC,
                            // _ID_audibleIndicator);
                            break;
                        default:
                            break;
                    }
                }

                if (this.extensions != null && this.capVersion >= 3)
                    ((CAPExtensionsImpl) this.extensions).encodeAll(aos, Tag.CLASS_CONTEXT_SPECIFIC, _ID_extensions);

                aos.FinalizeContent(pos);
                this.data = aos.toByteArray();
            } catch (IOException e) {
                throw new CAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
            } catch (AsnException e) {
                throw new CAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
            }
        }

        asnOutputStream.writeOctetStringData(data);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [timeDurationCharging [");

        sb.append("capVersion=");
        sb.append(this.capVersion);
        sb.append(", maxCallPeriodDuration=");
        sb.append(this.maxCallPeriodDuration);
        if (this.releaseIfDurationExceeded) {
            sb.append(", releaseIfDurationExceeded");
        }
        if (this.tariffSwitchInterval != null) {
            sb.append(", tariffSwitchInterval=");
            sb.append(tariffSwitchInterval);
        }
        if (this.audibleIndicator != null) {
            sb.append(", audibleIndicator=");
            sb.append(audibleIndicator.toString());
        }
        if (this.extensions != null) {
            sb.append(", extensions=");
            sb.append(extensions.toString());
        }

        sb.append("]]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<CAMELAChBillingChargingCharacteristicsImpl> CAMEL_ACH_BILLING_CHARGING_CHARACTERISTIC_XML = new XMLFormat<CAMELAChBillingChargingCharacteristicsImpl>(
            CAMELAChBillingChargingCharacteristicsImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml,
                CAMELAChBillingChargingCharacteristicsImpl camelAChBillingChargingCharacteristics) throws XMLStreamException {
            camelAChBillingChargingCharacteristics.capVersion = xml.getAttribute(CAP_VERSION, 2);

            camelAChBillingChargingCharacteristics.maxCallPeriodDuration = xml.get(MAX_CALL_PERIOD_DURATION, Long.class);
            camelAChBillingChargingCharacteristics.releaseIfDurationExceeded = xml.get(RELEASED_IF_DURATION_EXCEEDED,
                    Boolean.class);
            camelAChBillingChargingCharacteristics.tariffSwitchInterval = xml.get(TARIFF_SWITCH_INTERVAL, Long.class);
            camelAChBillingChargingCharacteristics.extensions = xml.get(EXTENSIONS, CAPExtensionsImpl.class);
            camelAChBillingChargingCharacteristics.audibleIndicator = xml.get(AUDIBLE_INDICATOR, AudibleIndicatorImpl.class);

        }

        @Override
        public void write(CAMELAChBillingChargingCharacteristicsImpl camelAChBillingChargingCharacteristics,
                javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            xml.setAttribute(CAP_VERSION, camelAChBillingChargingCharacteristics.capVersion);

            xml.add(camelAChBillingChargingCharacteristics.maxCallPeriodDuration, MAX_CALL_PERIOD_DURATION, Long.class);
            xml.add(camelAChBillingChargingCharacteristics.releaseIfDurationExceeded, RELEASED_IF_DURATION_EXCEEDED,
                    Boolean.class);

            if (camelAChBillingChargingCharacteristics.tariffSwitchInterval != null) {
                xml.add(camelAChBillingChargingCharacteristics.tariffSwitchInterval, TARIFF_SWITCH_INTERVAL, Long.class);
            }
            if (camelAChBillingChargingCharacteristics.extensions != null) {
                xml.add((CAPExtensionsImpl) camelAChBillingChargingCharacteristics.extensions, EXTENSIONS,
                        CAPExtensionsImpl.class);
            }

            if (camelAChBillingChargingCharacteristics.audibleIndicator != null) {
                xml.add(((AudibleIndicatorImpl) camelAChBillingChargingCharacteristics.audibleIndicator), AUDIBLE_INDICATOR, AudibleIndicatorImpl.class);
            }
        }
    };
}
