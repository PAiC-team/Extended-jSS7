package org.restcomm.protocols.ss7.map.service.sms;

import java.io.IOException;
import java.nio.charset.Charset;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.service.sms.SmsSignalInfo;
import org.restcomm.protocols.ss7.map.api.smstpdu.SmsTpdu;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;
import org.restcomm.protocols.ss7.map.smstpdu.SmsTpduImpl;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class SmsSignalInfoImpl implements SmsSignalInfo, MAPAsnPrimitive {

    public static final String _PrimitiveName = "SmsSignalInfo";

    private byte[] data;
    private Charset gsm8Charset;

    public SmsSignalInfoImpl() {
    }

    public SmsSignalInfoImpl(byte[] data, Charset gsm8Charset) {
        this.data = data;
        this.setGsm8Charset(gsm8Charset);
    }

    public SmsSignalInfoImpl(SmsTpdu tpdu, Charset gsm8Charset) throws MAPException {
        if (tpdu == null)
            throw new MAPException("SmsTpdu must not be null");

        this.setGsm8Charset(gsm8Charset);
        this.data = tpdu.encodeData();
    }

    public byte[] getData() {
        return this.data;
    }

    public Charset getGsm8Charset() {
        return gsm8Charset;
    }

    public void setGsm8Charset(Charset gsm8Charset) {
        this.gsm8Charset = gsm8Charset;
    }

    public SmsTpdu decodeTpdu(boolean mobileOriginatedMessage) throws MAPException {
        return SmsTpduImpl.createInstance(this.data, mobileOriginatedMessage, this.getGsm8Charset());
    }

    public int getTag() throws MAPException {
        return Tag.STRING_OCTET;
    }

    public int getTagClass() {
        return Tag.CLASS_UNIVERSAL;
    }

    public boolean getIsPrimitive() {
        return true;
    }

    public void decodeAll(AsnInputStream asnInputStream) throws MAPParsingComponentException {
        try {
            int length = asnInputStream.readLength();
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    public void decodeData(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException {
        try {
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    private void _decode(AsnInputStream asnInputStream, int length) throws IOException, AsnException {
        this.data = asnInputStream.readOctetStringData(length);
    }

    public void encodeAll(AsnOutputStream asnOutputStream) throws MAPException {
        this.encodeAll(asnOutputStream, Tag.CLASS_UNIVERSAL, this.getTag());
    }

    public void encodeAll(AsnOutputStream asnOutputStream, int tagClass, int tag) throws MAPException {

        try {
            asnOutputStream.writeTag(tagClass, true, tag);
            int pos = asnOutputStream.StartContentDefiniteLength();
            this.encodeData(asnOutputStream);
            asnOutputStream.FinalizeContent(pos);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {

        if (this.data == null || this.data.length == 0)
            throw new MAPException("Error when encoding " + _PrimitiveName + ": data is empty");

        asnOutputStream.writeOctetStringData(data);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("SmsSignalInfo [");

        boolean moExists = false;
        try {
            SmsTpdu tpdu = SmsTpduImpl.createInstance(this.data, true, getGsm8Charset());
            sb.append("MO case: ");
            sb.append(tpdu.toString());
            moExists = true;
        } catch (MAPException e) {
        }
        try {
            if (moExists)
                sb.append("\n");
            SmsTpdu tpdu = SmsTpduImpl.createInstance(this.data, false, getGsm8Charset());
            sb.append("MT case: ");
            sb.append(tpdu.toString());
        } catch (MAPException e) {
        }

        sb.append("]");

        return sb.toString();
    }
}
