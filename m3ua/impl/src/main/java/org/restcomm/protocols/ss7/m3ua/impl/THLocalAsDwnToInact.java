
package org.restcomm.protocols.ss7.m3ua.impl;

import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.m3ua.Functionality;
import org.restcomm.protocols.ss7.m3ua.impl.fsm.FSM;
import org.restcomm.protocols.ss7.m3ua.impl.fsm.FSMState;
import org.restcomm.protocols.ss7.m3ua.impl.fsm.TransitionHandler;
import org.restcomm.protocols.ss7.m3ua.message.MessageClass;
import org.restcomm.protocols.ss7.m3ua.message.MessageType;
import org.restcomm.protocols.ss7.m3ua.message.mgmt.Notify;
import org.restcomm.protocols.ss7.m3ua.parameter.Status;

/**
 *
 * @author amit bhayani
 *
 */
public class THLocalAsDwnToInact implements TransitionHandler {

    private static final Logger logger = Logger.getLogger(THLocalAsDwnToInact.class);

    private AsImpl asImpl;
    private FSM fsm;

    public THLocalAsDwnToInact(AsImpl asImpl, FSM fsm) {
        this.asImpl = asImpl;
        this.fsm = fsm;
    }

    public boolean process(FSMState state) {
        try {

            if (asImpl.getFunctionality() != Functionality.IPSP) {
                // Send Notify only for ASP or SGW
                AspImpl remAsp = (AspImpl) this.fsm.getAttribute(AsImpl.ATTRIBUTE_ASP);

                if (remAsp == null) {
                    logger.error(String.format("No ASP found. %s", this.fsm.toString()));
                    return false;
                }

                Notify msg = createNotify(remAsp);
                remAsp.getAspFactory().write(msg);
            }
            return true;
        } catch (Exception e) {
            logger.error(String.format("Error while translating Rem AS to INACTIVE message. %s", this.fsm.toString()), e);
        }
        return false;
    }

    private Notify createNotify(AspImpl remAsp) {
        Notify msg = (Notify) this.asImpl.getMessageFactory().createMessage(MessageClass.MANAGEMENT, MessageType.NOTIFY);

        Status status = this.asImpl.getParameterFactory().createStatus(Status.STATUS_AS_State_Change, Status.INFO_AS_INACTIVE);
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
