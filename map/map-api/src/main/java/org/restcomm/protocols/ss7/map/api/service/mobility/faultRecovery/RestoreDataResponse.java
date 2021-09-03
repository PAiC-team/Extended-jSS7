
package org.restcomm.protocols.ss7.map.api.service.mobility.faultRecovery;

import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.MobilityMessage;

/**
 *
 <code>
 MAP V3: RestoreDataRes ::= SEQUENCE {
   hlr-Number         ISDN-AddressString,
   msNotReachable     NULL OPTIONAL,
   extensionContainer ExtensionContainer OPTIONAL,
   ...
}

MAP V2: RestoreDataRes ::= SEQUENCE {
  hlr-Number       ISDN-AddressString,
  msNotReachable   NULL OPTIONAL,
  ...
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface RestoreDataResponse extends MobilityMessage {

    ISDNAddressString getHlrNumber();

    boolean getMsNotReachable();

    MAPExtensionContainer getExtensionContainer();

}
