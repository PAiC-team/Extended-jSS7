
package org.restcomm.protocols.ss7.map.service.oam;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.MessageImpl;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPMessageType;
import org.restcomm.protocols.ss7.map.api.MAPOperationCode;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.map.api.primitives.GSNAddress;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.oam.ActivateTraceModeRequest_Base;
import org.restcomm.protocols.ss7.map.api.service.oam.MDTConfiguration;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceDepthList;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceEventList;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceInterfaceList;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceNETypeList;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceReference;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceReference2;
import org.restcomm.protocols.ss7.map.api.service.oam.TraceType;
import org.restcomm.protocols.ss7.map.primitives.AddressStringImpl;
import org.restcomm.protocols.ss7.map.primitives.GSNAddressImpl;
import org.restcomm.protocols.ss7.map.primitives.IMSIImpl;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;

/**
*
* @author sergey vetyutnev
*
*/
public class ActivateTraceModeRequestImpl_Base extends MessageImpl implements ActivateTraceModeRequest_Base, MAPAsnPrimitive {

    public static final int _ID_imsi = 0;
    public static final int _ID_traceReference = 1;
    public static final int _ID_traceType = 2;
    public static final int _ID_omcId = 3;
    public static final int _ID_extensionContainer = 4;
    public static final int _ID_traceReference2 = 5;
    public static final int _ID_traceDepthList = 6;
    public static final int _ID_traceNETypeList = 7;
    public static final int _ID_traceInterfaceList = 8;
    public static final int _ID_traceEventList = 9;
    public static final int _ID_traceCollectionEntity = 10;
    public static final int _ID_mdtConfiguration = 11;

    public static final String _PrimitiveName = "ActivateTraceModeRequest";

    private IMSI imsi;
    private TraceReference traceReference;
    private TraceType traceType;
    private AddressString omcId;
    private MAPExtensionContainer extensionContainer;
    private TraceReference2 traceReference2;
    private TraceDepthList traceDepthList;
    private TraceNETypeList traceNeTypeList;
    private TraceInterfaceList traceInterfaceList;
    private TraceEventList traceEventList;
    private GSNAddress traceCollectionEntity;
    private MDTConfiguration mdtConfiguration;

    public ActivateTraceModeRequestImpl_Base() {
    }

    public ActivateTraceModeRequestImpl_Base(IMSI imsi, TraceReference traceReference, TraceType traceType, AddressString omcId,
            MAPExtensionContainer extensionContainer, TraceReference2 traceReference2, TraceDepthList traceDepthList, TraceNETypeList traceNeTypeList,
            TraceInterfaceList traceInterfaceList, TraceEventList traceEventList, GSNAddress traceCollectionEntity, MDTConfiguration mdtConfiguration) {

        this.imsi = imsi;
        this.traceReference = traceReference;
        this.traceType = traceType;
        this.omcId = omcId;
        this.extensionContainer = extensionContainer;
        this.traceReference2 = traceReference2;
        this.traceDepthList = traceDepthList;
        this.traceNeTypeList = traceNeTypeList;
        this.traceInterfaceList = traceInterfaceList;
        this.traceEventList = traceEventList;
        this.traceCollectionEntity = traceCollectionEntity;
        this.mdtConfiguration = mdtConfiguration;
    }

    @Override
    public MAPMessageType getMessageType() {
        return MAPMessageType.activateTraceMode_Request;
    }

    @Override
    public int getOperationCode() {
        return MAPOperationCode.activateTraceMode;
    }


    @Override
    public int getTag() throws MAPException {
        return Tag.SEQUENCE;
    }

    @Override
    public int getTagClass() {
        return Tag.CLASS_UNIVERSAL;
    }

    @Override
    public boolean getIsPrimitive() {
        return false;
    }

    @Override
    public IMSI getImsi() {
        return imsi;
    }

    @Override
    public TraceReference getTraceReference() {
        return traceReference;
    }

    @Override
    public TraceType getTraceType() {
        return traceType;
    }

