
package org.restcomm.protocols.ss7.cap;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.cap.api.CAPDialog;
import org.restcomm.protocols.ss7.cap.api.CAPMessage;

/**
 *
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public abstract class MessageImpl implements CAPMessage {

    private static final String INVOKE_ID = "invokeId";

    private long invokeId;
    private CAPDialog capDialog;

    public long getInvokeId() {
        return this.invokeId;
    }

    public CAPDialog getCAPDialog() {
        return this.capDialog;
    }

    public void setInvokeId(long invokeId) {
        this.invokeId = invokeId;
    }

    public void setCAPDialog(CAPDialog capDialog) {
        this.capDialog = capDialog;
    }

    protected void addInvokeIdInfo(StringBuilder sb) {
        sb.append("InvokeId=");
        sb.append(this.invokeId);
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<MessageImpl> CAP_MESSAGE_XML = new XMLFormat<MessageImpl>(MessageImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, MessageImpl message) throws XMLStreamException {
            message.invokeId = xml.getAttribute(INVOKE_ID, -1L);
        }

        @Override
        public void write(MessageImpl message, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            xml.setAttribute(INVOKE_ID, message.invokeId);
        }
    };
}
