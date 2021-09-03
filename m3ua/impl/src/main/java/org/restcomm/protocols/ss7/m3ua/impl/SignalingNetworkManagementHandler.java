
package org.restcomm.protocols.ss7.m3ua.impl;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.m3ua.Functionality;
import org.restcomm.protocols.ss7.m3ua.impl.fsm.FSM;
import org.restcomm.protocols.ss7.m3ua.message.ssnm.DestinationAvailable;
import org.restcomm.protocols.ss7.m3ua.message.ssnm.DestinationRestricted;
import org.restcomm.protocols.ss7.m3ua.message.ssnm.DestinationStateAudit;
import org.restcomm.protocols.ss7.m3ua.message.ssnm.DestinationUPUnavailable;
import org.restcomm.protocols.ss7.m3ua.message.ssnm.DestinationUnavailable;
import org.restcomm.protocols.ss7.m3ua.message.ssnm.SignallingCongestion;
import org.restcomm.protocols.ss7.m3ua.parameter.AffectedPointCode;
import org.restcomm.protocols.ss7.m3ua.parameter.CongestedIndication;
import org.restcomm.protocols.ss7.m3ua.parameter.ErrorCode;
import org.restcomm.protocols.ss7.m3ua.parameter.RoutingContext;
import org.restcomm.protocols.ss7.m3ua.parameter.UserCause;
import org.restcomm.protocols.ss7.m3ua.parameter.CongestedIndication.CongestionLevel;
import org.restcomm.protocols.ss7.mtp.Mtp3PausePrimitive;
import org.restcomm.protocols.ss7.mtp.Mtp3ResumePrimitive;
import org.restcomm.protocols.ss7.mtp.Mtp3StatusCause;
import org.restcomm.protocols.ss7.mtp.Mtp3StatusPrimitive;

/**
 *
 * @author amit bhayani
 *
 */
public class SignalingNetworkManagementHandler extends MessageHandler {

    private static final Logger logger = Logger.getLogger(SignalingNetworkManagementHandler.class);

    public SignalingNetworkManagementHandler(AspFactoryImpl aspFactoryImpl) {
        super(aspFactoryImpl);
    }

    public void handleDestinationUnavailable(DestinationUnavailable duna) {

        RoutingContext rcObj = duna.getRoutingContexts();

        if (aspFactoryImpl.getFunctionality() == Functionality.AS) {

            if (rcObj == null) {
                AspImpl aspImpl = this.getAspForNullRc();
                if (aspImpl == null) {
                    ErrorCode errorCodeObj = this.aspFactoryImpl.parameterFactory
                            .createErrorCode(ErrorCode.Invalid_Routing_Context);
                    sendError(rcObj, errorCodeObj);
                    logger.error(String
                            .format("Rx : DUNA=%s with null RC for Aspfactory=%s. But no ASP configured for null RC. Sending back Error",
                                    duna, this.aspFactoryImpl.getName()));
                    return;
                }

                FSM fsm = aspImpl.getLocalFSM();

                if (fsm == null) {
                    logger.error(String.format("Rx : DUNA=%s for ASP=%s. But Local FSM is null.", duna,
                            this.aspFactoryImpl.getName()));
                    return;
                }

                AspState aspState = AspState.getState(fsm.getState().getName());

                if (aspState == AspState.ACTIVE) {
                    AffectedPointCode affectedPcObjs = duna.getAffectedPointCodes();
                    int[] affectedPcs = affectedPcObjs.getPointCodes();

                    for (int i = 0; i < affectedPcs.length; i++) {
                        Mtp3PausePrimitive mtpPausePrimitive = new Mtp3PausePrimitive(affectedPcs[i]);
                        ((AsImpl) aspImpl.getAs()).getM3UAManagement().sendPauseMessageToLocalUser(mtpPausePrimitive);
                    }
                } else {
                    logger.error(String.format("Rx : DUNA for null RoutingContext. But ASP State=%s. Message=%s", aspState, duna));
                }

            } else {
                long[] rcs = rcObj.getRoutingContexts();
                for (int count = 0; count < rcs.length; count++) {
                    AspImpl aspImpl = this.aspFactoryImpl.getAsp(rcs[count]);
                    if (aspImpl == null) {
                        // this is error. Send back error
                        RoutingContext rcObjTemp = this.aspFactoryImpl.parameterFactory
                                .createRoutingContext(new long[] { rcs[count] });
                        ErrorCode errorCodeObj = this.aspFactoryImpl.parameterFactory
                                .createErrorCode(ErrorCode.Invalid_Routing_Context);
                        sendError(rcObjTemp, errorCodeObj);
                        logger.error(String
                                .format("Rx : DUNA=%s with RC=%d for Aspfactory=%s. But no ASP configured for this RC. Sending back Error",
                                        duna, rcs[count], this.aspFactoryImpl.getName()));
                        continue;
                    }// if (asp == null)

                    FSM fsm = aspImpl.getLocalFSM();

                    if (fsm == null) {
                        logger.error(String.format("Rx : DUNA=%s for ASP=%s. But Local FSM is null.", duna,
                                this.aspFactoryImpl.getName()));
                        return;
                    }

                    AspState aspState = AspState.getState(fsm.getState().getName());

                    if (aspState == AspState.ACTIVE) {
                        AffectedPointCode affectedPcObjs = duna.getAffectedPointCodes();
                        int[] affectedPcs = affectedPcObjs.getPointCodes();

                        for (int i = 0; i < affectedPcs.length; i++) {
                            Mtp3PausePrimitive mtpPausePrimi = new Mtp3PausePrimitive(affectedPcs[i]);
                            ((AsImpl) aspImpl.getAs()).getM3UAManagement().sendPauseMessageToLocalUser(mtpPausePrimi);
                        }
                    } else {
                        logger.error(String.format("Rx : DUNA for RoutingContext=%d. But ASP State=%s. Message=%s", rcs[count],
                                aspState, duna));
                    }
                }// for loop
            }

        } else {
            // TODO : Should we silently drop DUNA?

            logger.error(String.format(
                    "Rx : DUNA =%s But AppServer Functionality is not As. Sending back ErrorCode.Unexpected_Message", duna));

            ErrorCode errorCodeObj = this.aspFactoryImpl.parameterFactory.createErrorCode(ErrorCode.Unexpected_Message);
            sendError(rcObj, errorCodeObj);
        }
    }

