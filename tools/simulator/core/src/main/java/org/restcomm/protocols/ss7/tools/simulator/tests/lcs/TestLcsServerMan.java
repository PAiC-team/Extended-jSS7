package org.restcomm.protocols.ss7.tools.simulator.tests.lcs;

import org.restcomm.protocols.ss7.indicator.NatureOfAddress;
import org.restcomm.protocols.ss7.indicator.RoutingIndicator;
import org.restcomm.protocols.ss7.map.api.MAPApplicationContext;
import org.restcomm.protocols.ss7.map.api.MAPApplicationContextName;
import org.restcomm.protocols.ss7.map.api.MAPApplicationContextVersion;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParameterFactory;
import org.restcomm.protocols.ss7.map.api.MAPProvider;
import org.restcomm.protocols.ss7.map.api.datacoding.CBSDataCodingScheme;

import org.restcomm.protocols.ss7.map.MAPParameterFactoryImpl;

import org.restcomm.protocols.ss7.map.api.errors.MAPErrorMessage;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.AddressNature;
import org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan;
import org.restcomm.protocols.ss7.map.api.primitives.SubscriberIdentity;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.IMEI;
import org.restcomm.protocols.ss7.map.api.primitives.LMSI;
import org.restcomm.protocols.ss7.map.api.primitives.GSNAddress;
import org.restcomm.protocols.ss7.map.api.primitives.GSNAddressAddressType;
import org.restcomm.protocols.ss7.map.api.primitives.CellGlobalIdOrServiceAreaIdOrLAI;
import org.restcomm.protocols.ss7.map.api.primitives.CellGlobalIdOrServiceAreaIdFixedLength;
import org.restcomm.protocols.ss7.map.api.primitives.DiameterIdentity;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.primitives.USSDString;
import org.restcomm.protocols.ss7.map.api.primitives.AddressString;

import org.restcomm.protocols.ss7.map.api.service.lsm.EllipsoidPoint;
import org.restcomm.protocols.ss7.map.api.service.lsm.MAPServiceLsm;
import org.restcomm.protocols.ss7.map.api.service.lsm.MAPServiceLsmListener;
import org.restcomm.protocols.ss7.map.api.service.lsm.MAPDialogLsm;
import org.restcomm.protocols.ss7.map.api.service.lsm.LocationType;
import org.restcomm.protocols.ss7.map.api.service.lsm.LocationEstimateType;
import org.restcomm.protocols.ss7.map.api.service.lsm.DeferredLocationEventType;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSClientID;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSPriority;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSCodeword;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSQoS;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSPrivacyCheck;
import org.restcomm.protocols.ss7.map.api.service.lsm.PeriodicLDRInfo;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSEvent;
import org.restcomm.protocols.ss7.map.api.service.lsm.ExtGeographicalInformation;
import org.restcomm.protocols.ss7.map.api.service.lsm.DeferredmtlrData;
import org.restcomm.protocols.ss7.map.api.service.lsm.AccuracyFulfilmentIndicator;
import org.restcomm.protocols.ss7.map.api.service.lsm.Polygon;
import org.restcomm.protocols.ss7.map.api.service.lsm.ReportingPLMNList;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSClientType;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSClientExternalID;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSClientInternalID;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSFormatIndicator;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSClientName;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSRequestorID;
import org.restcomm.protocols.ss7.map.api.service.lsm.AreaEventInfo;
import org.restcomm.protocols.ss7.map.api.service.lsm.AreaType;
import org.restcomm.protocols.ss7.map.api.service.lsm.OccurrenceInfo;
import org.restcomm.protocols.ss7.map.api.service.lsm.PrivacyCheckRelatedAction;
import org.restcomm.protocols.ss7.map.api.service.lsm.SLRArgExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.lsm.AddGeographicalInformation;
import org.restcomm.protocols.ss7.map.api.service.lsm.AdditionalNumber;
import org.restcomm.protocols.ss7.map.api.service.lsm.ServingNodeAddress;
import org.restcomm.protocols.ss7.map.api.service.lsm.PositioningDataInformation;
import org.restcomm.protocols.ss7.map.api.service.lsm.GeranGANSSpositioningData;
import org.restcomm.protocols.ss7.map.api.service.lsm.UtranPositioningDataInfo;
import org.restcomm.protocols.ss7.map.api.service.lsm.UtranGANSSpositioningData;
import org.restcomm.protocols.ss7.map.api.service.lsm.VelocityEstimate;
import org.restcomm.protocols.ss7.map.api.service.lsm.LCSLocationInfo;
import org.restcomm.protocols.ss7.map.api.service.lsm.TerminationCause;
import org.restcomm.protocols.ss7.map.api.service.lsm.SendRoutingInfoForLCSRequest;
import org.restcomm.protocols.ss7.map.api.service.lsm.SendRoutingInfoForLCSResponse;
import org.restcomm.protocols.ss7.map.api.service.lsm.ProvideSubscriberLocationRequest;
import org.restcomm.protocols.ss7.map.api.service.lsm.ProvideSubscriberLocationResponse;
import org.restcomm.protocols.ss7.map.api.service.lsm.SubscriberLocationReportRequest;
import org.restcomm.protocols.ss7.map.api.service.lsm.SubscriberLocationReportResponse;

import org.restcomm.protocols.ss7.map.api.service.lsm.VelocityType;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.SupportedLCSCapabilitySets;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.TypeOfShape;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.APN;
import org.restcomm.protocols.ss7.map.datacoding.CBSDataCodingSchemeImpl;
import org.restcomm.protocols.ss7.map.errors.MAPErrorMessageFacilityNotSupImpl;
import org.restcomm.protocols.ss7.map.errors.MAPErrorMessageUnauthorizedLCSClientImpl;
import org.restcomm.protocols.ss7.map.primitives.ISDNAddressStringImpl;
import org.restcomm.protocols.ss7.map.primitives.CellGlobalIdOrServiceAreaIdFixedLengthImpl;
import org.restcomm.protocols.ss7.map.primitives.CellGlobalIdOrServiceAreaIdOrLAIImpl;
import org.restcomm.protocols.ss7.map.primitives.IMSIImpl;
import org.restcomm.protocols.ss7.map.primitives.IMEIImpl;
import org.restcomm.protocols.ss7.map.primitives.SubscriberIdentityImpl;
import org.restcomm.protocols.ss7.map.primitives.USSDStringImpl;
import org.restcomm.protocols.ss7.map.primitives.AddressStringImpl;
import org.restcomm.protocols.ss7.map.primitives.DiameterIdentityImpl;
import org.restcomm.protocols.ss7.map.primitives.GSNAddressImpl;
import org.restcomm.protocols.ss7.map.primitives.LMSIImpl;

import org.restcomm.protocols.ss7.map.service.lsm.AddGeographicalInformationImpl;
import org.restcomm.protocols.ss7.map.service.lsm.DeferredLocationEventTypeImpl;
import org.restcomm.protocols.ss7.map.service.lsm.LCSClientNameImpl;
import org.restcomm.protocols.ss7.map.service.lsm.PolygonImpl;
import org.restcomm.protocols.ss7.map.service.lsm.PositioningDataInformationImpl;
import org.restcomm.protocols.ss7.map.service.lsm.VelocityEstimateImpl;
import org.restcomm.protocols.ss7.map.service.lsm.DeferredmtlrDataImpl;
import org.restcomm.protocols.ss7.map.service.lsm.AdditionalNumberImpl;
import org.restcomm.protocols.ss7.map.service.lsm.LCSClientIDImpl;
import org.restcomm.protocols.ss7.map.service.lsm.LCSClientExternalIDImpl;
import org.restcomm.protocols.ss7.map.service.lsm.LCSCodewordImpl;
import org.restcomm.protocols.ss7.map.service.lsm.LCSPrivacyCheckImpl;
import org.restcomm.protocols.ss7.map.service.lsm.UtranPositioningDataInfoImpl;
import org.restcomm.protocols.ss7.map.service.lsm.GeranGANSSpositioningDataImpl;
import org.restcomm.protocols.ss7.map.service.lsm.UtranGANSSpositioningDataImpl;

import org.restcomm.protocols.ss7.map.service.mobility.locationManagement.SupportedLCSCapabilitySetsImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.APNImpl;
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

import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.tools.simulator.level3.MapMan;
import org.restcomm.protocols.ss7.tools.simulator.level3.NumberingPlanMapType;
import org.restcomm.protocols.ss7.tools.simulator.management.TesterHostImpl;

