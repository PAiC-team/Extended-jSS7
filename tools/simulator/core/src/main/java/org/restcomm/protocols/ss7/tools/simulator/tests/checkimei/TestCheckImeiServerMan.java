

package org.restcomm.protocols.ss7.tools.simulator.tests.checkimei;

import org.apache.log4j.Level;
import org.restcomm.protocols.ss7.map.api.MAPDialog;
import org.restcomm.protocols.ss7.map.api.MAPDialogListener;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPProvider;
import org.restcomm.protocols.ss7.map.api.dialog.MAPUserAbortChoice;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.MAPDialogMobility;
import org.restcomm.protocols.ss7.map.api.service.mobility.MAPServiceMobilityListener;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.AuthenticationFailureReportRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.AuthenticationFailureReportResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.SendAuthenticationInfoRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.authentication.SendAuthenticationInfoResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.faultRecovery.ForwardCheckSSIndicationRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.faultRecovery.ResetRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.faultRecovery.RestoreDataRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.faultRecovery.RestoreDataResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.imei.CheckImeiRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.imei.CheckImeiResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.imei.EquipmentStatus;
import org.restcomm.protocols.ss7.map.api.service.mobility.imei.UESBIIu;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.CancelLocationRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.CancelLocationResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.PurgeMSRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.PurgeMSResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.SendIdentificationRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.SendIdentificationResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.UpdateGprsLocationRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.UpdateGprsLocationResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.UpdateLocationRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.UpdateLocationResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.oam.ActivateTraceModeRequest_Mobility;
import org.restcomm.protocols.ss7.map.api.service.mobility.oam.ActivateTraceModeResponse_Mobility;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.AnyTimeInterrogationRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.AnyTimeInterrogationResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.AnyTimeSubscriptionInterrogationRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.AnyTimeSubscriptionInterrogationResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.ProvideSubscriberInfoRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.ProvideSubscriberInfoResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.DeleteSubscriberDataRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.DeleteSubscriberDataResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.InsertSubscriberDataRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.InsertSubscriberDataResponse;
import org.restcomm.protocols.ss7.map.dialog.MAPUserAbortChoiceImpl;
import org.restcomm.protocols.ss7.tcap.asn.comp.Problem;
import org.restcomm.protocols.ss7.tools.simulator.Stoppable;
import org.restcomm.protocols.ss7.tools.simulator.common.TesterBase;
import org.restcomm.protocols.ss7.tools.simulator.level3.MapMan;

/**
 * @author mnowa
 *
 */
public class TestCheckImeiServerMan extends TesterBase implements TestCheckImeiServerManMBean, Stoppable, MAPDialogListener, MAPServiceMobilityListener {

    public static String SOURCE_NAME = "TestCheckImeiServer";

    private final String name;
    private MapMan mapMan;

    MAPDialogMobility currentDialog = null;

    private boolean isStarted = false;
    private int countCheckImeiReq = 0;
    private int countCheckImeiResp = 0;
    private String currentRequestDef = "";
    private boolean needSendSend = false;
    private boolean needSendClose = false;

    public TestCheckImeiServerMan() {
        super(SOURCE_NAME);
        this.name = "???";
    }

    public TestCheckImeiServerMan(String name) {
        super(SOURCE_NAME);
        this.name = name;
    }

    public void setMapMan(MapMan val) {
        this.mapMan = val;
    }

    @Override
    public String closeCurrentDialog() {
        if (!isStarted)
            return "The tester is not started";

        MAPDialogMobility curDialog = currentDialog;
        if (curDialog != null) {
            try {
                MAPUserAbortChoice choice = new MAPUserAbortChoiceImpl();
                choice.setUserSpecificReason();
                curDialog.abort(choice);
                this.doRemoveDialog();
                return "The current dialog has been closed";
            } catch (MAPException e) {
                this.doRemoveDialog();
                return "Exception when closing the current dialog: " + e.toString();
            }
        } else {
            return "No current dialog";
        }
    }

    private void doRemoveDialog() {
        currentDialog = null;
        // currentRequestDef = "";
    }

    // Dialog messgaes

    @Override
    public void onRejectComponent(MAPDialog mapDialog, Long invokeId, Problem problem, boolean isLocalOriginated) {
        super.onRejectComponent(mapDialog, invokeId, problem, isLocalOriginated);
        if (isLocalOriginated)
            needSendClose = true;
    }

    @Override
    public void onDialogDelimiter(MAPDialog mapDialog) {
        try {
            if (needSendSend) {
                needSendSend = false;
                mapDialog.send();
                return;
            }
        } catch (Exception e) {
            this.testerHost.sendNotif(SOURCE_NAME, "Exception when invoking send() : " + e.getMessage(), e, Level.ERROR);
            return;
        }
        try {
            if (needSendClose) {
                needSendClose = false;
                mapDialog.close(false);
                return;
            }
        } catch (Exception e) {
            this.testerHost.sendNotif(SOURCE_NAME, "Exception when invoking close() : " + e.getMessage(), e, Level.ERROR);
            return;
        }
    }