    @Override
    public AddressString getOmcId() {
        return omcId;
    }

    @Override
    public MAPExtensionContainer getExtensionContainer() {
        return extensionContainer;
    }

    @Override
    public TraceReference2 getTraceReference2() {
        return traceReference2;
    }

    @Override
    public TraceDepthList getTraceDepthList() {
        return traceDepthList;
    }

    @Override
    public TraceNETypeList getTraceNeTypeList() {
        return traceNeTypeList;
    }

    @Override
    public TraceInterfaceList getTraceInterfaceList() {
        return traceInterfaceList;
    }

    @Override
    public TraceEventList getTraceEventList() {
        return traceEventList;
    }

    @Override
    public GSNAddress getTraceCollectionEntity() {
        return traceCollectionEntity;
    }

    @Override
    public MDTConfiguration getMdtConfiguration() {
        return mdtConfiguration;
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
        this.imsi = null;
        this.traceReference = null;
        this.traceType = null;
        this.omcId = null;
        this.extensionContainer = null;
        this.traceReference2 = null;
        this.traceDepthList = null;
        this.traceNeTypeList = null;
        this.traceInterfaceList = null;
        this.traceEventList = null;
        this.traceCollectionEntity = null;
        this.mdtConfiguration = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);

        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {

                switch (tag) {
                case _ID_imsi:
                    if (!ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ".imsi: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.imsi = new IMSIImpl();
                    ((IMSIImpl) this.imsi).decodeAll(ais);
                    break;
                case _ID_traceReference:
                    if (!ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ".traceReference: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.traceReference = new TraceReferenceImpl();
                    ((TraceReferenceImpl) this.traceReference).decodeAll(ais);
                    break;
                case _ID_traceType:
                    if (!ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ".traceType: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.traceType = new TraceTypeImpl();
                    ((TraceTypeImpl) this.traceType).decodeAll(ais);
                    break;
                case _ID_omcId:
                    if (!ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ".omcId: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.omcId = new AddressStringImpl();
                    ((AddressStringImpl) this.omcId).decodeAll(ais);
                    break;
                case _ID_extensionContainer:
                    if (ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".extensionContainer: Parameter is primitive", MAPParsingComponentExceptionReason.MistypedParameter);
                    this.extensionContainer = new MAPExtensionContainerImpl();
                    ((MAPExtensionContainerImpl) this.extensionContainer).decodeAll(ais);
                    break;
                case _ID_traceReference2:
                    if (!ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ".traceReference2: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.traceReference2 = new TraceReference2Impl();
                    ((TraceReference2Impl) this.traceReference2).decodeAll(ais);
                    break;
                case _ID_traceDepthList:
                    if (ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".traceDepthList: Parameter is primitive", MAPParsingComponentExceptionReason.MistypedParameter);
                    this.traceDepthList = new TraceDepthListImpl();
                    ((TraceDepthListImpl) this.traceDepthList).decodeAll(ais);
                    break;
                case _ID_traceNETypeList:
                    if (!ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ".traceReference2: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.traceNeTypeList = new TraceNETypeListImpl();
                    ((TraceNETypeListImpl) this.traceNeTypeList).decodeAll(ais);
                    break;
                case _ID_traceInterfaceList:
                    if (ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".traceInterfaceList: Parameter is primitive", MAPParsingComponentExceptionReason.MistypedParameter);
                    this.traceInterfaceList = new TraceInterfaceListImpl();
                    ((TraceInterfaceListImpl) this.traceInterfaceList).decodeAll(ais);
                    break;
                case _ID_traceEventList:
                    if (ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".traceEventList: Parameter is primitive", MAPParsingComponentExceptionReason.MistypedParameter);
                    this.traceEventList = new TraceEventListImpl();
                    ((TraceEventListImpl) this.traceEventList).decodeAll(ais);
                    break;
                case _ID_traceCollectionEntity:
                    if (!ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ".traceCollectionEntity: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.traceCollectionEntity = new GSNAddressImpl();
                    ((GSNAddressImpl) this.traceCollectionEntity).decodeAll(ais);
                    break;
                case _ID_mdtConfiguration:
                    if (ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".mdtConfiguration: Parameter is primitive", MAPParsingComponentExceptionReason.MistypedParameter);
                    this.mdtConfiguration = new MDTConfigurationImpl();
                    ((MDTConfigurationImpl) this.mdtConfiguration).decodeAll(ais);
                    break;

                default:
                    ais.advanceElement();
                    break;
                }
            } else {
                ais.advanceElement();
            }
        }

        if (this.traceReference == null)
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ": Parameter traceReference is mandator but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);
        if (this.traceType == null)
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ": Parameter traceType is mandator but not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);
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
        if (this.traceReference == null)
            throw new MAPException("traceReference parameter must not be null");
        if (this.traceType == null)
            throw new MAPException("traceType parameter must not be null");

        if (this.imsi != null)
            ((IMSIImpl) this.imsi).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_imsi);

        ((TraceReferenceImpl) this.traceReference).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_traceReference);
        ((TraceTypeImpl) this.traceType).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_traceType);

        if (this.omcId != null)
            ((AddressStringImpl) this.omcId).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_omcId);
        if (this.extensionContainer != null)
            ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_extensionContainer);
        if (this.traceReference2 != null)
            ((TraceReference2Impl) this.traceReference2).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_traceReference2);
        if (this.traceDepthList != null)
            ((TraceDepthListImpl) this.traceDepthList).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_traceDepthList);
        if (this.traceNeTypeList != null)
            ((TraceNETypeListImpl) this.traceNeTypeList).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_traceNETypeList);
        if (this.traceInterfaceList != null)
            ((TraceInterfaceListImpl) this.traceInterfaceList).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_traceInterfaceList);
        if (this.traceEventList != null)
            ((TraceEventListImpl) this.traceEventList).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_traceEventList);
        if (this.traceCollectionEntity != null)
            ((GSNAddressImpl) this.traceCollectionEntity).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_traceCollectionEntity);
        if (this.mdtConfiguration != null)
            ((MDTConfigurationImpl) this.mdtConfiguration).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_mdtConfiguration);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.imsi != null) {
            sb.append("imsi=");
            sb.append(imsi);
            sb.append(", ");
        }
        if (this.traceReference != null) {
            sb.append("traceReference=");
            sb.append(traceReference);
            sb.append(", ");
        }
        if (this.traceType != null) {
            sb.append("traceType=");
            sb.append(traceType);
            sb.append(", ");
        }
        if (this.omcId != null) {
            sb.append("omcId=");
            sb.append(omcId);
            sb.append(", ");
        }
        if (this.extensionContainer != null) {
            sb.append("extensionContainer=");
            sb.append(extensionContainer);
            sb.append(", ");
        }
        if (this.traceReference2 != null) {
            sb.append("traceReference2=");
            sb.append(traceReference2);
            sb.append(", ");
        }
        if (this.traceDepthList != null) {
            sb.append("traceDepthList=");
            sb.append(traceDepthList);
            sb.append(", ");
        }
        if (this.traceNeTypeList != null) {
            sb.append("traceNeTypeList=");
            sb.append(traceNeTypeList);
            sb.append(", ");
        }
        if (this.traceInterfaceList != null) {
            sb.append("traceInterfaceList=");
            sb.append(traceInterfaceList);
            sb.append(", ");
        }
        if (this.traceEventList != null) {
            sb.append("traceEventList=");
            sb.append(traceEventList);
            sb.append(", ");
        }
        if (this.traceCollectionEntity != null) {
            sb.append("traceCollectionEntity=");
            sb.append(traceCollectionEntity);
            sb.append(", ");
        }
        if (this.mdtConfiguration != null) {
            sb.append("mdtConfiguration=");
            sb.append(mdtConfiguration);
            sb.append(", ");
        }

        sb.append("]");

        return sb.toString();
    }

}
