
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
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.SSCSI;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.SSCamelData;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;
import org.restcomm.protocols.ss7.map.primitives.SequenceBase;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class SSCSIImpl extends SequenceBase implements SSCSI {

    private static final int _TAG_notificationToCSE = 0;
    private static final int _TAG_csiActive = 1;

    private SSCamelData ssCamelData;
    private MAPExtensionContainer extensionContainer;
    private boolean notificationToCSE;
    private boolean csiActive;

    public SSCSIImpl() {
        super("SSCSI");
    }

    public SSCSIImpl(SSCamelData ssCamelData, MAPExtensionContainer extensionContainer, boolean notificationToCSE,
            boolean csiActive) {
        super("SSCSI");
        this.ssCamelData = ssCamelData;
        this.extensionContainer = extensionContainer;
        this.notificationToCSE = notificationToCSE;
        this.csiActive = csiActive;
    }

    @Override
    public SSCamelData getSsCamelData() {
        return this.ssCamelData;
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
        this.ssCamelData = null;
        this.extensionContainer = null;
        this.notificationToCSE = false;
        this.csiActive = false;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);

        int num = 0;
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            switch (num) {
                case 0:
                    if (ais.isTagPrimitive() || ais.getTagClass() != Tag.CLASS_UNIVERSAL || tag != Tag.SEQUENCE)
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".ssCamelData: Parameter is primitive or bad tag or bad tag class",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.ssCamelData = new SSCamelDataImpl();
                    ((SSCamelDataImpl) this.ssCamelData).decodeAll(ais);
                    break;
                default:
                    switch (ais.getTagClass()) {
                        case Tag.CLASS_UNIVERSAL: {
                            switch (tag) {
                                case Tag.SEQUENCE:
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
                        case Tag.CLASS_CONTEXT_SPECIFIC: {
                            switch (tag) {
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
                    break;
            }

            num++;
        }

        if (ssCamelData == null) {
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": Parament ssCamelData is mandatory but does not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        if (this.ssCamelData == null) {
            throw new MAPException("Error while encoding " + _PrimitiveName
                    + " the mandatory parameter ssCamelData is not defined");
        }

        try {
            ((SSCamelDataImpl) this.ssCamelData).encodeAll(asnOutputStream);

            if (this.extensionContainer != null)
                ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOutputStream);

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

        if (this.ssCamelData != null) {
            sb.append("ssCamelData=");
            sb.append(this.ssCamelData.toString());
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
