
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;
import java.util.ArrayList;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 SMS-CSI ::= SEQUENCE { sms-CAMEL-TDP-DataList [0] SMS-CAMEL-TDP-DataList OPTIONAL, camelCapabilityHandling [1]
 * CamelCapabilityHandling OPTIONAL, extensionContainer [2] ExtensionContainer OPTIONAL, notificationToCSE [3] NULL OPTIONAL,
 * csi-Active [4] NULL OPTIONAL, ...} -- notificationToCSE and csi-Active shall not be present -- when MO-SMS-CSI or MT-SMS-CSI
 * is sent to VLR or SGSN. -- They may only be included in ATSI/ATM ack/NSDC message. -- SMS-CAMEL-TDP-Data and
 * camelCapabilityHandling shall be present in -- the SMS-CSI sequence. -- If SMS-CSI is segmented, sms-CAMEL-TDP-DataList and
 * camelCapabilityHandling shall be -- present in the first segment
 *
 * SMS-CAMEL-TDP-DataList ::= SEQUENCE SIZE (1..1-) OF SMS-CAMEL-TDP-Data -- SMS-CAMEL-TDP-DataList shall not contain more than
 * one instance of -- SMS-CAMEL-TDP-Data containing the same value for sms-TriggerDetectionPoint.
 *
 * CamelCapabilityHandling ::= INTEGER(1..16) -- value 1 = CAMEL phase 1, -- value 2 = CAMEL phase 2, -- value 3 = CAMEL Phase
 * 3, -- value 4 = CAMEL phase 4: -- reception of values greater than 4 shall be treated as CAMEL phase 4.
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SMSCSI extends Serializable {

    ArrayList<SMSCAMELTDPData> getSmsCamelTdpDataList();

    Integer getCamelCapabilityHandling();

    MAPExtensionContainer getExtensionContainer();

    boolean getNotificationToCSE();

    boolean getCsiActive();

}
