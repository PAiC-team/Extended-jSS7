
package org.restcomm.protocols.ss7.tools.simulator.tests.cap;

import org.restcomm.protocols.ss7.tools.simulator.common.AddressNatureType;
import org.restcomm.protocols.ss7.tools.simulator.common.CapApplicationContextSsf;
import org.restcomm.protocols.ss7.tools.simulator.level3.NumberingPlanMapType;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface TestCapSsfManMBean {

    // Operations

    String performInitialDp(String msg);

    String performAssistRequestInstructions(String msg);

    String performApplyChargingReport(String msg);

    String performEventReportBCSM(String msg);

    String closeCurrentDialog();


    // Attributes

    CapApplicationContextSsf getCapApplicationContext();

    String getCapApplicationContext_Value();

    void setCapApplicationContext(CapApplicationContextSsf val);

    void putCapApplicationContext(String val);

    int getServiceKey();
    void setServiceKey(int serviceKey);

    EventTypeBCSMType getIdpEventTypeBCSM();
    String getIdpEventTypeBCSM_Value();
    void setIdpEventTypeBCSM(EventTypeBCSMType value);

    boolean isUseCldInsteadOfCldBCDNumber();
    void setUseCldInsteadOfCldBCDNumber(boolean value);

    String getCallingPartyNumberAddress();
    void setCallingPartyNumberAddress(String callingPartyNumberAddress);
    IsupNatureOfAddressIndicatorType getCallingPartyNumberNatureOfAddress();
    String getCallingPartyNumberNatureOfAddress_Value();
    void setCallingPartyNumberNatureOfAddress(IsupNatureOfAddressIndicatorType callingPartyNumberNature);
    IsupNumberingPlanIndicatorType getCallingPartyNumberNumberingPlan();
    String getCallingPartyNumberNumberingPlan_Value();
    void setCallingPartyNumberNumberingPlan(IsupNumberingPlanIndicatorType callingPartyNumberPlan);

    String getCalledPartyBCDNumberAddress();
    void setCalledPartyBCDNumberAddress(String calledPartyBCDNumberAddress);
    AddressNatureType getCalledPartyBCDNumberAddressNature();
    String getCalledPartyBCDNumberAddressNature_Value();
    void setCalledPartyBCDNumberAddressNature(AddressNatureType val);
    NumberingPlanMapType getCalledPartyBCDNumberNumberingPlan();
    String getCalledPartyBCDNumberNumberingPlan_Value();
    void setCalledPartyBCDNumberNumberingPlan(NumberingPlanMapType val);

    String getCalledPartyNumberAddress();
    void setCalledPartyNumberAddress(String calledPartyNumberAddress);
    IsupNatureOfAddressIndicatorType getCalledPartyNumberNatureOfAddress();
    String getCalledPartyNumberNatureOfAddress_Value();
    void setCalledPartyNumberNatureOfAddress(IsupNatureOfAddressIndicatorType calledPartyNumberNatureOfAddress);
    IsupNumberingPlanIndicatorType getCalledPartyNumberNumberingPlan();
    String getCalledPartyNumberNumberingPlan_Value();
    void setCalledPartyNumberNumberingPlan(IsupNumberingPlanIndicatorType calledPartyNumberNumberingPlan);

    String getMscAddressAddress();
    void setMscAddressAddress(String mscAddressAddress);
    AddressNatureType getMscAddressNatureOfAddress();
    String getMscAddressNatureOfAddress_Value();
    void setMscAddressNatureOfAddress(AddressNatureType val);
    NumberingPlanMapType getMscAddressNumberingPlan();
    String getMscAddressNumberingPlan_Value();
    void setMscAddressNumberingPlan(NumberingPlanMapType val);


    // Other

    String getCurrentRequestDef();

    // Methods for configurable properties via HTTP interface for values that are based on EnumeratedBase abstract class

    void putCallingPartyNumberNatureOfAddress(String value);
    void putCallingPartyNumberNumberingPlan(String value);
    void putCalledPartyNumberNatureOfAddress(String value);
    void putCalledPartyNumberNumberingPlan(String value);
    void putIdpEventTypeBCSM(String value);
    void putCalledPartyBCDNumberAddressNature(String val);
    void putCalledPartyBCDNumberNumberingPlan(String val);
    void putMscAddressNatureOfAddress(String val);
    void putMscAddressNumberingPlan(String val);

}
