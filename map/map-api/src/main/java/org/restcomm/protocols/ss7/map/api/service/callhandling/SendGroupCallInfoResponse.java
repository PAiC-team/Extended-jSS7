
package org.restcomm.protocols.ss7.map.api.service.callhandling;

import org.restcomm.protocols.ss7.map.api.primitives.ASCICallReference;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.AdditionalInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.AdditionalSubscriptions;

/**
 *
 SendGroupCallInfoRes ::= SEQUENCE { anchorMSC-Address [0] ISDN-AddressString OPTIONAL, asciCallReference [1]
 * ASCI-CallReference OPTIONAL, imsi [2] IMSI OPTIONAL, additionalInfo [3] AdditionalInfo OPTIONAL, additionalSubscriptions [4]
 * AdditionalSubscriptions OPTIONAL, kc [5] Kc OPTIONAL, extensionContainer [6] ExtensionContainer OPTIONAL, ... }
 *
 * Kc ::= OCTET STRING (SIZE (8))
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SendGroupCallInfoResponse extends CallHandlingMessage {

     ISDNAddressString getAnchorMscAddress();

     ASCICallReference getAsciCallReference();

     IMSI getImsi();

     AdditionalInfo getAdditionalInfo();

     AdditionalSubscriptions getAdditionalSubscriptions();

     byte[] getKc();

     MAPExtensionContainer getExtensionContainer();

}
