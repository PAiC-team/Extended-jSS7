
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
import org.restcomm.protocols.ss7.map.api.primitives.IMEI;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.imei.CheckImeiRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.imei.RequestedEquipmentInfo;
import org.restcomm.protocols.ss7.map.primitives.IMEIImpl;
import org.restcomm.protocols.ss7.map.primitives.IMSIImpl;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;
import org.restcomm.protocols.ss7.map.service.mobility.MobilityMessageImpl;

/**
 * @author normandes
 *
 */
public class CheckImeiRequestImpl extends MobilityMessageImpl implements CheckImeiRequest {

    public static final String _PrimitiveName = "CheckImeiRequest";

    private IMEI imei = null;
    private RequestedEquipmentInfo requestedEquipmentInfo = null;
    private MAPExtensionContainer extensionContainer = null;

    private IMSI imsi = null;

    private long mapProtocolVersion;
    private int encodedLength;

    // For incoming messages
    public CheckImeiRequestImpl(long mapProtocolVersion) {
        this.mapProtocolVersion = mapProtocolVersion;
    }

    // For outgoing messages
    public CheckImeiRequestImpl(long mapProtocolVersion, IMEI imei, RequestedEquipmentInfo requestedEquipmentInfo,
            MAPExtensionContainer extensionContainer) {
        this.mapProtocolVersion = mapProtocolVersion;
        this.imei = imei;
        this.requestedEquipmentInfo = requestedEquipmentInfo;
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
            return Tag.STRING_OCTET;
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

    public int getEncodedLength() {
        return encodedLength;
    }

    public IMSI getIMSI() {
        return imsi;
    }

    public void setIMSI(IMSI imsi) {
        this.imsi = imsi;
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
        this.imei = null;
        this.requestedEquipmentInfo = null;
        this.extensionContainer = null;
        this.imsi = null;

        if (mapProtocolVersion >= 3) {
            AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
            int num = 0;
            while (true) {
                if (ais.available() == 0) {
                    break;
                }

                int tag = ais.readTag();

                switch (num) {
                    case 0:
                        // imei
                        if (tag != Tag.STRING_OCTET || ais.getTagClass() != Tag.CLASS_UNIVERSAL || !ais.isTagPrimitive()) {
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".imei: bad tag or tag class or is not primitive: TagClass=" + ais.getTagClass()
                                    + ", tag=" + tag, MAPParsingComponentExceptionReason.MistypedParameter);
                        }
                        this.imei = new IMEIImpl();
                        ((IMEIImpl) this.imei).decodeAll(ais);
                        break;
                    case 1:
                        // requestedEquipmentInfo
                        if (tag != Tag.STRING_BIT || ais.getTagClass() != Tag.CLASS_UNIVERSAL || !ais.isTagPrimitive()) {
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".requestedEquipmentInfo: bad tag or tag class or is not primitive: TagClass="
                                    + ais.getTagClass() + ", tag=" + tag, MAPParsingComponentExceptionReason.MistypedParameter);
                        }
                        this.requestedEquipmentInfo = new RequestedEquipmentInfoImpl();
                        ((RequestedEquipmentInfoImpl) this.requestedEquipmentInfo).decodeAll(ais);
                        break;
                    default:
                        if (tag == Tag.SEQUENCE && ais.getTagClass() == Tag.CLASS_UNIVERSAL) {
                            if (ais.isTagPrimitive())
                                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                        + ": Parameter extensionContainer is primitive",
                                        MAPParsingComponentExceptionReason.MistypedParameter);
                            this.extensionContainer = new MAPExtensionContainerImpl();
                            ((MAPExtensionContainerImpl) this.extensionContainer).decodeAll(ais);
                        } else {
                            ais.advanceElement();
                        }
                        break;
                }

                num++;
            }

            if (num < 2)
                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                        + ": Needs at least 2 mandatory parameters, found " + num,
                        MAPParsingComponentExceptionReason.MistypedParameter);
        } else {
            this.imei = new IMEIImpl();
            ((IMEIImpl) this.imei).decodeData(asnInputStream, length);

            // To decode IMSI in Huawei package
            if (asnInputStream.available() != 0) {
                int tag = asnInputStream.readTag();
                length = asnInputStream.readLength();
                if (tag != 0 && asnInputStream.getTagClass() != Tag.CLASS_UNIVERSAL && !asnInputStream.isTagPrimitive()) {
                    throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                            + ".imsi: bad tag or tag class or is not primitive: TagClass=" + asnInputStream.getTagClass() + ", tag="
                            + tag, MAPParsingComponentExceptionReason.MistypedParameter);
                }

                this.imsi = new IMSIImpl();
                ((IMSIImpl) this.imsi).decodeData(asnInputStream, length);
            }
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
            if (this.imsi == null) {
                int pos = asnOutputStream.StartContentDefiniteLength();
                this.encodeData(asnOutputStream);
                asnOutputStream.FinalizeContent(pos);
            } else {
                AsnOutputStream tempAos = new AsnOutputStream();
                this.encodeData(tempAos);
                asnOutputStream.writeLength(this.getEncodedLength());
                this.encodeData(asnOutputStream);
            }
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        } catch (IOException e) {
            throw new MAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        if (this.imei == null) {
            throw new MAPException("imei parameter must not be null");
        }

        if (mapProtocolVersion >= 3) {
            if (this.requestedEquipmentInfo == null) {
                throw new MAPException("requestedEquipmentInfo parameter must not be null");
            }
            ((IMEIImpl) this.imei).encodeAll(asnOutputStream);
            ((RequestedEquipmentInfoImpl) this.requestedEquipmentInfo).encodeAll(asnOutputStream);
            if (this.extensionContainer != null)
                ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOutputStream);
        } else {
            ((IMEIImpl) this.imei).encodeData(asnOutputStream);
            encodedLength = asnOutputStream.size();

            if (imsi != null) {
                ((IMSIImpl) this.imsi).encodeAll(asnOutputStream, Tag.CLASS_UNIVERSAL, 0);
            }
        }
    }

    @Override
    public MAPMessageType getMessageType() {
        return MAPMessageType.checkIMEI_Request;
    }

    @Override
    public int getOperationCode() {
        return MAPOperationCode.checkIMEI;
    }

    @Override
    public IMEI getIMEI() {
        return this.imei;
    }

    @Override
    public RequestedEquipmentInfo getRequestedEquipmentInfo() {
        return this.requestedEquipmentInfo;
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

        if (this.imei != null) {
            sb.append("imei=");
            sb.append(imei.toString());
            sb.append(", ");
        }

        if (this.requestedEquipmentInfo != null) {
            sb.append("requestedEquipmentInfo=");
            sb.append(requestedEquipmentInfo.toString());
            sb.append(", ");
        }

        if (this.extensionContainer != null) {
            sb.append("extensionContainer=");
            sb.append(extensionContainer.toString());
            sb.append(", ");
        }

        if (this.imsi != null) {
            sb.append("imsi=");
            sb.append(imsi.toString());
            sb.append(", ");
        }

        sb.append("mapProtocolVersion=");
        sb.append(mapProtocolVersion);

        sb.append("]");

        return sb.toString();
    }

}
