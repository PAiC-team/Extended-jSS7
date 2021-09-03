
package org.restcomm.protocols.ss7.map.api.service.sms;

import java.util.ArrayList;

import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 MT-ForwardSM-VGCS-Res ::= SEQUENCE { sm-RP-UI [0] SignalInfo OPTIONAL, dispatcherList [1] DispatcherList OPTIONAL,
 * ongoingCall NULL OPTIONAL, extensionContainer [2] ExtensionContainer OPTIONAL, ...}
 *
 * DispatcherList ::= SEQUENCE SIZE (1..5) OF ISDN-AddressString
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface MtForwardSMVgscResponse extends SmsMessage {

    SmsSignalInfo getSM_RP_UI();

    ArrayList<ISDNAddressString> getDispatcherList();

    boolean getOngoingCall();

    MAPExtensionContainer getExtensionContainer();

}