    public void handleDestinationAvailable(DestinationAvailable destinationAvailable) {
        RoutingContext rcObj = destinationAvailable.getRoutingContexts();

        if (aspFactoryImpl.getFunctionality() == Functionality.AS) {

            if (rcObj == null) {
                AspImpl aspImpl = this.getAspForNullRc();
                if (aspImpl == null) {
                    ErrorCode errorCodeObj = this.aspFactoryImpl.parameterFactory
                            .createErrorCode(ErrorCode.Invalid_Routing_Context);
                    sendError(rcObj, errorCodeObj);
                    logger.error(String
                            .format("Rx : DAVA=%s with null RC for Aspfactory=%s. But no ASP configured for null RC. Sending back Error",
                                destinationAvailable, this.aspFactoryImpl.getName()));
                    return;
                }

                FSM fsm = aspImpl.getLocalFSM();

                if (fsm == null) {
                    logger.error(String.format("Rx : DAVA=%s for ASP=%s. But Local FSM is null.", destinationAvailable,
                            this.aspFactoryImpl.getName()));
                    return;
                }

                AspState aspState = AspState.getState(fsm.getState().getName());

                if (aspState == AspState.ACTIVE) {
                    AffectedPointCode affectedPcObjs = destinationAvailable.getAffectedPointCodes();
                    int[] affectedPcs = affectedPcObjs.getPointCodes();

                    for (int i = 0; i < affectedPcs.length; i++) {
                        Mtp3ResumePrimitive mtpResumePrimi = new Mtp3ResumePrimitive(affectedPcs[i]);
                        ((AsImpl) aspImpl.getAs()).getM3UAManagement().sendResumeMessageToLocalUser(mtpResumePrimi);
                    }
                } else {
                    logger.error(String.format("Rx : DAVA for null RoutingContext. But ASP State=%s. Message=%s", aspState, destinationAvailable));
                }

            } else {
                long[] rcs = rcObj.getRoutingContexts();
                for (int count = 0; count < rcs.length; count++) {
                    AspImpl aspImpl = this.aspFactoryImpl.getAsp(rcs[count]);
                    if (aspImpl == null) {
                        // this is error. Send back error
                        RoutingContext rcObjTemp = this.aspFactoryImpl.parameterFactory
                                .createRoutingContext(new long[] { rcs[count] });
                        ErrorCode errorCodeObj = this.aspFactoryImpl.parameterFactory
                                .createErrorCode(ErrorCode.Invalid_Routing_Context);
                        sendError(rcObjTemp, errorCodeObj);
                        logger.error(String
                                .format("Rx : DAVA=%s with RC=%d for Aspfactory=%s. But no ASP configured for this RC. Sending back Error",
                                        destinationAvailable, rcs[count], this.aspFactoryImpl.getName()));
                        continue;
                    }// if (asp == null)

                    FSM fsm = aspImpl.getLocalFSM();

                    if (fsm == null) {
                        logger.error(String.format("Rx : DAVA=%s for ASP=%s. But Local FSM is null", destinationAvailable,
                                this.aspFactoryImpl.getName()));
                        return;
                    }

                    AspState aspState = AspState.getState(fsm.getState().getName());

                    if (aspState == AspState.ACTIVE) {
                        AffectedPointCode affectedPcObjs = destinationAvailable.getAffectedPointCodes();
                        int[] affectedPcs = affectedPcObjs.getPointCodes();

                        for (int i = 0; i < affectedPcs.length; i++) {
                            Mtp3ResumePrimitive mtpResumePrimi = new Mtp3ResumePrimitive(affectedPcs[i]);
                            ((AsImpl) aspImpl.getAs()).getM3UAManagement().sendResumeMessageToLocalUser(mtpResumePrimi);
                        }
                    } else {
                        logger.error(String.format("Rx : DAVA for RoutingContext=%d. But ASP State=%s. Message=%s", rcs[count],
                                aspState, destinationAvailable));
                    }
                }// for loop
            }

        } else {
            // TODO : Should we silently drop DUNA?

            logger.error(String.format(
                    "Rx : DAVA =%s But AppServer Functionality is not As. Sending back ErrorCode.Unexpected_Message", destinationAvailable));

            ErrorCode errorCodeObj = this.aspFactoryImpl.parameterFactory.createErrorCode(ErrorCode.Unexpected_Message);
            sendError(rcObj, errorCodeObj);
        }
    }

