
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
import org.restcomm.protocols.ss7.cap.api.EsiBcsm.MetDPCriterion;
import org.restcomm.protocols.ss7.cap.api.EsiBcsm.MetDPCriterionAlt;
import org.restcomm.protocols.ss7.cap.primitives.CAPAsnPrimitive;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.primitives.CellGlobalIdOrServiceAreaIdFixedLength;
import org.restcomm.protocols.ss7.map.api.primitives.LAIFixedLength;
import org.restcomm.protocols.ss7.map.primitives.CellGlobalIdOrServiceAreaIdFixedLengthImpl;
import org.restcomm.protocols.ss7.map.primitives.LAIFixedLengthImpl;

/**
*
* @author sergey vetyutnev
*
*/
public class MetDPCriterionImpl implements MetDPCriterion, CAPAsnPrimitive {

    private static final String ENTERING_CELL_GLOBAL_ID = "enteringCellGlobalId";
    private static final String LEAVING_CELL_GLOBAL_ID = "leavingCellGlobalId";
    private static final String ENTERING_SERVICE_AREA_ID = "enteringServiceAreaId";
    private static final String LEAVING_SERVICE_AREA_ID = "leavingServiceAreaId";
    private static final String ENTERING_LOCATION_AREA_ID = "enteringLocationAreaId";
    private static final String LEAVING_LOCATION_AREA_ID = "leavingLocationAreaId";
    private static final String INTER_SYSTEM_HAND_OVER_TO_UMTS = "interSystemHandOverToUMTS";
    private static final String INTER_SYSTEM_HAND_OVER_TO_GSM = "interSystemHandOverToGSM";
    private static final String INTER_PLMN_HAND_OVER = "interPLMNHandOver";
    private static final String INTER_MSC_HAND_OVER = "interMSCHandOver";
    private static final String MET_DP_CRITERION_ALT = "metDPCriterionAlt";

    public static final int _ID_enteringCellGlobalId = 0;
    public static final int _ID_leavingCellGlobalId = 1;
    public static final int _ID_enteringServiceAreaId = 2;
    public static final int _ID_leavingServiceAreaId = 3;
    public static final int _ID_enteringLocationAreaId = 4;
    public static final int _ID_leavingLocationAreaId = 5;
    public static final int _ID_interSystemHandOverToUMTS = 6;
    public static final int _ID_interSystemHandOverToGSM = 7;
    public static final int _ID_interPLMNHandOver = 8;
    public static final int _ID_interMSCHandOver = 9;
    public static final int _ID_metDPCriterionAlt = 10;

    public static final String _PrimitiveName = "MetDPCriterion";

    private CellGlobalIdOrServiceAreaIdFixedLength enteringCellGlobalId;
    private CellGlobalIdOrServiceAreaIdFixedLength leavingCellGlobalId;
    private CellGlobalIdOrServiceAreaIdFixedLength enteringServiceAreaId;
    private CellGlobalIdOrServiceAreaIdFixedLength leavingServiceAreaId;
    private LAIFixedLength enteringLocationAreaId;
    private LAIFixedLength leavingLocationAreaId;
    private boolean interSystemHandOverToUMTS;
    private boolean interSystemHandOverToGSM;
    private boolean interPLMNHandOver;
    private boolean interMSCHandOver;
    private MetDPCriterionAlt metDPCriterionAlt;

    public MetDPCriterionImpl() {
    }

    public MetDPCriterionImpl(CellGlobalIdOrServiceAreaIdFixedLength cgiFixedLength, CellGlobalIdOrServiceAreaIdFixedLength_Option cgiFixedLengthOption) {
        switch (cgiFixedLengthOption) {
        case enteringCellGlobalId:
            this.enteringCellGlobalId = cgiFixedLength;
            break;
        case leavingCellGlobalId:
            this.leavingCellGlobalId = cgiFixedLength;
            break;
        case enteringServiceAreaId:
            this.enteringServiceAreaId = cgiFixedLength;
            break;
        case leavingServiceAreaId:
            this.leavingServiceAreaId = cgiFixedLength;
            break;
        }
    }

