
package org.restcomm.protocols.ss7.map.api.errors;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 busySubscriber ERROR ::= { PARAMETER BusySubscriberParam -- optional CODE local:45 }
 *
 * BusySubscriberParam ::= SEQUENCE { extensionContainer ExtensionContainer OPTIONAL, ..., ccbs-Possible [0] NULL OPTIONAL,
 * ccbs-Busy [1] NULL OPTIONAL}
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface MAPErrorMessageBusySubscriber extends MAPErrorMessage {

    MAPExtensionContainer getExtensionContainer();

    boolean getCcbsPossible();

    boolean getCcbsBusy();

    void setExtensionContainer(MAPExtensionContainer val);

    void setCcbsPossible(boolean val);

    void setCcbsBusy(boolean val);

}