    @Override
    public void onDialogRelease(MAPDialog mapDialog) {
        if (this.currentDialog == mapDialog)
            this.doRemoveDialog();
    }

    public boolean start() {
        this.countCheckImeiReq = 0;
        this.countCheckImeiResp = 0;
        this.currentRequestDef = "";

        MAPProvider mapProvider = this.mapMan.getMAPStack().getMAPProvider();
        mapProvider.getMAPServiceMobility().activate();
        mapProvider.getMAPServiceMobility().addMAPServiceListener(this);
        mapProvider.addMAPDialogListener(this);
        this.testerHost.sendNotif(SOURCE_NAME, "CHECK IMEI Server has been started", "", Level.INFO);
        isStarted = true;

        return true;
    }

    // Stoppable interface methods

    @Override
    public void stop() {
        MAPProvider mapProvider = this.mapMan.getMAPStack().getMAPProvider();
        isStarted = false;

        this.doRemoveDialog();
        mapProvider.getMAPServiceMobility().deactivate();
        mapProvider.getMAPServiceMobility().removeMAPServiceListener(this);
        mapProvider.removeMAPDialogListener(this);
        this.testerHost.sendNotif(SOURCE_NAME, "CHECK IMEI Server has been stopped", "", Level.INFO);

    }

    @Override
    public void execute() {
    }

