
package org.restcomm.protocols.ss7.m3ua.impl;

import javolution.util.FastList;

import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.m3ua.Asp;
import org.restcomm.protocols.ss7.m3ua.Functionality;
import org.restcomm.protocols.ss7.m3ua.impl.fsm.FSM;
import org.restcomm.protocols.ss7.m3ua.impl.fsm.FSMState;
import org.restcomm.protocols.ss7.m3ua.impl.fsm.TransitionHandler;
import org.restcomm.protocols.ss7.m3ua.message.MessageClass;
import org.restcomm.protocols.ss7.m3ua.message.MessageType;
import org.restcomm.protocols.ss7.m3ua.message.mgmt.Notify;
import org.restcomm.protocols.ss7.m3ua.parameter.Status;
import org.restcomm.protocols.ss7.m3ua.parameter.TrafficModeType;

/**
 * <p>
 * Transition's the RemAsImpl from {@link org.restcomm.protocols.ss7.m3ua.impl.AsState#PENDING} to
 * {@link org.restcomm.protocols.ss7.m3ua.impl.AsState#ACTIVE}. Send's AS_ACTIVE notification to all {@link AspImpl} within
 * this As that are {@link AspState#ACTIVE}
 * </p>
 *
 * <p>
 * Also initiates sending of PayloadData from the pending queue of this As.
 * </p>
 *
 * @author amit bhayani
 *
 */
public class THLocalAsPendToAct implements TransitionHandler {

    private static final Logger logger = Logger.getLogger(THLocalAsPendToAct.class);

    private AsImpl asImpl = null;
    private FSM fsm;

    public THLocalAsPendToAct(AsImpl asImpl, FSM fsm) {
        this.asImpl = asImpl;
        this.fsm = fsm;
    }

    public boolean process(FSMState state) {

        if (this.asImpl.getTrafficModeType().getMode() == TrafficModeType.Broadcast) {
            // We don't handle this
            return false;
        }

        try {

            if (asImpl.getFunctionality() != Functionality.IPSP) {
                // Send Notify only for ASP or SGW

                // Iterate through ASP's and send AS_ACTIVE to ASP's who
                // are INACTIVE or ACTIVE
                for (FastList.Node<Asp> n = this.asImpl.appServerProcs.head(), end = this.asImpl.appServerProcs.tail(); (n = n
                        .getNext()) != end;) {
                    AspImpl remAspImpl = (AspImpl) n.getValue();

                    FSM aspPeerFSM = remAspImpl.getPeerFSM();
                    AspState aspState = AspState.getState(aspPeerFSM.getState().getName());

                    if (aspState == AspState.INACTIVE || aspState == AspState.ACTIVE) {
                        Notify msg = createNotify(remAspImpl);
                        remAspImpl.getAspFactory().write(msg);
                    }
                }// end of for
            }

            // Send the PayloadData (if any) from pending queue to other side
            AspImpl causeAsp = (AspImpl) this.fsm.getAttribute(AsImpl.ATTRIBUTE_ASP);
            this.asImpl.sendPendingPayloadData(causeAsp);

            return true;
        } catch (Exception e) {
            logger.error(String.format("Error while translating Rem AS to INACTIVE message. %s", this.fsm.toString()), e);
        }
        return false;
    }

    private Notify createNotify(AspImpl remAsp) {
        Notify msg = (Notify) this.asImpl.getMessageFactory().createMessage(MessageClass.MANAGEMENT, MessageType.NOTIFY);

        Status status = this.asImpl.getParameterFactory().createStatus(Status.STATUS_AS_State_Change, Status.INFO_AS_ACTIVE);
        msg.setStatus(status);

        if (remAsp.getASPIdentifier() != null) {
            msg.setASPIdentifier(remAsp.getASPIdentifier());
        }

        if (this.asImpl.getRoutingContext() != null) {
            msg.setRoutingContext(this.asImpl.getRoutingContext());
        }

        return msg;
    }

}
