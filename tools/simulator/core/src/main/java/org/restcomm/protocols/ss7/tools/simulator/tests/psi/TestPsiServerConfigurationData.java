package org.restcomm.protocols.ss7.tools.simulator.tests.psi;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.isup.impl.message.parameter.LocationNumberImpl;
import org.restcomm.protocols.ss7.isup.message.parameter.LocationNumber;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParameterFactory;

import org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan;
import org.restcomm.protocols.ss7.map.api.primitives.AddressNature;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.CellGlobalIdOrServiceAreaIdOrLAI;
import org.restcomm.protocols.ss7.map.api.primitives.DiameterIdentity;
import org.restcomm.protocols.ss7.map.api.primitives.IMEI;

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
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.PSSubscriberStateChoice;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.RAIdentity;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.PDPContextInfo;


import org.restcomm.protocols.ss7.map.primitives.DiameterIdentityImpl;
import org.restcomm.protocols.ss7.map.primitives.IMEIImpl;
import org.restcomm.protocols.ss7.map.primitives.ISDNAddressStringImpl;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.LSAIdentity;

import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.SubscriberInfoImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.LocationInformationImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.LocationInformationEPSImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.GeographicalInformationImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.GeodeticInformationImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.SubscriberStateImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.MNPInfoResImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.RouteingNumberImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.EUtranCgiImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.TAIdImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.RAIdentityImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.LocationNumberMapImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.LocationInformationGPRSImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.PSSubscriberStateImpl;

import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.LSAIdentityImpl;

import org.restcomm.protocols.ss7.tools.simulator.tests.sms.SRIReaction;

import java.util.ArrayList;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>*
 */
public class TestPsiServerConfigurationData {

    protected static final String SRIforSM_REACTION = "sriForSMReaction";
    protected SRIReaction sriForSMReaction = new SRIReaction(SRIReaction.VAL_RETURN_SUCCESS);
    protected static final String PSI_REACTION = "psiReaction";
    protected PSIReaction psiReaction = new PSIReaction(PSIReaction.VAL_RETURN_SUCCESS);

    protected static final String ADDRESS_NATURE = "addressNature";
    protected static final String NUMBERING_PLAN_TYPE = "numberingPlanType";
    protected static final String NUMBERING_PLAN = "numberingPlan";
    protected static final String SERVICE_CENTER_ADDRESS = "serviceCenterAddress";
    protected static final String MAP_PROTOCOL_VERSION = "mapProtocolVersion";
    protected static final String SRI_RESPONSE_IMSI = "sriResponseImsi";
    protected static final String SRI_RESPONSE_VLR = "sriResponseVlr";
    protected static final String SMSC_SSN = "smscSsn";
    protected static final String NATIONAL_LANGUAGE_CODE = "nationalLanguageCode";
    protected static final String TYPE_OF_NUMBER = "typeOfNumber";
    protected static final String NUMBERING_PLAN_IDENTIFICATION = "numberingPlanIdentification";
    protected static final String SMS_CODING_TYPE = "smsCodingType";
    protected static final String SRI_REACTION = "sriReaction";
    protected static final String SRI_INFORM_SERVICE_CENTER = "sriInformServiceCenter";
    protected static final String SRI_SC_ADDRESS_NOT_INCLUDED = "sriScAddressNotIncluded";
    protected static final String MT_FSM_REACTION = "mtFSMReaction";
    protected static final String ESM_DEL_STAT = "esmDelStat";
    protected static final String ONE_NOTIFICATION_FOR_100_DIALOGS = "oneNotificationFor100Dialogs";
    protected static final String RETURN_20_PERS_DELIVERY_ERRORS = "return20PersDeliveryErrors";
    protected static final String CONTINUE_DIALOG = "continueDialog";
    protected static final String IMSI = "imsi";
    protected static final String LMSI = "lmsi";
    protected static final String NETWORK_NODE_NUMBER_ADDRESS = "networkNodeNumber";
    protected static final String IMEI = "imei";
    protected static final String MSISDN = "msisdn";
    protected static final String MCC = "mcc";
    protected static final String MNC = "mnc";
    protected static final String LAC = "algo";
    protected static final String CI = "ci";
    protected static final String MSC_NUMBER = "mscNumber";
    protected static final String VLR_NUMBER = "vlrNumber";
    protected static final String AOL = "aol";
    protected static final String SAI_PRESENT = "saiPresent";
    protected static final String GEOGRAPHICAL_TYPE_OF_SHAPE = "geographicalTypeOfShape";
    protected static final String GEOGRAPHICAL_LATITUDE = "geographicalLatitude";
    protected static final String GEOGRAPHICAL_LONGITUDE = "geographicalLongitude";
    protected static final String GEOGRAPHICAL_UNCERTAINTY = "geographicalUncertainty";
    protected static final String GEODETIC_TYPE_OF_SHAPE = "geodeticTypeOfShape";
    protected static final String GEODETIC_LATITUDE = "geodeticLatitude";
    protected static final String GEODETIC_LONGITUDE = "geodeticLongitude";
    protected static final String GEODETIC_UNCERTAINTY = "geodeticUncertainty";
    protected static final String GEODETIC_CONFIDENCE = "geodeticConfidence";
    protected static final String CURRENT_LOCATION_RETRIEVED = "currentLocationRetrieved";
    protected static final String MME_NAME = "mmeName";
    protected static final String SUBSCRIBER_STATE = "subscriberState";
    protected static final String ROUTEING_NUMBER = "routeingNumber";
    protected static final String MAP_EXTENSION_CONTAINER = "mapExtensionContainer";
    protected static final String MS_CLASSMARK_2 = "msClassmark2";
    protected static final String E_UTRAN_CGI = "eUtranCgi";
    protected static final String TA_ID = "taId";
    protected static final String PS_SUBSCRIBER_STATE = "psSubscriberState";
    protected static final String LSA_IDENTITY = "lsaIdentity";
    protected static final String RA_ID = "RouteingAreaId";
    protected static final String LOCATION_NUMBER_MAP = "locationNumberMap";
    protected static final String USER_CGI_INFORMATION = "lsaIdentity";
    protected static final String LOCATION_INFORMATION_GPRS = "locationInformationGPRS";
    protected static final String GPRS_MS_CLASS = "gprsmsClass";


