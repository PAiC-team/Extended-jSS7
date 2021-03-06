
package org.restcomm.protocols.ss7.map.errors;

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
import org.restcomm.protocols.ss7.map.api.errors.MAPErrorCode;
import org.restcomm.protocols.ss7.map.api.errors.MAPErrorMessageSsIncompatibility;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.BasicServiceCode;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSCode;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSStatus;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.BasicServiceCodeImpl;
import org.restcomm.protocols.ss7.map.service.supplementary.SSCodeImpl;
import org.restcomm.protocols.ss7.map.service.supplementary.SSStatusImpl;

/**
 *
 * @author sergey vetyutnev
 * @author amit bhayani
 */
public class MAPErrorMessageSsIncompatibilityImpl extends MAPErrorMessageImpl implements MAPErrorMessageSsIncompatibility {

    private static final String SS_CODE = "ssCode";
    private static final String BASIC_SERVICE = "basicService";
    private static final String SS_STATUS = "ssStatus";

    public static final int _tag_ss_Code = 1;
    public static final int _tag_ss_Status = 4;

    private SSCode ssCode;
    private BasicServiceCode basicService;
    private SSStatus ssStatus;

    protected String _PrimitiveName = "MAPErrorMessageSsIncompatibility";

    public MAPErrorMessageSsIncompatibilityImpl(SSCode ssCode, BasicServiceCode basicService, SSStatus ssStatus) {
        super((long) MAPErrorCode.ssIncompatibility);

        this.ssCode = ssCode;
        this.basicService = basicService;
        this.ssStatus = ssStatus;
    }

    public MAPErrorMessageSsIncompatibilityImpl() {
        super((long) MAPErrorCode.ssIncompatibility);
    }

    public boolean isEmSsIncompatibility() {
        return true;
    }

    public MAPErrorMessageSsIncompatibility getEmSsIncompatibility() {
        return this;
    }

    @Override
    public SSCode getSSCode() {
        return ssCode;
    }

    @Override
    public BasicServiceCode getBasicService() {
        return basicService;
    }

    @Override
    public SSStatus getSSStatus() {
        return ssStatus;
    }

    @Override
    public void setSSCode(SSCode val) {
        ssCode = val;
    }

    @Override
    public void setBasicService(BasicServiceCode val) {
        basicService = val;
    }

    @Override
    public void setSSStatus(SSStatus val) {
        ssStatus = val;
    }

    public int getTag() throws MAPException {
        return Tag.SEQUENCE;
    }

    public int getTagClass() {
        return Tag.CLASS_UNIVERSAL;
    }

    public boolean getIsPrimitive() {
        return false;
    }

    @Override
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

    @Override
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

    private void _decode(AsnInputStream localAsnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {

        this.ssCode = null;
        this.basicService = null;
        this.ssStatus = null;

        if (localAsnInputStream.getTagClass() != Tag.CLASS_UNIVERSAL || localAsnInputStream.getTag() != Tag.SEQUENCE
            || localAsnInputStream.isTagPrimitive())
            throw new MAPParsingComponentException("Error decoding " + _PrimitiveName
                    + ": bad tag class or tag or parameter is primitive", MAPParsingComponentExceptionReason.MistypedParameter);

        AsnInputStream ais = localAsnInputStream.readSequenceStreamData(length);

        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            switch (ais.getTagClass()) {
                case Tag.CLASS_CONTEXT_SPECIFIC:
                    switch (tag) {
                        case _tag_ss_Code:
                            this.ssCode = new SSCodeImpl();
                            ((SSCodeImpl) this.ssCode).decodeAll(ais);
                            break;
                        case BasicServiceCodeImpl._TAG_bearerService:
                        case BasicServiceCodeImpl._TAG_teleservice:
                            // AsnInputStream ais2 = ais.readSequenceStream();
                            // ais2.readTag();
                            this.basicService = new BasicServiceCodeImpl();
                            ((BasicServiceCodeImpl) this.basicService).decodeAll(ais);
                            break;
                        case _tag_ss_Status:
                            this.ssStatus = new SSStatusImpl();
                            ((SSStatusImpl) this.ssStatus).decodeAll(ais);
                            break;

                        default:
                            ais.advanceElement();
                            break;
                    }
                    break;

                default:
                    ais.advanceElement();
                    break;
            }
        }
    }

    @Override
    public void encodeAll(AsnOutputStream asnOutputStream) throws MAPException {

        this.encodeAll(asnOutputStream, this.getTagClass(), this.getTag());
    }

    @Override
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

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {

        if (this.ssCode == null && this.basicService == null && this.ssStatus == null)
            return;

        if (this.ssCode != null)
            ((SSCodeImpl) this.ssCode).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _tag_ss_Code);
        if (this.basicService != null) {
            ((BasicServiceCodeImpl) this.basicService).encodeAll(asnOutputStream,
                    ((BasicServiceCodeImpl) this.basicService).getTagClass(),
                    ((BasicServiceCodeImpl) this.basicService).getTag());
        }
        if (this.ssStatus != null)
            ((SSStatusImpl) this.ssStatus).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _tag_ss_Status);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.ssCode != null)
            sb.append("ssCode=" + this.ssCode.toString());
        if (this.basicService != null)
            sb.append(", basicService=" + this.basicService.toString());
        if (this.ssStatus != null)
            sb.append(", ssStatus=" + this.ssStatus.toString());
        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<MAPErrorMessageSsIncompatibilityImpl> MAP_ERROR_MESSAGE_SS_INCOMPATIBILITY_XML = new XMLFormat<MAPErrorMessageSsIncompatibilityImpl>(
            MAPErrorMessageSsIncompatibilityImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, MAPErrorMessageSsIncompatibilityImpl errorMessage)
                throws XMLStreamException {
            MAP_ERROR_MESSAGE_XML.read(xml, errorMessage);
            errorMessage.ssCode = xml.get(SS_CODE, SSCodeImpl.class);
            errorMessage.basicService = xml.get(BASIC_SERVICE, BasicServiceCodeImpl.class);
            errorMessage.ssStatus = xml.get(SS_STATUS, SSStatusImpl.class);
        }

        @Override
        public void write(MAPErrorMessageSsIncompatibilityImpl errorMessage, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {
            MAP_ERROR_MESSAGE_XML.write(errorMessage, xml);
            xml.add((SSCodeImpl) errorMessage.ssCode, SS_CODE, SSCodeImpl.class);
            xml.add((BasicServiceCodeImpl) errorMessage.getBasicService(), BASIC_SERVICE, BasicServiceCodeImpl.class);
            xml.add((SSStatusImpl) errorMessage.getSSStatus(), SS_STATUS, SSStatusImpl.class);
        }
    };

}
