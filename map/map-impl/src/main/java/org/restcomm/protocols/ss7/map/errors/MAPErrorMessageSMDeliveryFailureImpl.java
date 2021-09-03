
package org.restcomm.protocols.ss7.map.errors;

import java.io.IOException;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.ByteArrayContainer;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.errors.MAPErrorCode;
import org.restcomm.protocols.ss7.map.api.errors.MAPErrorMessageSMDeliveryFailure;
import org.restcomm.protocols.ss7.map.api.errors.SMEnumeratedDeliveryFailureCause;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.smstpdu.SmsDeliverReportTpdu;
import org.restcomm.protocols.ss7.map.api.smstpdu.SmsTpdu;
import org.restcomm.protocols.ss7.map.api.smstpdu.SmsTpduType;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;
import org.restcomm.protocols.ss7.map.smstpdu.SmsTpduImpl;

/**
 *
 * @author sergey vetyutnev
 * @author amit bhayani
 */
public class MAPErrorMessageSMDeliveryFailureImpl extends MAPErrorMessageImpl implements MAPErrorMessageSMDeliveryFailure {

    private static final String MAP_PROTOCOL_VERSION = "mapProtocolVersion";
    private static final String SM_ENUMERATE_DEL_FAIL_CAUSE = "sMEnumeratedDeliveryFailureCause";
    private static final String SIGNAL_INFO = "signalInfo";
    private static final String MAP_EXTENSION_CONTAINER = "mapExtensionContainer";

    private long mapProtocolVersion;
    private SMEnumeratedDeliveryFailureCause smEnumeratedDeliveryFailureCause;
    private byte[] signalInfo;
    private MAPExtensionContainer extensionContainer;

    public MAPErrorMessageSMDeliveryFailureImpl(long mapProtocolVersion, SMEnumeratedDeliveryFailureCause smEnumeratedDeliveryFailureCause,
            byte[] signalInfo, MAPExtensionContainer extensionContainer) {
        super((long) MAPErrorCode.smDeliveryFailure);
        this.mapProtocolVersion = mapProtocolVersion;
        this.smEnumeratedDeliveryFailureCause = smEnumeratedDeliveryFailureCause;
        this.signalInfo = signalInfo;
        this.extensionContainer = extensionContainer;
    }

    public MAPErrorMessageSMDeliveryFailureImpl() {
        super((long) MAPErrorCode.smDeliveryFailure);
    }

    public SMEnumeratedDeliveryFailureCause getSMEnumeratedDeliveryFailureCause() {
        return this.smEnumeratedDeliveryFailureCause;
    }

    public byte[] getSignalInfo() {
        return this.signalInfo;
    }

    public long getMapProtocolVersion() {
        return this.mapProtocolVersion;
    }

    public MAPExtensionContainer getExtensionContainer() {
        return this.extensionContainer;
    }

    public void setSMEnumeratedDeliveryFailureCause(SMEnumeratedDeliveryFailureCause sMEnumeratedDeliveryFailureCause) {
        this.smEnumeratedDeliveryFailureCause = sMEnumeratedDeliveryFailureCause;
    }

    public void setSignalInfo(byte[] signalInfo) {
        this.signalInfo = signalInfo;
    }

    public void setExtensionContainer(MAPExtensionContainer extensionContainer) {
        this.extensionContainer = extensionContainer;
    }

    public void setMapProtocolVersion(long mapProtocolVersion) {
        this.mapProtocolVersion = mapProtocolVersion;
    }

    public boolean isEmSMDeliveryFailure() {
        return true;
    }

    public MAPErrorMessageSMDeliveryFailure getEmSMDeliveryFailure() {
        return this;
    }

    public int getTag() throws MAPException {
        if (this.mapProtocolVersion == 1)
            return Tag.ENUMERATED;
        else
            return Tag.SEQUENCE;
    }

    public int getTagClass() {
        return Tag.CLASS_UNIVERSAL;
    }

    public boolean getIsPrimitive() {
        if (this.mapProtocolVersion == 1)
            return true;
        else
            return false;
    }

