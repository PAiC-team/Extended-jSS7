
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
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.LMSI;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.CancelLocationRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.CancellationType;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.IMSIWithLMSI;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.TypeOfUpdate;
import org.restcomm.protocols.ss7.map.primitives.IMSIImpl;
import org.restcomm.protocols.ss7.map.primitives.ISDNAddressStringImpl;
import org.restcomm.protocols.ss7.map.primitives.LMSIImpl;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;
import org.restcomm.protocols.ss7.map.service.mobility.MobilityMessageImpl;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class CancelLocationRequestImpl extends MobilityMessageImpl implements CancelLocationRequest {

    private static final int TAG_typeOfUpdate = 0;
    private static final int TAG_mtrfSupportedAndAuthorized = 1;
    private static final int TAG_mtrfSupportedAndNotAuthorized = 2;
    private static final int TAG_newMSCNumber = 3;
    private static final int TAG_newVLRNumber = 4;
    private static final int TAG_newLmsi = 5;

    public static final int TAG_cancelLocationRequest = 3;
    public static final String _PrimitiveName = "CancelLocationRequest";

    private IMSI imsi;
    private IMSIWithLMSI imsiWithLmsi;
    private CancellationType cancellationType;
    private MAPExtensionContainer extensionContainer;
    private TypeOfUpdate typeOfUpdate;
    private boolean mtrfSupportedAndAuthorized;
    private boolean mtrfSupportedAndNotAuthorized;
    private ISDNAddressString newMSCNumber;
    private ISDNAddressString newVLRNumber;
    private LMSI newLmsi;
    private long mapProtocolVersion;

    public CancelLocationRequestImpl(long mapProtocolVersion) {
        this.mapProtocolVersion = mapProtocolVersion;
    }

    public CancelLocationRequestImpl(IMSI imsi, IMSIWithLMSI imsiWithLmsi, CancellationType cancellationType,
            MAPExtensionContainer extensionContainer, TypeOfUpdate typeOfUpdate, boolean mtrfSupportedAndAuthorized,
            boolean mtrfSupportedAndNotAuthorized, ISDNAddressString newMSCNumber, ISDNAddressString newVLRNumber,
            LMSI newLmsi, long mapProtocolVersion) {
        super();
        this.imsi = imsi;
        this.imsiWithLmsi = imsiWithLmsi;
        this.cancellationType = cancellationType;
        this.extensionContainer = extensionContainer;
        this.typeOfUpdate = typeOfUpdate;
        this.mtrfSupportedAndAuthorized = mtrfSupportedAndAuthorized;
        this.mtrfSupportedAndNotAuthorized = mtrfSupportedAndNotAuthorized;
        this.newMSCNumber = newMSCNumber;
        this.newVLRNumber = newVLRNumber;
        this.newLmsi = newLmsi;
        this.mapProtocolVersion = mapProtocolVersion;
    }

    @Override
    public MAPMessageType getMessageType() {
        return MAPMessageType.cancelLocation_Request;
    }

    @Override
    public int getOperationCode() {
        return MAPOperationCode.cancelLocation;
    }

    @Override
    public IMSI getImsi() {
        return this.imsi;
    }

    @Override
    public IMSIWithLMSI getImsiWithLmsi() {
        return this.imsiWithLmsi;
    }

    @Override
    public CancellationType getCancellationType() {
        return this.cancellationType;
    }

    @Override
    public MAPExtensionContainer getExtensionContainer() {
        return this.extensionContainer;
    }

    @Override
    public TypeOfUpdate getTypeOfUpdate() {
        return this.typeOfUpdate;
    }

    @Override
    public boolean getMtrfSupportedAndAuthorized() {
        return this.mtrfSupportedAndAuthorized;
    }

    @Override
    public boolean getMtrfSupportedAndNotAuthorized() {
        return this.mtrfSupportedAndNotAuthorized;
    }

    @Override
    public ISDNAddressString getNewMSCNumber() {
        return this.newMSCNumber;
    }

    @Override
    public ISDNAddressString getNewVLRNumber() {
        return this.newVLRNumber;
    }

    @Override
    public LMSI getNewLmsi() {
        return this.newLmsi;
    }

    @Override
    public int getTag() throws MAPException {
        if (this.mapProtocolVersion == 3) {
            return TAG_cancelLocationRequest;
        } else {
            if (imsi != null) {
                return Tag.STRING_OCTET;
            } else {
                return Tag.SEQUENCE;
            }
        }
    }

    @Override
    public int getTagClass() {
        if (this.mapProtocolVersion == 3) {
            return Tag.CLASS_CONTEXT_SPECIFIC;
        } else {
            return Tag.CLASS_UNIVERSAL;
        }

    }

    @Override
    public boolean getIsPrimitive() {
        if (mapProtocolVersion < 3 && this.imsi != null) {
            return true;
        }
        return false;
    }

    @Override
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

    @Override
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
        this.imsi = null;
        this.imsiWithLmsi = null;
        this.cancellationType = null;
        this.extensionContainer = null;
        this.typeOfUpdate = null;
        this.mtrfSupportedAndAuthorized = false;
        this.mtrfSupportedAndNotAuthorized = false;
        this.newMSCNumber = null;
        this.newVLRNumber = null;
        this.newLmsi = null;

        if (this.mapProtocolVersion >= 3) {
            AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
            int num = 0;
            while (true) {
                if (ais.available() == 0)
                    break;

                int tag = ais.readTag();

                if (num == 0) {
                    if (tag == Tag.STRING_OCTET && ais.getTagClass() == Tag.CLASS_UNIVERSAL) {
                        if (!ais.isTagPrimitive()) {
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".imsi: is not primitive", MAPParsingComponentExceptionReason.MistypedParameter);
                        }
                        this.imsi = new IMSIImpl();
                        ((IMSIImpl) this.imsi).decodeAll(ais);
                    } else if (tag == Tag.SEQUENCE && ais.getTagClass() == Tag.CLASS_UNIVERSAL && !ais.isTagPrimitive()) {
                        if (ais.isTagPrimitive()) {
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".imsiWithLmsi: is primitive", MAPParsingComponentExceptionReason.MistypedParameter);
                        }
                        this.imsiWithLmsi = new IMSIWithLMSIImpl();
                        ((IMSIWithLMSIImpl) this.imsiWithLmsi).decodeAll(ais);
                    } else {
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ": Bad tag or tag class for the parameter Identity",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    }
                } else {
                    switch (ais.getTagClass()) {
                        case Tag.CLASS_UNIVERSAL:
                            switch (tag) {
                                case Tag.ENUMERATED:
                                    if (!ais.isTagPrimitive()) {
                                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                                + ".cancellationType: is not primitive",
                                                MAPParsingComponentExceptionReason.MistypedParameter);
                                    }
                                    this.cancellationType = CancellationType.getInstance((int) ais.readInteger());
                                    break;
                                case Tag.SEQUENCE:
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
                            break;

                        case Tag.CLASS_CONTEXT_SPECIFIC:
                            switch (tag) {
                                case CancelLocationRequestImpl.TAG_typeOfUpdate:
                                    if (!ais.isTagPrimitive()) {
                                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                                + ".typeOfUpdate: is not primitive",
                                                MAPParsingComponentExceptionReason.MistypedParameter);
                                    }
                                    this.typeOfUpdate = TypeOfUpdate.getInstance((int) ais.readInteger());
                                    break;
                                case CancelLocationRequestImpl.TAG_mtrfSupportedAndAuthorized:
                                    if (!ais.isTagPrimitive()) {
                                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                                + ".mtrfSupportedAndAuthorized: is not primitive",
                                                MAPParsingComponentExceptionReason.MistypedParameter);
                                    }
                                    ais.readNull();
                                    this.mtrfSupportedAndAuthorized = true;
                                    break;
                                case CancelLocationRequestImpl.TAG_mtrfSupportedAndNotAuthorized:
                                    if (!ais.isTagPrimitive()) {
                                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                                + ".mtrfSupportedAndNotAuthorized: is not primitive",
                                                MAPParsingComponentExceptionReason.MistypedParameter);
                                    }
                                    ais.readNull();
                                    this.mtrfSupportedAndNotAuthorized = true;
                                    break;
                                case CancelLocationRequestImpl.TAG_newMSCNumber:
                                    if (!ais.isTagPrimitive()) {
                                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                                + ".newMSCNumber: is not primitive",
                                                MAPParsingComponentExceptionReason.MistypedParameter);
                                    }
                                    this.newMSCNumber = new ISDNAddressStringImpl();
                                    ((ISDNAddressStringImpl) this.newMSCNumber).decodeAll(ais);
                                    break;
                                case CancelLocationRequestImpl.TAG_newVLRNumber:
                                    if (!ais.isTagPrimitive()) {
                                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                                + ".newVLRNumber: is not primitive",
                                                MAPParsingComponentExceptionReason.MistypedParameter);
                                    }
                                    this.newVLRNumber = new ISDNAddressStringImpl();
                                    ((ISDNAddressStringImpl) this.newVLRNumber).decodeAll(ais);
                                    break;
                                case CancelLocationRequestImpl.TAG_newLmsi:
                                    if (!ais.isTagPrimitive()) {
                                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                                + ".newLmsi: is not primitive",
                                                MAPParsingComponentExceptionReason.MistypedParameter);
                                    }
                                    this.newLmsi = new LMSIImpl();
                                    ((LMSIImpl) this.newLmsi).decodeAll(ais);
                                    break;
                                default:
                                    ais.advanceElement();
                                    break;
                            }
                            break;

                        default:
                            ais.advanceElement();
                            break;
                    }
                }

                num++;
            }
            if (num < 1)
                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                        + ": Needs at least 1 mandatory parameters, found " + num,
                        MAPParsingComponentExceptionReason.MistypedParameter);

        } else {
            int tag = asnInputStream.getTag();
            if (tag == Tag.STRING_OCTET && asnInputStream.getTagClass() == Tag.CLASS_UNIVERSAL) {
                if (!asnInputStream.isTagPrimitive()) {
                    throw new MAPParsingComponentException(
                            "Error while decoding " + _PrimitiveName + ".imsi: is not primitive",
                            MAPParsingComponentExceptionReason.MistypedParameter);
                }
                this.imsi = new IMSIImpl();
                ((IMSIImpl) this.imsi).decodeData(asnInputStream, length);
            } else if (tag == Tag.SEQUENCE && asnInputStream.getTagClass() == Tag.CLASS_UNIVERSAL) {
                if (asnInputStream.isTagPrimitive()) {
                    throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                            + ".imsiWithLmsi: is primitive", MAPParsingComponentExceptionReason.MistypedParameter);
                }
                this.imsiWithLmsi = new IMSIWithLMSIImpl();
                ((IMSIWithLMSIImpl) this.imsiWithLmsi).decodeData(asnInputStream, length);
            }
        }
    }

    @Override
    public void encodeAll(AsnOutputStream asnOutputStream) throws MAPException {
        try {
            this.encodeAll(asnOutputStream, this.getTagClass(), this.getTag());
        } catch (Exception e) {
            e.printStackTrace();
            throw new MAPException(e);
        }

    }

    @Override
    public void encodeAll(AsnOutputStream asnOutputStream, int tagClass, int tag) throws MAPException {
        try {
            asnOutputStream.writeTag(tagClass, this.getIsPrimitive(), tag);
            int pos = asnOutputStream.StartContentDefiniteLength();
            this.encodeData(asnOutputStream);
            asnOutputStream.FinalizeContent(pos);

        } catch (AsnException e) {
            e.printStackTrace();
            throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }

    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        if (this.imsi == null && this.imsiWithLmsi == null) {
            throw new MAPException("Error while encoding " + _PrimitiveName
                    + " the mandatory parameter imsi / imsiWithLmsi is not defined");
        }

        if (mapProtocolVersion >= 3) {

            if (this.imsi != null) {
                ((IMSIImpl) this.imsi).encodeAll(asnOutputStream);
            } else {
                ((IMSIWithLMSIImpl) this.imsiWithLmsi).encodeAll(asnOutputStream);
            }

            if (this.cancellationType != null) {
                try {
                    asnOutputStream.writeInteger(Tag.CLASS_UNIVERSAL, Tag.ENUMERATED, this.cancellationType.getCode());
                } catch (IOException e) {
                    throw new MAPException("IOException while encoding " + _PrimitiveName + " parameter cancellationType", e);
                } catch (AsnException e) {
                    throw new MAPException("IOException while encoding " + _PrimitiveName + " parameter cancellationType", e);
                }
            }

            if (this.extensionContainer != null) {
                ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOutputStream);
            }

            if (this.typeOfUpdate != null) {
                try {
                    asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, CancelLocationRequestImpl.TAG_typeOfUpdate,
                            this.typeOfUpdate.getCode());
                } catch (IOException e) {
                    throw new MAPException("IOException while encoding " + _PrimitiveName + " parameter typeOfUpdate", e);
                } catch (AsnException e) {
                    throw new MAPException("IOException while encoding " + _PrimitiveName + " parameter typeOfUpdate", e);
                }
            }

            if (this.mtrfSupportedAndAuthorized) {
                try {
                    asnOutputStream.writeNull(Tag.CLASS_CONTEXT_SPECIFIC, TAG_mtrfSupportedAndAuthorized);
                } catch (IOException e) {
                    throw new MAPException("IOException while encoding " + _PrimitiveName
                            + " parameter mtrfSupportedAndAuthorized", e);
                } catch (AsnException e) {
                    throw new MAPException("AsnException while encoding " + _PrimitiveName
                            + " parameter mtrfSupportedAndAuthorized", e);
                }
            }

            if (this.mtrfSupportedAndNotAuthorized) {
                try {
                    asnOutputStream.writeNull(Tag.CLASS_CONTEXT_SPECIFIC, TAG_mtrfSupportedAndNotAuthorized);
                } catch (IOException e) {
                    throw new MAPException("IOException while encoding " + _PrimitiveName
                            + " parameter mtrfSupportedAndNotAuthorized", e);
                } catch (AsnException e) {
                    throw new MAPException("AsnException while encoding " + _PrimitiveName
                            + " parameter mtrfSupportedAndNotAuthorized", e);
                }
            }

            if (this.newMSCNumber != null) {
                ((ISDNAddressStringImpl) this.newMSCNumber).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, TAG_newMSCNumber);
            }

            if (this.newVLRNumber != null) {
                ((ISDNAddressStringImpl) this.newVLRNumber).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, TAG_newVLRNumber);
            }

            if (this.newLmsi != null) {
                ((LMSIImpl) this.newLmsi).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, TAG_newLmsi);
            }

        } else {
            if (this.imsi != null) {
                ((IMSIImpl) this.imsi).encodeData(asnOutputStream);
            } else {
                ((IMSIWithLMSIImpl) this.imsiWithLmsi).encodeData(asnOutputStream);
            }

        }

    }

    @Override
    public long getMapProtocolVersion() {
        return this.mapProtocolVersion;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.imsi != null) {
            sb.append("imsi=");
            sb.append(imsi.toString());
            sb.append(", ");
        }
        if (this.imsiWithLmsi != null) {
            sb.append("imsiWithLmsi=");
            sb.append(imsiWithLmsi.toString());
            sb.append(", ");
        }
        if (this.cancellationType != null) {
            sb.append("cancellationType=");
            sb.append(cancellationType.toString());
            sb.append(", ");
        }
        if (this.extensionContainer != null) {
            sb.append("extensionContainer=");
            sb.append(extensionContainer.toString());
            sb.append(", ");
        }
        if (this.typeOfUpdate != null) {
            sb.append("typeOfUpdate=");
            sb.append(typeOfUpdate.toString());
            sb.append(", ");
        }
        if (this.mtrfSupportedAndAuthorized) {
            sb.append("mtrfSupportedAndAuthorized, ");
        }
        if (this.mtrfSupportedAndNotAuthorized) {
            sb.append("mtrfSupportedAndNotAuthorized, ");
        }
        if (this.newMSCNumber != null) {
            sb.append("newMSCNumber=");
            sb.append(newMSCNumber.toString());
            sb.append(", ");
        }
        if (this.newVLRNumber != null) {
            sb.append("newVLRNumber=");
            sb.append(newVLRNumber.toString());
            sb.append(", ");
        }
        if (this.newLmsi != null) {
            sb.append("newLmsi=");
            sb.append(newLmsi.toString());
            sb.append(", ");
        }

        sb.append("mapProtocolVersion=");
        sb.append(mapProtocolVersion);

        sb.append("]");

        return sb.toString();
    }

}
