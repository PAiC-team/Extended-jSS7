package org.restcomm.protocols.ss7.map.dialog;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.dialog.Reason;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;
import org.restcomm.protocols.ss7.tcap.asn.ApplicationContextName;
import org.restcomm.protocols.ss7.tcap.asn.ApplicationContextNameImpl;

/**
 * MAP-RefuseInfo ::= SEQUENCE { reason Reason, ..., extensionContainer ExtensionContainer -- extensionContainer must not be
 * used in version 2 }
 *
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public class MAPRefuseInfoImpl implements MAPAsnPrimitive {

    public static final int MAP_REFUSE_INFO_TAG = 0x03;

    protected static final int REFUSE_TAG_CLASS = Tag.CLASS_CONTEXT_SPECIFIC;
    protected static final boolean REFUSE_TAG_PC_CONSTRUCTED = false;
    protected static final boolean REFUSE_TAG_PC_PRIMITIVE = true;

    private Reason reason;
    private MAPExtensionContainer extensionContainer;
    private ApplicationContextName alternativeAcn;

    public Reason getReason() {
        return this.reason;
    }

    public MAPExtensionContainer getExtensionContainer() {
        return extensionContainer;
    }

    public ApplicationContextName getAlternativeAcn() {
        return this.alternativeAcn;
    }

    public void setReason(Reason reason) {
        this.reason = reason;
    }

    public void setExtensionContainer(MAPExtensionContainer extensionContainer) {
        this.extensionContainer = extensionContainer;
    }

    public void setAlternativeAcn(ApplicationContextName alternativeAcn) {
        this.alternativeAcn = alternativeAcn;
    }

    public int getTag() throws MAPException {
        return MAP_REFUSE_INFO_TAG;
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
            throw new MAPParsingComponentException("IOException when decoding MAP-RefuseInfo: " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding MAP-RefuseInfo: " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    public void decodeData(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException {

        try {
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding MAP-RefuseInfo: " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding MAP-RefuseInfo: " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    private void _decode(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {
        // MAP-RefuseInfo ::= SEQUENCE {
        // reason ENUMERATED {
        // noReasonGiven ( 0 ),
        // invalidDestinationReference ( 1 ),
        // invalidOriginatingReference ( 2 ) },
        // ... ,
        // extensionContainer SEQUENCE {
        // privateExtensionList [0] IMPLICIT SEQUENCE ( SIZE( 1 .. 10 ) ) OF
        // SEQUENCE {
        // extId MAP-EXTENSION .&extensionId ( {
        // ,
        // ...} ) ,
        // extType MAP-EXTENSION .&ExtensionType ( {
        // ,
        // ...} { @extId } ) OPTIONAL} OPTIONAL,
        // pcs-Extensions [1] IMPLICIT SEQUENCE {
        // ... } OPTIONAL,
        // ... } OPTIONAL,
        // alternativeApplicationContext OBJECT IDENTIFIER OPTIONAL}

        this.reason = null;
        this.alternativeAcn = null;
        this.extensionContainer = null;

        AsnInputStream localAis = asnInputStream.readSequenceStreamData(length);

        int tag = localAis.readTag();
        if (tag != Tag.ENUMERATED || localAis.getTagClass() != Tag.CLASS_UNIVERSAL)
            throw new MAPParsingComponentException("Error decoding MAP-RefuseInfo.Reason: bad tag or tagClass",
                    MAPParsingComponentExceptionReason.MistypedParameter);
        int reasonCode = (int) localAis.readInteger();
        this.reason = Reason.getReason(reasonCode);

        while (localAis.available() > 0) {
            tag = localAis.readTag();

            switch (localAis.getTagClass()) {
                case Tag.CLASS_UNIVERSAL:
                    switch (tag) {
                        case Tag.SEQUENCE:
                            this.extensionContainer = new MAPExtensionContainerImpl();
                            ((MAPExtensionContainerImpl) this.extensionContainer).decodeAll(localAis);
                            break;

                        case Tag.OBJECT_IDENTIFIER:
                            this.alternativeAcn = new ApplicationContextNameImpl();
                            long[] oid = localAis.readObjectIdentifier();
                            this.alternativeAcn.setOid(oid);
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

        this.encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, MAP_REFUSE_INFO_TAG);
    }

    public void encodeAll(AsnOutputStream asnOutputStream, int tagClass, int tag) throws MAPException {

        try {
            asnOutputStream.writeTag(tagClass, false, tag);
            int pos = asnOutputStream.StartContentDefiniteLength();
            this.encodeData(asnOutputStream);
            asnOutputStream.FinalizeContent(pos);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding MAPRefuseInfo: " + e.getMessage(), e);
        }
    }

    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {

        if (this.reason == null)
            throw new MAPException("Error decoding MAP-RefuseInfo: Reason field must not be empty");

        try {
            asnOutputStream.writeInteger(Tag.CLASS_UNIVERSAL, Tag.ENUMERATED, this.reason.getCode());

            if (this.extensionContainer != null)
                ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOutputStream);
            if (this.alternativeAcn != null)
                asnOutputStream.writeObjectIdentifier(this.alternativeAcn.getOid());

        } catch (IOException e) {
            throw new MAPException("IOException when encoding MAPRefuseInfo: " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding MAPRefuseInfo: " + e.getMessage(), e);
        }
    }
}