    public void handleDestinationStateAudit(DestinationStateAudit destinationStateAudit) {
        RoutingContext rcObj = destinationStateAudit.getRoutingContexts();
        if (aspFactoryImpl.getFunctionality() == Functionality.SGW) {
            logger.warn(String.format("Received DAUD=%s. Handling of DAUD message is not yet implemented", destinationStateAudit));
        } else {
            // TODO : Should we silently drop DUNA?

            logger.error(String.format(
                    "Rx : DAUD =%s But AppServer Functionality is not SGW. Sending back ErrorCode.Unexpected_Message", destinationStateAudit));

            // ASPACTIVE_ACK is unexpected in this state
            ErrorCode errorCodeObj = this.aspFactoryImpl.parameterFactory.createErrorCode(ErrorCode.Unexpected_Message);
            sendError(rcObj, errorCodeObj);
        }
    }

    public void handleSignallingCongestion(SignallingCongestion signallingCongestion) {
        RoutingContext rcObj = signallingCongestion.getRoutingContexts();
        if (aspFactoryImpl.getFunctionality() == Functionality.AS || aspFactoryImpl.getFunctionality() == Functionality.IPSP) {
            if (rcObj == null) {
                AspImpl aspImpl = this.getAspForNullRc();
                if (aspImpl == null) {
                    ErrorCode errorCodeObj = this.aspFactoryImpl.parameterFactory
                            .createErrorCode(ErrorCode.Invalid_Routing_Context);
                    sendError(rcObj, errorCodeObj);
                    logger.error(String
                            .format("Rx : SCON=%s with null RC for Aspfactory=%s. But no ASP configured for null RC. Sending back Error",
                                signallingCongestion, this.aspFactoryImpl.getName()));
                    return;
                }

                FSM fsm = aspImpl.getLocalFSM();

                if (fsm == null) {
                    logger.error(String.format("Rx : SCON=%s for ASP=%s. But Local FSM is null.", signallingCongestion,
                            this.aspFactoryImpl.getName()));
                    return;
                }

                AspState aspState = AspState.getState(fsm.getState().getName());

                if (aspState == AspState.ACTIVE) {
                    AffectedPointCode affectedPcObjs = signallingCongestion.getAffectedPointCodes();
                    int[] affectedPcs = affectedPcObjs.getPointCodes();

                    int cong = 0;
                    for (int i = 0; i < affectedPcs.length; i++) {
                        CongestedIndication congeInd = signallingCongestion.getCongestedIndication();
                        if (congeInd != null) {
                            CongestionLevel congLevel = congeInd.getCongestionLevel();
                            if (congLevel != null) {
                                cong = congLevel.getLevel();
                            }
                        }

                        Mtp3StatusPrimitive mtpPausePrimi = new Mtp3StatusPrimitive(affectedPcs[i],
                                Mtp3StatusCause.SignallingNetworkCongested, cong, 0);
                        ((AsImpl) aspImpl.getAs()).getM3UAManagement().sendStatusMessageToLocalUser(mtpPausePrimi);
                    }
                } else {
                    logger.error(String.format("Rx : SCON for null RoutingContext. But ASP State=%s. Message=%s", aspState, signallingCongestion));
                }
            } else {
                long[] rcs = rcObj.getRoutingContexts();
                for (int count = 0; count < rcs.length; count++) {
                    AspImpl aspImpl = this.aspFactoryImpl.getAsp(rcs[count]);
                    if (aspImpl == null) {
                        // this is error. Send back error
                        RoutingContext rcObjTemp = this.aspFactoryImpl.parameterFactory
                                .createRoutingContext(new long[] { rcs[count] });
                        ErrorCode errorCodeObj = this.aspFactoryImpl.parameterFactory
                                .createErrorCode(ErrorCode.Invalid_Routing_Context);
                        sendError(rcObjTemp, errorCodeObj);
                        logger.error(String
                                .format("Rx : SCON=%s with RC=%d for Aspfactory=%s. But no ASP configured for this RC. Sending back Error",
                                        signallingCongestion, rcs[count], this.aspFactoryImpl.getName()));
                        continue;
                    }// if (asp == null)

                    FSM fsm = aspImpl.getLocalFSM();

                    if (fsm == null) {
                        logger.error(String.format("Rx : SCON=%s for ASP=%s. But Local FSM is null", signallingCongestion, this.aspFactoryImpl.getName()));
                        return;
                    }

                    AspState aspState = AspState.getState(fsm.getState().getName());

                    if (aspState == AspState.ACTIVE) {
                        AffectedPointCode affectedPcObjs = signallingCongestion.getAffectedPointCodes();
                        int[] affectedPcs = affectedPcObjs.getPointCodes();

                        int cong = 0;
                        for (int i = 0; i < affectedPcs.length; i++) {
                            CongestedIndication congeInd = signallingCongestion.getCongestedIndication();
                            if (congeInd != null) {
                                CongestionLevel congLevel = congeInd.getCongestionLevel();
                                if (congLevel != null) {
                                    cong = congLevel.getLevel();
                                }
                            }

                            Mtp3StatusPrimitive mtpPausePrimi = new Mtp3StatusPrimitive(affectedPcs[i],
                                    Mtp3StatusCause.SignallingNetworkCongested, cong, 0);
                            ((AsImpl) aspImpl.getAs()).getM3UAManagement().sendStatusMessageToLocalUser(mtpPausePrimi);
                        }
                    } else {
                        logger.error(String.format("Rx : DAVA for RoutingContext=%d. But ASP State=%s. Message=%s", rcs[count],
                                aspState, signallingCongestion));
                    }
                }// for loop
            }

        } else {
            logger.error(String.format(
                    "Rx : SCON =%s But AppServer Functionality is not AS or IPSP. Sending back ErrorCode.Unexpected_Message",
                signallingCongestion));

            // SCON is unexpected in this state
            ErrorCode errorCodeObj = this.aspFactoryImpl.parameterFactory.createErrorCode(ErrorCode.Unexpected_Message);
            sendError(rcObj, errorCodeObj);
        }
    }

