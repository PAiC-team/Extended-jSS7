
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 DP-AnalysedInfoCriterium ::= SEQUENCE { dialledNumber ISDN-AddressString, serviceKey ServiceKey, gsmSCF-Address
 * ISDN-AddressString, defaultCallHandling DefaultCallHandling, extensionContainer ExtensionContainer OPTIONAL, ...}
 *
 * ServiceKey ::= INTEGER (0..2147483647)
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface DPAnalysedInfoCriterium extends Serializable {

    ISDNAddressString getDialledNumber();

    long getServiceKey();

    ISDNAddressString getGsmSCFAddress();

    DefaultCallHandling getDefaultCallHandling();

    MAPExtensionContainer getExtensionContainer();

}
