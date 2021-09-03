
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
 * Called when one of the ASPs of this AS becomes ACTIVE while AS is already ACTIVE
 *
 * @author amit bhayani
 *
 */
public class THLocalAsActToActRemAspAct implements TransitionHandler {

    private static final Logger logger = Logger.getLogger(THLocalAsActToActRemAspAct.class);

    private AsImpl asImpl;
    private FSM fsm;

    public THLocalAsActToActRemAspAct(AsImpl asImpl, FSM fsm) {
        this.asImpl = asImpl;
        this.fsm = fsm;
    }

    public boolean process(FSMState state) {
        try {

            if (this.asImpl.getTrafficModeType().getMode() == TrafficModeType.Broadcast) {
                // We don't handle this
                return false;
            }

            AspImpl remAsp = (AspImpl) this.fsm.getAttribute(AsImpl.ATTRIBUTE_ASP);

            if (this.asImpl.getTrafficModeType().getMode() == TrafficModeType.Loadshare
                    && asImpl.getFunctionality() != Functionality.IPSP) {
                // Send Notify only for ASP or SGW

                // Iterate through ASP's and send AS_ACTIVE to ASP's who
                // are INACTIVE
                for (FastList.Node<Asp> n = this.asImpl.appServerProcs.head(), end = this.asImpl.appServerProcs.tail(); (n = n
                        .getNext()) != end;) {
                    AspImpl remAspImpl = (AspImpl) n.getValue();

                    FSM aspPeerFSM = remAspImpl.getPeerFSM();
                    AspState aspState = AspState.getState(aspPeerFSM.getState().getName());

                    if (aspState == AspState.INACTIVE) {
                        Notify msg = createNotify(remAspImpl, Status.STATUS_AS_State_Change, Status.INFO_AS_ACTIVE);
                        remAspImpl.getAspFactory().write(msg);
                    }
                }
            } else if (this.asImpl.getTrafficModeType().getMode() == TrafficModeType.Override) {
                // Look at 5.2.2. 1+1 Sparing, Backup Override

                for (FastList.Node<Asp> n = this.asImpl.appServerProcs.head(), end = this.asImpl.appServerProcs.tail(); (n = n
                        .getNext()) != end;) {
                    AspImpl remAspImpl = (AspImpl) n.getValue();

                    FSM aspPeerFSM = remAspImpl.getPeerFSM();
                    AspState aspState = AspState.getState(aspPeerFSM.getState().getName());

                    // Transition the other ASP to INACTIVE
                    if (aspState == AspState.ACTIVE && !(remAspImpl.getName().equals(remAsp.getName()))) {
                        if (asImpl.getFunctionality() != Functionality.IPSP) {
                            // Send Notify only for ASP or SGW

                            Notify msg = createNotify(remAspImpl, Status.STATUS_Other, Status.INFO_Alternate_ASP_Active);
                            remAspImpl.getAspFactory().write(msg);
                        }

                        // Transition this ASP to INACTIVE
                        aspPeerFSM.signal(TransitionState.OTHER_ALTERNATE_ASP_ACTIVE);

                        break;
                    }
                }
            }

            return true;
        } catch (Exception e) {
            logger.error(String.format("Error while translating Rem AS to INACTIVE. %s", this.fsm.toString()), e);
        }
        // something wrong
        return false;
    }

    private Notify createNotify(AspImpl remAsp, int type, int info) {
        Notify msg = (Notify) this.asImpl.getMessageFactory().createMessage(MessageClass.MANAGEMENT, MessageType.NOTIFY);

        Status status = this.asImpl.getParameterFactory().createStatus(type, info);
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
