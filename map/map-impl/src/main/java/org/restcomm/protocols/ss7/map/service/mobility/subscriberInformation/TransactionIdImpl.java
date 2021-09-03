
package org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation;

import jakarta.xml.bind.DatatypeConverter;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.TransactionId;
import org.restcomm.protocols.ss7.map.primitives.OctetStringBase;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class TransactionIdImpl extends OctetStringBase implements TransactionId {

    private static final String DATA = "data";

    private static final String DEFAULT_VALUE = null;

    public TransactionIdImpl() {
        super(1, 2, "TransactionId");
    }

    public TransactionIdImpl(byte[] data) {
        super(1, 2, "TransactionId", data);
    }

    public byte[] getData() {
        return data;
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<TransactionIdImpl> TRANSACTION_ID_XML = new XMLFormat<TransactionIdImpl>(TransactionIdImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, TransactionIdImpl transactionId) throws XMLStreamException {
            String s = xml.getAttribute(DATA, DEFAULT_VALUE);
            if (s != null) {
                transactionId.data = DatatypeConverter.parseHexBinary(s);
            }
        }

        @Override
        public void write(TransactionIdImpl transactionId, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            if (transactionId.data != null) {
                xml.setAttribute(DATA, DatatypeConverter.printHexBinary(transactionId.data));
            }
        }
    };
}
