
package org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation;

import java.io.IOException;
import java.util.ArrayList;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.NotReachableReason;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.PDPContextInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.PSSubscriberState;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.PSSubscriberStateChoice;
import org.restcomm.protocols.ss7.map.primitives.ArrayListSerializingBase;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;

/**
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public class PSSubscriberStateImpl implements PSSubscriberState, MAPAsnPrimitive {

    public static final int _ID_notProvidedFromSGSNorMME = 0;
    public static final int _ID_ps_Detached = 1;
    public static final int _ID_ps_AttachedNotReachableForPaging = 2;
    public static final int _ID_ps_AttachedReachableForPaging = 3;
    public static final int _ID_ps_PDP_ActiveNotReachableForPaging = 4;
    public static final int _ID_ps_PDP_ActiveReachableForPaging = 5;

    public static final String _PrimitiveName = "PSSubscriberState";

    private static final String DEFAULT_STRING_VALUE = null;

    private static final String PDP_CONTEXT_INFO = "pdpContextInfo";

    private static final String CHOICE = "choice";
    private static final String NET_DET_NOT_REACHABLE = "netDetNotReachable";
    private static final String PDP_CONTEXT_INFO_LIST = "pdpContextInfoList";

    private PSSubscriberStateChoice choice;
    private NotReachableReason netDetNotReachable;
    private ArrayList<PDPContextInfo> pdpContextInfoList;

    public PSSubscriberStateImpl() {
    }

    public PSSubscriberStateImpl(PSSubscriberStateChoice choice, NotReachableReason netDetNotReachable,
            ArrayList<PDPContextInfo> pdpContextInfoList) {
        this.choice = choice;
        this.netDetNotReachable = netDetNotReachable;
        this.pdpContextInfoList = pdpContextInfoList;
    }

    @Override
    public PSSubscriberStateChoice getChoice() {
        return choice;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.subscriberInformation. PSSubscriberState#getNetDetNotReachable()
     */
    public NotReachableReason getNetDetNotReachable() {
        return this.netDetNotReachable;
    }

    @Override
    public ArrayList<PDPContextInfo> getPDPContextInfoList() {
        return pdpContextInfoList;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#getTag()
     */
    public int getTag() throws MAPException {
        if (this.choice == PSSubscriberStateChoice.notProvidedFromSGSNorMME) {
            return _ID_notProvidedFromSGSNorMME;
        } else if (this.choice == PSSubscriberStateChoice.psDetached) {
            return _ID_ps_Detached;
        } else if (this.choice == PSSubscriberStateChoice.psAttachedNotReachableForPaging) {
            return _ID_ps_AttachedNotReachableForPaging;
        } else if (this.choice == PSSubscriberStateChoice.psAttachedReachableForPaging) {
            return _ID_ps_AttachedReachableForPaging;
        } else if (this.choice == PSSubscriberStateChoice.psPDPActiveNotReachableForPaging) {
            return _ID_ps_PDP_ActiveNotReachableForPaging;
        } else if (this.choice == PSSubscriberStateChoice.psPDPActiveReachableForPaging) {
            return _ID_ps_PDP_ActiveReachableForPaging;
        } else if (this.choice == PSSubscriberStateChoice.netDetNotReachable) {
            return Tag.ENUMERATED;
        }

        throw new MAPException("Error encoding " + _PrimitiveName + ": Bad hoice value");
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#getTagClass()
     */
    public int getTagClass() {
        if (this.choice == PSSubscriberStateChoice.netDetNotReachable)
            return Tag.CLASS_UNIVERSAL;
        else
            return Tag.CLASS_CONTEXT_SPECIFIC;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#getIsPrimitive ()
     */
    public boolean getIsPrimitive() {
        if (this.choice == PSSubscriberStateChoice.psPDPActiveNotReachableForPaging
                || this.choice == PSSubscriberStateChoice.psPDPActiveReachableForPaging)
            return false;
        else
            return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#decodeAll( org.mobicents.protocols.asn.AsnInputStream)
     */
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

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#decodeData (org.mobicents.protocols.asn.AsnInputStream,
     * int)
     */
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

        this.choice = null;
        this.netDetNotReachable = null;
        this.pdpContextInfoList = null;

        if (asnInputStream.getTagClass() == Tag.CLASS_UNIVERSAL) {
            switch (asnInputStream.getTag()) {
                case Tag.ENUMERATED:
                    if (!asnInputStream.isTagPrimitive())
                        throw new MAPParsingComponentException(
                                "Error while decoding netDetNotReachable choice: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.choice = PSSubscriberStateChoice.netDetNotReachable;
                    int i1 = (int) asnInputStream.readIntegerData(length);
                    this.netDetNotReachable = NotReachableReason.getInstance(i1);
                    break;

                default:
                    throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                            + ": bad tag for Universal TagClass: " + asnInputStream.getTag(),
                            MAPParsingComponentExceptionReason.MistypedParameter);
            }
        } else if (asnInputStream.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
            switch (asnInputStream.getTag()) {
                case _ID_notProvidedFromSGSNorMME:
                    if (!asnInputStream.isTagPrimitive())
                        throw new MAPParsingComponentException(
                                "Error while decoding notProvidedFromSGSNorMME choice: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.choice = PSSubscriberStateChoice.notProvidedFromSGSNorMME;
                    asnInputStream.readNullData(length);
                    break;

                case _ID_ps_Detached:
                    if (!asnInputStream.isTagPrimitive())
                        throw new MAPParsingComponentException(
                                "Error while decoding psDetached choice: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.choice = PSSubscriberStateChoice.psDetached;
                    asnInputStream.readNullData(length);
                    break;

                case _ID_ps_AttachedNotReachableForPaging:
                    if (!asnInputStream.isTagPrimitive())
                        throw new MAPParsingComponentException(
                                "Error while decoding psAttachedNotReachableForPaging choice: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.choice = PSSubscriberStateChoice.psAttachedNotReachableForPaging;
                    asnInputStream.readNullData(length);
                    break;

                case _ID_ps_AttachedReachableForPaging:
                    if (!asnInputStream.isTagPrimitive())
                        throw new MAPParsingComponentException(
                                "Error while decoding psAttachedReachableForPaging choice: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.choice = PSSubscriberStateChoice.psAttachedReachableForPaging;
                    asnInputStream.readNullData(length);
                    break;

                case _ID_ps_PDP_ActiveNotReachableForPaging:
                    if (asnInputStream.isTagPrimitive())
                        throw new MAPParsingComponentException(
                                "Error while decoding psPDPActiveNotReachableForPaging choice: Parameter is primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.choice = PSSubscriberStateChoice.psPDPActiveNotReachableForPaging;
                    this.decodePdpContextInfoList(asnInputStream, length);
                    break;

                case _ID_ps_PDP_ActiveReachableForPaging:
                    if (asnInputStream
                        .isTagPrimitive())
                        throw new MAPParsingComponentException(
                                "Error while decoding psPDPActiveReachableForPaging choice: Parameter is primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.choice = PSSubscriberStateChoice.psPDPActiveReachableForPaging;
                    this.decodePdpContextInfoList(asnInputStream, length);
                    break;

                default:
                    throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                            + ": bad tag for ContextSpecific TagClass: " + asnInputStream.getTag(),
                            MAPParsingComponentExceptionReason.MistypedParameter);
            }
        } else {
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ": bad tagClass: "
                    + asnInputStream.getTagClass(), MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    private void decodePdpContextInfoList(AsnInputStream asnInputStream, int length) throws AsnException, IOException,
            MAPParsingComponentException {
        this.pdpContextInfoList = new ArrayList<PDPContextInfo>();
        while (true) {
            if (asnInputStream.available() == 0)
                break;

            int tag2 = asnInputStream.readTag();
            if (asnInputStream.getTagClass() != Tag.CLASS_UNIVERSAL || tag2 != Tag.SEQUENCE || asnInputStream.isTagPrimitive())
                throw new MAPParsingComponentException("Error when decoding " + _PrimitiveName
                        + " pdpContextInfoList parameter components: bad tag class or tag or is primitive",
                        MAPParsingComponentExceptionReason.MistypedParameter);

            PDPContextInfoImpl elem = new PDPContextInfoImpl();
            elem.decodeAll(asnInputStream);
            this.pdpContextInfoList.add(elem);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#encodeAll(org.mobicents.protocols.asn.AsnOutputStream)
     */
    public void encodeAll(AsnOutputStream asnOutputStream) throws MAPException {
        this.encodeAll(asnOutputStream, this.getTagClass(), this.getTag());
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#encodeAll(org.mobicents.protocols.asn.AsnOutputStream,
     * int, int)
     */
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

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#encodeData (org.mobicents.protocols.asn.AsnOutputStream)
     */
    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        try {
            if (this.choice == null)
                throw new MAPException("Error while encoding the " + _PrimitiveName + ": choice is not defined");

            if ((this.choice == PSSubscriberStateChoice.psPDPActiveNotReachableForPaging || this.choice == PSSubscriberStateChoice.psPDPActiveReachableForPaging)
                    && this.pdpContextInfoList == null)
                throw new MAPException(
                        "Error while encoding the "
                                + _PrimitiveName
                                + ": for choice psPDPActiveNotReachableForPaging or psPDPActiveReachableForPaging - pdpContextInfoList must not be null");

            if ((this.choice == PSSubscriberStateChoice.netDetNotReachable) && this.netDetNotReachable == null)
                throw new MAPException("Error while encoding the " + _PrimitiveName
                        + ": for choice netDetNotReachable - netDetNotReachable must not be null");

            switch (this.choice) {
                case notProvidedFromSGSNorMME:
                case psDetached:
                case psAttachedNotReachableForPaging:
                case psAttachedReachableForPaging:
                    asnOutputStream.writeNullData();
                    break;
                case psPDPActiveNotReachableForPaging:
                case psPDPActiveReachableForPaging:

                    if (this.pdpContextInfoList.size() < 1 || this.pdpContextInfoList.size() > 50)
                        throw new MAPException("Error while encoding " + _PrimitiveName
                                + ": pdpContextInfoList size must be from 1 to 50");

                    for (PDPContextInfo cii : this.pdpContextInfoList) {
                        PDPContextInfoImpl ci = (PDPContextInfoImpl) cii;
                        ci.encodeAll(asnOutputStream);
                    }
                    break;
                case netDetNotReachable:
                    asnOutputStream.writeIntegerData(this.netDetNotReachable.getCode());
                    break;
            }

        } catch (IOException e) {
            throw new MAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.choice != null)
            sb.append(this.choice.toString());
        if (this.netDetNotReachable != null) {
            sb.append(", netDetNotReachable=");
            sb.append(this.netDetNotReachable.toString());
        }
        if (this.pdpContextInfoList != null && this.pdpContextInfoList.size() > 0) {
            sb.append(", pdpContextInfoList [");
            for (PDPContextInfo p : pdpContextInfoList) {
                sb.append("PDPContextInfo=");
                sb.append(p);
                sb.append(", ");
            }
            sb.append("]");
        }

        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<PSSubscriberStateImpl> PS_SUBSCRIBER_STATE_XML = new XMLFormat<PSSubscriberStateImpl>(
            PSSubscriberStateImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, PSSubscriberStateImpl subscriberState)
                throws XMLStreamException {
            String ssc = xml.getAttribute(CHOICE, DEFAULT_STRING_VALUE);
            if (ssc != null) {
                subscriberState.choice = Enum.valueOf(PSSubscriberStateChoice.class, ssc);
            }
            String nrr = xml.getAttribute(NET_DET_NOT_REACHABLE, DEFAULT_STRING_VALUE);
            if (nrr != null) {
                subscriberState.netDetNotReachable = Enum.valueOf(NotReachableReason.class, nrr);
            }
            PSSubscriberState_PDPContextInfo al = xml.get(PDP_CONTEXT_INFO_LIST, PSSubscriberState_PDPContextInfo.class);
            if (al != null) {
                subscriberState.pdpContextInfoList = al.getData();
            }
        }

        @Override
        public void write(PSSubscriberStateImpl subscriberState, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {
            if (subscriberState.choice != null)
                xml.setAttribute(CHOICE, subscriberState.choice.toString());
            if (subscriberState.netDetNotReachable != null)
                xml.setAttribute(NET_DET_NOT_REACHABLE, subscriberState.netDetNotReachable.toString());
            if(subscriberState.pdpContextInfoList != null) {
                PSSubscriberState_PDPContextInfo al = new PSSubscriberState_PDPContextInfo(subscriberState.pdpContextInfoList);
                xml.add(al, PDP_CONTEXT_INFO_LIST, PSSubscriberState_PDPContextInfo.class);
            }
        }
    };

    public static class PSSubscriberState_PDPContextInfo extends ArrayListSerializingBase<PDPContextInfo> {

        public PSSubscriberState_PDPContextInfo() {
            super(PDP_CONTEXT_INFO, PDPContextInfoImpl.class);
        }

        public PSSubscriberState_PDPContextInfo(ArrayList<PDPContextInfo> data) {
            super(PDP_CONTEXT_INFO, PDPContextInfoImpl.class, data);
        }
    }
}
