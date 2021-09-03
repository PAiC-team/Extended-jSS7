
package org.restcomm.protocols.ss7.tools.simulator.level2;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface SccpManMBean {

    boolean isRouteOnGtMode();

    void setRouteOnGtMode(boolean val);

    int getRemoteSpc();

    void setRemoteSpc(int val);

    int getRemoteSpc2();

    void setRemoteSpc2(int val);

    int getLocalSpc();

    void setLocalSpc(int val);

    int getNi();

    void setNi(int val);

    int getRemoteSsn();

    void setRemoteSsn(int val);

    int getLocalSsn();

    void setLocalSsn(int val);

    int getLocalSsn2();

    void setLocalSsn2(int val);

    GlobalTitleType getGlobalTitleType();

    String getGlobalTitleType_Value();

    void setGlobalTitleType(GlobalTitleType val);

    NatureOfAddressType getNatureOfAddress();

    String getNatureOfAddress_Value();

    void setNatureOfAddress(NatureOfAddressType val);

    NumberingPlanSccpType getNumberingPlan();

    String getNumberingPlan_Value();

    void setNumberingPlan(NumberingPlanSccpType val);

    int getTranslationType();

    void setTranslationType(int val);

    String getCallingPartyAddressDigits();

    void setCallingPartyAddressDigits(String val);

    SccpProtocolVersionType getSccpProtocolVersion();

    String getSccpProtocolVersion_Value();

    void setSccpProtocolVersion(SccpProtocolVersionType val);

    void putGlobalTitleType(String val);

    void putNatureOfAddress(String val);

    void putNumberingPlan(String val);

    void putSccpProtocolVersion(String val);

}
