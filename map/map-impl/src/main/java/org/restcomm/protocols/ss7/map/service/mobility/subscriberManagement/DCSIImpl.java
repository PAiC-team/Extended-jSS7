
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
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.DCSI;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.DPAnalysedInfoCriterium;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;
import org.restcomm.protocols.ss7.map.primitives.SequenceBase;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class DCSIImpl extends SequenceBase implements DCSI {

    public static final int _TAG_dpAnalysedInfoCriteriaList = 0;
    public static final int _TAG_camelCapabilityHandling = 1;
    public static final int _TAG_extensionContainer = 2;
    public static final int _TAG_notificationToCSE = 3;
    public static final int _TAG_csiActive = 4;

    private ArrayList<DPAnalysedInfoCriterium> dpAnalysedInfoCriteriaList;
    private Integer camelCapabilityHandling;
    private MAPExtensionContainer extensionContainer;
    private boolean notificationToCSE;
    private boolean csiActive;

    public DCSIImpl() {
        super("DCSI");
    }

    public DCSIImpl(ArrayList<DPAnalysedInfoCriterium> dpAnalysedInfoCriteriaList, Integer camelCapabilityHandling,
            MAPExtensionContainer extensionContainer, boolean notificationToCSE, boolean csiActive) {
        super("DCSI");
        this.dpAnalysedInfoCriteriaList = dpAnalysedInfoCriteriaList;
        this.camelCapabilityHandling = camelCapabilityHandling;
        this.extensionContainer = extensionContainer;
        this.notificationToCSE = notificationToCSE;
        this.csiActive = csiActive;
    }

    @Override
    public ArrayList<DPAnalysedInfoCriterium> getDPAnalysedInfoCriteriaList() {
        return this.dpAnalysedInfoCriteriaList;
    }

    @Override
    public Integer getCamelCapabilityHandling() {
        return this.camelCapabilityHandling;
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
        this.dpAnalysedInfoCriteriaList = null;
        this.camelCapabilityHandling = null;
        this.extensionContainer = null;
        this.notificationToCSE = false;
        this.csiActive = false;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);

        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            switch (tag) {
                case _TAG_dpAnalysedInfoCriteriaList:
                    if (ais.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC || ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".dpAnalysedInfoCriteriaList: Parameter bad tag class or primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);

                    this.dpAnalysedInfoCriteriaList = new ArrayList<DPAnalysedInfoCriterium>();

                    AsnInputStream ais2 = ais.readSequenceStream();

                    while (true) {
                        if (ais2.available() == 0)
                            break;

                        int tag2 = ais2.readTag();

                        if (tag2 != Tag.SEQUENCE || ais2.getTagClass() != Tag.CLASS_UNIVERSAL || ais2.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": bad tag or tagClass or is primitive when decoding dpAnalysedInfoCriteriaList",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        DPAnalysedInfoCriterium elem = new DPAnalysedInfoCriteriumImpl();
                        ((DPAnalysedInfoCriteriumImpl) elem).decodeAll(ais2);
                        this.dpAnalysedInfoCriteriaList.add(elem);

                        if (this.dpAnalysedInfoCriteriaList.size() < 1 || this.dpAnalysedInfoCriteriaList.size() > 10)
                            throw new MAPParsingComponentException(
                                    "Error while decoding "
                                            + _PrimitiveName
                                            + ".oBcsmCamelTDPDataList: dpAnalysedInfoCriteriaList elements count must be from 1 to 10, found: "
                                            + this.dpAnalysedInfoCriteriaList.size(),
                                    MAPParsingComponentExceptionReason.MistypedParameter);
                    }
                    break;
                case _TAG_camelCapabilityHandling:
                    if (ais.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC || !ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".camelCapabilityHandling: Parameter is not primitive or bad tag class",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.camelCapabilityHandling = (int) ais.readInteger();
                    break;
                case _TAG_extensionContainer:
                    if (ais.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC || ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".extensionContainer: Parameter is primitive or bad tag class",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.extensionContainer = new MAPExtensionContainerImpl();
                    ((MAPExtensionContainerImpl) this.extensionContainer).decodeAll(ais);
                    break;
                case _TAG_notificationToCSE:
                    if (ais.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC || !ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".notificationToCSE: Parameter is not primitive or bad tag class",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.notificationToCSE = true;
                    ais.readNull();
                    break;
                case _TAG_csiActive:
                    if (ais.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC || !ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".csiActive: Parameter is not primitive or bad tag class",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.csiActive = true;
                    ais.readNull();
                    break;
                default:
                    ais.advanceElement();
                    break;
            }
        }

    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        try {
            if (this.dpAnalysedInfoCriteriaList != null
                    && (this.dpAnalysedInfoCriteriaList.size() < 1 || this.dpAnalysedInfoCriteriaList.size() > 10))
                throw new MAPException("Error while encoding" + _PrimitiveName
                        + ": dpAnalysedInfoCriteriaList size must be from 1 to 10, found: "
                        + this.dpAnalysedInfoCriteriaList.size());

            if (this.dpAnalysedInfoCriteriaList != null) {
                asnOutputStream.writeTag(Tag.CLASS_CONTEXT_SPECIFIC, false, _TAG_dpAnalysedInfoCriteriaList);
                int pos = asnOutputStream.StartContentDefiniteLength();
                for (DPAnalysedInfoCriterium dpAnalysedInfoCriterium : this.dpAnalysedInfoCriteriaList) {
                    ((DPAnalysedInfoCriteriumImpl) dpAnalysedInfoCriterium).encodeAll(asnOutputStream);
                }
                asnOutputStream.FinalizeContent(pos);
            }

            if (this.camelCapabilityHandling != null)
                asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _TAG_camelCapabilityHandling, this.camelCapabilityHandling);

            if (this.extensionContainer != null)
                ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC,
                        _TAG_extensionContainer);

            if (this.notificationToCSE)
                asnOutputStream.writeNull(Tag.CLASS_CONTEXT_SPECIFIC, _TAG_notificationToCSE);

            if (this.csiActive)
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

        if (this.dpAnalysedInfoCriteriaList != null) {
            sb.append("dpAnalysedInfoCriteriaList=[");
            boolean firstItem = true;
            for (DPAnalysedInfoCriterium be : this.dpAnalysedInfoCriteriaList) {
                if (firstItem)
                    firstItem = false;
                else
                    sb.append(", ");
                sb.append(be.toString());
            }
            sb.append("], ");
        }

        if (this.camelCapabilityHandling != null) {
            sb.append("camelCapabilityHandling=");
            sb.append(this.camelCapabilityHandling.toString());
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
