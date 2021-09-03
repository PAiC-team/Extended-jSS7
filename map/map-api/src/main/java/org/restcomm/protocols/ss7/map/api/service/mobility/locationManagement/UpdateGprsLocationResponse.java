
package org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement;

import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.MobilityMessage;

/**
 *
 MAP V3: UpdateGprsLocationRes ::= SEQUENCE { hlr-Number ISDN-AddressString, extensionContainer ExtensionContainer OPTIONAL,
 * ..., add-Capability NULL OPTIONAL, sgsn-mmeSeparationSupported [0] NULL OPTIONAL }
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface UpdateGprsLocationResponse extends MobilityMessage {

    ISDNAddressString getHlrNumber();

    MAPExtensionContainer getExtensionContainer();

    boolean getAddCapability();

    boolean getSgsnMmeSeparationSupported();

}
