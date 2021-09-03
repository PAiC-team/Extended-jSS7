package org.restcomm.protocols.ss7.tools.simulator.tests.psi;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.indicator.NatureOfAddress;
import org.restcomm.protocols.ss7.indicator.RoutingIndicator;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.LocationNumberImpl;
import org.restcomm.protocols.ss7.isup.message.parameter.LocationNumber;
import org.restcomm.protocols.ss7.map.api.MAPDialog;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParameterFactory;
import org.restcomm.protocols.ss7.map.api.MAPProvider;
import org.restcomm.protocols.ss7.map.api.errors.MAPErrorCode;
import org.restcomm.protocols.ss7.map.api.errors.MAPErrorMessage;

import org.restcomm.protocols.ss7.map.api.errors.UnknownSubscriberDiagnostic;
import org.restcomm.protocols.ss7.map.api.primitives.ExternalSignalInfo;
import org.restcomm.protocols.ss7.map.api.primitives.NAEAPreferredCI;
import org.restcomm.protocols.ss7.map.api.primitives.NetworkResource;
import org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan;
import org.restcomm.protocols.ss7.map.api.primitives.AddressNature;
import org.restcomm.protocols.ss7.map.api.primitives.LMSI;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.CellGlobalIdOrServiceAreaIdOrLAI;
import org.restcomm.protocols.ss7.map.api.primitives.CellGlobalIdOrServiceAreaIdFixedLength;
import org.restcomm.protocols.ss7.map.api.primitives.DiameterIdentity;
import org.restcomm.protocols.ss7.map.api.primitives.IMEI;

import org.restcomm.protocols.ss7.map.api.service.callhandling.AllowedServices;
import org.restcomm.protocols.ss7.map.api.service.callhandling.CCBSIndicators;
import org.restcomm.protocols.ss7.map.api.service.callhandling.CUGCheckInfo;
import org.restcomm.protocols.ss7.map.api.service.callhandling.ExtendedRoutingInfo;
import org.restcomm.protocols.ss7.map.api.service.callhandling.IstCommandRequest;
import org.restcomm.protocols.ss7.map.api.service.callhandling.IstCommandResponse;
import org.restcomm.protocols.ss7.map.api.service.callhandling.MAPDialogCallHandling;
import org.restcomm.protocols.ss7.map.api.service.callhandling.MAPServiceCallHandling;
import org.restcomm.protocols.ss7.map.api.service.callhandling.MAPServiceCallHandlingListener;
import org.restcomm.protocols.ss7.map.api.service.callhandling.ProvideRoamingNumberRequest;
import org.restcomm.protocols.ss7.map.api.service.callhandling.ProvideRoamingNumberResponse;
import org.restcomm.protocols.ss7.map.api.service.callhandling.RoutingInfo;
import org.restcomm.protocols.ss7.map.api.service.callhandling.SendRoutingInformationRequest;
import org.restcomm.protocols.ss7.map.api.service.callhandling.SendRoutingInformationResponse;
import org.restcomm.protocols.ss7.map.api.service.callhandling.UnavailabilityCause;
import org.restcomm.protocols.ss7.map.api.service.lsm.AdditionalNumber;
import org.restcomm.protocols.ss7.map.api.service.mobility.MAPDialogMobility;
import org.restcomm.protocols.ss7.map.api.service.mobility.MAPServiceMobility;
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

import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.UpdateLocationRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.UpdateLocationResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.CancelLocationRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.CancelLocationResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.SendIdentificationRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.SendIdentificationResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.UpdateGprsLocationRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.UpdateGprsLocationResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.PurgeMSRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.PurgeMSResponse;

import org.restcomm.protocols.ss7.map.api.service.mobility.oam.ActivateTraceModeRequest_Mobility;
import org.restcomm.protocols.ss7.map.api.service.mobility.oam.ActivateTraceModeResponse_Mobility;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.AnyTimeSubscriptionInterrogationRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.AnyTimeSubscriptionInterrogationResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.DomainType;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.MSNetworkCapability;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.MSRadioAccessCapability;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.PDPContextInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.PSSubscriberStateChoice;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.RequestedInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtBasicServiceCode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.LSAIdentity;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.InsertSubscriberDataRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.InsertSubscriberDataResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.DeleteSubscriberDataRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.DeleteSubscriberDataResponse;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.OfferedCamel4CSIs;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.SupportedCamelPhases;
import org.restcomm.protocols.ss7.map.api.service.sms.IpSmGwGuidance;
import org.restcomm.protocols.ss7.map.api.service.sms.SendRoutingInfoForSMRequest;
import org.restcomm.protocols.ss7.map.api.service.sms.SendRoutingInfoForSMResponse;
import org.restcomm.protocols.ss7.map.api.service.sms.MAPDialogSms;
import org.restcomm.protocols.ss7.map.api.service.sms.MAPServiceSms;
import org.restcomm.protocols.ss7.map.api.service.sms.MAPServiceSmsListener;
import org.restcomm.protocols.ss7.map.api.service.sms.LocationInfoWithLMSI;
import org.restcomm.protocols.ss7.map.api.service.sms.ForwardShortMessageRequest;
import org.restcomm.protocols.ss7.map.api.service.sms.ForwardShortMessageResponse;
import org.restcomm.protocols.ss7.map.api.service.sms.AlertServiceCentreRequest;
import org.restcomm.protocols.ss7.map.api.service.sms.AlertServiceCentreResponse;
import org.restcomm.protocols.ss7.map.api.service.sms.ReportSMDeliveryStatusRequest;
import org.restcomm.protocols.ss7.map.api.service.sms.ReportSMDeliveryStatusResponse;
import org.restcomm.protocols.ss7.map.api.service.sms.MoForwardShortMessageRequest;
import org.restcomm.protocols.ss7.map.api.service.sms.MoForwardShortMessageResponse;
import org.restcomm.protocols.ss7.map.api.service.sms.MtForwardShortMessageRequest;
import org.restcomm.protocols.ss7.map.api.service.sms.MtForwardShortMessageResponse;
import org.restcomm.protocols.ss7.map.api.service.sms.InformServiceCentreRequest;
import org.restcomm.protocols.ss7.map.api.service.sms.ReadyForSMRequest;
import org.restcomm.protocols.ss7.map.api.service.sms.ReadyForSMResponse;
import org.restcomm.protocols.ss7.map.api.service.sms.NoteSubscriberPresentRequest;

import org.restcomm.protocols.ss7.map.api.service.supplementary.SSCode;
import org.restcomm.protocols.ss7.map.errors.MAPErrorMessageAbsentSubscriberImpl;
import org.restcomm.protocols.ss7.map.errors.MAPErrorMessageUnknownSubscriberImpl;
import org.restcomm.protocols.ss7.map.primitives.DiameterIdentityImpl;
import org.restcomm.protocols.ss7.map.primitives.IMSIImpl;
import org.restcomm.protocols.ss7.map.primitives.ISDNAddressStringImpl;
import org.restcomm.protocols.ss7.map.primitives.LMSIImpl;

import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.GeographicalInformationImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.GeodeticInformationImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.MSNetworkCapabilityImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.MSRadioAccessCapabilityImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.RouteingNumberImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.LocationInformationEPSImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.EUtranCgiImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.TAIdImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.LocationNumberMapImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.RAIdentityImpl;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.ProvideSubscriberInfoRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.ProvideSubscriberInfoResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.AnyTimeInterrogationRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.AnyTimeInterrogationResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.SubscriberInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformation;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformationGPRS;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformationEPS;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationNumberMap;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.SubscriberState;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.SubscriberStateChoice;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.MNPInfoRes;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.GeographicalInformation;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.GeodeticInformation;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.TypeOfShape;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.NumberPortabilityStatus;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.RouteingNumber;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.NotReachableReason;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.MSClassmark2;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.PSSubscriberState;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.UserCSGInformation;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.TAId;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.EUtranCgi;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.GPRSMSClass;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.RAIdentity;

import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.LSAIdentityImpl;

