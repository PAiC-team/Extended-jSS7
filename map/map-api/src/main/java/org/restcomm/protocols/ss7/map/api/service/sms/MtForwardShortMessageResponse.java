package org.restcomm.protocols.ss7.map.api.service.sms;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 MT-ForwardSM-Res ::= SEQUENCE { sm-RP-UI SignalInfo OPTIONAL, extensionContainer ExtensionContainer OPTIONAL, ...}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface MtForwardShortMessageResponse extends SmsMessage {

    SmsSignalInfo getSM_RP_UI();

    MAPExtensionContainer getExtensionContainer();

}