    public void handleDestinationUPUnavailable(DestinationUPUnavailable destinationUPUnavailable) {
        RoutingContext rcObj = destinationUPUnavailable.getRoutingContext();

        if (aspFactoryImpl.getFunctionality() == Functionality.AS) {

            if (rcObj == null) {
                AspImpl aspImpl = this.getAspForNullRc();
                if (aspImpl == null) {
                    ErrorCode errorCodeObj = this.aspFactoryImpl.parameterFactory
                            .createErrorCode(ErrorCode.Invalid_Routing_Context);
                    sendError(rcObj, errorCodeObj);
                    logger.error(String
                            .format("Rx : DUPU=%s with null RC for Aspfactory=%s. But no ASP configured for null RC. Sending back Error",
                                destinationUPUnavailable, this.aspFactoryImpl.getName()));
                    return;
                }

                FSM fsm = aspImpl.getLocalFSM();

                if (fsm == null) {
                    logger.error(String.format("Rx : DUPU=%s for ASP=%s. But Local FSM is null.", destinationUPUnavailable,
                            this.aspFactoryImpl.getName()));
                    return;
                }

                AspState aspState = AspState.getState(fsm.getState().getName());

                if (aspState == AspState.ACTIVE) {
                    AffectedPointCode affectedPcObjs = destinationUPUnavailable.getAffectedPointCode();
                    int[] affectedPcs = affectedPcObjs.getPointCodes();

                    int cause = 0;
                    for (int i = 0; i < affectedPcs.length; i++) {

                        UserCause userCause = destinationUPUnavailable.getUserCause();
                        cause = userCause.getCause();
                        Mtp3StatusPrimitive mtpPausePrimi = new Mtp3StatusPrimitive(affectedPcs[i],
                                Mtp3StatusCause.getMtp3StatusCause(cause), 0, 0);
                        ((AsImpl) aspImpl.getAs()).getM3UAManagement().sendStatusMessageToLocalUser(mtpPausePrimi);
                    }
                } else {
                    logger.error(String.format("Rx : DUPU for null RoutingContext. But ASP State=%s. Message=%s", aspState, destinationUPUnavailable));
                }

            } else {
                long[] rcs = rcObj.getRoutingContexts();
                for (int count = 0; count < rcs.length; count++) {
                    AspImpl aspImpl = this.aspFactoryImpl.getAsp(rcs[count]);
                    if (aspImpl == null) {
                        // this is error. Send back error
                        RoutingContext rcObjTemp = this.aspFactoryImpl.parameterFactory
                                .createRoutingContext(new long[] { rcs[count] });
                        ErrorCode errorCodeObj = this.aspFactoryImpl.parameterFactory
                                .createErrorCode(ErrorCode.Invalid_Routing_Context);
                        sendError(rcObjTemp, errorCodeObj);
                        logger.error(String
                                .format("Rx : DUPU=%s with RC=%d for Aspfactory=%s. But no ASP configured for this RC. Sending back Error",
                                        destinationUPUnavailable, rcs[count], this.aspFactoryImpl.getName()));
                        continue;
                    }// if (asp == null)

                    FSM fsm = aspImpl.getLocalFSM();

                    if (fsm == null) {
                        logger.error(String.format("Rx : DUPU=%s for ASP=%s. But Local FSM is null", destinationUPUnavailable,
                                this.aspFactoryImpl.getName()));
                        return;
                    }

                    AspState aspState = AspState.getState(fsm.getState().getName());

                    if (aspState == AspState.ACTIVE) {
                        AffectedPointCode affectedPcObjs = destinationUPUnavailable.getAffectedPointCode();
                        int[] affectedPcs = affectedPcObjs.getPointCodes();
                        int cause = 0;
                        for (int i = 0; i < affectedPcs.length; i++) {

                            UserCause userCause = destinationUPUnavailable.getUserCause();
                            cause = userCause.getCause();
                            Mtp3StatusPrimitive mtpPausePrimi = new Mtp3StatusPrimitive(affectedPcs[i],
                                    Mtp3StatusCause.getMtp3StatusCause(cause), 0, 0);
                            ((AsImpl) aspImpl.getAs()).getM3UAManagement().sendStatusMessageToLocalUser(mtpPausePrimi);
                        }
                    } else {
                        logger.error(String.format("Rx : DUPU for RoutingContext=%d. But ASP State=%s. Message=%s", rcs[count],
                                aspState, destinationUPUnavailable));
                    }
                }// for loop
            }

        } else {
            // TODO : Should we silently drop DUNA?

            logger.error(String.format(
                    "Rx : DUPU =%s But AppServer Functionality is not AS. Sending back ErrorCode.Unexpected_Message", destinationUPUnavailable));

            ErrorCode errorCodeObj = this.aspFactoryImpl.parameterFactory.createErrorCode(ErrorCode.Unexpected_Message);
            sendError(rcObj, errorCodeObj);
        }
    }

    public void handleDestinationRestricted(DestinationRestricted destinationRestricted) {

        if (aspFactoryImpl.getFunctionality() == Functionality.AS) {
            if (logger.isEnabledFor(Level.WARN)) {
                logger.warn(String.format("Received DRST message for AS side. Not implemented yet", destinationRestricted));
            }
        } else {
            // TODP log error
        }
    }

}
