
package org.restcomm.protocols.ss7.m3ua.impl;

import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.m3ua.impl.fsm.FSM;
import org.restcomm.protocols.ss7.m3ua.message.transfer.PayloadData;
import org.restcomm.protocols.ss7.m3ua.parameter.ErrorCode;
import org.restcomm.protocols.ss7.m3ua.parameter.ProtocolData;
import org.restcomm.protocols.ss7.m3ua.parameter.RoutingContext;
import org.restcomm.protocols.ss7.mtp.Mtp3TransferPrimitive;
import org.restcomm.protocols.ss7.mtp.Mtp3TransferPrimitiveFactory;

/**
 *
 * @author amit bhayani
 *
 */
public class TransferMessageHandler extends MessageHandler {

    private static final Logger logger = Logger.getLogger(TransferMessageHandler.class);

    private Mtp3TransferPrimitiveFactory mtp3TransferPrimitiveFactory = null;

    public TransferMessageHandler(AspFactoryImpl aspFactoryImpl) {
        super(aspFactoryImpl);

    }

    protected void setM3UAManagement(M3UAManagementImpl m3uaManagement) {
        this.mtp3TransferPrimitiveFactory = m3uaManagement.getMtp3TransferPrimitiveFactory();
    }

    public void handlePayload(PayloadData payload) {
        RoutingContext rc = payload.getRoutingContext();

        if (rc == null) {
            AspImpl aspImpl = this.getAspForNullRc();

            if (aspImpl == null) {
                // Error condition
                logger.error(String
                        .format("Rx : PayloadData=%s with null RC for Aspfactory=%s. But no ASP configured for null RC. Sending back Error",
                                payload, this.aspFactoryImpl.getName()));
                return;
            }

            FSM fsm = getAspFSMForRxPayload(aspImpl);
            AspState aspState = AspState.getState(fsm.getState().getName());

            if (aspState == AspState.ACTIVE) {
                ProtocolData protocolData = payload.getData();
                Mtp3TransferPrimitive mtp3TransferPrimitive = this.mtp3TransferPrimitiveFactory.createMtp3TransferPrimitive(
                        protocolData.getSI(), protocolData.getNI(), protocolData.getMP(), protocolData.getOpc(),
                        protocolData.getDpc(), protocolData.getSLS(), protocolData.getData());
                ((AsImpl) aspImpl.getAs()).getM3UAManagement().sendTransferMessageToLocalUser(mtp3TransferPrimitive,
                        payload.getData().getSLS());
            } else {
                logger.error(String.format(
                        "Rx : PayloadData for Aspfactory=%s with null RoutingContext. But ASP State=%s. Message=%s",
                        this.aspFactoryImpl.getName(), aspState, payload));
            }

        } else {
            // Payload is always for single AS
            long rcl = payload.getRoutingContext().getRoutingContexts()[0];
            AspImpl aspImpl = this.aspFactoryImpl.getAsp(rcl);

            if (aspImpl == null) {
                // this is error. Send back error
                RoutingContext rcObj = this.aspFactoryImpl.parameterFactory.createRoutingContext(new long[] { rcl });
                ErrorCode errorCodeObj = this.aspFactoryImpl.parameterFactory
                        .createErrorCode(ErrorCode.Invalid_Routing_Context);
                sendError(rcObj, errorCodeObj);
                logger.error(String.format(
                        "Rx : Payload=%s with RC=%d for Aspfactory=%s. But no ASP configured for this RC. Sending back Error",
                        payload, rcl, this.aspFactoryImpl.getName()));
                return;
            }

            FSM fsm = getAspFSMForRxPayload(aspImpl);
            AspState aspState = AspState.getState(fsm.getState().getName());

            if (aspState == AspState.ACTIVE) {
                ProtocolData protocolData = payload.getData();
                Mtp3TransferPrimitive mtp3TransferPrimitive = this.mtp3TransferPrimitiveFactory.createMtp3TransferPrimitive(
                        protocolData.getSI(), protocolData.getNI(), protocolData.getMP(), protocolData.getOpc(),
                        protocolData.getDpc(), protocolData.getSLS(), protocolData.getData());
                ((AsImpl) aspImpl.getAs()).getM3UAManagement().sendTransferMessageToLocalUser(mtp3TransferPrimitive,
                        payload.getData().getSLS());
            } else {
                logger.error(String.format(
                        "Rx : PayloadData for Aspfactory=%s for RoutingContext=%s. But ASP State=%s. Message=%s",
                        this.aspFactoryImpl.getName(), rc, aspState, payload));
            }
        }
    }
}
