
package org.restcomm.protocols.ss7.map.service.mobility.locationManagement;

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
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.UpdateLocationResponse;
import org.restcomm.protocols.ss7.map.primitives.ISDNAddressStringImpl;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;
import org.restcomm.protocols.ss7.map.service.mobility.MobilityMessageImpl;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class UpdateLocationResponseImpl extends MobilityMessageImpl implements UpdateLocationResponse {

    protected static final int _TAG_pagingArea_Capability = 0;

    public static final String _PrimitiveName = "UpdateLocationResponse";

    private ISDNAddressString hlrNumber;
    private MAPExtensionContainer extensionContainer;
    private boolean addCapability;
    private boolean pagingAreaCapability;
    private long mapProtocolVersion;

    public UpdateLocationResponseImpl(long mapProtocolVersion) {
        this.mapProtocolVersion = mapProtocolVersion;
    }

    public UpdateLocationResponseImpl(long mapProtocolVersion, ISDNAddressString hlrNumber,
            MAPExtensionContainer extensionContainer, boolean addCapability, boolean pagingAreaCapability) {
        this.mapProtocolVersion = mapProtocolVersion;
        this.hlrNumber = hlrNumber;
        this.extensionContainer = extensionContainer;
        this.addCapability = addCapability;
        this.pagingAreaCapability = pagingAreaCapability;
    }

    public MAPMessageType getMessageType() {
        return MAPMessageType.updateLocation_Response;
    }

    public int getOperationCode() {
        return MAPOperationCode.updateLocation;
    }

    public ISDNAddressString getHlrNumber() {
        return hlrNumber;
    }

    public MAPExtensionContainer getExtensionContainer() {
        return extensionContainer;
    }

    public boolean getAddCapability() {
        return addCapability;
    }

    public boolean getPagingAreaCapability() {
        return pagingAreaCapability;
    }

    public long getMapProtocolVersion() {
        return mapProtocolVersion;
    }

    public int getTag() throws MAPException {
        if (this.mapProtocolVersion >= 2)
            return Tag.SEQUENCE;
        else
            return Tag.STRING_OCTET;
    }

    public int getTagClass() {
        return Tag.CLASS_UNIVERSAL;
    }

    public boolean getIsPrimitive() {
        if (this.mapProtocolVersion >= 2)
            return false;
        else
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

    private void _decode(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {
        hlrNumber = null;
        extensionContainer = null;
        addCapability = false;
        pagingAreaCapability = false;

        if (this.mapProtocolVersion >= 2) {
            AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
            int num = 0;
            while (true) {
                if (ais.available() == 0)
                    break;

                int tag = ais.readTag();

                switch (num) {
                    case 0:
                        // hlrNumber
                        if (ais.getTagClass() != Tag.CLASS_UNIVERSAL || !ais.isTagPrimitive() || tag != Tag.STRING_OCTET)
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".hlrNumber: Parameter 0 bad tag or tag class or not primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);
                        this.hlrNumber = new ISDNAddressStringImpl();
                        ((ISDNAddressStringImpl) this.hlrNumber).decodeAll(ais);
                        break;

                    default:
                        if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                            switch (tag) {
                                case _TAG_pagingArea_Capability:
                                    // pagingAreaCapability
                                    if (!ais.isTagPrimitive())
                                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                                + ".pagingAreaCapability: Parameter is not primitive",
                                                MAPParsingComponentExceptionReason.MistypedParameter);
                                    ais.readNull();
                                    this.pagingAreaCapability = true;
                                    break;

                                default:
                                    ais.advanceElement();
                                    break;
                            }
                        } else if (ais.getTagClass() == Tag.CLASS_UNIVERSAL) {

                            switch (tag) {
                                case Tag.SEQUENCE:
                                    // extensionContainer
                                    if (ais.isTagPrimitive())
                                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                                + ".extensionContainer: Parameter extensionContainer is primitive",
                                                MAPParsingComponentExceptionReason.MistypedParameter);
                                    this.extensionContainer = new MAPExtensionContainerImpl();
                                    ((MAPExtensionContainerImpl) this.extensionContainer).decodeAll(ais);
                                    break;
                                case Tag.NULL:
                                    // addCapability
                                    if (!ais.isTagPrimitive())
                                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                                + ".addCapability: Parameter is not primitive",
                                                MAPParsingComponentExceptionReason.MistypedParameter);
                                    ais.readNull();
                                    this.addCapability = true;
                                    break;

                                default:
                                    ais.advanceElement();
                                    break;
                            }
                        } else {

                            ais.advanceElement();
                        }
                        break;
                }

                num++;
            }

            if (num < 1)
                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                        + ": Needs at least 1 mandatory parameters, found " + num,
                        MAPParsingComponentExceptionReason.MistypedParameter);
        } else {

            this.hlrNumber = new ISDNAddressStringImpl();
            ((ISDNAddressStringImpl) this.hlrNumber).decodeData(asnInputStream, length);
        }
    }

    public void encodeAll(AsnOutputStream asnOutputStream) throws MAPException {
        this.encodeAll(asnOutputStream, this.getTagClass(), this.getTag());
    }

    public void encodeAll(AsnOutputStream asnOutputStream, int tagClass, int tag) throws MAPException {

        try {
            asnOutputStream.writeTag(tagClass, this.getIsPrimitive(), tag);
            int pos = asnOutputStream.StartContentDefiniteLength();
            this.encodeData(asnOutputStream);
            asnOutputStream.FinalizeContent(pos);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {

        if (this.hlrNumber == null)
            throw new MAPException("hlrNumber parameter must not be null");

        try {
            if (this.mapProtocolVersion >= 2) {

                ((ISDNAddressStringImpl) this.hlrNumber).encodeAll(asnOutputStream);

                if (this.extensionContainer != null)
                    ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOutputStream);
                if (addCapability)
                    asnOutputStream.writeNull();
                if (pagingAreaCapability)
                    asnOutputStream.writeNull(Tag.CLASS_CONTEXT_SPECIFIC, _TAG_pagingArea_Capability);
            } else {

                ((ISDNAddressStringImpl) this.hlrNumber).encodeData(asnOutputStream);
            }
        } catch (IOException e) {
            throw new MAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UpdateLocationResponse [");

        if (this.hlrNumber != null) {
            sb.append("hlrNumber=");
            sb.append(hlrNumber.toString());
            sb.append(", ");
        }
        if (this.extensionContainer != null) {
            sb.append("extensionContainer=");
            sb.append(extensionContainer.toString());
            sb.append(", ");
        }
        if (this.addCapability) {
            sb.append("addCapability, ");
        }
        if (this.pagingAreaCapability) {
            sb.append("pagingAreaCapability, ");
        }
        sb.append("mapProtocolVersion=");
        sb.append(mapProtocolVersion);

        sb.append("]");

        return sb.toString();
    }
}
