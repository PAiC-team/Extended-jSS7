package org.restcomm.protocols.ss7.map.dialog;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.dialog.MAPUserAbortChoice;
import org.restcomm.protocols.ss7.map.api.dialog.ProcedureCancellationReason;
import org.restcomm.protocols.ss7.map.api.dialog.ResourceUnavailableReason;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;

/**
 * MAP-UserAbortInfo ::= SEQUENCE { map-UserAbortChoice MAP-UserAbortChoice, ... extensionContainer ExtensionContainer OPTIONAL
 * }
 *
 * MAP-UserAbortChoice ::= CHOICE { userSpecificReason [0] NULL, userResourceLimitation [1] NULL, resourceUnavailable [2]
 * ResourceUnavailableReason, applicationProcedureCancellation [3] ProcedureCancellationReason}
 *
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public class MAPUserAbortInfoImpl implements MAPAsnPrimitive {

    public static final int MAP_USER_ABORT_INFO_TAG = 0x04;

    protected static final int USER_ABORT_TAG_CLASS = Tag.CLASS_CONTEXT_SPECIFIC;
    protected static final boolean USER_ABORT_TAG_PC_CONSTRUCTED = false;

    private MAPUserAbortChoice mapUserAbortChoice = null;
    private MAPExtensionContainer extensionContainer;

    public MAPUserAbortChoice getMAPUserAbortChoice() {
        return this.mapUserAbortChoice;
    }

    public MAPExtensionContainer getExtensionContainer() {
        return extensionContainer;
    }

    public void setMAPUserAbortChoice(MAPUserAbortChoice mapUserAbortChoice) {
        this.mapUserAbortChoice = mapUserAbortChoice;
    }

    public void setExtensionContainer(MAPExtensionContainer extensionContainer) {
        this.extensionContainer = extensionContainer;
    }

    public int getTag() throws MAPException {
        return MAP_USER_ABORT_INFO_TAG;
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
            throw new MAPParsingComponentException("IOException when decoding MAPUserAbortInfo: " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding MAPUserAbortInfo: " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    public void decodeData(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException {

        try {
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding MAPUserAbortInfo: " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding MAPUserAbortInfo: " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    private void _decode(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {

        // MAP-UserAbortInfo ::= SEQUENCE {
        // map-UserAbortChoice CHOICE {
        // userSpecificReason [0] IMPLICIT NULL,
        // userResourceLimitation [1] IMPLICIT NULL,
        // resourceUnavailable [2] IMPLICIT ENUMERATED {
        // shortTermResourceLimitation ( 0 ),
        // longTermResourceLimitation ( 1 ) },
        // applicationProcedureCancellation [3] IMPLICIT ENUMERATED {
        // handoverCancellation ( 0 ),
        // radioChannelRelease ( 1 ),
        // networkPathRelease ( 2 ),
        // callRelease ( 3 ),
        // associatedProcedureFailure ( 4 ),
        // tandemDialogueRelease ( 5 ),
        // remoteOperationsFailure ( 6 ) }},
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
        // ... } OPTIONAL}

        this.mapUserAbortChoice = null;
        this.extensionContainer = null;

        AsnInputStream localAis = asnInputStream.readSequenceStreamData(length);

        int tag = localAis.readTag();

        if (localAis.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC || !localAis.isTagPrimitive())
            throw new MAPParsingComponentException(
                    "Error while decoding MAPUserAbortInfo.map-UserAbortChoice: bad tag class or not primitive element",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        MAPUserAbortChoiceImpl usAbrtChoice = new MAPUserAbortChoiceImpl();
        switch (tag) {
            case MAPUserAbortChoiceImpl.USER_SPECIFIC_REASON_TAG:
                try {
                    localAis.readNull();
                } catch (Exception e) {
                    // we ignore ASN bad syntax
                }
                usAbrtChoice.setUserSpecificReason();
                this.setMAPUserAbortChoice(usAbrtChoice);
                break;

            case MAPUserAbortChoiceImpl.USER_RESOURCE_LIMITATION_TAG:
                try {
                    localAis.readNull();
                } catch (Exception e) {
                    // we ignore ASN bad syntax
                }
                usAbrtChoice.setUserResourceLimitation();
                this.setMAPUserAbortChoice(usAbrtChoice);
                break;

            case MAPUserAbortChoiceImpl.RESOURCE_UNAVAILABLE:
                int code = (int) localAis.readInteger();
                ResourceUnavailableReason resUnaReas = ResourceUnavailableReason.getInstance(code);
                usAbrtChoice.setResourceUnavailableReason(resUnaReas);
                this.setMAPUserAbortChoice(usAbrtChoice);
                break;

            case MAPUserAbortChoiceImpl.APPLICATION_PROCEDURE_CANCELLATION:
                code = (int) localAis.readInteger();
                ProcedureCancellationReason procCanReasn = ProcedureCancellationReason.getInstance(code);
                usAbrtChoice.setProcedureCancellationReason(procCanReasn);
                this.setMAPUserAbortChoice(usAbrtChoice);
                break;

            default:
                throw new MAPParsingComponentException("Error while decoding MAPUserAbortInfo.map-UserAbortChoice: bad tag",
                        MAPParsingComponentExceptionReason.MistypedParameter);
        }

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

        this.encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, MAP_USER_ABORT_INFO_TAG);
    }

    public void encodeAll(AsnOutputStream asnOutputStream, int tagClass, int tag) throws MAPException {

        try {
            asnOutputStream.writeTag(tagClass, false, tag);
            int pos = asnOutputStream.StartContentDefiniteLength();
            this.encodeData(asnOutputStream);
            asnOutputStream.FinalizeContent(pos);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding MAPUserAbortInfo: " + e.getMessage(), e);
        }
    }

    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {

        if (this.mapUserAbortChoice == null)
            throw new MAPException("Error encoding MAPUserAbortInfo: UserSpecificReason must not be null");

        try {
            if (this.mapUserAbortChoice.isUserSpecificReason()) {
                asnOutputStream.writeNull(Tag.CLASS_CONTEXT_SPECIFIC, MAPUserAbortChoiceImpl.USER_SPECIFIC_REASON_TAG);
            } else if (this.mapUserAbortChoice.isUserResourceLimitation()) {
                asnOutputStream.writeNull(Tag.CLASS_CONTEXT_SPECIFIC, MAPUserAbortChoiceImpl.USER_RESOURCE_LIMITATION_TAG);
            } else if (this.mapUserAbortChoice.isResourceUnavailableReason()) {
                ResourceUnavailableReason rur = this.mapUserAbortChoice.getResourceUnavailableReason();
                if (rur == null)
                    throw new MAPException("Error encoding MAPUserAbortInfo: ResourceUnavailableReason value must not be null");
                asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, MAPUserAbortChoiceImpl.RESOURCE_UNAVAILABLE, rur.getCode());
            } else if (this.mapUserAbortChoice.isProcedureCancellationReason()) {
                ProcedureCancellationReason pcr = this.mapUserAbortChoice.getProcedureCancellationReason();
                if (pcr == null)
                    throw new MAPException(
                            "Error encoding MAPUserAbortInfo: ProcedureCancellationReason value must not be null");
                asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, MAPUserAbortChoiceImpl.APPLICATION_PROCEDURE_CANCELLATION,
                        pcr.getCode());
            }

            if (this.extensionContainer != null)
                ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOutputStream);

        } catch (IOException e) {
            throw new MAPException("IOException when encoding MAPUserAbortInfo: " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding MAPUserAbortInfo: " + e.getMessage(), e);
        }
    }
}
