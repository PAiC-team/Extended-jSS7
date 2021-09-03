
package org.restcomm.protocols.ss7.map.service.supplementary;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.map.MessageImpl;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.datacoding.CBSDataCodingScheme;
import org.restcomm.protocols.ss7.map.api.primitives.USSDString;
import org.restcomm.protocols.ss7.map.api.service.supplementary.MAPDialogSupplementary;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SupplementaryMessage;
import org.restcomm.protocols.ss7.map.datacoding.CBSDataCodingSchemeImpl;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;
import org.restcomm.protocols.ss7.map.primitives.USSDStringImpl;

/**
 * @author amit bhayani
 *
 */
public abstract class SupplementaryMessageImpl extends MessageImpl implements SupplementaryMessage, MAPAsnPrimitive {

    private static final Logger logger = Logger.getLogger(SupplementaryMessageImpl.class);

    private static final String DATA_CODING_SCHEME = "dataCodingScheme";
    private static final String STRING = "string";

    private static final byte DEFAULT_DATA_CODING_SCHEME = 0x0f;
    private static final String DEFAULT_USSD_STRING = "";

    protected CBSDataCodingScheme ussdDataCodingSch;
    protected USSDString ussdString;

    /**
     *
     */
    public SupplementaryMessageImpl() {
        super();
    }

    public SupplementaryMessageImpl(CBSDataCodingScheme ussdDataCodingSch, USSDString ussdString) {
        this.ussdDataCodingSch = ussdDataCodingSch;
        this.ussdString = ussdString;
    }

    public MAPDialogSupplementary getMAPDialog() {
        return (MAPDialogSupplementary) super.getMAPDialog();
    }

    public CBSDataCodingScheme getDataCodingScheme() {
        return ussdDataCodingSch;
    }

    public USSDString getUSSDString() {
        return this.ussdString;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(", ussdDataCodingSch=");
        sb.append(ussdDataCodingSch);
        if (ussdString != null) {
            sb.append(", ussdString=");
            try {
                sb.append(ussdString.getString(null));
            } catch (Exception e) {
            }
        }

        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<SupplementaryMessageImpl> USSD_MESSAGE_XML = new XMLFormat<SupplementaryMessageImpl>(
            SupplementaryMessageImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, SupplementaryMessageImpl ussdMessage)
                throws XMLStreamException {
            MAP_MESSAGE_XML.read(xml, ussdMessage);

            int cbs = xml.getAttribute(DATA_CODING_SCHEME,-1);
            if(cbs != -1){
                ussdMessage.ussdDataCodingSch = new CBSDataCodingSchemeImpl(cbs);
            }

            String encodedString = xml.getAttribute(STRING, null);
            if (encodedString != null)
                try {
                    ussdMessage.ussdString = new USSDStringImpl(encodedString, ussdMessage.ussdDataCodingSch, null);
                } catch (MAPException e) {
                    logger.error("Error while trying to read ussd string", e);
                }
        }

        @Override
        public void write(SupplementaryMessageImpl ussdMessage, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {
            MAP_MESSAGE_XML.write(ussdMessage, xml);
            if (ussdMessage.ussdDataCodingSch != null)
                xml.setAttribute(DATA_CODING_SCHEME, ussdMessage.ussdDataCodingSch.getCode());

            if (ussdMessage.ussdString != null)
                try {
                    String ussdStr = ussdMessage.ussdString.getString(null);
                    if (ussdStr != null)
                        xml.setAttribute(STRING, ussdStr);
                } catch (MAPException e) {
                    logger.error("Error while trying to write ussd string", e);
                }

        }
    };
}
