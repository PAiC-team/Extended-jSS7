
package org.restcomm.protocols.ss7.map.service.supplementary;

import java.io.IOException;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPMessageType;
import org.restcomm.protocols.ss7.map.api.MAPOperationCode;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.datacoding.CBSDataCodingScheme;
import org.restcomm.protocols.ss7.map.api.primitives.USSDString;
import org.restcomm.protocols.ss7.map.api.service.supplementary.ProcessUnstructuredSSResponse;
import org.restcomm.protocols.ss7.map.datacoding.CBSDataCodingSchemeImpl;
import org.restcomm.protocols.ss7.map.primitives.USSDStringImpl;

/**
 *
 * @author amit bhayani
 *
 */
public class ProcessUnstructuredSSResponseImpl extends SupplementaryMessageImpl implements ProcessUnstructuredSSResponse {

    public ProcessUnstructuredSSResponseImpl() {
        super();
    }

    /**
     * @param ussdDataCodingSch
     * @param ussdString
     */
    public ProcessUnstructuredSSResponseImpl(CBSDataCodingScheme ussdDataCodingSch, USSDString ussdString) {
        super(ussdDataCodingSch, ussdString);
    }

    public MAPMessageType getMessageType() {
        return MAPMessageType.processUnstructuredSSRequest_Response;
    }

    public int getOperationCode() {
        return MAPOperationCode.processUnstructuredSS_Request;
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

    public void decodeAll(AsnInputStream asnInputStream) throws MAPParsingComponentException {
        try {
            int length = asnInputStream.readLength();
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding ProcessUnstructuredSSResponseIndication: "
                    + e.getMessage(), e, MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding ProcessUnstructuredSSResponseIndication: "
                    + e.getMessage(), e, MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    public void decodeData(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException {
        try {
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding ProcessUnstructuredSSResponseIndication: "
                    + e.getMessage(), e, MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding ProcessUnstructuredSSResponseIndication: "
                    + e.getMessage(), e, MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    private void _decode(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {
        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);

        int tag = ais.readTag();

        // ussd-DataCodingScheme USSD-DataCodingScheme
        if (ais.getTagClass() != Tag.CLASS_UNIVERSAL || !ais.isTagPrimitive())
            throw new MAPParsingComponentException(
                    "Error while decoding ProcessUnstructuredSSResponseIndication: Parameter ussd-DataCodingScheme bad tag class or not primitive",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        int length1 = ais.readLength();
        this.ussdDataCodingSch = new CBSDataCodingSchemeImpl(ais.readOctetStringData(length1)[0]);

        tag = ais.readTag();

        // ussd-String USSD-String
        if (ais.getTagClass() != Tag.CLASS_UNIVERSAL || !ais.isTagPrimitive())
            throw new MAPParsingComponentException(
                    "Error while decoding ProcessUnstructuredSSResponseIndication: Parameter ussd-String bad tag class or not primitive",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        this.ussdString = new USSDStringImpl(this.ussdDataCodingSch);
        ((USSDStringImpl) this.ussdString).decodeAll(ais);
    }

    public void encodeAll(AsnOutputStream asnOutputStream) throws MAPException {

        this.encodeAll(asnOutputStream, Tag.CLASS_UNIVERSAL, Tag.SEQUENCE);
    }

    public void encodeAll(AsnOutputStream asnOutputStream, int tagClass, int tag) throws MAPException {
        try {
            asnOutputStream.writeTag(tagClass, false, tag);
            int pos = asnOutputStream.StartContentDefiniteLength();
            this.encodeData(asnOutputStream);
            asnOutputStream.FinalizeContent(pos);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding ProcessUnstructuredSSResponseIndication", e);
        }
    }

    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        if (this.ussdString == null)
            throw new MAPException("ussdString must not be null");

        try {
            asnOutputStream.writeOctetString(new byte[] { (byte) this.ussdDataCodingSch.getCode() });

            ((USSDStringImpl) this.ussdString).encodeAll(asnOutputStream);

        } catch (IOException e) {
            throw new MAPException("IOException when encoding ProcessUnstructuredSSResponseIndication", e);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding ProcessUnstructuredSSResponseIndication", e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ProcessUnstructuredSSResponse [");
        if (this.getMAPDialog() != null) {
            sb.append("DialogId=").append(this.getMAPDialog().getLocalDialogId());
        }
        sb.append(super.toString());
        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<ProcessUnstructuredSSResponseImpl> PROCESS_UNSTRUCTURED_SS_RESPONSE_XML = new XMLFormat<ProcessUnstructuredSSResponseImpl>(
            ProcessUnstructuredSSResponseImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, ProcessUnstructuredSSResponseImpl ussdMessage)
                throws XMLStreamException {
            USSD_MESSAGE_XML.read(xml, ussdMessage);

        }

        @Override
        public void write(ProcessUnstructuredSSResponseImpl ussdMessage, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {
            USSD_MESSAGE_XML.write(ussdMessage, xml);
        }
    };

}
