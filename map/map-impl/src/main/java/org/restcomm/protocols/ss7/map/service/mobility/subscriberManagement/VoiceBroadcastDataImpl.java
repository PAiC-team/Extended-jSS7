
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.GroupId;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.LongGroupId;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.VoiceBroadcastData;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;
import org.restcomm.protocols.ss7.map.primitives.SequenceBase;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class VoiceBroadcastDataImpl extends SequenceBase implements VoiceBroadcastData {

    private static final int _TAG_longGroupId = 0;

    private GroupId groupId;
    private boolean broadcastInitEntitlement;
    private MAPExtensionContainer extensionContainer;
    private LongGroupId longGroupId;

    public VoiceBroadcastDataImpl() {
        super("VoiceBroadcastData");
    }

    public VoiceBroadcastDataImpl(GroupId groupId, boolean broadcastInitEntitlement, MAPExtensionContainer extensionContainer,
            LongGroupId longGroupId) {
        super("VoiceBroadcastData");
        this.groupId = groupId;
        this.broadcastInitEntitlement = broadcastInitEntitlement;
        this.extensionContainer = extensionContainer;
        this.longGroupId = longGroupId;
    }

    @Override
    public GroupId getGroupId() {
        return this.groupId;
    }

    @Override
    public boolean getBroadcastInitEntitlement() {
        return this.broadcastInitEntitlement;
    }

    @Override
    public MAPExtensionContainer getExtensionContainer() {
        return this.extensionContainer;
    }

    @Override
    public LongGroupId getLongGroupId() {
        return this.longGroupId;
    }

    @Override
    protected void _decode(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {
        this.groupId = null;
        this.broadcastInitEntitlement = false;
        this.extensionContainer = null;
        this.longGroupId = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);

        int num = 0;
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            switch (num) {
                case 0:
                    if (!ais.isTagPrimitive() || tag != Tag.STRING_OCTET || ais.getTagClass() != Tag.CLASS_UNIVERSAL)
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".groupId: Bad tag , tag classs or Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.groupId = new GroupIdImpl();
                    ((GroupIdImpl) this.groupId).decodeAll(ais);
                    break;
                default:
                    switch (ais.getTagClass()) {
                        case Tag.CLASS_UNIVERSAL: {
                            switch (tag) {
                                case Tag.NULL:
                                    if (!ais.isTagPrimitive())
                                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                                + ".broadcastInitEntitlement: Parameter is not primitive",
                                                MAPParsingComponentExceptionReason.MistypedParameter);
                                    ais.readNull();
                                    this.broadcastInitEntitlement = true;
                                    break;
                                case Tag.SEQUENCE:
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
                        }
                            break;
                        case Tag.CLASS_CONTEXT_SPECIFIC: {
                            switch (tag) {
                                case _TAG_longGroupId:
                                    if (!ais.isTagPrimitive())
                                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                                + ".longGroupId: Parameter not primitive",
                                                MAPParsingComponentExceptionReason.MistypedParameter);
                                    this.longGroupId = new LongGroupIdImpl();
                                    ((LongGroupIdImpl) this.longGroupId).decodeAll(ais);
                                    break;
                                default:
                                    ais.advanceElement();
                                    break;
                            }
                        }
                            break;
                        default:
                            ais.advanceElement();
                            break;
                    }
                    break;
            }
            num++;
        }

        if (this.groupId == null) {
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": Parament groupId is mandatory but does not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        if (this.groupId == null && this.longGroupId == null)
            throw new MAPException("Error while encoding" + _PrimitiveName + ": groupId must not be null");

        try {
            if (this.longGroupId != null) {
                (new GroupIdImpl("")).encodeAll(asnOutputStream, Tag.CLASS_UNIVERSAL, Tag.STRING_OCTET);
            } else {
                ((GroupIdImpl) this.groupId).encodeAll(asnOutputStream, Tag.CLASS_UNIVERSAL, Tag.STRING_OCTET);
            }

            if (broadcastInitEntitlement)
                asnOutputStream.writeNull();

            if (this.extensionContainer != null)
                ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOutputStream, Tag.CLASS_UNIVERSAL, Tag.SEQUENCE);

            if (this.longGroupId != null)
                ((LongGroupIdImpl) this.longGroupId).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_longGroupId);

        } catch (IOException e) {
            throw new MAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName + " [");

        if (this.groupId != null) {
            sb.append("groupId=");
            sb.append(this.groupId.toString());
            sb.append(", ");
        }

        if (this.broadcastInitEntitlement) {
            sb.append("broadcastInitEntitlement, ");
        }

        if (this.extensionContainer != null) {
            sb.append("extensionContainer=");
            sb.append(this.extensionContainer.toString());
            sb.append(", ");
        }

        if (this.longGroupId != null) {
            sb.append("longGroupId=");
            sb.append(this.longGroupId.toString());
            sb.append(" ");
        }

        sb.append("]");
        return sb.toString();
    }

}