    public MetDPCriterionImpl(LAIFixedLength laiFixedLength, LAIFixedLength_Option laiFixedLengthOption) {
        switch (laiFixedLengthOption) {
        case enteringLocationAreaId:
            this.enteringLocationAreaId = laiFixedLength;
            break;
        case leavingLocationAreaId:
            this.leavingLocationAreaId = laiFixedLength;
            break;
        }
    }

    public MetDPCriterionImpl(Boolean_Option booleanOption) {
        switch (booleanOption) {
        case interSystemHandOverToUMTS:
            this.interSystemHandOverToUMTS = true;
            break;
        case interSystemHandOverToGSM:
            this.interSystemHandOverToGSM = true;
            break;
        case interPLMNHandOver:
            this.interPLMNHandOver = true;
            break;
        case interMSCHandOver:
            this.interMSCHandOver = true;
            break;
        }
    }

    public MetDPCriterionImpl(MetDPCriterionAlt metDPCriterionAlt) {
        this.metDPCriterionAlt = metDPCriterionAlt;
    }


    @Override
    public int getTag() throws CAPException {
        if (enteringCellGlobalId != null) {
            return _ID_enteringCellGlobalId;
        } else if (leavingCellGlobalId != null) {
            return _ID_leavingCellGlobalId;
        } else if (enteringServiceAreaId != null) {
            return _ID_enteringServiceAreaId;
        } else if (leavingServiceAreaId != null) {
            return _ID_leavingServiceAreaId;
        } else if (enteringLocationAreaId != null) {
            return _ID_enteringLocationAreaId;
        } else if (leavingLocationAreaId != null) {
            return _ID_leavingLocationAreaId;
        } else if (interSystemHandOverToUMTS) {
            return _ID_interSystemHandOverToUMTS;
        } else if (interSystemHandOverToGSM) {
            return _ID_interSystemHandOverToGSM;
        } else if (interPLMNHandOver) {
            return _ID_interPLMNHandOver;
        } else if (interMSCHandOver) {
            return _ID_interMSCHandOver;
        } else if (metDPCriterionAlt != null) {
            return _ID_metDPCriterionAlt;
        }

        throw new CAPException("Error while encoding " + _PrimitiveName + ": no choice is specified");
    }

    @Override
    public int getTagClass() {
        return Tag.CLASS_CONTEXT_SPECIFIC;
    }

    @Override
    public boolean getIsPrimitive() {
        if (metDPCriterionAlt != null)
            return false;
        else
            return true;
    }

    @Override
    public CellGlobalIdOrServiceAreaIdFixedLength getEnteringCellGlobalId() {
        return enteringCellGlobalId;
    }

    @Override
    public CellGlobalIdOrServiceAreaIdFixedLength getLeavingCellGlobalId() {
        return leavingCellGlobalId;
    }

    @Override
    public CellGlobalIdOrServiceAreaIdFixedLength getEnteringServiceAreaId() {
        return enteringServiceAreaId;
    }

    @Override
    public CellGlobalIdOrServiceAreaIdFixedLength getLeavingServiceAreaId() {
        return leavingServiceAreaId;
    }

    @Override
    public LAIFixedLength getEnteringLocationAreaId() {
        return enteringLocationAreaId;
    }

    @Override
    public LAIFixedLength getLeavingLocationAreaId() {
        return leavingLocationAreaId;
    }

    @Override
    public boolean getInterSystemHandOverToUMTS() {
        return interSystemHandOverToUMTS;
    }

    @Override
    public boolean getInterSystemHandOverToGSM() {
        return interSystemHandOverToGSM;
    }

    @Override
    public boolean getInterPLMNHandOver() {
        return interPLMNHandOver;
    }

    @Override
    public boolean getInterMSCHandOver() {
        return interMSCHandOver;
    }

