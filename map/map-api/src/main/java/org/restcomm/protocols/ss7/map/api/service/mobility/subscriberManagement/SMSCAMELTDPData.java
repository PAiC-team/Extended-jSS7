
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 SMS-CAMEL-TDP-Data ::= SEQUENCE { sms-TriggerDetectionPoint [0] SMS-TriggerDetectionPoint, serviceKey [1] ServiceKey,
 * gsmSCF-Address [2] ISDN-AddressString, defaultSMS-Handling [3] DefaultSMS-Handling, extensionContainer [4] ExtensionContainer
 * OPTIONAL, ... }
 *
 * ServiceKey ::= INTEGER (0..2147483647)
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SMSCAMELTDPData extends Serializable {

    SMSTriggerDetectionPoint getSMSTriggerDetectionPoint();

    long getServiceKey();

    ISDNAddressString getGsmSCFAddress();

    DefaultSMSHandling getDefaultSMSHandling();

    MAPExtensionContainer getExtensionContainer();

}
