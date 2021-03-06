
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
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.UserCSGInformation;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.CSGId;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.CSGIdImpl;

/**
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public class UserCSGInformationImpl implements UserCSGInformation, MAPAsnPrimitive {

    private static final String _PrimitiveName = "UserCSGInformation";

    public static final int _ID_csgId = 0;
    public static final int _ID_extensionContainer = 1;
    public static final int _ID_accessMode = 2;
    public static final int _ID_cmi = 3;

    private static final String CSG_ID = "csgId";
    private static final String EXTENSION_CONTAINER = "extensionContainer";
    private static final String ACCESS_MODE = "accessMode";
    private static final String CMI = "cmi";

    private CSGId csgId;
    private MAPExtensionContainer extensionContainer;
    private Integer accessMode;
    private Integer cmi;

    public UserCSGInformationImpl() {
    }

    public UserCSGInformationImpl(CSGId csgId, MAPExtensionContainer extensionContainer, Integer accessMode, Integer cmi) {
        this.csgId = csgId;
        this.extensionContainer = extensionContainer;
        this.accessMode = accessMode;
        this.cmi = cmi;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.subscriberInformation. UserCSGInformation#getCSGId()
     */
    public CSGId getCSGId() {
        return this.csgId;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.subscriberInformation. UserCSGInformation#getExtensionContainer()
     */
    public MAPExtensionContainer getExtensionContainer() {
        return this.extensionContainer;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.subscriberInformation. UserCSGInformation#getAccessMode()
     */
    public Integer getAccessMode() {
        return this.accessMode;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.subscriberInformation. UserCSGInformation#getCmi()
     */
    public Integer getCmi() {
        return this.cmi;
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
        this.csgId = null;
        this.extensionContainer = null;
        this.accessMode = null;
        this.cmi = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);

        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();
            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {

                switch (tag) {
                    case _ID_csgId:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + " csgId: Parameter is not primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);
                        this.csgId = new CSGIdImpl();
                        ((CSGIdImpl) this.csgId).decodeAll(ais);
                        break;
                    case _ID_extensionContainer:
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + " extensionContainer: Parameter is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);
                        this.extensionContainer = new MAPExtensionContainerImpl();
                        ((MAPExtensionContainerImpl) this.extensionContainer).decodeAll(ais);
                        break;
                    case _ID_accessMode:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + " accessMode: Parameter is not primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);
                        byte[] buf = ais.readOctetString();
                        if (buf.length != 1)
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + " accessMode: Parameter length must be 1, found: " + buf.length,
                                    MAPParsingComponentExceptionReason.MistypedParameter);
                        this.accessMode = (int) buf[0];
                        break;
                    case _ID_cmi:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + " cmi: Parameter is not primitive", MAPParsingComponentExceptionReason.MistypedParameter);
                        buf = ais.readOctetString();
                        if (buf.length != 1)
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + " cmi: Parameter length must be 1, found: " + buf.length,
                                    MAPParsingComponentExceptionReason.MistypedParameter);
                        this.cmi = (int) buf[0];
                        break;

                    default:
                        ais.advanceElement();
                        break;
                }
            } else {
                ais.advanceElement();
            }
        }

        if (this.csgId == null) {
            throw new MAPParsingComponentException("Error when decoding " + _PrimitiveName
                    + ": csgId is mandatory but not found", MAPParsingComponentExceptionReason.MistypedParameter);
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

        if (this.csgId == null)
            throw new MAPException("Parametr csgId must not be null");

        try {
            ((CSGIdImpl) this.csgId).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_csgId);

            if (this.extensionContainer != null)
                ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC,
                        _ID_extensionContainer);

            byte[] buf = new byte[1];
            if (this.accessMode != null) {
                buf[0] = (byte) (int) this.accessMode;
                asnOutputStream.writeOctetString(Tag.CLASS_CONTEXT_SPECIFIC, _ID_accessMode, buf);
            }

            if (this.cmi != null) {
                buf[0] = (byte) (int) this.cmi;
                asnOutputStream.writeOctetString(Tag.CLASS_CONTEXT_SPECIFIC, _ID_cmi, buf);
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
        sb.append("LocationInformation [");

        if (this.csgId != null) {
            sb.append("csgId=");
            sb.append(this.csgId);
        }
        if (this.extensionContainer != null) {
            sb.append(", extensionContainer=");
            sb.append(this.extensionContainer);
        }
        if (this.accessMode != null) {
            sb.append(", accessMode=");
            sb.append(this.accessMode);
        }
        if (this.cmi != null) {
            sb.append(", cmi=");
            sb.append(this.cmi);
        }

        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<UserCSGInformationImpl> USER_CSG_INFORMATION_XML = new XMLFormat<UserCSGInformationImpl>(
            UserCSGInformationImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, UserCSGInformationImpl userCSGInformation)
                throws XMLStreamException {
            userCSGInformation.csgId = xml.get(CSG_ID, CSGIdImpl.class);
            userCSGInformation.extensionContainer = xml.get(EXTENSION_CONTAINER, MAPExtensionContainerImpl.class);
            userCSGInformation.accessMode = xml.get(ACCESS_MODE, Integer.class);
            userCSGInformation.cmi = xml.get(CMI, Integer.class);
        }

        @Override
        public void write(UserCSGInformationImpl userCSGInformation, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {
            if (userCSGInformation.csgId != null) {
                xml.add((CSGIdImpl) userCSGInformation.csgId, CSG_ID, CSGIdImpl.class);
            }
            if (userCSGInformation.extensionContainer != null) {
                xml.add((MAPExtensionContainerImpl) userCSGInformation.extensionContainer, EXTENSION_CONTAINER,
                        MAPExtensionContainerImpl.class);
            }
            if (userCSGInformation.accessMode != null) {
                xml.add((Integer) userCSGInformation.accessMode, ACCESS_MODE, Integer.class);
            }
            if (userCSGInformation.cmi != null) {
                xml.add((Integer) userCSGInformation.cmi, CMI, Integer.class);
            }
        }
    };

}
