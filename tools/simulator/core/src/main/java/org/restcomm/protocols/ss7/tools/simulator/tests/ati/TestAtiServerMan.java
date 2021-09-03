package org.restcomm.protocols.ss7.tools.simulator.tests.ati;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.indicator.NatureOfAddress;
import org.restcomm.protocols.ss7.indicator.RoutingIndicator;
import org.restcomm.protocols.ss7.isup.impl.message.parameter.LocationNumberImpl;
import org.restcomm.protocols.ss7.isup.message.parameter.LocationNumber;
import org.restcomm.protocols.ss7.map.api.MAPDialog;
import org.restcomm.protocols.ss7.map.api.MAPDialogListener;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPProvider;
import org.restcomm.protocols.ss7.map.api.errors.MAPErrorCode;
import org.restcomm.protocols.ss7.map.api.errors.MAPErrorMessage;
import org.restcomm.protocols.ss7.map.api.errors.UnknownSubscriberDiagnostic;
import org.restcomm.protocols.ss7.map.api.primitives.AddressNature;
import org.restcomm.protocols.ss7.map.api.primitives.CellGlobalIdOrServiceAreaIdFixedLength;
import org.restcomm.protocols.ss7.map.api.primitives.CellGlobalIdOrServiceAreaIdOrLAI;
import org.restcomm.protocols.ss7.map.api.primitives.DiameterIdentity;
import org.restcomm.protocols.ss7.map.api.primitives.IMEI;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.primitives.NetworkResource;
import org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan;
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
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.DomainType;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.EUtranCgi;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.GPRSMSClass;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.GeodeticInformation;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.GeographicalInformation;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformation;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformationEPS;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformationGPRS;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationNumberMap;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.MNPInfoRes;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.MSClassmark2;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.MSNetworkCapability;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.MSRadioAccessCapability;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.NotReachableReason;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.NumberPortabilityStatus;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.PDPContextInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.PSSubscriberState;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.PSSubscriberStateChoice;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.ProvideSubscriberInfoRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.ProvideSubscriberInfoResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.RAIdentity;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.RequestedInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.RouteingNumber;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.SubscriberInfo;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.SubscriberState;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.SubscriberStateChoice;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.TAId;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.TypeOfShape;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.UserCSGInformation;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.DeleteSubscriberDataRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.DeleteSubscriberDataResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.InsertSubscriberDataRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.InsertSubscriberDataResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.LSAIdentity;
import org.restcomm.protocols.ss7.map.errors.MAPErrorMessageUnknownSubscriberImpl;
import org.restcomm.protocols.ss7.map.primitives.DiameterIdentityImpl;
import org.restcomm.protocols.ss7.map.primitives.IMSIImpl;
import org.restcomm.protocols.ss7.map.primitives.ISDNAddressStringImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.EUtranCgiImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.GeodeticInformationImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.GeographicalInformationImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.LocationInformationEPSImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.LocationNumberMapImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.MSNetworkCapabilityImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.MSRadioAccessCapabilityImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.RAIdentityImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.RouteingNumberImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.TAIdImpl;
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
import org.restcomm.protocols.ss7.tools.simulator.common.TesterBase;
import org.restcomm.protocols.ss7.tools.simulator.level3.MapMan;
import org.restcomm.protocols.ss7.tools.simulator.management.TesterHostImpl;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 *  @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 *  @author sergey vetyutnev
 *
 */
public class TestAtiServerMan extends TesterBase implements TestAtiServerManMBean, Stoppable, MAPDialogListener, MAPServiceMobilityListener {

    public static String SOURCE_NAME = "TestAtiServer";

    private static Logger logger = Logger.getLogger(TestAtiServerMan.class);

    private final String name;
    private MapMan mapMan;

    private boolean isStarted = false;
    private int countAtiReq = 0;
    private int countAtiResp = 0;
    private String currentRequestDef = "";
    private boolean needSendSend = false;
    private boolean needSendClose = false;
    private int countErrSent = 0;

    public TestAtiServerMan() {
        super(SOURCE_NAME);
        this.name = "???";
    }

    public TestAtiServerMan(String name) {
        super(SOURCE_NAME);
        this.name = name;
    }

    public void setTesterHost(TesterHostImpl testerHost) {
        this.testerHost = testerHost;
    }

    public void setMapMan(MapMan val) {
        this.mapMan = val;
    }