    @Override
    public String getState() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append(SOURCE_NAME);
        sb.append(": CurDialog=");
        MAPDialogMobility curDialog = currentDialog;
        if (curDialog != null)
            sb.append(curDialog.getLocalDialogId());
        else
            sb.append("No");
        sb.append("<br>Count: countCheckImeiReq-");
        sb.append(countCheckImeiReq);
        sb.append(", countCheckImeiResp-");
        sb.append(countCheckImeiResp);
        sb.append("</html>");
        return sb.toString();
    }

    // TestCheckImeiServerManMBean interface methods

    @Override
    public EquipmentStatusType getAutoEquipmentStatus() {
        return new EquipmentStatusType(this.testerHost.getConfigurationData().getTestCheckImeiServerConfigurationData().getAutoEquipmentStatus().getCode());
    }

    @Override
    public String getAutoEquipmentStatus_Value() {
        return new EquipmentStatusType(this.testerHost.getConfigurationData().getTestCheckImeiServerConfigurationData().getAutoEquipmentStatus().getCode()).toString();
    }

    @Override
    public void setAutoEquipmentStatus(EquipmentStatusType equipmentStatusType) {
        this.testerHost.getConfigurationData().getTestCheckImeiServerConfigurationData().setAutoEquipmentStatus(EquipmentStatus.getInstance(equipmentStatusType.intValue()));
        this.testerHost.markStore();
    }

    @Override
    public void putAutoEquipmentStatus(String val) {
        EquipmentStatusType x = EquipmentStatusType.createInstance(val);
        if (x != null)
            this.setAutoEquipmentStatus(x);
    }

    @Override
    public boolean isOneNotificationFor100Dialogs() {
        return this.testerHost.getConfigurationData().getTestCheckImeiServerConfigurationData().isOneNotificationFor100Dialogs();
    }

    @Override
    public void setOneNotificationFor100Dialogs(boolean val) {
        this.testerHost.getConfigurationData().getTestCheckImeiServerConfigurationData().setOneNotificationFor100Dialogs(val);
        this.testerHost.markStore();
    }

    @Override
    public String getCurrentRequestDef() {
        if (this.currentDialog != null)
            return "CurDialog: " + currentRequestDef;
        else
            return "PrevDialog: " + currentRequestDef;
    }

    // key MAPServiceMobilityListener interface methods


    @Override
    public void onCheckImeiRequest(CheckImeiRequest request) {
        if (!isStarted)
            return;

        currentRequestDef = "";

        this.countCheckImeiReq++;

        MAPDialogMobility curDialog = request.getMAPDialog();

        if (!this.testerHost.getConfigurationData().getTestCheckImeiServerConfigurationData().isOneNotificationFor100Dialogs()) {
            currentRequestDef += "Rcvd: CheckImeiReq: =\"" + request+ "\";";

            String uData = this.createCheckImeiReqData(request);
            this.testerHost.sendNotif(SOURCE_NAME, "Rcvd: CheckImeiReq: " + "imei=" + request.getIMEI(), uData, Level.DEBUG);
        }

        try {

            MAPExtensionContainer extensionContainer = null;
            UESBIIu bmuef = null;
            EquipmentStatus equipmentStatus = this.testerHost.getConfigurationData().getTestCheckImeiServerConfigurationData().getAutoEquipmentStatus();

            curDialog.addCheckImeiResponse(request.getInvokeId(), equipmentStatus, bmuef, extensionContainer);

            this.countCheckImeiResp++;

            this.needSendClose = true;

            if (!this.testerHost.getConfigurationData().getTestCheckImeiServerConfigurationData().isOneNotificationFor100Dialogs()) {
                String uData = this.createCheckImeiRespData(curDialog.getLocalDialogId());
                this.testerHost.sendNotif(SOURCE_NAME, "Sent CheckImeiResponse: " + "equipmentStatus=" + equipmentStatus, uData, Level.DEBUG);
            }


        } catch (MAPException e) {
            this.testerHost.sendNotif(SOURCE_NAME, "Exception when invoking addCheckImeiResponse() : " + e.getMessage(), e, Level.ERROR);

        } finally {
            if (this.testerHost.getConfigurationData().getTestCheckImeiServerConfigurationData().isOneNotificationFor100Dialogs() && (countCheckImeiReq %100 == 0) ) {
                currentRequestDef += "Rcvd: CheckImeiReq: " + countCheckImeiReq + " messages received ("+countCheckImeiResp+" responses sent);";
                this.testerHost.sendNotif(SOURCE_NAME, "Rcvd: CheckImeiReq: " + countCheckImeiReq + " messages received("+countCheckImeiResp+" responses sent)", "", Level.DEBUG);
            }
        }

    }

    private String createCheckImeiReqData(CheckImeiRequest request) {
        StringBuilder sb = new StringBuilder();
        sb.append("dialogId=");
        sb.append(request.getMAPDialog().getLocalDialogId());
        sb.append(", request=\"");
        sb.append(request);
        sb.append("\"");

        sb.append(",\nRemoteAddress=");
        sb.append(request.getMAPDialog().getRemoteAddress());
        sb.append(",\nLocalAddress=");
        sb.append(request.getMAPDialog().getLocalAddress());

        return sb.toString();
    }

    private String createCheckImeiRespData(long dialogId) {
        StringBuilder sb = new StringBuilder();
        sb.append("dialogId=");
        sb.append(dialogId);
        return sb.toString();
    }

    @Override
    public void onCheckImeiResponse(CheckImeiResponse response) {
     // TODO Auto-generated method stub
    }

    // other MAPServiceMobilityListener interface methods

    @Override
    public void onUpdateLocationRequest(UpdateLocationRequest ind) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onUpdateLocationResponse(UpdateLocationResponse ind) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onCancelLocationRequest(CancelLocationRequest request) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onCancelLocationResponse(CancelLocationResponse response) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onSendIdentificationRequest(SendIdentificationRequest request) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onSendIdentificationResponse(SendIdentificationResponse response) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onUpdateGprsLocationRequest(UpdateGprsLocationRequest request) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onUpdateGprsLocationResponse(UpdateGprsLocationResponse response) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPurgeMSRequest(PurgeMSRequest request) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPurgeMSResponse(PurgeMSResponse response) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onSendAuthenticationInfoRequest(SendAuthenticationInfoRequest ind) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onSendAuthenticationInfoResponse(SendAuthenticationInfoResponse ind) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onAuthenticationFailureReportRequest(AuthenticationFailureReportRequest ind) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onAuthenticationFailureReportResponse(AuthenticationFailureReportResponse ind) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onResetRequest(ResetRequest ind) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onForwardCheckSSIndicationRequest(ForwardCheckSSIndicationRequest ind) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onRestoreDataRequest(RestoreDataRequest ind) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onRestoreDataResponse(RestoreDataResponse ind) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onAnyTimeInterrogationRequest(AnyTimeInterrogationRequest request) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onAnyTimeInterrogationResponse(AnyTimeInterrogationResponse response) {
        // TODO Auto-generated method stub

    }

    public void onAnyTimeSubscriptionInterrogationRequest(AnyTimeSubscriptionInterrogationRequest request) {

    }

    public void onAnyTimeSubscriptionInterrogationResponse(AnyTimeSubscriptionInterrogationResponse response) {

    }

    @Override
    public void onProvideSubscriberInfoRequest(ProvideSubscriberInfoRequest request) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProvideSubscriberInfoResponse(ProvideSubscriberInfoResponse response) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onInsertSubscriberDataRequest(InsertSubscriberDataRequest request) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onInsertSubscriberDataResponse(InsertSubscriberDataResponse request) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onDeleteSubscriberDataRequest(DeleteSubscriberDataRequest request) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onDeleteSubscriberDataResponse(DeleteSubscriberDataResponse request) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onActivateTraceModeRequest_Mobility(ActivateTraceModeRequest_Mobility ind) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onActivateTraceModeResponse_Mobility(ActivateTraceModeResponse_Mobility ind) {
        // TODO Auto-generated method stub

    }

}
