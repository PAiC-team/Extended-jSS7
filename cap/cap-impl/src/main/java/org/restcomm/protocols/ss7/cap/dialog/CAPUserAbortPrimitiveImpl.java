
package org.restcomm.protocols.ss7.cap.dialog;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.cap.api.dialog.CAPUserAbortReason;
import org.restcomm.protocols.ss7.cap.primitives.CAPAsnPrimitive;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class CAPUserAbortPrimitiveImpl implements CAPAsnPrimitive {

    public static final long[] CAP_AbortReason_OId = { 0, 4, 0, 0, 1, 1, 2, 2 };

    private CAPUserAbortReason reason;

    public CAPUserAbortPrimitiveImpl() {
    }

    public CAPUserAbortPrimitiveImpl(CAPUserAbortReason capUserAbortReason) {
        this.reason = capUserAbortReason;
    }

    public CAPUserAbortReason getCAPUserAbortReason() {
        return this.reason;
    }

    @Override
    public int getTag() throws CAPException {
        return Tag.ENUMERATED;
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
            throw new CAPParsingComponentException("IOException when decoding CAPUserAbortPrimitive: " + e.getMessage(), e,
                    CAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new CAPParsingComponentException("AsnException when decoding CAPUserAbortPrimitive: " + e.getMessage(), e,
                    CAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    @Override
    public void decodeData(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException {

        try {
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new CAPParsingComponentException("IOException when decoding CAPUserAbortPrimitive: " + e.getMessage(), e,
                    CAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new CAPParsingComponentException("AsnException when decoding CAPUserAbortPrimitive: " + e.getMessage(), e,
                    CAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    private void _decode(AsnInputStream ais, int length) throws CAPParsingComponentException, IOException, AsnException {

        int code = (int) ais.readIntegerData(length);

        this.reason = CAPUserAbortReason.getInstance(code);
        if (this.reason == null)
            this.reason = CAPUserAbortReason.no_reason_given;

        if (ais.available() > 0)
            throw new AsnException("Too much source data");
    }

    @Override
    public void encodeAll(AsnOutputStream asnOs) throws CAPException {

        this.encodeAll(asnOs, Tag.CLASS_UNIVERSAL, Tag.ENUMERATED);
    }

    @Override
    public void encodeAll(AsnOutputStream asnOs, int tagClass, int tag) throws CAPException {

        try {
            asnOs.writeTag(tagClass, true, tag);
            int pos = asnOs.StartContentDefiniteLength();
            this.encodeData(asnOs);
            asnOs.FinalizeContent(pos);
        } catch (AsnException e) {
            throw new CAPException("AsnException when encoding CAPUserAbortPrimitive: " + e.getMessage(), e);
        }
    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws CAPException {

        if (this.reason == null)
            throw new CAPException("CAP reason field must not be empty");

        try {
            asnOutputStream.writeIntegerData(this.reason.getCode());
        } catch (IOException e) {
            throw new CAPException("IOException when encoding CAPUserAbortPrimitive: " + e.getMessage(), e);
        }
    }
}
