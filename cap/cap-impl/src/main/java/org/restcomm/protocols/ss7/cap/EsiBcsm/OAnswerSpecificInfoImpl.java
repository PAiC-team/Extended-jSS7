
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
import org.restcomm.protocols.ss7.cap.api.EsiBcsm.ChargeIndicator;
import org.restcomm.protocols.ss7.cap.api.EsiBcsm.OAnswerSpecificInfo;
import org.restcomm.protocols.ss7.cap.api.isup.CalledPartyNumberCap;
import org.restcomm.protocols.ss7.cap.isup.CalledPartyNumberCapImpl;
import org.restcomm.protocols.ss7.cap.primitives.SequenceBase;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtBasicServiceCode;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.ExtBasicServiceCodeImpl;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class OAnswerSpecificInfoImpl extends SequenceBase implements OAnswerSpecificInfo {

    private static final String DESTINATION_ADDRESS = "destinationAddress";
    private static final String OR_CALL = "orCall";
    private static final String FORWARDED_CALL = "forwardedCall";
    private static final String CHARGE_INDICATOR = "chargeIndicator";
    private static final String EXT_BASIC_SERVICE_CODE = "extBasicServiceCode";
    private static final String EXT_BASIC_SERVICE_CODE2 = "extBasicServiceCode2";

    public static final int _ID_destinationAddress = 50;
    public static final int _ID_orCall = 51;
    public static final int _ID_forwardedCall = 52;
    public static final int _ID_chargeIndicator = 53;
    public static final int _ID_extBasicServiceCode = 54;
    public static final int _ID_extBasicServiceCode2 = 55;

    private CalledPartyNumberCap destinationAddress;
    private boolean orCall;
    private boolean forwardedCall;
    private ChargeIndicator chargeIndicator;
    private ExtBasicServiceCode extBasicServiceCode;
    private ExtBasicServiceCode extBasicServiceCode2;

    public OAnswerSpecificInfoImpl() {
        super("OAnswerSpecificInfo");
    }

    public OAnswerSpecificInfoImpl(CalledPartyNumberCap destinationAddress, boolean orCall, boolean forwardedCall,
            ChargeIndicator chargeIndicator, ExtBasicServiceCode extBasicServiceCode, ExtBasicServiceCode extBasicServiceCode2) {
        super("OAnswerSpecificInfo");
        this.destinationAddress = destinationAddress;
        this.orCall = orCall;
        this.forwardedCall = forwardedCall;
        this.chargeIndicator = chargeIndicator;
        this.extBasicServiceCode = extBasicServiceCode;
        this.extBasicServiceCode2 = extBasicServiceCode2;
    }

    @Override
    public CalledPartyNumberCap getDestinationAddress() {
        return destinationAddress;
    }

    @Override
    public boolean getOrCall() {
        return orCall;
    }

    @Override
    public boolean getForwardedCall() {
        return forwardedCall;
    }

    @Override
    public ChargeIndicator getChargeIndicator() {
        return chargeIndicator;
    }

    @Override
    public ExtBasicServiceCode getExtBasicServiceCode() {
        return extBasicServiceCode;
    }

    @Override
    public ExtBasicServiceCode getExtBasicServiceCode2() {
        return extBasicServiceCode2;
    }

    protected void _decode(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException, MAPParsingComponentException,
            IOException, AsnException {

        this.destinationAddress = null;
        this.orCall = false;
        this.forwardedCall = false;
        this.chargeIndicator = null;
        this.extBasicServiceCode = null;
        this.extBasicServiceCode2 = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                    case _ID_destinationAddress:
                        this.destinationAddress = new CalledPartyNumberCapImpl();
                        ((CalledPartyNumberCapImpl) this.destinationAddress).decodeAll(ais);
                        break;
                    case _ID_orCall:
                        ais.readNull();
                        this.orCall = true;
                        break;
                    case _ID_forwardedCall:
                        ais.readNull();
                        this.forwardedCall = true;
                        break;
                    case _ID_chargeIndicator:
                        this.chargeIndicator = new ChargeIndicatorImpl();
                        ((ChargeIndicatorImpl) this.chargeIndicator).decodeAll(ais);
                        break;
                    case _ID_extBasicServiceCode:
                        AsnInputStream ais2 = ais.readSequenceStream();
                        ais2.readTag();
                        this.extBasicServiceCode = new ExtBasicServiceCodeImpl();
                        ((ExtBasicServiceCodeImpl) this.extBasicServiceCode).decodeAll(ais2);
                        break;
                    case _ID_extBasicServiceCode2:
                        ais2 = ais.readSequenceStream();
                        ais2.readTag();
                        this.extBasicServiceCode2 = new ExtBasicServiceCodeImpl();
                        ((ExtBasicServiceCodeImpl) this.extBasicServiceCode2).decodeAll(ais2);
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
            if (this.destinationAddress != null) {
                ((CalledPartyNumberCapImpl) this.destinationAddress).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_destinationAddress);
            }
            if (this.orCall) {
                asnOutputStream.writeNull(Tag.CLASS_CONTEXT_SPECIFIC, _ID_orCall);
            }
            if (this.forwardedCall) {
                asnOutputStream.writeNull(Tag.CLASS_CONTEXT_SPECIFIC, _ID_forwardedCall);
            }
            if (this.chargeIndicator != null) {
                ((ChargeIndicatorImpl) this.chargeIndicator).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_chargeIndicator);
            }
            if (this.extBasicServiceCode != null) {
                asnOutputStream.writeTag(Tag.CLASS_CONTEXT_SPECIFIC, false, _ID_extBasicServiceCode);
                int pos = asnOutputStream.StartContentDefiniteLength();
                ((ExtBasicServiceCodeImpl) this.extBasicServiceCode).encodeAll(asnOutputStream);
                asnOutputStream.FinalizeContent(pos);
            }
            if (this.extBasicServiceCode2 != null) {
                asnOutputStream.writeTag(Tag.CLASS_CONTEXT_SPECIFIC, false, _ID_extBasicServiceCode2);
                int pos = asnOutputStream.StartContentDefiniteLength();
                ((ExtBasicServiceCodeImpl) this.extBasicServiceCode2).encodeAll(asnOutputStream);
                asnOutputStream.FinalizeContent(pos);
            }
        } catch (IOException e) {
            throw new CAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
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

        if (this.destinationAddress != null) {
            sb.append("destinationAddress= [");
            sb.append(destinationAddress.toString());
            sb.append("]");
        }
        if (this.orCall) {
            sb.append(", orCall");
        }
        if (this.forwardedCall) {
            sb.append(", forwardedCall");
        }
        if (this.chargeIndicator != null) {
            sb.append(", chargeIndicator= [");
            sb.append(chargeIndicator.toString());
            sb.append("]");
        }
        if (this.extBasicServiceCode != null) {
            sb.append(", extBasicServiceCode= [");
            sb.append(extBasicServiceCode.toString());
            sb.append("]");
        }
        if (this.extBasicServiceCode2 != null) {
            sb.append(", extBasicServiceCode2= [");
            sb.append(extBasicServiceCode2.toString());
            sb.append("]");
        }

        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<OAnswerSpecificInfoImpl> O_ANSWER_SPECIFIC_INFO_XML = new XMLFormat<OAnswerSpecificInfoImpl>(
            OAnswerSpecificInfoImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, OAnswerSpecificInfoImpl oAnswerSpecificInfo)
                throws XMLStreamException {
            oAnswerSpecificInfo.destinationAddress = xml.get(DESTINATION_ADDRESS, CalledPartyNumberCapImpl.class);

            Boolean bval = xml.get(OR_CALL, Boolean.class);
            if (bval != null)
                oAnswerSpecificInfo.orCall = bval;
            bval = xml.get(FORWARDED_CALL, Boolean.class);
            if (bval != null)
                oAnswerSpecificInfo.forwardedCall = bval;

            oAnswerSpecificInfo.chargeIndicator = xml.get(CHARGE_INDICATOR, ChargeIndicatorImpl.class);

            oAnswerSpecificInfo.extBasicServiceCode = xml.get(EXT_BASIC_SERVICE_CODE, ExtBasicServiceCodeImpl.class);
            oAnswerSpecificInfo.extBasicServiceCode2 = xml.get(EXT_BASIC_SERVICE_CODE2, ExtBasicServiceCodeImpl.class);
        }

        @Override
        public void write(OAnswerSpecificInfoImpl oAnswerSpecificInfo, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {

            if (oAnswerSpecificInfo.destinationAddress != null) {
                xml.add(((CalledPartyNumberCapImpl) oAnswerSpecificInfo.destinationAddress), DESTINATION_ADDRESS,
                        CalledPartyNumberCapImpl.class);
            }

            if (oAnswerSpecificInfo.orCall)
                xml.add(oAnswerSpecificInfo.orCall, OR_CALL, Boolean.class);
            if (oAnswerSpecificInfo.forwardedCall)
                xml.add(oAnswerSpecificInfo.forwardedCall, FORWARDED_CALL, Boolean.class);

            if (oAnswerSpecificInfo.chargeIndicator != null) {
                xml.add(((ChargeIndicatorImpl) oAnswerSpecificInfo.chargeIndicator), CHARGE_INDICATOR, ChargeIndicatorImpl.class);
            }

            if (oAnswerSpecificInfo.extBasicServiceCode != null) {
                xml.add(((ExtBasicServiceCodeImpl) oAnswerSpecificInfo.extBasicServiceCode), EXT_BASIC_SERVICE_CODE,
                        ExtBasicServiceCodeImpl.class);
            }

            if (oAnswerSpecificInfo.extBasicServiceCode2 != null) {
                xml.add(((ExtBasicServiceCodeImpl) oAnswerSpecificInfo.extBasicServiceCode2), EXT_BASIC_SERVICE_CODE2,
                        ExtBasicServiceCodeImpl.class);
            }
        }
    };
}
