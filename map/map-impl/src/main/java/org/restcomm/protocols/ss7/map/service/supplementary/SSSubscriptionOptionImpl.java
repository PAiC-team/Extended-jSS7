
package org.restcomm.protocols.ss7.map.service.supplementary;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.service.supplementary.CliRestrictionOption;
import org.restcomm.protocols.ss7.map.api.service.supplementary.OverrideCategory;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSSubscriptionOption;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;

/**
 * @author daniel bichara
 *
 */
public class SSSubscriptionOptionImpl implements SSSubscriptionOption, MAPAsnPrimitive {

    public static final String _PrimitiveName = "SSSubscriptionOption";

    public static final int _TAG_cliRestrictionOption = 2;
    public static final int _TAG_overrideCategory = 1;

    private CliRestrictionOption cliRestrictionOption = null;
    private OverrideCategory overrideCategory = null;

    public SSSubscriptionOptionImpl() {

    }

    public SSSubscriptionOptionImpl(CliRestrictionOption cliRestrictionOption) {

        this.cliRestrictionOption = cliRestrictionOption;
    }

    public SSSubscriptionOptionImpl(OverrideCategory overrideCategory) {

        this.overrideCategory = overrideCategory;
    }

    public CliRestrictionOption getCliRestrictionOption() {
        return this.cliRestrictionOption;
    }

    public OverrideCategory getOverrideCategory() {
        return this.overrideCategory;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#getTag()
     */
    public int getTag() throws MAPException {
        if (cliRestrictionOption != null) {
            return _TAG_cliRestrictionOption;
        } else if (overrideCategory != null) {
            return _TAG_overrideCategory;
        } else {
            throw new MAPException("No of choices are supplied");
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#getTagClass()
     */
    public int getTagClass() {
        return Tag.CLASS_CONTEXT_SPECIFIC;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#getIsPrimitive ()
     */
    public boolean getIsPrimitive() {
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#decodeAll(org.mobicents.protocols.asn.AsnInputStream)
     */
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

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#decodeData(org.mobicents.protocols.asn.AsnInputStream,
     * int)
     */
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
        this.cliRestrictionOption = null;
        this.overrideCategory = null;

        if (asnInputStream.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC || !asnInputStream.isTagPrimitive())
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": bad tag class or is not primitive: TagClass=" + asnInputStream.getTagClass(),
                    MAPParsingComponentExceptionReason.MistypedParameter);

        switch (asnInputStream.getTag()) {
            case _TAG_cliRestrictionOption:
                this.cliRestrictionOption = CliRestrictionOption.getInstance((int) asnInputStream.readIntegerData(length));
                break;
            case _TAG_overrideCategory:
                this.overrideCategory = OverrideCategory.getInstance((int) asnInputStream.readIntegerData(length));
                break;

            default:
                throw new MAPParsingComponentException("Error while " + _PrimitiveName + ": bad tag: " + asnInputStream.getTag(),
                        MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#encodeAll(org.mobicents.protocols.asn.AsnOutputStream)
     */
    public void encodeAll(AsnOutputStream asnOutputStream) throws MAPException {
        this.encodeAll(asnOutputStream, this.getTagClass(), this.getTag());
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#encodeAll(org.mobicents.protocols.asn.AsnOutputStream,
     * int, int)
     */
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

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#encodeData (org.mobicents.protocols.asn.AsnOutputStream)
     */
    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {

        if (this.cliRestrictionOption == null && this.overrideCategory == null)
            throw new MAPException("Error while encoding " + _PrimitiveName
                    + ": missing cliRestrictionOption and overrideCategory.");

        if (this.cliRestrictionOption != null && this.overrideCategory != null)
            throw new MAPException("Error while encoding " + _PrimitiveName
                    + ": both cliRestrictionOption and overrideCategory are defined.");

        try {

            if (this.cliRestrictionOption != null) {
                asnOutputStream.writeIntegerData(this.cliRestrictionOption.getCode());
            } else {
                asnOutputStream.writeIntegerData(this.overrideCategory.getCode());
            }
        } catch (IOException e) {
            throw new MAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName + " [");

        if (this.cliRestrictionOption != null) {
            sb.append("cliRestrictionOption=");
            sb.append(this.cliRestrictionOption.toString());
            sb.append(", ");
        }

        if (this.overrideCategory != null) {
            sb.append("overrideCategory=");
            sb.append(this.overrideCategory.toString());
        }

        sb.append("]");

        return sb.toString();
    }

}
