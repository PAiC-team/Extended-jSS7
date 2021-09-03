package org.restcomm.protocols.ss7.tools.simulator.tests.lcs;

import org.restcomm.protocols.ss7.tools.simulator.common.AddressNatureType;
import org.restcomm.protocols.ss7.tools.simulator.level3.NumberingPlanMapType;

/**
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public interface TestLcsClientManMBean {

    // Operations
    String performSendRoutingInfoForLCSRequest();

    String performProvideSubscriberLocationRequest();

    String performSubscriberLocationReportRequest();

    String performSubscriberLocationReportResponse();

    // Other
    String getCurrentRequestDef();

    // Attributes
    String getNetworkNodeNumber();

    void setNetworkNodeNumber(String address);

    AddressNatureType getAddressNature();

    NumberingPlanMapType getNumberingPlanType();

    void setAddressNature(AddressNatureType val);

    void setNumberingPlanType(NumberingPlanMapType val);

    String getMSISDN();

    void setMSISDN(String msisdn);

    String getIMSI();

    void setIMSI(String imsi);

    Integer getCellId();

    void setCellId(Integer lac);

    String getIMEI();

    void setIMEI(String imei);

    String getHGMLCAddress();

    void setHGMLCAddress(String hgmlcAddress);

    Integer getLAC();

    void setAgeOfLocationEstimate(Integer ageOfLocationEstimate);

    Integer getAgeOfLocationEstimate();

    void setLAC(Integer lac);

    LCSEventType getLCSEventType();

    void setLCSEventType(LCSEventType val);

    Integer getLCSReferenceNumber();

    void setLCSReferenceNumber(Integer lcsReferenceNumber);

    Integer getMCC();

    void setMCC(Integer mcc);

    Integer getMNC();

    void setMNC(Integer mnc);

    String getNaESRDAddress();

    void setNaESRDAddress(String address);

    String getNaESRKAddress();

    void setNaESRKAddress(String address);

    // Methods for configurable properties via HTTP interface for values that are based on EnumeratedBase abstract class

    void putAddressNature(String val);

    void putNumberingPlanType(String val);

    void putLCSEventType(String val);


}

