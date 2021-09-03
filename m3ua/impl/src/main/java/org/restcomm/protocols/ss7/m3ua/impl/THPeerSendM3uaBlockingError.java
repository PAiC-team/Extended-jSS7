package org.restcomm.protocols.ss7.m3ua.impl;

import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.m3ua.impl.fsm.FSM;
import org.restcomm.protocols.ss7.m3ua.impl.fsm.FSMState;
import org.restcomm.protocols.ss7.m3ua.impl.fsm.TransitionHandler;
import org.restcomm.protocols.ss7.m3ua.impl.parameter.ErrorCodeImpl;
import org.restcomm.protocols.ss7.m3ua.message.MessageClass;
import org.restcomm.protocols.ss7.m3ua.message.MessageType;
import org.restcomm.protocols.ss7.m3ua.message.mgmt.Error;
import org.restcomm.protocols.ss7.m3ua.parameter.ErrorCode;

/**
 * This class purpose is to test sending M3UA ERR
 * with error code 13 (Refused_Management_Blocking)
 * only for testing purposes
 *
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class THPeerSendM3uaBlockingError implements TransitionHandler {

    private AspImpl aspImpl;
    private FSM fsm;
    private static final Logger logger = Logger.getLogger(THPeerSendM3uaBlockingError.class);

    public THPeerSendM3uaBlockingError(AspImpl aspImpl, FSM fsm) {
        this.aspImpl = aspImpl;
        this.fsm = fsm;
    }

    public boolean process(FSMState state) {
        Error error = (Error) this.aspImpl.aspFactoryImpl.messageFactory.createMessage(MessageClass.MANAGEMENT, MessageType.ERROR);
        ErrorCode errorCode = new ErrorCodeImpl(ErrorCode.Refused_Management_Blocking);
        error.setErrorCode(errorCode);
        this.aspImpl.aspFactoryImpl.write(error);
        return true;
    }
}