    private MAPParameterFactory mapParameterFactory;
    private AddressNature addressNature = AddressNature.international_number;
    private NumberingPlan numberingPlanType = NumberingPlan.ISDN;
    private String numberingPlan = "1";
    private String networkNodeNumber = "59804800025";
    private String imsi = "748010192837465";
    private String lmsi = "11121314";
    private int mcc = 748;
    private int mnc = 1;
    private int lac = 5;
    private int ci = 3479;
    private String mscAddress = "5982123007";
    private String vmscAddress = "5982123007";
    private String vlrAddress = "5982123007";
    private String sgsnAddress = "5982133021";
    private ISDNAddressString mscNumber = new ISDNAddressStringImpl(AddressNature.international_number, NumberingPlan.ISDN, mscAddress);
    private ISDNAddressString vmscNumber = new ISDNAddressStringImpl(AddressNature.international_number, NumberingPlan.ISDN, vmscAddress);
    private ISDNAddressString vlrNumber = new ISDNAddressStringImpl(AddressNature.international_number, NumberingPlan.ISDN, vlrAddress);
    private ISDNAddressString sgsnNumber = new ISDNAddressStringImpl(AddressNature.international_number, NumberingPlan.ISDN, sgsnAddress);
    private int aol = 1;
    private boolean saiPresent = false;
    private TypeOfShape geographicalTypeOfShape = TypeOfShape.EllipsoidPointWithUncertaintyCircle;
    private Double geographicalLatitude = -23.291032;
    private Double geographicalLongitude = 109.977810;
    private Double geographicalUncertainty = 50.0;
    private int screeningAndPresentationIndicators = 3;
    private TypeOfShape geodeticTypeOfShape = TypeOfShape.EllipsoidPointWithUncertaintyCircle;
    private Double geodeticLatitude = -24.010010;
    private Double geodeticLongitude = 110.00987;
    private Double geodeticUncertainty = 100.0;
    private int geodeticConfidence = 1;
    private LocationInformationEPS locationInformationEPS = null;
    private boolean currentLocationRetrieved = true;
    byte[] lteCgi = {53, 48, 57, 50, 49, 55, 49};
    private String eUtranCgiString = "eUtranCgiString"; // new String(lteCgi.getClass().toString());
    EUtranCgi eUtranCgi = new EUtranCgiImpl(lteCgi);
    byte[] trackinAreaId = {49, 51, 50, 57, 53};
    private String taIdString = "taIdString"; //trackinAreaId.getClass().toString();
    TAId taId = new TAIdImpl(trackinAreaId);
    private MAPExtensionContainer mapExtensionContainer = null;
    byte[] mmeNom = {77, 77, 69, 55, 52, 56, 48, 48, 48, 49};
    private String mmeNameString = "mmeNameString"; //mmeNom.getClass().getName();
    private DiameterIdentity mmeName = new DiameterIdentityImpl(mmeNom);
    private LSAIdentity lsaIdentity = null;
    private UserCSGInformation userCSGInformation = null;
    SubscriberStateChoice subscriberStateChoice = SubscriberStateChoice.assumedIdle;
    private String subscriberStateStr = subscriberStateChoice.toString();
    NotReachableReason notReachableReason = NotReachableReason.restrictedArea;
    private String notReachableReasonStr = notReachableReason.toString();
    private SubscriberState subscriberState = new SubscriberStateImpl(subscriberStateChoice, notReachableReason);
    LocationInformationGPRS locationInformationGPRS = null;
    private PSSubscriberStateChoice psSubscriberStateChoice = PSSubscriberStateChoice.psAttachedReachableForPaging;
    ArrayList<PDPContextInfo> pdpContextInfoList = null;
    private PSSubscriberState psSubscriberState = new PSSubscriberStateImpl(psSubscriberStateChoice, notReachableReason, pdpContextInfoList);
    GPRSMSClass gprsmsClass = null;
    String imei = "01171400466105";
    IMEI iMei = new IMEIImpl(imei);
    MSClassmark2 msClassmark2 = null;
    private String routeingNum = "MNP598";
    RouteingNumber routeingNumber = new RouteingNumberImpl(routeingNum);
    String msisdnStr = "59899077937";
    ISDNAddressString msisdn = new ISDNAddressStringImpl(AddressNature.international_number,
            NumberingPlan.ISDN, msisdnStr);
    byte[] lsaId = {49, 51, 50};
    private String lsaIdString = "lsaIdString"; //lsaId.getClass().toString();
    LSAIdentity selectedLSAIdentity = new LSAIdentityImpl(lsaId);
    byte[] raId = {49, 51, 50, 57, 53, 50};
    private String raIdString = raId.getClass().toString();
    RAIdentity routeingAreaIdentity = new RAIdentityImpl(raId);
    int natureOfAddressIndicator = 4;
    String locationNumberAddressDigits= "819203961904";
    int numberingPlanIndicator = 1;
    int internalNetworkNumberIndicator = 1;
    int addressRepresentationRestrictedIndicator = 1;
    int screeningIndicator = 3;
    private LocationNumber locationNumber = new LocationNumberImpl(natureOfAddressIndicator, locationNumberAddressDigits, numberingPlanIndicator,
            internalNetworkNumberIndicator, addressRepresentationRestrictedIndicator, screeningIndicator);
    private LocationNumberMap locationNumberMap;