    @Override
    public MetDPCriterionAlt getMetDPCriterionAlt() {
        return metDPCriterionAlt;
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

        this.enteringCellGlobalId = null;
        this.leavingCellGlobalId = null;
        this.enteringServiceAreaId = null;
        this.leavingServiceAreaId = null;
        this.enteringLocationAreaId = null;
        this.leavingLocationAreaId = null;
        this.interSystemHandOverToUMTS = false;
        this.interSystemHandOverToGSM = false;
        this.interPLMNHandOver = false;
        this.interMSCHandOver = false;
        this.metDPCriterionAlt = null;

        int tag = asnInputStream.getTag();

        if (asnInputStream.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
            switch (tag) {
            case _ID_enteringCellGlobalId:
                this.enteringCellGlobalId = new CellGlobalIdOrServiceAreaIdFixedLengthImpl();
                ((CellGlobalIdOrServiceAreaIdFixedLengthImpl) this.enteringCellGlobalId).decodeData(asnInputStream, length);
                break;
            case _ID_leavingCellGlobalId:
                this.leavingCellGlobalId = new CellGlobalIdOrServiceAreaIdFixedLengthImpl();
                ((CellGlobalIdOrServiceAreaIdFixedLengthImpl) this.leavingCellGlobalId).decodeData(asnInputStream, length);
                break;
            case _ID_enteringServiceAreaId:
                this.enteringServiceAreaId = new CellGlobalIdOrServiceAreaIdFixedLengthImpl();
                ((CellGlobalIdOrServiceAreaIdFixedLengthImpl) this.enteringServiceAreaId).decodeData(asnInputStream, length);
                break;
            case _ID_leavingServiceAreaId:
                this.leavingServiceAreaId = new CellGlobalIdOrServiceAreaIdFixedLengthImpl();
                ((CellGlobalIdOrServiceAreaIdFixedLengthImpl) this.leavingServiceAreaId).decodeData(asnInputStream, length);
                break;

            case _ID_enteringLocationAreaId:
                this.enteringLocationAreaId = new LAIFixedLengthImpl();
                ((LAIFixedLengthImpl) this.enteringLocationAreaId).decodeData(asnInputStream, length);
                break;
            case _ID_leavingLocationAreaId:
                this.leavingLocationAreaId = new LAIFixedLengthImpl();
                ((LAIFixedLengthImpl) this.leavingLocationAreaId).decodeData(asnInputStream, length);
                break;

            case _ID_interSystemHandOverToUMTS:
                asnInputStream.readNullData(length);
                this.interSystemHandOverToUMTS = true;
                break;
            case _ID_interSystemHandOverToGSM:
                asnInputStream.readNullData(length);
                this.interSystemHandOverToGSM = true;
                break;
            case _ID_interPLMNHandOver:
                asnInputStream.readNullData(length);
                this.interPLMNHandOver = true;
                break;
            case _ID_interMSCHandOver:
                asnInputStream.readNullData(length);
                this.interMSCHandOver = true;
                break;

            case _ID_metDPCriterionAlt:
                this.metDPCriterionAlt = new MetDPCriterionAltImpl();
                ((MetDPCriterionAltImpl) this.metDPCriterionAlt).decodeData(asnInputStream, length);
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
            if (enteringCellGlobalId != null) {
                ((CellGlobalIdOrServiceAreaIdFixedLengthImpl) enteringCellGlobalId).encodeData(asnOutputStream);
                return;
            } else if (leavingCellGlobalId != null) {
                ((CellGlobalIdOrServiceAreaIdFixedLengthImpl) leavingCellGlobalId).encodeData(asnOutputStream);
                return;
            } else if (enteringServiceAreaId != null) {
                ((CellGlobalIdOrServiceAreaIdFixedLengthImpl) enteringServiceAreaId).encodeData(asnOutputStream);
                return;
            } else if (leavingServiceAreaId != null) {
                ((CellGlobalIdOrServiceAreaIdFixedLengthImpl) leavingServiceAreaId).encodeData(asnOutputStream);
                return;

            } else if (enteringLocationAreaId != null) {
                ((LAIFixedLengthImpl) enteringLocationAreaId).encodeData(asnOutputStream);
                return;
            } else if (leavingLocationAreaId != null) {
                ((LAIFixedLengthImpl) leavingLocationAreaId).encodeData(asnOutputStream);
                return;

            } else if (interSystemHandOverToUMTS) {
                asnOutputStream.writeNullData();
                return;
            } else if (interSystemHandOverToGSM) {
                asnOutputStream.writeNullData();
                return;
            } else if (interPLMNHandOver) {
                asnOutputStream.writeNullData();
                return;
            } else if (interMSCHandOver) {
                asnOutputStream.writeNullData();
                return;

            } else if (metDPCriterionAlt != null) {
                ((MetDPCriterionAltImpl) metDPCriterionAlt).encodeData(asnOutputStream);
                return;
            }
        } catch (MAPException e) {
            throw new CAPException("MAPException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }

        throw new CAPException("Error while encoding " + _PrimitiveName + ": no choice is specified");
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (enteringCellGlobalId != null) {
            sb.append("enteringCellGlobalId=");
            sb.append(enteringCellGlobalId.toString());
        } else if (leavingCellGlobalId != null) {
            sb.append("leavingCellGlobalId=");
            sb.append(leavingCellGlobalId.toString());
        } else if (enteringServiceAreaId != null) {
            sb.append("enteringServiceAreaId=");
            sb.append(enteringServiceAreaId.toString());
        } else if (leavingServiceAreaId != null) {
            sb.append("leavingServiceAreaId=");
            sb.append(leavingServiceAreaId.toString());
        } else if (enteringLocationAreaId != null) {
            sb.append("enteringLocationAreaId=");
            sb.append(enteringLocationAreaId.toString());
        } else if (leavingLocationAreaId != null) {
            sb.append("leavingLocationAreaId=");
            sb.append(leavingLocationAreaId.toString());
        } else if (interSystemHandOverToUMTS) {
            sb.append("interSystemHandOverToUMTS");
        } else if (interSystemHandOverToGSM) {
            sb.append("interSystemHandOverToGSM");
        } else if (interPLMNHandOver) {
            sb.append("interPLMNHandOver");
        } else if (interMSCHandOver) {
            sb.append("interMSCHandOver");
        } else if (metDPCriterionAlt != null) {
            sb.append("metDPCriterionAlt=");
            sb.append(metDPCriterionAlt.toString());
        }

        sb.append("]");

        return sb.toString();
    }

    protected static final XMLFormat<MetDPCriterionImpl> MET_DP_CRITERION_XML = new XMLFormat<MetDPCriterionImpl>(MetDPCriterionImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, MetDPCriterionImpl metDPCriterion) throws XMLStreamException {
            metDPCriterion.enteringCellGlobalId = xml.get(ENTERING_CELL_GLOBAL_ID, CellGlobalIdOrServiceAreaIdFixedLengthImpl.class);
            metDPCriterion.leavingCellGlobalId = xml.get(LEAVING_CELL_GLOBAL_ID, CellGlobalIdOrServiceAreaIdFixedLengthImpl.class);
            metDPCriterion.enteringServiceAreaId = xml.get(ENTERING_SERVICE_AREA_ID, CellGlobalIdOrServiceAreaIdFixedLengthImpl.class);
            metDPCriterion.leavingServiceAreaId = xml.get(LEAVING_SERVICE_AREA_ID, CellGlobalIdOrServiceAreaIdFixedLengthImpl.class);

            metDPCriterion.enteringLocationAreaId = xml.get(ENTERING_LOCATION_AREA_ID, LAIFixedLengthImpl.class);
            metDPCriterion.leavingLocationAreaId = xml.get(LEAVING_LOCATION_AREA_ID, LAIFixedLengthImpl.class);

            Boolean bval = xml.get(INTER_SYSTEM_HAND_OVER_TO_UMTS, Boolean.class);
            if (bval != null)
                metDPCriterion.interSystemHandOverToUMTS = bval;
            bval = xml.get(INTER_SYSTEM_HAND_OVER_TO_GSM, Boolean.class);
            if (bval != null)
                metDPCriterion.interSystemHandOverToGSM = bval;
            bval = xml.get(INTER_PLMN_HAND_OVER, Boolean.class);
            if (bval != null)
                metDPCriterion.interPLMNHandOver = bval;
            bval = xml.get(INTER_MSC_HAND_OVER, Boolean.class);
            if (bval != null)
                metDPCriterion.interMSCHandOver = bval;

            metDPCriterion.metDPCriterionAlt = xml.get(MET_DP_CRITERION_ALT, MetDPCriterionAltImpl.class);
        }

        @Override
        public void write(MetDPCriterionImpl metDPCriterion, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            if (metDPCriterion.enteringCellGlobalId != null) {
                xml.add((CellGlobalIdOrServiceAreaIdFixedLengthImpl) metDPCriterion.enteringCellGlobalId, ENTERING_CELL_GLOBAL_ID, CellGlobalIdOrServiceAreaIdFixedLengthImpl.class);
            }
            if (metDPCriterion.leavingCellGlobalId != null) {
                xml.add((CellGlobalIdOrServiceAreaIdFixedLengthImpl) metDPCriterion.leavingCellGlobalId, LEAVING_CELL_GLOBAL_ID, CellGlobalIdOrServiceAreaIdFixedLengthImpl.class);
            }
            if (metDPCriterion.enteringServiceAreaId != null) {
                xml.add((CellGlobalIdOrServiceAreaIdFixedLengthImpl) metDPCriterion.enteringServiceAreaId, ENTERING_SERVICE_AREA_ID, CellGlobalIdOrServiceAreaIdFixedLengthImpl.class);
            }
            if (metDPCriterion.leavingServiceAreaId != null) {
                xml.add((CellGlobalIdOrServiceAreaIdFixedLengthImpl) metDPCriterion.leavingServiceAreaId, LEAVING_SERVICE_AREA_ID, CellGlobalIdOrServiceAreaIdFixedLengthImpl.class);
            }

            if (metDPCriterion.enteringLocationAreaId != null) {
                xml.add((LAIFixedLengthImpl) metDPCriterion.enteringLocationAreaId, ENTERING_LOCATION_AREA_ID, LAIFixedLengthImpl.class);
            }
            if (metDPCriterion.leavingLocationAreaId != null) {
                xml.add((LAIFixedLengthImpl) metDPCriterion.leavingLocationAreaId, LEAVING_LOCATION_AREA_ID, LAIFixedLengthImpl.class);
            }

            if (metDPCriterion.interSystemHandOverToUMTS)
                xml.add(metDPCriterion.interSystemHandOverToUMTS, INTER_SYSTEM_HAND_OVER_TO_UMTS, Boolean.class);
            if (metDPCriterion.interSystemHandOverToGSM)
                xml.add(metDPCriterion.interSystemHandOverToGSM, INTER_SYSTEM_HAND_OVER_TO_GSM, Boolean.class);
            if (metDPCriterion.interPLMNHandOver)
                xml.add(metDPCriterion.interPLMNHandOver, INTER_PLMN_HAND_OVER, Boolean.class);
            if (metDPCriterion.interMSCHandOver)
                xml.add(metDPCriterion.interMSCHandOver, INTER_MSC_HAND_OVER, Boolean.class);

            if (metDPCriterion.metDPCriterionAlt != null) {
                xml.add((MetDPCriterionAltImpl) metDPCriterion.metDPCriterionAlt, MET_DP_CRITERION_ALT, MetDPCriterionAltImpl.class);
            }
        }
    };

    public enum CellGlobalIdOrServiceAreaIdFixedLength_Option {
        enteringCellGlobalId, leavingCellGlobalId, enteringServiceAreaId, leavingServiceAreaId;
    }

    public enum LAIFixedLength_Option {
        enteringLocationAreaId, leavingLocationAreaId;
    }

    public enum Boolean_Option {
        interSystemHandOverToUMTS, interSystemHandOverToGSM, interPLMNHandOver, interMSCHandOver;
    }

}
