
package org.restcomm.protocols.ss7.map.dialog;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.dialog.MAPProviderAbortReason;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;

/**
 *
 MAP-ProviderAbortInfo ::= SEQUENCE { map-ProviderAbortReason MAP-ProviderAbortReason, ..., extensionContainer
 * ExtensionContainer OPTIONAL -- extensionContainer must not be used in version 2 }
 *
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public class MAPProviderAbortInfoImpl implements MAPAsnPrimitive {

    public static final int MAP_PROVIDER_ABORT_INFO_TAG = 0x05;

    protected static final int PROVIDER_ABORT_TAG_CLASS = Tag.CLASS_CONTEXT_SPECIFIC;
    protected static final boolean PROVIDER_ABORT_TAG_PC_CONSTRUCTED = false;

    private MAPProviderAbortReason mapProviderAbortReason = null;
    private MAPExtensionContainer extensionContainer;

    public MAPProviderAbortInfoImpl() {
    }

    public MAPProviderAbortReason getMAPProviderAbortReason() {
        return this.mapProviderAbortReason;
    }

    public MAPExtensionContainer getExtensionContainer() {
        return extensionContainer;
    }

    public void setMAPProviderAbortReason(MAPProviderAbortReason mapProvAbrtReas) {
        this.mapProviderAbortReason = mapProvAbrtReas;
    }

    public void setExtensionContainer(MAPExtensionContainer extensionContainer) {
        this.extensionContainer = extensionContainer;
    }

    public int getTag() throws MAPException {
        return MAP_PROVIDER_ABORT_INFO_TAG;
    }

    public int getTagClass() {
        return Tag.CLASS_CONTEXT_SPECIFIC;
    }

    public boolean getIsPrimitive() {
        return false;
    }

    public void decodeAll(AsnInputStream asnInputStream) throws MAPParsingComponentException {

        try {
            int length = asnInputStream.readLength();
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding MAPProviderAbortInfo: " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding MAPProviderAbortInfo: " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    public void decodeData(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException {

        try {
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding MAPProviderAbortInfo: " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding MAPProviderAbortInfo: " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    private void _decode(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {

        this.setMAPProviderAbortReason(null);
        this.setExtensionContainer(null);

        AsnInputStream localAis = asnInputStream.readSequenceStreamData(length);

        int tag = localAis.readTag();
        if (localAis.getTagClass() != Tag.CLASS_UNIVERSAL && tag != Tag.ENUMERATED)
            throw new MAPParsingComponentException(
                    "Error decoding MAP-ProviderAbortInfo.map-ProviderAbortReason: bad tagClass or tag: tagClass="
                            + localAis.getTagClass() + ", tag=" + tag, MAPParsingComponentExceptionReason.MistypedParameter);
        int code = (int) localAis.readInteger();
        this.mapProviderAbortReason = MAPProviderAbortReason.getInstance(code);
        if (this.mapProviderAbortReason == null)
            throw new MAPParsingComponentException(
                    "Bad map-ProviderAbortReason code received when decoding MAP-ProviderAbortInfo" + code,
                    MAPParsingComponentExceptionReason.MistypedParameter);

        while (localAis.available() > 0) {
            tag = localAis.readTag();

            switch (localAis.getTagClass()) {
                case Tag.CLASS_UNIVERSAL:
                    switch (tag) {
                        case Tag.SEQUENCE:
                            this.extensionContainer = new MAPExtensionContainerImpl();
                            ((MAPExtensionContainerImpl) this.extensionContainer).decodeAll(localAis);
                            break;

                        default:
                            localAis.advanceElement();
                            break;
                    }
                    break;

                default:
                    localAis.advanceElement();
                    break;
            }
        }
    }

    public void encodeAll(AsnOutputStream asnOutputStream) throws MAPException {

        this.encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, MAP_PROVIDER_ABORT_INFO_TAG);
    }

    public void encodeAll(AsnOutputStream asnOutputStream, int tagClass, int tag) throws MAPException {

        try {
            asnOutputStream.writeTag(tagClass, false, tag);
            int pos = asnOutputStream.StartContentDefiniteLength();
            this.encodeData(asnOutputStream);
            asnOutputStream.FinalizeContent(pos);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding MAP-ProviderAbortInfo: " + e.getMessage(), e);
        }
    }

    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {

        if (this.mapProviderAbortReason == null)
            throw new MAPException("Error while encoding MAP-ProviderAbortInfo: MapProviderAbortReason parameter has not set");

        try {
            asnOutputStream.writeInteger(Tag.CLASS_UNIVERSAL, Tag.ENUMERATED, this.mapProviderAbortReason.getCode());

            if (this.extensionContainer != null)
                ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOutputStream);

        } catch (IOException e) {
            throw new MAPException("IOException when encoding MAPProviderAbortInfo: " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding MAPProviderAbortInfo: " + e.getMessage(), e);
        }
    }
}
