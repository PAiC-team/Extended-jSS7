
package org.restcomm.protocols.ss7.oam.common.tcap;

import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.tcap.api.TCAPException;
import org.restcomm.protocols.ss7.tcap.api.TCAPProvider;
import org.restcomm.protocols.ss7.tcap.api.TCAPStack;
import org.restcomm.protocols.ss7.tcap.api.TCListener;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.Dialog;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCBeginIndication;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCContinueIndication;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCEndIndication;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCNoticeIndication;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCPAbortIndication;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCUniIndication;
import org.restcomm.protocols.ss7.tcap.api.tc.dialog.events.TCUserAbortIndication;
import org.restcomm.protocols.ss7.tcap.asn.ApplicationContextName;
import org.restcomm.protocols.ss7.tcap.asn.comp.Invoke;

/**
 *
 * @author baranowb
 *
 */
public class EventTestHarness implements TCListener {

    public static final long[] _ACN_ = new long[] { 0, 4, 0, 0, 1, 0, 19, 2 };
    
    protected Dialog dialog;
    protected TCAPStack stack;
    protected SccpAddress thisAddress;
    protected SccpAddress remoteAddress;
    protected TCAPProvider tcapProvider;

    protected int sequence = 0;

    protected ApplicationContextName acn;
//    protected UserInformation ui;

    public EventTestHarness(TCAPStack stack, SccpAddress thisAddress, SccpAddress remoteAddress) {
        super();
        this.stack = stack;
        this.thisAddress = thisAddress;
        this.remoteAddress = remoteAddress;
        this.tcapProvider = this.stack.getProvider();
        this.tcapProvider.addTCListener(this);
    }

    public void startClientDialog() throws TCAPException {
        if (dialog != null) {
            throw new IllegalStateException("Dialog exists...");
        }
        dialog = this.tcapProvider.getNewDialog(thisAddress, remoteAddress);
    }


    @Override
    public void onTCUni(TCUniIndication ind) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onTCBegin(TCBeginIndication ind) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onTCContinue(TCContinueIndication ind) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onTCEnd(TCEndIndication ind) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onTCUserAbort(TCUserAbortIndication ind) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onTCPAbort(TCPAbortIndication ind) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onTCNotice(TCNoticeIndication ind) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onDialogReleased(Dialog d) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onInvokeTimeout(Invoke tcInvokeRequest) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onDialogTimeout(Dialog d) {
        // TODO Auto-generated method stub
        
    }

}
