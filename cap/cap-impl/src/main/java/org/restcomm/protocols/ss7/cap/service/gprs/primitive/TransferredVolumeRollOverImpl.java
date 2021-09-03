
package org.restcomm.protocols.ss7.cap.service.gprs.primitive;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.ROVolumeIfTariffSwitch;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.TransferredVolumeRollOver;
import org.restcomm.protocols.ss7.cap.primitives.CAPAsnPrimitive;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class TransferredVolumeRollOverImpl implements TransferredVolumeRollOver, CAPAsnPrimitive {

    public static final String _PrimitiveName = "TransferredVolumeRollOver";

    public static final int _ID_roVolumeIfNoTariffSwitch = 0;
    public static final int _ID_roVolumeIfTariffSwitch = 1;

    private Integer roVolumeIfNoTariffSwitch;
    private ROVolumeIfTariffSwitch roVolumeIfTariffSwitch;

    public TransferredVolumeRollOverImpl() {
    }

    public TransferredVolumeRollOverImpl(Integer roVolumeIfNoTariffSwitch) {
        this.roVolumeIfNoTariffSwitch = roVolumeIfNoTariffSwitch;
    }

    public TransferredVolumeRollOverImpl(ROVolumeIfTariffSwitch roVolumeIfTariffSwitch) {
        this.roVolumeIfTariffSwitch = roVolumeIfTariffSwitch;
    }

    public Integer getROVolumeIfNoTariffSwitch() {
        return this.roVolumeIfNoTariffSwitch;
    }

    public ROVolumeIfTariffSwitch getROVolumeIfTariffSwitch() {
        return this.roVolumeIfTariffSwitch;
    }

    @Override
    public int getTag() throws CAPException {
        if (roVolumeIfNoTariffSwitch != null) {
            return _ID_roVolumeIfNoTariffSwitch;
        } else {
            return _ID_roVolumeIfTariffSwitch;
        }
    }

    @Override
    public int getTagClass() {
        return Tag.CLASS_CONTEXT_SPECIFIC;
    }

    @Override
    public boolean getIsPrimitive() {
        if (roVolumeIfNoTariffSwitch != null) {
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

        this.roVolumeIfNoTariffSwitch = null;
        this.roVolumeIfTariffSwitch = null;

        int tag = asnInputStream.getTag();

        if (asnInputStream.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
            switch (tag) {
                case _ID_roVolumeIfNoTariffSwitch:
                    if (!asnInputStream.isTagPrimitive())
                        throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".roVolumeIfNoTariffSwitch: Parameter is not primitive",
                                CAPParsingComponentExceptionReason.MistypedParameter);

                    this.roVolumeIfNoTariffSwitch = (int) asnInputStream.readIntegerData(length);
                    break;
                case _ID_roVolumeIfTariffSwitch:
                    if (asnInputStream.isTagPrimitive())
                        throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".volumeIfTariffSwitch: Parameter is primitive",
                                CAPParsingComponentExceptionReason.MistypedParameter);
                    this.roVolumeIfTariffSwitch = new ROVolumeIfTariffSwitchImpl();
                    ((ROVolumeIfTariffSwitchImpl) this.roVolumeIfTariffSwitch).decodeData(asnInputStream, length);
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

        if (this.roVolumeIfNoTariffSwitch == null && this.roVolumeIfTariffSwitch == null
                || this.roVolumeIfNoTariffSwitch != null && this.roVolumeIfTariffSwitch != null) {
            throw new CAPException("Error while decoding " + _PrimitiveName + ": One and only one choice must be selected");
        }

        try {
            if (this.roVolumeIfNoTariffSwitch != null) {
                asnOutputStream.writeIntegerData(roVolumeIfNoTariffSwitch.intValue());
            } else {
                ((ROVolumeIfTariffSwitchImpl) this.roVolumeIfTariffSwitch).encodeData(asnOutputStream);
            }
        } catch (IOException e) {
            throw new CAPException("MAPException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName + " [");

        if (this.roVolumeIfNoTariffSwitch != null) {
            sb.append("roVolumeIfNoTariffSwitch=");
            sb.append(this.roVolumeIfNoTariffSwitch.toString());
        }

        if (this.roVolumeIfTariffSwitch != null) {
            sb.append("roVolumeIfTariffSwitch=");
            sb.append(this.roVolumeIfTariffSwitch.toString());
        }

        sb.append("]");

        return sb.toString();
    }

}