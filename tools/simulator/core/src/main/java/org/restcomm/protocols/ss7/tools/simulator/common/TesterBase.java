
package org.restcomm.protocols.ss7.tools.simulator.common;

import org.apache.log4j.Level;
import org.restcomm.protocols.ss7.map.api.MAPDialog;
import org.restcomm.protocols.ss7.map.api.MAPDialogListener;
import org.restcomm.protocols.ss7.map.api.MAPMessage;
import org.restcomm.protocols.ss7.map.api.MAPServiceListener;
import org.restcomm.protocols.ss7.map.api.dialog.MAPAbortProviderReason;
import org.restcomm.protocols.ss7.map.api.dialog.MAPAbortSource;
import org.restcomm.protocols.ss7.map.api.dialog.MAPNoticeProblemDiagnostic;
import org.restcomm.protocols.ss7.map.api.dialog.MAPRefuseReason;
import org.restcomm.protocols.ss7.map.api.dialog.MAPUserAbortChoice;
import org.restcomm.protocols.ss7.map.api.errors.MAPErrorMessage;
import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.tcap.asn.ApplicationContextName;
import org.restcomm.protocols.ss7.tcap.asn.comp.Problem;
import org.restcomm.protocols.ss7.tools.simulator.management.TesterHostInterface;

public abstract class TesterBase implements MAPDialogListener, MAPServiceListener {

    protected TesterHostInterface testerHost;
    protected final String className;

    public TesterBase(String name) {
        this.className = name;
    }

    public void setTesterHost(TesterHostInterface testerHost) {
        this.testerHost = testerHost;
    }

    @Override
    public void onErrorComponent(MAPDialog dlg, Long invokeId, MAPErrorMessage msg) {
        String uData = msg.toString() + ", dlgId=" + dlg.getLocalDialogId() + ", invokeId=" + invokeId;
        this.testerHost.sendNotif(this.className, "Rcvd: Error component", uData, Level.DEBUG);
    }

    @Override
    public void onRejectComponent(MAPDialog mapDialog, Long invokeId, Problem problem, boolean isLocalOriginated) {
        String uData = problem.toString() + ", dlgId=" + mapDialog.getLocalDialogId() + ", InvokeId=" + invokeId
                + ", isLocalOriginated=" + isLocalOriginated;
        this.testerHost.sendNotif(this.className, "Rcvd: RejectComponent", uData, Level.DEBUG);
    }

    @Override
    public void onInvokeTimeout(MAPDialog arg0, Long arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onMAPMessage(MAPMessage arg0) {
    }

    @Override
    public void onDialogAccept(MAPDialog arg0, MAPExtensionContainer arg1) {
    }

    @Override
    public void onDialogClose(MAPDialog arg0) {
    }

    @Override
    public void onDialogDelimiter(MAPDialog arg0) {
    }

    @Override
    public void onDialogNotice(MAPDialog mapDialog, MAPNoticeProblemDiagnostic notice) {
        String uData = "dialogNotice=" + notice.toString() + ", dlgId=" + mapDialog.getLocalDialogId();
        this.testerHost.sendNotif(this.className, "Rcvd: DialogNotice", uData, Level.DEBUG);
    }

    @Override
    public void onDialogProviderAbort(MAPDialog mapDialog, MAPAbortProviderReason abortProviderReason,
            MAPAbortSource abortSource, MAPExtensionContainer extensionContainer) {
        String uData = "abortProviderReason=" + abortProviderReason + ", abortSource=" + abortSource + ", dlgId="
                + mapDialog.getLocalDialogId();
        this.testerHost.sendNotif(this.className, "Rcvd: DialogProviderAbort", uData, Level.DEBUG);
    }

    @Override
    public void onDialogReject(MAPDialog mapDialog, MAPRefuseReason refuseReason,
            ApplicationContextName alternativeApplicationContext, MAPExtensionContainer extensionContainer) {
        String uData = "refuseReason=" + refuseReason + ", alternativeApplicationContext=" + alternativeApplicationContext
                + ", dlgId=" + mapDialog.getLocalDialogId();
        this.testerHost.sendNotif(this.className, "Rcvd: DialogReject", uData, Level.DEBUG);
    }

    @Override
    public void onDialogRelease(MAPDialog arg0) {
    }

    @Override
    public void onDialogRequest(MAPDialog arg0, AddressString arg1, AddressString arg2, MAPExtensionContainer arg3) {
    }

    @Override
    public void onDialogRequestEricsson(MAPDialog arg0, AddressString arg1, AddressString arg2, AddressString arg3, AddressString arg4) {
    }

    @Override
    public void onDialogTimeout(MAPDialog arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onDialogUserAbort(MAPDialog mapDialog, MAPUserAbortChoice userReason, MAPExtensionContainer extensionContainer) {
        String uData = "userReason=" + userReason + ", dlgId=" + mapDialog.getLocalDialogId();
        this.testerHost.sendNotif(this.className, "Rcvd: DialogUserAbort", uData, Level.DEBUG);
    }

}
