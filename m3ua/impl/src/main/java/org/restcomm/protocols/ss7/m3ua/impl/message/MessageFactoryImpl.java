
package org.restcomm.protocols.ss7.m3ua.impl.message;

import io.netty.buffer.ByteBuf;

import org.restcomm.protocols.ss7.m3ua.impl.message.aspsm.ASPDownAckImpl;
import org.restcomm.protocols.ss7.m3ua.impl.message.aspsm.ASPDownImpl;
import org.restcomm.protocols.ss7.m3ua.impl.message.aspsm.ASPUpAckImpl;
import org.restcomm.protocols.ss7.m3ua.impl.message.aspsm.ASPUpImpl;
import org.restcomm.protocols.ss7.m3ua.impl.message.aspsm.HeartbeatAckImpl;
import org.restcomm.protocols.ss7.m3ua.impl.message.aspsm.HeartbeatImpl;
import org.restcomm.protocols.ss7.m3ua.impl.message.asptm.ASPActiveAckImpl;
import org.restcomm.protocols.ss7.m3ua.impl.message.asptm.ASPActiveImpl;
import org.restcomm.protocols.ss7.m3ua.impl.message.asptm.ASPInactiveAckImpl;
import org.restcomm.protocols.ss7.m3ua.impl.message.asptm.ASPInactiveImpl;
import org.restcomm.protocols.ss7.m3ua.impl.message.mgmt.ErrorImpl;
import org.restcomm.protocols.ss7.m3ua.impl.message.mgmt.NotifyImpl;
import org.restcomm.protocols.ss7.m3ua.impl.message.rkm.DeregistrationRequestImpl;
import org.restcomm.protocols.ss7.m3ua.impl.message.rkm.DeregistrationResponseImpl;
import org.restcomm.protocols.ss7.m3ua.impl.message.rkm.RegistrationRequestImpl;
import org.restcomm.protocols.ss7.m3ua.impl.message.rkm.RegistrationResponseImpl;
import org.restcomm.protocols.ss7.m3ua.impl.message.ssnm.DestinationAvailableImpl;
import org.restcomm.protocols.ss7.m3ua.impl.message.ssnm.DestinationRestrictedImpl;
import org.restcomm.protocols.ss7.m3ua.impl.message.ssnm.DestinationStateAuditImpl;
import org.restcomm.protocols.ss7.m3ua.impl.message.ssnm.DestinationUPUnavailableImpl;
import org.restcomm.protocols.ss7.m3ua.impl.message.ssnm.DestinationUnavailableImpl;
import org.restcomm.protocols.ss7.m3ua.impl.message.ssnm.SignallingCongestionImpl;
import org.restcomm.protocols.ss7.m3ua.impl.message.transfer.PayloadDataImpl;
import org.restcomm.protocols.ss7.m3ua.message.MessageClass;
import org.restcomm.protocols.ss7.m3ua.message.MessageFactory;
import org.restcomm.protocols.ss7.m3ua.message.MessageType;

/**
 * @author amit bhayani
 * @author kulikov
 * @author sergey vetyutnev
 */
public class MessageFactoryImpl implements MessageFactory {

public M3UAMessageImpl createMessage(int messageClass, int messageType) {

        switch (messageClass) {
            case MessageClass.TRANSFER_MESSAGES:
                switch (messageType) {
                    case MessageType.PAYLOAD:
                        return new PayloadDataImpl();
                }
                break;
            case MessageClass.SIGNALING_NETWORK_MANAGEMENT:
                switch (messageType) {
                    case MessageType.DESTINATION_UNAVAILABLE:
                        return new DestinationUnavailableImpl();
                    case MessageType.DESTINATION_AVAILABLE:
                        return new DestinationAvailableImpl();
                    case MessageType.DESTINATION_STATE_AUDIT:
                        return new DestinationStateAuditImpl();
                    case MessageType.SIGNALING_CONGESTION:
                        return new SignallingCongestionImpl();
                    case MessageType.DESTINATION_USER_PART_UNAVAILABLE:
                        return new DestinationUPUnavailableImpl();
                    case MessageType.DESTINATION_RESTRICTED:
                        return new DestinationRestrictedImpl();
                }
                break;
            case MessageClass.ASP_STATE_MAINTENANCE:
                switch (messageType) {
                    case MessageType.ASP_UP:
                        return new ASPUpImpl();
                    case MessageType.ASP_UP_ACK:
                        return new ASPUpAckImpl();
                    case MessageType.ASP_DOWN:
                        return new ASPDownImpl();
                    case MessageType.ASP_DOWN_ACK:
                        return new ASPDownAckImpl();
                    case MessageType.HEARTBEAT:
                        return new HeartbeatImpl();
                    case MessageType.HEARTBEAT_ACK:
                        return new HeartbeatAckImpl();
                }
                break;
            case MessageClass.ASP_TRAFFIC_MAINTENANCE:
                switch (messageType) {
                    case MessageType.ASP_ACTIVE:
                        return new ASPActiveImpl();
                    case MessageType.ASP_ACTIVE_ACK:
                        return new ASPActiveAckImpl();
                    case MessageType.ASP_INACTIVE:
                        return new ASPInactiveImpl();
                    case MessageType.ASP_INACTIVE_ACK:
                        return new ASPInactiveAckImpl();

                }
                break;
            case MessageClass.ROUTING_KEY_MANAGEMENT:
                switch (messageType) {
                    case MessageType.REG_REQUEST:
                        return new RegistrationRequestImpl();
                    case MessageType.REG_RESPONSE:
                        return new RegistrationResponseImpl();
                    case MessageType.DEREG_REQUEST:
                        return new DeregistrationRequestImpl();
                    case MessageType.DEREG_RESPONSE:
                        return new DeregistrationResponseImpl();
                }

            case MessageClass.MANAGEMENT:
                switch (messageType) {
                    case MessageType.ERROR:
                        return new ErrorImpl();
                    case MessageType.NOTIFY:
                        return new NotifyImpl();
                }
        }
        return null;
    }

    public M3UAMessageImpl createMessage(ByteBuf byteBuf) {
        int dataLen;
        if (byteBuf.readableBytes() < 8) {
            return null;
        }

        // obtain message class and type from header
        byteBuf.markReaderIndex();
        byteBuf.skipBytes(2);
        int messageClass = byteBuf.readUnsignedByte();
        int messageType = byteBuf.readUnsignedByte();

        // obtain remaining length of the message and prepare buffer
        dataLen = byteBuf.readInt() - 8;
        if (byteBuf.readableBytes() < dataLen) {
            byteBuf.resetReaderIndex();
            return null;
        }

        // construct new message instance
        M3UAMessageImpl messageTemp = this.createMessage(messageClass, messageType);

        // parsing params of this message
        byteBuf.markWriterIndex();
        byteBuf.writerIndex(byteBuf.readerIndex() + dataLen);
        messageTemp.decode(byteBuf);
        byteBuf.resetWriterIndex();

        return messageTemp;
    }
}