    public void decodeAll(AsnInputStream asnInputStream) throws MAPParsingComponentException {

        try {
            int length = asnInputStream.readLength();
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding MAPErrorMessageSMDeliveryFailure: "
                    + e.getMessage(), e, MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding MAPErrorMessageSMDeliveryFailure: "
                    + e.getMessage(), e, MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    public void decodeData(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException {
        try {
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding MAPErrorMessageSMDeliveryFailure: "
                    + e.getMessage(), e, MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding MAPErrorMessageSMDeliveryFailure: "
                    + e.getMessage(), e, MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    private void _decode(AsnInputStream localAsnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {

        this.smEnumeratedDeliveryFailureCause = null;
        this.signalInfo = null;
        this.extensionContainer = null;

        if (localAsnInputStream.getTagClass() != Tag.CLASS_UNIVERSAL)
            throw new MAPParsingComponentException("Error decoding MAPErrorMessageSMDeliveryFailure: bad tag class",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        switch (localAsnInputStream.getTag()) {
            case Tag.ENUMERATED:
                if (!localAsnInputStream.isTagPrimitive())
                    throw new MAPParsingComponentException(
                            "Error decoding MAPErrorMessageSMDeliveryFailure: ENUMERATED tag but data is not primitive",
                            MAPParsingComponentExceptionReason.MistypedParameter);
                int code = (int) localAsnInputStream.readIntegerData(length);
                this.smEnumeratedDeliveryFailureCause = SMEnumeratedDeliveryFailureCause.getInstance(code);
                if (this.smEnumeratedDeliveryFailureCause == null)
                    throw new MAPParsingComponentException(
                            "Error decoding MAPErrorMessageSMDeliveryFailure.smEnumeratedDeliveryFailureCause: bad code value",
                            MAPParsingComponentExceptionReason.MistypedParameter);
                this.mapProtocolVersion = 1;
                break;

            case Tag.SEQUENCE:
                if (localAsnInputStream.isTagPrimitive())
                    throw new MAPParsingComponentException(
                            "Error decoding MAPErrorMessageCallBarred: SEQUENCE tag but data is primitive",
                            MAPParsingComponentExceptionReason.MistypedParameter);
                AsnInputStream ais = localAsnInputStream.readSequenceStreamData(length);

                int tag = ais.readTag();
                if (ais.getTagClass() != Tag.CLASS_UNIVERSAL || tag != Tag.ENUMERATED)
                    throw new MAPParsingComponentException(
                            "Error decoding MAPErrorMessageSMDeliveryFailure.smEnumeratedDeliveryFailureCause: bad tag class or tag",
                            MAPParsingComponentExceptionReason.MistypedParameter);
                code = (int) ais.readInteger();
                this.smEnumeratedDeliveryFailureCause = SMEnumeratedDeliveryFailureCause.getInstance(code);
                if (this.smEnumeratedDeliveryFailureCause == null)
                    throw new MAPParsingComponentException(
                            "Error decoding MAPErrorMessageSMDeliveryFailure.smEnumeratedDeliveryFailureCause: bad value",
                            MAPParsingComponentExceptionReason.MistypedParameter);

                while (true) {
                    if (ais.available() == 0)
                        break;

                    tag = ais.readTag();

                    switch (ais.getTagClass()) {
                        case Tag.CLASS_UNIVERSAL:
                            switch (tag) {
                                case Tag.STRING_OCTET:
                                    this.signalInfo = ais.readOctetString();
                                    break;

                                case Tag.SEQUENCE:
                                    this.extensionContainer = new MAPExtensionContainerImpl();
                                    ((MAPExtensionContainerImpl) this.extensionContainer).decodeAll(ais);
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

                this.mapProtocolVersion = 3;
                break;
        }
    }

    public void encodeAll(AsnOutputStream asnOutputStream) throws MAPException {

        this.encodeAll(asnOutputStream, Tag.CLASS_UNIVERSAL, Tag.SEQUENCE);
    }

    public void encodeAll(AsnOutputStream asnOutputStream, int tagClass, int tag) throws MAPException {

        try {
            asnOutputStream.writeTag(tagClass, this.getIsPrimitive(), tag);
            int pos = asnOutputStream.StartContentDefiniteLength();
            this.encodeData(asnOutputStream);
            asnOutputStream.FinalizeContent(pos);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding MAPErrorMessageSMDeliveryFailure: " + e.getMessage(), e);
        }
    }

    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {

        if (this.smEnumeratedDeliveryFailureCause == null)
            throw new MAPException(
                    "Error encoding MAPErrorMessageSMDeliveryFailure: parameter smEnumeratedDeliveryFailureCause must not be null");

        try {
            if (this.mapProtocolVersion < 3) {
                asnOutputStream.writeIntegerData(this.smEnumeratedDeliveryFailureCause.getCode());
            } else {
                asnOutputStream.writeInteger(Tag.CLASS_UNIVERSAL, Tag.ENUMERATED, this.smEnumeratedDeliveryFailureCause.getCode());

                if (this.signalInfo != null)
                    asnOutputStream.writeOctetString(Tag.CLASS_UNIVERSAL, Tag.STRING_OCTET, this.signalInfo);
                if (this.extensionContainer != null)
                    ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOutputStream);
            }
        } catch (IOException e) {
            throw new MAPException("IOException when encoding MAPErrorMessageSMDeliveryFailure: " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding MAPErrorMessageSMDeliveryFailure: " + e.getMessage(), e);
        }
    }

    public SmsDeliverReportTpdu getSmsDeliverReportTpdu() throws MAPException {
        if (this.signalInfo != null) {
            SmsTpdu smsTpdu = SmsTpduImpl.createInstance(this.signalInfo, true, null);
            if (smsTpdu.getSmsTpduType() == SmsTpduType.SMS_DELIVER_REPORT) {
                SmsDeliverReportTpdu smsDeliverReportTpdu = (SmsDeliverReportTpdu) smsTpdu;
                return smsDeliverReportTpdu;
            }
        }
        return null;
    }

    public void setSmsDeliverReportTpdu(SmsDeliverReportTpdu smsDeliverReportTpdu) throws MAPException {
        this.signalInfo = smsDeliverReportTpdu.encodeData();
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("MAPErrorMessageSMDeliveryFailure [");
        if (this.smEnumeratedDeliveryFailureCause != null)
            sb.append("smEnumeratedDeliveryFailureCause=" + this.smEnumeratedDeliveryFailureCause.toString());
        if (this.signalInfo != null)
            sb.append(", signalInfo=" + this.printDataArr(this.signalInfo));
        if (this.extensionContainer != null)
            sb.append(", extensionContainer=" + this.extensionContainer.toString());
        sb.append("]");

        return sb.toString();
    }

    private String printDataArr(byte[] data) {
        StringBuilder sb = new StringBuilder();
        if (data != null) {
            for (int b : data) {
                sb.append(b);
                sb.append(", ");
            }
        }

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<MAPErrorMessageSMDeliveryFailureImpl> MAP_ERROR_MESSAGE_SM_DEL_FAILURE_XML = new XMLFormat<MAPErrorMessageSMDeliveryFailureImpl>(
            MAPErrorMessageSMDeliveryFailureImpl.class) {

        // TODO: we need to think of parsing of SignallingInfo into XML components (now we just write a byte array)

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, MAPErrorMessageSMDeliveryFailureImpl errorMessage)
                throws XMLStreamException {
            MAP_ERROR_MESSAGE_XML.read(xml, errorMessage);
            errorMessage.mapProtocolVersion = xml.get(MAP_PROTOCOL_VERSION, Long.class);

            String str = xml.get(SM_ENUMERATE_DEL_FAIL_CAUSE, String.class);
            if (str != null)
                errorMessage.smEnumeratedDeliveryFailureCause = Enum.valueOf(SMEnumeratedDeliveryFailureCause.class, str);

            ByteArrayContainer bc = xml.get(SIGNAL_INFO, ByteArrayContainer.class);
            if (bc != null) {
                errorMessage.signalInfo = bc.getData();
            }

            errorMessage.extensionContainer = xml.get(MAP_EXTENSION_CONTAINER, MAPExtensionContainerImpl.class);
        }

        @Override
        public void write(MAPErrorMessageSMDeliveryFailureImpl errorMessage, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {
            MAP_ERROR_MESSAGE_XML.write(errorMessage, xml);
            xml.add(errorMessage.getMapProtocolVersion(), MAP_PROTOCOL_VERSION, Long.class);

            if (errorMessage.getSMEnumeratedDeliveryFailureCause() != null)
                xml.add((String) errorMessage.getSMEnumeratedDeliveryFailureCause().toString(), SM_ENUMERATE_DEL_FAIL_CAUSE,
                        String.class);

            if (errorMessage.signalInfo != null) {
                ByteArrayContainer bac = new ByteArrayContainer(errorMessage.signalInfo);
                xml.add(bac, SIGNAL_INFO, ByteArrayContainer.class);
            }

            xml.add((MAPExtensionContainerImpl) errorMessage.extensionContainer, MAP_EXTENSION_CONTAINER,
                    MAPExtensionContainerImpl.class);
        }
    };
}
