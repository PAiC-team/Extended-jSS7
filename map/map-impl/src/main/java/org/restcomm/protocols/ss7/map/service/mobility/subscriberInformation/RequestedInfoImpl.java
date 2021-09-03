package org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.DomainType;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.RequestedInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.RequestedServingNode;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;

/**
 * @author amit bhayani
 * @author sergey vetyutnev
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class RequestedInfoImpl implements RequestedInfo, MAPAsnPrimitive {

    public static final int _ID_locationInformation = 0;
    public static final int _ID_subscriberState = 1;
    public static final int _ID_extensionContainer = 2;
    public static final int _ID_currentLocation = 3;
    public static final int _ID_requestedDomain = 4;
    public static final int _ID_imei = 6;
    public static final int _ID_msclassmark = 5;
    public static final int _ID_mnpRequestedInfo = 7;
    public static final int _ID_tadsData = 8;
    public static final int _ID_requestedNodes = 9;
    public static final int _ID_servingNodeIndication = 10;
    public static final int _ID_locationInformationEPSSupported = 11;
    public static final int _ID_localTimeZoneRequest = 12;

    public static final String _PrimitiveName = "RequestedInfo";

    private boolean locationInformation;
    private boolean subscriberState;
    private MAPExtensionContainer extensionContainer;
    private boolean currentLocation;
    private DomainType requestedDomain;
    private boolean imei;
    private boolean msClassmark;
    private boolean mnpRequestedInfo;
    private boolean tadsData;
    private RequestedServingNode requestedNodes;
    private boolean servingNodeIndication;
    private boolean locationInformationEPSSupported;
    private boolean localTimeZoneRequest;

    /**
     *
     */
    public RequestedInfoImpl() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param locationInformation
     * @param subscriberState
     * @param extensionContainer
     * @param currentLocation
     * @param requestedDomain
     * @param imei
     * @param msClassmark
     * @param mnpRequestedInfo
     * @param locationInformationEPSSupported
     */
    public RequestedInfoImpl(boolean locationInformation, boolean subscriberState, MAPExtensionContainer extensionContainer, boolean currentLocation,
                             DomainType requestedDomain, boolean imei, boolean msClassmark, boolean mnpRequestedInfo, boolean locationInformationEPSSupported) {
        super();
        this.locationInformation = locationInformation;
        this.subscriberState = subscriberState;
        this.extensionContainer = extensionContainer;
        this.currentLocation = currentLocation;
        this.requestedDomain = requestedDomain;
        this.imei = imei;
        this.msClassmark = msClassmark;
        this.mnpRequestedInfo = mnpRequestedInfo;
        this.locationInformationEPSSupported = locationInformationEPSSupported;
    }

    /**
     * @param locationInformation
     * @param subscriberState
     * @param extensionContainer
     * @param currentLocation
     * @param requestedDomain
     * @param imei
     * @param msClassmark
     * @param mnpRequestedInfo
     * @param tadsData
     * @param requestedNodes
     * @param servingNodeIndication
     * @param locationInformationEPSSupported
     * @param localTimeZoneRequest
     */
    public RequestedInfoImpl(boolean locationInformation, boolean subscriberState, MAPExtensionContainer extensionContainer, boolean currentLocation,
                             DomainType requestedDomain, boolean imei, boolean msClassmark, boolean mnpRequestedInfo, boolean tadsData, RequestedServingNode requestedNodes,
                             boolean servingNodeIndication, boolean locationInformationEPSSupported, boolean localTimeZoneRequest) {
        this.locationInformation = locationInformation;
        this.subscriberState = subscriberState;
        this.extensionContainer = extensionContainer;
        this.currentLocation = currentLocation;
        this.requestedDomain = requestedDomain;
        this.imei = imei;
        this.msClassmark = msClassmark;
        this.mnpRequestedInfo = mnpRequestedInfo;
        this.tadsData = tadsData;
        this.requestedNodes = requestedNodes;
        this.servingNodeIndication = servingNodeIndication;
        this.locationInformationEPSSupported = locationInformationEPSSupported;
        this.localTimeZoneRequest = localTimeZoneRequest;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.map.primitives.MAPAsnPrimitive#getTag()
     */
    public int getTag() throws MAPException {
        return Tag.SEQUENCE;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.map.primitives.MAPAsnPrimitive#getTagClass()
     */
    public int getTagClass() {
        return Tag.CLASS_UNIVERSAL;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.map.primitives.MAPAsnPrimitive#getIsPrimitive ()
     */
    public boolean getIsPrimitive() {
        return false;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.map.primitives.MAPAsnPrimitive#decodeAll(org.mobicents.protocols.asn.AsnInputStream)
     */
    public void decodeAll(AsnInputStream ansIS) throws MAPParsingComponentException {
        try {
            int length = ansIS.readLength();
            this._decode(ansIS, length);
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
     * @see org.mobicents.protocols.ss7.map.primitives.MAPAsnPrimitive#decodeData(org.mobicents.protocols.asn.AsnInputStream,
     * int)
     */
    public void decodeData(AsnInputStream ansIS, int length) throws MAPParsingComponentException {
        try {
            this._decode(ansIS, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    private void _decode(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {
        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);

        locationInformation = false;
        subscriberState = false;
        extensionContainer = null;
        currentLocation = false;
        requestedDomain = null;
        imei = false;
        msClassmark = false;
        mnpRequestedInfo = false;
        this.locationInformationEPSSupported = false;

        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                    case _ID_locationInformation:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException(
                                "Error while decoding RequestedInfo: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                        ais.readNull();
                        this.locationInformation = Boolean.TRUE;
                        break;
                    case _ID_subscriberState:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException(
                                "Error while decoding RequestedInfo: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                        ais.readNull();
                        this.subscriberState = Boolean.TRUE;
                        break;
                    case _ID_extensionContainer:
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException(
                                "Error while decoding RequestedInfo: Parameter is primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                        extensionContainer = new MAPExtensionContainerImpl();
                        ((MAPExtensionContainerImpl) extensionContainer).decodeAll(ais);
                        break;
                    case _ID_currentLocation:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException(
                                "Error while decoding RequestedInfo: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                        ais.readNull();
                        this.currentLocation = Boolean.TRUE;
                        break;
                    case _ID_requestedDomain:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException(
                                "Error while decoding RequestedInfo: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                        int i1 = (int) ais.readInteger();
                        this.requestedDomain = DomainType.getInstance(i1);
                        break;
                    case _ID_msclassmark:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException(
                                "Error while decoding RequestedInfo: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                        ais.readNull();
                        this.msClassmark = Boolean.TRUE;
                        break;
                    case _ID_imei:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException(
                                "Error while decoding RequestedInfo: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                        ais.readNull();
                        this.imei = Boolean.TRUE;
                        break;
                    case _ID_mnpRequestedInfo:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException(
                                "Error while decoding RequestedInfo: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                        ais.readNull();
                        this.mnpRequestedInfo = Boolean.TRUE;
                        break;
                    case _ID_tadsData:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException(
                                "Error while decoding RequestedInfo: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                        ais.readNull();
                        this.tadsData = Boolean.TRUE;
                        break;
                    case _ID_requestedNodes:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException(
                                "Error while decoding RequestedInfo: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                        ais.readNull();
                        this.requestedNodes = new RequestedServingNode() {
                            public boolean getMmeAndSgsn() {
                                return true;
                            }
                        };
                        break;
                    case _ID_servingNodeIndication:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException(
                                "Error while decoding RequestedInfo: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                        ais.readNull();
                        this.servingNodeIndication = Boolean.TRUE;
                        break;
                    case _ID_locationInformationEPSSupported:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException(
                                "Error while decoding RequestedInfo: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                        ais.readNull();
                        this.locationInformationEPSSupported = Boolean.TRUE;
                        break;
                    case _ID_localTimeZoneRequest:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException(
                                "Error while decoding RequestedInfo: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                        ais.readNull();
                        this.localTimeZoneRequest = Boolean.TRUE;
                        break;
                    default:
                        ais.advanceElement();
                        break;
                }
            } else {
                ais.advanceElement();
            }
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.map.primitives.MAPAsnPrimitive#encodeAll( org.mobicents.protocols.asn.AsnOutputStream)
     */
    public void encodeAll(AsnOutputStream asnOutputStream) throws MAPException {
        this.encodeAll(asnOutputStream, this.getTagClass(), this.getTag());
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.map.primitives.MAPAsnPrimitive#encodeAll( org.mobicents.protocols.asn.AsnOutputStream,
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
     * @see org.mobicents.protocols.ss7.map.primitives.MAPAsnPrimitive#encodeData (org.mobicents.protocols.asn.AsnOutputStream)
     */
    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        try {
            if (this.locationInformation) {
                asnOutputStream.writeNull(Tag.CLASS_CONTEXT_SPECIFIC, _ID_locationInformation);
            }
        } catch (IOException e) {
            throw new MAPException("IOException when encoding parameter locationInformation: ", e);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding parameter locationInformation: ", e);
        }

        try {
            if (this.subscriberState) {
                asnOutputStream.writeNull(Tag.CLASS_CONTEXT_SPECIFIC, _ID_subscriberState);
            }
        } catch (IOException e) {
            throw new MAPException("IOException when encoding parameter subscriberState: ", e);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding parameter subscriberState: ", e);
        }

        if (this.extensionContainer != null)
            ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC,
                _ID_extensionContainer);

        try {
            if (this.currentLocation) {
                asnOutputStream.writeNull(Tag.CLASS_CONTEXT_SPECIFIC, _ID_currentLocation);
            }
        } catch (IOException e) {
            throw new MAPException("IOException when encoding parameter currentLocation: ", e);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding parameter currentLocation: ", e);
        }

        try {
            if (this.requestedDomain != null) {
                asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _ID_requestedDomain, this.requestedDomain.getType());
            }
        } catch (IOException e) {
            throw new MAPException("IOException when encoding parameter requestedDomain: ", e);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding parameter requestedDomain: ", e);
        }

        try {
            if (this.imei) {
                asnOutputStream.writeNull(Tag.CLASS_CONTEXT_SPECIFIC, _ID_imei);
            }
        } catch (IOException e) {
            throw new MAPException("IOException when encoding parameter imei: ", e);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding parameter imei: ", e);
        }

        try {
            if (this.msClassmark) {
                asnOutputStream.writeNull(Tag.CLASS_CONTEXT_SPECIFIC, _ID_msclassmark);
            }
        } catch (IOException e) {
            throw new MAPException("IOException when encoding parameter msClassmark: ", e);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding parameter msClassmark: ", e);
        }

        try {
            if (this.mnpRequestedInfo) {
                asnOutputStream.writeNull(Tag.CLASS_CONTEXT_SPECIFIC, _ID_mnpRequestedInfo);
            }
        } catch (IOException e) {
            throw new MAPException("IOException when encoding parameter mnpRequestedInfo: ", e);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding parameter mnpRequestedInfo: ", e);
        }

        try {
            if (this.locationInformationEPSSupported) {
                asnOutputStream.writeNull(2, 11);
            }

        } catch (IOException liesIoe) {
            throw new MAPException("IOException when encoding parameter locationInformationEPSSupported: ", liesIoe);
        } catch (AsnException liesAsne) {
            throw new MAPException("AsnException when encoding parameter locationInformationEPSSupported: ", liesAsne);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.map.api.service.subscriberInformation.RequestedInfo#getLocationInformation()
     */
    public boolean getLocationInformation() {
        return this.locationInformation;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.map.api.service.subscriberInformation.RequestedInfo#getSubscriberState()
     */
    public boolean getSubscriberState() {
        return this.subscriberState;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.map.api.service.subscriberInformation.RequestedInfo#getExtensionContainer()
     */
    public MAPExtensionContainer getExtensionContainer() {
        return this.extensionContainer;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.map.api.service.subscriberInformation.RequestedInfo#getCurrentLocation()
     */
    public boolean getCurrentLocation() {
        return this.currentLocation;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.map.api.service.subscriberInformation.RequestedInfo#getRequestedDomain()
     */
    public DomainType getRequestedDomain() {
        return this.requestedDomain;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.map.api.service.subscriberInformation.RequestedInfo#getImei()
     */
    public boolean getImei() {
        return this.imei;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.map.api.service.subscriberInformation.RequestedInfo#getMsClassmark()
     */
    public boolean getMsClassmark() {
        return this.msClassmark;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.map.api.service.subscriberInformation.RequestedInfo#getMnpRequestedInfo()
     */
    public boolean getMnpRequestedInfo() {
        return this.mnpRequestedInfo;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.map.api.service.subscriberInformation.RequestedInfo#getTadsData()
     */
    public boolean getTadsData() {
        return tadsData;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.map.api.service.subscriberInformation.RequestedInfo#getRequestedNodes()
     */
    public RequestedServingNode getRequestedNodes() {
        return requestedNodes;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.map.api.service.subscriberInformation.RequestedInfo#getServingNodeIndication()
     */
    public boolean getServingNodeIndication() {
        return servingNodeIndication;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.map.api.service.subscriberInformation.RequestedInfo#getLocationInformationEPSSupported()
     */
    public boolean getLocationInformationEPSSupported() {
        return locationInformationEPSSupported;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.ss7.map.api.service.subscriberInformation.RequestedInfo#getLocalTimeZoneRequest()
     */
    public boolean getLocalTimeZoneRequest() {
        return localTimeZoneRequest;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (locationInformation) {
            sb.append(", locationInformation");
        }

        if (subscriberState) {
            sb.append(", subscriberState");
        }

        if (this.extensionContainer != null) {
            sb.append(", extensionContainer=");
            sb.append(this.extensionContainer);
        }

        if (currentLocation) {
            sb.append(", currentLocation");
        }

        if (this.requestedDomain != null) {
            sb.append(", requestedDomain=");
            sb.append(this.requestedDomain);
        }

        if (imei) {
            sb.append(", imei");
        }

        if (msClassmark) {
            sb.append(", msClassmark");
        }

        if (mnpRequestedInfo) {
            sb.append(", mnpRequestedInfo");
        }

        if (tadsData) {
            sb.append(", tadsData");
        }

        if (this.requestedNodes != null) {
            sb.append(", requestedNodes=");
            sb.append(this.requestedNodes);
        }

        if (servingNodeIndication) {
            sb.append(", servingNodeIndication");
        }

        if (this.locationInformationEPSSupported) {
            sb.append(", locationInformationEPSSupported");
        }

        if (localTimeZoneRequest) {
            sb.append(", localTimeZoneRequest");
        }

        sb.append("]");
        return sb.toString();
    }
}
