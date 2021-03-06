package org.restcomm.protocols.ss7.map.service.lsm;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPMessageType;
import org.restcomm.protocols.ss7.map.api.MAPOperationCode;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.primitives.SubscriberIdentity;
import org.restcomm.protocols.ss7.map.api.service.lsm.SendRoutingInfoForLCSRequest;
import org.restcomm.protocols.ss7.map.primitives.ISDNAddressStringImpl;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;
import org.restcomm.protocols.ss7.map.primitives.SubscriberIdentityImpl;

/**
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public class SendRoutingInfoForLCSRequestImpl extends LsmMessageImpl implements SendRoutingInfoForLCSRequest {

    private static final int _TAG_MLC_NUMBER = 0;
    private static final int _TAG_TARGET_MS = 1;
    private static final int _TAG_EXTENSION_CONTAINER = 2;

    public static final String _PrimitiveName = "SendRoutingInfoForLCSRequest";

    private MAPExtensionContainer extensionContainer;
    private SubscriberIdentity targetMS;
    private ISDNAddressString mlcNumber;

    /**
     *
     */
    public SendRoutingInfoForLCSRequestImpl() {
        super();
    }

    /**
     * @param targetMS
     * @param mlcNumber
     */
    public SendRoutingInfoForLCSRequestImpl(ISDNAddressString mlcNumber, SubscriberIdentity targetMS) {
        super();
        this.targetMS = targetMS;
        this.mlcNumber = mlcNumber;
    }

    /**
     * @param extensionContainer
     * @param targetMS
     * @param mlcNumber
     */
    public SendRoutingInfoForLCSRequestImpl(ISDNAddressString mlcNumber, SubscriberIdentity targetMS, MAPExtensionContainer extensionContainer) {
        this(mlcNumber, targetMS);
        this.extensionContainer = extensionContainer;
    }

    public MAPMessageType getMessageType() {
        return MAPMessageType.sendRoutingInfoForLCS_Request;
    }

    public int getOperationCode() {
        return MAPOperationCode.sendRoutingInfoForLCS;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.lsm. SendRoutingInforForLCSRequestIndication#getMLCNumber()
     */
    public ISDNAddressString getMLCNumber() {
        return this.mlcNumber;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.lsm. SendRoutingInforForLCSRequestIndication#getTargetMS()
     */
    public SubscriberIdentity getTargetMS() {
        return this.targetMS;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.lsm. SendRoutingInforForLCSRequestIndication#getExtensionContainer()
     */
    public MAPExtensionContainer getExtensionContainer() {
        return this.extensionContainer;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.primitives.MAPAsnPrimitive#getTag()
     */
    public int getTag() throws MAPException {
        return Tag.SEQUENCE;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.primitives.MAPAsnPrimitive#getTagClass ()
     */
    public int getTagClass() {
        return Tag.CLASS_UNIVERSAL;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.primitives.MAPAsnPrimitive#getIsPrimitive ()
     */
    public boolean getIsPrimitive() {
        return false;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.primitives.MAPAsnPrimitive#decodeAll
     * (org.mobicents.protocols.asn.AsnInputStream)
     */
    public void decodeAll(AsnInputStream asnInputStream) throws MAPParsingComponentException {
        try {
            int length = asnInputStream.readLength();
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding " + _PrimitiveName + ": ", e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding " + _PrimitiveName + ": ", e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.primitives.MAPAsnPrimitive#decodeData
     * (org.mobicents.protocols.asn.AsnInputStream, int)
     */
    public void decodeData(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException {
        try {
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding " + _PrimitiveName + ": ", e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding " + _PrimitiveName + ": ", e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    private void _decode(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {
        this.extensionContainer = null;
        this.targetMS = null;
        this.mlcNumber = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);

        // mlcNumber [0] ISDN-AddressString,
        int tag = ais.readTag();

        if (ais.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC || !ais.isTagPrimitive() || tag != _TAG_MLC_NUMBER) {
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": Parameter [mlcNumber [0] ISDN-AddressString] bad tag class or not primitive or not Sequence",
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
        this.mlcNumber = new ISDNAddressStringImpl();
        ((ISDNAddressStringImpl) this.mlcNumber).decodeAll(ais);

        // targetMS [1] SubscriberIdentity
        tag = ais.readTag();
        if (ais.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC || ais.isTagPrimitive() || tag != _TAG_TARGET_MS) {
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": Parameter [targetMS [1] SubscriberIdentity] bad tag class or not primitive or not Sequence",
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }

        this.targetMS = new SubscriberIdentityImpl();
        AsnInputStream ais2 = ais.readSequenceStream();
        ais2.readTag();
        ((SubscriberIdentityImpl) this.targetMS).decodeAll(ais2);
        // this.targetMS = new SubscriberIdentityImpl();
        // int length1 = ais.readLength();
        // tag = ais.readTag();
        // ((SubscriberIdentityImpl)this.targetMS).decodeAll(ais);

        while (true) {
            if (ais.available() == 0)
                break;

            tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                    case _TAG_EXTENSION_CONTAINER:
                        if (ais.isTagPrimitive()) {
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter [extensionContainer [2] ExtensionContainer] is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);
                        }
                        this.extensionContainer = new MAPExtensionContainerImpl();
                        ((MAPExtensionContainerImpl) this.extensionContainer).decodeAll(ais);
                        break;
                    default:
                        ais.advanceElement();
                        break;
                }
            } else {
                ais.advanceElement();
            }
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.primitives.MAPAsnPrimitive#encodeAll
     * (org.mobicents.protocols.asn.AsnOutputStream)
     */
    public void encodeAll(AsnOutputStream asnOutputStream) throws MAPException {
        this.encodeAll(asnOutputStream, this.getTagClass(), this.getTag());
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.primitives.MAPAsnPrimitive#encodeAll
     * (org.mobicents.protocols.asn.AsnOutputStream, int, int)
     */
    public void encodeAll(AsnOutputStream asnOutputStream, int tagClass, int tag) throws MAPException {
        try {
            asnOutputStream.writeTag(tagClass, false, tag);
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
     * @see org.restcomm.protocols.ss7.map.api.primitives.MAPAsnPrimitive#encodeData
     * (org.mobicents.protocols.asn.AsnOutputStream)
     */
    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        if (this.mlcNumber == null) {
            throw new MAPException("Encoding of " + _PrimitiveName
                    + " failed. Mandatory parameter mlcNumber [0] ISDN-AddressString is not set");
        }

        if (this.targetMS == null) {
            throw new MAPException("Encoding of " + _PrimitiveName
                    + " failed. Mandatory parameter targetMS [1] SubscriberIdentity is not set");
        }

        ((ISDNAddressStringImpl) this.mlcNumber).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_MLC_NUMBER);

        try {
            asnOutputStream.writeTag(Tag.CLASS_CONTEXT_SPECIFIC, false, _TAG_TARGET_MS);
            int pos = asnOutputStream.StartContentDefiniteLength();
            ((SubscriberIdentityImpl) this.targetMS).encodeAll(asnOutputStream);
            asnOutputStream.FinalizeContent(pos);
        } catch (AsnException e) {
            throw new MAPException("AsnException while encoding parameter targetMS [1] SubscriberIdentity");
        }

        if (this.extensionContainer != null) {
            ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC,
                    _TAG_EXTENSION_CONTAINER);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.mlcNumber != null) {
            sb.append("mlcNumber");
            sb.append(this.mlcNumber);
        }
        if (this.targetMS != null) {
            sb.append(", targetMS=");
            sb.append(this.targetMS);
        }
        if (this.extensionContainer != null) {
            sb.append(", extensionContainer=");
            sb.append(this.extensionContainer);
        }

        sb.append("]");

        return sb.toString();
    }
}
