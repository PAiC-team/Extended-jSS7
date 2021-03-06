
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
import org.restcomm.protocols.ss7.map.api.primitives.LAIFixedLength;
import org.restcomm.protocols.ss7.map.api.primitives.LMSI;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.primitives.TMSI;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.SendIdentificationRequest;
import org.restcomm.protocols.ss7.map.primitives.ISDNAddressStringImpl;
import org.restcomm.protocols.ss7.map.primitives.LAIFixedLengthImpl;
import org.restcomm.protocols.ss7.map.primitives.LMSIImpl;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;
import org.restcomm.protocols.ss7.map.primitives.TMSIImpl;
import org.restcomm.protocols.ss7.map.service.mobility.MobilityMessageImpl;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class SendIdentificationRequestImpl extends MobilityMessageImpl implements SendIdentificationRequest {

    protected static final int _TAG_previousLAI = 0;
    protected static final int _TAG_hopCounter = 1;
    protected static final int _TAG_mtRoamingForwardingSupported = 2;
    protected static final int _TAG_newVLRNumber = 3;
    protected static final int _TAG_newLmsi = 4;

    public static final String _PrimitiveName = "SendIdentificationRequest";

    private TMSI tmsi;
    private Integer numberOfRequestedVectors;
    private boolean segmentationProhibited;
    private MAPExtensionContainer extensionContainer;
    private ISDNAddressString mscNumber;
    private LAIFixedLength previousLAI;
    private Integer hopCounter;
    private boolean mtRoamingForwardingSupported;
    private ISDNAddressString newVLRNumber;
    private LMSI newLmsi;
    private long mapProtocolVersion;

    public SendIdentificationRequestImpl(long mapProtocolVersion) {
        this.mapProtocolVersion = mapProtocolVersion;
    }

    public SendIdentificationRequestImpl(TMSI tmsi, Integer numberOfRequestedVectors, boolean segmentationProhibited,
            MAPExtensionContainer extensionContainer, ISDNAddressString mscNumber, LAIFixedLength previousLAI,
            Integer hopCounter, boolean mtRoamingForwardingSupported, ISDNAddressString newVLRNumber, LMSI newLmsi,
            long mapProtocolVersion) {
        super();
        this.tmsi = tmsi;
        this.numberOfRequestedVectors = numberOfRequestedVectors;
        this.segmentationProhibited = segmentationProhibited;
        this.extensionContainer = extensionContainer;
        this.mscNumber = mscNumber;
        this.previousLAI = previousLAI;
        this.hopCounter = hopCounter;
        this.mtRoamingForwardingSupported = mtRoamingForwardingSupported;
        this.newVLRNumber = newVLRNumber;
        this.newLmsi = newLmsi;
        this.mapProtocolVersion = mapProtocolVersion;
    }

    public SendIdentificationRequestImpl(TMSI tmsi, long mapProtocolVersion) {
        super();
        this.tmsi = tmsi;
        this.mapProtocolVersion = mapProtocolVersion;
    }

    @Override
    public MAPMessageType getMessageType() {
        return MAPMessageType.sendIdentification_Request;
    }

    @Override
    public int getOperationCode() {
        return MAPOperationCode.sendIdentification;
    }

    @Override
    public TMSI getTmsi() {
        return this.tmsi;
    }

    @Override
    public Integer getNumberOfRequestedVectors() {
        return this.numberOfRequestedVectors;
    }

    @Override
    public boolean getSegmentationProhibited() {
        return this.segmentationProhibited;
    }

    @Override
    public MAPExtensionContainer getExtensionContainer() {
        return this.extensionContainer;
    }

    @Override
    public ISDNAddressString getMscNumber() {
        return this.mscNumber;
    }

    @Override
    public LAIFixedLength getPreviousLAI() {
        return this.previousLAI;
    }

    @Override
    public Integer getHopCounter() {
        return this.hopCounter;
    }

    @Override
    public boolean getMtRoamingForwardingSupported() {
        return this.mtRoamingForwardingSupported;
    }

    @Override
    public ISDNAddressString getNewVLRNumber() {
        return this.newVLRNumber;
    }

    @Override
    public LMSI getNewLmsi() {
        return this.newLmsi;
    }

    public long getMapProtocolVersion() {
        return mapProtocolVersion;
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

    @Override
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
        this.tmsi = null;
        this.numberOfRequestedVectors = null;
        this.segmentationProhibited = false;
        this.extensionContainer = null;
        this.mscNumber = null;
        this.previousLAI = null;
        this.hopCounter = null;
        this.mtRoamingForwardingSupported = false;
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
                    if (tag != Tag.STRING_OCTET || ais.getTagClass() != Tag.CLASS_UNIVERSAL || !ais.isTagPrimitive()) {
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".tmsi: Parameter 0 bad tag or tag class or not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);

                    }
                    this.tmsi = new TMSIImpl();
                    ((TMSIImpl) this.tmsi).decodeAll(ais);

                } else {

                    switch (ais.getTagClass()) {
                        case Tag.CLASS_UNIVERSAL:
                            switch (tag) {
                                case Tag.INTEGER:
                                    if (!ais.isTagPrimitive()) {
                                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                                + ".numberOfRequestedVectors: is not primitive",
                                                MAPParsingComponentExceptionReason.MistypedParameter);
                                    }
                                    this.numberOfRequestedVectors = (int) ais.readInteger();
                                    break;
                                case Tag.NULL:
                                    if (!ais.isTagPrimitive()) {
                                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                                + ".segmentationProhibited: is not primitive",
                                                MAPParsingComponentExceptionReason.MistypedParameter);
                                    }
                                    ais.readNull();
                                    this.segmentationProhibited = true;
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
                                case Tag.STRING_OCTET:
                                    if (!ais.isTagPrimitive()) {
                                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                                + ".mscNumber: is not primitive",
                                                MAPParsingComponentExceptionReason.MistypedParameter);
                                    }
                                    this.mscNumber = new ISDNAddressStringImpl();
                                    ((ISDNAddressStringImpl) this.mscNumber).decodeAll(ais);
                                    break;
                                default:
                                    ais.advanceElement();
                                    break;
                            }
                            break;

                        case Tag.CLASS_CONTEXT_SPECIFIC:
                            switch (tag) {
                                case SendIdentificationRequestImpl._TAG_previousLAI:
                                    if (!ais.isTagPrimitive()) {
                                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                                + ".previousLAI: is not primitive",
                                                MAPParsingComponentExceptionReason.MistypedParameter);
                                    }
                                    this.previousLAI = new LAIFixedLengthImpl();
                                    ((LAIFixedLengthImpl) this.previousLAI).decodeAll(ais);
                                    break;
                                case SendIdentificationRequestImpl._TAG_hopCounter:
                                    if (!ais.isTagPrimitive()) {
                                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                                + ".hopCounter: is not primitive",
                                                MAPParsingComponentExceptionReason.MistypedParameter);
                                    }
                                    this.hopCounter = (int) ais.readInteger();
                                    break;
                                case SendIdentificationRequestImpl._TAG_mtRoamingForwardingSupported:
                                    if (!ais.isTagPrimitive()) {
                                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                                + ".mtRoamingForwardingSupported: is not primitive",
                                                MAPParsingComponentExceptionReason.MistypedParameter);
                                    }
                                    ais.readNull();
                                    this.mtRoamingForwardingSupported = true;
                                    break;
                                case SendIdentificationRequestImpl._TAG_newVLRNumber:
                                    if (!ais.isTagPrimitive()) {
                                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                                + ".newVLRNumber: is not primitive",
                                                MAPParsingComponentExceptionReason.MistypedParameter);
                                    }
                                    this.newVLRNumber = new ISDNAddressStringImpl();
                                    ((ISDNAddressStringImpl) this.newVLRNumber).decodeAll(ais);
                                    break;
                                case SendIdentificationRequestImpl._TAG_newLmsi:
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

            if (tag != Tag.STRING_OCTET || asnInputStream.getTagClass() != Tag.CLASS_UNIVERSAL || !asnInputStream.isTagPrimitive()) {
                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                        + ".tmsi: Parameter 0 bad tag or tag class or not primitive",
                        MAPParsingComponentExceptionReason.MistypedParameter);

            }
            this.tmsi = new TMSIImpl();
            ((TMSIImpl) this.tmsi).decodeData(asnInputStream, length);
        }
    }

    @Override
    public void encodeAll(AsnOutputStream asnOutputStream) throws MAPException {
        this.encodeAll(asnOutputStream, this.getTagClass(), this.getTag());
    }

    @Override
    public void encodeAll(AsnOutputStream asnOutputStream, int tagClass, int tag) throws MAPException {
        try {
            asnOutputStream.writeTag(tagClass, getIsPrimitive(), tag);
            int pos = asnOutputStream.StartContentDefiniteLength();
            this.encodeData(asnOutputStream);
            asnOutputStream.FinalizeContent(pos);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        if (this.tmsi == null) {
            throw new MAPException("Error while encoding " + _PrimitiveName + " the mandatory parameter tmsi is not defined");
        }

        try {
            if (mapProtocolVersion >= 3) {
                ((TMSIImpl) this.tmsi).encodeAll(asnOutputStream);

                if (this.numberOfRequestedVectors != null)
                    asnOutputStream.writeInteger(this.numberOfRequestedVectors);

                if (this.segmentationProhibited)
                    asnOutputStream.writeNull();

                if (this.extensionContainer != null)
                    ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOutputStream);

                if (this.mscNumber != null)
                    ((ISDNAddressStringImpl) this.mscNumber).encodeAll(asnOutputStream);

                if (this.previousLAI != null)
                    ((LAIFixedLengthImpl) this.previousLAI).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_previousLAI);

                if (this.hopCounter != null)
                    asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _TAG_hopCounter, this.hopCounter);

                if (this.mtRoamingForwardingSupported)
                    asnOutputStream.writeNull(Tag.CLASS_CONTEXT_SPECIFIC, _TAG_mtRoamingForwardingSupported);

                if (this.newVLRNumber != null)
                    ((ISDNAddressStringImpl) this.newVLRNumber).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_newVLRNumber);

                if (this.newLmsi != null)
                    ((LMSIImpl) this.newLmsi).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_newLmsi);

            } else {
                ((TMSIImpl) this.tmsi).encodeData(asnOutputStream);
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
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.tmsi != null) {
            sb.append("tmsi=");
            sb.append(tmsi.toString());
            sb.append(", ");
        }

        if (this.numberOfRequestedVectors != null) {
            sb.append("numberOfRequestedVectors=");
            sb.append(numberOfRequestedVectors.toString());
            sb.append(", ");
        }

        if (this.segmentationProhibited) {
            sb.append("segmentationProhibited, ");
        }

        if (this.extensionContainer != null) {
            sb.append("extensionContainer=");
            sb.append(extensionContainer.toString());
            sb.append(", ");
        }
        if (this.mscNumber != null) {
            sb.append("mscNumber=");
            sb.append(mscNumber.toString());
            sb.append(", ");
        }
        if (this.previousLAI != null) {
            sb.append("previousLAI=");
            sb.append(previousLAI.toString());
            sb.append(", ");
        }
        if (this.hopCounter != null) {
            sb.append("hopCounter=");
            sb.append(hopCounter.toString());
            sb.append(", ");
        }

        if (this.mtRoamingForwardingSupported) {
            sb.append("mtRoamingForwardingSupported, ");
        }

        if (this.newVLRNumber != null) {
            sb.append("newVLRNumber=");
            sb.append(newVLRNumber.toString());
            sb.append(", ");
        }

        if (this.newLmsi != null) {
            sb.append("lmsi=");
            sb.append(newLmsi.toString());
            sb.append(", ");
        }

        sb.append("mapProtocolVersion=");
        sb.append(mapProtocolVersion);

        sb.append("]");

        return sb.toString();
    }

}
