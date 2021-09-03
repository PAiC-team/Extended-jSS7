package org.restcomm.protocols.ss7.tools.simulator.tests.psi;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.ProvideSubscriberInfoRequest;
import org.restcomm.protocols.ss7.tools.simulator.common.AddressNatureType;
import org.restcomm.protocols.ss7.tools.simulator.level3.NumberingPlanMapType;
import org.restcomm.protocols.ss7.tools.simulator.tests.sms.SRIReaction;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public interface TestPsiServerManMBean {

  //Operations
  String performSendRoutingInfoForSMResponse();

  String performSendRoutingInformationResponse();

  void onProvideSubscriberInfoRequest(ProvideSubscriberInfoRequest provideSubscriberInfoRequest);

  String performProvideSubscriberInfoResponse();

  //Attributes

  AddressNatureType getAddressNature();

  String getNumberingPlan();

  NumberingPlanMapType getNumberingPlanType();

  void setAddressNature(AddressNatureType addressNatureType);

  void setNumberingPlan(String numberingPlan);

  void setNumberingPlanType(NumberingPlanMapType numberingPlanMapType);

  void putAddressNature(String addressNature);

  void putNumberingPlanType(String NumberingPlanType);

  /********************/

  String getNetworkNodeNumber();

  void setNetworkNodeNumber(String networkNodeNumber);

  String getVmscAddress();

  void setVmscAddress(String vmscAddress);

  String getImsi();

  void setImsi(String imsi);

  String getLmsi();

  void setLmsi(String lmsi);

  int getMcc();

  void setMcc(int mcc);

  int getMnc();

  void setMnc(int mnc);

  int getLac();

  void setLac(int lac);

  int getCi();

  void setCi(int ci);

  int getAol();

  void setAol(int aol);

  boolean isSaiPresent();

  void setSaiPresent(boolean saiPresent);

  double getGeographicalLatitude();

  void setGeographicalLatitude(double geographicalLatitude);

  double getGeographicalLongitude();

  void setGeographicalLongitude(double geographicalLongitude);

  double getGeographicalUncertainty();

  void setGeographicalUncertainty(double geographicalUncertainty) ;

  int getScreeningAndPresentationIndicators();

  void setScreeningAndPresentationIndicators(int screeningAndPresentationIndicators);

  double getGeodeticLatitude();

  void setGeodeticLatitude(double geodeticLatitude);

  double getGeodeticLongitude();

  void setGeodeticLongitude(double geodeticLongitude);

  double getGeodeticUncertainty();

  void setGeodeticUncertainty(double geodeticUncertainty);

  int getGeodeticConfidence();

  void setGeodeticConfidence(int geodeticConfidence);

  boolean isCurrentLocationRetrieved();

  void setCurrentLocationRetrieved(boolean currentLocationRetrieved);

  String getImei();

  void setImei(String imei);

  /*************************/

  SRIReaction getSRIReaction();

  String getSRIReaction_Value();

  void setSRIReaction(SRIReaction val);

  void putSRIReaction(String val);

  PSIReaction getPSIReaction();

  String getPSIReaction_Value();

  void setPSIReaction(PSIReaction val);

  void putPSIReaction(String val);

  String getCurrentRequestDef();

}
