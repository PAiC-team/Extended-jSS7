
package org.restcomm.protocols.ss7.map.service.mobility.imei;

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
import org.restcomm.protocols.ss7.map.api.service.mobility.imei.CheckImeiResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.imei.EquipmentStatus;
import org.restcomm.protocols.ss7.map.api.service.mobility.imei.UESBIIu;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;
import org.restcomm.protocols.ss7.map.service.mobility.MobilityMessageImpl;

/**
 *
 * @author normandes
 *
 */
public class CheckImeiResponseImpl extends MobilityMessageImpl implements CheckImeiResponse {

    public static final int _ID_extensionContainer = 0;

    public static final String _PrimitiveName = "CheckImeiResponse";

    private EquipmentStatus equipmentStatus;
    private UESBIIu bmuef;
    private MAPExtensionContainer extensionContainer;

    private long mapProtocolVersion;

    // For incoming messages
    public CheckImeiResponseImpl(long mapProtocolVersion) {
        this.mapProtocolVersion = mapProtocolVersion;
    }

    // for outgoing messages
    public CheckImeiResponseImpl(long mapProtocolVersion, EquipmentStatus equipmentStatus, UESBIIu bmuef,
            MAPExtensionContainer extensionContainer) {
        this.mapProtocolVersion = mapProtocolVersion;
        this.equipmentStatus = equipmentStatus;
        this.bmuef = bmuef;
        this.extensionContainer = extensionContainer;
    }

    public long getMapProtocolVersion() {
        return this.mapProtocolVersion;
    }

    @Override
    public int getTag() throws MAPException {
        if (this.mapProtocolVersion >= 3) {
            return Tag.SEQUENCE;
        } else {
            return Tag.ENUMERATED;
        }
    }

    @Override
    public int getTagClass() {
        return Tag.CLASS_UNIVERSAL;
    }

    @Override
    public boolean getIsPrimitive() {
        if (this.mapProtocolVersion >= 3) {
            return false;
        } else {
            return true;
        }
    }

    @Override
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
        this.equipmentStatus = null;
        this.bmuef = null;
        this.extensionContainer = null;

        if (mapProtocolVersion >= 3) {
            AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
            int num = 0;
            while (true) {
                if (ais.available() == 0) {
                    break;
                }

                int tag = ais.readTag();

                if (ais.getTagClass() == Tag.CLASS_UNIVERSAL) {
                    switch (tag) {
                        case Tag.ENUMERATED:
                            // equipmentStatus
                            if (!ais.isTagPrimitive()) {
                                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                        + ".requestedEquipmentInfo: is not primitive",
                                        MAPParsingComponentExceptionReason.MistypedParameter);
                            }
                            int i1 = (int) ais.readInteger();
                            this.equipmentStatus = EquipmentStatus.getInstance(i1);
                            break;
                        case Tag.SEQUENCE:
                            // bmuef
                            if (ais.isTagPrimitive()) {
                                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                        + ".bmuef: is primitive", MAPParsingComponentExceptionReason.MistypedParameter);
                            }
                            this.bmuef = new UESBIIuImpl();
                            ((UESBIIuImpl) this.bmuef).decodeAll(ais);
                            break;
                        default:
                            ais.advanceElement();
                            break;
                    }
                } else if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                    switch (tag) {
                        case _ID_extensionContainer:
                            if (ais.isTagPrimitive()) {
                                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                        + ".extensionContainer: is primitive",
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

                num++;
            }
        } else {
            int i1 = (int) asnInputStream.readIntegerData(length);
            this.equipmentStatus = EquipmentStatus.getInstance(i1);
        }
    }

    @Override
    public void encodeAll(AsnOutputStream asnOutputStream) throws MAPException {
        this.encodeAll(asnOutputStream, this.getTagClass(), this.getTag());
    }

    @Override
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

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        try {
            if (mapProtocolVersion >= 3) {
                if (this.equipmentStatus != null) {
                    asnOutputStream.writeInteger(Tag.CLASS_UNIVERSAL, Tag.ENUMERATED, this.equipmentStatus.getCode());
                }
                if (this.bmuef != null) {
                    ((UESBIIuImpl) this.bmuef).encodeAll(asnOutputStream);
                }
                if (this.extensionContainer != null) {
                    ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC,
                            _ID_extensionContainer);
                }
            } else {
                if (this.equipmentStatus == null) {
                    throw new MAPException("equipmentStatus parameter must not be null at version 2");
                }

                asnOutputStream.writeIntegerData(this.equipmentStatus.getCode());
            }
        } catch (IOException e) {
            throw new MAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public MAPMessageType getMessageType() {
        return MAPMessageType.checkIMEI_Response;
    }

    @Override
    public int getOperationCode() {
        return MAPOperationCode.checkIMEI;
    }

    @Override
    public EquipmentStatus getEquipmentStatus() {
        return this.equipmentStatus;
    }

    @Override
    public UESBIIu getBmuef() {
        return bmuef;
    }

    @Override
    public MAPExtensionContainer getExtensionContainer() {
        return this.extensionContainer;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.equipmentStatus != null) {
            sb.append("equipmentStatus=");
            sb.append(this.equipmentStatus.toString());
            sb.append(", ");
        }

        if (this.bmuef != null) {
            sb.append("bmuef=");
            sb.append(bmuef.toString());
            sb.append(", ");
        }

        if (this.extensionContainer != null) {
            sb.append("extensionContainer=");
            sb.append(extensionContainer.toString());
            sb.append(", ");
        }

        sb.append("mapProtocolVersion=");
        sb.append(mapProtocolVersion);

        sb.append("]");

        return sb.toString();
    }

}
