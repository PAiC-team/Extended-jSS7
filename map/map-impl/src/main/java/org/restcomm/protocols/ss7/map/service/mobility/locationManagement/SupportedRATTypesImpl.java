
package org.restcomm.protocols.ss7.map.service.mobility.locationManagement;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.BitSetStrictLength;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.SupportedRATTypes;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;

/**
 *
 *
 * @author sergey vetyutnev
 *
 */
public class SupportedRATTypesImpl implements SupportedRATTypes, MAPAsnPrimitive {

    private static final int _INDEX_utran = 0;
    private static final int _INDEX_geran = 1;
    private static final int _INDEX_gan = 2;
    private static final int _INDEX_i_hspa_evolution = 3;
    private static final int _INDEX_e_utran = 4;

    public static final String _PrimitiveName = "SupportedRATTypes";

    private BitSetStrictLength bitString = new BitSetStrictLength(5);

    public SupportedRATTypesImpl() {
    }

    public SupportedRATTypesImpl(boolean utran, boolean geran, boolean gan, boolean i_hspa_evolution, boolean e_utran) {
        if (utran)
            this.bitString.set(_INDEX_utran);
        if (geran)
            this.bitString.set(_INDEX_geran);
        if (gan)
            this.bitString.set(_INDEX_gan);
        if (i_hspa_evolution)
            this.bitString.set(_INDEX_i_hspa_evolution);
        if (e_utran)
            this.bitString.set(_INDEX_e_utran);
    }

    public int getTag() throws MAPException {
        return Tag.STRING_BIT;
    }

    public int getTagClass() {
        return Tag.CLASS_UNIVERSAL;
    }

    public boolean getIsPrimitive() {
        return true;
    }

    public boolean getUtran() {
        return this.bitString.get(_INDEX_utran);
    }

    public boolean getGeran() {
        return this.bitString.get(_INDEX_geran);
    }

    public boolean getGan() {
        return this.bitString.get(_INDEX_gan);
    }

    public boolean getIHspaEvolution() {
        return this.bitString.get(_INDEX_i_hspa_evolution);
    }

    public boolean getEUtran() {
        return this.bitString.get(_INDEX_e_utran);
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

    private void _decode(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {
        if (length < 1 || length > 2)
            throw new MAPParsingComponentException("Error decoding " + _PrimitiveName
                    + ": the field must contain from 1 or 2 octets. Contains: " + length,
                    MAPParsingComponentExceptionReason.MistypedParameter);

        this.bitString = asnInputStream.readBitStringData(length);
    }

    public void encodeAll(AsnOutputStream asnOutputStream) throws MAPException {
        this.encodeAll(asnOutputStream, this.getTagClass(), this.getTag());
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
        try {
            asnOutputStream.writeBitStringData(this.bitString);
        } catch (IOException e) {
            throw new MAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bitString == null) ? 0 : bitString.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SupportedRATTypesImpl other = (SupportedRATTypesImpl) obj;
        if (bitString == null) {
            if (other.bitString != null)
                return false;
        } else if (!bitString.equals(other.bitString))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SupportedRATTypes [");

        if (getUtran())
            sb.append("utran, ");
        if (getGeran())
            sb.append("geran, ");
        if (getGan())
            sb.append("gan, ");
        if (getIHspaEvolution())
            sb.append("i_hspa_evolution, ");
        if (getEUtran())
            sb.append("e_utran, ");

        sb.append("]");

        return sb.toString();
    }
}