    {
        try {
            locationNumberMap = new LocationNumberMapImpl(locationNumber);
        } catch (MAPException e) {
            e.printStackTrace();
        }
    }

    NumberPortabilityStatus numberPortabilityStatus = NumberPortabilityStatus.ownNumberNotPortedOut;

    private CellGlobalIdOrServiceAreaIdOrLAI cellGlobalIdOrServiceAreaIdOrLAI;
    private GeographicalInformation geographicalInformation;
    private GeodeticInformation geodeticInformation;
    private SubscriberInfo subscriberInfo;
    private LocationInformation locationInformation;
    private MNPInfoRes mnpInfoRes;

    {
        try {
            geographicalInformation = new GeographicalInformationImpl(geographicalTypeOfShape, geographicalLatitude, geographicalLongitude, geographicalUncertainty);
            geodeticInformation = new GeodeticInformationImpl(screeningAndPresentationIndicators, geodeticTypeOfShape, geodeticLatitude, geodeticLongitude, geodeticUncertainty, geodeticConfidence);
            locationInformationEPS = new LocationInformationEPSImpl(eUtranCgi, taId, mapExtensionContainer, geographicalInformation,
                    geodeticInformation, currentLocationRetrieved, aol, mmeName);
            locationInformation = new LocationInformationImpl(aol, geographicalInformation, vlrNumber, locationNumberMap, cellGlobalIdOrServiceAreaIdOrLAI, mapExtensionContainer, lsaIdentity, mscNumber, geodeticInformation, currentLocationRetrieved,
                    saiPresent, locationInformationEPS, userCSGInformation);
            locationInformationGPRS = new LocationInformationGPRSImpl(cellGlobalIdOrServiceAreaIdOrLAI, routeingAreaIdentity, geographicalInformation, sgsnNumber,
                    selectedLSAIdentity, mapExtensionContainer, saiPresent, geodeticInformation, currentLocationRetrieved, aol);
            mnpInfoRes = new MNPInfoResImpl(routeingNumber, null, msisdn, numberPortabilityStatus, mapExtensionContainer);
            subscriberInfo = new SubscriberInfoImpl(locationInformation, subscriberState, mapExtensionContainer,
                    locationInformationGPRS, psSubscriberState, iMei, msClassmark2, gprsmsClass, mnpInfoRes);
        } catch (MAPException e) {
            e.printStackTrace();
        }
    }

    public SRIReaction getSriForSMReaction() {
        return sriForSMReaction;
    }

    public void setSriForSMReaction(SRIReaction sriForSMReaction) {
        this.sriForSMReaction = sriForSMReaction;
    }

    public PSIReaction getPsiReaction() {
        return psiReaction;
    }

    public void setPsiReaction(PSIReaction psiReaction) {
        this.psiReaction = psiReaction;
    }

    public AddressNature getAddressNature() {
        return addressNature;
    }

    public void setAddressNature(AddressNature addressNature) {
        this.addressNature = addressNature;
    }

    public NumberingPlan getNumberingPlanType() {
        return numberingPlanType;
    }

    public void setNumberingPlanType(NumberingPlan numberingPlanType) {
        this.numberingPlanType = numberingPlanType;
    }

    public static String getNumberingPlan() {
        return NUMBERING_PLAN;
    }

    public void setNumberingPlan(String numberingPlan) {
        this.numberingPlan = numberingPlan;
    }

    public String getNetworkNodeNumber() {
        return networkNodeNumber;
    }

