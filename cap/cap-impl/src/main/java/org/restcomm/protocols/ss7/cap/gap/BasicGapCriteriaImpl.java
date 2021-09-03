
package org.restcomm.protocols.ss7.cap.gap;

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
import org.restcomm.protocols.ss7.cap.api.gap.BasicGapCriteria;
import org.restcomm.protocols.ss7.cap.api.gap.CalledAddressAndService;
import org.restcomm.protocols.ss7.cap.api.gap.CallingAddressAndService;
import org.restcomm.protocols.ss7.cap.api.gap.GapOnService;
import org.restcomm.protocols.ss7.cap.api.isup.Digits;
import org.restcomm.protocols.ss7.cap.isup.DigitsImpl;
import org.restcomm.protocols.ss7.cap.primitives.CAPAsnPrimitive;

/**
 *
 * @author <a href="mailto:bartosz.krok@pro-ids.com"> Bartosz Krok (ProIDS sp. z o.o.)</a>
 */
public class BasicGapCriteriaImpl implements BasicGapCriteria, CAPAsnPrimitive {

    public static final int _ID_calledAddressValue = 0;
    public static final int _ID_gapOnService = 2;
    public static final int _ID_calledAddressAndService = 29;
    public static final int _ID_callingAddressAndService = 30;

    private static final String CALLED_ADDRESS_VALUE = "calledAddressValue";
    private static final String GAP_ON_SERVICE = "gapOnService";
    private static final String CALLED_ADDRESS_AND_SERVICE = "calledAddressAndService";
    private static final String CALLING_ADDRESS_AND_SERVICE = "callingAddressAndService";

    public static final String _PrimitiveName = "BasicGapCriteria";

    private Digits calledAddressValue;
    private GapOnService gapOnService;
    private CalledAddressAndService calledAddressAndService;
    private CallingAddressAndService callingAddressAndService;

    public BasicGapCriteriaImpl() {
    }

    public BasicGapCriteriaImpl(Digits calledAddressValue) {
        this.calledAddressValue = calledAddressValue;
    }

    public BasicGapCriteriaImpl(GapOnService gapOnService) {
        this.gapOnService = gapOnService;
    }

    public BasicGapCriteriaImpl(CalledAddressAndService calledAddressAndService) {
        this.calledAddressAndService = calledAddressAndService;
    }

    public BasicGapCriteriaImpl(CallingAddressAndService callingAddressAndService) {
        this.callingAddressAndService = callingAddressAndService;
    }

    public Digits getCalledAddressValue() {
        return calledAddressValue;
    }

    public GapOnService getGapOnService() {
        return gapOnService;
    }

    public CalledAddressAndService getCalledAddressAndService() {
        return calledAddressAndService;
    }

    public CallingAddressAndService getCallingAddressAndService() {
        return callingAddressAndService;
    }

    public int getTag() throws CAPException {
        if (calledAddressValue != null) {
            return _ID_calledAddressValue;
        } else if (gapOnService != null) {
            return _ID_gapOnService;
        } else if (calledAddressAndService != null) {
            return _ID_calledAddressAndService;
        } else if (callingAddressAndService != null) {
            return _ID_callingAddressAndService;
        }

        throw new CAPException("Error while encoding " + _PrimitiveName + ": no choice is specified");
    }

    public int getTagClass() {
        return Tag.CLASS_CONTEXT_SPECIFIC;
    }

    public boolean getIsPrimitive() {
        if (calledAddressValue != null)
            return true;
        else
            return false;
    }

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

        this.calledAddressValue = null;
        this.gapOnService = null;
        this.calledAddressAndService = null;
        this.callingAddressAndService = null;

