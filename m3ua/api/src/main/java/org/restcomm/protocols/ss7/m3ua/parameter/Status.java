package org.restcomm.protocols.ss7.m3ua.parameter;

/**
 * The status parameter is used in the notify message to provide the status of the specified entity. There are two fields in
 * this parameter, the status type and the status information field. The status information field provides the current state of
 * the entity identified in the routing context of the notify message.
 *
 * @author amit bhayani
 *
 */
public interface Status extends Parameter {

    int STATUS_AS_State_Change = 0x01;
    int STATUS_Other = 0x02;

    // If the Status Type is AS-State_Change the following Status Information
    // values are used:
    int INFO_Reserved = 0x01;
    int INFO_AS_INACTIVE = 0x02;
    int INFO_AS_ACTIVE = 0x03;
    int INFO_AS_PENDING = 0x04;

    // If the Status Type is Other, then the following Status Information values
    // are defined:
    int INFO_Insufficient_ASP_Resources_Active = 0x01;
    int INFO_Alternate_ASP_Active = 0x02;
    int INFO_Alternate_ASP_Failure = 0x03;

    int getType();

    int getInfo();
}
