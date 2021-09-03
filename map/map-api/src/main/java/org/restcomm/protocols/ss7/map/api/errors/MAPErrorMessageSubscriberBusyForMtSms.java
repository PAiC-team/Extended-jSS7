package org.restcomm.protocols.ss7.map.api.errors;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 * subscriberBusyForMT-SMS ERROR ::= { PARAMETER SubBusyForMT-SMS-Param -- optional CODE local:31 }
 *
 *
 * SubBusyForMT-SMS-Param ::= SEQUENCE { extensionContainer ExtensionContainer OPTIONAL, ... , gprsConnectionSuspended NULL
 * OPTIONAL } -- If GprsConnectionSuspended is not understood it shall -- be discarded
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface MAPErrorMessageSubscriberBusyForMtSms extends MAPErrorMessage {

    MAPExtensionContainer getExtensionContainer();

    Boolean getGprsConnectionSuspended();

    void setExtensionContainer(MAPExtensionContainer extensionContainer);

    void setGprsConnectionSuspended(Boolean gprsConnectionSuspended);

}
