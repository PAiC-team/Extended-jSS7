
package org.restcomm.protocols.ss7.tcapAnsi.api;

import org.restcomm.protocols.ss7.tcapAnsi.api.asn.ApplicationContext;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.UserInformation;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.UserInformationElement;
import org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.Dialog;
import org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.events.TCConversationRequest;
import org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.events.TCQueryRequest;
import org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.events.TCResponseRequest;
import org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.events.TCUniRequest;
import org.restcomm.protocols.ss7.tcapAnsi.api.tc.dialog.events.TCUserAbortRequest;

/**
 *
 * * @author baranowb
 *
 * @author amit bhayani
 *
 */
public interface DialogPrimitiveFactory {

    TCQueryRequest createQuery(Dialog d, boolean dialogTermitationPermission);

    TCConversationRequest createConversation(Dialog d, boolean dialogTermitationPermission);

    TCResponseRequest createResponse(Dialog d);

    TCUserAbortRequest createUAbort(Dialog d);

    TCUniRequest createUni(Dialog d);

    ApplicationContext createApplicationContext(long[] val);

    ApplicationContext createApplicationContext(long val);

    UserInformation createUserInformation();

    UserInformationElement createUserInformationElement();

}