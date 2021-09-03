
package org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement;

import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.MobilityMessage;

/**
 * MAP V3: UpdateLocationRes ::= SEQUENCE { hlr-Number ISDN-AddressString, extensionContainer ExtensionContainer OPTIONAL, ...,
 * add-Capability NULL OPTIONAL, pagingArea-Capability [0]NULL OPTIONAL }
 *
 * MAP V2: UpdateLocationRes ::= CHOICE { hlr-Number ISDN-AddressString, -- hlr-Number must not be used in version greater 1
 * extensibleUpdateLocationRes ExtensibleUpdateLocationRes} -- extensibleUpdateLocationRes must not be used in version 1
 *
 * ExtensibleUpdateLocationRes ::= SEQUENCE { hlr-Number ISDN-AddressString, ...}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface UpdateLocationResponse extends MobilityMessage {

    ISDNAddressString getHlrNumber();

    MAPExtensionContainer getExtensionContainer();

    boolean getAddCapability();

    boolean getPagingAreaCapability();

    long getMapProtocolVersion();

}
