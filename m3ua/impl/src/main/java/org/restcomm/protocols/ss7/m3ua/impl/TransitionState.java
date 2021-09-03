package org.restcomm.protocols.ss7.m3ua.impl;

import java.util.HashMap;

import org.restcomm.protocols.ss7.m3ua.message.M3UAMessage;
import org.restcomm.protocols.ss7.m3ua.message.MessageClass;
import org.restcomm.protocols.ss7.m3ua.message.MessageType;
import org.restcomm.protocols.ss7.m3ua.message.mgmt.Error;
import org.restcomm.protocols.ss7.m3ua.message.mgmt.Notify;
import org.restcomm.protocols.ss7.m3ua.parameter.ErrorCode;
import org.restcomm.protocols.ss7.m3ua.parameter.Status;

/**
 *
 * @author amit bhayani
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class TransitionState {

    public static final String COMM_UP = "commup";
    public static final String COMM_DOWN = "commdown";
    public static final String PAYLOAD = "payload";

    public static final String DUNA = "duna";
    public static final String DAVA = "dava";
    public static final String DAUD = "daud";
    public static final String SCON = "scon";
    public static final String DUPU = "dupu";
    public static final String DRST = "drst";

    public static final String ASP_UP_SENT = "aspupsent";
    public static final String ASP_UP = "aspup";
    public static final String ASP_UP_ACK = "aspupack";

    public static final String ASP_DOWN_SENT = "aspdownsent";
    public static final String ASP_DOWN = "aspdown";
    public static final String ASP_DOWN_ACK = "aspdownack";

    public static final String HEARTBEAT = "heartbeat";
    public static final String HEARTBEAT_ACK = "heartbeatack";

    public static final String ASP_INACTIVE_SENT = "aspinactivesent";
    public static final String ASP_INACTIVE = "aspinactive";
    public static final String ASP_INACTIVE_ACK = "aspinactiveack";

    public static final String ASP_ACTIVE = "aspactive";
    public static final String ASP_ACTIVE_SENT = "aspactivesent";
    public static final String ASP_ACTIVE_ACK = "aspactiveack";

    public static final String AS_DOWN = "asdown";
    public static final String AS_INACTIVE = "asinactive";

    public static final String AS_STATE_CHANGE_RESERVE = "ntfyreserve";
    public static final String AS_STATE_CHANGE_INACTIVE = "ntfyasinactive";
    public static final String AS_STATE_CHANGE_ACTIVE = "ntfyasactive";
    public static final String AS_STATE_CHANGE_PENDING = "ntfyaspending";

    public static final String OTHER_INSUFFICIENT_ASP = "ntfyinsuffasp";
    public static final String OTHER_ALTERNATE_ASP_ACTIVE = "ntfyaltaspact";
    public static final String OTHER_ALTERNATE_ASP_FAILURE = "ntfyaltaspfail";

    public static final String INVALID_VERSION = "1"; // Invalid_Version = 0x01;
    public static final String UNSUPPORTED_MESSAGE_CLASS = "3"; // Unsupported_Message_Class = 0x03;
    public static final String UNSUPPORTED_MESSAGE_TYPE = "4"; // Unsupported_Message_Type = 0x04;
    public static final String UNSUPPORTED_TRAFFIC_MODE_TYPE = "5"; // Unsupported_Traffic_Mode_Type = 0x05;
    public static final String UNEXPECTED_MESSAGE = "6"; // Unexpected_Message = 0x06;
    public static final String PROTOCOL_ERROR = "7"; // Protocol_Error = 0x07;
    public static final String INVALID_STREAM_IDENTIFIER = "9"; // Invalid_Stream_Identifier = 0x09;
    public static final String REFUSED_MANAGEMENT_BLOCKING = "13"; // Refused_Management_Blocking = 0x0d (13);
    public static final String ASP_IDENTIFIER_REQUIRED = "14"; // ASP_Identifier_Required = 0x0e (14);
    public static final String INVALID_ASP_IDENTIFIER = "15"; // Invalid_ASP_Identifier = 0x0f (15);
    public static final String INVALID_PARAMETER_VALUE = "17"; // Invalid_Parameter_Value = 0x11 (17);
    public static final String PARAMETER_FIELD_ERROR = "18"; // Parameter_Field_Error = 0x12 (18);
    public static final String UNEXPECTED_PARAMETER = "19"; // Unexpected_Parameter = 0x13 (19);
    public static final String DESTINATION_STATUS_UNKNOWN = "20"; // Destination_Status_Unknown = 0x14 (20);
    public static final String INVALID_NETWORK_APPEARANCE = "21"; // Invalid_Network_Appearance = 0x15 (21);
    public static final String MISSING_PARAMETER = "22"; // Missing_Parameter = 0x16 (22);
    public static final String INVALID_ROUTING_CONTEXT = "25"; // Invalid_Routing_Context = 0x19 (25);
    public static final String NO_CONFIGURED_AS_FOR_ASP = "26"; // No_Configured_AS_for_ASP = 0x1a (26);

    private static HashMap<Integer, HashMap<Integer, String>> transContainer = new HashMap<Integer, HashMap<Integer, String>>();

    static {

        // Transfer
        HashMap<Integer, String> trfrTransCont = new HashMap<Integer, String>();
        trfrTransCont.put(MessageType.PAYLOAD, PAYLOAD);

        transContainer.put(MessageClass.TRANSFER_MESSAGES, trfrTransCont);

        // SSNM
        HashMap<Integer, String> ssnmTransCont = new HashMap<Integer, String>();
        ssnmTransCont.put(MessageType.DESTINATION_UNAVAILABLE, DUNA);
        ssnmTransCont.put(MessageType.DESTINATION_AVAILABLE, DAVA);
        ssnmTransCont.put(MessageType.DESTINATION_STATE_AUDIT, DAUD);
        ssnmTransCont.put(MessageType.SIGNALING_CONGESTION, SCON);
        ssnmTransCont.put(MessageType.DESTINATION_USER_PART_UNAVAILABLE, DUPU);
        ssnmTransCont.put(MessageType.DESTINATION_RESTRICTED, DRST);

        transContainer.put(MessageClass.SIGNALING_NETWORK_MANAGEMENT, ssnmTransCont);

        // ASPSM
        HashMap<Integer, String> aspsmTransCont = new HashMap<Integer, String>();
        aspsmTransCont.put(MessageType.ASP_UP, ASP_UP);
        aspsmTransCont.put(MessageType.ASP_UP_ACK, ASP_UP_ACK);
        aspsmTransCont.put(MessageType.ASP_DOWN, ASP_DOWN);
        aspsmTransCont.put(MessageType.ASP_DOWN_ACK, ASP_DOWN_ACK);
        aspsmTransCont.put(MessageType.HEARTBEAT, HEARTBEAT);
        aspsmTransCont.put(MessageType.HEARTBEAT_ACK, HEARTBEAT_ACK);

        transContainer.put(MessageClass.ASP_STATE_MAINTENANCE, aspsmTransCont);

        // ASPTM
        HashMap<Integer, String> asptmTransCont = new HashMap<Integer, String>();
        asptmTransCont.put(MessageType.ASP_ACTIVE, ASP_ACTIVE);
        asptmTransCont.put(MessageType.ASP_ACTIVE_ACK, ASP_ACTIVE_ACK);
        asptmTransCont.put(MessageType.ASP_INACTIVE, ASP_INACTIVE);
        asptmTransCont.put(MessageType.ASP_INACTIVE_ACK, ASP_INACTIVE_ACK);

        transContainer.put(MessageClass.ASP_TRAFFIC_MAINTENANCE, asptmTransCont);

        // MGMT
        HashMap<Integer, String> mgmtTransCont = new HashMap<Integer, String>();

        // NTFY
        mgmtTransCont.put((Status.STATUS_AS_State_Change << 16 | Status.INFO_Reserved), AS_STATE_CHANGE_RESERVE);
        mgmtTransCont.put((Status.STATUS_AS_State_Change << 16 | Status.INFO_AS_INACTIVE), AS_STATE_CHANGE_INACTIVE);
        mgmtTransCont.put((Status.STATUS_AS_State_Change << 16 | Status.INFO_AS_ACTIVE), AS_STATE_CHANGE_ACTIVE);
        mgmtTransCont.put((Status.STATUS_AS_State_Change << 16 | Status.INFO_AS_PENDING), AS_STATE_CHANGE_PENDING);

        mgmtTransCont.put((Status.STATUS_Other << 16 | Status.INFO_Insufficient_ASP_Resources_Active), OTHER_INSUFFICIENT_ASP);
        mgmtTransCont.put((Status.STATUS_Other << 16 | Status.INFO_Alternate_ASP_Active), OTHER_ALTERNATE_ASP_ACTIVE);
        mgmtTransCont.put((Status.STATUS_Other << 16 | Status.INFO_Alternate_ASP_Failure), OTHER_ALTERNATE_ASP_FAILURE);

        // ERR
        mgmtTransCont.put(ErrorCode.Invalid_Version, INVALID_VERSION);
        mgmtTransCont.put(ErrorCode.Unsupported_Message_Class, UNSUPPORTED_MESSAGE_CLASS);
        mgmtTransCont.put(ErrorCode.Unsupported_Message_Type, UNSUPPORTED_MESSAGE_TYPE);
        mgmtTransCont.put(ErrorCode.Unsupported_Traffic_Mode_Type, UNSUPPORTED_TRAFFIC_MODE_TYPE);
        mgmtTransCont.put(ErrorCode.Unexpected_Message, UNEXPECTED_MESSAGE);
        mgmtTransCont.put(ErrorCode.Protocol_Error, PROTOCOL_ERROR);
        mgmtTransCont.put(ErrorCode.Invalid_Stream_Identifier, INVALID_STREAM_IDENTIFIER);
        mgmtTransCont.put(ErrorCode.Refused_Management_Blocking, REFUSED_MANAGEMENT_BLOCKING);
        mgmtTransCont.put(ErrorCode.ASP_Identifier_Required, ASP_IDENTIFIER_REQUIRED);
        mgmtTransCont.put(ErrorCode.Invalid_ASP_Identifier, INVALID_ASP_IDENTIFIER);
        mgmtTransCont.put(ErrorCode.Invalid_Parameter_Value, INVALID_PARAMETER_VALUE);
        mgmtTransCont.put(ErrorCode.Parameter_Field_Error, PARAMETER_FIELD_ERROR);
        mgmtTransCont.put(ErrorCode.Unexpected_Parameter, UNEXPECTED_PARAMETER);
        mgmtTransCont.put(ErrorCode.Invalid_Network_Appearance, INVALID_NETWORK_APPEARANCE);
        mgmtTransCont.put(ErrorCode.Missing_Parameter, MISSING_PARAMETER);
        mgmtTransCont.put(ErrorCode.Destination_Status_Unknown, DESTINATION_STATUS_UNKNOWN);
        mgmtTransCont.put(ErrorCode.Invalid_Routing_Context, INVALID_ROUTING_CONTEXT);
        mgmtTransCont.put(ErrorCode.No_Configured_AS_for_ASP, NO_CONFIGURED_AS_FOR_ASP);


        transContainer.put(MessageClass.MANAGEMENT, mgmtTransCont);
    }

    public static String getTransition(M3UAMessage message) {
        switch (message.getMessageClass()) {
            case MessageClass.MANAGEMENT:
                switch (message.getMessageType()) {
                    case MessageType.ERROR:
                        int errorCode = ((Error) message).getErrorCode().getCode();
                        return String.valueOf(errorCode);
                    case MessageType.NOTIFY:
                        Status status = ((Notify) message).getStatus();
                        return transContainer.get(message.getMessageClass()).get((status.getType() << 16 | status.getInfo()));
                }
            default:
                return transContainer.get(message.getMessageClass()).get(message.getMessageType());
        }
    }
}
