
package org.restcomm.protocols.ss7.tools.simulator.tests.cap;

import org.restcomm.protocols.ss7.tools.simulator.common.CapApplicationContextScf;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface TestCapScfManMBean {

    // Operations

    String closeCurrentDialog();

    String performInitiateCallAttempt(String msg);

    String performApplyCharging(String msg);

    String performCancel(String msg);

    String performConnect(String msg);

    String performContinue(String msg);

    String performReleaseCall(String msg);

    String performRequestReportBCSMEvent(String msg);

    String performConnectToResource(String msg);

    String performFurnishChargingInformation(String msg);

    String performPromptAndCollectUserInformation(String msg);

    String performActivityTest(String msg);

    // Attributes

    CapApplicationContextScf getCapApplicationContext();

    String getCapApplicationContext_Value();

    void setCapApplicationContext(CapApplicationContextScf val);

    String getConnectDestinationRoutingAddressAddress();
    void setConnectDestinationRoutingAddressAddress(String ConDestRouteAddrAddress);
    IsupNatureOfAddressIndicatorType getConnectDestinationRoutingAddressNatureOfAddress();
    String getConnectDestinationRoutingAddressNatureOfAddress_Value();
    void setConnectDestinationRoutingAddressNatureOfAddress(IsupNatureOfAddressIndicatorType value);
    IsupNumberingPlanIndicatorType getConnectDestinationRoutingAddressNumberingPlan();
    String getConnectDestinationRoutingAddressNumberingPlan_Value();
    void setConnectDestinationRoutingAddressNumberingPlan(IsupNumberingPlanIndicatorType value);

    IsupCauseIndicatorCauseValueType getReleaseCauseValue();
    String getReleaseCauseValue_Value();
    void setReleaseCauseValue(IsupCauseIndicatorCauseValueType value);

    IsupCauseIndicatorCodingStandardType getReleaseCauseCodingStandardIndicator();
    String getReleaseCauseCodingStandardIndicator_Value();
    void setReleaseCauseCodingStandardIndicator(IsupCauseIndicatorCodingStandardType value);

    IsupCauseIndicatorLocationType getReleaseCauseLocationIndicator();
    String getReleaseCauseLocationIndicator_Value();
    void setReleaseCauseLocationIndicator(IsupCauseIndicatorLocationType value);

    // Other

    String getCurrentRequestDef();

    // Methods for configurable properties via HTTP interface for values that are based on EnumeratedBase abstract class

    void putCapApplicationContext(String val);

    void putConDestRouteAddrNatureOfAddress(String value);

    void putConDestRouteAddrNumberingPlan(String value);

    void putReleaseCauseValue(String value);

    void putReleaseCauseCodingStandardIndicator(String value);

    void putReleaseCauseLocationIndicator(String value);
}
