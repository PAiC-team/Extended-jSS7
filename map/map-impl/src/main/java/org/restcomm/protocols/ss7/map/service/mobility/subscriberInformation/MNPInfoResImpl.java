package org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation;

import java.io.IOException;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.MNPInfoRes;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.NumberPortabilityStatus;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.RouteingNumber;
import org.restcomm.protocols.ss7.map.primitives.IMSIImpl;
import org.restcomm.protocols.ss7.map.primitives.ISDNAddressStringImpl;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;

/**
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public class MNPInfoResImpl implements MNPInfoRes, MAPAsnPrimitive {

    public static final String _PrimitiveName = "MNPInfoRes";

    private static final int _ID_routeingNumber = 0;
    private static final int _ID_imsi = 1;
    private static final int _ID_msisdn = 2;
    private static final int _ID_numberPortabilityStatus = 3;
    private static final int _ID_extensionContainer = 4;

    private static final String ROUTEING_NUMBER = "routeingNumber";
    private static final String IMSI = "imsi";
    private static final String MSISDN = "msisdn";
    private static final String NUMBER_PORTABILITY_STATUS = "numberPortabilityStatus";
    private static final String EXTENSION_CONTAINER = "extensionContainer";

    private RouteingNumber routeingNumber;
    private IMSI imsi;
    private ISDNAddressString msisdn;
    private NumberPortabilityStatus numberPortabilityStatus;
    private MAPExtensionContainer extensionContainer;

    /**
     *
     */
    public MNPInfoResImpl() {
    }

    public MNPInfoResImpl(RouteingNumber routeingNumber, IMSI imsi, ISDNAddressString msisdn,
            NumberPortabilityStatus numberPortabilityStatus, MAPExtensionContainer extensionContainer) {
        this.routeingNumber = routeingNumber;
        this.imsi = imsi;
        this.msisdn = msisdn;
        this.numberPortabilityStatus = numberPortabilityStatus;
        this.extensionContainer = extensionContainer;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.subscriberInformation.MNPInfoRes#getRouteingNumber()
     */
    public RouteingNumber getRouteingNumber() {
        return this.routeingNumber;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.subscriberInformation.MNPInfoRes#getIMSI()
     */
    public IMSI getIMSI() {
        return this.imsi;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.subscriberInformation.MNPInfoRes#getMSISDN()
     */
    public ISDNAddressString getMSISDN() {
        return this.msisdn;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.subscriberInformation.MNPInfoRes#getNumberPortabilityStatus()
     */
    public NumberPortabilityStatus getNumberPortabilityStatus() {
        return this.numberPortabilityStatus;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.subscriberInformation.MNPInfoRes#getExtensionContainer()
     */
    public MAPExtensionContainer getExtensionContainer() {
        return this.extensionContainer;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#getTag()
     */
    public int getTag() throws MAPException {
        return Tag.SEQUENCE;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#getTagClass()
     */
    public int getTagClass() {
        return Tag.CLASS_UNIVERSAL;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#getIsPrimitive ()
     */
    public boolean getIsPrimitive() {
        return false;
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
        this.routeingNumber = null;
        this.imsi = null;
        this.msisdn = null;
        this.numberPortabilityStatus = null;
        this.extensionContainer = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);

        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {

                switch (tag) {
                    case _ID_routeingNumber:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + " routeingNumber: Parameter is not primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);
                        this.routeingNumber = new RouteingNumberImpl();
                        ((RouteingNumberImpl) this.routeingNumber).decodeAll(ais);
                        break;
                    case _ID_imsi:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + " imsi: Parameter is not primitive", MAPParsingComponentExceptionReason.MistypedParameter);
                        this.imsi = new IMSIImpl();
                        ((IMSIImpl) this.imsi).decodeAll(ais);
                        break;
                    case _ID_msisdn:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + " msisdn: Parameter is not primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);
                        this.msisdn = new ISDNAddressStringImpl();
                        ((ISDNAddressStringImpl) this.msisdn).decodeAll(ais);
                        break;
                    case _ID_numberPortabilityStatus:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + " numberPortabilityStatus: Parameter is not primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);
                        int i1 = (int) ais.readInteger();
                        this.numberPortabilityStatus = NumberPortabilityStatus.getInstance(i1);
                        break;
                    case _ID_extensionContainer:
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + " extensionContainer: Parameter is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);
                        this.extensionContainer = new MAPExtensionContainerImpl();
                        ((MAPExtensionContainerImpl) this.extensionContainer).decodeAll(ais);
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
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#encodeAll( org.mobicents.protocols.asn.AsnOutputStream)
     */
    public void encodeAll(AsnOutputStream asnOutputStream) throws MAPException {
        this.encodeAll(asnOutputStream, this.getTagClass(), this.getTag());
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#encodeAll( org.mobicents.protocols.asn.AsnOutputStream,
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
        if (this.routeingNumber != null)
            ((RouteingNumberImpl) this.routeingNumber).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_routeingNumber);

        if (this.imsi != null)
            ((IMSIImpl) this.imsi).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_imsi);

        if (this.msisdn != null)
            ((ISDNAddressStringImpl) this.msisdn).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_msisdn);

        if (this.numberPortabilityStatus != null) {
            try {
                asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _ID_numberPortabilityStatus,
                        this.numberPortabilityStatus.getType());
            } catch (IOException e) {
                throw new MAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
            } catch (AsnException e) {
                throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
            }
        }

        if (this.extensionContainer != null)
            ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC,
                    _ID_extensionContainer);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.routeingNumber != null) {
            sb.append("routeingNumber=");
            sb.append(this.routeingNumber);
        }

        if (this.imsi != null) {
            sb.append(", imsi=");
            sb.append(this.imsi);
        }

        if (this.msisdn != null) {
            sb.append(", msisdn=");
            sb.append(this.msisdn);
        }

        if (this.numberPortabilityStatus != null) {
            sb.append(", numberPortabilityStatus=");
            sb.append(this.numberPortabilityStatus);
        }

        if (this.extensionContainer != null) {
            sb.append(", extensionContainer=");
            sb.append(this.extensionContainer);
        }

        sb.append("]");
        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<MNPInfoResImpl> MNP_INFO_RES_XML = new XMLFormat<MNPInfoResImpl>(MNPInfoResImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, MNPInfoResImpl mNPInfoRes)
                throws XMLStreamException {
            mNPInfoRes.routeingNumber = xml.get(ROUTEING_NUMBER, RouteingNumberImpl.class);
            mNPInfoRes.imsi = xml.get(IMSI, IMSIImpl.class);
            mNPInfoRes.msisdn = xml.get(MSISDN, ISDNAddressStringImpl.class);
            String nps = xml.get(NUMBER_PORTABILITY_STATUS, String.class);
            if (nps != null) {
                mNPInfoRes.numberPortabilityStatus = Enum.valueOf(NumberPortabilityStatus.class, nps);
            }
            mNPInfoRes.extensionContainer = xml.get(EXTENSION_CONTAINER, MAPExtensionContainerImpl.class);
        }

        @Override
        public void write(MNPInfoResImpl mNPInfoRes, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {
            if (mNPInfoRes.routeingNumber != null) {
                xml.add((RouteingNumberImpl) mNPInfoRes.routeingNumber, ROUTEING_NUMBER, RouteingNumberImpl.class);
            }
            if (mNPInfoRes.imsi != null) {
                xml.add((IMSIImpl) mNPInfoRes.imsi, IMSI, IMSIImpl.class);
            }
            if (mNPInfoRes.msisdn != null) {
                xml.add((ISDNAddressStringImpl) mNPInfoRes.msisdn, MSISDN, ISDNAddressStringImpl.class);
            }
            if (mNPInfoRes.numberPortabilityStatus != null) {
                xml.add(mNPInfoRes.numberPortabilityStatus.toString(), NUMBER_PORTABILITY_STATUS, String.class);
            }
            if (mNPInfoRes.extensionContainer != null) {
                xml.add((MAPExtensionContainerImpl) mNPInfoRes.extensionContainer, EXTENSION_CONTAINER, MAPExtensionContainerImpl.class);
            }
        }
    };
}
