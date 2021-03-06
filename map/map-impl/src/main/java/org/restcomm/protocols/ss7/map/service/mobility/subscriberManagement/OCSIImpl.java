
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
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.OBcsmCamelTDPData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.OCSI;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;
import org.restcomm.protocols.ss7.map.primitives.SequenceBase;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class OCSIImpl extends SequenceBase implements OCSI {

    public static final int _TAGcamelCapabilityHandling = 0;
    public static final int _TAGnotificationToCSE = 1;
    public static final int _TAGcsi_Active = 2;

    private ArrayList<OBcsmCamelTDPData> oBcsmCamelTDPDataList;
    private MAPExtensionContainer extensionContainer;
    private Integer camelCapabilityHandling;
    private boolean notificationToCSE;
    private boolean csiActive;

    public OCSIImpl() {
        super("OCSI");
    }

    public OCSIImpl(ArrayList<OBcsmCamelTDPData> oBcsmCamelTDPDataList, MAPExtensionContainer extensionContainer,
            Integer camelCapabilityHandling, boolean notificationToCSE, boolean csiActive) {
        super("OCSI");

        this.oBcsmCamelTDPDataList = oBcsmCamelTDPDataList;
        this.extensionContainer = extensionContainer;
        this.camelCapabilityHandling = camelCapabilityHandling;
        this.notificationToCSE = notificationToCSE;
        this.csiActive = csiActive;
    }

    @Override
    public ArrayList<OBcsmCamelTDPData> getOBcsmCamelTDPDataList() {
        return oBcsmCamelTDPDataList;
    }

    @Override
    public MAPExtensionContainer getExtensionContainer() {
        return extensionContainer;
    }

    @Override
    public Integer getCamelCapabilityHandling() {
        return camelCapabilityHandling;
    }

    @Override
    public boolean getNotificationToCSE() {
        return notificationToCSE;
    }

    @Override
    public boolean getCsiActive() {
        return csiActive;
    }

    @Override
    protected void _decode(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {
        this.oBcsmCamelTDPDataList = null;
        this.extensionContainer = null;
        this.camelCapabilityHandling = null;
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
                    // o-BcsmCamelTDPDataList
                    if (tag != Tag.SEQUENCE || ais.getTagClass() != Tag.CLASS_UNIVERSAL || ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ": Parameter 0 bad tag, tag class or primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);

                    this.oBcsmCamelTDPDataList = new ArrayList<OBcsmCamelTDPData>();

                    AsnInputStream ais2 = ais.readSequenceStream();

                    while (true) {
                        if (ais2.available() == 0)
                            break;

                        int tag2 = ais2.readTag();

                        if (tag2 != Tag.SEQUENCE || ais2.getTagClass() != Tag.CLASS_UNIVERSAL || ais2.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": bad tag or tagClass or is primitive when decoding oBcsmCamelTDPDataList",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        OBcsmCamelTDPDataImpl elem = new OBcsmCamelTDPDataImpl();
                        ((OBcsmCamelTDPDataImpl) elem).decodeAll(ais2);
                        this.oBcsmCamelTDPDataList.add(elem);

                        if (this.oBcsmCamelTDPDataList.size() < 1 || this.oBcsmCamelTDPDataList.size() > 10)
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".oBcsmCamelTDPDataList: tBcsmCamelTDPData elements count must be from 1 to 10, found: "
                                    + this.oBcsmCamelTDPDataList.size(), MAPParsingComponentExceptionReason.MistypedParameter);
                    }
                    break;

                default:
                    switch (ais.getTagClass()) {
                        case Tag.CLASS_UNIVERSAL: {
                            switch (tag) {
                                case Tag.SEQUENCE: // forwardingData
                                    if (ais.isTagPrimitive())
                                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                                + ".forwardingData: Parameter is primitive",
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
                                case _TAGcamelCapabilityHandling:
                                    if (!ais.isTagPrimitive())
                                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                                + ".camelCapabilityHandling: Parameter is not primitive",
                                                MAPParsingComponentExceptionReason.MistypedParameter);
                                    this.camelCapabilityHandling = (int) ais.readInteger();
                                    break;
                                case _TAGnotificationToCSE:
                                    if (!ais.isTagPrimitive())
                                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                                + ".notificationToCSE: Parameter is not primitive",
                                                MAPParsingComponentExceptionReason.MistypedParameter);
                                    this.notificationToCSE = true;
                                    ais.readNull();
                                    break;
                                case _TAGcsi_Active:
                                    if (!ais.isTagPrimitive())
                                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                                + ".csiActive: Parameter is not primitive",
                                                MAPParsingComponentExceptionReason.MistypedParameter);
                                    this.csiActive = true;
                                    ais.readNull();
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

            num++;
        }

        if (num < 1) {
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": Parameter oBcsmCamelTDPDataList is mandatory but does not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        try {
            if (this.oBcsmCamelTDPDataList == null)
                throw new MAPException("Error while encoding" + _PrimitiveName + ": oBcsmCamelTDPDataList must not be null");
            if (this.oBcsmCamelTDPDataList.size() < 1 || this.oBcsmCamelTDPDataList.size() > 10)
                throw new MAPException("Error while encoding" + _PrimitiveName
                        + ": oBcsmCamelTDPDataList size must be from 1 to 10, found: " + this.oBcsmCamelTDPDataList.size());

            asnOutputStream.writeTag(Tag.CLASS_UNIVERSAL, false, Tag.SEQUENCE);
            int pos = asnOutputStream.StartContentDefiniteLength();
            for (OBcsmCamelTDPData be : this.oBcsmCamelTDPDataList) {
                OBcsmCamelTDPDataImpl bee = (OBcsmCamelTDPDataImpl) be;
                bee.encodeAll(asnOutputStream);
            }
            asnOutputStream.FinalizeContent(pos);

            if (this.extensionContainer != null)
                ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOutputStream);

            if (this.camelCapabilityHandling != null)
                asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _TAGcamelCapabilityHandling, this.camelCapabilityHandling);

            if (this.notificationToCSE)
                asnOutputStream.writeNull(Tag.CLASS_CONTEXT_SPECIFIC, _TAGnotificationToCSE);

            if (this.csiActive)
                asnOutputStream.writeNull(Tag.CLASS_CONTEXT_SPECIFIC, _TAGcsi_Active);

        } catch (IOException e) {
            throw new MAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this._PrimitiveName);
        sb.append(" [");

        if (this.oBcsmCamelTDPDataList != null) {
            sb.append("oBcsmCamelTDPDataList=[");
            for (OBcsmCamelTDPData be : this.oBcsmCamelTDPDataList) {
                sb.append(be.toString());
            }
            sb.append("]");
        }
        if (this.extensionContainer != null) {
            sb.append(", extensionContainer=");
            sb.append(this.extensionContainer.toString());
        }
        if (this.camelCapabilityHandling != null) {
            sb.append(", camelCapabilityHandling=");
            sb.append(this.camelCapabilityHandling);
        }
        if (this.notificationToCSE) {
            sb.append(", notificationToCSE");
        }
        if (this.csiActive) {
            sb.append(", csiActive");
        }

        sb.append("]");

        return sb.toString();
    }

}
