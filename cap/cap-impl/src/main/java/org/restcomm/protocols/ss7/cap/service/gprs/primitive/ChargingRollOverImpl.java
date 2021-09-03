
package org.restcomm.protocols.ss7.cap.service.gprs.primitive;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.ChargingRollOver;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.ElapsedTimeRollOver;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.TransferredVolumeRollOver;
import org.restcomm.protocols.ss7.cap.primitives.CAPAsnPrimitive;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class ChargingRollOverImpl implements ChargingRollOver, CAPAsnPrimitive {

    public static final String _PrimitiveName = "ChargingRollOver";

    public static final int _ID_transferredVolumeRollOver = 0;
    public static final int _ID_elapsedTimeRollOver = 1;

    private TransferredVolumeRollOver transferredVolumeRollOver;
    private ElapsedTimeRollOver elapsedTimeRollOver;

    public ChargingRollOverImpl() {
    }

    public ChargingRollOverImpl(TransferredVolumeRollOver transferredVolumeRollOver) {
        this.transferredVolumeRollOver = transferredVolumeRollOver;
    }

    public ChargingRollOverImpl(ElapsedTimeRollOver elapsedTimeRollOver) {
        this.elapsedTimeRollOver = elapsedTimeRollOver;
    }

    @Override
    public TransferredVolumeRollOver getTransferredVolumeRollOver() {
        return this.transferredVolumeRollOver;
    }

    public ElapsedTimeRollOver getElapsedTimeRollOver() {
        return this.elapsedTimeRollOver;
    }

    @Override
    public int getTag() throws CAPException {
        if (transferredVolumeRollOver != null) {
            return _ID_transferredVolumeRollOver;
        } else {
            return _ID_elapsedTimeRollOver;
        }
    }

    @Override
    public int getTagClass() {
        return Tag.CLASS_CONTEXT_SPECIFIC;
    }

    @Override
    public boolean getIsPrimitive() {

        if (transferredVolumeRollOver != null) {
            return ((TransferredVolumeRollOverImpl) transferredVolumeRollOver).getIsPrimitive();
        } else {
            return ((ElapsedTimeRollOverImpl) elapsedTimeRollOver).getIsPrimitive();
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

        this.transferredVolumeRollOver = null;
        this.elapsedTimeRollOver = null;

        int tag = asnInputStream.getTag();

        if (asnInputStream.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {

            switch (tag) {
                case _ID_transferredVolumeRollOver:
                    this.transferredVolumeRollOver = new TransferredVolumeRollOverImpl();
                    asnInputStream.readTag();
                    ((TransferredVolumeRollOverImpl) this.transferredVolumeRollOver).decodeAll(asnInputStream);
                    break;
                case _ID_elapsedTimeRollOver:
                    this.elapsedTimeRollOver = new ElapsedTimeRollOverImpl();
                    asnInputStream.readTag();
                    ((ElapsedTimeRollOverImpl) this.elapsedTimeRollOver).decodeAll(asnInputStream);
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

        if (this.transferredVolumeRollOver == null && this.elapsedTimeRollOver == null
                || this.transferredVolumeRollOver != null && this.elapsedTimeRollOver != null) {
            throw new CAPException("Error while decoding " + _PrimitiveName + ": One and only one choice must be selected");
        }

        if (this.transferredVolumeRollOver != null) {
            ((TransferredVolumeRollOverImpl) this.transferredVolumeRollOver).encodeAll(asnOutputStream);
        } else {
            ((ElapsedTimeRollOverImpl) this.elapsedTimeRollOver).encodeAll(asnOutputStream);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName + " [");

        if (this.transferredVolumeRollOver != null) {
            sb.append("transferredVolumeRollOver=");
            sb.append(this.transferredVolumeRollOver.toString());
        }

        if (this.elapsedTimeRollOver != null) {
            sb.append("elapsedTimeRollOver=");
            sb.append(this.elapsedTimeRollOver.toString());
        }

        sb.append("]");

        return sb.toString();
    }

}