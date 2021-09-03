
package org.restcomm.protocols.ss7.tools.simulator.tests.ussd;

import org.restcomm.protocols.ss7.tools.simulator.common.AddressNatureType;
import org.restcomm.protocols.ss7.tools.simulator.level3.NumberingPlanMapType;
import org.restcomm.protocols.ss7.tools.simulator.tests.sms.SRIReaction;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface TestUssdClientManMBean {

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

    UssdClientAction getUssdClientAction();

    String getUssdClientAction_Value();

    void setUssdClientAction(UssdClientAction val);

    String getAutoRequestString();

    void setAutoRequestString(String val);

    int getMaxConcurrentDialogs();

    void setMaxConcurrentDialogs(int val);

    boolean isOneNotificationFor100Dialogs();

    void setOneNotificationFor100Dialogs(boolean val);

    String getSRIResponseImsi();

    void setSRIResponseImsi(String val);

    String getSRIResponseVlr();

    void setSRIResponseVlr(String val);

    SRIReaction getSRIReaction();

    String getSRIReaction_Value();

    void setSRIReaction(SRIReaction val);

    boolean isReturn20PersDeliveryErrors();

    void setReturn20PersDeliveryErrors(boolean val);


    String getCurrentRequestDef();

    String performProcessUnstructuredRequest(String msg);

    String performUnstructuredResponse(String msg);

    String sendUssdBusyResponse();

    String closeCurrentDialog();


    void putMsisdnAddressNature(String val);

    void putMsisdnNumberingPlan(String val);

    void putUssdClientAction(String val);

    void putSRIReaction(String val);

    void setAutoResponseString(String text);

    void setAutoResponseOnUnstructuredSSRequests(boolean selected);

    String getAutoResponseString();

    boolean isAutoResponseOnUnstructuredSSRequests();

}
