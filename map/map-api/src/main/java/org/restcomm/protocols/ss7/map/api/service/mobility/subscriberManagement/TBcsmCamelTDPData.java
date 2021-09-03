
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 T-BcsmCamelTDPData ::= SEQUENCE { t-BcsmTriggerDetectionPoint T-BcsmTriggerDetectionPoint, serviceKey ServiceKey,
 * gsmSCF-Address [0] ISDN-AddressString, defaultCallHandling [1] DefaultCallHandling, extensionContainer [2] ExtensionContainer
 * OPTIONAL, ... }
 *
 * ServiceKey ::= INTEGER (0..2147483647)
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface TBcsmCamelTDPData extends Serializable {

    TBcsmTriggerDetectionPoint getTBcsmTriggerDetectionPoint();

    long getServiceKey();

    ISDNAddressString getGsmSCFAddress();

    DefaultCallHandling getDefaultCallHandling();

    MAPExtensionContainer getExtensionContainer();

}
