
package org.restcomm.protocols.ss7.cap.service.gprs.primitive;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.ChargingResult;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.ElapsedTime;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.TransferredVolume;
import org.restcomm.protocols.ss7.cap.primitives.CAPAsnPrimitive;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class ChargingResultImpl implements ChargingResult, CAPAsnPrimitive {

    public static final String _PrimitiveName = "ChargingResult";

    public static final int _ID_transferredVolume = 0;
    public static final int _ID_elapsedTime = 1;

    private TransferredVolume transferredVolume;
    private ElapsedTime elapsedTime;

    public ChargingResultImpl() {

    }

    public ChargingResultImpl(TransferredVolume transferredVolume) {
        this.transferredVolume = transferredVolume;
    }

    public ChargingResultImpl(ElapsedTime elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    @Override
    public TransferredVolume getTransferredVolume() {
        return this.transferredVolume;
    }

    public ElapsedTime getElapsedTime() {
        return this.elapsedTime;
    }

    @Override
    public int getTag() throws CAPException {
        if (transferredVolume != null) {
            return _ID_transferredVolume;
        } else {
            return _ID_elapsedTime;
        }
    }

    @Override
    public int getTagClass() {
        return Tag.CLASS_CONTEXT_SPECIFIC;
    }

    @Override
    public boolean getIsPrimitive() {
        return false;
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

        this.transferredVolume = null;
        this.elapsedTime = null;

        int tag = asnInputStream.getTag();

        if (asnInputStream.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
            switch (tag) {
                case _ID_transferredVolume:
                    this.transferredVolume = new TransferredVolumeImpl();
                    asnInputStream.readTag();
                    ((TransferredVolumeImpl) this.transferredVolume).decodeAll(asnInputStream);
                    break;
                case _ID_elapsedTime:
                    this.elapsedTime = new ElapsedTimeImpl();
                    asnInputStream.readTag();
                    ((ElapsedTimeImpl) this.elapsedTime).decodeAll(asnInputStream);
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
        if (this.transferredVolume == null && this.elapsedTime == null || this.transferredVolume != null
                && this.elapsedTime != null) {
            throw new CAPException("Error while decoding " + _PrimitiveName + ": One and only one choice must be selected");
        }

        if (this.transferredVolume != null) {
            ((TransferredVolumeImpl) this.transferredVolume).encodeAll(asnOutputStream);
        } else {
            ((ElapsedTimeImpl) this.elapsedTime).encodeAll(asnOutputStream);
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName + " [");

        if (this.transferredVolume != null) {
            sb.append("transferredVolume=");
            sb.append(this.transferredVolume.toString());
        }

        if (this.elapsedTime != null) {
            sb.append("elapsedTime=");
            sb.append(this.elapsedTime.toString());
        }

        sb.append("]");

        return sb.toString();
    }

}
