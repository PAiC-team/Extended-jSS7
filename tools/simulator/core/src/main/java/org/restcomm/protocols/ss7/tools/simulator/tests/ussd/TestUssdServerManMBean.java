
package org.restcomm.protocols.ss7.tools.simulator.tests.ussd;

import org.restcomm.protocols.ss7.tools.simulator.common.AddressNatureType;
import org.restcomm.protocols.ss7.tools.simulator.level3.NumberingPlanMapType;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface TestUssdServerManMBean {

    String getMsisdnAddress();

    void setMsisdnAddress(String val);

    AddressNatureType getMsisdnAddressNature();

    String getMsisdnAddressNature_Value();

    void setMsisdnAddressNature(AddressNatureType val);

    NumberingPlanMapType getMsisdnNumberingPlan();

    String getMsisdnNumberingPlan_Value();

    void setMsisdnNumberingPlan(NumberingPlanMapType val);

    int getDataCodingScheme();

    void setDataCodingScheme(int val);

    int getAlertingPattern();

    void setAlertingPattern(int val);

    ProcessSsRequestAction getProcessSsRequestAction();

    String getProcessSsRequestAction_Value();

    void setProcessSsRequestAction(ProcessSsRequestAction val);

    String getAutoResponseString();

    void setAutoResponseString(String val);

    String getAutoUnstructured_SS_RequestString();

    void setAutoUnstructured_SS_RequestString(String val);

    boolean isOneNotificationFor100Dialogs();

    void setOneNotificationFor100Dialogs(boolean val);

    String getCurrentRequestDef();

    String performProcessUnstructuredResponse(String msg);

    String performUnstructuredRequest(String msg);

    String performUnstructuredNotify(String msg);

    String closeCurrentDialog();

    void putMsisdnAddressNature(String val);

    void putMsisdnNumberingPlan(String val);

    void putProcessSsRequestAction(String val);

}
