
package org.restcomm.protocols.ss7.tools.simulator.tests.sms;

import org.restcomm.protocols.ss7.tools.simulator.common.AddressNatureType;
import org.restcomm.protocols.ss7.tools.simulator.level3.MapProtocolVersion;
import org.restcomm.protocols.ss7.tools.simulator.level3.NumberingPlanMapType;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface TestSmsServerManMBean {

    AddressNatureType getAddressNature();

    String getAddressNature_Value();

    void setAddressNature(AddressNatureType val);

    NumberingPlanMapType getNumberingPlan();

    String getNumberingPlan_Value();

    void setNumberingPlan(NumberingPlanMapType val);

    String getServiceCenterAddress();

    void setServiceCenterAddress(String val);

    MapProtocolVersion getMapProtocolVersion();

    String getMapProtocolVersion_Value();

    void setMapProtocolVersion(MapProtocolVersion val);

    int getHlrSsn();

    void setHlrSsn(int val);

    int getVlrSsn();

    void setVlrSsn(int val);

    TypeOfNumberType getTypeOfNumber();

    String getTypeOfNumber_Value();

    void setTypeOfNumber(TypeOfNumberType val);

    NumberingPlanIdentificationType getNumberingPlanIdentification();

    String getNumberingPlanIdentification_Value();

    void setNumberingPlanIdentification(NumberingPlanIdentificationType val);

    SmsCodingType getSmsCodingType();

    String getSmsCodingType_Value();

    void setSmsCodingType(SmsCodingType val);

    boolean isSendSrsmdsIfError();

    void setSendSrsmdsIfError(boolean val);

    boolean isGprsSupportIndicator();

    void setGprsSupportIndicator(boolean val);

    void putAddressNature(String val);

    void putNumberingPlan(String val);

    void putMapProtocolVersion(String val);

    void putTypeOfNumber(String val);

    void putNumberingPlanIdentification(String val);

    void putSmsCodingType(String val);

    String getCurrentRequestDef();

    String performSRIForSM(String destIsdnNumber);

    String performSRIForSM_MtForwardSM(String msg, String destIsdnNumber, String origIsdnNumber);

    String performMtForwardSM(String msg, String destImsi, String vlrNumber, String origIsdnNumber);

    String closeCurrentDialog();

}