import java.nio.charset.Charset;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class TestLcsServerMan extends TesterBase implements TestLcsServerManMBean, Stoppable, MAPServiceLsmListener {

    private static Logger logger = Logger.getLogger(TestLcsServerMan.class);

    public static String SOURCE_NAME = "TestLcsServerMan";
    private final String name;
    private MapMan mapMan;
    private boolean isStarted = false;
    private int countMapLcsReq = 0;
    private int countMapLcsResp = 0;
    private String currentRequestDef = "";
    private MAPProvider mapProvider;
    private MAPServiceLsm mapServiceLsm;
    private MAPParameterFactory mapParameterFactory;

    public TestLcsServerMan(String name) {
        super(SOURCE_NAME);
        this.name = name;
        this.isStarted = false;
    }

    public boolean start() {

        this.mapProvider = this.mapMan.getMAPStack().getMAPProvider();
        this.mapServiceLsm = mapProvider.getMAPServiceLsm();
        this.mapParameterFactory = mapProvider.getMAPParameterFactory();

        mapServiceLsm.activate();
        mapServiceLsm.addMAPServiceListener(this);
        mapProvider.addMAPDialogListener(this);

        this.testerHost.sendNotif(SOURCE_NAME, "LCS Server has been started", "", Level.INFO);
        isStarted = true;
        this.countMapLcsReq = 0;
        this.countMapLcsResp = 0;
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
        sb.append("<br>Count: countMapLcsReq-");
        sb.append(countMapLcsReq);
        sb.append(", countMapLcsResp-");
        sb.append(countMapLcsResp);
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
        mapProvider.getMAPServiceLsm().removeMAPServiceListener(this);
        mapProvider.removeMAPDialogListener(this);
        this.testerHost.sendNotif(SOURCE_NAME, "LCS Client has been stopped", "", Level.INFO);
    }

    //***************************//
    //**** SRIforLCS methods ***//
    //*************************//
    @Override
    public String performSendRoutingInfoForLCSResponse() {
        if (!isStarted) {
            return "The tester is not started";
        }

        return sendRoutingInfoForLCSResponse();
    }

    public String sendRoutingInfoForLCSResponse() {

        return "sendRoutingInfoForLCSResponse called automatically";
    }

    public void onSendRoutingInfoForLCSRequest(SendRoutingInfoForLCSRequest sendRoutingInforForLCSRequest) {

        logger.debug("\nonSendRoutingInfoForLCSRequest");
        if (!isStarted)
            return;

        this.countMapLcsReq++;

        MAPDialogLsm curDialog = sendRoutingInforForLCSRequest.getMAPDialog();
        long invokeId = sendRoutingInforForLCSRequest.getInvokeId();

        this.testerHost.sendNotif(SOURCE_NAME, "Rcvd: SendRoutingInfoForLCSRequest",
            createSRIforLCSReqData(curDialog.getLocalDialogId(), sendRoutingInforForLCSRequest.getMLCNumber(),
                sendRoutingInforForLCSRequest.getTargetMS()), Level.INFO);

        Random rand = new Random();

        // Set Calling SCCP Address (HLR for SRILCS response)
        curDialog.setLocalAddress(getHLRSCCPAddress("59899170001"));

        String subId = null;
        // Generate MAP errors for specific MSISDNs
        if (sendRoutingInforForLCSRequest.getTargetMS().getMSISDN() != null)
            subId = sendRoutingInforForLCSRequest.getTargetMS().getMSISDN().getAddress();
        else if (sendRoutingInforForLCSRequest.getTargetMS().getIMSI() != null)
            subId = sendRoutingInforForLCSRequest.getTargetMS().getIMSI().getData();
        if (subId.equalsIgnoreCase("99998888")) {
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
            this.testerHost.sendNotif(SOURCE_NAME, "Sent: RejectComponent", createSRIforLCSResData(curDialog.getLocalDialogId(),
                null, null, null), Level.INFO);
            return;
        }
        if (subId.equalsIgnoreCase("99990000")) {
            MAPErrorMessage mapErrorMessageUnauthorizedLCSClient = new MAPErrorMessageUnauthorizedLCSClientImpl();
            try {
                curDialog.sendErrorComponent(invokeId, mapErrorMessageUnauthorizedLCSClient);
                curDialog.close(false);
            } catch (MAPException e) {
                e.printStackTrace();
            }
            logger.debug("\nErrorComponent sent");
            this.testerHost.sendNotif(SOURCE_NAME, "Sent: ErrorComponent", createSRIforLCSResData(curDialog.getLocalDialogId(),
                null, null, null), Level.INFO);
            return;
        }

        try {
            MAPParameterFactoryImpl mapFactory = new MAPParameterFactoryImpl();
            ISDNAddressString msisdnAddress = new ISDNAddressStringImpl(AddressNature.international_number,
                org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan.ISDN, "59899077937");
            SubscriberIdentity msisdn = new SubscriberIdentityImpl(msisdnAddress);
            IMSI imsiImpl;
            SubscriberIdentity imsi, targetMS = null;
            if (sendRoutingInforForLCSRequest.getTargetMS().getIMSI() != null)
                targetMS = msisdn;
            if (sendRoutingInforForLCSRequest.getTargetMS().getMSISDN() != null) {
                msisdnAddress = sendRoutingInforForLCSRequest.getTargetMS().getMSISDN();
                if (msisdnAddress.getAddress().equals("60196229802"))
                    imsiImpl = new IMSIImpl("502153207655206");
                 else if (msisdnAddress.getAddress().equals("60196229803"))
                    imsiImpl = new IMSIImpl("502153100826899");
                else if (msisdnAddress.getAddress().equals("60196229804"))
                    imsiImpl = new IMSIImpl("502153147968442");
                else
                    imsiImpl = new IMSIImpl("748026871012345");
                imsi = new SubscriberIdentityImpl(imsiImpl);
                targetMS = imsi;
            }
            ISDNAddressString mlcNumber = sendRoutingInforForLCSRequest.getMLCNumber();
            String mscAddress = "598991800024";
            ISDNAddressString mscNumber = new ISDNAddressStringImpl(AddressNature.international_number,
                NumberingPlan.ISDN, mscAddress);
            String additionalMcsAddress = "598991800179";
            String sgsnAddress = "598992000077";
            ISDNAddressString additionalMcsNumber = null;
            ISDNAddressString sgsnNumber = null;
            AdditionalNumber additionalNumber = null;
            Boolean gprsNodeIndicator = false;
            int addNumRandom = rand.nextInt(5) + 1;
            switch (addNumRandom) {
                case 1:
                    break;
                case 2:
                    gprsNodeIndicator = true;
                    break;
                case 3:
                    additionalMcsNumber = new ISDNAddressStringImpl(AddressNature.international_number,
                        NumberingPlan.ISDN, additionalMcsAddress);
                    additionalNumber = new AdditionalNumberImpl(additionalMcsNumber, sgsnNumber);
                    break;
                case 4:
                    gprsNodeIndicator = true;
                    sgsnNumber = new ISDNAddressStringImpl(AddressNature.international_number,
                        NumberingPlan.ISDN, sgsnAddress);
                    additionalNumber = new AdditionalNumberImpl(additionalMcsNumber, sgsnNumber);
                    break;
                case 5:
                    additionalMcsNumber = new ISDNAddressStringImpl(AddressNature.international_number,
                        NumberingPlan.ISDN, additionalMcsAddress);
                    additionalNumber = new AdditionalNumberImpl(additionalMcsNumber, sgsnNumber);
                    gprsNodeIndicator = true;
                    break;
                default:
                    additionalNumber = null; // not needed, just for being explicit about the default case
                    gprsNodeIndicator = false; // not needed, just for being explicit about the default case
                    break;
            }

            logger.warn("Additional Number onSendRoutingInfoForLCSRequest : " + additionalNumber);

            byte[] lmsiByte = null;
            int lmsiRandom = rand.nextInt(4) + 1;
            switch (lmsiRandom) {
                case 1:
                    // ﻿char packet_bytes[] = {0x72, 0x02, 0xe9, 0x8c};
                    lmsiByte = new byte[]{114, 2, (byte) 233, (byte) 140};
                    break;
                case 2:
                    // ﻿char packet_bytes[] = {﻿0x71, 0xff, 0xac, 0xce};
                    lmsiByte = new byte[]{113, (byte) 255, (byte) 172, (byte) 206};
                    break;
                case 3:
                    // ﻿char packet_bytes[] = {﻿0x72, 0x02, 0xeb, 0x37};
                    lmsiByte = new byte[]{114, 2, (byte) 235, 55};
                    break;
                case 4:
                    // ﻿char packet_bytes[] = {﻿0x72, 0x02, 0xe7, 0xd5};
                    lmsiByte = new byte[]{114, 2, (byte) 231, (byte) 213};
                    break;
            }
            LMSI lmsi = new LMSIImpl(lmsiByte);
            boolean lcsCapabilitySetRelease98_99 = true;
            boolean lcsCapabilitySetRelease4 = true;
            boolean lcsCapabilitySetRelease5 = true;
            boolean lcsCapabilitySetRelease6 = true;
            boolean lcsCapabilitySetRelease7 = false;
            SupportedLCSCapabilitySets supportedLCSCapabilitySets = new SupportedLCSCapabilitySetsImpl(lcsCapabilitySetRelease98_99, lcsCapabilitySetRelease4,
                lcsCapabilitySetRelease5, lcsCapabilitySetRelease6, lcsCapabilitySetRelease7);
            lcsCapabilitySetRelease7 = true;
            SupportedLCSCapabilitySets additionalLCSCapabilitySets = new SupportedLCSCapabilitySetsImpl(lcsCapabilitySetRelease98_99, lcsCapabilitySetRelease4,
                lcsCapabilitySetRelease5, lcsCapabilitySetRelease6, lcsCapabilitySetRelease7);
            MAPExtensionContainer mapExtensionContainer = null;
            String mmeNameStr = "mmec03.mmeer3000.mme.epc.mnc002.mcc748.3gppnetwork.org";
            byte[] mme = mmeNameStr.getBytes();
            //byte[] mme = {77, 77, 69, 55, 52, 56, 48, 48, 48, 49};
            DiameterIdentity mmeName = new DiameterIdentityImpl(mme);
            String aaaServerNameStr = "aaa04.aaa3000.aaa.epc.mnc002.mcc748.3gppnetwork.org";
            byte[] aaa = aaaServerNameStr.getBytes();
            //byte[] aaa = {65, 65, 65, 55, 52, 56, 48, 48, 48, 49, 53, 48};
            DiameterIdentity aaaServerName = new DiameterIdentityImpl(aaa);
            GSNAddressAddressType gsnAddressAddressType = GSNAddressAddressType.IPv4;
            byte[] visitedGmlcAddressData = {(byte) 180, 53, (byte) 105, 48};
            GSNAddress vGmlcAddress = new GSNAddressImpl(gsnAddressAddressType, visitedGmlcAddressData);
            byte[] homeGmlcAddressData = {(byte) 181, (byte) 104, (byte) 201, 3};
            GSNAddress hGmlcAddress = new GSNAddressImpl(gsnAddressAddressType, homeGmlcAddressData);
            byte[] pivacyProfileRegisterAddressData = {(byte) 181, (byte) 104, 97, 21};
            GSNAddress pprAddress = new GSNAddressImpl(gsnAddressAddressType, pivacyProfileRegisterAddressData);
            byte[] addVGmlcAddressData = {(byte) 181, 53, (byte) 105, 74};
            GSNAddress additionalVGmlcAddress = new GSNAddressImpl(gsnAddressAddressType, addVGmlcAddressData);

            LCSLocationInfo lcsLocationInfo = mapFactory.createLCSLocationInfo(mscNumber, lmsi, mapExtensionContainer, gprsNodeIndicator,
                additionalNumber, supportedLCSCapabilitySets, additionalLCSCapabilitySets, mmeName, aaaServerName);

            int sriLcsResponseDelay = rand.nextInt(150);
            try {
                Thread.sleep(sriLcsResponseDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            curDialog.addSendRoutingInfoForLCSResponse(sendRoutingInforForLCSRequest.getInvokeId(),
                targetMS, lcsLocationInfo, mapExtensionContainer, vGmlcAddress, hGmlcAddress, pprAddress, additionalVGmlcAddress);

            logger.debug("\nset addSendRoutingForLCSResponse");
            curDialog.close(false);
            logger.debug("\naddSendRoutingForLCSResponse sent");
            this.countMapLcsResp++;

            this.testerHost.sendNotif(SOURCE_NAME, "Sent: SendRoutingForLCSResponse",
                createSRIforLCSResData(curDialog.getLocalDialogId(), mscNumber, targetMS, additionalNumber), Level.INFO);

        } catch (MAPException me) {
            logger.debug("Failed building SendRoutingInfoForLCS response " + me.toString());
        }

    }

    private String createSRIforLCSReqData(long dialogId, ISDNAddressString mlcNumber, SubscriberIdentity targetMS) {
        StringBuilder sb = new StringBuilder();
        sb.append("dialogId=");
        sb.append(dialogId);
        if (mlcNumber != null) {
            sb.append(", mlcNumber=\"");
            sb.append(mlcNumber.getAddress());
        }
        if (targetMS != null) {
            if (targetMS.getMSISDN() != null) {
                sb.append(", MSISDN=\"");
                sb.append(targetMS.getMSISDN().getAddress());
            }
            if (targetMS.getIMSI() != null) {
                sb.append(", IMSI=\"");
                sb.append(new String(targetMS.getIMSI().getData().getBytes()));
            }
        }
        sb.append("\"");
        return sb.toString();
    }


    private String createSRIforLCSResData(long dialogId, ISDNAddressString networkNodeNumber, SubscriberIdentity targetMS,
                                          AdditionalNumber additionalNumber) {
        StringBuilder sb = new StringBuilder();
        sb.append("dialogId=");
        sb.append(dialogId);
        if (networkNodeNumber != null) {
            sb.append(", networkNodeNumber=\"");
            sb.append(networkNodeNumber.getAddress());
        }
        if (additionalNumber != null) {
            if (additionalNumber.getMSCNumber() != null) {
                sb.append(", Additional MSC Number=\"");
                sb.append(targetMS.getMSISDN());
            }
            if (additionalNumber.getSGSNNumber() != null) {
                sb.append(", Additional SGSN Number=\"");
                sb.append(targetMS.getIMSI());
            }
        }
        if (targetMS != null) {
            if (targetMS.getMSISDN() != null) {
                sb.append(", MSISDN=\"");
                sb.append(targetMS.getMSISDN());
            }
            if (targetMS.getIMSI() != null) {
                sb.append(", IMSI=\"");
                sb.append(targetMS.getIMSI());
            }
        }
        sb.append("\"");
        return sb.toString();
    }

    public void onSendRoutingInfoForLCSResponse(SendRoutingInfoForLCSResponse sendRoutingInforForLCSResponseIndication) {
        logger.debug("\nonSendRoutingInfoForLCSResponse");
        this.countMapLcsResp++;
        MAPDialogLsm curDialog = sendRoutingInforForLCSResponseIndication.getMAPDialog();
        this.testerHost.sendNotif(SOURCE_NAME,
            "Rcvd: SendRoutingInfoForLCSResponse", this
                .createSRIforLCSResData(curDialog.getLocalDialogId(),
                    sendRoutingInforForLCSResponseIndication.getLCSLocationInfo().getNetworkNodeNumber(),
                    sendRoutingInforForLCSResponseIndication.getTargetMS(),
                    sendRoutingInforForLCSResponseIndication.getLCSLocationInfo().getAdditionalNumber()),
            Level.INFO);

    }

    //*********************//
    //**** PSL methods ***//
    //*******************//
    @Override
    public void onProvideSubscriberLocationRequest(ProvideSubscriberLocationRequest provideSubscriberLocationRequest) {

        logger.debug("\nonProvideSubscriberLocationRequest");
        if (!isStarted)
            return;

        MAPDialogLsm curDialog = provideSubscriberLocationRequest.getMAPDialog();
        long invokeId = provideSubscriberLocationRequest.getInvokeId();

        // Set Calling SCCP Address (MSC for PSL response)
        curDialog.setLocalAddress(getMSCSCCPAddress("59899180071"));

        MAPParameterFactoryImpl mapFactory = new MAPParameterFactoryImpl();
        int cbsDataCodingSchemeCode = 15;
        CBSDataCodingScheme cbsDataCodingScheme = new CBSDataCodingSchemeImpl(cbsDataCodingSchemeCode);
        String ussdLcsString = "3";
        Charset gsm8Charset = Charset.defaultCharset();
        ISDNAddressString externalAddress = new ISDNAddressStringImpl(AddressNature.international_number,
            NumberingPlan.ISDN, "444567");
        ISDNAddressString msisdn;
        MAPExtensionContainer mapExtensionContainer = null;
        LCSClientExternalID lcsClientExternalID = new LCSClientExternalIDImpl(externalAddress, mapExtensionContainer);
        LCSClientInternalID lcsClientInternalID = LCSClientInternalID.oandMHPLMN;
        USSDString ussdString = null;
        Boolean saiPresent;
        try {
            ussdString = new USSDStringImpl(ussdLcsString, cbsDataCodingScheme, gsm8Charset);
        } catch (MAPException e) {
            e.printStackTrace();
        }
        LCSFormatIndicator lcsFormatIndicator = LCSFormatIndicator.url;
        PrivacyCheckRelatedAction callSessionUnrelated = PrivacyCheckRelatedAction.allowedWithNotification;
        PrivacyCheckRelatedAction callSessionRelated = PrivacyCheckRelatedAction.allowedIfNoResponse;

        LCSPrivacyCheck lcsPrivacyCheck;
        LCSClientID lcsClientID;
        LCSCodeword lcsCodeword;
        IMSI imsi;
        IMEI imei;
        Integer lcsReferenceNumber = null;

        if (provideSubscriberLocationRequest.getIMSI().getData().equals("502153207655206")) {
            try {
                Thread.sleep(15000);
                return;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (provideSubscriberLocationRequest.getIMSI().getData().equals("502153100826899")) {
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
                createPSLResponse(curDialog.getLocalDialogId(), null, null), Level.INFO);
            return;
        } else if (provideSubscriberLocationRequest.getIMSI().getData().equals("502153147968442")) {
            MAPErrorMessage mapErrorMessage1 = new MAPErrorMessageFacilityNotSupImpl();
            try {
                curDialog.sendErrorComponent(invokeId, mapErrorMessage1);
                curDialog.close(false);
            } catch (MAPException e) {
                e.printStackTrace();
            }
            logger.debug("\nErrorComponent sent");
            this.testerHost.sendNotif(SOURCE_NAME, "Sent: ErrorComponent",
                createPSLResponse(curDialog.getLocalDialogId(), null, null), Level.INFO);
            return;
        }

        Random rand = new Random();

        if (provideSubscriberLocationRequest.getLCSClientID() == null) {
            String clientName = "545248";
            LCSClientName lcsClientName = new LCSClientNameImpl(cbsDataCodingScheme, ussdString, lcsFormatIndicator);
            AddressString lcsClientDialedByMS = new AddressStringImpl(AddressNature.international_number, NumberingPlan.ISDN, clientName);
            String apnStr = "restcomm.org";
            APN lcsAPN = null;
            try {
                lcsAPN = new APNImpl(apnStr);
            } catch (MAPException e) {
                e.printStackTrace();
            }
            lcsClientID = new LCSClientIDImpl(LCSClientType.valueAddedServices, lcsClientExternalID, lcsClientInternalID, lcsClientName, lcsClientDialedByMS, lcsAPN, null);
        } else {
            lcsClientID = provideSubscriberLocationRequest.getLCSClientID();
        }

        if (provideSubscriberLocationRequest.getLCSCodeword() == null) {
            lcsCodeword = new LCSCodewordImpl(cbsDataCodingScheme, ussdString);
        } else {
            lcsCodeword = provideSubscriberLocationRequest.getLCSCodeword();
        }

        if (provideSubscriberLocationRequest.getIMSI() == null) {
            imsi = new IMSIImpl("748026871012345");
        } else {
            imsi = provideSubscriberLocationRequest.getIMSI();
        }

        if (provideSubscriberLocationRequest.getMSISDN() == null) {
            msisdn = new ISDNAddressStringImpl(AddressNature.international_number,
                NumberingPlan.ISDN, "59899077937");
        } else {
            msisdn = provideSubscriberLocationRequest.getMSISDN();
        }

        if (provideSubscriberLocationRequest.getIMEI() == null) {
            imei = new IMEIImpl("01171400466105");
        } else {
            imei = provideSubscriberLocationRequest.getIMEI();
        }

        if (provideSubscriberLocationRequest.getLCSPrivacyCheck() == null) {
            lcsPrivacyCheck = new LCSPrivacyCheckImpl(callSessionUnrelated, callSessionRelated);
        } else {
            lcsPrivacyCheck = provideSubscriberLocationRequest.getLCSPrivacyCheck();
        }

        if (provideSubscriberLocationRequest.getLCSReferenceNumber() != null) {
            setLCSReferenceNumber(provideSubscriberLocationRequest.getLCSReferenceNumber());
            lcsReferenceNumber = provideSubscriberLocationRequest.getLCSReferenceNumber();
        }

        logger.info("\n\nDialog Id=" + curDialog);
        logger.info("\n\nLocal Dialog Id=" + curDialog.getLocalDialogId());
        logger.info("\n\nLocation Type=" + provideSubscriberLocationRequest.getLocationType());
        logger.info("\n\nMLC Number=" + provideSubscriberLocationRequest.getMlcNumber());
        logger.info("\n\nLCS Client ID=" + lcsClientID);
        logger.info("\n\nIMSI=" + imsi);
        logger.info("\n\nMSISDN=" + msisdn);
        logger.info("\n\nLMSI=" + provideSubscriberLocationRequest.getLMSI());
        logger.info("\n\nLCS Priority=" + provideSubscriberLocationRequest.getLCSPriority());
        logger.info("\n\nLCS QoS=" + provideSubscriberLocationRequest.getLCSQoS());
        logger.info("\n\nIMEI=" + imei);
        logger.info("\n\nLCS Reference Number=" + lcsReferenceNumber);
        logger.info("\n\nLCS Service Type ID=" + provideSubscriberLocationRequest.getLCSServiceTypeID());
        logger.info("\n\nLCS Codeword=" + lcsCodeword);
        logger.info("\n\nLCS Privacy Check=" + lcsPrivacyCheck);
        logger.info("\n\nArea Event Info=" + provideSubscriberLocationRequest.getAreaEventInfo());
        logger.info("\n\nH-GMLC Address=" + provideSubscriberLocationRequest.getHGMLCAddress());
        logger.info("\n\nMO LR Short Circuit Indicator=" + provideSubscriberLocationRequest.getMoLrShortCircuitIndicator());
        logger.info("\n\nPeriodic LDR Info=" + provideSubscriberLocationRequest.getPeriodicLDRInfo());

        this.testerHost.sendNotif(SOURCE_NAME, "Rcvd: ProvideSubscriberLocationRequest",
            createPSLRequestData(curDialog.getLocalDialogId(),
                provideSubscriberLocationRequest.getLocationType(),
                provideSubscriberLocationRequest.getMlcNumber(),
                lcsClientID,
                imsi,
                msisdn,
                provideSubscriberLocationRequest.getLMSI(),
                provideSubscriberLocationRequest.getLCSPriority(),
                provideSubscriberLocationRequest.getLCSQoS(),
                imei,
                lcsReferenceNumber,
                provideSubscriberLocationRequest.getLCSServiceTypeID(),
                lcsCodeword,
                lcsPrivacyCheck,
                provideSubscriberLocationRequest.getAreaEventInfo(),
                provideSubscriberLocationRequest.getHGMLCAddress(),
                provideSubscriberLocationRequest.getMoLrShortCircuitIndicator(),
                provideSubscriberLocationRequest.getPeriodicLDRInfo()
            ), Level.INFO);

        byte[] geranPosInfo = {0, 3};
        PositioningDataInformation geranPositioningData = new PositioningDataInformationImpl(geranPosInfo);
        Integer ageOfLocationEstimate = 0;
        AddGeographicalInformation additionalLocationEstimate = null;
        MAPExtensionContainer extensionContainer = null;
        boolean deferredMTLRResponseIndicator = true;
        byte[] cidOrSaiFixedLength = new BigInteger("34970120704321", 16).toByteArray();
        CellGlobalIdOrServiceAreaIdFixedLength cellGlobalIdOrServiceAreaIdFixedLength = new CellGlobalIdOrServiceAreaIdFixedLengthImpl(cidOrSaiFixedLength);
        CellGlobalIdOrServiceAreaIdOrLAI cellGlobalIdOrServiceAreaIdOrLAI = new CellGlobalIdOrServiceAreaIdOrLAIImpl(cellGlobalIdOrServiceAreaIdFixedLength);
        if (this.countMapLcsReq % 2 == 0)
            saiPresent = false; // set saiPresent to false if this ATI request is even since test started
        else
            saiPresent = true; // set saiPresent to true if this ATI request is odd since test started
        ISDNAddressString mscNumber = new ISDNAddressStringImpl(AddressNature.international_number,
            NumberingPlan.ISDN, "598991800024");
        ISDNAddressString sgsnNumber = new ISDNAddressStringImpl(AddressNature.international_number,
            NumberingPlan.ISDN, "598992000077");
        byte[] utranData = {57, 51, 51, 54, 48, 49};
        UtranPositioningDataInfo utranPositioningDataInfo = new UtranPositioningDataInfoImpl(utranData);
        AccuracyFulfilmentIndicator accuracyFulfilmentIndicator = AccuracyFulfilmentIndicator.requestedAccuracyFulfilled;
        VelocityType velocityType = VelocityType.HorizontalWithVerticalVelocityAndUncertainty;
        int horizontalSpeed = 101;
        int bearing = 3;
        int verticalSpeed = 2;
        int uncertaintyHorizontalSpeed = 5;
        int uncertaintyVerticalSpeed = 1;
        VelocityEstimate velocityEstimate = null;
        try {
            velocityEstimate = new VelocityEstimateImpl(velocityType, horizontalSpeed, bearing, verticalSpeed, uncertaintyHorizontalSpeed, uncertaintyVerticalSpeed);
        } catch (MAPException e) {
            e.printStackTrace();
        }
        boolean moLrShortCircuitIndicator = true;
        byte[] gGanss = {56, 50, 48, 49, 51, 53};
        GeranGANSSpositioningData geranGANSSpositioningData = new GeranGANSSpositioningDataImpl(gGanss);
        byte[] uGanss = {57, 51, 51, 54, 48, 48};
        UtranGANSSpositioningData utranGANSSpositioningData = new UtranGANSSpositioningDataImpl(uGanss);
        ServingNodeAddress targetServingNodeForHandover = mapFactory.createServingNodeAddressMscNumber(mscNumber);

        ExtGeographicalInformation locationEstimate = null;
        TypeOfShape typeOfShape = null, additionalTypeOfShape = null;
        double latitude, longitude, uncertainty, uncertaintySemiMajorAxis, uncertaintySemiMinorAxis, angleOfMajorAxis, uncertaintyAltitude, uncertaintyRadius,
            offsetAngle, includedAngle;
        int confidence, altitude, innerRadius;
        EllipsoidPoint ellipsoidPoint1, ellipsoidPoint2, ellipsoidPoint3, ellipsoidPoint4, ellipsoidPoint5, ellipsoidPoint6, ellipsoidPoint7,
            ellipsoidPoint8, ellipsoidPoint9, ellipsoidPoint10, ellipsoidPoint11, ellipsoidPoint12, ellipsoidPoint13, ellipsoidPoint14, ellipsoidPoint15;
        // 3 <= numberOfPoints <= 15
        int typeOfShapeRandomOption = rand.nextInt(6) + 1;
        switch (typeOfShapeRandomOption) {
            case 1:
                typeOfShape = TypeOfShape.EllipsoidPoint;
                latitude = 34.909744;
                longitude = -56.146317;
                try {
                    locationEstimate = mapParameterFactory.createExtGeographicalInformation_EllipsoidPoint(latitude, longitude);
                } catch (MAPException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                typeOfShape = TypeOfShape.EllipsoidPointWithUncertaintyCircle;
                latitude = -34.910349;
                longitude = -56.149832;
                uncertainty = 5.1;
                try {
                    locationEstimate = mapParameterFactory.createExtGeographicalInformation_EllipsoidPointWithUncertaintyCircle(latitude, longitude, uncertainty);
                } catch (MAPException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                typeOfShape = TypeOfShape.EllipsoidPointWithUncertaintyEllipse;
                latitude = -34.905624;
                longitude = -55.042191;
                uncertaintySemiMajorAxis = 21.2;
                uncertaintySemiMinorAxis = 10.4;
                angleOfMajorAxis = 30.0; // orientation of major axis
                confidence = 1;
                try {
                    locationEstimate = mapParameterFactory.createExtGeographicalInformation_EllipsoidPointWithUncertaintyEllipse(latitude, longitude,
                        uncertaintySemiMajorAxis, uncertaintySemiMinorAxis, angleOfMajorAxis, confidence);
                } catch (MAPException e) {
                    e.printStackTrace();
                }
                break;
            case 4:
                typeOfShape = TypeOfShape.EllipsoidPointWithAltitudeAndUncertaintyEllipsoid;
                latitude = -34.956436;
                longitude = -54.937820;
                altitude = 570;
                uncertaintySemiMajorAxis = 25.4;
                uncertaintySemiMinorAxis = 12.1;
                angleOfMajorAxis = 30.2; // orientation of major axis
                uncertaintyAltitude = 80.1;
                confidence = 5;
                try {
                    locationEstimate = mapParameterFactory.createExtGeographicalInformation_EllipsoidPointWithAltitudeAndUncertaintyEllipsoid(latitude,
                        longitude, uncertaintySemiMajorAxis, uncertaintySemiMinorAxis, angleOfMajorAxis, confidence, altitude, uncertaintyAltitude);
                } catch (MAPException e) {
                    e.printStackTrace();
                }
                break;
            case 5:
                typeOfShape = TypeOfShape.EllipsoidArc;
                latitude = -34.939956;
                longitude = -54.914474;
                innerRadius = 5;
                uncertaintyRadius = 1.50;
                offsetAngle = 20.0;
                includedAngle = 20.0;
                confidence = 2;
                try {
                    locationEstimate = mapParameterFactory.createExtGeographicalInformation_EllipsoidArc(latitude, longitude, innerRadius,
                        uncertaintyRadius, offsetAngle, includedAngle, confidence);
                } catch (MAPException e) {
                    e.printStackTrace();
                }
                break;
            case 6:
                typeOfShape = TypeOfShape.Polygon;
                latitude = 0.0;
                longitude = 0.0;
                try {
                    locationEstimate = mapParameterFactory.createExtGeographicalInformation_EllipsoidPoint(latitude, longitude);
                } catch (MAPException e) {
                    e.printStackTrace();
                }
                break;
        }

        int additionalLocationEstimateRandomOption = rand.nextInt(6) + 1;
        if (typeOfShape == TypeOfShape.Polygon) {
            additionalTypeOfShape = TypeOfShape.Polygon;
            ellipsoidPoint1 = new EllipsoidPoint(-2.907010, 70.778014);
            ellipsoidPoint2 = new EllipsoidPoint(-3.017238, 70.708922);
            ellipsoidPoint3 = new EllipsoidPoint(-2.941387, 70.432091);
            ellipsoidPoint4 = new EllipsoidPoint(-3.040019, 70.681903);
            ellipsoidPoint5 = new EllipsoidPoint(-3.045001, 70.700109);
            ellipsoidPoint6 = new EllipsoidPoint(-2.989001, 71.000004);
            EllipsoidPoint[] ellipsoidPoints = {ellipsoidPoint1, ellipsoidPoint2, ellipsoidPoint3, ellipsoidPoint4, ellipsoidPoint5, ellipsoidPoint6};

            /*  char packet_bytes[] = { 0x53, 0x29, 0xea, 0x8a, 0x37, 0x43, 0x11, 0x29, 0xea, 0x88, 0x37, 0x43, 0x03, 0x29, 0xea, 0x00, 0x37, 0x43, 0x18};   */
            byte[] polygonData1 = { 83,
                                    41, (byte) 234, (byte) 138, 55, 67, 17,
                                    41, (byte) 234, (byte) 136, 55, 67, 3,
                                    41, (byte) 234, 0, 55, 67, 24};

            /*  char packet_bytes[] = { 0x53, 0x29, 0xea, 0x8a, 0x37, 0x43, 0x11, 0x29, 0xea, 0x88, 0x37, 0x43, 0x03, 0x29, 0xea, 0x00, 0x37, 0x43, 0x18};  */
            byte[] polygonData2 = { 83,
                                    44, 29, (byte) 188, 53, (byte) 227, (byte) 135,
                                    44, 29, (byte) 193, 53, (byte) 227, (byte) 130,
                                    44, 29, (byte) 190, 53, (byte) 227, 123};

            /* char packet_bytes[] =  { 0x53, 0x24, 0xa7, 0x3c, 0x34, 0x25, 0x00, 0x24, 0xa7, 0x31, 0x34, 0x24, 0xff, 0x24, 0xa7, 0x32,0x34, 0x25, 0x00}; */
            byte[] polygonData3 = { 83,
                                    36, (byte) 167, 60, 52, 37, 0,
                                    36, (byte) 167, 49, 52, 36, (byte) 255,
                                    36, (byte) 167, 50, 52, 37, 0};

            /* char packet_bytes[] =  { 0x53, 0x24, 0x7c, 0xa3, 0x3b, 0x31, 0x70, 0x24, 0x7e, 0x07, 0x3b, 0x31, 0x8a, 0x24, 0x7f, 0xe0, 0x3b, 0x31, 0x48}; */
            byte[] polygonData4 = { 83,
                                    36, 124, (byte) 163, 59, 49, 112,
                                    36, 126, 7, 59, 49, (byte) 138,
                                    36, 127, (byte) 224, 59, 49, 72};

            /* char packet_bytes[] =  { 0x53, 0x25, 0xe5, 0xb3, 0x34, 0x42, 0xd3, 0x25, 0xe6, 0x40, 0x34, 0x43, 0x7c, 0x25, 0xe6, 0x83, 0x34, 0x43, 0x79
                                        0x25, 0xe6, 0x84, 0x34, 0x43, 0x7d};  */
            byte[] polygonData5 = { 84,
                                    37, (byte) 229, (byte) 179, 52, 66, (byte) 211,
                                    37, (byte) 230, 64, 52, 67, 124,
                                    37, (byte) 230, (byte) 131, 52, 67, 121,
                                    37, (byte) 230, (byte) 132, 52, 67, 125};

            Polygon polygon1, polygon2, polygon3, polygon4, polygon5, polygon6 = new PolygonImpl();

            try {
                switch (additionalLocationEstimateRandomOption) {
                    case 1:
                        polygon1 = new PolygonImpl(polygonData1);
                        additionalLocationEstimate = new AddGeographicalInformationImpl(polygon1.getData());
                        break;
                    case 2:
                        polygon2 = new PolygonImpl(polygonData2);
                        additionalLocationEstimate = new AddGeographicalInformationImpl(polygon2.getData());
                        break;
                    case 3:
                        polygon3 = new PolygonImpl(polygonData3);
                        additionalLocationEstimate = new AddGeographicalInformationImpl(polygon3.getData());
                        break;
                    case 4:
                        polygon4 = new PolygonImpl(polygonData4);
                        additionalLocationEstimate = new AddGeographicalInformationImpl(polygon4.getData());
                        break;
                    case 5:
                        polygon5 = new PolygonImpl(polygonData5);
                        additionalLocationEstimate = new AddGeographicalInformationImpl(polygon5.getData());
                        break;
                    case 6:
                        ((PolygonImpl) polygon6).setData(ellipsoidPoints);
                        additionalLocationEstimate = new AddGeographicalInformationImpl(polygon6.getData());
                        break;
                }
            } catch (MAPException e) {
                e.printStackTrace();
            }
        }

        try {
            //locationEstimate = new ExtGeographicalInformationImpl(typeOfShape, latitude, longitude, uncertainty, uncertaintySemiMajorAxis, uncertaintySemiMinorAxis, angleOfMajorAxis, confidence, altitude, uncertaintyAltitude, innerRadius, uncertaintyRadius, offsetAngle, includedAngle);

            int pslResponse = rand.nextInt(10) + 1;
            switch (pslResponse) {
                case 1:
                    delayResponse(rand.nextInt(500));
                    break;
                case 2:
                    delayResponse(rand.nextInt(1000));
                    break;
                case 3:
                    delayResponse(rand.nextInt(2000));
                    break;
                case 4:
                    delayResponse(rand.nextInt(3000));
                    break;
                case 5:
                    delayResponse(rand.nextInt(4000));
                    break;
                case 6:
                    delayResponse(rand.nextInt(4500));
                    break;
                case 7:
                    delayResponse(rand.nextInt(5000));
                    break;
                case 8:
                    delayResponse(rand.nextInt(6500));
                    break;
                case 9:
                    delayResponse(rand.nextInt(10000));
                    break;
                case 10:
                    delayResponse(35000);
                    break;
                default:
                    delayResponse(500);
                    break;
            }

            curDialog.addProvideSubscriberLocationResponse(
                provideSubscriberLocationRequest.getInvokeId(),
                locationEstimate,
                geranPositioningData,
                utranPositioningDataInfo,
                ageOfLocationEstimate,
                additionalLocationEstimate,
                extensionContainer,
                deferredMTLRResponseIndicator,
                cellGlobalIdOrServiceAreaIdOrLAI,
                saiPresent,
                accuracyFulfilmentIndicator,
                velocityEstimate,
                moLrShortCircuitIndicator,
                geranGANSSpositioningData,
                utranGANSSpositioningData,
                targetServingNodeForHandover);

            logger.debug("\nset addProvideSubscriberLocationResponse");
            curDialog.close(false);
            logger.debug("\naddProvideSubscriberLocationResponse sent");
            this.countMapLcsResp++;

            this.testerHost.sendNotif(SOURCE_NAME, "Sent: ProvideSubscriberLocationResponse", createPSLResponse(curDialog.getLocalDialogId(),
                locationEstimate, lcsReferenceNumber), Level.INFO);

        } catch (MAPException me) {
            logger.debug("Exception on addProvideSubscriberLocationResponse " + me.toString());
        }
    }

    private void delayResponse(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String createPSLResponse(long dialogId, ExtGeographicalInformation locationEstimate, Integer lcsReferenceNumber) {

        StringBuilder sb = new StringBuilder();
        sb.append("dialogId=");
        sb.append(dialogId).append("\",\n ");
        sb.append("locationEstimate=\"");
        sb.append(locationEstimate).append("\"");
        sb.append(locationEstimate).append(", ");
        if (locationEstimate.getTypeOfShape() != null) {
            sb.append("\", typeOfShape=\"");
            sb.append(locationEstimate.getTypeOfShape()).append(", ");
        }
        if (locationEstimate.getLatitude() > -90 && locationEstimate.getLatitude() < 90) {
            sb.append("\", latitude=\"");
            sb.append(locationEstimate.getLatitude()).append(", ");
        }
        if (locationEstimate.getLongitude() > -180 && locationEstimate.getLongitude() < 180) {
            sb.append("\", longitude=\"");
            sb.append(locationEstimate.getLongitude()).append(", ");
        }
        if (locationEstimate.getUncertainty() >= 0 && locationEstimate.getUncertainty() < 128) {
            sb.append("\", uncertainty=\"");
            sb.append(locationEstimate.getUncertainty()).append(", ");
        }
        if (locationEstimate.getAltitude() > Integer.MIN_VALUE && locationEstimate.getAltitude() < Integer.MAX_VALUE) {
            sb.append("\", altitude=\"");
            sb.append(locationEstimate.getAltitude()).append(", ");
        }
        if (locationEstimate.getUncertaintyAltitude() > Double.MIN_VALUE && locationEstimate.getUncertaintyAltitude() < Double.MAX_VALUE) {
            sb.append("\", uncertaintyAltitude=\"");
            sb.append(locationEstimate.getUncertaintyAltitude()).append(", ");
        }
        if (locationEstimate.getConfidence() > Integer.MIN_VALUE && locationEstimate.getConfidence() < Integer.MAX_VALUE) {
            sb.append("\", confidence=\"");
            sb.append(locationEstimate.getConfidence()).append(", ");
        }
        if (locationEstimate.getInnerRadius() > Integer.MIN_VALUE && locationEstimate.getInnerRadius() < Integer.MAX_VALUE) {
            sb.append("\", innerRadius=\"");
            sb.append(locationEstimate.getInnerRadius()).append(", ");
        }
        if (locationEstimate.getUncertaintyRadius() > Double.MIN_VALUE && locationEstimate.getUncertaintyRadius() < Double.MAX_VALUE) {
            sb.append("\", uncertaintyRadius=\"");
            sb.append(locationEstimate.getUncertaintyRadius()).append(", ");
        }
        if (locationEstimate.getUncertaintySemiMajorAxis() > Double.MIN_VALUE && locationEstimate.getUncertaintySemiMajorAxis() < Double.MAX_VALUE) {
            sb.append("\", uncertaintySemiMajorAxis=\"");
            sb.append(locationEstimate.getUncertaintySemiMajorAxis()).append(", ");
        }
        if (locationEstimate.getUncertaintySemiMinorAxis() > Double.MIN_VALUE && locationEstimate.getUncertaintySemiMinorAxis() < Double.MAX_VALUE) {
            sb.append("\", uncertaintySemiMinorAxis=\"");
            sb.append(locationEstimate.getUncertaintySemiMinorAxis()).append(", ");
        }
        if (locationEstimate.getAngleOfMajorAxis() > Double.MIN_VALUE && locationEstimate.getAngleOfMajorAxis() < Double.MAX_VALUE) {
            sb.append("\", angleOfMajorAxis=\"");
            sb.append(locationEstimate.getAngleOfMajorAxis()).append(", ");
        }
        if (locationEstimate.getOffsetAngle() > Double.MIN_VALUE && locationEstimate.getOffsetAngle() < Double.MAX_VALUE) {
            sb.append("\", offsetAngle=\"");
            sb.append(locationEstimate.getOffsetAngle()).append(", ");
        }
        if (locationEstimate.getIncludedAngle() > Double.MIN_VALUE && locationEstimate.getIncludedAngle() < Double.MAX_VALUE) {
            sb.append("\", includedAngle=\"");
            sb.append(locationEstimate.getIncludedAngle());
        }
        if (lcsReferenceNumber != null) {
            sb.append("\", lcsReferenceNumber=\"");
            sb.append(lcsReferenceNumber);
        }
        return sb.toString();
    }

    private String createPSLRequestData(long dialogId, LocationType locationType, ISDNAddressString mlcNumber, LCSClientID lcsClientID, IMSI imsi,
                                        ISDNAddressString msisdn, LMSI lmsi, LCSPriority lcsPriority, LCSQoS lcsQoS, IMEI imei, Integer lcsReferenceNumber,
                                        Integer lcsServiceTypeID, LCSCodeword lcsCodeword, LCSPrivacyCheck lcsPrivacyCheck, AreaEventInfo areaEventInfo,
                                        GSNAddress hgmlcAddress, boolean moLrShortCircuitIndicator, PeriodicLDRInfo periodicLDRInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append("dialogId=");
        sb.append(dialogId).append("\",\n ");
        sb.append("locationType=\"");
        sb.append(locationType).append("\",\n ");
        if (locationType.getLocationEstimateType() != null) {
            sb.append("locationEstimateType=\"");
            sb.append(locationType.getLocationEstimateType().getType()).append("\",\n ");
        }
        if (locationType.getDeferredLocationEventType() != null) {
            sb.append("deferredLocationEventType=\"");
            sb.append(locationType.getDeferredLocationEventType()).append("\",\n ");
        }
        if (mlcNumber.getAddress() != null) {
            sb.append("mlcNumber=\"");
            sb.append(mlcNumber.getAddress()).append("\",\n ");
        }
        sb.append("lcsClientID=\"");
        sb.append(lcsClientID).append("\",\n ");
        if (imsi != null) {
            sb.append("IMSI=\"");
            sb.append(imsi.getData()).append("\",\n ");
        }
        if (msisdn != null) {
            sb.append("MSISDIN=\"");
            sb.append(msisdn.getAddress()).append("\",\n ");
        }
        sb.append("LMSI=\"");
        sb.append(lmsi).append("\",\n ");
        sb.append("lcsPriority=\"");
        sb.append(lcsPriority).append("\",\n ");
        sb.append("lcsQos=\"");
        sb.append(lcsQoS).append("\",\n ");
        if (lcsQoS != null) {
            sb.append("lcsQosHorizontalAccuracy=\"");
            if (lcsQoS.getHorizontalAccuracy() != null)
                sb.append(lcsQoS.getHorizontalAccuracy().intValue()).append("\",\n ");
            sb.append("lcsQosVerticalAccuracy=\"");
            if (lcsQoS.getVerticalAccuracy() != null)
                sb.append(lcsQoS.getVerticalAccuracy().intValue()).append("\",\n ");
            sb.append("lcsQosResponseTimeCategory=\"");
            if (lcsQoS.getResponseTime() != null)
                sb.append(lcsQoS.getResponseTime().getResponseTimeCategory()).append("\",\n ");
            sb.append("lcsQosVerticalCoordinateRequest=\"");
            if (lcsQoS.getVerticalCoordinateRequest())
                sb.append(lcsQoS.getVerticalCoordinateRequest()).append("\",\n ");
        }
        sb.append("IMEI=\"");
        sb.append(imei).append("\",\n ");
        sb.append("lcsReferenceNumber=\"");
        sb.append(lcsReferenceNumber).append("\",\n ");
        sb.append("lcsServiceTypeID=\"");
        sb.append(lcsServiceTypeID).append("\",\n ");
        sb.append("lcsCodeword=\"");
        sb.append(lcsCodeword).append("\",\n ");
        sb.append("lcsPrivacyCheck=\"");
        sb.append(lcsPrivacyCheck).append("\",\n ");
        sb.append("areaEventInfo=\"");
        sb.append(areaEventInfo).append("\",\n ");
        sb.append("hgmlcAddress=\"");
        sb.append(hgmlcAddress).append("\",\n ");
        sb.append("moLrShortCircuitIndicator=\"");
        sb.append(moLrShortCircuitIndicator).append("\",\n ");
        sb.append("periodicLDRInfo=\"");
        sb.append(periodicLDRInfo);
        return sb.toString();
    }

    public void onProvideSubscriberLocationResponse(
        ProvideSubscriberLocationResponse provideSubscriberLocationResponse) {

        logger.debug("onProvideSubscriberLocationResponse");

        MAPDialogLsm curDialog = provideSubscriberLocationResponse.getMAPDialog();

        this.countMapLcsResp++;
        this.testerHost.sendNotif(SOURCE_NAME,
            "Rcvd: ProvideSubscriberLocationResponse", this.createPSLResponse(curDialog.getLocalDialogId(),
                provideSubscriberLocationResponse.getLocationEstimate(), null), Level.INFO);
    }

    //*********************//
    //**** SLR methods ***//
    //*******************//
    @Override
    public String performSubscriberLocationReportRequest(Boolean refNum) {
        if (!isStarted) {
            return "The tester is not started";
        }

        if (refNum)
            return subscriberLocationReportRequest();
        else
            return subscriberLocationReportRequestNullRefNum();
    }

    private String subscriberLocationReportRequest() {
        if (mapProvider == null) {
            return "mapProvider is null";
        }

        try {
            Random rand = new Random();

            // LSM dialog creation
            MAPApplicationContext appCnt = MAPApplicationContext.getInstance(MAPApplicationContextName.locationSvcEnquiryContext,
                MAPApplicationContextVersion.version3);
            AddressString origReference = null;
            AddressString destReference = null;
            MAPDialogLsm mapDialogLsm = mapServiceLsm.createNewDialog(appCnt, this.mapMan.createOrigAddress(), origReference,
                this.mapMan.createDestAddress(), destReference);
            logger.debug("MAPDialogLsm Created");
            TestLcsServerConfigurationData configData = this.testerHost.getConfigurationData().getTestLcsServerConfigurationData();

            // SLR Mandatory parameters LCSEvent, LCSClientID & Network Node Number
            MAPParameterFactoryImpl mapFactory = new MAPParameterFactoryImpl();
            LCSEvent lcsEvent = LCSEvent.deferredmtlrResponse;

            LCSClientExternalID lcsClientExternalID = null;
            LCSClientInternalID lcsClientInternalID = LCSClientInternalID.anonymousLocation;
            String clientName = "545248";
            int cbsDataCodingSchemeCode = 15;
            CBSDataCodingScheme cbsDataCodingScheme = new CBSDataCodingSchemeImpl(cbsDataCodingSchemeCode);
            String ussdLcsString = "3";
            Charset gsm8Charset = Charset.defaultCharset();
            USSDString ussdString = new USSDStringImpl(ussdLcsString, cbsDataCodingScheme, gsm8Charset);
            LCSFormatIndicator lcsFormatIndicator = LCSFormatIndicator.url;
            LCSClientName lcsClientName = new LCSClientNameImpl(cbsDataCodingScheme, ussdString, lcsFormatIndicator);
            AddressString lcsClientDialedByMS = new AddressStringImpl(AddressNature.international_number, NumberingPlan.ISDN, clientName);
            String apnStr = "internet.mnc002.mcc345.gprs";
            APN lcsAPN = null;
            try {
                lcsAPN = new APNImpl(apnStr);
            } catch (MAPException e) {
                e.printStackTrace();
            }
            LCSRequestorID lcsRequestorID = null;
            LCSClientID lcsClientID = mapParameterFactory.createLCSClientID(configData.getLcsClientType(), lcsClientExternalID, lcsClientInternalID,
                lcsClientName, lcsClientDialedByMS, lcsAPN, lcsRequestorID);

            ISDNAddressString networkNodeNumber = mapParameterFactory.createISDNAddressString(
                this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getAddressNature(),
                this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getNumberingPlanType(),
                getNetworkNodeNumber());

            // SLR TC-user optional parameters
            IMEI imei = mapParameterFactory.createIMEI(getIMEI());

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

            // LSR Conditional parameters
            IMSI imsi = mapParameterFactory.createIMSI(getIMSI());

            ISDNAddressString msisdn = mapParameterFactory.createISDNAddressString(
                this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getAddressNature(),
                this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getNumberingPlanType(),
                getMSISDN());

            ExtGeographicalInformation locationEstimate = null;
            AddGeographicalInformation additionalLocationEstimate = null;
            TypeOfShape typeOfShape = null, additionalTypeOfShape = null;
            double latitude, longitude, uncertainty, uncertaintySemiMajorAxis, uncertaintySemiMinorAxis, angleOfMajorAxis, uncertaintyAltitude, uncertaintyRadius,
                offsetAngle, includedAngle;
            int confidence, altitude, innerRadius;
            EllipsoidPoint ellipsoidPoint1, ellipsoidPoint2, ellipsoidPoint3, ellipsoidPoint4, ellipsoidPoint5,
                ellipsoidPoint6, ellipsoidPoint7, ellipsoidPoint8, ellipsoidPoint9, ellipsoidPoint10 = null,
                ellipsoidPoint11, ellipsoidPoint12, ellipsoidPoint13, ellipsoidPoint14, ellipsoidPoint15 = null;
            Integer typeOfShapeRandomOption = rand.nextInt(6) + 1;
            switch (typeOfShapeRandomOption) {
                case 1:
                    typeOfShape = TypeOfShape.EllipsoidPoint;
                    latitude = 34.789123;
                    longitude = -124.902033;
                    try {
                        locationEstimate = mapParameterFactory.createExtGeographicalInformation_EllipsoidPoint(latitude, longitude);
                    } catch (MAPException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    typeOfShape = TypeOfShape.EllipsoidPointWithUncertaintyCircle;
                    latitude = 51.123002;
                    longitude = -102.108732;
                    uncertainty = 5.1;
                    try {
                        locationEstimate = mapParameterFactory.createExtGeographicalInformation_EllipsoidPointWithUncertaintyCircle(latitude, longitude, uncertainty);
                    } catch (MAPException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    typeOfShape = TypeOfShape.EllipsoidPointWithUncertaintyEllipse;
                    latitude = 75.301024;
                    longitude = 122.718139;
                    uncertaintySemiMajorAxis = 21.2;
                    uncertaintySemiMinorAxis = 10.4;
                    angleOfMajorAxis = 30.0; // orientation of major axis
                    confidence = 1;
                    try {
                        locationEstimate = mapParameterFactory.createExtGeographicalInformation_EllipsoidPointWithUncertaintyEllipse(latitude, longitude,
                            uncertaintySemiMajorAxis, uncertaintySemiMinorAxis, angleOfMajorAxis, confidence);
                    } catch (MAPException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    typeOfShape = TypeOfShape.EllipsoidPointWithAltitudeAndUncertaintyEllipsoid;
                    latitude = 45.907010;
                    longitude = -99.000239;
                    altitude = 570;
                    uncertaintySemiMajorAxis = 25.4;
                    uncertaintySemiMinorAxis = 12.1;
                    angleOfMajorAxis = 30.2; // orientation of major axis
                    uncertaintyAltitude = 80.1;
                    confidence = 5;
                    try {
                        locationEstimate = mapParameterFactory.createExtGeographicalInformation_EllipsoidPointWithAltitudeAndUncertaintyEllipsoid(latitude,
                            longitude, uncertaintySemiMajorAxis, uncertaintySemiMinorAxis, angleOfMajorAxis, confidence, altitude, uncertaintyAltitude);
                    } catch (MAPException e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    typeOfShape = TypeOfShape.EllipsoidArc;
                    latitude = 45.907010;
                    longitude = -99.000239;
                    innerRadius = 5;
                    uncertaintyRadius = 1.50;
                    offsetAngle = 20.0;
                    includedAngle = 20.0;
                    confidence = 2;
                    try {
                        locationEstimate = mapParameterFactory.createExtGeographicalInformation_EllipsoidArc(latitude, longitude, innerRadius,
                            uncertaintyRadius, offsetAngle, includedAngle, confidence);
                    } catch (MAPException e) {
                        e.printStackTrace();
                    }
                    break;
                case 6:
                    typeOfShape = TypeOfShape.Polygon;
                    latitude = 0.0;
                    longitude = 0.0;
                    try {
                        locationEstimate = mapParameterFactory.createExtGeographicalInformation_EllipsoidPoint(latitude, longitude);
                    } catch (MAPException e) {
                        e.printStackTrace();
                    }
                    break;
            }

            int additionalLocationEstimateRandomOption = rand.nextInt(6) + 1;
            if (typeOfShape == TypeOfShape.Polygon) {
                additionalTypeOfShape = TypeOfShape.Polygon;
                ellipsoidPoint1 = new EllipsoidPoint(-2.907010, 70.778014);
                ellipsoidPoint2 = new EllipsoidPoint(-3.017238, 70.708922);
                ellipsoidPoint3 = new EllipsoidPoint(-2.941387, 70.432091);
                ellipsoidPoint4 = new EllipsoidPoint(-3.040019, 70.681903);
                ellipsoidPoint5 = new EllipsoidPoint(-3.045001, 70.700109);
                EllipsoidPoint[] ellipsoidPoints = {ellipsoidPoint1, ellipsoidPoint2, ellipsoidPoint3, ellipsoidPoint4, ellipsoidPoint5};

                /*  char packet_bytes[] = { 0x53, 0x27, 0x65, 0xe6, 0x35, 0x72, 0xb9, 0x27, 0x65, 0xe6, 0x35, 0x72, 0xb9, 0x27, 0x66, 0xef, 0x35, 0x73, 0x8e};   */
                byte[] polygonData1 = { 83,
                                        39, 101, (byte) 230, 53, 114, (byte) 185,
                                        39, 101, (byte) 230, 53, 114, (byte) 185,
                                        39, 102, (byte) 239, 53, 115, (byte) 142};

                /*  char packet_bytes[] = { 0x53, 0x2c, 0x1d, 0xbc, 0x35, 0xe3, 0x87, 0x2c,0x1d, 0xc1, 0x35, 0xe3, 0x82, 0x2c, 0x1d, 0xbe, 0x35, 0xe3, 0x7b};  */
                byte[] polygonData2 = { 83,
                                        44, 29, (byte) 188, 53, (byte) 227, (byte) 135,
                                        44, 29, (byte) 193, 53, (byte) 227, (byte) 130,
                                        44, 29, (byte) 190, 53, (byte) 227, 123};

                /* char packet_bytes[] =  { 0x53, 0x24, 0xa7, 0x3c, 0x34, 0x25, 0x00, 0x24, 0xa7, 0x31, 0x34, 0x24, 0xff, 0x24, 0xa7, 0x32,0x34, 0x25, 0x00}; */
                byte[] polygonData3 = { 83,
                                        36, (byte) 167, 60, 52, 37, 0,
                                        36, (byte) 167, 49, 52, 36, (byte) 255,
                                        36, (byte) 167, 50, 52, 37, 0};

                /* char packet_bytes[] =  { 0x53, 0x24, 0x7c, 0xa3, 0x3b, 0x31, 0x70, 0x24, 0x7e, 0x07, 0x3b, 0x31, 0x8a, 0x24, 0x7f, 0xe0, 0x3b, 0x31, 0x48}; */
                byte[] polygonData4 = { 83,
                                        36, 124, (byte) 163, 59, 49, 112,
                                        36, 126, 7, 59, 49, (byte) 138,
                                        36, 127, (byte) 224, 59, 49, 72};

                /* char packet_bytes[] =  { 0x53, 0x25, 0xe5, 0xb3, 0x34, 0x42, 0xd3, 0x25, 0xe6, 0x40, 0x34, 0x43, 0x7c, 0x25, 0xe6, 0x83, 0x34, 0x43, 0x79
                                            0x25, 0xe6, 0x84, 0x34, 0x43, 0x7d};  */
                byte[] polygonData5 = { 84,
                                        37, (byte) 229, (byte) 179, 52, 66, (byte) 211,
                                        37, (byte) 230, 64, 52, 67, 124,
                                        37, (byte) 230, (byte) 131, 52, 67, 121,
                                        37, (byte) 230, (byte) 132, 52, 67, 125};

                Polygon polygon1, polygon2, polygon3, polygon4, polygon5, polygon6 = new PolygonImpl();

                try {
                    switch (additionalLocationEstimateRandomOption) {
                        case 1:
                            polygon1 = new PolygonImpl(polygonData1);
                            additionalLocationEstimate = new AddGeographicalInformationImpl(polygon1.getData());
                            break;
                        case 2:
                            polygon2 = new PolygonImpl(polygonData2);
                            additionalLocationEstimate = new AddGeographicalInformationImpl(polygon2.getData());
                            break;
                        case 3:
                            polygon3 = new PolygonImpl(polygonData3);
                            additionalLocationEstimate = new AddGeographicalInformationImpl(polygon3.getData());
                            break;
                        case 4:
                            polygon4 = new PolygonImpl(polygonData4);
                            additionalLocationEstimate = new AddGeographicalInformationImpl(polygon4.getData());
                            break;
                        case 5:
                            polygon5 = new PolygonImpl(polygonData5);
                            additionalLocationEstimate = new AddGeographicalInformationImpl(polygon5.getData());
                            break;
                        case 6:
                            ((PolygonImpl) polygon6).setData(ellipsoidPoints);
                            additionalLocationEstimate = new AddGeographicalInformationImpl(polygon6.getData());
                            break;
                    }
                } catch (MAPException e) {
                    e.printStackTrace();
                }
            }

            Boolean gprsNodeIndicator = false;

            GSNAddress hgmlcAddress = createGSNAddress(getHGMLCAddress());

            AccuracyFulfilmentIndicator accuracyFulfilmentIndicator = AccuracyFulfilmentIndicator.requestedAccuracyFulfilled;

            ISDNAddressString naEsrd = null;
            ISDNAddressString naEsrk = null;
            SLRArgExtensionContainer slrArgExtensionContainer = null;

            CellGlobalIdOrServiceAreaIdFixedLength cellGlobalIdOrServiceAreaIdFixedLength = mapParameterFactory.createCellGlobalIdOrServiceAreaIdFixedLength(this.getMCC(), this.getMNC(), this.getLAC(), this.getCellId());
            CellGlobalIdOrServiceAreaIdOrLAI cellIdOrSai = mapParameterFactory.createCellGlobalIdOrServiceAreaIdOrLAI(cellGlobalIdOrServiceAreaIdFixedLength);

            MAPExtensionContainer extensionContainer = null;

            byte[] geranPosInfo = {0, 3};
            PositioningDataInformation geranPositioningData = new PositioningDataInformationImpl(geranPosInfo);
            byte[] utranData = {57, 51, 52, 54, 48, 49};
            UtranPositioningDataInfo utranPositioningDataInfo = new UtranPositioningDataInfoImpl(utranData);
            Integer lcsServiceTypeID = 1;
            Boolean saiPresent = false;
            Boolean pseudonymIndicator = false;
            VelocityType velocityType = VelocityType.HorizontalWithVerticalVelocityAndUncertainty;
            int horizontalSpeed = 101;
            int bearing = 3;
            int verticalSpeed = 2;
            int uncertaintyHorizontalSpeed = 5;
            int uncertaintyVerticalSpeed = 1;
            VelocityEstimate velocityEstimate = null;
            try {
                velocityEstimate = new VelocityEstimateImpl(velocityType, horizontalSpeed, bearing, verticalSpeed, uncertaintyHorizontalSpeed, uncertaintyVerticalSpeed);
            } catch (MAPException e) {
                e.printStackTrace();
            }
            Integer sequenceNumber = 0;
            int reportingAmount = 10;
            int reportingInterval = 60;
            PeriodicLDRInfo periodicLDRInfo = mapParameterFactory.createPeriodicLDRInfo(reportingAmount, reportingInterval);
            Boolean moLrShortCircuitIndicator = false;
            byte[] gGanss = {57, 50, 48, 49, 51, 52};
            GeranGANSSpositioningData geranGANSSpositioningData = new GeranGANSSpositioningDataImpl(gGanss);
            byte[] uGanss = {57, 51, 51, 54, 49, 48};
            UtranGANSSpositioningData utranGANSSpositioningData = new UtranGANSSpositioningDataImpl(uGanss);
            ServingNodeAddress targetNodeForHandover = null;
            AdditionalNumber additionalNumber = null;
            boolean lcsCapabilitySetRelease98_99 = true;
            boolean lcsCapabilitySetRelease4 = true;
            boolean lcsCapabilitySetRelease5 = true;
            boolean lcsCapabilitySetRelease6 = true;
            boolean lcsCapabilitySetRelease7 = true;
            SupportedLCSCapabilitySets supportedLCSCapabilitySets = new SupportedLCSCapabilitySetsImpl(lcsCapabilitySetRelease98_99, lcsCapabilitySetRelease4,
                lcsCapabilitySetRelease5, lcsCapabilitySetRelease6, lcsCapabilitySetRelease7);
            SupportedLCSCapabilitySets additionalLCSCapabilitySets = new SupportedLCSCapabilitySetsImpl(lcsCapabilitySetRelease98_99, lcsCapabilitySetRelease4,
                lcsCapabilitySetRelease5, lcsCapabilitySetRelease6, lcsCapabilitySetRelease7);
            String mmneNameStr = "mmec01.mmegi8000.mme.epc.mnc053.mcc404.3gppnetwork.org";
            byte[] mme = mmneNameStr.getBytes();
            //byte[] mme = {77, 77, 69, 55, 52, 56, 48, 48, 48, 49};
            DiameterIdentity mmeName = new DiameterIdentityImpl(mme);
            String aaaServerNameStr = "aaa01.aaa8000.aaa.epc.mnc053.mcc404.3gppnetwork.org";
            byte[] aaa = aaaServerNameStr.getBytes();
            //byte[] aaa = {65, 65, 65, 55, 52, 56, 48, 48, 48, 49, 53, 48};
            DiameterIdentity aaaServerName = new DiameterIdentityImpl(aaa);

            LCSLocationInfo lcsLocationInfo = mapParameterFactory.createLCSLocationInfo(networkNodeNumber, lmsi, extensionContainer, gprsNodeIndicator,
                additionalNumber, supportedLCSCapabilitySets, additionalLCSCapabilitySets, mmeName, aaaServerName);

            boolean msAvailable = false;
            boolean enteringIntoArea = false;
            boolean leavingFromArea = false;
            boolean beingInsideArea = true;
            boolean periodicLDR = false;
            DeferredLocationEventType deferredLocationEventType = new DeferredLocationEventTypeImpl(msAvailable, enteringIntoArea, leavingFromArea, beingInsideArea, periodicLDR);
            TerminationCause terminationCause = TerminationCause.congestion;
            DeferredmtlrData deferredmtlrData = new DeferredmtlrDataImpl(deferredLocationEventType, terminationCause, lcsLocationInfo);

            ReportingPLMNList reportingPLMNList = null;

            mapDialogLsm.addSubscriberLocationReportRequest(lcsEvent, lcsClientID, lcsLocationInfo,
                msisdn, imsi, imei, naEsrd, naEsrk, locationEstimate, getAgeOfLocationEstimate(), slrArgExtensionContainer, additionalLocationEstimate, deferredmtlrData,
                getLCSReferenceNumber(), geranPositioningData, utranPositioningDataInfo, cellIdOrSai, hgmlcAddress, lcsServiceTypeID, saiPresent, pseudonymIndicator,
                accuracyFulfilmentIndicator, velocityEstimate, sequenceNumber, periodicLDRInfo, moLrShortCircuitIndicator, geranGANSSpositioningData,
                utranGANSSpositioningData, targetNodeForHandover);
            logger.debug("Added SubscriberLocationReportRequest");

            mapDialogLsm.send();

            this.countMapLcsReq++;

            this.testerHost.sendNotif(SOURCE_NAME, "Sent: SubscriberLocationReportRequest", createSLRReqData(mapDialogLsm.getLocalDialogId(), lcsEvent,
                this.getNetworkNodeNumber(), lcsClientID, msisdn, imsi, imei, locationEstimate, getAgeOfLocationEstimate(), lmsi, getLCSReferenceNumber(),
                deferredmtlrData, cellIdOrSai, hgmlcAddress, accuracyFulfilmentIndicator, reportingPLMNList), Level.INFO);

            currentRequestDef += "Sent SLR Request;";

        } catch (MAPException e) {
            return "Exception on addSubscriberLocationReportRequest: " + e.toString();
        }

        return "subscriberLocationReportRequest sent";
    }

    private String subscriberLocationReportRequestNullRefNum() {
        if (mapProvider == null) {
            return "mapProvider is null";
        }

        try {
            Random rand = new Random();

            // LSM dialog creation
            MAPApplicationContext appCnt = MAPApplicationContext.getInstance(MAPApplicationContextName.locationSvcEnquiryContext,
                MAPApplicationContextVersion.version3);
            AddressString origReference = null;
            AddressString destReference = null;
            MAPDialogLsm mapDialogLsm = mapServiceLsm.createNewDialog(appCnt, this.mapMan.createOrigAddress(), origReference,
                this.mapMan.createDestAddress(), destReference);
            logger.debug("MAPDialogLsm Created");
            TestLcsServerConfigurationData configData = this.testerHost.getConfigurationData().getTestLcsServerConfigurationData();

            // SLR Mandatory parameters LCSEvent, LCSClientID & Network Node Number
            MAPParameterFactoryImpl mapFactory = new MAPParameterFactoryImpl();
            LCSEvent lcsEvent = this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getLCSEvent();

            LCSClientExternalID lcsClientExternalID = null;
            LCSClientInternalID lcsClientInternalID = null; //LCSClientInternalID.anonymousLocation;
            String clientName = "545248";
            int cbsDataCodingSchemeCode = 15;
            CBSDataCodingScheme cbsDataCodingScheme = new CBSDataCodingSchemeImpl(cbsDataCodingSchemeCode);
            String ussdLcsString = "3";
            Charset gsm8Charset = Charset.defaultCharset();
            USSDString ussdString = new USSDStringImpl(ussdLcsString, cbsDataCodingScheme, gsm8Charset);
            LCSFormatIndicator lcsFormatIndicator = LCSFormatIndicator.url;
            LCSClientName lcsClientName = null; //new LCSClientNameImpl(cbsDataCodingScheme, ussdString, lcsFormatIndicator);
            AddressString lcsClientDialedByMS = null; //new AddressStringImpl(AddressNature.international_number, NumberingPlan.ISDN, clientName);
            //String apnStr = "internet.mnc002.mcc345.gprs";
            APN lcsAPN = null;
            /*try {
                lcsAPN = new APNImpl(apnStr);
            } catch (MAPException e) {
                e.printStackTrace();
            }*/
            LCSRequestorID lcsRequestorID = null;
            LCSClientID lcsClientID = mapParameterFactory.createLCSClientID(LCSClientType.emergencyServices, lcsClientExternalID, lcsClientInternalID,
                lcsClientName, lcsClientDialedByMS, lcsAPN, lcsRequestorID);

            ISDNAddressString networkNodeNumber = mapParameterFactory.createISDNAddressString(
                this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getAddressNature(),
                this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getNumberingPlanType(),
                "919418599995");

            // SLR TC-user optional parameters
            IMEI imei = null; // mapParameterFactory.createIMEI(getIMEI());

            byte[] lmsiByte = {(byte) 179, 125, 75, 2}; //0xb3, 0x7d, 0x4b, 0x02
            LMSI lmsi = new LMSIImpl(lmsiByte);

            // LSR Conditional parameters
            IMSI imsi = mapParameterFactory.createIMSI("404511170527751");

            ISDNAddressString msisdn = mapParameterFactory.createISDNAddressString(
                this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getAddressNature(),
                this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getNumberingPlanType(),
                "919418967382");

            ExtGeographicalInformation locationEstimate = null;
            AddGeographicalInformation additionalLocationEstimate = null;
            TypeOfShape typeOfShape = null, additionalTypeOfShape = null;
            double latitude, longitude, uncertainty, uncertaintySemiMajorAxis, uncertaintySemiMinorAxis, angleOfMajorAxis, uncertaintyAltitude, uncertaintyRadius,
                offsetAngle, includedAngle;
            int confidence, altitude, innerRadius;
            EllipsoidPoint ellipsoidPoint1, ellipsoidPoint2, ellipsoidPoint3, ellipsoidPoint4, ellipsoidPoint5,
                ellipsoidPoint6, ellipsoidPoint7, ellipsoidPoint8, ellipsoidPoint9, ellipsoidPoint10 = null,
                ellipsoidPoint11, ellipsoidPoint12, ellipsoidPoint13, ellipsoidPoint14, ellipsoidPoint15 = null;
            int typeOfShapeRandomOption = rand.nextInt(6) + 1;
            // = -34.870059;
            // = -56.000217;
            switch (typeOfShapeRandomOption) {
                case 1:
                    typeOfShape = TypeOfShape.EllipsoidPoint;
                    latitude = -34.810259;
                    longitude = -56.000217;
                    try {
                        locationEstimate = mapParameterFactory.createExtGeographicalInformation_EllipsoidPoint(latitude, longitude);
                    } catch (MAPException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    typeOfShape = TypeOfShape.EllipsoidPointWithUncertaintyCircle;
                    latitude = -34.801052;
                    longitude = -56.000219;
                    uncertainty = 5.1;
                    try {
                        locationEstimate = mapParameterFactory.createExtGeographicalInformation_EllipsoidPointWithUncertaintyCircle(latitude, longitude, uncertainty);
                    } catch (MAPException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    typeOfShape = TypeOfShape.EllipsoidPointWithUncertaintyEllipse;
                    latitude = -34.322357;
                    longitude = -56.000322;
                    uncertaintySemiMajorAxis = 35.0;
                    uncertaintySemiMinorAxis = 33.0;
                    angleOfMajorAxis = 100.0; // orientation of major axis
                    confidence = 80;
                    try {
                        locationEstimate = mapParameterFactory.createExtGeographicalInformation_EllipsoidPointWithUncertaintyEllipse(latitude, longitude,
                            uncertaintySemiMajorAxis, uncertaintySemiMinorAxis, angleOfMajorAxis, confidence);
                    } catch (MAPException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    typeOfShape = TypeOfShape.EllipsoidPointWithAltitudeAndUncertaintyEllipsoid;
                    latitude = -34.778123;
                    longitude = -56.001014;
                    altitude = 570;
                    uncertaintySemiMajorAxis = 25.4;
                    uncertaintySemiMinorAxis = 12.1;
                    angleOfMajorAxis = 30.2; // orientation of major axis
                    uncertaintyAltitude = 80.1;
                    confidence = 5;
                    try {
                        locationEstimate = mapParameterFactory.createExtGeographicalInformation_EllipsoidPointWithAltitudeAndUncertaintyEllipsoid(latitude,
                            longitude, uncertaintySemiMajorAxis, uncertaintySemiMinorAxis, angleOfMajorAxis, confidence, altitude, uncertaintyAltitude);
                    } catch (MAPException e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    typeOfShape = TypeOfShape.EllipsoidArc;
                    latitude = -34.100017;
                    longitude = -56.000441;
                    innerRadius = 5;
                    uncertaintyRadius = 1.50;
                    offsetAngle = 20.0;
                    includedAngle = 20.0;
                    confidence = 2;
                    try {
                        locationEstimate = mapParameterFactory.createExtGeographicalInformation_EllipsoidArc(latitude, longitude, innerRadius,
                            uncertaintyRadius, offsetAngle, includedAngle, confidence);
                    } catch (MAPException e) {
                        e.printStackTrace();
                    }
                    break;
                case 6:
                    typeOfShape = TypeOfShape.Polygon;
                    latitude = 0.0;
                    longitude = 0.0;
                    try {
                        locationEstimate = mapParameterFactory.createExtGeographicalInformation_EllipsoidPoint(latitude, longitude);
                    } catch (MAPException e) {
                        e.printStackTrace();
                    }
                    break;
            }

            int additionalLocationEstimateRandomOption = rand.nextInt(6) + 1;
            if (typeOfShape == TypeOfShape.Polygon) {
                additionalTypeOfShape = TypeOfShape.Polygon;
                ellipsoidPoint1 = new EllipsoidPoint(-2.907010, 70.778014);
                ellipsoidPoint2 = new EllipsoidPoint(-3.017238, 70.708922);
                ellipsoidPoint3 = new EllipsoidPoint(-2.941387, 70.432091);
                ellipsoidPoint4 = new EllipsoidPoint(-3.040019, 70.681903);
                ellipsoidPoint5 = new EllipsoidPoint(-3.045001, 70.700109);
                EllipsoidPoint[] ellipsoidPoints = {ellipsoidPoint1, ellipsoidPoint2, ellipsoidPoint3, ellipsoidPoint4, ellipsoidPoint5};

                /*  char packet_bytes[] = { 0x53, 0x27, 0x65, 0xe6, 0x35, 0x72, 0xb9, 0x27, 0x65, 0xe6, 0x35, 0x72, 0xb9, 0x27, 0x66, 0xef, 0x35, 0x73, 0x8e};   */
                byte[] polygonData1 = { 83,
                                        39, 101, (byte) 230, 53, 114, (byte) 185,
                                        39, 101, (byte) 230, 53, 114, (byte) 185,
                                        39, 102, (byte) 239, 53, 115, (byte) 142};

                /*  char packet_bytes[] = { 0x53, 0x2c, 0x1d, 0xbc, 0x35, 0xe3, 0x87, 0x2c,0x1d, 0xc1, 0x35, 0xe3, 0x82, 0x2c, 0x1d, 0xbe, 0x35, 0xe3, 0x7b};  */
                byte[] polygonData2 = { 83,
                                        44, 29, (byte) 188, 53, (byte) 227, (byte) 135,
                                        44, 29, (byte) 193, 53, (byte) 227, (byte) 130,
                                        44, 29, (byte) 190, 53, (byte) 227, 123};

                /* char packet_bytes[] =  { 0x53, 0x24, 0xa7, 0x3c, 0x34, 0x25, 0x00, 0x24, 0xa7, 0x31, 0x34, 0x24, 0xff, 0x24, 0xa7, 0x32,0x34, 0x25, 0x00}; */
                byte[] polygonData3 = { 83,
                                        36, (byte) 167, 60, 52, 37, 0,
                                        36, (byte) 167, 49, 52, 36, (byte) 255,
                                        36, (byte) 167, 50, 52, 37, 0};

                /* char packet_bytes[] =  { 0x53, 0x24, 0x7c, 0xa3, 0x3b, 0x31, 0x70, 0x24, 0x7e, 0x07, 0x3b, 0x31, 0x8a, 0x24, 0x7f, 0xe0, 0x3b, 0x31, 0x48}; */
                byte[] polygonData4 = { 83,
                                        36, 124, (byte) 163, 59, 49, 112,
                                        36, 126, 7, 59, 49, (byte) 138,
                                        36, 127, (byte) 224, 59, 49, 72};

                /* char packet_bytes[] =  { 0x53, 0x25, 0xe5, 0xb3, 0x34, 0x42, 0xd3, 0x25, 0xe6, 0x40, 0x34, 0x43, 0x7c, 0x25, 0xe6, 0x83, 0x34, 0x43, 0x79
                                            0x25, 0xe6, 0x84, 0x34, 0x43, 0x7d};  */
                byte[] polygonData5 = { 84,
                                        37, (byte) 229, (byte) 179, 52, 66, (byte) 211,
                                        37, (byte) 230, 64, 52, 67, 124,
                                        37, (byte) 230, (byte) 131, 52, 67, 121,
                                        37, (byte) 230, (byte) 132, 52, 67, 125};

                Polygon polygon1, polygon2, polygon3, polygon4, polygon5, polygon6 = new PolygonImpl();

                try {
                    switch (additionalLocationEstimateRandomOption) {
                        case 1:
                            polygon1 = new PolygonImpl(polygonData1);
                            additionalLocationEstimate = new AddGeographicalInformationImpl(polygon1.getData());
                            break;
                        case 2:
                            polygon2 = new PolygonImpl(polygonData2);
                            additionalLocationEstimate = new AddGeographicalInformationImpl(polygon2.getData());
                            break;
                        case 3:
                            polygon3 = new PolygonImpl(polygonData3);
                            additionalLocationEstimate = new AddGeographicalInformationImpl(polygon3.getData());
                            break;
                        case 4:
                            polygon4 = new PolygonImpl(polygonData4);
                            additionalLocationEstimate = new AddGeographicalInformationImpl(polygon4.getData());
                            break;
                        case 5:
                            polygon5 = new PolygonImpl(polygonData5);
                            additionalLocationEstimate = new AddGeographicalInformationImpl(polygon5.getData());
                            break;
                        case 6:
                            ((PolygonImpl) polygon6).setData(ellipsoidPoints);
                            additionalLocationEstimate = new AddGeographicalInformationImpl(polygon6.getData());
                            break;
                    }
                } catch (MAPException e) {
                    e.printStackTrace();
                }
            }

            Boolean gprsNodeIndicator = false;

            GSNAddress hgmlcAddress = null; //createGSNAddress(getHGMLCAddress());

            AccuracyFulfilmentIndicator accuracyFulfilmentIndicator = null; //AccuracyFulfilmentIndicator.requestedAccuracyFulfilled;

            ISDNAddressString naEsrd = null;
            ISDNAddressString naEsrk = null;
            SLRArgExtensionContainer slrArgExtensionContainer = null;

            byte[] cgiByteArray = {4, (byte) 244, 21, 19, (byte) 136, 85, 71}; // 0x04, 0xf4, 0x15, 0x13, 0x88, 0x55, 0x47
            CellGlobalIdOrServiceAreaIdFixedLength cellGlobalIdOrServiceAreaIdFixedLength = mapParameterFactory.createCellGlobalIdOrServiceAreaIdFixedLength(cgiByteArray);
            CellGlobalIdOrServiceAreaIdOrLAI cellIdOrSai = mapParameterFactory.createCellGlobalIdOrServiceAreaIdOrLAI(cellGlobalIdOrServiceAreaIdFixedLength);

            MAPExtensionContainer extensionContainer = null;

            byte[] geranPosInfo = null; //{50, 57, 49, 53, 51};
            PositioningDataInformation geranPositioningData = null; //new PositioningDataInformationImpl(geranPosInfo);
            byte[] utranData = null; //{57, 51, 52, 54, 48, 49};
            UtranPositioningDataInfo utranPositioningDataInfo = null; //new UtranPositioningDataInfoImpl(utranData);
            Integer lcsServiceTypeID = null;
            Boolean saiPresent = false;
            Boolean pseudonymIndicator = false;
            VelocityType velocityType = VelocityType.HorizontalWithVerticalVelocityAndUncertainty;
            int horizontalSpeed = 101;
            int bearing = 3;
            int verticalSpeed = 2;
            int uncertaintyHorizontalSpeed = 5;
            int uncertaintyVerticalSpeed = 1;
            VelocityEstimate velocityEstimate;
            try {
                velocityEstimate = new VelocityEstimateImpl(velocityType, horizontalSpeed, bearing, verticalSpeed, uncertaintyHorizontalSpeed, uncertaintyVerticalSpeed);
            } catch (MAPException e) {
                e.printStackTrace();
            }
            velocityEstimate = null;
            Integer sequenceNumber = null;
            int reportingAmount = 10;
            int reportingInterval = 60;
            PeriodicLDRInfo periodicLDRInfo = null; //mapParameterFactory.createPeriodicLDRInfo(reportingAmount, reportingInterval);
            Boolean moLrShortCircuitIndicator = false;
            byte[] gGanss = {57, 50, 48, 49, 51, 52};
            GeranGANSSpositioningData geranGANSSpositioningData = null; //new GeranGANSSpositioningDataImpl(gGanss);
            byte[] uGanss = {57, 51, 51, 54, 49, 48};
            UtranGANSSpositioningData utranGANSSpositioningData = null; //new UtranGANSSpositioningDataImpl(uGanss);
            ServingNodeAddress targetNodeForHandover = null;
            AdditionalNumber additionalNumber = null;
            boolean lcsCapabilitySetRelease98_99 = true;
            boolean lcsCapabilitySetRelease4 = true;
            boolean lcsCapabilitySetRelease5 = true;
            boolean lcsCapabilitySetRelease6 = true;
            boolean lcsCapabilitySetRelease7 = true;
            SupportedLCSCapabilitySets supportedLCSCapabilitySets = null; // new SupportedLCSCapabilitySetsImpl(lcsCapabilitySetRelease98_99, lcsCapabilitySetRelease4, lcsCapabilitySetRelease5, lcsCapabilitySetRelease6, lcsCapabilitySetRelease7);
            SupportedLCSCapabilitySets additionalLCSCapabilitySets = null; // new SupportedLCSCapabilitySetsImpl(lcsCapabilitySetRelease98_99, lcsCapabilitySetRelease4, lcsCapabilitySetRelease5, lcsCapabilitySetRelease6, lcsCapabilitySetRelease7);
            String mmneNameStr = "mmec02.mmegi8000.mme.epc.mnc052.mcc404.3gppnetwork.org";
            byte[] mme = mmneNameStr.getBytes();
            //byte[] mme = {77, 77, 69, 55, 52, 56, 48, 48, 48, 49};
            DiameterIdentity mmeName = new DiameterIdentityImpl(mme);
            String aaaServerNameStr = "aaa02.aaa8000.aaa.epc.mnc052.mcc404.3gppnetwork.org";
            byte[] aaa = aaaServerNameStr.getBytes();
            //byte[] aaa = {65, 65, 65, 55, 52, 56, 48, 48, 48, 49, 53, 48};
            DiameterIdentity aaaServerName = new DiameterIdentityImpl(aaa);

            LCSLocationInfo lcsLocationInfo = mapParameterFactory.createLCSLocationInfo(networkNodeNumber, lmsi, extensionContainer, gprsNodeIndicator,
                additionalNumber, supportedLCSCapabilitySets, additionalLCSCapabilitySets, mmeName, aaaServerName);

            boolean msAvailable = false;
            boolean enteringIntoArea = false;
            boolean leavingFromArea = false;
            boolean beingInsideArea = true;
            boolean periodicLDR = false;
            DeferredLocationEventType deferredLocationEventType = new DeferredLocationEventTypeImpl(msAvailable, enteringIntoArea, leavingFromArea, beingInsideArea, periodicLDR);
            TerminationCause terminationCause = TerminationCause.congestion;
            DeferredmtlrData deferredmtlrData = null; //new DeferredmtlrDataImpl(deferredLocationEventType, terminationCause, lcsLocationInfo);

            ReportingPLMNList reportingPLMNList = null;

            mapDialogLsm.addSubscriberLocationReportRequest(lcsEvent, lcsClientID, lcsLocationInfo, msisdn, imsi, imei, naEsrd, naEsrk, locationEstimate,
                getAgeOfLocationEstimate(), slrArgExtensionContainer, additionalLocationEstimate, deferredmtlrData, null, geranPositioningData,
                utranPositioningDataInfo, cellIdOrSai, hgmlcAddress, lcsServiceTypeID, saiPresent, pseudonymIndicator, accuracyFulfilmentIndicator,
                velocityEstimate, sequenceNumber, periodicLDRInfo, moLrShortCircuitIndicator, geranGANSSpositioningData, utranGANSSpositioningData,
                targetNodeForHandover);
            logger.debug("Added SubscriberLocationReportRequest");

            mapDialogLsm.send();

            this.countMapLcsReq++;

            this.testerHost.sendNotif(SOURCE_NAME, "Sent: SubscriberLocationReportRequest", createSLRReqData(mapDialogLsm.getLocalDialogId(), lcsEvent,
                this.getNetworkNodeNumber(), lcsClientID, msisdn, imsi, imei, locationEstimate, getAgeOfLocationEstimate(), lmsi, null,
                deferredmtlrData, cellIdOrSai, hgmlcAddress, accuracyFulfilmentIndicator, reportingPLMNList), Level.INFO);

            currentRequestDef += "Sent SLR Request;";

        } catch (MAPException e) {
            return "Exception on addSubscriberLocationReportRequest: " + e.toString();
        }

        return "subscriberLocationReportRequest sent";
    }

    private String createSLRReqData(long dialogId, LCSEvent lcsEvent, String networkNodeNumber, LCSClientID lcsClientID, ISDNAddressString msisdn, IMSI imsi,
                                    IMEI imei, ExtGeographicalInformation locationEstimate, Integer ageOfLocationEstimate, LMSI lmsi, Integer lcsReferenceNumber,
                                    DeferredmtlrData deferredmtlrData, CellGlobalIdOrServiceAreaIdOrLAI cellIdOrSai, GSNAddress hgmlcAddress,
                                    AccuracyFulfilmentIndicator accuracyFulfilmentIndicator, ReportingPLMNList reportingPLMNList) {
        StringBuilder sb = new StringBuilder();
        sb.append("dialogId=");
        sb.append(dialogId);
        sb.append(", lcsEvent=\"");
        if (lcsEvent != null)
            sb.append(lcsEvent.getEvent());
        sb.append("\", networkNodeNumber=\"");
        sb.append(networkNodeNumber).append(", ");
        sb.append("\", lcsClientID=\"");
        sb.append(lcsClientID).append(", ");
        sb.append("\", MSISDN=\"");
        if (msisdn != null)
            sb.append(msisdn.getAddress()).append(", ");
        sb.append("\", IMSI=\"");
        if (imsi != null)
            sb.append(imsi.getData()).append(", ");
        sb.append("\", IMEI=\"");
        sb.append(imei).append(", ");
        if (locationEstimate.getLatitude() > -90 && locationEstimate.getLatitude() < 90) {
            sb.append("\", latitude=\"");
            sb.append(locationEstimate.getLatitude()).append(", ");
        }
        if (locationEstimate.getLongitude() > -180 && locationEstimate.getLongitude() < 180) {
            sb.append("\", longitude=\"");
            sb.append(locationEstimate.getLongitude()).append(", ");
        }
        if (locationEstimate.getTypeOfShape() != null) {
            sb.append("\", typeOfShape=\"");
            sb.append(locationEstimate.getTypeOfShape()).append(", ");
        }
        if (locationEstimate.getUncertainty() >= 0 && locationEstimate.getUncertainty() < 128) {
            sb.append("\", uncertainty=\"");
            sb.append(locationEstimate.getUncertainty()).append(", ");
        }
        if (locationEstimate.getAltitude() > Integer.MIN_VALUE && locationEstimate.getAltitude() < Integer.MAX_VALUE) {
            sb.append("\", altitude=\"");
            sb.append(locationEstimate.getAltitude()).append(", ");
        }
        if (locationEstimate.getUncertaintyAltitude() > Double.MIN_VALUE && locationEstimate.getUncertaintyAltitude() < Double.MAX_VALUE) {
            sb.append("\", uncertaintyAltitude=\"");
            sb.append(locationEstimate.getUncertaintyAltitude()).append(", ");
        }
        if (locationEstimate.getConfidence() > Integer.MIN_VALUE && locationEstimate.getConfidence() < Integer.MAX_VALUE) {
            sb.append("\", confidence=\"");
            sb.append(locationEstimate.getConfidence()).append(", ");
        }
        if (locationEstimate.getInnerRadius() > Integer.MIN_VALUE && locationEstimate.getInnerRadius() < Integer.MAX_VALUE) {
            sb.append("\", innerRadius=\"");
            sb.append(locationEstimate.getInnerRadius()).append(", ");
        }
        if (locationEstimate.getUncertaintyRadius() > Double.MIN_VALUE && locationEstimate.getUncertaintyRadius() < Double.MAX_VALUE) {
            sb.append("\", uncertaintyRadius=\"");
            sb.append(locationEstimate.getUncertaintyRadius()).append(", ");
        }
        if (locationEstimate.getUncertaintySemiMajorAxis() > Double.MIN_VALUE && locationEstimate.getUncertaintySemiMajorAxis() < Double.MAX_VALUE) {
            sb.append("\", uncertaintySemiMajorAxis=\"");
            sb.append(locationEstimate.getUncertaintySemiMajorAxis()).append(", ");
        }
        if (locationEstimate.getUncertaintySemiMinorAxis() > Double.MIN_VALUE && locationEstimate.getUncertaintySemiMinorAxis() < Double.MAX_VALUE) {
            sb.append("\", uncertaintySemiMinorAxis=\"");
            sb.append(locationEstimate.getUncertaintySemiMinorAxis()).append(", ");
        }
        if (locationEstimate.getAngleOfMajorAxis() > Double.MIN_VALUE && locationEstimate.getAngleOfMajorAxis() < Double.MAX_VALUE) {
            sb.append("\", angleOfMajorAxis=\"");
            sb.append(locationEstimate.getAngleOfMajorAxis()).append(", ");
        }
        if (locationEstimate.getOffsetAngle() > Double.MIN_VALUE && locationEstimate.getOffsetAngle() < Double.MAX_VALUE) {
            sb.append("\", offsetAngle=\"");
            sb.append(locationEstimate.getOffsetAngle()).append(", ");
        }
        if (locationEstimate.getIncludedAngle() > Double.MIN_VALUE && locationEstimate.getIncludedAngle() < Double.MAX_VALUE) {
            sb.append("\", includeAngle=\"");
            sb.append(locationEstimate.getIncludedAngle()).append(", ");
        }
        sb.append("\", ageOfLocationEstimate=\"");
        sb.append(ageOfLocationEstimate);
        sb.append("\", LMSI=\"");
        if (lmsi != null)
            sb.append(lmsi.toString()).append(", ");
        sb.append("\", lcsReferenceNumber=\"");
        sb.append(lcsReferenceNumber).append("\", ");
        sb.append("\", deferredmtlrData=\"");
        sb.append(deferredmtlrData).append(", ");
        sb.append("\", MCC=\"");
        try {
            sb.append((cellIdOrSai.getCellGlobalIdOrServiceAreaIdFixedLength().getMCC())).append(", ");
        } catch (MAPException e) {
            e.printStackTrace();
        }
        sb.append("\", MNC=\"");
        try {
            sb.append((cellIdOrSai.getCellGlobalIdOrServiceAreaIdFixedLength().getMNC())).append(", ");
        } catch (MAPException e) {
            e.printStackTrace();
        }
        sb.append("\", LAC=\"");
        try {
            sb.append((cellIdOrSai.getCellGlobalIdOrServiceAreaIdFixedLength().getLac())).append(", ");
        } catch (MAPException e) {
            e.printStackTrace();
        }
        sb.append("\", LAC=\"");
        try {
            sb.append((cellIdOrSai.getCellGlobalIdOrServiceAreaIdFixedLength().getCellIdOrServiceAreaCode())).append(", ");
        } catch (MAPException e) {
            e.printStackTrace();
        }
        sb.append("\", H-GMLCAddress=\"");
        sb.append(hgmlcAddress);
        sb.append("\", accuracyFulfilmentIndicator=\"");
        sb.append(accuracyFulfilmentIndicator);
        sb.append("\", reportingPLMNList=\"");
        if (reportingPLMNList != null) {
            for (int i = 0; i < reportingPLMNList.getPlmnList().size(); i++) {
                if (i < reportingPLMNList.getPlmnList().size())
                    sb.append(reportingPLMNList.getPlmnList().get(i)).append(", ");
                else
                    sb.append(reportingPLMNList.getPlmnList().get(i));
            }
        }

        return sb.toString();
    }

    private String createSLRResData(long dialogId, String address) {
        StringBuilder sb = new StringBuilder();
        sb.append("dialogId=");
        sb.append(dialogId);
        sb.append(", naESRD=\"");
        sb.append(address);
        sb.append("\"");
        return sb.toString();
    }

    public void onSubscriberLocationReportRequest(SubscriberLocationReportRequest subscriberLocationReportRequestIndication) {
        logger.debug("onSubscriberLocationReportRequest");
        this.countMapLcsReq++;
        if (!isStarted)
            return;

        MAPDialogLsm curDialog = subscriberLocationReportRequestIndication.getMAPDialog();
        String networkNodeNumberAddress = subscriberLocationReportRequestIndication.getLCSLocationInfo().getNetworkNodeNumber().getAddress();

        this.testerHost.sendNotif(SOURCE_NAME, "Rcvd: SubscriberLocationReportRequest",
            createSLRReqData(curDialog.getLocalDialogId(), subscriberLocationReportRequestIndication.getLCSEvent(),
                networkNodeNumberAddress,
                subscriberLocationReportRequestIndication.getLCSClientID(),
                subscriberLocationReportRequestIndication.getMSISDN(),
                subscriberLocationReportRequestIndication.getIMSI(),
                subscriberLocationReportRequestIndication.getIMEI(),
                subscriberLocationReportRequestIndication.getLocationEstimate(),
                subscriberLocationReportRequestIndication.getAgeOfLocationEstimate(),
                subscriberLocationReportRequestIndication.getLMSI(),
                subscriberLocationReportRequestIndication.getLCSReferenceNumber(),
                subscriberLocationReportRequestIndication.getDeferredmtlrData(),
                subscriberLocationReportRequestIndication.getCellGlobalIdOrServiceAreaIdOrLAI(),
                subscriberLocationReportRequestIndication.getHGMLCAddress(),
                subscriberLocationReportRequestIndication.getAccuracyFulfilmentIndicator(),
                subscriberLocationReportRequestIndication.getReportingPLMNList()), Level.INFO);

        MAPParameterFactory mapParameterFactory = this.mapProvider.getMAPParameterFactory();
        ISDNAddressString naEsrd = mapParameterFactory.createISDNAddressString(
            AddressNature.getInstance(getAddressNature().intValue()),
            NumberingPlan.getInstance(getNumberingPlanType().intValue()),
            getNaESRDAddress());

        try {
            curDialog.addSubscriberLocationReportResponse(subscriberLocationReportRequestIndication.getInvokeId(), naEsrd, null, null);
            logger.debug("\nset addSubscriberLocationReportResponse");
            curDialog.send();
            logger.debug("\naddSubscriberLocationReportResponse sent");
            this.countMapLcsResp++;

            this.testerHost.sendNotif(SOURCE_NAME, "Sent: SubscriberLocationReportResponse",
                createSLRResData(curDialog.getLocalDialogId(), getNaESRDAddress()), Level.INFO);

        } catch (MAPException e) {
            logger.debug("Exception on addSubscriberLocationReportResponse: " + e.toString());
        }
    }

    public void onSubscriberLocationReportResponse(SubscriberLocationReportResponse subscriberLocationReportResponseIndication) {
        logger.debug("onSubscriberLocationReportResponse");
        this.countMapLcsResp++;
        String naESRD = null;
        if (subscriberLocationReportResponseIndication.getNaESRD() != null)
            naESRD = subscriberLocationReportResponseIndication.getNaESRD().getAddress();
        this.testerHost.sendNotif(SOURCE_NAME,
            "Rcvd: SubscriberLocationReportResponse", this.createSLRResData(subscriberLocationReportResponseIndication.getInvokeId(), naESRD), Level.INFO);
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

    //**********************************************************//
    //*** Common methods for MAP LSM operations' attributes ***//
    //********************************************************//
    @Override
    public String getMlcNumber() {
        return this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getMlcNumber();
    }

    @Override
    public void setMlcNumber(String mlcNumber) {
        this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().setMlcNumber(mlcNumber);
    }

    @Override
    public String getNetworkNodeNumber() {
        return this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getNetworkNodeNumber();
    }

    @Override
    public void setNetworkNodeNumber(String data) {
        this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().setNetworkNodeNumber(data);
        this.testerHost.markStore();
    }

    // PSL Request
    @Override
    public Double getLocationEstimateLatitude() {
        return this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getLocationEstimate().getLatitude();
    }

    @Override
    public void setLocationEstimateLatitude(Double locationEstimateLatitude) {
        this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().setLatitude(locationEstimateLatitude);
        this.testerHost.markStore();
    }

    @Override
    public Double getLocationEstimateLongitude() {
        return this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getLocationEstimate().getLongitude();
    }

    @Override
    public void setLocationEstimateLongitude(Double locationEstimateLongitude) {
        this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().setLongitude(locationEstimateLongitude);
        this.testerHost.markStore();
    }

    @Override
    public LocationEstimateTypeEnumerated getLocEstimateType() {
        return new LocationEstimateTypeEnumerated(this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getLocationEstimateType().getType());
    }

    @Override
    public void setLocEstimateType(LocationEstimateTypeEnumerated locEstimate) {
        this.testerHost.getConfigurationData().
            getTestLcsServerConfigurationData().setLocationEstimateType(LocationEstimateType.getLocationEstimateType(locEstimate.intValue()));
        this.testerHost.markStore();
    }

    @Override
    public TypeOfShapeEnumerated getTypeOfShape() {
        return new TypeOfShapeEnumerated(this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getTypeOfShape().getCode());
    }

    @Override
    public void setTypeOfShapeEnumerated(TypeOfShapeEnumerated typeOfShape) {
        this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().setTypeOfShape(TypeOfShape.valueOf(typeOfShape.toString()));
        this.testerHost.markStore();
    }

    @Override
    public Integer getLcsServiceTypeID() {
        return this.testerHost.getConfigurationData().
            getTestLcsServerConfigurationData().getLcsServiceTypeID();
    }

    @Override
    public void setLcsServiceTypeID(Integer lcsServiceTypeID) {
        this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().setLcsServiceTypeID(lcsServiceTypeID);
        this.testerHost.markStore();
    }

    @Override
    public LCSClientTypeEnumerated getLcsClientTypeEnumerated() {
        return new LCSClientTypeEnumerated(this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getLcsClientType().getType());
    }

    @Override
    public void setLcsClientTypeEnumerated(LCSClientTypeEnumerated val) {
        this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().setLcsClientType(LCSClientType.getLCSClientType(val.intValue()));
        this.testerHost.markStore();
    }


    @Override
    public void setCodeWordUSSDString(String codeWordUSSDString) {
        this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().setCodeWordUSSDString(codeWordUSSDString);
        this.testerHost.markStore();
    }

    @Override
    public String getCodeWordUSSDString() {
        return this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getCodeWordUSSDString();
    }

    @Override
    public void setCallSessionUnrelated(PrivacyCheckRelatedActionEnumerated val) {
        this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().setCallSessionUnrelated(PrivacyCheckRelatedAction.getPrivacyCheckRelatedAction(val.intValue()));
        this.testerHost.markStore();
    }

    @Override
    public PrivacyCheckRelatedActionEnumerated getCallSessionUnrelated() {
        return new PrivacyCheckRelatedActionEnumerated(this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getCallSessionUnrelated().getAction());
    }

    @Override
    public void setCallSessionRelated(PrivacyCheckRelatedActionEnumerated val) {
        this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().setCallSessionRelated(PrivacyCheckRelatedAction.getPrivacyCheckRelatedAction(val.intValue()));
        this.testerHost.markStore();
    }

    @Override
    public PrivacyCheckRelatedActionEnumerated getCallSessionRelated() {
        return new PrivacyCheckRelatedActionEnumerated(this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getCallSessionRelated().getAction());
    }

    @Override
    public boolean getMoLrShortCircuitIndicator() {
        return this.testerHost.getConfigurationData().
            getTestLcsServerConfigurationData().getMoLrShortCircuitIndicator();
    }

    @Override
    public void setMoLrShortCircuitIndicator(boolean moLrShortCircuitIndicator) {
        this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().setMoLrShortCircuitIndicator(moLrShortCircuitIndicator);
        this.testerHost.markStore();
    }

    @Override
    public LCSEventType getLCSEventType() {
        return new LCSEventType(this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getLCSEvent().getEvent());
    }

    @Override
    public void setLCSEventType(LCSEventType val) {
        this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().setLCSEvent(LCSEvent.getLCSEvent(val.intValue()));
        this.testerHost.markStore();
    }

    @Override
    public LCSEvent getLCSEvent() {
        return this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getLCSEvent();
    }

    @Override
    public void setLCSEvent(LCSEvent lcsEvent) {
        this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().setLCSEvent(lcsEvent);
        this.testerHost.markStore();
    }

    @Override
    public Integer getCellId() {
        return this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getCellId();
    }

    @Override
    public void setCellId(Integer cellId) {
        this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().setCellId(cellId);
        this.testerHost.markStore();
    }

    @Override
    public Integer getLAC() {
        return this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getLAC();
    }

    @Override
    public void setLAC(Integer lac) {
        this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().setLAC(lac);
        this.testerHost.markStore();
    }

    @Override
    public Integer getMNC() {
        return this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getMNC();
    }

    @Override
    public void setMNC(Integer mnc) {
        this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().setMNC(mnc);
        this.testerHost.markStore();
    }

    @Override
    public Integer getMCC() {
        return this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getMCC();
    }

    @Override
    public void setMCC(Integer mcc) {
        this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().setMCC(mcc);
        this.testerHost.markStore();
    }

    @Override
    public Integer getAgeOfLocationEstimate() {
        return this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getAgeOfLocationEstimate();
    }

    @Override
    public void setAgeOfLocationEstimate(Integer ageLocationEstimate) {
        this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().setAgeOfLocationEstimate(ageLocationEstimate);
        this.testerHost.markStore();
    }

    @Override
    public String getHGMLCAddress() {
        return this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getHGMLCAddress();
    }

    @Override
    public void setHGMLCAddress(String hgmlcAddress) {
        this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().setHGMLCAddress(hgmlcAddress);
        this.testerHost.markStore();
    }

    @Override
    public Integer getLCSReferenceNumber() {
        return this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getLCSReferenceNumber();
    }

    @Override
    public void setLCSReferenceNumber(Integer lcsReferenceNumber) {
        this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().setLCSReferenceNumber(lcsReferenceNumber);
        this.testerHost.markStore();
    }

    @Override
    public String getMSISDN() {
        return this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getMSISDN();
    }

    @Override
    public void setMSISDN(String msisdn) {
        this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().setMSISDN(msisdn);
        this.testerHost.markStore();
    }

    @Override
    public String getLMSI() {
        return this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getLMSI();
    }

    @Override
    public void setLMSI(String lmsi) {
        this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().setLMSI(lmsi);
        this.testerHost.markStore();
    }

    @Override
    public String getIMEI() {
        return this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getIMEI();
    }

    @Override
    public void setIMEI(String imei) {
        this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().setIMEI(imei);
        this.testerHost.markStore();
    }

    @Override
    public String getIMSI() {
        return this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getIMSI();
    }

    @Override
    public void setIMSI(String imsi) {
        this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().setIMSI(imsi);
        this.testerHost.markStore();
    }

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
        this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().setNumberingPlanType(NumberingPlan.getInstance(val.intValue()));
        this.testerHost.markStore();
    }

    @Override
    public void setAreaType(AreaTypeEnumerated val) {
        this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().setAreaType(AreaType.getAreaType(val.intValue()));
        this.testerHost.markStore();
    }

    @Override
    public AreaTypeEnumerated getAreaType() {
        return new AreaTypeEnumerated(this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getAreaType().getType());
    }

    @Override
    public OccurrenceInfoEnumerated getOccurrenceInfo() {
        return new OccurrenceInfoEnumerated(this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getOccurrenceInfo().getInfo());
    }

    @Override
    public void setOccurrenceInfo(OccurrenceInfoEnumerated occurrenceInfo) {
        this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().setOccurrenceInfo(OccurrenceInfo.getOccurrenceInfo(occurrenceInfo.intValue()));
        this.testerHost.markStore();
    }

    @Override
    public Integer getIntervalTime() {
        return this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getIntervalTime();
    }

    @Override
    public void setIntervalTime(Integer intervalTime) {
        this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().setIntervalTime(intervalTime);
        this.testerHost.markStore();
    }

    @Override
    public void setReportingAmount(Integer val) {
        this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().setReportingAmount(val);
        this.testerHost.markStore();
    }

    @Override
    public Integer getReportingAmount() {
        return this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getReportingAmount();
    }

    @Override
    public void setReportingInterval(Integer val) {
        this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().setReportingInterval(val);
        this.testerHost.markStore();
    }

    @Override
    public Integer getReportingInterval() {
        return this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getReportingInterval();
    }

    @Override
    public void setDataCodingScheme(Integer val) {
        this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().setDataCodingScheme(val);
        this.testerHost.markStore();
    }

    @Override
    public Integer getDataCodingScheme() {
        return this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getDataCodingScheme();
    }

    @Override
    public String getCurrentRequestDef() {
        return "LastDialog: " + currentRequestDef;
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

    @Override
    public void putLCSEventType(String val) {
        LCSEventType x = LCSEventType.createInstance(val);
        if (x != null)
            this.setLCSEventType(x);
    }

    @Override
    public String getNaESRDAddress() {
        return this.testerHost.getConfigurationData().getTestLcsClientConfigurationData().getNaESRDAddress();
    }

    @Override
    public void setNaESRDAddress(String address) {
        this.testerHost.getConfigurationData().getTestLcsClientConfigurationData().setNaESRDAddress(address);
        this.testerHost.markStore();
    }

    //TODO move this helper method to constructor type...
    private GSNAddress createGSNAddress(String gsnAddress) throws MAPException {
        try {
            //From InetAddress javadoc "the host name can either be a machine name, such as "java.sun.com", or a textual representation of its IP address.
            //If a literal IP address is supplied, only the validity of the address format is checked".
            InetAddress address = InetAddress.getByName(gsnAddress);
            GSNAddressAddressType addressType = null;
            if (address instanceof Inet4Address) {
                addressType = GSNAddressAddressType.IPv4;
            } else if (address instanceof Inet6Address) {
                addressType = GSNAddressAddressType.IPv6;
            }
            byte[] addressData = address.getAddress();
            return this.mapParameterFactory.createGSNAddress(addressType, addressData);

        } catch (UnknownHostException e) {
            throw new MAPException("Invalid GSNAddress", e);
        }
    }

    //*********************************************************//
    //*** Tester Host MAP LSM operations' reaction methods ***//
    //*******************************************************//

    @Override
    public SRIforLCSReaction getSRIforLCSReaction() {
        return this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getSriForLCSReaction();
    }

    @Override
    public String getSRIforLCSReaction_Value() {
        return this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getSriForLCSReaction().toString();
    }

    @Override
    public void setSRIforLCSReaction(SRIforLCSReaction val) {
        this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().setSriForLCSReaction(val);
        this.testerHost.markStore();
    }

    @Override
    public void putSRIforLCSReaction(String val) {
        SRIforLCSReaction x = SRIforLCSReaction.createInstance(val);
        if (x != null)
            this.setSRIforLCSReaction(x);
    }

    @Override
    public PSLReaction getPSLReaction() {
        return this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getPslReaction();
    }

    @Override
    public String getPSLReaction_Value() {
        return this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getPslReaction().toString();
    }

    @Override
    public void setPSLReaction(PSLReaction val) {
        this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().setPslReaction(val);
        this.testerHost.markStore();
    }

    @Override
    public void putPSLReaction(String val) {
        PSLReaction x = PSLReaction.createInstance(val);
        if (x != null)
            this.setPSLReaction(x);
    }

    @Override
    public SLRReaction getSLRReaction() {
        return this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getSlrReaction();
    }

    @Override
    public String getSLRReaction_Value() {
        return this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().getSlrReaction().toString();
    }

    @Override
    public void setSLRReaction(SLRReaction val) {
        this.testerHost.getConfigurationData().getTestLcsServerConfigurationData().setSlrReaction(val);
        this.testerHost.markStore();
    }

    @Override
    public void putSLRReaction(String val) {
        SLRReaction x = SLRReaction.createInstance(val);
        if (x != null)
            this.setSLRReaction(x);
    }


}
