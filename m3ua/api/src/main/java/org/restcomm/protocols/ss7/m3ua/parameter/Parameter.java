package org.restcomm.protocols.ss7.m3ua.parameter;

/**
 * Defines list of valid parameters.
 *
 * @author kulikov
 */
public interface Parameter {

    short INFO_String = 0x0004;
    short Routing_Context = 0x0006;
    short Diagnostic_Information = 0x0007;
    short Heartbeat_Data = 0x0009;
    short Traffic_Mode_Type = 0x000b;
    short Error_Code = 0x000c;
    short Status = 0x000d;
    short ASP_Identifier = 0x0011;
    short Affected_Point_Code = 0x0012;
    short Correlation_ID = 0x0013;
    short Network_Appearance = 0x0200;
    short User_Cause = 0x0204;
    short Congestion_Indications = 0x0205;
    short Concerned_Destination = 0x0206;
    short Routing_Key = 0x0207;
    short Registration_Result = 0x0208;
    short Deregistration_Result = 0x0209;
    short Local_Routing_Key_Identifier = 0x020a;
    short Destination_Point_Code = 0x020b;
    short Service_Indicators = 0x020c;
    short Originating_Point_Code_List = 0x020e;
    short Circuit_Range = 0x020f;
    short Protocol_Data = 0x0210;
    short Registration_Status = 0x0212;
    short Deregistration_Status = 0x0213;

}