        if (asnInputStream.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {

            switch (asnInputStream.getTag()) {
                case _ID_calledAddressValue: {
                    this.calledAddressValue = new DigitsImpl();
                    ((DigitsImpl) this.calledAddressValue).decodeData(asnInputStream, length);
                    this.calledAddressValue.setIsGenericNumber();
                    break;
                }
                case _ID_gapOnService: {
                    this.gapOnService = new GapOnServiceImpl();
                    ((GapOnServiceImpl) this.gapOnService).decodeData(asnInputStream, length);
                    break;
                }
                case _ID_calledAddressAndService: {
                    this.calledAddressAndService = new CalledAddressAndServiceImpl();
                    ((CalledAddressAndServiceImpl) this.calledAddressAndService).decodeData(asnInputStream, length);
                    break;
                }
                case _ID_callingAddressAndService: {
                    this.callingAddressAndService = new CallingAddressAndServiceImpl();
                    ((CallingAddressAndServiceImpl) this.callingAddressAndService).decodeData(asnInputStream, length);
                    break;
                }
                default: {
                    throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName + ": bad choice tag",
                            CAPParsingComponentExceptionReason.MistypedParameter);
                }
            }
        } else {
            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName + ": bad choice tagClass",
                    CAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    public void encodeAll(AsnOutputStream asnOutputStream) throws CAPException {
        this.encodeAll(asnOutputStream, this.getTagClass(), this.getTag());
    }

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

    public void encodeData(AsnOutputStream asnOutputStream) throws CAPException {

        int cnt = 0;
        if (this.calledAddressValue != null)
            cnt++;
        if (this.gapOnService != null)
            cnt++;
        if (this.calledAddressAndService != null)
            cnt++;
        if (this.callingAddressAndService != null)
            cnt++;
        if (cnt != 1) {
            throw new CAPException("Error while encoding " + _PrimitiveName + ": One and only one choice must be selected");
        }

        try {
            if (calledAddressValue != null) {
                ((DigitsImpl) calledAddressValue).encodeData(asnOutputStream);

            } else if (gapOnService != null) {
                ((GapOnServiceImpl) gapOnService).encodeData(asnOutputStream);

            } else if (calledAddressAndService != null) {
                ((CalledAddressAndServiceImpl) calledAddressAndService).encodeData(asnOutputStream);

            } else if (callingAddressAndService != null) {
                ((CallingAddressAndServiceImpl) callingAddressAndService).encodeData(asnOutputStream);
            }
        } catch (CAPException e) {
            throw new CAPException("CAPException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    protected static final XMLFormat<BasicGapCriteriaImpl> BASIC_GAP_CRITERIA_XML = new XMLFormat<BasicGapCriteriaImpl>(BasicGapCriteriaImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, BasicGapCriteriaImpl basicGapCriteria) throws XMLStreamException {
            basicGapCriteria.calledAddressValue = xml.get(CALLED_ADDRESS_VALUE, DigitsImpl.class);
            basicGapCriteria.gapOnService = xml.get(GAP_ON_SERVICE, GapOnServiceImpl.class);
            basicGapCriteria.calledAddressAndService = xml.get(CALLED_ADDRESS_AND_SERVICE, CalledAddressAndServiceImpl.class);
            basicGapCriteria.callingAddressAndService = xml.get(CALLING_ADDRESS_AND_SERVICE, CallingAddressAndServiceImpl.class);
        }

        @Override
        public void write(BasicGapCriteriaImpl basicGapCriteria, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            if (basicGapCriteria.calledAddressValue != null) {
                xml.add((DigitsImpl) basicGapCriteria.calledAddressValue, CALLED_ADDRESS_VALUE, DigitsImpl.class);
            }
            if (basicGapCriteria.gapOnService != null) {
                xml.add((GapOnServiceImpl) basicGapCriteria.gapOnService, GAP_ON_SERVICE, GapOnServiceImpl.class);
            }
            if (basicGapCriteria.calledAddressAndService != null) {
                xml.add((CalledAddressAndServiceImpl) basicGapCriteria.calledAddressAndService, CALLED_ADDRESS_AND_SERVICE, CalledAddressAndServiceImpl.class);
            }
            if (basicGapCriteria.callingAddressAndService != null) {
                xml.add((CallingAddressAndServiceImpl) basicGapCriteria.callingAddressAndService, CALLING_ADDRESS_AND_SERVICE, CallingAddressAndServiceImpl.class);
            }
        }
    };

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (calledAddressValue != null) {
            sb.append("calledAddressValue=");
            sb.append(calledAddressValue);
        } else if (gapOnService != null) {
            sb.append("gapOnService=");
            sb.append(gapOnService);
        } else if (calledAddressAndService != null) {
            sb.append("calledAddressAndService=");
            sb.append(calledAddressAndService);
        } else if (callingAddressAndService != null) {
            sb.append("callingAddressAndService=");
            sb.append(callingAddressAndService);
        }

        sb.append("]");

        return sb.toString();
    }

}