    @Override
    public String getCurrentRequestDef() {
        return "LastDialog: " + currentRequestDef;
    }

    @Override
    public ATIReaction getATIReaction() {
        return this.testerHost.getConfigurationData().getTestAtiServerConfigurationData().getATIReaction();
    }

    @Override
    public String getATIReaction_Value() {
        return this.testerHost.getConfigurationData().getTestAtiServerConfigurationData().getATIReaction().toString();
    }

    @Override
    public void setATIReaction(ATIReaction val) {
        this.testerHost.getConfigurationData().getTestAtiServerConfigurationData().setATIReaction(val);
        this.testerHost.markStore();
    }

    @Override
    public void putATIReaction(String val) {
        ATIReaction x = ATIReaction.createInstance(val);
        if (x != null)
            this.setATIReaction(x);
    }

    @Override
    public String getState() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append(SOURCE_NAME);
        sb.append(": ");
        sb.append("<br>Count: countAtiReq-");
        sb.append(countAtiReq);
        sb.append(", countAtiResp-");
        sb.append(countAtiResp);
        sb.append(", countErrSent-");
        sb.append(countErrSent);
        sb.append("</html>");
        return sb.toString();
    }

    public boolean start() {
        this.countAtiReq = 0;
        this.countAtiResp = 0;
        this.countErrSent = 0;

        MAPProvider mapProvider = this.mapMan.getMAPStack().getMAPProvider();
        mapProvider.getMAPServiceMobility().activate();
        mapProvider.getMAPServiceMobility().addMAPServiceListener(this);
        mapProvider.addMAPDialogListener(this);
        this.testerHost.sendNotif(SOURCE_NAME, "ATI Server has been started", "", Level.INFO);
        isStarted = true;

        return true;
    }

    @Override
    public void stop() {
        MAPProvider mapProvider = this.mapMan.getMAPStack().getMAPProvider();
        isStarted = false;
        mapProvider.getMAPServiceMobility().deactivate();
        mapProvider.getMAPServiceMobility().removeMAPServiceListener(this);
        mapProvider.removeMAPDialogListener(this);
        this.testerHost.sendNotif(SOURCE_NAME, "ATI Server has been stopped", "", Level.INFO);
    }

    @Override
    public void execute() {
    }

    @Override
    public void onAnyTimeInterrogationRequest(AnyTimeInterrogationRequest ind) {
        if (!isStarted)
            return;

        this.countAtiReq++;

        MAPProvider mapProvider = this.mapMan.getMAPStack().getMAPProvider();
        MAPDialogMobility curDialog = ind.getMAPDialog();
        long invokeId = ind.getInvokeId();
        String uData = this.createAtiData(ind);

        this.testerHost.sendNotif(SOURCE_NAME, "Rcvd: atiReq", uData, Level.DEBUG);

        // Set Calling SCCP Address (HLR for ATI response)
        curDialog.setLocalAddress(getHLRSCCPAddress("598991700001"));

        // Generate MAP errors for specific MSISDNs
        String msisdnAddress = ind.getSubscriberIdentity().getMSISDN().getAddress();
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
                createAtiRespData(curDialog.getLocalDialogId()), Level.INFO);
            return;
        }
        if (msisdnAddress.equalsIgnoreCase("99990000")) {
            MAPErrorMessage mapErrorMessageUnknownSubscriber = new MAPErrorMessageUnknownSubscriberImpl();
            try {
                curDialog.sendErrorComponent(invokeId, mapErrorMessageUnknownSubscriber);
                curDialog.close(false);
            } catch (MAPException e) {
                e.printStackTrace();
            }
            logger.debug("\nErrorComponent sent");
            this.testerHost.sendNotif(SOURCE_NAME, "Sent: ErrorComponent",
                createAtiRespData(curDialog.getLocalDialogId()), Level.INFO);
            return;
        }

        Random rand = new Random();

        RequestedInfo requestedInfo = ind.getRequestedInfo();
        try {
            ATIReaction atiReaction = this.testerHost.getConfigurationData().getTestAtiServerConfigurationData().getATIReaction();

            switch (atiReaction.intValue()) {
                case ATIReaction.VAL_RETURN_SUCCESS:
                    LocationInformation locationInformation = null;
                    LocationInformationEPS locationInformationEPS;
                    LocationInformationGPRS locationInformationGPRS = null;
                    Integer ageOfLocationInformation = 0;
                    Boolean currentLocationRetrieved = null;
                    Boolean saiPresent;
                    int mcc = 0, mnc = 0, lac = 0, cellId = 0;
                    CellGlobalIdOrServiceAreaIdOrLAI cellGlobalIdOrServiceAreaIdOrLAI;
                    CellGlobalIdOrServiceAreaIdFixedLength cgiOrSai;
                    LocationNumber locationNumber = null;
                    LocationNumberMap locationNumberMap = null;
                    String mscAddress = "5982123007";
                    String vlrAddress = "59899000231";
                    String sgsnAddress = "5982133021";
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
                        int geodeticConfidence;
                        int screeningAndPresentationIndicators;
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
                        if (msisdnAddress.equalsIgnoreCase("77778888")) {
                            mcc = 748;
                            mnc = 10;
                            lac = 9501;
                            cellId = 35100;
                            geographicalLatitude = 0.0;
                            geographicalLongitude = 0.0;
                            geographicalUncertainty = 0.0;
                            geographicalInformation = new GeographicalInformationImpl(geographicalTypeOfShape, geographicalLatitude, geographicalLongitude, geographicalUncertainty);
                        }
                        if (msisdnAddress.equalsIgnoreCase("9899070965")) {
                            Integer newDelhiLocation = rand.nextInt(10) + 1;
                            switch(newDelhiLocation) {
                                case 1:
                                    mcc = 404;
                                    mnc = 10;
                                    lac = 19870;
                                    cellId = 19087;
                                    geographicalLatitude = 28.612451;
                                    geographicalLongitude = 77.190871;
                                    geographicalUncertainty = 1.0;
                                    break;
                                case 2:
                                    mcc = 404;
                                    mnc = 10;
                                    lac = 19870;
                                    cellId = 19088;
                                    geographicalLatitude = 28.612702;
                                    geographicalLongitude = 77.190882;
                                    geographicalUncertainty = 2.0;
                                    break;
                                case 3:
                                    mcc = 404;
                                    mnc = 10;
                                    lac = 19870;
                                    cellId = 19089;
                                    geographicalLatitude = 28.613733;
                                    geographicalLongitude = 77.191843;
                                    geographicalUncertainty = 3.0;
                                    break;
                                case 4:
                                    mcc = 404;
                                    mnc = 10;
                                    lac = 19870;
                                    cellId = 19090;
                                    geographicalLatitude = 28.612744;
                                    geographicalLongitude = 77.192844;
                                    geographicalUncertainty = 4.0;
                                    break;
                                case 5:
                                    mcc = 404;
                                    mnc = 10;
                                    lac = 19870;
                                    cellId = 19091;
                                    geographicalLatitude = 28.612734;
                                    geographicalLongitude = 77.190871;
                                    geographicalUncertainty = 5.0;
                                    break;
                                case 6:
                                    mcc = 404;
                                    mnc = 10;
                                    lac = 19870;
                                    cellId = 19092;
                                    geographicalLatitude = 28.612725;
                                    geographicalLongitude = 77.190705;
                                    geographicalUncertainty = 6.0;
                                    break;
                                case 7:
                                    mcc = 404;
                                    mnc = 10;
                                    lac = 19870;
                                    cellId = 19093;
                                    geographicalLatitude = 28.612006;
                                    geographicalLongitude = 77.190006;
                                    geographicalUncertainty = 7.0;
                                    break;
                                case 8:
                                    mcc = 404;
                                    mnc = 10;
                                    lac = 19870;
                                    cellId = 19094;
                                    geographicalLatitude = 28.622637;
                                    geographicalLongitude = 77.191227;
                                    geographicalUncertainty = 8.0;
                                    break;
                                case 9:
                                    mcc = 404;
                                    mnc = 10;
                                    lac = 19870;
                                    cellId = 19095;
                                    geographicalLatitude = 28.612888;
                                    geographicalLongitude = 77.190798;
                                    geographicalUncertainty = 9.0;
                                    break;
                                case 10:
                                    mcc = 404;
                                    mnc = 10;
                                    lac = 19870;
                                    cellId = 19096;
                                    geographicalLatitude = 28.612010;
                                    geographicalLongitude = 77.190010;
                                    geographicalUncertainty = 10.0;
                                    break;
                                default:
                                    mcc = 404;
                                    mnc = 10;
                                    lac = 19870;
                                    cellId = 19097;
                                    geographicalLatitude = 28.612734;
                                    geographicalLongitude = 77.190871;
                                    geographicalUncertainty = 10.0;
                                    break;
                            }
                            geographicalInformation = new GeographicalInformationImpl(geographicalTypeOfShape, geographicalLatitude, geographicalLongitude, geographicalUncertainty);
                            geodeticInformation = null;
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

                            cgiOrSai = mapProvider.getMAPParameterFactory().createCellGlobalIdOrServiceAreaIdFixedLength(mcc, mnc, lac, cellId);
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
                                if (this.countAtiReq % 4 == 0) {
                                    // Let's set that one out of 4 ATI requests (25%) with epsLocationInfoSupported = true
                                    // doesn't retrieve EPS location information (as if the subscriber is under GERAN or UTRAN coverage only)
                                    locationInformationEPS = null;
                                    locationInformation = mapProvider.getMAPParameterFactory().createLocationInformation(ageOfLocationInformation, geographicalInformation,
                                        vlrNumber, locationNumberMap, cellGlobalIdOrServiceAreaIdOrLAI, extensionContainer, selectedLSAId, mscNumber, geodeticInformation,
                                        currentLocationRetrieved, saiPresent, locationInformationEPS, userCSGInformation);
                                } else {
                                    // Rest of ATI requests (75%) does retrieve EPS location information (subscriber under E-UTRAN coverage)
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
                            if (msisdnAddress.equalsIgnoreCase("9899070965")) {
                                mcc = 404;
                                mnc = 10;
                                lac = 12704;
                                cellId = 10087;
                                raId = hexStringToByteArray("04f40131a009");
                            }
                            if (this.countAtiReq % 2 == 0)
                                saiPresent = true; // set saiPresent to true if this ATI request is even since test started
                            else
                                saiPresent = false; // set saiPresent to false if this ATI request is odd since test started
                            cgiOrSai = mapProvider.getMAPParameterFactory().createCellGlobalIdOrServiceAreaIdFixedLength(mcc, mnc, lac, cellId);
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
                            IMSI imsi = new IMSIImpl("748026871012345");
                            String msisdnStr = "59899077937";
                            ISDNAddressString msisdn = new ISDNAddressStringImpl(AddressNature.international_number,
                                NumberingPlan.ISDN, msisdnStr);
                            numberPortabilityStatus = NumberPortabilityStatus.ownNumberNotPortedOut;
                            mnpInfoRes = mapProvider.getMAPParameterFactory().createMNPInfoRes(routeingNumber, imsi, msisdn, numberPortabilityStatus, extensionContainer);
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

                    SubscriberInfo subscriberInfo = mapProvider.getMAPParameterFactory().createSubscriberInfo(locationInformation, subscriberState, extensionContainer,
                            locationInformationGPRS, psSubscriberState, imei, msClassmark2, gprsMSClass, mnpInfoRes);

                    delayResponse(300);

                    curDialog.addAnyTimeInterrogationResponse(invokeId, subscriberInfo, extensionContainer);

                    this.countAtiResp++;
                    uData = this.createAtiRespData(curDialog.getLocalDialogId());
                    this.testerHost.sendNotif(SOURCE_NAME, "Sent: atiResp", uData, Level.DEBUG);
                    break;

                case ATIReaction.VAL_ERROR_UNKNOWN_SUBSCRIBER:
                    MAPErrorMessage mapErrorMessage = null;
                    // MAPUserAbortChoice mapUserAbortChoice = new MAPUserAbortChoiceImpl();
                    // mapUserAbortChoice.setProcedureCancellationReason(ProcedureCancellationReason.handoverCancellation);
                    // curDialog.abort(mapUserAbortChoice);
                    // return;
                    mapErrorMessage = mapProvider.getMAPErrorMessageFactory().createMAPErrorMessageUnknownSubscriber(null,
                            UnknownSubscriberDiagnostic.imsiUnknown);

                    curDialog.sendErrorComponent(invokeId, mapErrorMessage);

                    this.countErrSent++;
                    uData = this.createErrorData(curDialog.getLocalDialogId(), (int) invokeId, mapErrorMessage);
                    this.testerHost.sendNotif(SOURCE_NAME, "Sent: error Unknown Subscriber", uData, Level.DEBUG);
                    break;

                case ATIReaction.VAL_DATA_MISSING:
                    mapErrorMessage = mapProvider.getMAPErrorMessageFactory().createMAPErrorMessageExtensionContainer(
                            (long) MAPErrorCode.dataMissing, null);
                    curDialog.sendErrorComponent(invokeId, mapErrorMessage);

                    this.countErrSent++;
                    uData = this.createErrorData(curDialog.getLocalDialogId(), (int) invokeId, mapErrorMessage);
                    this.testerHost.sendNotif(SOURCE_NAME, "Sent: error Data Missing", uData, Level.DEBUG);
                    break;

                case ATIReaction.VAL_ERROR_SYSTEM_FAILURE:
                    mapErrorMessage = mapProvider.getMAPErrorMessageFactory().createMAPErrorMessageSystemFailure(
                            (long) curDialog.getApplicationContext().getApplicationContextVersion().getVersion(), NetworkResource.hlr, null, null);
                    curDialog.sendErrorComponent(invokeId, mapErrorMessage);

                    this.countErrSent++;
                    uData = this.createErrorData(curDialog.getLocalDialogId(), (int) invokeId, mapErrorMessage);
                    this.testerHost.sendNotif(SOURCE_NAME, "Sent: err System Failure", uData, Level.DEBUG);
                    break;
            }

            this.needSendClose = true;

        } catch (MAPException e) {
            this.testerHost.sendNotif(SOURCE_NAME, "Exception when invoking addAnyTimeInterrogationResponse() : " + e.getMessage(), e, Level.ERROR);
        }
    }

    private void delayResponse(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

    private String createAtiData(AnyTimeInterrogationRequest ind) {
        StringBuilder sb = new StringBuilder();
        sb.append("dialogId=");
        sb.append(ind.getMAPDialog().getLocalDialogId());
        sb.append(",\natiReq=");
        sb.append(ind);

        sb.append(",\nRemoteAddress=");
        sb.append(ind.getMAPDialog().getRemoteAddress());
        sb.append(",\nLocalAddress=");
        sb.append(ind.getMAPDialog().getLocalAddress());

        return sb.toString();
    }

    private String createAtiRespData(long dialogId) {
        StringBuilder sb = new StringBuilder();
        sb.append("dialogId=");
        sb.append(dialogId);
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

    @Override
    public void onActivateTraceModeRequest_Mobility(ActivateTraceModeRequest_Mobility arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onActivateTraceModeResponse_Mobility(ActivateTraceModeResponse_Mobility arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onAnyTimeInterrogationResponse(AnyTimeInterrogationResponse arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onAnyTimeSubscriptionInterrogationRequest(AnyTimeSubscriptionInterrogationRequest request) {

    }

    @Override
    public void onAnyTimeSubscriptionInterrogationResponse(AnyTimeSubscriptionInterrogationResponse response) {

    }

    @Override
    public void onCancelLocationRequest(CancelLocationRequest arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onCancelLocationResponse(CancelLocationResponse arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onCheckImeiRequest(CheckImeiRequest arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onCheckImeiResponse(CheckImeiResponse arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onDeleteSubscriberDataRequest(DeleteSubscriberDataRequest arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onDeleteSubscriberDataResponse(DeleteSubscriberDataResponse arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onForwardCheckSSIndicationRequest(ForwardCheckSSIndicationRequest arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onInsertSubscriberDataRequest(InsertSubscriberDataRequest arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onInsertSubscriberDataResponse(InsertSubscriberDataResponse arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProvideSubscriberInfoRequest(ProvideSubscriberInfoRequest arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProvideSubscriberInfoResponse(ProvideSubscriberInfoResponse arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPurgeMSRequest(PurgeMSRequest arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPurgeMSResponse(PurgeMSResponse arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onResetRequest(ResetRequest arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onRestoreDataRequest(RestoreDataRequest arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onRestoreDataResponse(RestoreDataResponse arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onSendAuthenticationInfoRequest(SendAuthenticationInfoRequest arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onSendAuthenticationInfoResponse(SendAuthenticationInfoResponse arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onSendIdentificationRequest(SendIdentificationRequest arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onSendIdentificationResponse(SendIdentificationResponse arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onUpdateGprsLocationRequest(UpdateGprsLocationRequest arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onUpdateGprsLocationResponse(UpdateGprsLocationResponse arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onUpdateLocationRequest(UpdateLocationRequest arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onUpdateLocationResponse(UpdateLocationResponse arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onAuthenticationFailureReportRequest(AuthenticationFailureReportRequest arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onAuthenticationFailureReportResponse(AuthenticationFailureReportResponse arg0) {
        // TODO Auto-generated method stub

    }

}