    public void setNetworkNodeNumber(String networkNodeNumber) {
        this.networkNodeNumber = networkNodeNumber;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getLmsi() {
        return lmsi;
    }

    public void setLmsi(String lmsi) {
        this.lmsi = lmsi;
    }

    public org.restcomm.protocols.ss7.map.api.primitives.IMEI getiMei() {
        return iMei;
    }

    public void setiMei(org.restcomm.protocols.ss7.map.api.primitives.IMEI iMei) {
        this.iMei = iMei;
    }

    public int getMcc() {
        return mcc;
    }

    public void setMcc(int mcc) {
        this.mcc = mcc;
    }

    public int getMnc() {
        return mnc;
    }

    public void setMnc(int mnc) {
        this.mnc = mnc;
    }

    public int getLac() {
        return lac;
    }

    public void setLac(int lac) {
        this.lac = lac;
    }

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public String getMscAddress() {
        return mscAddress;
    }

    public void setMscAddress(String mscAddress) {
        this.mscAddress = mscAddress;
    }

    public String getVmscAddress() {
        return vmscAddress;
    }

    public void setVmscAddress(String vmscAddress) {
        this.vmscAddress = vmscAddress;
    }

    public ISDNAddressString getVmscNumber() {
        return vmscNumber;
    }

    public void setVmscNumber(ISDNAddressString vmscNumber) {
        this.vmscNumber = vmscNumber;
    }

    public String getVlrAddress() {
        return vlrAddress;
    }

    public void setVlrAddress(String vlrAddress) {
        this.vlrAddress = vlrAddress;
    }

    public ISDNAddressString getMscNumber() {
        return mscNumber;
    }

    public void setMscNumber(ISDNAddressString mscNumber) {
        this.mscNumber = mscNumber;
    }

    public ISDNAddressString getVlrNumber() {
        return vlrNumber;
    }

    public void setVlrNumber(ISDNAddressString vlrNumber) {
        this.vlrNumber = vlrNumber;
    }

    public ISDNAddressString getSgsnNumber() {
        return sgsnNumber;
    }

    public void setSgsnNumber(ISDNAddressString sgsnNumber) {
        this.sgsnNumber = sgsnNumber;
    }

    public String getSgsnAddress() {
        return sgsnAddress;
    }

    public void setSgsnAddress(String sgsnAddress) {
        this.sgsnAddress = sgsnAddress;
    }

    public String getTaIdString() {
        return taIdString;
    }

    public void setTaIdString(String taIdString) {
        this.taIdString = taIdString;
    }

    public String getLsaIdString() {
        return lsaIdString;
    }

    public void setLsaIdString(String lsaIdString) {
        this.lsaIdString = lsaIdString;
    }

    public String getRaIdString() {
        return raIdString;
    }

    public void setRaIdString(String raIdString) {
        this.raIdString = raIdString;
    }

    public int getAol() {
        return aol;
    }

    public void setAol(int aol) {
        this.aol = aol;
    }

    public boolean isSaiPresent() {
        return saiPresent;
    }

    public void setSaiPresent(boolean saiPresent) {
        this.saiPresent = saiPresent;
    }

    public TypeOfShape getGeographicalTypeOfShape() {
        return geographicalTypeOfShape;
    }

    public void setGeographicalTypeOfShape(TypeOfShape geographicalTypeOfShape) {
        this.geographicalTypeOfShape = geographicalTypeOfShape;
    }

    public Double getGeographicalLatitude() {
        return geographicalLatitude;
    }

    public void setGeographicalLatitude(Double geographicalLatitude) {
        this.geographicalLatitude = geographicalLatitude;
    }

    public Double getGeographicalLongitude() {
        return geographicalLongitude;
    }

    public void setGeographicalLongitude(Double geographicalLongitude) {
        this.geographicalLongitude = geographicalLongitude;
    }

    public Double getGeographicalUncertainty() {
        return geographicalUncertainty;
    }

    public void setGeographicalUncertainty(Double geographicalUncertainty) {
        this.geographicalUncertainty = geographicalUncertainty;
    }

    public int getScreeningAndPresentationIndicators() {
        return screeningAndPresentationIndicators;
    }

    public void setScreeningAndPresentationIndicators(int screeningAndPresentationIndicators) {
        this.screeningAndPresentationIndicators = screeningAndPresentationIndicators;
    }

    public TypeOfShape getGeodeticTypeOfShape() {
        return geodeticTypeOfShape;
    }

    public void setGeodeticTypeOfShape(TypeOfShape geodeticTypeOfShape) {
        this.geodeticTypeOfShape = geodeticTypeOfShape;
    }

    public Double getGeodeticLatitude() {
        return geodeticLatitude;
    }

    public void setGeodeticLatitude(Double geodeticLatitude) {
        this.geodeticLatitude = geodeticLatitude;
    }

    public Double getGeodeticLongitude() {
        return geodeticLongitude;
    }

    public void setGeodeticLongitude(Double geodeticLongitude) {
        this.geodeticLongitude = geodeticLongitude;
    }

    public Double getGeodeticUncertainty() {
        return geodeticUncertainty;
    }

    public void setGeodeticUncertainty(Double geodeticUncertainty) {
        this.geodeticUncertainty = geodeticUncertainty;
    }

    public int getGeodeticConfidence() {
        return geodeticConfidence;
    }

    public void setGeodeticConfidence(int geodeticConfidence) {
        this.geodeticConfidence = geodeticConfidence;
    }

    public LocationInformationEPS getLocationInformationEPS() {
        return locationInformationEPS;
    }

    public void setLocationInformationEPS(LocationInformationEPS locationInformationEPS) {
        this.locationInformationEPS = locationInformationEPS;
    }

    public boolean isCurrentLocationRetrieved() {
        return currentLocationRetrieved;
    }

    public void setCurrentLocationRetrieved(boolean currentLocationRetrieved) {
        this.currentLocationRetrieved = currentLocationRetrieved;
    }

    public String getMmeNameString() {
        return mmeNameString;
    }

    public void setMmeNameString(String mmeNameString) {
        this.mmeNameString = mmeNameString;
    }

    public EUtranCgi geteUtranCgi() {
        return eUtranCgi;
    }

    public void seteUtranCgi(EUtranCgi eUtranCgi) {
        this.eUtranCgi = eUtranCgi;
    }

    public String geteUtranCgiString() {
        return eUtranCgiString;
    }

    public void seteUtranCgiString(String eUtranCgiString) {
        this.eUtranCgiString = eUtranCgiString;
    }

    public TAId getTaId() {
        return taId;
    }

    public void setTaId(TAId taId) {
        this.taId = taId;
    }

    public int getNatureOfAddressIndicator() {
        return natureOfAddressIndicator;
    }

    public void setNatureOfAddressIndicator(int natureOfAddressIndicator) {
        this.natureOfAddressIndicator = natureOfAddressIndicator;
    }

    public String getLocationNumberAddressDigits() {
        return locationNumberAddressDigits;
    }

    public void setLocationNumberAddressDigits(String locationNumberAddressDigits) {
        this.locationNumberAddressDigits = locationNumberAddressDigits;
    }

    public int getNumberingPlanIndicator() {
        return numberingPlanIndicator;
    }

    public void setNumberingPlanIndicator(int numberingPlanIndicator) {
        this.numberingPlanIndicator = numberingPlanIndicator;
    }

    public int getInternalNetworkNumberIndicator() {
        return internalNetworkNumberIndicator;
    }

    public void setInternalNetworkNumberIndicator(int internalNetworkNumberIndicator) {
        this.internalNetworkNumberIndicator = internalNetworkNumberIndicator;
    }

    public int getAddressRepresentationRestrictedIndicator() {
        return addressRepresentationRestrictedIndicator;
    }

    public void setAddressRepresentationRestrictedIndicator(int addressRepresentationRestrictedIndicator) {
        this.addressRepresentationRestrictedIndicator = addressRepresentationRestrictedIndicator;
    }

    public int getScreeningIndicator() {
        return screeningIndicator;
    }

    public void setScreeningIndicator(int screeningIndicator) {
        this.screeningIndicator = screeningIndicator;
    }

    public MAPExtensionContainer getMapExtensionContainer() {
        return mapExtensionContainer;
    }

    public void setMapExtensionContainer(MAPExtensionContainer mapExtensionContainer) {
        this.mapExtensionContainer = mapExtensionContainer;
    }

    public byte[] getMmeNom() {
        return mmeNom;
    }

    public void setMmeNom(byte[] mmeNom) {
        this.mmeNom = mmeNom;
    }

    public DiameterIdentity getMmeName() {
        return mmeName;
    }

    public void setMmeName(DiameterIdentity mmeName) {
        this.mmeName = mmeName;
    }

    public LSAIdentity getLsaIdentity() {
        return lsaIdentity;
    }

    public void setLsaIdentity(LSAIdentity lsaIdentity) {
        this.lsaIdentity = lsaIdentity;
    }

    public LSAIdentity getSelectedLSAIdentity() {
        return selectedLSAIdentity;
    }

    public void setSelectedLSAIdentity(LSAIdentity selectedLSAIdentity) {
        this.selectedLSAIdentity = selectedLSAIdentity;
    }

    public RAIdentity getRouteingAreaIdentity() {
        return routeingAreaIdentity;
    }

    public void setRouteingAreaIdentity(RAIdentity routeingAreaIdentity) {
        this.routeingAreaIdentity = routeingAreaIdentity;
    }

    public LocationNumberMap getLocationNumberMap() {
        return locationNumberMap;
    }

    public void setLocationNumberMap(LocationNumberMap locationNumberMap) {
        this.locationNumberMap = locationNumberMap;
    }

    public UserCSGInformation getUserCSGInformation() {
        return userCSGInformation;
    }

    public void setUserCSGInformation(UserCSGInformation userCSGInformation) {
        this.userCSGInformation = userCSGInformation;
    }

    public SubscriberStateChoice getSubscriberStateChoice() {
        return subscriberStateChoice;
    }

    public void setSubscriberStateChoice(SubscriberStateChoice subscriberStateChoice) {
        this.subscriberStateChoice = subscriberStateChoice;
    }

    public PSSubscriberStateChoice getPsSubscriberStateChoice() {
        return psSubscriberStateChoice;
    }

    public void setPsSubscriberStateChoice(PSSubscriberStateChoice psSubscriberStateChoice) {
        this.psSubscriberStateChoice = psSubscriberStateChoice;
    }

    public NotReachableReason getNotReachableReason() {
        return notReachableReason;
    }

    public void setNotReachableReason(NotReachableReason notReachableReason) {
        this.notReachableReason = notReachableReason;
    }

    public String getSubscriberStateStr() {
        return subscriberStateStr;
    }

    public void setSubscriberStateStr(String subscriberStateStr) {
        this.subscriberStateStr = subscriberStateStr;
    }

    public String getNotReachableReasonStr() {
        return notReachableReasonStr;
    }

    public void setNotReachableReasonStr(String notReachableReasonStr) {
        this.notReachableReasonStr = notReachableReasonStr;
    }

    public SubscriberState getSubscriberState() {
        return subscriberState;
    }

    public void setSubscriberState(SubscriberState subscriberState) {
        this.subscriberState = subscriberState;
    }

    public LocationInformationGPRS getLocationInformationGPRS() {
        return locationInformationGPRS;
    }

    public void setLocationInformationGPRS(LocationInformationGPRS locationInformationGPRS) {
        this.locationInformationGPRS = locationInformationGPRS;
    }

    public PSSubscriberState getPsSubscriberState() {
        return psSubscriberState;
    }

    public void setPsSubscriberState(PSSubscriberState psSubscriberState) {
        this.psSubscriberState = psSubscriberState;
    }

    public GPRSMSClass getGprsmsClass() {
        return gprsmsClass;
    }

    public void setGprsmsClass(GPRSMSClass gprsmsClass) {
        this.gprsmsClass = gprsmsClass;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public MSClassmark2 getMsClassmark2() {
        return msClassmark2;
    }

    public void setMsClassmark2(MSClassmark2 msClassmark2) {
        this.msClassmark2 = msClassmark2;
    }

    public String getRouteingNum() {
        return routeingNum;
    }

    public void setRouteingNum(String routeingNum) {
        this.routeingNum = routeingNum;
    }

    public RouteingNumber getRouteingNumber() {
        return routeingNumber;
    }

    public void setRouteingNumber(RouteingNumber routeingNumber) {
        this.routeingNumber = routeingNumber;
    }

    public String getMsisdnStr() {
        return msisdnStr;
    }

    public void setMsisdnStr(String msisdnStr) {
        this.msisdnStr = msisdnStr;
    }

    public ISDNAddressString getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(ISDNAddressString msisdn) {
        this.msisdn = msisdn;
    }

    public NumberPortabilityStatus getNumberPortabilityStatus() {
        return numberPortabilityStatus;
    }

    public void setNumberPortabilityStatus(NumberPortabilityStatus numberPortabilityStatus) {
        this.numberPortabilityStatus = numberPortabilityStatus;
    }

    public CellGlobalIdOrServiceAreaIdOrLAI getCellGlobalIdOrServiceAreaIdOrLAI() {
        return cellGlobalIdOrServiceAreaIdOrLAI;
    }

    public void setCellGlobalIdOrServiceAreaIdOrLAI(CellGlobalIdOrServiceAreaIdOrLAI cellGlobalIdOrServiceAreaIdOrLAI) {
        this.cellGlobalIdOrServiceAreaIdOrLAI = cellGlobalIdOrServiceAreaIdOrLAI;
    }

    public GeographicalInformation getGeographicalInformation() {
        return geographicalInformation;
    }

    public void setGeographicalInformation(GeographicalInformation geographicalInformation) {
        this.geographicalInformation = geographicalInformation;
    }

    public GeodeticInformation getGeodeticInformation() {
        return geodeticInformation;
    }

    public void setGeodeticInformation(GeodeticInformation geodeticInformation) {
        this.geodeticInformation = geodeticInformation;
    }

    public SubscriberInfo getSubscriberInfo() {
        return subscriberInfo;
    }

    public void setSubscriberInfo(SubscriberInfo subscriberInfo) {
        this.subscriberInfo = subscriberInfo;
    }

    public LocationInformation getLocationInformation() {
        return locationInformation;
    }

    public void setLocationInformation(LocationInformation locationInformation) {
        this.locationInformation = locationInformation;
    }

    public MNPInfoRes getMnpInfoRes() {
        return mnpInfoRes;
    }

    public void setMnpInfoRes(MNPInfoRes mnpInfoRes) {
        this.mnpInfoRes = mnpInfoRes;
    }

    public static String getServiceCenterAddress() {
        return SERVICE_CENTER_ADDRESS;
    }

    public static String getMapProtocolVersion() {
        return MAP_PROTOCOL_VERSION;
    }

    public static String getSriResponseImsi() {
        return SRI_RESPONSE_IMSI;
    }

    public static String getSriResponseVlr() {
        return SRI_RESPONSE_VLR;
    }

    public static String getSmscSsn() {
        return SMSC_SSN;
    }

    public static String getNationalLanguageCode() {
        return NATIONAL_LANGUAGE_CODE;
    }

    public static String getTypeOfNumber() {
        return TYPE_OF_NUMBER;
    }

    public static String getNumberingPlanIdentification() {
        return NUMBERING_PLAN_IDENTIFICATION;
    }

    public static String getSmsCodingType() {
        return SMS_CODING_TYPE;
    }

    public static String getSriReaction() {
        return SRI_REACTION;
    }

    public static String getSriInformServiceCenter() {
        return SRI_INFORM_SERVICE_CENTER;
    }

    public static String getSriScAddressNotIncluded() {
        return SRI_SC_ADDRESS_NOT_INCLUDED;
    }

    public static String getMtFsmReaction() {
        return MT_FSM_REACTION;
    }

    public static String getEsmDelStat() {
        return ESM_DEL_STAT;
    }

    public static String getOneNotificationFor100Dialogs() {
        return ONE_NOTIFICATION_FOR_100_DIALOGS;
    }

    public static String getReturn20PersDeliveryErrors() {
        return RETURN_20_PERS_DELIVERY_ERRORS;
    }

    public static String getContinueDialog() {
        return CONTINUE_DIALOG;
    }

    public static String getIMSI() {
        return IMSI;
    }

    public static String getLMSI() {
        return LMSI;
    }

    public static String getNetworkNodeNumberAddress() {
        return NETWORK_NODE_NUMBER_ADDRESS;
    }

    public static String getIMEI() {
        return IMEI;
    }

    public MAPParameterFactory getMapParameterFactory() {
        return mapParameterFactory;
    }

    public void setMapParameterFactory(MAPParameterFactory mapParameterFactory) {
        this.mapParameterFactory = mapParameterFactory;
    }

    public LocationNumber getLocationNumber() {
        return locationNumber;
    }

    public void setLocationNumber(LocationNumber locationNumber) {
        this.locationNumber = locationNumber;
    }

    protected static final XMLFormat<TestPsiServerConfigurationData> XML = new XMLFormat<TestPsiServerConfigurationData>(TestPsiServerConfigurationData.class) {

        public void write(TestPsiServerConfigurationData clt, OutputElement xml) throws XMLStreamException {
            xml.add(clt.psiReaction.toString(), PSI_REACTION, String.class);
            xml.add(clt.addressNature.toString(), ADDRESS_NATURE, String.class);
            xml.add(clt.numberingPlanType.toString(), NUMBERING_PLAN_TYPE, String.class);
            xml.add(clt.getNumberingPlan(), NUMBERING_PLAN, String.class);
            xml.add(clt.imsi, IMSI, String.class);
            xml.add(clt.lmsi, LMSI, String.class);
            xml.add(clt.networkNodeNumber, NETWORK_NODE_NUMBER_ADDRESS, String.class);
            xml.add(clt.imei, IMEI, String.class);
            xml.add(clt.msisdn.getAddress(), MSISDN, String.class);
            xml.add(clt.mcc, MCC, Integer.class);
            xml.add(clt.mnc, MNC, Integer.class);
            xml.add(clt.lac, LAC, Integer.class);
            xml.add(clt.ci, CI, Integer.class);
            xml.add(clt.mscNumber.getAddress(), MSC_NUMBER, String.class);
            xml.add(clt.vlrNumber.getAddress(), VLR_NUMBER, String.class);
            xml.add(clt.aol, AOL, Integer.class);
            xml.add(clt.saiPresent, SAI_PRESENT, Boolean.class);
            xml.add(clt.geographicalTypeOfShape.getCode(), GEOGRAPHICAL_TYPE_OF_SHAPE, Integer.class);
            xml.add(clt.geographicalLatitude, GEOGRAPHICAL_LATITUDE, Double.class);
            xml.add(clt.geographicalLongitude, GEOGRAPHICAL_LONGITUDE, Double.class);
            xml.add(clt.geographicalUncertainty, GEOGRAPHICAL_UNCERTAINTY, Double.class);
            xml.add(clt.geodeticTypeOfShape.getCode(), GEODETIC_TYPE_OF_SHAPE, Integer.class);
            xml.add(clt.geodeticLatitude, GEODETIC_LATITUDE, Double.class);
            xml.add(clt.geodeticLongitude, GEODETIC_LONGITUDE, Double.class);
            xml.add(clt.geodeticUncertainty, GEODETIC_UNCERTAINTY, Double.class);
            xml.add(clt.geodeticConfidence, GEODETIC_CONFIDENCE, Integer.class);
            xml.add(clt.currentLocationRetrieved, CURRENT_LOCATION_RETRIEVED, Boolean.class);
            xml.add(clt.mmeNameString, MME_NAME, String.class);
            xml.add(clt.subscriberStateStr, SUBSCRIBER_STATE, String.class);
            xml.add(clt.routeingNum, ROUTEING_NUMBER, String.class);
            xml.add(clt.eUtranCgiString, E_UTRAN_CGI, String.class);
            xml.add(clt.taIdString, TA_ID, String.class);
            xml.add(clt.lsaIdString, LSA_IDENTITY, String.class);
            xml.add(clt.locationNumberAddressDigits, LOCATION_NUMBER_MAP, String.class);
            xml.add(clt.sgsnAddress, LOCATION_INFORMATION_GPRS, String.class);
            xml.add(clt.psSubscriberStateChoice.toString(), PS_SUBSCRIBER_STATE, String.class);
        }

        public void read(XMLFormat.InputElement xml, TestPsiServerConfigurationData clt) throws XMLStreamException {
            String psiR = xml.get(PSI_REACTION, String.class);
            clt.psiReaction = PSIReaction.createInstance(psiR);
            String an = (String) xml.get(ADDRESS_NATURE, String.class);
            clt.addressNature = AddressNature.valueOf(an);
            String npt = (String) xml.get(NUMBERING_PLAN_TYPE, String.class);
            clt.numberingPlanType = NumberingPlan.valueOf(npt);
            String np = (String) xml.get(NUMBERING_PLAN, String.class);
            clt.numberingPlan = np;
            String imsi = (String) xml.get(IMSI, String.class);
            clt.imsi = imsi;
            String lmsi = (String) xml.get(LMSI, String.class);
            clt.lmsi = lmsi;
            String networkNodeNumber = (String) xml.get(NETWORK_NODE_NUMBER_ADDRESS, String.class);
            clt.networkNodeNumber = networkNodeNumber;
            String imei = (String) xml.get(IMEI, String.class);
            clt.imei = imei;
            String msisdn = (String) xml.get(MSISDN, String.class);
            clt.msisdn = new ISDNAddressStringImpl(clt.addressNature, clt.numberingPlanType, msisdn);
            Integer mcc = (Integer) xml.get(MCC, Integer.class);
            clt.mcc = mcc.intValue();
            Integer mnc = (Integer) xml.get(MNC, Integer.class);
            clt.mnc = mnc.intValue();
            Integer lac = (Integer) xml.get(LAC, Integer.class);
            clt.lac = lac.intValue();
            Integer ci = (Integer) xml.get(CI, Integer.class);
            clt.ci = ci.intValue();
            String mscNumber = (String) xml.get(MSC_NUMBER, String.class);
            clt.mscNumber = new ISDNAddressStringImpl(clt.addressNature, clt.numberingPlanType, mscNumber);
            String vlrNumber = (String) xml.get(VLR_NUMBER, String.class);
            clt.vlrNumber = new ISDNAddressStringImpl(clt.addressNature, clt.numberingPlanType, vlrNumber);
            Integer aol = (Integer) xml.get(AOL, Integer.class);
            clt.aol = aol.intValue();
            Boolean saiPresent = (Boolean) xml.get(SAI_PRESENT, Boolean.class);
            clt.saiPresent = saiPresent.booleanValue();
            Integer geographicalTypeOfShape = (Integer) xml.get(GEOGRAPHICAL_TYPE_OF_SHAPE, Integer.class);
            clt.geographicalTypeOfShape = TypeOfShape.values()[geographicalTypeOfShape.intValue()];
            Double geographicalLatitude = (Double) xml.get(GEOGRAPHICAL_LATITUDE, Double.class);
            clt.geographicalLatitude = geographicalLatitude;
            Double geographicalLongitude = (Double) xml.get(GEOGRAPHICAL_LONGITUDE, Double.class);
            clt.geographicalLongitude = geographicalLongitude;
            Double geographicalUncertainty = (Double) xml.get(GEOGRAPHICAL_UNCERTAINTY, Double.class);
            clt.geographicalUncertainty = geographicalUncertainty;
            Integer geodeticTypeOfShape = (Integer) xml.get(GEODETIC_TYPE_OF_SHAPE, Integer.class);
            clt.geodeticTypeOfShape = TypeOfShape.values()[geodeticTypeOfShape.intValue()];
            Double geodeticLatitude = (Double) xml.get(GEODETIC_LATITUDE, Double.class);
            clt.geodeticLatitude = geodeticLatitude;
            Double geodeticLongitude = (Double) xml.get(GEODETIC_LONGITUDE, Double.class);
            clt.geodeticLongitude = geodeticLongitude;
            Double geodeticUncertainty = (Double) xml.get(GEODETIC_UNCERTAINTY, Double.class);
            clt.geodeticUncertainty = geodeticUncertainty;
            Integer geodeticConfidence = (Integer) xml.get(GEODETIC_CONFIDENCE, Integer.class);
            clt.geodeticConfidence = geodeticConfidence.intValue();
            Boolean currentLocationRetrieved = (Boolean) xml.get(CURRENT_LOCATION_RETRIEVED, Boolean.class);
            clt.currentLocationRetrieved = currentLocationRetrieved.booleanValue();
            String mmeName = (String) xml.get(MME_NAME, String.class);
            clt.mmeName = new DiameterIdentityImpl();
            String subscriberStateChoice = (String) xml.get(SUBSCRIBER_STATE, String.class);
            clt.subscriberStateChoice = SubscriberStateChoice.valueOf(subscriberStateChoice);
            String routeingNumber = (String) xml.get(ROUTEING_NUMBER, String.class);
            clt.routeingNum = routeingNumber;
            String eUtranCgi = (String) xml.get(E_UTRAN_CGI, String.class);
            clt.eUtranCgi = new EUtranCgiImpl();
            String taId = (String) xml.get(TA_ID, String.class);
            clt.taId = new TAIdImpl();
            String lsaId = (String) xml.get(LSA_IDENTITY, String.class);
            clt.lsaIdentity = new LSAIdentityImpl();
            clt.locationNumberAddressDigits = (String) xml.get(LOCATION_NUMBER_MAP, String.class);
            clt.sgsnAddress = (String) xml.get(LOCATION_INFORMATION_GPRS, String.class);
            String psSubscriberStateChoiceString = (String) xml.get(PS_SUBSCRIBER_STATE, String.class);
            clt.psSubscriberStateChoice = PSSubscriberStateChoice.valueOf(psSubscriberStateChoiceString);
        }
    };
}
