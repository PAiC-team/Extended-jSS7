
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

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
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtBasicServiceCode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtBearerServiceCode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtTeleserviceCode;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class ExtBasicServiceCodeImpl implements ExtBasicServiceCode, MAPAsnPrimitive {

    private static final String EXT_BEARER_SERVICE = "extBearerService";
    private static final String EXT_TELE_SERVICE = "extTeleservice";

    public static final int _ID_ext_BearerService = 2;
    public static final int _ID_ext_Teleservice = 3;

    public static final String _PrimitiveName = "ExtBasicServiceCode";

    private ExtBearerServiceCode extBearerService;
    private ExtTeleserviceCode extTeleservice;

    public ExtBasicServiceCodeImpl() {
    }

    public ExtBasicServiceCodeImpl(ExtBearerServiceCode extBearerService) {
        this.extBearerService = extBearerService;
    }

    public ExtBasicServiceCodeImpl(ExtTeleserviceCode extTeleserviceCode) {
        this.extTeleservice = extTeleserviceCode;
    }

    public ExtBearerServiceCode getExtBearerService() {
        return extBearerService;
    }

    public ExtTeleserviceCode getExtTeleservice() {
        return extTeleservice;
    }

    public int getTag() throws MAPException {
        if (extBearerService != null)
            return _ID_ext_BearerService;
        else
            return _ID_ext_Teleservice;
    }

    public int getTagClass() {
        return Tag.CLASS_CONTEXT_SPECIFIC;
    }

    public boolean getIsPrimitive() {
        return true;
    }

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
        this.extBearerService = null;
        this.extTeleservice = null;

        int tag = asnInputStream.getTag();

        if (asnInputStream.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
            switch (tag) {
                case _ID_ext_BearerService:
                    this.extBearerService = new ExtBearerServiceCodeImpl();
                    ((ExtBearerServiceCodeImpl) this.extBearerService).decodeData(asnInputStream, length);
                    break;
                case _ID_ext_Teleservice:
                    this.extTeleservice = new ExtTeleserviceCodeImpl();
                    ((ExtTeleserviceCodeImpl) this.extTeleservice).decodeData(asnInputStream, length);
                    break;

                default:
                    throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ": bad choice tag",
                            MAPParsingComponentExceptionReason.MistypedParameter);
            }
        } else {
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ": bad choice tagClass",
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    public void encodeAll(AsnOutputStream asnOutputStream) throws MAPException {
        this.encodeAll(asnOutputStream, this.getTagClass(), this.getTag());
    }

    public void encodeAll(AsnOutputStream asnOutputStream, int tagClass, int tag) throws MAPException {
        try {
            asnOutputStream.writeTag(tagClass, true, tag);
            int pos = asnOutputStream.StartContentDefiniteLength();
            this.encodeData(asnOutputStream);
            asnOutputStream.FinalizeContent(pos);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        if (this.extBearerService == null && this.extTeleservice == null)
            throw new MAPException("Both extBearerService and extTeleservice must not be null");
        if (this.extBearerService != null && this.extTeleservice != null)
            throw new MAPException("Both extBearerService and extTeleservice must not be not null");

        if (this.extBearerService != null) {
            ((ExtBearerServiceCodeImpl) this.extBearerService).encodeData(asnOutputStream);
        } else {
            ((ExtTeleserviceCodeImpl) this.extTeleservice).encodeData(asnOutputStream);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ExtBasicServiceCode [");

        if (this.extBearerService != null) {
            sb.append(this.extBearerService.toString());
        }
        if (this.extTeleservice != null) {
            sb.append(this.extTeleservice.toString());
        }

        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<ExtBasicServiceCodeImpl> EXT_BASIC_SERVICE_CODE_XML = new XMLFormat<ExtBasicServiceCodeImpl>(
            ExtBasicServiceCodeImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, ExtBasicServiceCodeImpl extBasicServiceCode)
                throws XMLStreamException {
            extBasicServiceCode.extBearerService = xml.get(EXT_BEARER_SERVICE, ExtBearerServiceCodeImpl.class);
            extBasicServiceCode.extTeleservice = xml.get(EXT_TELE_SERVICE, ExtTeleserviceCodeImpl.class);

        }

        @Override
        public void write(ExtBasicServiceCodeImpl extBasicServiceCode, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {

            if (extBasicServiceCode.extBearerService != null) {
                xml.add(((ExtBearerServiceCodeImpl) extBasicServiceCode.extBearerService), EXT_BEARER_SERVICE,
                        ExtBearerServiceCodeImpl.class);
            }

            if (extBasicServiceCode.extTeleservice != null) {
                xml.add(((ExtTeleserviceCodeImpl) extBasicServiceCode.extTeleservice), EXT_TELE_SERVICE,
                        ExtTeleserviceCodeImpl.class);
            }
        }
    };
}
