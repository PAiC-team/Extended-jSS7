package org.restcomm.protocols.ss7.tcap.api;

import org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCBeginRequest;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCContinueRequest;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCEndRequest;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCUniRequest;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCUserAbortRequest;
import org.restcomm.protocols.ss7.tcap.asn.ApplicationContextName;
import org.restcomm.protocols.ss7.tcap.asn.UserInformation;

/**
 *
 * * @author baranowb
 *
 * @author amit bhayani
 *
 */
public interface DialogPrimitiveFactory {

    TCBeginRequest createBegin(Dialog tcapDialog);

    TCContinueRequest createContinue(Dialog tcapDialog);

    TCEndRequest createEnd(Dialog tcapDialog);

    TCUserAbortRequest createUAbort(Dialog tcapDialog);

    TCUniRequest createUni(Dialog tcapDialog);

    ApplicationContextName createApplicationContextName(long[] oid);

    UserInformation createUserInformation();

}