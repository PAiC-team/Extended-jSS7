
package org.restcomm.protocols.ss7.map.service.mobility.authentication;

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
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.AuthenticationSetList;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.EpsAuthenticationSetList;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.SendAuthenticationInfoResponse;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;
import org.restcomm.protocols.ss7.map.service.mobility.MobilityMessageImpl;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class SendAuthenticationInfoResponseImpl extends MobilityMessageImpl implements SendAuthenticationInfoResponse {

    public static final int _TAG_General = 3;
    protected static final int _TAG_eps_AuthenticationSetList = 2;

    public static final String _PrimitiveName = "SendAuthenticationInfoResponse";

    private AuthenticationSetList authenticationSetList;
    private MAPExtensionContainer extensionContainer;
    private EpsAuthenticationSetList epsAuthenticationSetList;
    private long mapProtocolVersion;

    public SendAuthenticationInfoResponseImpl(long mapProtocolVersion) {
        this.mapProtocolVersion = mapProtocolVersion;
    }

    public SendAuthenticationInfoResponseImpl(long mapProtocolVersion, AuthenticationSetList authenticationSetList,
            MAPExtensionContainer extensionContainer, EpsAuthenticationSetList epsAuthenticationSetList) {
        this.mapProtocolVersion = mapProtocolVersion;
        this.authenticationSetList = authenticationSetList;
        this.extensionContainer = extensionContainer;
        this.epsAuthenticationSetList = epsAuthenticationSetList;

        if (authenticationSetList != null)
            ((AuthenticationSetListImpl) authenticationSetList).setMapProtocolVersion(mapProtocolVersion);
    }

    public MAPMessageType getMessageType() {
        return MAPMessageType.sendAuthenticationInfo_Response;
    }

    public int getOperationCode() {
        return MAPOperationCode.sendAuthenticationInfo;
    }

    public AuthenticationSetList getAuthenticationSetList() {
        return authenticationSetList;
    }

    public MAPExtensionContainer getExtensionContainer() {
        return extensionContainer;
    }

    public EpsAuthenticationSetList getEpsAuthenticationSetList() {
        return epsAuthenticationSetList;
    }

    public long getMapProtocolVersion() {
        return mapProtocolVersion;
    }

    public int getTag() throws MAPException {
        if (this.mapProtocolVersion >= 3)
            return SendAuthenticationInfoResponseImpl._TAG_General;
        else
            return Tag.SEQUENCE;
    }

    public int getTagClass() {
        if (this.mapProtocolVersion >= 3)
            return Tag.CLASS_CONTEXT_SPECIFIC;
        else
            return Tag.CLASS_UNIVERSAL;
    }

    public boolean getIsPrimitive() {
        return false;
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

        this.authenticationSetList = null;
        this.extensionContainer = null;
        this.epsAuthenticationSetList = null;

        if (mapProtocolVersion >= 3) {

            AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
            while (true) {
                if (ais.available() == 0)
                    break;

                int tag = ais.readTag();
                if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                    switch (tag) {
                        case AuthenticationSetListImpl._TAG_tripletList:
                        case AuthenticationSetListImpl._TAG_quintupletList:
                            // authenticationSetList
                            this.authenticationSetList = new AuthenticationSetListImpl();
                            ((AuthenticationSetListImpl) this.authenticationSetList).decodeAll(ais);
                            break;
                        case _TAG_eps_AuthenticationSetList:
                            // epsAuthenticationSetList
                            if (ais.isTagPrimitive())
                                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                        + ".epsAuthenticationSetList: Parameter epsAuthenticationSetList is primitive",
                                        MAPParsingComponentExceptionReason.MistypedParameter);
                            this.epsAuthenticationSetList = new EpsAuthenticationSetListImpl();
                            ((EpsAuthenticationSetListImpl) this.epsAuthenticationSetList).decodeAll(ais);
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

                        default:
                            ais.advanceElement();
                            break;
                    }
                } else {

                    ais.advanceElement();
                }
            }
        } else {
            this.authenticationSetList = new AuthenticationSetListImpl();
            ((AuthenticationSetListImpl) this.authenticationSetList).decodeData(asnInputStream, length);
        }
    }

    public void encodeAll(AsnOutputStream asnOutputStream) throws MAPException {
        this.encodeAll(asnOutputStream, this.getTagClass(), this.getTag());
    }

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

    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        if (this.mapProtocolVersion <= 2) {
            if (this.authenticationSetList == null)
                throw new MAPException("authenticationSetList must not be null for MAP Version2");
            ((AuthenticationSetListImpl) this.authenticationSetList).encodeData(asnOutputStream);

        } else {
            if (this.authenticationSetList == null && extensionContainer == null && epsAuthenticationSetList == null)
                throw new MAPException("At least one parameter must not be null for MAP Version3");

            if (this.authenticationSetList != null)
                ((AuthenticationSetListImpl) this.authenticationSetList).encodeAll(asnOutputStream);
            if (this.extensionContainer != null)
                ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOutputStream);
            if (this.epsAuthenticationSetList != null) {
                ((EpsAuthenticationSetListImpl) this.epsAuthenticationSetList).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC,
                        _TAG_eps_AuthenticationSetList);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SendAuthenticationInfoResponse [");

        if (this.authenticationSetList != null) {
            sb.append("authenticationSetList [");
            sb.append(authenticationSetList.toString());
            sb.append("], ");
        }
        if (this.extensionContainer != null) {
            sb.append("extensionContainer [");
            sb.append(extensionContainer.toString());
            sb.append("], ");
        }
        if (this.epsAuthenticationSetList != null) {
            sb.append("epsAuthenticationSetList [");
            sb.append(epsAuthenticationSetList.toString());
            sb.append("], ");
        }

        sb.append("]");

        return sb.toString();
    }
}
