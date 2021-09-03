package org.restcomm.protocols.ss7.map.api.service.lsm;

import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 * SubscriberLocationReport-Res ::= SEQUENCE { extensionContainer ExtensionContainer OPTIONAL, ..., na-ESRK [0]
 * ISDN-AddressString OPTIONAL, na-ESRD [1] ISDN-AddressString OPTIONAL } -- na-ESRK and na-ESRD are mutually exclusive -- --
 * exception handling -- receipt of both na-ESRK and na-ESRD shall be treated the same as a return error
 *
 *
 * @author amit bhayani
 *
 */
public interface SubscriberLocationReportResponse extends LsmMessage {

    MAPExtensionContainer getExtensionContainer();

    ISDNAddressString getNaESRK();

    ISDNAddressString getNaESRD();

}
