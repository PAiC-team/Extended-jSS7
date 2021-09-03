package org.restcomm.protocols.ss7.tools.simulator.tests.ati;

import org.restcomm.protocols.ss7.tools.simulator.common.AddressNatureType;
import org.restcomm.protocols.ss7.tools.simulator.level3.NumberingPlanMapType;

/**
*
* @author sergey vetyutnev
*
*/
public interface TestAtiClientManMBean {

    AddressNatureType getAddressNature();

    String getAddressNature_Value();

    void setAddressNature(AddressNatureType val);

    NumberingPlanMapType getNumberingPlan();

    String getNumberingPlan_Value();

    void setNumberingPlan(NumberingPlanMapType val);

    SubscriberIdentityType getSubscriberIdentityType();

    String getSubscriberIdentityType_Value();

    void setSubscriberIdentityType(SubscriberIdentityType val);

    boolean isGetLocationInformation();

    void setGetLocationInformation(boolean val);

    boolean isGetSubscriberState();

    void setGetSubscriberState(boolean val);

    boolean isGetCurrentLocation();

    void setGetCurrentLocation(boolean val);

    AtiDomainType getGetRequestedDomain();

    String getGetRequestedDomain_Value();

    void setGetRequestedDomain(AtiDomainType val);

    boolean isGetImei();

    void setGetImei(boolean val);

    boolean isGetMsClassmark();

    void setGetMsClassmark(boolean val);

    boolean isGetMnpRequestedInfo();

    void setGetMnpRequestedInfo(boolean val);

    String getGsmSCFAddress();

    void setGsmSCFAddress(String val);


    void putAddressNature(String val);

    void putNumberingPlan(String val);

    void putSubscriberIdentityType(String val);

    void putGetRequestedDomain(String val);


    String performAtiRequest(String address);

    String getCurrentRequestDef();

    String closeCurrentDialog();

}
