
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.DefaultSMSHandling;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.SMSCAMELTDPData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.SMSTriggerDetectionPoint;
import org.restcomm.protocols.ss7.map.primitives.ISDNAddressStringImpl;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;
import org.restcomm.protocols.ss7.map.primitives.SequenceBase;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class SMSCAMELTDPDataImpl extends SequenceBase implements SMSCAMELTDPData {

    public static final int _TAG_smsTriggerDetectionPoint = 0;
    public static final int _TAG_serviceKey = 1;
    public static final int _TAG_gsmSCFAddress = 2;
    public static final int _TAG_defaultSMSHandling = 3;
    public static final int _TAG_extensionContainer = 4;

    private SMSTriggerDetectionPoint smsTriggerDetectionPoint;
    private long serviceKey;
    private ISDNAddressString gsmSCFAddress;
    private DefaultSMSHandling defaultSMSHandling;
    private MAPExtensionContainer extensionContainer;

    public SMSCAMELTDPDataImpl() {
        super("SMSCAMELTDPData");
    }

    public SMSCAMELTDPDataImpl(SMSTriggerDetectionPoint smsTriggerDetectionPoint, long serviceKey,
            ISDNAddressString gsmSCFAddress, DefaultSMSHandling defaultSMSHandling, MAPExtensionContainer extensionContainer) {
        super("SMSCAMELTDPData");
        this.smsTriggerDetectionPoint = smsTriggerDetectionPoint;
        this.serviceKey = serviceKey;
        this.gsmSCFAddress = gsmSCFAddress;
        this.defaultSMSHandling = defaultSMSHandling;
        this.extensionContainer = extensionContainer;
    }

    @Override
    public SMSTriggerDetectionPoint getSMSTriggerDetectionPoint() {
        return this.smsTriggerDetectionPoint;
    }

    @Override
    public long getServiceKey() {
        return this.serviceKey;
    }

    @Override
    public ISDNAddressString getGsmSCFAddress() {
        return this.gsmSCFAddress;
    }

    @Override
    public DefaultSMSHandling getDefaultSMSHandling() {
        return this.defaultSMSHandling;
    }

    @Override
    public MAPExtensionContainer getExtensionContainer() {
        return this.extensionContainer;
    }

    @Override
    protected void _decode(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {
        this.smsTriggerDetectionPoint = null;
        this.serviceKey = -1;
        this.gsmSCFAddress = null;
        this.defaultSMSHandling = null;
        this.extensionContainer = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);

        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            switch (ais.getTagClass()) {
                case Tag.CLASS_CONTEXT_SPECIFIC: {
                    switch (tag) {

                        case _TAG_smsTriggerDetectionPoint:
                            if (!ais.isTagPrimitive())
                                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                        + ".smsTriggerDetectionPoint: Parameter not primitive",
                                        MAPParsingComponentExceptionReason.MistypedParameter);
                            int code = (int) ais.readInteger();
                            this.smsTriggerDetectionPoint = SMSTriggerDetectionPoint.getInstance(code);
                            break;
                        case _TAG_serviceKey:
                            if (!ais.isTagPrimitive())
                                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                        + ".serviceKey: Parameter is not primitive",
                                        MAPParsingComponentExceptionReason.MistypedParameter);
                            this.serviceKey = ais.readInteger();
                            break;
                        case _TAG_gsmSCFAddress:
                            if (!ais.isTagPrimitive())
                                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                        + ".offeredCamel4CSIs: Parameter is not primitive",
                                        MAPParsingComponentExceptionReason.MistypedParameter);
                            this.gsmSCFAddress = new ISDNAddressStringImpl();
                            ((ISDNAddressStringImpl) this.gsmSCFAddress).decodeAll(ais);
                            break;
                        case _TAG_defaultSMSHandling:
                            if (!ais.isTagPrimitive())
                                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                        + ".csiActive: Parameter not primitive",
                                        MAPParsingComponentExceptionReason.MistypedParameter);
                            code = (int) ais.readInteger();
                            this.defaultSMSHandling = DefaultSMSHandling.getInstance(code);
                            break;
                        case _TAG_extensionContainer:
                            if (ais.isTagPrimitive())
                                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                        + ".extensionContainer: Parameter is primitive",
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
                default:
                    ais.advanceElement();
                    break;
            }
        }

        if (this.smsTriggerDetectionPoint == null) {
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": Parament smsTriggerDetectionPoint is mandatory but does not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }

        if (this.gsmSCFAddress == null) {
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": Parament gsmSCFAddress is mandatory but does not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }

        if (this.defaultSMSHandling == null) {
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": Parament defaultSMSHandling is mandatory but does not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }

        if (this.serviceKey == -1) {
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": Parament serviceKey is mandatory but does not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        if (this.smsTriggerDetectionPoint == null) {
            throw new MAPException("Error while encoding " + _PrimitiveName
                    + " the mandatory parameter smsTriggerDetectionPoint is not defined");
        }
        if (this.gsmSCFAddress == null) {
            throw new MAPException("Error while encoding " + _PrimitiveName
                    + " the mandatory parameter gsmSCFAddress is not defined");
        }
        if (this.defaultSMSHandling == null) {
            throw new MAPException("Error while encoding " + _PrimitiveName
                    + " the mandatory parameter defaultSMSHandling is not defined");
        }

        try {

            asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _TAG_smsTriggerDetectionPoint, smsTriggerDetectionPoint.getCode());

            asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _TAG_serviceKey, serviceKey);

            ((ISDNAddressStringImpl) this.gsmSCFAddress).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_gsmSCFAddress);

            asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _TAG_defaultSMSHandling, defaultSMSHandling.getCode());

            if (this.extensionContainer != null)
                ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC,
                        _TAG_extensionContainer);

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

        if (this.smsTriggerDetectionPoint != null) {
            sb.append("smsTriggerDetectionPoint=");
            sb.append(this.smsTriggerDetectionPoint.toString());
            sb.append(", ");
        }

        sb.append("serviceKey=");
        sb.append(this.serviceKey);
        sb.append(", ");

        if (this.gsmSCFAddress != null) {
            sb.append("gsmSCFAddress=");
            sb.append(this.gsmSCFAddress.toString());
            sb.append(", ");
        }

        if (this.defaultSMSHandling != null) {
            sb.append("defaultSMSHandling=");
            sb.append(this.defaultSMSHandling.toString());
            sb.append(", ");
        }

        if (this.extensionContainer != null) {
            sb.append("extensionContainer=");
            sb.append(this.extensionContainer.toString());
            sb.append(" ");
        }

        sb.append("]");

        return sb.toString();
    }

}
