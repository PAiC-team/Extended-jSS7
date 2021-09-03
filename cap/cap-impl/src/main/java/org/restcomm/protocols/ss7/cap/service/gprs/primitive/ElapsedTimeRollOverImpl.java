
package org.restcomm.protocols.ss7.cap.service.gprs.primitive;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.ElapsedTimeRollOver;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.ROTimeGPRSIfTariffSwitch;
import org.restcomm.protocols.ss7.cap.primitives.CAPAsnPrimitive;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class ElapsedTimeRollOverImpl implements ElapsedTimeRollOver, CAPAsnPrimitive {

    public static final String _PrimitiveName = "ElapsedTimeRollOver";

    public static final int _ID_roTimeGPRSIfNoTariffSwitch = 0;
    public static final int _ID_roTimeGPRSIfTariffSwitch = 1;

    public Integer roTimeGPRSIfNoTariffSwitch;
    public ROTimeGPRSIfTariffSwitch roTimeGPRSIfTariffSwitch;

    public ElapsedTimeRollOverImpl() {
    }

    public ElapsedTimeRollOverImpl(Integer roTimeGPRSIfNoTariffSwitch) {
        this.roTimeGPRSIfNoTariffSwitch = roTimeGPRSIfNoTariffSwitch;
    }

    public ElapsedTimeRollOverImpl(ROTimeGPRSIfTariffSwitch roTimeGPRSIfTariffSwitch) {
        this.roTimeGPRSIfTariffSwitch = roTimeGPRSIfTariffSwitch;
    }

    public Integer getROTimeGPRSIfNoTariffSwitch() {
        return this.roTimeGPRSIfNoTariffSwitch;
    }

    public ROTimeGPRSIfTariffSwitch getROTimeGPRSIfTariffSwitch() {
        return this.roTimeGPRSIfTariffSwitch;
    }

    @Override
    public int getTag() throws CAPException {
        if (roTimeGPRSIfNoTariffSwitch != null) {
            return _ID_roTimeGPRSIfNoTariffSwitch;
        } else {
            return _ID_roTimeGPRSIfTariffSwitch;
        }
    }

    @Override
    public int getTagClass() {
        return Tag.CLASS_CONTEXT_SPECIFIC;
    }

    @Override
    public boolean getIsPrimitive() {
        if (roTimeGPRSIfNoTariffSwitch != null) {
            return true;
        } else {
            return false;
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

    private void _decode(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException, IOException, AsnException,
            MAPParsingComponentException {

        this.roTimeGPRSIfNoTariffSwitch = null;
        this.roTimeGPRSIfTariffSwitch = null;

        int tag = asnInputStream.getTag();

        if (asnInputStream.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
            switch (tag) {
                case _ID_roTimeGPRSIfNoTariffSwitch:
                    if (!asnInputStream.isTagPrimitive())
                        throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".roTimeGPRSIfNoTariffSwitch: Parameter is not primitive",
                                CAPParsingComponentExceptionReason.MistypedParameter);
                    this.roTimeGPRSIfNoTariffSwitch = (int) asnInputStream.readIntegerData(length);
                    break;
                case _ID_roTimeGPRSIfTariffSwitch:
                    if (asnInputStream.isTagPrimitive())
                        throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".roTimeGPRSIfTariffSwitch: Parameter is  primitive",
                                CAPParsingComponentExceptionReason.MistypedParameter);
                    this.roTimeGPRSIfTariffSwitch = new ROTimeGPRSIfTariffSwitchImpl();
                    ((ROTimeGPRSIfTariffSwitchImpl) this.roTimeGPRSIfTariffSwitch).decodeData(asnInputStream, length);
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

        if (this.roTimeGPRSIfNoTariffSwitch == null && this.roTimeGPRSIfTariffSwitch == null
                || this.roTimeGPRSIfNoTariffSwitch != null && this.roTimeGPRSIfTariffSwitch != null) {
            throw new CAPException("Error while decoding " + _PrimitiveName + ": One and only one choice must be selected");
        }
        try {
            if (this.roTimeGPRSIfNoTariffSwitch != null) {
                asnOutputStream.writeIntegerData(roTimeGPRSIfNoTariffSwitch.intValue());
            } else {
                ((ROTimeGPRSIfTariffSwitchImpl) this.roTimeGPRSIfTariffSwitch).encodeData(asnOutputStream);
            }
        } catch (IOException e) {
            throw new CAPException("MAPException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName + " [");

        if (this.roTimeGPRSIfNoTariffSwitch != null) {
            sb.append("roTimeGPRSIfNoTariffSwitch=");
            sb.append(this.roTimeGPRSIfNoTariffSwitch.toString());
        }

        if (this.roTimeGPRSIfTariffSwitch != null) {
            sb.append("roTimeGPRSIfTariffSwitch=");
            sb.append(this.roTimeGPRSIfTariffSwitch.toString());
        }

        sb.append("]");

        return sb.toString();
    }

}