import org.restcomm.protocols.ss7.sccp.impl.parameter.ParameterFactoryImpl;
import org.restcomm.protocols.ss7.sccp.parameter.EncodingScheme;
import org.restcomm.protocols.ss7.sccp.parameter.GlobalTitle;
import org.restcomm.protocols.ss7.sccp.parameter.ParameterFactory;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.tcap.asn.ProblemImpl;
import org.restcomm.protocols.ss7.tcap.asn.comp.InvokeProblemType;
import org.restcomm.protocols.ss7.tcap.asn.comp.Problem;
import org.restcomm.protocols.ss7.tools.simulator.Stoppable;
import org.restcomm.protocols.ss7.tools.simulator.common.AddressNatureType;
import org.restcomm.protocols.ss7.tools.simulator.common.TesterBase;
import org.restcomm.protocols.ss7.tools.simulator.level3.MapMan;
import org.restcomm.protocols.ss7.tools.simulator.level3.NumberingPlanMapType;
import org.restcomm.protocols.ss7.tools.simulator.management.TesterHostImpl;
import org.restcomm.protocols.ss7.tools.simulator.tests.sms.SRIReaction;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class TestPsiServerMan extends TesterBase implements TestPsiServerManMBean, Stoppable, MAPServiceMobilityListener, MAPServiceSmsListener, MAPServiceCallHandlingListener {

  private static Logger logger = Logger.getLogger(TestPsiServerMan.class);

  public static String SOURCE_NAME = "TestPsiServerMan";
  private final String name;
  private MapMan mapMan;
  private boolean isStarted = false;
  private int countMapSriForSmReq = 0;
  private int countMapSriForSmResp = 0;
  private int countMapSriReq = 0;
  private int countMapSriResp = 0;
  private int countMapPsiReq = 0;
  private int countMapPsiResp = 0;
  private String currentRequestDef = "";
  private boolean needSendSend = false;
  private boolean needSendClose = false;
  private int countErrSent = 0;
  private MAPProvider mapProvider;
  private MAPServiceMobility mapServiceMobility;
  private MAPServiceSms mapServiceSms;
  private MAPServiceCallHandling mapServiceCallHandling;
  private MAPParameterFactory mapParameterFactory;

  public TestPsiServerMan(String name) {
    super(SOURCE_NAME);
    this.name = name;
    this.isStarted = false;
  }

  public boolean start() {

    this.mapProvider = this.mapMan.getMAPStack().getMAPProvider();
    this.mapServiceSms = this.mapProvider.getMAPServiceSms();
    this.mapServiceCallHandling = this.mapProvider.getMAPServiceCallHandling();
    this.mapServiceMobility = mapProvider.getMAPServiceMobility();
    this.mapParameterFactory = mapProvider.getMAPParameterFactory();

    mapServiceSms.activate();
    mapServiceCallHandling.activate();
    mapServiceMobility.activate();
    mapServiceSms.addMAPServiceListener(this);
    mapServiceCallHandling.addMAPServiceListener(this);
    mapServiceMobility.addMAPServiceListener(this);
    mapProvider.addMAPDialogListener(this);

    this.testerHost.sendNotif(SOURCE_NAME, "PSI Server has been started", "", Level.INFO);
    isStarted = true;
    this.countMapPsiReq = 0;
    this.countMapPsiResp = 0;
    return true;
  }

  public void setTesterHost(TesterHostImpl testerHost) {
    this.testerHost = testerHost;
  }

  public void setMapMan(MapMan val) {
    this.mapMan = val;
  }

  @Override
  public String getState() {
    StringBuilder sb = new StringBuilder();
    sb.append("<html>");
    sb.append(SOURCE_NAME);
    sb.append(": ");
    sb.append("<br>Count: countMapSriForSmReq-");
    sb.append(countMapSriForSmReq);
    sb.append(", countMapSriForSmResp-");
    sb.append(countMapSriForSmResp);
    sb.append("<br>Count: countMapSriReq-");
    sb.append(countMapSriReq);
    sb.append(", countMapSriResp-");
    sb.append(countMapSriResp);
    sb.append("<br>Count: countMapPsiReq-");
    sb.append(countMapPsiReq);
    sb.append(", countMapPsiResp-");
    sb.append(countMapPsiResp);
    sb.append("</html>");
    return sb.toString();
  }

  @Override
  public void execute() {
  }

  @Override
  public void stop() {
    isStarted = false;
    mapProvider.getMAPServiceLsm().deactivate();
    mapProvider.getMAPServiceMobility().deactivate();
    mapProvider.getMAPServiceSms().removeMAPServiceListener(this);
    mapProvider.getMAPServiceMobility().removeMAPServiceListener(this);
    mapProvider.removeMAPDialogListener(this);
    this.testerHost.sendNotif(SOURCE_NAME, "PSI Client has been stopped", "", Level.INFO);
  }

  //**************************//
  //*** SRIforSMS methods ***//
  //************************//
  @Override
  public String performSendRoutingInfoForSMResponse() {
    if (!isStarted) {
      return "The tester is not started";
    }

    return sendRoutingInfoForSMResponse();
  }

  public String sendRoutingInfoForSMResponse() {

    return "sendRoutingInfoForSMResponse called automatically";
  }

  public void onSendRoutingInfoForSMRequest(SendRoutingInfoForSMRequest sendRoutingInforForSMRequest) {

    MAPErrorMessage mapErrorMessage = null;
    logger.debug("\nonSendRoutingInfoForSMRequest");
    if (!isStarted)
      return;

    this.countMapSriForSmReq++;

    MAPProvider mapProvider = this.mapMan.getMAPStack().getMAPProvider();
    MAPDialogSms curDialog = sendRoutingInforForSMRequest.getMAPDialog();
    long invokeId = sendRoutingInforForSMRequest.getInvokeId();

    if (!this.testerHost.getConfigurationData().getTestSmsClientConfigurationData().isOneNotificationFor100Dialogs()) {
      String srIforSMReqData = this.createSRIforSMData(sendRoutingInforForSMRequest);
      this.testerHost.sendNotif(SOURCE_NAME, "Rcvd: sriForSMReq", srIforSMReqData, Level.DEBUG);
    }

    Random rand = new Random();

    // Set Calling SCCP Address (HLR for SRISM response)
    curDialog.setLocalAddress(getHLRSCCPAddress("598991700001"));

    ISDNAddressString msisdn = sendRoutingInforForSMRequest.getMsisdn();
    String msisdnAddress = msisdn.getAddress();
    IMSI imsi = new IMSIImpl("748026871012345");
    if (msisdnAddress.equals("60196229802"))
      imsi = new IMSIImpl("502153207655206");
    if (msisdnAddress.equals("60196229803"))
      imsi = new IMSIImpl("502153100826899");
    if (msisdnAddress.equals("60196229804"))
      imsi = new IMSIImpl("502153147968442");

    // Generate MAP errors for specific MSISDNs
    msisdnAddress = sendRoutingInforForSMRequest.getMsisdn().getAddress();
    if (msisdnAddress.equalsIgnoreCase("99998888")) {
      InvokeProblemType invokeProblemType = InvokeProblemType.UnrecognizedOperation;
      Problem problem = new ProblemImpl();
      problem.setInvokeProblemType(invokeProblemType);
      try {
        curDialog.sendRejectComponent(invokeId, problem);
        curDialog.close(false);
      } catch (MAPException e) {
        e.printStackTrace();
      }
      logger.debug("\nRejectComponent sent");
      this.testerHost.sendNotif(SOURCE_NAME, "Sent: RejectComponent",
          createSRIforSMRespData(curDialog.getLocalDialogId(), null, null), Level.INFO);
      return;
    }
    if (msisdnAddress.equalsIgnoreCase("99990000")) {
      MAPErrorMessage mapErrorMessageAbsentSubscriber = new MAPErrorMessageAbsentSubscriberImpl();
      try {
        curDialog.sendErrorComponent(invokeId, mapErrorMessageAbsentSubscriber);
        curDialog.close(false);
      } catch (MAPException e) {
        e.printStackTrace();
      }
      logger.debug("\nErrorComponent sent");
      this.testerHost.sendNotif(SOURCE_NAME, "Sent: ErrorComponent",
          createSRIforSMRespData(curDialog.getLocalDialogId(), null, null), Level.INFO);
      return;
    }

    String nnnAddress = getVLRSCCPAddress("598991900032").getGlobalTitle().getDigits();
    ISDNAddressString networkNodeNumber = new ISDNAddressStringImpl(AddressNature.international_number, NumberingPlan.ISDN, nnnAddress);

    byte[] lmsiByte = null;
    int lmsiRandom = rand.nextInt(4) + 1;
    switch (lmsiRandom) {
      case 1:
        // char packet_bytes[] = {0x72, 0x02, 0xe9, 0x8c};
        lmsiByte = new byte[]{114, 2, (byte) 233, (byte) 140};
        break;
      case 2:
        // char packet_bytes[] = {0x71, 0xff, 0xac, 0xce};
        lmsiByte = new byte[]{113, (byte) 255, (byte) 172, (byte) 206};
        break;
      case 3:
        // char packet_bytes[] = {0x72, 0x02, 0xeb, 0x37};
        lmsiByte = new byte[]{114, 2, (byte) 235, 55};
        break;
      case 4:
        // char packet_bytes[] = {0x72, 0x02, 0xe7, 0xd5};
        lmsiByte = new byte[]{114, 2, (byte) 231, (byte) 213};
        break;
    }
    LMSI lmsi = new LMSIImpl(lmsiByte);

    MAPExtensionContainer mapExtensionContainer = null;
    AdditionalNumber additionalNumber = null;
    boolean mwdSet = false;
    IpSmGwGuidance ipSmGwGuidance = null;
    LocationInfoWithLMSI locationInfoWithLMSI  = mapProvider.getMAPParameterFactory().createLocationInfoWithLMSI(networkNodeNumber, lmsi, mapExtensionContainer, false, additionalNumber);
    logger.info("LocationInfoWithLMSI for onSendRoutingInfoForSMRequest: NNN="
            +locationInfoWithLMSI.getNetworkNodeNumber().getAddress()+ ", IMSI="+imsi.getData()+ ", LMSI="+lmsi.getData());

    try {

      curDialog.addSendRoutingInfoForSMResponse(invokeId, imsi, locationInfoWithLMSI, mapExtensionContainer, mwdSet,ipSmGwGuidance);
      curDialog.close(false);

      logger.debug("\nSendRoutingForSMResponse sent");
      this.countMapSriForSmResp++;

      this.testerHost.sendNotif(SOURCE_NAME, "Sent: SendRoutingForSMResponse",
              createSRIforSMRespData(curDialog.getLocalDialogId(), imsi, locationInfoWithLMSI), Level.INFO);

    } catch (MAPException e) {
      this.testerHost.sendNotif(SOURCE_NAME, "Exception when invoking addSendRoutingInfoForSMResponse() : " + e.getMessage(), e, Level.ERROR);
    }
  }

  private String createSRIforSMData(SendRoutingInfoForSMRequest sendRoutingInfoForSMRequest) {
    StringBuilder sb = new StringBuilder();
    sb.append("dialogId=");
    sb.append(sendRoutingInfoForSMRequest.getMAPDialog().getLocalDialogId());
    sb.append(",\nsriReq=");
    sb.append(sendRoutingInfoForSMRequest);
    sb.append(",\nRemoteAddress=");
    sb.append(sendRoutingInfoForSMRequest.getMAPDialog().getRemoteAddress());
    sb.append(",\nLocalAddress=");
    sb.append(sendRoutingInfoForSMRequest.getMAPDialog().getLocalAddress());

    return sb.toString();
  }

  private String createSRIforSMRespData(long dialogId, IMSI imsi, LocationInfoWithLMSI locationInfoWithLMSI) {
    StringBuilder sb = new StringBuilder();
    sb.append("dialogId=");
    sb.append(dialogId);
    sb.append(",\n imsi=");
    sb.append(imsi);
    sb.append(",\n locationInfo=");
    sb.append(locationInfoWithLMSI);
    sb.append(",\n");
    return sb.toString();
  }

  //**************************//
  //*** SRI methods ***//
  //************************//
  @Override
  public String performSendRoutingInformationResponse() {
    if (!isStarted) {
      return "The tester is not started";
    }

    return sendRoutingInformationResponse();
  }

  public String sendRoutingInformationResponse() {

    return "sendRoutingInfoResponse called automatically";
  }

  public void onSendRoutingInformationRequest(SendRoutingInformationRequest sendRoutingInformationRequest) {

    MAPErrorMessage mapErrorMessage = null;
    logger.debug("\nonSendRoutingInfoRequest");
    if (!isStarted)
      return;

    this.countMapSriReq++;

    MAPProvider mapProvider = this.mapMan.getMAPStack().getMAPProvider();
    MAPDialogCallHandling curDialog = sendRoutingInformationRequest.getMAPDialog();
    long invokeId = sendRoutingInformationRequest.getInvokeId();

    String sriReqData = this.createSRIData(sendRoutingInformationRequest);
    this.testerHost.sendNotif(SOURCE_NAME, "Rcvd: sriReq", sriReqData, Level.DEBUG);

    ISDNAddressString msisdn = sendRoutingInformationRequest.getMsisdn();
    String msisdnAddress = msisdn.getAddress();
    IMSI imsi = new IMSIImpl("748026871012345");
    if (msisdnAddress.equals("60196229802"))
      imsi = new IMSIImpl("502153207655206");
    if (msisdnAddress.equals("60196229803"))
      imsi = new IMSIImpl("502153100826899");
    if (msisdnAddress.equals("60196229804"))
      imsi = new IMSIImpl("502153147968442");

    // Set Calling SCCP Address (HLR for SRI response)
    curDialog.setLocalAddress(getHLRSCCPAddress("598991700001"));

    // Generate MAP errors for specific MSISDNs
    if (msisdnAddress.equalsIgnoreCase("99998888")) {
      InvokeProblemType invokeProblemType = InvokeProblemType.ResourceLimitation;
      Problem problem = new ProblemImpl();
      problem.setInvokeProblemType(invokeProblemType);
      try {
        curDialog.sendRejectComponent(invokeId, problem);
        curDialog.close(false);
      } catch (MAPException e) {
        e.printStackTrace();
      }
      logger.debug("\nRejectComponent sent");
      this.testerHost.sendNotif(SOURCE_NAME, "Sent: RejectComponent",
          createSRIRespData(curDialog.getLocalDialogId(), null, null), Level.INFO);
      return;
    }
    if (msisdnAddress.equalsIgnoreCase("99990000")) {
      MAPErrorMessage mapErrorMessage1 = new MAPErrorMessageUnknownSubscriberImpl();
      try {
        curDialog.sendErrorComponent(invokeId, mapErrorMessage1);
        curDialog.close(false);
      } catch (MAPException e) {
        e.printStackTrace();
      }
      logger.debug("\nErrorComponent sent");
      this.testerHost.sendNotif(SOURCE_NAME, "Sent: ErrorComponent",
          createSRIRespData(curDialog.getLocalDialogId(), null, null), Level.INFO);
      return;
    }

    String vmscAddressDigits = getMSCSCCPAddress("598991900032").getGlobalTitle().getDigits();
    ISDNAddressString vmscAddress = new ISDNAddressStringImpl(AddressNature.international_number, NumberingPlan.ISDN, vmscAddressDigits);

    logger.info("onSendRoutingInfoRequest: VMSC Address=" +vmscAddress.getAddress()+ ", IMSI="+imsi.getData()+ "invokeID="+invokeId);

    ExtendedRoutingInfo extRoutingInfo = null;
    CUGCheckInfo cugCheckInfo = null;
    boolean cugSubscriptionFlag = false;
    SubscriberInfo subscriberInfo = null;
    ArrayList<SSCode> ssList = null;
    ExtBasicServiceCode basicService = null;
    boolean forwardingInterrogationRequired = false;
    MAPExtensionContainer extensionContainer = null;
    NAEAPreferredCI naeaPreferredCI = null;
    CCBSIndicators ccbsIndicators = null;
    NumberPortabilityStatus nrPortabilityStatus = null;
    Integer istAlertTimer = null;
    SupportedCamelPhases supportedCamelPhases = null;
    OfferedCamel4CSIs offeredCamel4CSIs = null;
    RoutingInfo routingInfo2 = null;
    ArrayList<SSCode> ssList2 = null;
    ExtBasicServiceCode basicService2 = null;
    AllowedServices allowedServices = null;
    UnavailabilityCause unavailabilityCause = null;
    boolean releaseResourcesSupported = false;
    ExternalSignalInfo gsmBearerCapability = null;

    try {

      Random rand = new Random();

      int sriLcsResponseDelay = rand.nextInt(150);
      try {
        Thread.sleep(sriLcsResponseDelay);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      curDialog.addSendRoutingInformationResponse(invokeId, imsi, extRoutingInfo, cugCheckInfo, cugSubscriptionFlag, subscriberInfo, ssList,
          basicService, forwardingInterrogationRequired, vmscAddress, extensionContainer, naeaPreferredCI, ccbsIndicators,
          msisdn, nrPortabilityStatus, istAlertTimer, supportedCamelPhases, offeredCamel4CSIs, routingInfo2, ssList2, basicService2,
          allowedServices, unavailabilityCause, releaseResourcesSupported, gsmBearerCapability);

      curDialog.close(false);

      logger.debug("\nSendRoutingInformationResponse sent");
      this.countMapSriResp++;

      this.testerHost.sendNotif(SOURCE_NAME, "Sent: SendRoutingInformationResponse",
          createSRIRespData(curDialog.getLocalDialogId(), imsi, vmscAddress), Level.INFO);

    } catch (MAPException e) {
      this.testerHost.sendNotif(SOURCE_NAME, "Exception when invoking addSendRoutingInformationResponse() : " + e.getMessage(), e, Level.ERROR);
    }
  }

  private String createSRIData(SendRoutingInformationRequest sendRoutingInformationRequest) {
    StringBuilder sb = new StringBuilder();
    sb.append("dialogId=");
    sb.append(sendRoutingInformationRequest.getMAPDialog().getLocalDialogId());
    sb.append(",\nsriReq=");
    sb.append(sendRoutingInformationRequest);
    sb.append(",\nRemoteAddress=");
    sb.append(sendRoutingInformationRequest.getMAPDialog().getRemoteAddress());
    sb.append(",\nLocalAddress=");
    sb.append(sendRoutingInformationRequest.getMAPDialog().getLocalAddress());

    return sb.toString();
  }

  private String createSRIRespData(long dialogId, IMSI imsi, ISDNAddressString vmscAddress) {
    StringBuilder sb = new StringBuilder();
    sb.append("dialogId=");
    sb.append(dialogId);
    sb.append(",\n imsi=");
    sb.append(imsi);
    sb.append(",\n vmscAddress=");
    sb.append(vmscAddress);
    sb.append(",\n");
    return sb.toString();
  }

  //********************//
  //*** PSI methods ***//
  //******************//

  @Override
  public String performProvideSubscriberInfoResponse() {
    if (!isStarted) {
      return "The tester is not started";
    }

    return sendProvideSubscriberInfoResponse();
  }

  public String sendProvideSubscriberInfoResponse() {

    return "sendProvideSubscriberInfoResponse called automatically";
  }

  public void onProvideSubscriberInfoRequest(ProvideSubscriberInfoRequest provideSubscriberInfoRequest) {

    MAPErrorMessage mapErrorMessage = null;
    logger.debug("\nonProvideSubscriberInfoRequest");
    if (!isStarted)
      return;

    this.countMapPsiReq++;

    MAPProvider mapProvider = this.mapMan.getMAPStack().getMAPProvider();
    MAPDialogMobility curDialog = provideSubscriberInfoRequest.getMAPDialog();
    long invokeId = provideSubscriberInfoRequest.getInvokeId();
    RequestedInfo requestedInfo = provideSubscriberInfoRequest.getRequestedInfo();

    String psiReqData = this.createPSIReqData(provideSubscriberInfoRequest);
    this.testerHost.sendNotif(SOURCE_NAME, "Rcvd: psiReq", psiReqData, Level.DEBUG);

    // Set Calling SCCP Address (VLR for PSI response)
    curDialog.setLocalAddress(getVLRSCCPAddress("598991900032"));

    if (provideSubscriberInfoRequest.getImsi().getData().equals("502153207655206")) {
      try {
        Thread.sleep(15000);
        return;
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    } else if (provideSubscriberInfoRequest.getImsi().getData().equals("502153100826899")) {
      InvokeProblemType invokeProblemType = InvokeProblemType.ResourceLimitation;
      Problem problem = new ProblemImpl();
      problem.setInvokeProblemType(invokeProblemType);
      try {
        curDialog.sendRejectComponent(invokeId, problem);
        curDialog.close(false);
      } catch (MAPException e) {
        e.printStackTrace();
      }
      logger.debug("\nRejectComponent sent");
      this.testerHost.sendNotif(SOURCE_NAME, "Sent: RejectComponent",
          createPSIRespData(curDialog.getLocalDialogId(), null, null), Level.INFO);
      return;
    } else if (provideSubscriberInfoRequest.getImsi().getData().equals("502153147968442")) {
      MAPErrorMessage mapErrorMessage1 = new MAPErrorMessageUnknownSubscriberImpl();
      try {
        curDialog.sendErrorComponent(invokeId, mapErrorMessage1);
        curDialog.close(false);
      } catch (MAPException e) {
        e.printStackTrace();
      }
      logger.debug("\nErrorComponent sent");
      this.testerHost.sendNotif(SOURCE_NAME, "Sent: ErrorComponent",
          createPSIRespData(curDialog.getLocalDialogId(), null, null), Level.INFO);
      return;
    }

    Random rand = new Random();

    try {

      PSIReaction psiReaction = this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().getPsiReaction();

      switch (psiReaction.intValue()) {

        case PSIReaction.VAL_RETURN_SUCCESS:
          String msisdnStr = "59899077937";
          ISDNAddressString msisdn = new ISDNAddressStringImpl(AddressNature.international_number, NumberingPlan.ISDN, msisdnStr);
          SubscriberInfo subscriberInfo;
          IMSI imsi = null;
          LocationInformation locationInformation = null;
          LocationInformationEPS locationInformationEPS;
          LocationInformationGPRS locationInformationGPRS = null;
          Integer ageOfLocationInformation = 0;
          Boolean currentLocationRetrieved = null;
          Boolean saiPresent;
          int mcc, mnc, lac, cellId;
          CellGlobalIdOrServiceAreaIdOrLAI cellGlobalIdOrServiceAreaIdOrLAI;
          CellGlobalIdOrServiceAreaIdFixedLength cgiOrSai = null;
          LocationNumber locationNumber;
          LocationNumberMap locationNumberMap;
          String mscAddress = getVLRSCCPAddress("598991900032").getGlobalTitle().getDigits();
          String vlrAddress = getMSCSCCPAddress("598991900032").getGlobalTitle().getDigits();
          String sgsnAddress = getSGSNSCCPAddress("598992000089").getGlobalTitle().getDigits();
          ISDNAddressString mscNumber, vlrNumber, sgsnNumber;
          GeographicalInformation geographicalInformation;
          GeodeticInformation geodeticInformation;
          byte[] lteCgi;
          EUtranCgi eUtranCgi;
          byte[] trackingAreaId;
          TAId taId;
          LSAIdentity selectedLSAIdentity;
          RAIdentity routeingAreaIdentity;
          RouteingNumber routeingNumber;
          UserCSGInformation userCSGInformation;
          SubscriberStateChoice subscriberStateChoice = null;
          PSSubscriberStateChoice psSubscriberStateChoice = null;
          SubscriberState subscriberState = null;
          PSSubscriberState psSubscriberState = null;
          NotReachableReason notReachableReason = null;
          ArrayList<PDPContextInfo> pdpContextInfoList = null;//new ArrayList<PDPContextInfo>();;
          MNPInfoRes mnpInfoRes = null;
          NumberPortabilityStatus numberPortabilityStatus;
          MSClassmark2 msClassmark2 = null;
          GPRSMSClass gprsMSClass = null;
          IMEI imei = null;
          MAPExtensionContainer extensionContainer = null;

          if (requestedInfo.getLocationInformation()) {
            if (requestedInfo.getCurrentLocation()) {
              currentLocationRetrieved = true;
            }
            Integer sai = rand.nextInt(10) + 1;
            switch(sai) {
              case 1:
              case 2:
              case 3:
              case 4:
              case 5:
                saiPresent = true; // set saiPresent to false
                break;
              case 6:
              case 7:
              case 8:
              case 9:
              case 10:
              default:
                saiPresent = false; // set saiPresent to false
                break;
            }
            Integer stateOption = rand.nextInt(17) + 1;
            switch (stateOption) {
              case 1:
              case 2:
              case 3:
              case 4:
              case 5:
              case 6:
                subscriberStateChoice = SubscriberStateChoice.assumedIdle;
                psSubscriberStateChoice = PSSubscriberStateChoice.psAttachedReachableForPaging;
                break;
              case 7:
              case 8:
              case 9:
              case 10:
              case 11:
              case 12:
                subscriberStateChoice = SubscriberStateChoice.camelBusy;
                psSubscriberStateChoice = PSSubscriberStateChoice.psAttachedReachableForPaging;
                break;
              case 13:
                subscriberStateChoice = SubscriberStateChoice.netDetNotReachable;
                notReachableReason = NotReachableReason.imsiDetached;
                psSubscriberStateChoice = PSSubscriberStateChoice.netDetNotReachable;
                break;
              case 14:
              case 15:
                subscriberStateChoice = SubscriberStateChoice.notProvidedFromVLR;
                psSubscriberStateChoice = PSSubscriberStateChoice.notProvidedFromSGSNorMME;
                break;
              case 16:
                subscriberStateChoice = SubscriberStateChoice.netDetNotReachable;
                notReachableReason = NotReachableReason.restrictedArea;
                psSubscriberStateChoice = PSSubscriberStateChoice.netDetNotReachable;
                break;
              case 17:
                subscriberStateChoice = SubscriberStateChoice.netDetNotReachable;
                notReachableReason = NotReachableReason.msPurged;
                psSubscriberStateChoice = PSSubscriberStateChoice.psAttachedNotReachableForPaging;
                break;
            }
            if (requestedInfo.getSubscriberState()) {
              if (requestedInfo.getRequestedDomain() == null || requestedInfo.getRequestedDomain() == DomainType.csDomain) {
                subscriberState = mapProvider.getMAPParameterFactory().createSubscriberState(subscriberStateChoice, notReachableReason);
              } else {
                psSubscriberState = mapProvider.getMAPParameterFactory().createPSSubscriberState(psSubscriberStateChoice, notReachableReason, pdpContextInfoList);
              }
            }
            TypeOfShape geographicalTypeOfShape = TypeOfShape.EllipsoidPointWithUncertaintyCircle;
            Double geographicalLatitude;
            Double geographicalLongitude;
            Double geographicalUncertainty;
            //GeographicalInformation geographicalInformation = mapProvider.getMAPParameterFactory().createGeographicalInformation(geographicalLatitude, geographicalLongitude, geographicalUncertainty);
            TypeOfShape geodeticTypeOfShape = TypeOfShape.EllipsoidPointWithUncertaintyCircle;
            Double geodeticLatitude;
            Double geodeticLongitude;
            Double geodeticlUncertainty;
            int geodeticConfidence = 1;
            int screeningAndPresentationIndicators = 3;
            //geodeticInformation = new GeodeticInformationImpl(screeningAndPresentationIndicators, geodeticTypeOfShape, geodeticLatitude, geodeticLongitude, geodeticlUncertainty, geodeticConfidence);
            Integer randLoc = rand.nextInt(10) + 1;
            switch(randLoc) {
              case 1:
                mcc = 748;
                mnc = 1;
                lac = 101;
                cellId = 10263;
                geographicalLatitude = -34.909744;
                geographicalLongitude = -56.146317;
                geographicalUncertainty = 1.0;
                geographicalInformation = new GeographicalInformationImpl(geographicalTypeOfShape, geographicalLatitude, geographicalLongitude, geographicalUncertainty);
                geodeticInformation = null;
                lteCgi = hexStringToByteArray("47f8100007ea02"); // ECGI = 748-1-518658; TBCD encoded: 47f8100007ea02
                trackingAreaId = hexStringToByteArray("47f810006d"); // TAI = 748-1-109; TBCD encoded: 47f810006d
                break;
              case 2:
                mcc = 748;
                mnc = 1;
                lac = 119;
                cellId = 15336;
                geographicalInformation = null;
                geodeticLatitude = -34.910349;
                geodeticLongitude = -56.149832;
                geodeticlUncertainty = 2.0;
                geodeticConfidence = 1;
                screeningAndPresentationIndicators = 3;
                geodeticInformation = new GeodeticInformationImpl(screeningAndPresentationIndicators, geodeticTypeOfShape, geodeticLatitude, geodeticLongitude, geodeticlUncertainty, geodeticConfidence);
                lteCgi = hexStringToByteArray("47f81000095f02"); // ECGI = 748-1-614146; TBCD encoded: 47f81000095f02
                trackingAreaId = hexStringToByteArray("47f810006d"); // TAI = 748-1-109; TBCD encoded: 47f810006d
                break;
              case 3:
                mcc = 748;
                mnc = 1;
                lac = 118;
                cellId = 292;
                geographicalInformation = null;
                geodeticInformation = null;
                lteCgi = hexStringToByteArray("47f870004b2c04"); // ECGI = 748-7-4926468; TBCD encoded: 47f870004b2c04
                trackingAreaId = hexStringToByteArray("47f8701b58"); // TAI = 748-7-7000; TBCD encoded: 47f8701b58
                break;
              case 4:
                mcc = 748;
                mnc = 1;
                lac = 109;
                cellId = 10175;
                geographicalInformation = null;
                geodeticInformation = null;
                lteCgi = hexStringToByteArray("47f8100007f001"); // // ECGI = 748-1-520193; TBCD encoded: 47f8100007f001
                trackingAreaId = hexStringToByteArray("47f810006d"); // TAI = 748-1-109; TBCD encoded: 47f810006d
                break;
              case 5:
                mcc = 748;
                mnc = 1;
                lac = 11;
                cellId = 4812;
                geographicalInformation = null;
                geodeticInformation = null;
                lteCgi = hexStringToByteArray("47f870004c2e08"); // ECGI = 748-7-4992520; TBCD encoded: 47f870004c2e08
                trackingAreaId = hexStringToByteArray("47f8701b58"); // TAI = 748-7-7000; TBCD encoded: 47f8701b58
                break;
              case 6:
                mcc = 748;
                mnc = 7;
                lac = 8820;
                cellId = 9748;
                geographicalInformation = null;
                geodeticInformation = null;
                lteCgi = hexStringToByteArray("47f87000477304"); // ECGI = 748-7-4682500; TBCD encoded: 47f87000477304
                trackingAreaId = hexStringToByteArray("47f8701b6c"); // TAI = 748-7-7020; TBCD encoded: 47f8701b6c
                break;
              case 7:
                mcc = 748;
                mnc = 7;
                lac = 8552;
                cellId = 8239;
                geographicalInformation = null;
                geodeticInformation = null;
                lteCgi = hexStringToByteArray("47f870004b3605"); // ECGI = 748-7-4929029; TBCD encoded: 47f870004b3605
                trackingAreaId = hexStringToByteArray("47f8701b58"); // TAI = 748-7-7000; TBCD encoded: 47f8701b58
                break;
              case 8:
                mcc = 748;
                mnc = 10;
                lac = 9501;
                cellId = 35100;
                geographicalInformation = null;
                geodeticLatitude = -34.905624;
                geodeticLongitude = -55.042191;
                geodeticlUncertainty = 4.0;
                geodeticConfidence = 10;
                screeningAndPresentationIndicators = 3;
                geodeticInformation = new GeodeticInformationImpl(screeningAndPresentationIndicators, geodeticTypeOfShape, geodeticLatitude, geodeticLongitude, geodeticlUncertainty, geodeticConfidence);
                lteCgi = hexStringToByteArray("47f81000004802"); // ECGI = 748-1-18434; TBCD encoded: 47f81000004802
                trackingAreaId = hexStringToByteArray("47f8100002"); // TAI = 748-1-2; TBCD encoded: 47f8100002
                break;
              case 9:
                mcc = 748;
                mnc = 7;
                lac = 8313;
                cellId = 9281;
                geographicalInformation = null;
                geodeticLatitude = -34.891032;
                geodeticLongitude = -56.0008102;
                geodeticlUncertainty = 4.0;
                geodeticConfidence = 2;
                screeningAndPresentationIndicators = 1;
                geodeticInformation = new GeodeticInformationImpl(screeningAndPresentationIndicators, geodeticTypeOfShape, geodeticLatitude, geodeticLongitude, geodeticlUncertainty, geodeticConfidence);
                lteCgi = hexStringToByteArray("47f81000089700"); // ECGI = 748-1-562944; TBCD encoded: 47f81000089700
                trackingAreaId = hexStringToByteArray("47f8100067"); // TAI = 748-1-103; TBCD encoded: 47f8100067
                break;
              case 10:
                mcc = 748;
                mnc = 7;
                lac = 8820;
                cellId = 8051;
                geographicalInformation = null;
                geodeticInformation = null;
                lteCgi = hexStringToByteArray("47f8010000d502"); // ECGI = 748-10-54530; TBCD encoded: 47f8010000d502
                trackingAreaId = hexStringToByteArray("47f8017238"); // TAI = 748-10-29240; TBCD encoded: 47f8017238
                break;
              default:
                mcc = 748;
                mnc = 10;
                lac = 9501;
                cellId = 35100;
                geographicalInformation = null;
                geodeticLatitude = -34.905624;
                geodeticLongitude = -55.042190;
                geodeticlUncertainty = 4.0;
                geodeticConfidence = 10;
                screeningAndPresentationIndicators = 3;
                geodeticInformation = new GeodeticInformationImpl(screeningAndPresentationIndicators, geodeticTypeOfShape, geodeticLatitude, geodeticLongitude, geodeticlUncertainty, geodeticConfidence);
                lteCgi = hexStringToByteArray("47f81000004802"); // ECGI = 748-1-18434; TBCD encoded: 47f81000004802
                trackingAreaId = hexStringToByteArray("47f8100002"); // TAI = 748-1-2; TBCD encoded: 47f8100002
                break;
            }
            if (requestedInfo.getRequestedDomain() == null || requestedInfo.getRequestedDomain() == DomainType.csDomain) {
              mscNumber = new ISDNAddressStringImpl(AddressNature.international_number,NumberingPlan.ISDN, mscAddress);
              vlrNumber = mapProvider.getMAPParameterFactory().createISDNAddressString(AddressNature.international_number,
                  NumberingPlan.ISDN, vlrAddress);
              int natureOfAddressIndicator = 4;
              String locationNumberAddressDigits= "819203961904";
              int numberingPlanIndicator = 1;
              int internalNetworkNumberIndicator = 1;
              int addressRepresentationRestrictedIndicator = 1;
              int screeningIndicator = 3;
              locationNumber = new LocationNumberImpl(natureOfAddressIndicator, locationNumberAddressDigits, numberingPlanIndicator,
                  internalNetworkNumberIndicator, addressRepresentationRestrictedIndicator, screeningIndicator);
              locationNumberMap = null;
              try {
                locationNumberMap = new LocationNumberMapImpl(locationNumber);
              } catch (MAPException e) {
                e.printStackTrace();
              }
              eUtranCgi = new EUtranCgiImpl(lteCgi);
              taId = new TAIdImpl(trackingAreaId);
              //byte[] mmeNom = {77, 77, 69, 55, 52, 56, 48, 48, 48, 49};
              //DiameterIdentity mmeName = new DiameterIdentityImpl(mmeNom);
              String mmneNameStr = "mmec03.mmeer3000.mme.epc.mnc002.mcc748.3gppnetwork.org";
              byte[] mme = mmneNameStr.getBytes();
              DiameterIdentity mmeName = new DiameterIdentityImpl(mme);
              byte[] lsaId = {49, 51, 50};
              LSAIdentity selectedLSAId = new LSAIdentityImpl(lsaId);
              userCSGInformation = null;

              try {
                cgiOrSai = mapProvider.getMAPParameterFactory().createCellGlobalIdOrServiceAreaIdFixedLength(mcc, mnc, lac, cellId);
              } catch (MAPException ex) {
                ex.printStackTrace();
              }
              cellGlobalIdOrServiceAreaIdOrLAI = mapProvider.getMAPParameterFactory().createCellGlobalIdOrServiceAreaIdOrLAI(cgiOrSai);
              if (subscriberStateChoice == SubscriberStateChoice.assumedIdle) {
                ageOfLocationInformation = 0;
                currentLocationRetrieved = true;
              } else if (subscriberStateChoice == SubscriberStateChoice.camelBusy) {
                ageOfLocationInformation = 0;
                currentLocationRetrieved = true;
              } else if (subscriberStateChoice == SubscriberStateChoice.notProvidedFromVLR) {
                ageOfLocationInformation = 3;
                currentLocationRetrieved = false;
              } else if (subscriberStateChoice == SubscriberStateChoice.netDetNotReachable) {
                if (notReachableReason == NotReachableReason.imsiDetached) {
                  ageOfLocationInformation = 1575;
                  currentLocationRetrieved = false;
                  geographicalInformation = null;
                  geodeticInformation = null;
                  mscNumber = null;
                  vlrNumber = mapProvider.getMAPParameterFactory().createISDNAddressString(AddressNature.international_number,
                      NumberingPlan.ISDN, vlrAddress);
                } else if (notReachableReason == NotReachableReason.restrictedArea) {
                  ageOfLocationInformation = 300;
                  currentLocationRetrieved = false;
                  geographicalInformation = null;
                  geodeticInformation = null;
                  mscNumber = null;
                  vlrNumber = mapProvider.getMAPParameterFactory().createISDNAddressString(AddressNature.international_number,
                      NumberingPlan.ISDN, vlrAddress);
                } else if (notReachableReason == NotReachableReason.msPurged) {
                  ageOfLocationInformation = 221;
                  currentLocationRetrieved = false;
                  geographicalInformation = null;
                  geodeticInformation = null;
                  mscNumber = null;
                  vlrNumber = mapProvider.getMAPParameterFactory().createISDNAddressString(AddressNature.international_number,
                      NumberingPlan.ISDN, vlrAddress);
                } else {
                  ageOfLocationInformation = 1879;
                  currentLocationRetrieved = false;
                  geographicalInformation = null;
                  geodeticInformation = null;
                  mscNumber = null;
                  vlrNumber = mapProvider.getMAPParameterFactory().createISDNAddressString(AddressNature.international_number,
                      NumberingPlan.ISDN, vlrAddress);
                }
              }
              boolean epsLocationInfoSupported = requestedInfo.getLocationInformationEPSSupported();
              if (!epsLocationInfoSupported) {
                locationInformationEPS = null; // set locationInformationEPS to null
                locationInformation = mapProvider.getMAPParameterFactory().createLocationInformation(ageOfLocationInformation, geographicalInformation,
                    vlrNumber, locationNumberMap, cellGlobalIdOrServiceAreaIdOrLAI, extensionContainer, selectedLSAId, mscNumber, geodeticInformation,
                    currentLocationRetrieved, saiPresent, locationInformationEPS, userCSGInformation);
              } else {
                if (this.countMapPsiReq % 4 == 0) {
                  // Let's set that one out of 4 PSI requests (25%) with epsLocationInfoSupported = true
                  // doesn't retrieve EPS location information (as if the subscriber is under GERAN or UTRAN coverage only)
                  locationInformationEPS = null;
                  locationInformation = mapProvider.getMAPParameterFactory().createLocationInformation(ageOfLocationInformation, geographicalInformation,
                      vlrNumber, locationNumberMap, cellGlobalIdOrServiceAreaIdOrLAI, extensionContainer, selectedLSAId, mscNumber, geodeticInformation,
                      currentLocationRetrieved, saiPresent, locationInformationEPS, userCSGInformation);
                } else {
                  // Rest of PSI requests (75%) does retrieve EPS location information (subscriber under E-UTRAN coverage)
                  // thus, locationInformationEPS is NOT null, but rest of CS location information is null (except for Location Number)
                  locationInformationEPS = new LocationInformationEPSImpl(eUtranCgi, taId, extensionContainer, geographicalInformation,
                      geodeticInformation, currentLocationRetrieved, ageOfLocationInformation, mmeName);
                  ageOfLocationInformation = null;
                  geographicalInformation = null;
                  vlrNumber = null;
                  cellGlobalIdOrServiceAreaIdOrLAI = null;
                  selectedLSAId = null;
                  mscNumber = null;
                  geodeticInformation = null;
                  locationInformation = mapProvider.getMAPParameterFactory().createLocationInformation(ageOfLocationInformation, geographicalInformation,
                      vlrNumber, locationNumberMap, cellGlobalIdOrServiceAreaIdOrLAI, extensionContainer, selectedLSAId, mscNumber, geodeticInformation,
                      currentLocationRetrieved, saiPresent, locationInformationEPS, userCSGInformation);
                }
              }

            } else {
              switch(randLoc) {
                case 1:
                  mcc = 748;
                  mnc = 1;
                  lac = 101;
                  cellId = 10263;
                  geographicalLatitude = -34.909744;
                  geographicalLongitude = -56.146317;
                  geographicalUncertainty = 1.0;
                  geographicalInformation = new GeographicalInformationImpl(geographicalTypeOfShape, geographicalLatitude, geographicalLongitude, geographicalUncertainty);
                  geodeticInformation = null;
                  break;
                case 2:
                  mcc = 748;
                  mnc = 1;
                  lac = 119;
                  cellId = 15336;
                  geographicalInformation = null;
                  geodeticLatitude = -34.910349;
                  geodeticLongitude = -56.149832;
                  geodeticlUncertainty = 2.0;
                  geodeticConfidence = 1;
                  screeningAndPresentationIndicators = 3;
                  geodeticInformation = new GeodeticInformationImpl(screeningAndPresentationIndicators, geodeticTypeOfShape, geodeticLatitude, geodeticLongitude, geodeticlUncertainty, geodeticConfidence);
                  break;
                case 3:
                  mcc = 748;
                  mnc = 1;
                  lac = 118;
                  cellId = 292;
                  geographicalInformation = null;
                  geodeticInformation = null;
                  break;
                case 4:
                  mcc = 748;
                  mnc = 1;
                  lac = 109;
                  cellId = 10175;
                  geographicalInformation = null;
                  geodeticInformation = null;
                  break;
                case 5:
                  mcc = 748;
                  mnc = 1;
                  lac = 11;
                  cellId = 4812;
                  geographicalInformation = null;
                  geodeticInformation = null;
                  break;
                case 6:
                  mcc = 748;
                  mnc = 7;
                  lac = 8820;
                  cellId = 9748;
                  geographicalInformation = null;
                  geodeticInformation = null;
                  break;
                case 7:
                  mcc = 748;
                  mnc = 7;
                  lac = 8552;
                  cellId = 8239;
                  geographicalInformation = null;
                  geodeticInformation = null;
                  break;
                case 8:
                  mcc = 748;
                  mnc = 10;
                  lac = 9501;
                  cellId = 35100;
                  geographicalInformation = null;
                  geodeticLatitude = -34.905624;
                  geodeticLongitude = -55.042191;
                  geodeticlUncertainty = 4.0;
                  geodeticConfidence = 10;
                  screeningAndPresentationIndicators = 3;
                  geodeticInformation = new GeodeticInformationImpl(screeningAndPresentationIndicators, geodeticTypeOfShape, geodeticLatitude, geodeticLongitude, geodeticlUncertainty, geodeticConfidence);
                  break;
                case 9:
                  mcc = 748;
                  mnc = 7;
                  lac = 8313;
                  cellId = 9281;
                  geographicalInformation = null;
                  geodeticLatitude = -34.891032;
                  geodeticLongitude = -56.0008102;
                  geodeticlUncertainty = 4.0;
                  geodeticConfidence = 2;
                  screeningAndPresentationIndicators = 1;
                  geodeticInformation = new GeodeticInformationImpl(screeningAndPresentationIndicators, geodeticTypeOfShape, geodeticLatitude, geodeticLongitude, geodeticlUncertainty, geodeticConfidence);
                  break;
                case 10:
                  mcc = 748;
                  mnc = 7;
                  lac = 8820;
                  cellId = 8051;
                  geographicalInformation = null;
                  geodeticInformation = null;
                  break;
                default:
                  mcc = 748;
                  mnc = 10;
                  lac = 9501;
                  cellId = 35100;
                  geographicalInformation = null;
                  geodeticLatitude = -34.905624;
                  geodeticLongitude = -55.042190;
                  geodeticlUncertainty = 4.0;
                  geodeticConfidence = 10;
                  screeningAndPresentationIndicators = 3;
                  geodeticInformation = new GeodeticInformationImpl(screeningAndPresentationIndicators, geodeticTypeOfShape, geodeticLatitude, geodeticLongitude, geodeticlUncertainty, geodeticConfidence);
                  break;
              }
              byte[] raId = hexStringToByteArray("47f810006517");
              if (this.countMapPsiReq % 2 == 0)
                saiPresent = true; // set saiPresent to true if this ATI request is even since test started
              else
                saiPresent = false; // set saiPresent to false if this ATI request is odd since test started
              try {
                cgiOrSai = mapProvider.getMAPParameterFactory().createCellGlobalIdOrServiceAreaIdFixedLength(mcc, mnc, lac, cellId);
              } catch (MAPException ex) {
                ex.printStackTrace();
              }
              cellGlobalIdOrServiceAreaIdOrLAI = mapProvider.getMAPParameterFactory().createCellGlobalIdOrServiceAreaIdOrLAI(cgiOrSai);
              routeingAreaIdentity = new RAIdentityImpl(raId);
              sgsnNumber = mapProvider.getMAPParameterFactory().createISDNAddressString(AddressNature.international_number,
                  NumberingPlan.ISDN, sgsnAddress);
              byte[] lsaId = {49, 51, 49};
              selectedLSAIdentity = new LSAIdentityImpl(lsaId);
              if (psSubscriberStateChoice == PSSubscriberStateChoice.psAttachedReachableForPaging) {
                ageOfLocationInformation = 0;
                currentLocationRetrieved = true;
              } else if (psSubscriberStateChoice == PSSubscriberStateChoice.psAttachedNotReachableForPaging) {
                ageOfLocationInformation = 1704;
                currentLocationRetrieved = false;
                geographicalInformation = null;
                geodeticInformation = null;
              } else if (psSubscriberStateChoice == PSSubscriberStateChoice.notProvidedFromSGSNorMME) {
                ageOfLocationInformation = 2;
                currentLocationRetrieved = false;
              } else if (psSubscriberStateChoice == PSSubscriberStateChoice.netDetNotReachable) {
                ageOfLocationInformation = 879;
                currentLocationRetrieved = false;
                geographicalInformation = null;
                geodeticInformation = null;
              } else {
                ageOfLocationInformation = 1500;
                currentLocationRetrieved = false;
                geographicalInformation = null;
                geodeticInformation = null;
              }
              locationInformationGPRS = mapProvider.getMAPParameterFactory().createLocationInformationGPRS(cellGlobalIdOrServiceAreaIdOrLAI,
                  routeingAreaIdentity, geographicalInformation, sgsnNumber, selectedLSAIdentity, extensionContainer, saiPresent, geodeticInformation,
                  currentLocationRetrieved, ageOfLocationInformation);
            }
          }

          if (requestedInfo.getMnpRequestedInfo()) {
            if (subscriberStateChoice != SubscriberStateChoice.netDetNotReachable &&
                psSubscriberStateChoice != PSSubscriberStateChoice.psAttachedNotReachableForPaging &&
                psSubscriberStateChoice != PSSubscriberStateChoice.netDetNotReachable) {
              //RouteingNumber routeingNumber = mapProvider.getMAPParameterFactory().createRouteingNumber("5555555888");
              routeingNumber = new RouteingNumberImpl("598123");
              IMSI mnpImsi = new IMSIImpl("748026871012345");
              String mnpMsisdnStr = "59899077937";
              ISDNAddressString mnpMsisdn = new ISDNAddressStringImpl(AddressNature.international_number,
                  NumberingPlan.ISDN, msisdnStr);
              numberPortabilityStatus = NumberPortabilityStatus.ownNumberNotPortedOut;
              mnpInfoRes = mapProvider.getMAPParameterFactory().createMNPInfoRes(routeingNumber, mnpImsi, mnpMsisdn, numberPortabilityStatus, extensionContainer);
            } else {
              mnpInfoRes = null;
            }
          }

          if (requestedInfo.getImei()) {
            if (subscriberStateChoice != SubscriberStateChoice.netDetNotReachable &&
                psSubscriberStateChoice != PSSubscriberStateChoice.psAttachedNotReachableForPaging &&
                psSubscriberStateChoice != PSSubscriberStateChoice.netDetNotReachable) {
              if (requestedInfo.getRequestedDomain() == null || requestedInfo.getRequestedDomain() == DomainType.csDomain) {
                imei = mapProvider.getMAPParameterFactory().createIMEI("011714004661050");
              } else {
                imei = mapProvider.getMAPParameterFactory().createIMEI("011714004661051");
              }
            } else {
              imei = null;
            }
          }

          if (requestedInfo.getMsClassmark()) {
            if (requestedInfo.getRequestedDomain() == null || requestedInfo.getRequestedDomain() == DomainType.csDomain) {
              if (subscriberStateChoice != SubscriberStateChoice.netDetNotReachable) {
                byte[] classmark = {57, 58, 82};
                msClassmark2 = mapProvider.getMAPParameterFactory().createMSClassmark2(classmark);
              } else {
                msClassmark2 = null;
              }
            } else {
              if (psSubscriberStateChoice != PSSubscriberStateChoice.psAttachedNotReachableForPaging &&
                  psSubscriberStateChoice != PSSubscriberStateChoice.netDetNotReachable) {
                byte[] mSNetworkCapabilityB = hexStringToByteArray("3130303032303331");
                MSNetworkCapability mSNetworkCapability = new MSNetworkCapabilityImpl(mSNetworkCapabilityB);
                byte[] mSRadioAccessCapabilityB = hexStringToByteArray("31303030323033313730383134");
                MSRadioAccessCapability mSRadioAccessCapability = new MSRadioAccessCapabilityImpl(mSRadioAccessCapabilityB);
                gprsMSClass = mapProvider.getMAPParameterFactory().createGPRSMSClass(mSNetworkCapability, mSRadioAccessCapability);
              } else {
                gprsMSClass = null;
              }
            }
          }

          try {

            subscriberInfo = mapProvider.getMAPParameterFactory().createSubscriberInfo(locationInformation, subscriberState, extensionContainer,
                locationInformationGPRS, psSubscriberState, imei, msClassmark2, gprsMSClass, mnpInfoRes);

            delayResponse(300);

            curDialog.addProvideSubscriberInfoResponse(invokeId, subscriberInfo, extensionContainer);
            curDialog.close(false);

            logger.debug("\nProvideSubscriberInfoResponse sent");
            this.countMapPsiResp++;

            this.testerHost.sendNotif(SOURCE_NAME, "Sent: ProvideSubscriberInfoResponse",
                createPSIRespData(curDialog.getLocalDialogId(), subscriberInfo, extensionContainer), Level.INFO);

          } catch (MAPException e) {
            this.testerHost.sendNotif(SOURCE_NAME, "Exception when invoking addProvideSubscriberInfoResponse() : " + e.getMessage(), e, Level.ERROR);
          }
          break;

        case PSIReaction.VAL_UNEXPECTED_DATA_VALUE:
          mapErrorMessage = mapProvider.getMAPErrorMessageFactory().createMAPErrorMessageExtensionContainer(
              (long) MAPErrorCode.unexpectedDataValue, null);
          curDialog.sendErrorComponent(invokeId, mapErrorMessage);
          this.countErrSent++;
          psiReqData = this.createErrorData(curDialog.getLocalDialogId(), (int) invokeId, mapErrorMessage);
          this.testerHost.sendNotif(SOURCE_NAME, "Sent: unexpectedDataVal", psiReqData, Level.DEBUG);
          break;

        case PSIReaction.VAL_ERROR_SYSTEM_FAILURE:
          mapErrorMessage = mapProvider.getMAPErrorMessageFactory().createMAPErrorMessageSystemFailure(
              (long) curDialog.getApplicationContext().getApplicationContextVersion().getVersion(), NetworkResource.hlr, null, null);
          curDialog.sendErrorComponent(invokeId, mapErrorMessage);
          this.countErrSent++;
          psiReqData = this.createErrorData(curDialog.getLocalDialogId(), (int) invokeId, mapErrorMessage);
          this.testerHost.sendNotif(SOURCE_NAME, "Sent: errSysFail", psiReqData, Level.DEBUG);
          break;

        case PSIReaction.VAL_DATA_MISSING:
          mapErrorMessage = mapProvider.getMAPErrorMessageFactory().createMAPErrorMessageExtensionContainer(
              (long) MAPErrorCode.dataMissing, null);
          curDialog.sendErrorComponent(invokeId, mapErrorMessage);
          this.countErrSent++;
          psiReqData = this.createErrorData(curDialog.getLocalDialogId(), (int) invokeId, mapErrorMessage);
          this.testerHost.sendNotif(SOURCE_NAME, "Sent: errDataMissing", psiReqData, Level.DEBUG);
          break;

        case PSIReaction.VAL_ERROR_UNKNOWN_SUBSCRIBER:
          mapErrorMessage = mapProvider.getMAPErrorMessageFactory().createMAPErrorMessageUnknownSubscriber(null,
              UnknownSubscriberDiagnostic.imsiUnknown);
          curDialog.sendErrorComponent(invokeId, mapErrorMessage);
          this.countErrSent++;
          psiReqData = this.createErrorData(curDialog.getLocalDialogId(), (int) invokeId, mapErrorMessage);
          this.testerHost.sendNotif(SOURCE_NAME, "Sent: errUnknSubs", psiReqData, Level.DEBUG);
          break;
      }

    } catch (Exception e) {
      this.testerHost.sendNotif(SOURCE_NAME, "Exception when invoking addProvideSubscriberInfoResponse() : " + e.getMessage(), e, Level.ERROR);
    }

  }

  private String createPSIReqData(ProvideSubscriberInfoRequest provideSubscriberInfoRequest) {
    StringBuilder sb = new StringBuilder();
    sb.append("dialogId=");
    sb.append(provideSubscriberInfoRequest.getMAPDialog().getLocalDialogId());
    sb.append(",\nPSI Operation Code=");
    sb.append(provideSubscriberInfoRequest.getOperationCode());
    sb.append(",\nRemoteAddress=");
    sb.append(provideSubscriberInfoRequest.getMAPDialog().getRemoteAddress());
    sb.append(",\nLocalAddress=");
    sb.append(provideSubscriberInfoRequest.getMAPDialog().getLocalAddress());
    sb.append(",\nPSI Request=");
    sb.append(provideSubscriberInfoRequest);

    return sb.toString();
  }

  private String createPSIRespData(long dialogId, SubscriberInfo subscriberInfo, MAPExtensionContainer mapExtensionContainer) {
    StringBuilder sb = new StringBuilder();
    sb.append("dialogId=");
    sb.append(dialogId);
    if (subscriberInfo.getLocationInformation() != null) {
      if (subscriberInfo.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI() != null) {
        if (subscriberInfo.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength() != null) {
          try {
            sb.append(",\nMCC=");
            sb.append(subscriberInfo.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getMCC());
            sb.append(",\nMNC=");
            sb.append(subscriberInfo.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getMNC());
            sb.append(",\nLAC=");
            sb.append(subscriberInfo.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getLac());
            sb.append(",\nCI=");
            sb.append(subscriberInfo.getLocationInformation().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getCellIdOrServiceAreaCode());
          } catch (MAPException e) {
            e.printStackTrace();
          }
        }
        if (subscriberInfo.getLocationInformation().getLocationNumber() != null) {
          sb.append(",\nLocation number=");
          try {
            sb.append(",\nLocation number address digits=");
            sb.append(subscriberInfo.getLocationInformation().getLocationNumber().getLocationNumber().getAddress());
            sb.append(",\nLocation number NAI=");
            sb.append(subscriberInfo.getLocationInformation().getLocationNumber().getLocationNumber().getNatureOfAddressIndicator());
            sb.append(",\nLocation number code=");
            sb.append(subscriberInfo.getLocationInformation().getLocationNumber().getLocationNumber().getCode());
            sb.append(",\nLocation number NPI=");
            sb.append(subscriberInfo.getLocationInformation().getLocationNumber().getLocationNumber().getNumberingPlanIndicator());
            sb.append(",\nLocation number AddressRepresentationRestrictedIndicator=");
            sb.append(subscriberInfo.getLocationInformation().getLocationNumber().getLocationNumber().getAddressRepresentationRestrictedIndicator());
            sb.append(",\nLocation number InternalNetworkNumberIndicator=");
            sb.append(subscriberInfo.getLocationInformation().getLocationNumber().getLocationNumber().getInternalNetworkNumberIndicator());
            sb.append(",\nLocation number ScreeningIndicator=");
            sb.append(subscriberInfo.getLocationInformation().getLocationNumber().getLocationNumber().getScreeningIndicator());
          } catch (MAPException e) {
            e.printStackTrace();
          }
        }
      }
      if (subscriberInfo.getLocationInformation().getMscNumber() != null) {
        sb.append(",\nMSC number=");
        sb.append(subscriberInfo.getLocationInformation().getMscNumber().getAddress());
      }
      if (subscriberInfo.getLocationInformation().getVlrNumber() != null) {
        sb.append(",\nVLR number=");
        sb.append(subscriberInfo.getLocationInformation().getVlrNumber().getAddress());
      }
      sb.append(",\nAOL=");
      sb.append(subscriberInfo.getLocationInformation().getAgeOfLocationInformation());
      sb.append(",\nSAI present=");
      sb.append(subscriberInfo.getLocationInformation().getSaiPresent());
      if (subscriberInfo.getLocationInformation().getGeographicalInformation() != null) {
        sb.append(",\nGeographicalLatitude=");
        sb.append(subscriberInfo.getLocationInformation().getGeographicalInformation().getLatitude());
        sb.append(",\nGeographical Longitude=");
        sb.append(subscriberInfo.getLocationInformation().getGeographicalInformation().getLongitude());
        sb.append(",\nGeographicalUncertainty=");
        sb.append(subscriberInfo.getLocationInformation().getGeographicalInformation().getUncertainty());
        sb.append(",\nGeographical Type of Shape=");
        sb.append(subscriberInfo.getLocationInformation().getGeographicalInformation().getTypeOfShape());
      }
      if (subscriberInfo.getLocationInformation().getGeographicalInformation() != null) {
        sb.append(",\nGeodetic Latitude=");
        sb.append(subscriberInfo.getLocationInformation().getGeodeticInformation().getLatitude());
        sb.append(",\nGeodetic Longitude=");
        sb.append(subscriberInfo.getLocationInformation().getGeodeticInformation().getLongitude());
        sb.append(",\nGeodetic Uncertainty=");
        sb.append(subscriberInfo.getLocationInformation().getGeodeticInformation().getUncertainty());
        sb.append(",\nGeodetic Confidence=");
        sb.append(subscriberInfo.getLocationInformation().getGeodeticInformation().getConfidence());
        sb.append(",\nGeodetic Type of Shape=");
        sb.append(subscriberInfo.getLocationInformation().getGeodeticInformation().getTypeOfShape());
        sb.append(",\nGeodetic Screening and Presentation Indicators=");
        sb.append(subscriberInfo.getLocationInformation().getGeodeticInformation().getScreeningAndPresentationIndicators());
      }
      sb.append(",\nCurrent Location Retrieved=");
      sb.append(subscriberInfo.getLocationInformation().getCurrentLocationRetrieved());
      if (subscriberInfo.getLocationInformation().getLocationInformationEPS() != null) {
        if (subscriberInfo.getLocationInformation().getLocationInformationEPS().getMmeName() != null) {
          sb.append(",\nMME name=");
          sb.append(new String(subscriberInfo.getLocationInformation().getLocationInformationEPS().getMmeName().getData()));
        }
        if (subscriberInfo.getLocationInformation().getLocationInformationEPS().getEUtranCellGlobalIdentity() != null) {
          sb.append(",\nE-UTRAN CGI=");
          sb.append(new String(subscriberInfo.getLocationInformation().getLocationInformationEPS().getEUtranCellGlobalIdentity().getData()));
        }
        if (subscriberInfo.getLocationInformation().getLocationInformationEPS().getTrackingAreaIdentity() != null) {
          sb.append(",\nEPS Traking Identity=");
          sb.append(new String(subscriberInfo.getLocationInformation().getLocationInformationEPS().getTrackingAreaIdentity().getData()));
        }
        if (subscriberInfo.getLocationInformation().getLocationInformationEPS().getGeographicalInformation() != null) {
          sb.append(",\nEPS GeographicalLatitude=");
          sb.append(subscriberInfo.getLocationInformation().getLocationInformationEPS().getGeographicalInformation().getLatitude());
          sb.append(",\nEPS Geographical Longitude=");
          sb.append(subscriberInfo.getLocationInformation().getLocationInformationEPS().getGeographicalInformation().getLongitude());
          sb.append(",\nEPS GeographicalUncertainty=");
          sb.append(subscriberInfo.getLocationInformation().getLocationInformationEPS().getGeographicalInformation().getUncertainty());
          sb.append(",\nEPS Geographical Type of Shape Code=");
          sb.append(subscriberInfo.getLocationInformation().getGeographicalInformation().getTypeOfShape().getCode());
        }
        if (subscriberInfo.getLocationInformation().getLocationInformationEPS().getGeodeticInformation() != null) {
          sb.append(",\nEPS Geodetic Latitude=");
          sb.append(subscriberInfo.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getLatitude());
          sb.append(",\nEPS Geodetic Longitude=");
          sb.append(subscriberInfo.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getLongitude());
          sb.append(",\nEPS Geodetic Uncertainty=");
          sb.append(subscriberInfo.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getUncertainty());
          sb.append(",\nEPS Geodetic Confidence=");
          sb.append(subscriberInfo.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getConfidence());
          sb.append(",\nEPS Geodetic Type of Shape=");
          sb.append(subscriberInfo.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getTypeOfShape());
          sb.append(",\nEPS Geodetic Screening and Presentation Indicators=");
          sb.append(subscriberInfo.getLocationInformation().getLocationInformationEPS().getGeodeticInformation().getScreeningAndPresentationIndicators());
        }
        sb.append(",\nEPS Current Location Retrieved=");
        sb.append(subscriberInfo.getLocationInformation().getLocationInformationEPS().getCurrentLocationRetrieved());
        sb.append(",\nEPS AOL=");
        sb.append(subscriberInfo.getLocationInformation().getLocationInformationEPS().getAgeOfLocationInformation());
      }
    }
    if (subscriberInfo.getLocationInformationGPRS() != null) {
      if (subscriberInfo.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI() != null) {
        if (subscriberInfo.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength() != null) {
          try {
            sb.append(",\nMCC=");
            sb.append(subscriberInfo.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getMCC());
            sb.append(",\nMNC=");
            sb.append(subscriberInfo.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getMNC());
            sb.append(",\nLAC=");
            sb.append(subscriberInfo.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getLac());
            sb.append(",\nCI=");
            sb.append(subscriberInfo.getLocationInformationGPRS().getCellGlobalIdOrServiceAreaIdOrLAI().getCellGlobalIdOrServiceAreaIdFixedLength().getCellIdOrServiceAreaCode());
          } catch (MAPException e) {
            e.printStackTrace();
          }
        }
      }
      if (subscriberInfo.getLocationInformationGPRS().getGeographicalInformation() != null) {
        sb.append(",\nGPRS GeographicalLatitude=");
        sb.append(subscriberInfo.getLocationInformationGPRS().getGeographicalInformation().getLatitude());
        sb.append(",\nGPRS Geographical Longitude=");
        sb.append(subscriberInfo.getLocationInformationGPRS().getGeographicalInformation().getLongitude());
        sb.append(",\nGPRS GeographicalUncertainty=");
        sb.append(subscriberInfo.getLocationInformationGPRS().getGeographicalInformation().getUncertainty());
        sb.append(",\nGPRS Geographical Type of Shape=");
        sb.append(subscriberInfo.getLocationInformationGPRS().getGeographicalInformation().getTypeOfShape());
      }
      if (subscriberInfo.getLocationInformationGPRS().getGeodeticInformation() != null) {
        sb.append(",\nGPRS Geodetic Latitude=");
        sb.append(subscriberInfo.getLocationInformationGPRS().getGeodeticInformation().getLatitude());
        sb.append(",\nGPRS Geodetic Longitude=");
        sb.append(subscriberInfo.getLocationInformationGPRS().getGeodeticInformation().getLongitude());
        sb.append(",\nGPRS Geodetic Uncertainty=");
        sb.append(subscriberInfo.getLocationInformationGPRS().getGeodeticInformation().getUncertainty());
        sb.append(",\nGPRS Geodetic Confidence=");
        sb.append(subscriberInfo.getLocationInformationGPRS().getGeodeticInformation().getConfidence());
        sb.append(",\nGPRS Geodetic Type of Shape=");
        sb.append(subscriberInfo.getLocationInformationGPRS().getGeodeticInformation().getTypeOfShape());
        sb.append(",\nGPRS Geodetic Screening and Presentation Indicators=");
        sb.append(subscriberInfo.getLocationInformationGPRS().getGeodeticInformation().getScreeningAndPresentationIndicators());
      }
    }
    sb.append(",\nMNP info result=");
    if (subscriberInfo.getMNPInfoRes() != null) {
      sb.append(",\nMNP info result number portability status=");
      sb.append(subscriberInfo.getMNPInfoRes().getNumberPortabilityStatus().getType());
      sb.append(",\nMNP info result MSISDN=");
      sb.append(subscriberInfo.getMNPInfoRes().getMSISDN().getAddress());
      sb.append(",\nMNP info result IMSI=");
      sb.append(subscriberInfo.getMNPInfoRes().getIMSI().getData());
      sb.append(",\nMNP info result MSISDN=");
      sb.append(subscriberInfo.getMNPInfoRes().getMSISDN().getAddress());
      sb.append(",\nMNP info result Routeing Number=");
      sb.append(subscriberInfo.getMNPInfoRes().getRouteingNumber().getRouteingNumber());
    }

    sb.append(",\nMS Classmark2=");
    if (subscriberInfo.getMSClassmark2() != null)
      sb.append(new String(subscriberInfo.getMSClassmark2().getData()));
    sb.append(",\nGPRS MS Class:");
    if (subscriberInfo.getGPRSMSClass() != null) {
      sb.append(",\nNetwork Capability:");
      if (subscriberInfo.getGPRSMSClass().getMSNetworkCapability() != null)
        sb.append(new String(subscriberInfo.getGPRSMSClass().getMSNetworkCapability().getData()));
      sb.append(",\nMS Radio Access Capability:");
      if (subscriberInfo.getGPRSMSClass().getMSRadioAccessCapability() != null)
        sb.append(new String(subscriberInfo.getGPRSMSClass().getMSRadioAccessCapability().getData()));
    }
    return sb.toString();
  }

  private String createErrorData(long dialogId, int invokeId, MAPErrorMessage mapErrorMessage) {
    StringBuilder sb = new StringBuilder();
    sb.append("dialogId=");
    sb.append(dialogId);
    sb.append(",\n invokeId=");
    sb.append(invokeId);
    sb.append(",\n mapErrorMessage=");
    sb.append(mapErrorMessage);
    sb.append(",\n");
    return sb.toString();
  }

  /*
   * HLR SCCP Address creation
   */
  private SccpAddress getHLRSCCPAddress(String address) {
    ParameterFactory sccpParam = new ParameterFactoryImpl();
    int translationType = 0; // Translation Type = 0 : Unknown
    EncodingScheme encodingScheme = null;
    GlobalTitle gt = sccpParam.createGlobalTitle(address, translationType, org.restcomm.protocols.ss7.indicator.NumberingPlan.ISDN_TELEPHONY, encodingScheme, NatureOfAddress.INTERNATIONAL);
    int hlrSsn = 6;
    return sccpParam.createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_GLOBAL_TITLE, gt, translationType, hlrSsn);
  }

  /*
   * VLR SCCP Address creation
   */
  private SccpAddress getVLRSCCPAddress(String address) {
    ParameterFactory sccpParam = new ParameterFactoryImpl();
    int translationType = 0; // Translation Type = 0 : Unknown
    EncodingScheme encodingScheme = null;
    GlobalTitle gt = sccpParam.createGlobalTitle(address, translationType, org.restcomm.protocols.ss7.indicator.NumberingPlan.ISDN_TELEPHONY, encodingScheme, NatureOfAddress.INTERNATIONAL);
    int vlrSsn = 7;
    return sccpParam.createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_GLOBAL_TITLE, gt, translationType, vlrSsn);
  }

  /*
   * MSC SCCP Address creation
   */
  private SccpAddress getMSCSCCPAddress(String address) {
    ParameterFactory sccpParam = new ParameterFactoryImpl();
    int translationType = 0; // Translation Type = 0 : Unknown
    EncodingScheme encodingScheme = null;
    GlobalTitle gt = sccpParam.createGlobalTitle(address, translationType, org.restcomm.protocols.ss7.indicator.NumberingPlan.ISDN_TELEPHONY, encodingScheme, NatureOfAddress.INTERNATIONAL);
    int mscSsn = 8;
    return sccpParam.createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_GLOBAL_TITLE, gt, translationType, mscSsn);
  }

  /*
   * SGSN SCCP Address creation
   */
  private SccpAddress getSGSNSCCPAddress(String address) {
    ParameterFactory sccpParam = new ParameterFactoryImpl();
    int translationType = 0; // Translation Type = 0 : Unknown
    EncodingScheme encodingScheme = null;
    GlobalTitle gt = sccpParam.createGlobalTitle(address, translationType, org.restcomm.protocols.ss7.indicator.NumberingPlan.ISDN_TELEPHONY, encodingScheme, NatureOfAddress.INTERNATIONAL);
    int sgsnSsn = 149;
    return sccpParam.createSccpAddress(RoutingIndicator.ROUTING_BASED_ON_GLOBAL_TITLE, gt, translationType, sgsnSsn);
  }

  public static byte[] hexStringToByteArray(String s) {
    int len = s.length();
    byte[] data = new byte[len / 2];
    for (int i = 0; i < len; i += 2) {
      data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
          + Character.digit(s.charAt(i+1), 16));
    }
    return data;
  }

  private void delayResponse(int delay) {
    try {
      Thread.sleep(delay);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  // *** GETTER & SETTERS **//
  //***********************//

  @Override
  public AddressNatureType getAddressNature() {
    return new AddressNatureType(this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getAddressNature().getIndicator());
  }

  @Override
  public void setAddressNature(AddressNatureType val) {
    this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().setAddressNature(AddressNature.getInstance(val.intValue()));
    this.testerHost.markStore();
  }

  @Override
  public String getNumberingPlan() {
    return this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getNumberingPlan();
  }

  @Override
  public void setNumberingPlan(String numPlan) {
    this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().setNumberingPlan(numPlan);
    this.testerHost.markStore();
  }

  @Override
  public NumberingPlanMapType getNumberingPlanType() {
    return new NumberingPlanMapType(this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getNumberingPlanType().getIndicator());
  }

  @Override
  public void setNumberingPlanType(NumberingPlanMapType val) {
    this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().setNumberingPlanType(NumberingPlan.getInstance(val.intValue()));
    this.testerHost.markStore();
  }

  @Override
  public void putAddressNature(String val) {
    AddressNatureType x = AddressNatureType.createInstance(val);
    if (x != null)
      this.setAddressNature(x);
  }

  @Override
  public void putNumberingPlanType(String val) {
    NumberingPlanMapType x = NumberingPlanMapType.createInstance(val);
    if (x != null)
      this.setNumberingPlanType(x);
  }


  /*******************************/

  @Override
  public String getNetworkNodeNumber() {
    return new String(this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().getNetworkNodeNumber());
  }

  @Override
  public void setNetworkNodeNumber(String val) {
    this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().setNetworkNodeNumber(val);
    this.testerHost.markStore();
  }

  @Override
  public String getVmscAddress() {
    return new String(this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().getVmscAddress());
  }

  @Override
  public void setVmscAddress(String val) {
    this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().setVmscAddress(val);
    this.testerHost.markStore();
  }

  @Override
  public String getImsi() {
    return new String(this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().getImsi());
  }

  @Override
  public void setImsi(String val) {
    this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().setLmsi(val);
    this.testerHost.markStore();
  }

  @Override
  public String getLmsi() {
    return new String(this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().getLmsi());
  }

  @Override
  public void setLmsi(String val) {
    this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().setImsi(val);
    this.testerHost.markStore();
  }

  @Override
  public int getMcc() {
    return new Integer(this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().getMcc());
  }

  @Override
  public void setMcc(int val) {
    this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().setMcc(val);
    this.testerHost.markStore();
  }

  @Override
  public int getMnc() {
    return new Integer(this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().getMnc());
  }

  @Override
  public void setMnc(int val) {
    this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().setMnc(val);
    this.testerHost.markStore();
  }

  @Override
  public int getLac() {
    return new Integer(this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().getLac());
  }

  @Override
  public void setLac(int val) {
    this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().setLac(val);
    this.testerHost.markStore();
  }

  @Override
  public int getCi() {
    return new Integer(this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().getCi());
  }

  @Override
  public void setCi(int val) {
    this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().setCi(val);
    this.testerHost.markStore();
  }

  @Override
  public int getAol() {
    return new Integer(this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().getAol());
  }

  @Override
  public void setAol(int val) {
    this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().setAol(val);
    this.testerHost.markStore();
  }

  @Override
  public boolean isSaiPresent() {
    return new Boolean(this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().isSaiPresent());
  }

  @Override
  public void setSaiPresent(boolean val) {
    this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().setSaiPresent(val);
    this.testerHost.markStore();
  }

  @Override
  public double getGeographicalLatitude() {
    return new Double(this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().getGeographicalLatitude());
  }

  @Override
  public void setGeographicalLatitude(double val) {
    this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().setGeographicalLatitude(val);
    this.testerHost.markStore();
  }

  @Override
  public double getGeographicalLongitude() {
    return new Double(this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().getGeographicalLongitude());
  }

  @Override
  public void setGeographicalLongitude(double val) {
    this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().setGeographicalLongitude(val);
    this.testerHost.markStore();
  }

  @Override
  public double getGeographicalUncertainty() {
    return new Double(this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().getGeographicalUncertainty());
  }

  @Override
  public void setGeographicalUncertainty(double val) {
    this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().setGeographicalLongitude(val);
    this.testerHost.markStore();
  }

  @Override
  public int getScreeningAndPresentationIndicators() {
    return new Integer(this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().getScreeningAndPresentationIndicators());
  }

  @Override
  public void setScreeningAndPresentationIndicators(int val) {
    this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().setScreeningAndPresentationIndicators(val);
    this.testerHost.markStore();
  }

  @Override
  public double getGeodeticLatitude() {
    return new Double(this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().getGeodeticLatitude());
  }

  @Override
  public void setGeodeticLatitude(double val) {
    this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().setGeodeticLatitude(val);
    this.testerHost.markStore();
  }

  @Override
  public double getGeodeticLongitude() {
    return new Double(this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().getGeodeticLongitude());
  }

  @Override
  public void setGeodeticLongitude(double val) {
    this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().setGeodeticLongitude(val);
    this.testerHost.markStore();
  }

  @Override
  public double getGeodeticUncertainty() {
    return new Double(this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().getGeodeticUncertainty());
  }

  @Override
  public void setGeodeticUncertainty(double val) {
    this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().setGeodeticUncertainty(val);
    this.testerHost.markStore();
  }

  @Override
  public int getGeodeticConfidence() {
    return new Integer(this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().getGeodeticConfidence());
  }

  @Override
  public void setGeodeticConfidence(int val) {
    this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().setGeodeticConfidence(val);
    this.testerHost.markStore();
  }

  @Override
  public boolean isCurrentLocationRetrieved() {
    return new Boolean(this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().isCurrentLocationRetrieved());
  }

  @Override
  public void setCurrentLocationRetrieved(boolean val) {
    this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().setCurrentLocationRetrieved(val);
    this.testerHost.markStore();
  }

  @Override
  public String getImei() {
    return new String(this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().getImei());
  }

  @Override
  public void setImei(String val) {
    this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().setImei(val);
    this.testerHost.markStore();
  }


  /*******************************/


  @Override
  public SRIReaction getSRIReaction() {
    return this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().getSriForSMReaction();
  }

  @Override
  public String getSRIReaction_Value() {
    return this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().getSriForSMReaction().toString();
  }

  @Override
  public void setSRIReaction(SRIReaction val) {
    this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().setSriForSMReaction(val);
    this.testerHost.markStore();
  }

  @Override
  public void putSRIReaction(String val) {
    SRIReaction x = SRIReaction.createInstance(val);
    if (x != null)
      this.setSRIReaction(x);
  }

  @Override
  public PSIReaction getPSIReaction() {
    return this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().getPsiReaction();
  }

  @Override
  public String getPSIReaction_Value() {
    return this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().getPsiReaction().toString();
  }

  @Override
  public void setPSIReaction(PSIReaction val) {
    this.testerHost.getConfigurationData().getTestPsiServerConfigurationData().setPsiReaction(val);
    this.testerHost.markStore();
  }

  @Override
  public void putPSIReaction(String val) {
    PSIReaction x = PSIReaction.createInstance(val);
    if (x != null)
      this.setPSIReaction(x);
  }

  @Override
  public String getCurrentRequestDef() {
    return "LastDialog: " + currentRequestDef;
  }

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


  //** Not needed MAPServiceSms methods **//
  @Override
  public void onForwardShortMessageRequest(ForwardShortMessageRequest forwardShortMessageRequest) {

  }

  @Override
  public void onForwardShortMessageResponse(ForwardShortMessageResponse forwardShortMessageResponse) {

  }

  @Override
  public void onMoForwardShortMessageRequest(MoForwardShortMessageRequest moForwardShortMessageRequest) {

  }

  @Override
  public void onMoForwardShortMessageResponse(MoForwardShortMessageResponse moForwardShortMessageResponse) {

  }

  @Override
  public void onMtForwardShortMessageRequest(MtForwardShortMessageRequest mtForwardShortMessageRequest) {

  }

  @Override
  public void onMtForwardShortMessageResponse(MtForwardShortMessageResponse mtForwardShortMessageResponse) {

  }

  @Override
  public void onSendRoutingInfoForSMResponse(SendRoutingInfoForSMResponse sendRoutingInfoForSMResponse) {

  }

  @Override
  public void onReportSMDeliveryStatusRequest(ReportSMDeliveryStatusRequest reportSMDeliveryStatusRequest) {

  }

  @Override
  public void onReportSMDeliveryStatusResponse(ReportSMDeliveryStatusResponse reportSMDeliveryStatusResponse) {

  }

  @Override
  public void onInformServiceCentreRequest(InformServiceCentreRequest informServiceCentreRequest) {

  }

  @Override
  public void onAlertServiceCentreRequest(AlertServiceCentreRequest alertServiceCentreRequest) {

  }

  @Override
  public void onAlertServiceCentreResponse(AlertServiceCentreResponse alertServiceCentreResponse) {

  }

  @Override
  public void onReadyForSMRequest(ReadyForSMRequest readyForSMRequest) {

  }

  @Override
  public void onReadyForSMResponse(ReadyForSMResponse readyForSMResponse) {

  }

  @Override
  public void onNoteSubscriberPresentRequest(NoteSubscriberPresentRequest noteSubscriberPresentRequest) {

  }

  //** Not needed MAPServiceMobility methods **//
  @Override
  public void onUpdateLocationRequest(UpdateLocationRequest updateLocationRequest) {

  }

  @Override
  public void onUpdateLocationResponse(UpdateLocationResponse updateLocationResponse) {

  }

  @Override
  public void onCancelLocationRequest(CancelLocationRequest cancelLocationRequest) {

  }

  @Override
  public void onCancelLocationResponse(CancelLocationResponse cancelLocationResponse) {

  }

  @Override
  public void onSendIdentificationRequest(SendIdentificationRequest sendIdentificationRequest) {

  }

  @Override
  public void onSendIdentificationResponse(SendIdentificationResponse sendIdentificationResponse) {

  }

  @Override
  public void onUpdateGprsLocationRequest(UpdateGprsLocationRequest updateGprsLocationRequest) {

  }

  @Override
  public void onUpdateGprsLocationResponse(UpdateGprsLocationResponse updateGprsLocationResponse) {

  }

  @Override
  public void onPurgeMSRequest(PurgeMSRequest purgeMSRequest) {

  }

  @Override
  public void onPurgeMSResponse(PurgeMSResponse purgeMSResponse) {

  }

  @Override
  public void onSendAuthenticationInfoRequest(SendAuthenticationInfoRequest sendAuthenticationInfoRequest) {

  }

  @Override
  public void onSendAuthenticationInfoResponse(SendAuthenticationInfoResponse sendAuthenticationInfoResponse) {

  }

  @Override
  public void onAuthenticationFailureReportRequest(AuthenticationFailureReportRequest authenticationFailureReportRequest) {

  }

  @Override
  public void onAuthenticationFailureReportResponse(AuthenticationFailureReportResponse authenticationFailureReportResponse) {

  }

  @Override
  public void onResetRequest(ResetRequest resetRequest) {

  }

  @Override
  public void onForwardCheckSSIndicationRequest(ForwardCheckSSIndicationRequest forwardCheckSSIndicationRequest) {

  }

  @Override
  public void onRestoreDataRequest(RestoreDataRequest restoreDataRequest) {

  }

  @Override
  public void onRestoreDataResponse(RestoreDataResponse restoreDataResponse) {

  }

  @Override
  public void onAnyTimeInterrogationRequest(AnyTimeInterrogationRequest anyTimeInterrogationRequest) {

  }

  @Override
  public void onAnyTimeInterrogationResponse(AnyTimeInterrogationResponse anyTimeInterrogationResponse) {

  }

  @Override
  public void onAnyTimeSubscriptionInterrogationRequest(AnyTimeSubscriptionInterrogationRequest request) {

  }

  @Override
  public void onAnyTimeSubscriptionInterrogationResponse(AnyTimeSubscriptionInterrogationResponse response) {

  }

  @Override
  public void onProvideSubscriberInfoResponse(ProvideSubscriberInfoResponse provideSubscriberInfoResponse) {

  }

  @Override
  public void onInsertSubscriberDataRequest(InsertSubscriberDataRequest insertSubscriberDataRequest) {

  }

  @Override
  public void onInsertSubscriberDataResponse(InsertSubscriberDataResponse insertSubscriberDataResponse) {

  }

  @Override
  public void onDeleteSubscriberDataRequest(DeleteSubscriberDataRequest deleteSubscriberDataRequest) {

  }

  @Override
  public void onDeleteSubscriberDataResponse(DeleteSubscriberDataResponse deleteSubscriberDataResponse) {

  }

  @Override
  public void onCheckImeiRequest(CheckImeiRequest checkImeiRequest) {

  }

  @Override
  public void onCheckImeiResponse(CheckImeiResponse checkImeiResponse) {

  }

  @Override
  public void onActivateTraceModeRequest_Mobility(ActivateTraceModeRequest_Mobility activateTraceModeRequest_mobility) {

  }

  @Override
  public void onActivateTraceModeResponse_Mobility(ActivateTraceModeResponse_Mobility activateTraceModeResponse_mobility) {

  }

  @Override
  public void onSendRoutingInformationResponse(SendRoutingInformationResponse response) {

  }

  @Override
  public void onProvideRoamingNumberRequest(ProvideRoamingNumberRequest request) {

  }

  @Override
  public void onProvideRoamingNumberResponse(ProvideRoamingNumberResponse response) {

  }

  @Override
  public void onIstCommandRequest(IstCommandRequest request) {

  }

  @Override
  public void onIstCommandResponse(IstCommandResponse response) {

  }

}
