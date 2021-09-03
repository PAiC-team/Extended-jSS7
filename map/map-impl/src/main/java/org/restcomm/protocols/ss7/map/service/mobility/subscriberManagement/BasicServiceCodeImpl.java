
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
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.BasicServiceCode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.BearerServiceCode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.TeleserviceCode;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;

/**
 *
 * @author sergey vetyutnev
 * @author amit bhayani
 *
 */
public class BasicServiceCodeImpl implements BasicServiceCode, MAPAsnPrimitive {

    private static final String BEARER_SERVICE = "bearerService";
    private static final String TELE_SERVICE = "teleservice";

    public static final int _TAG_bearerService = 2;
    public static final int _TAG_teleservice = 3;

    public static final String _PrimitiveName = "BasicServiceCode";

    private BearerServiceCode bearerService;
    private TeleserviceCode teleservice;

    public BasicServiceCodeImpl() {
    }

    public BasicServiceCodeImpl(TeleserviceCode teleservice) {
        this.teleservice = teleservice;
    }

    public BasicServiceCodeImpl(BearerServiceCode bearerService) {
        this.bearerService = bearerService;
    }

    @Override
    public BearerServiceCode getBearerService() {
        return bearerService;
    }

    @Override
    public TeleserviceCode getTeleservice() {
        return teleservice;
    }

    @Override
    public int getTag() throws MAPException {
        if (bearerService != null)
            return _TAG_bearerService;
        else
            return _TAG_teleservice;
    }

    @Override
    public int getTagClass() {
        return Tag.CLASS_CONTEXT_SPECIFIC;
    }

    @Override
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
        this.bearerService = null;
        this.teleservice = null;

        int tag = asnInputStream.getTag();

        if (asnInputStream.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
            switch (tag) {
                case _TAG_bearerService:
                    if (!asnInputStream.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".bearerService: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.bearerService = new BearerServiceCodeImpl();
                    ((BearerServiceCodeImpl) this.bearerService).decodeData(asnInputStream, length);
                    break;
                case _TAG_teleservice:
                    if (!asnInputStream.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".teleservice: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.teleservice = new TeleserviceCodeImpl();
                    ((TeleserviceCodeImpl) this.teleservice).decodeData(asnInputStream, length);
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
            asnOutputStream.writeTag(tagClass, this.getIsPrimitive(), tag);
            int pos = asnOutputStream.StartContentDefiniteLength();
            this.encodeData(asnOutputStream);
            asnOutputStream.FinalizeContent(pos);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        if (this.bearerService == null && this.teleservice == null || this.bearerService != null && this.teleservice != null) {
            throw new MAPException("Error while decoding " + _PrimitiveName + ": One and only one choice must be selected");
        }

        if (this.bearerService != null) {
            ((BearerServiceCodeImpl) this.bearerService).encodeData(asnOutputStream);
        } else {
            ((TeleserviceCodeImpl) this.teleservice).encodeData(asnOutputStream);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.bearerService != null) {
            sb.append("bearerService=" + this.bearerService.toString());
            sb.append(", ");
        }
        if (this.teleservice != null)
            sb.append("teleservice=" + this.teleservice.toString());
        sb.append("]");

        return sb.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bearerService == null) ? 0 : bearerService.hashCode());
        result = prime * result + ((teleservice == null) ? 0 : teleservice.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BasicServiceCodeImpl other = (BasicServiceCodeImpl) obj;
        if (bearerService == null) {
            if (other.bearerService != null)
                return false;
        } else if (!bearerService.equals(other.bearerService))
            return false;
        if (teleservice == null) {
            if (other.teleservice != null)
                return false;
        } else if (!teleservice.equals(other.teleservice))
            return false;
        return true;
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<BasicServiceCodeImpl> BASIC_SERVICE_CODE_XMLS = new XMLFormat<BasicServiceCodeImpl>(
            BasicServiceCodeImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, BasicServiceCodeImpl ssCode) throws XMLStreamException {
            ssCode.bearerService = xml.get(BEARER_SERVICE, BearerServiceCodeImpl.class);
            ssCode.teleservice = xml.get(TELE_SERVICE, TeleserviceCodeImpl.class);
        }

        @Override
        public void write(BasicServiceCodeImpl ssCode, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            xml.add((BearerServiceCodeImpl) ssCode.getBearerService(), BEARER_SERVICE, BearerServiceCodeImpl.class);
            xml.add((TeleserviceCodeImpl) ssCode.getTeleservice(), TELE_SERVICE, TeleserviceCodeImpl.class);
        }
    };

}
