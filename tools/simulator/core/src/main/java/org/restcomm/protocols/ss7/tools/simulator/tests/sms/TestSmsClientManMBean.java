
package org.restcomm.protocols.ss7.tools.simulator.tests.sms;

import org.restcomm.protocols.ss7.tools.simulator.common.AddressNatureType;
import org.restcomm.protocols.ss7.tools.simulator.level3.MapProtocolVersion;
import org.restcomm.protocols.ss7.tools.simulator.level3.NumberingPlanMapType;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface TestSmsClientManMBean {

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

    SRIReaction getSRIReaction();

    String getSRIReaction_Value();

    void setSRIReaction(SRIReaction val);

    SRIInformServiceCenter getSRIInformServiceCenter();

    String getSRIInformServiceCenter_Value();

    void setSRIInformServiceCenter(SRIInformServiceCenter val);

    boolean isSRIScAddressNotIncluded();

    void setSRIScAddressNotIncluded(boolean val);

    MtFSMReaction getMtFSMReaction();

    String getMtFSMReaction_Value();

    void setMtFSMReaction(MtFSMReaction val);

    ReportSMDeliveryStatusReaction getReportSMDeliveryStatusReaction();

    String getReportSMDeliveryStatusReaction_Value();

    void setReportSMDeliveryStatusReaction(ReportSMDeliveryStatusReaction val);

    String getSRIResponseImsi();

    void setSRIResponseImsi(String val);

    String getSRIResponseVlr();

    void setSRIResponseVlr(String val);

    int getSmscSsn();

    void setSmscSsn(int val);

    int getNationalLanguageCode();

    void setNationalLanguageCode(int val);

    boolean isStatusReportRequest();

    void setStatusReportRequest(boolean val);

    TypeOfNumberType getTypeOfNumber();

    String getTypeOfNumber_Value();

    void setTypeOfNumber(TypeOfNumberType val);

    NumberingPlanIdentificationType getNumberingPlanIdentification();

    String getNumberingPlanIdentification_Value();

    void setNumberingPlanIdentification(NumberingPlanIdentificationType val);

    SmsCodingType getSmsCodingType();

    String getSmsCodingType_Value();

    void setSmsCodingType(SmsCodingType val);

    boolean isOneNotificationFor100Dialogs();

    void setOneNotificationFor100Dialogs(boolean val);

    boolean isReturn20PersDeliveryErrors();

    void setReturn20PersDeliveryErrors(boolean val);

    boolean isContinueDialog();

    void setContinueDialog(boolean val);


    void putAddressNature(String val);

    void putNumberingPlan(String val);

    void putMapProtocolVersion(String val);

    void putSRIReaction(String val);

    void putSRIInformServiceCenter(String val);

    void putMtFSMReaction(String val);

    void putReportSMDeliveryStatusReaction(String val);

    void putTypeOfNumber(String val);

    void putNumberingPlanIdentification(String val);

    void putSmsCodingType(String val);

    String getCurrentRequestDef();

    String performMoForwardSM(String msg, String destIsdnNumber, String origIsdnNumber);

    String performMoForwardSMPartial(String msg, String destIsdnNumber, String origIsdnNumber, int msgRef, int segmCnt, int segmNum);

    String performAlertServiceCentre(String destIsdnNumber);

    String closeCurrentDialog();

}
