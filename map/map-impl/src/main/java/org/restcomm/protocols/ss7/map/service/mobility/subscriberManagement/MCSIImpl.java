
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import java.io.IOException;
import java.util.ArrayList;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.MCSI;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.MMCode;
import org.restcomm.protocols.ss7.map.primitives.ISDNAddressStringImpl;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;
import org.restcomm.protocols.ss7.map.primitives.SequenceBase;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class MCSIImpl extends SequenceBase implements MCSI {

    private static final int _TAG_gsmSCFAddress = 0;
    private static final int _TAG_extensionContainer = 1;
    private static final int _TAG_notificationToCSE = 2;
    private static final int _TAG_csiActive = 3;

    private ArrayList<MMCode> mobilityTriggers;
    private long serviceKey;
    private ISDNAddressString gsmSCFAddress;
    private MAPExtensionContainer extensionContainer;
    private boolean notificationToCSE;
    private boolean csiActive;

    public MCSIImpl() {
        super("MCSI");
    }

    public MCSIImpl(ArrayList<MMCode> mobilityTriggers, long serviceKey, ISDNAddressString gsmSCFAddress,
            MAPExtensionContainer extensionContainer, boolean notificationToCSE, boolean csiActive) {
        super("MCSI");
        this.mobilityTriggers = mobilityTriggers;
        this.serviceKey = serviceKey;
        this.gsmSCFAddress = gsmSCFAddress;
        this.extensionContainer = extensionContainer;
        this.notificationToCSE = notificationToCSE;
        this.csiActive = csiActive;
    }

    @Override
    public ArrayList<MMCode> getMobilityTriggers() {
        return this.mobilityTriggers;
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
    public MAPExtensionContainer getExtensionContainer() {
        return this.extensionContainer;
    }

    @Override
    public boolean getNotificationToCSE() {
        return this.notificationToCSE;
    }

    @Override
    public boolean getCsiActive() {
        return this.csiActive;
    }

    @Override
    protected void _decode(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {

        this.mobilityTriggers = null;
        this.gsmSCFAddress = null;
        this.extensionContainer = null;
        this.notificationToCSE = false;
        this.csiActive = false;
        this.serviceKey = -1;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);

        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            switch (ais.getTagClass()) {
                case Tag.CLASS_UNIVERSAL: {
                    switch (tag) {
                        case Tag.SEQUENCE:
                            if (ais.isTagPrimitive())
                                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                        + ".mobilityTriggers: Parameter mobilityTriggers is primitive",
                                        MAPParsingComponentExceptionReason.MistypedParameter);

                            MMCode mmCode = null;
                            AsnInputStream ais2 = ais.readSequenceStream();
                            this.mobilityTriggers = new ArrayList<MMCode>();
                            while (true) {
                                if (ais2.available() == 0)
                                    break;

                                int tag2 = ais2.readTag();
                                if (tag2 != Tag.STRING_OCTET || ais2.getTagClass() != Tag.CLASS_UNIVERSAL
                                        || !ais2.isTagPrimitive())
                                    throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                            + "MMCode: bad tag or tagClass or is not primitive ",
                                            MAPParsingComponentExceptionReason.MistypedParameter);

                                mmCode = new MMCodeImpl();
                                ((MMCodeImpl) mmCode).decodeAll(ais2);
                                this.mobilityTriggers.add(mmCode);
                            }

                            if (this.mobilityTriggers.size() < 1 || this.mobilityTriggers.size() > 10) {
                                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                        + ": Parameter mobilityTriggers size must be from 1 to 10, found: "
                                        + this.mobilityTriggers.size(), MAPParsingComponentExceptionReason.MistypedParameter);
                            }
                            break;
                        case Tag.INTEGER:
                            if (!ais.isTagPrimitive())
                                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                        + ".serviceKey: is not primitive", MAPParsingComponentExceptionReason.MistypedParameter);
                            this.serviceKey = (long) ais.readInteger();
                            break;
                        default:
                            ais.advanceElement();
                            break;
                    }
                }
                    break;
                case Tag.CLASS_CONTEXT_SPECIFIC: {
                    switch (tag) {
                        case _TAG_gsmSCFAddress:
                            if (!ais.isTagPrimitive())
                                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                        + ".gsmSCFAddress: Parameter not primitive",
                                        MAPParsingComponentExceptionReason.MistypedParameter);
                            this.gsmSCFAddress = new ISDNAddressStringImpl();
                            ((ISDNAddressStringImpl) this.gsmSCFAddress).decodeAll(ais);
                            break;
                        case _TAG_extensionContainer:
                            if (ais.isTagPrimitive())
                                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                        + ".extensionContainer: Parameter is primitive",
                                        MAPParsingComponentExceptionReason.MistypedParameter);
                            this.extensionContainer = new MAPExtensionContainerImpl();
                            ((MAPExtensionContainerImpl) this.extensionContainer).decodeAll(ais);
                            break;
                        case _TAG_notificationToCSE:
                            if (!ais.isTagPrimitive())
                                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                        + ".notificationToCSE: Parameter not primitive",
                                        MAPParsingComponentExceptionReason.MistypedParameter);
                            ais.readNull();
                            this.notificationToCSE = true;
                            break;
                        case _TAG_csiActive:
                            if (!ais.isTagPrimitive())
                                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                        + ".csiActive: Parameter not primitive",
                                        MAPParsingComponentExceptionReason.MistypedParameter);
                            ais.readNull();
                            this.csiActive = true;
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

        if (this.mobilityTriggers == null) {
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": Parameter mobilityTriggers is mandatory but does not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }

        if (this.gsmSCFAddress == null) {
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": Parameter gsmSCFAddress is mandatory but does not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }

        if (this.serviceKey == -1) {
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": Parameter serviceKey is mandatory but does not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        if (this.mobilityTriggers == null) {
            throw new MAPException("Error while encoding " + _PrimitiveName
                    + " the mandatory parameter mobilityTriggers is not defined");
        }

        if (this.gsmSCFAddress == null) {
            throw new MAPException("Error while encoding " + _PrimitiveName
                    + " the mandatory parameter gsmSCFAddress is not defined");
        }

        if (this.mobilityTriggers.size() < 1 || this.mobilityTriggers.size() > 10) {
            throw new MAPException("Error while encoding " + _PrimitiveName
                    + ": Parameter mobilityTriggers size must be from 1 to 10, found: " + this.mobilityTriggers.size());
        }

        try {
            asnOutputStream.writeTag(Tag.CLASS_UNIVERSAL, false, Tag.SEQUENCE);
            int pos = asnOutputStream.StartContentDefiniteLength();
            for (MMCode mmCode : this.mobilityTriggers) {
                ((MMCodeImpl) mmCode).encodeAll(asnOutputStream);
            }
            asnOutputStream.FinalizeContent(pos);

            asnOutputStream.writeInteger(serviceKey);

            ((ISDNAddressStringImpl) this.gsmSCFAddress).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_gsmSCFAddress);

            if (this.extensionContainer != null)
                ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC,
                        _TAG_extensionContainer);

            if (notificationToCSE)
                asnOutputStream.writeNull(Tag.CLASS_CONTEXT_SPECIFIC, _TAG_notificationToCSE);

            if (csiActive)
                asnOutputStream.writeNull(Tag.CLASS_CONTEXT_SPECIFIC, _TAG_csiActive);

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

        if (this.mobilityTriggers != null) {
            sb.append("mobilityTriggers=[");
            boolean firstItem = true;
            for (MMCode be : this.mobilityTriggers) {
                if (firstItem)
                    firstItem = false;
                else
                    sb.append(", ");
                sb.append(be.toString());
            }
            sb.append("], ");
        }

        sb.append("serviceKey=");
        sb.append(this.serviceKey);
        sb.append(", ");

        if (this.gsmSCFAddress != null) {
            sb.append("gsmSCFAddress=");
            sb.append(this.gsmSCFAddress.toString());
            sb.append(", ");
        }

        if (this.extensionContainer != null) {
            sb.append("extensionContainer=");
            sb.append(this.extensionContainer.toString());
            sb.append(", ");
        }

        if (this.notificationToCSE) {
            sb.append("notificationToCSE ");
            sb.append(", ");
        }

        if (this.csiActive) {
            sb.append("csiActive ");
        }

        sb.append("]");

        return sb.toString();
    }

}
