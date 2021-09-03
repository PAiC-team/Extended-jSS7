
package org.restcomm.protocols.ss7.map.dialog;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.primitives.AddressStringImpl;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;

/**
<code>
MAP-OpenInfo ::= SEQUENCE {
  destinationReference [0] AddressString OPTIONAL,
  originationReference [1] AddressString OPTIONAL,
-- WS modification: Ericsson proprietary fields
  callingMsisdn        [2] AddressString OPTIONAL,
  callingVlrAddress    [3] AddressString OPTIONAL,
-- WS modification: Ericsson proprietary fields
  ...,
  extensionContainer ExtensionContainer OPTIONAL
  -- extensionContainer must not be used in version 2
}

Another (old) version of Ericsson :
MAP OpenInfo ::= SEQUENCE ( imsi (0) IMSI OPTIONAL, originationReference (1) AddressString OPTIONAL,
  msisdn (2) AddressString, ... vlrNo (3) AddressString OPTIONAL )
<code>

 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public class MAPOpenInfoImpl implements MAPAsnPrimitive {

    public static final int MAP_OPEN_INFO_TAG = 0x00;

    protected static final int DESTINATION_REF_TAG = 0x00;
    protected static final int ORIGINATION_REF_TAG = 0x01;
    protected static final int ERI_MSISDN_TAG = 0x02;
    protected static final int ERI_NLR_NO_TAG = 0x03;

    protected static final int OPEN_INFO_TAG_CLASS = Tag.CLASS_CONTEXT_SPECIFIC;
    protected static final boolean OPEN_INFO_TAG_PC_PRIMITIVE = true;
    protected static final boolean OPEN_INFO_TAG_PC_CONSTRUCTED = false;

    private AddressString destReference;
    private AddressString origReference;
    private MAPExtensionContainer extensionContainer;

    private boolean ericssonStyle;
    private AddressString ericssonMsisdn;
    private AddressString ericssonVlrNo;

    public AddressString getDestReference() {
        return this.destReference;
    }

    public AddressString getOrigReference() {
        return this.origReference;
    }

    public MAPExtensionContainer getExtensionContainer() {
        return extensionContainer;
    }

    public boolean getEricssonStyle() {
        return this.ericssonStyle;
    }

    public AddressString getEricssonMsisdn() {
        return ericssonMsisdn;
    }

    public AddressString getEricssonVlrNo() {
        return ericssonVlrNo;
    }

    public void setDestReference(AddressString destReference) {
        this.destReference = destReference;
    }

    public void setOrigReference(AddressString origReference) {
        this.origReference = origReference;
    }

    public void setExtensionContainer(MAPExtensionContainer extensionContainer) {
        this.extensionContainer = extensionContainer;
    }

    public void setEricssonStyle(boolean ericssonStyle) {
        this.ericssonStyle = ericssonStyle;
    }

    public void setEricssonMsisdn(AddressString ericssonMsisdn) {
        this.ericssonMsisdn = ericssonMsisdn;
    }

    public void setEricssonVlrNo(AddressString ericssonVlrNo) {
        this.ericssonVlrNo = ericssonVlrNo;
    }

    public int getTag() throws MAPException {
        return MAP_OPEN_INFO_TAG;
    }

    public int getTagClass() {
        return Tag.CLASS_CONTEXT_SPECIFIC;
    }

    public boolean getIsPrimitive() {
        return false;
    }

    public void decodeAll(AsnInputStream ansIS) throws MAPParsingComponentException {

        try {
            int length = ansIS.readLength();
            this._decode(ansIS, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding MAPOpenInfo: " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding MAPOpenInfo: " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    public void decodeData(AsnInputStream ansIS, int length) throws MAPParsingComponentException {

        try {
            this._decode(ansIS, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding MAPOpenInfo: " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding MAPOpenInfo: " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    private void _decode(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {
        this.destReference = null;
        this.origReference = null;
        this.extensionContainer = null;
        this.ericssonStyle = false;
        this.ericssonMsisdn = null;
        this.ericssonVlrNo = null;

        AsnInputStream localAis = asnInputStream.readSequenceStreamData(length);

        // checking for Ericsson-style
        int startPos = localAis.position();
        while (localAis.available() > 0) {
            int tag = localAis.readTag();
            if (localAis.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC && tag == ERI_MSISDN_TAG) {
                this.ericssonStyle = true;
                break;
            }
            localAis.advanceElement();
        }

        // parsing
        localAis.position(startPos);
        while (localAis.available() > 0) {
            int tag = localAis.readTag();

            switch (localAis.getTagClass()) {
                case Tag.CLASS_CONTEXT_SPECIFIC:
                    switch (tag) {
                        case DESTINATION_REF_TAG:
                            // if (this.ericssonStyle) {
                            // this.eriImsi = new AddressStringImpl();
                            // ((AddressStringImpl) this.eriImsi).decodeAll(localAis);
                            // } else {

                            this.destReference = new AddressStringImpl();
                            ((AddressStringImpl) this.destReference).decodeAll(localAis);

                            // }
                            break;

                        case ORIGINATION_REF_TAG:
                            this.origReference = new AddressStringImpl();
                            ((AddressStringImpl) this.origReference).decodeAll(localAis);
                            break;

                        case ERI_MSISDN_TAG:
                            this.ericssonMsisdn = new AddressStringImpl();
                            ((AddressStringImpl) this.ericssonMsisdn).decodeAll(localAis);
                            break;

                        case ERI_NLR_NO_TAG:
                            this.ericssonVlrNo = new AddressStringImpl();
                            ((AddressStringImpl) this.ericssonVlrNo).decodeAll(localAis);
                            break;

                        default:
                            localAis.advanceElement();
                            break;
                    }
                    break;

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

        this.encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, MAP_OPEN_INFO_TAG);
    }

    public void encodeAll(AsnOutputStream asnOutputStream, int tagClass, int tag) throws MAPException {

        try {
            asnOutputStream.writeTag(tagClass, false, tag);
            int pos = asnOutputStream.StartContentDefiniteLength();
            this.encodeData(asnOutputStream);
            asnOutputStream.FinalizeContent(pos);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding MAPOpenInfo: " + e.getMessage(), e);
        }
    }

    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {

        if (this.ericssonStyle) {
            if (this.destReference != null)
                ((AddressStringImpl) this.destReference).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, DESTINATION_REF_TAG);
            if (this.origReference != null)
                ((AddressStringImpl) this.origReference).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, ORIGINATION_REF_TAG);
            if (this.ericssonMsisdn != null)
                ((AddressStringImpl) this.ericssonMsisdn).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, ERI_MSISDN_TAG);
            if (this.ericssonVlrNo != null)
                ((AddressStringImpl) this.ericssonVlrNo).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, ERI_NLR_NO_TAG);
        } else {

            if (this.destReference != null)
                ((AddressStringImpl) this.destReference).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, DESTINATION_REF_TAG);

            if (this.origReference != null)
                ((AddressStringImpl) this.origReference).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, ORIGINATION_REF_TAG);

            if (this.extensionContainer != null)
                ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOutputStream);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("MAPOpenInfo[");
        if (destReference != null) {
            sb.append("destReference=");
            sb.append(destReference);
        }
        if (origReference != null) {
            sb.append(", origReference=");
            sb.append(origReference);
        }
        if (extensionContainer != null) {
            sb.append("extensionContainer=");
            sb.append(extensionContainer);
        }
        if (ericssonStyle) {
            sb.append(", ericssonStyle");
        }
        if (ericssonMsisdn != null) {
            sb.append(", ericssonMsisdn=");
            sb.append(ericssonMsisdn);
        }
        if (ericssonVlrNo != null) {
            sb.append(", ericssonVlrNo=");
            sb.append(ericssonVlrNo);
        }
        sb.append("]");

        return sb.toString();
    }

}
