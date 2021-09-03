
package org.restcomm.protocols.ss7.map;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.map.api.MAPDialog;
import org.restcomm.protocols.ss7.map.api.MAPMessage;

/**
 *
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public abstract class MessageImpl implements MAPMessage {

    private static final String INVOKE_ID = "invokeId";
    private static final String RETURN_RESULT_NOT_LAST = "returnResultNotLast";

    private long invokeId;
    private MAPDialog mapDialog;
    private boolean returnResultNotLast = false;

    public long getInvokeId() {
        return this.invokeId;
    }

    public MAPDialog getMAPDialog() {
        return this.mapDialog;
    }

    public void setInvokeId(long invokeId) {
        this.invokeId = invokeId;
    }

    public void setMAPDialog(MAPDialog mapDialog) {
        this.mapDialog = mapDialog;
    }

    public boolean isReturnResultNotLast() {
        return returnResultNotLast;
    }

    public void setReturnResultNotLast(boolean returnResultNotLast) {
        this.returnResultNotLast = returnResultNotLast;
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<MessageImpl> MAP_MESSAGE_XML = new XMLFormat<MessageImpl>(MessageImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, MessageImpl message) throws XMLStreamException {
            message.invokeId = xml.getAttribute(INVOKE_ID, -1L);
            message.returnResultNotLast = xml.getAttribute(RETURN_RESULT_NOT_LAST, false);
        }

        @Override
        public void write(MessageImpl message, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            xml.setAttribute(INVOKE_ID, message.invokeId);
            if (message.returnResultNotLast)
                xml.setAttribute(RETURN_RESULT_NOT_LAST, message.returnResultNotLast);
        }
    };

}
