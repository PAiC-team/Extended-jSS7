
package org.restcomm.protocols.ss7.tools.simulator.level1;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface M3uaManMBean {

    boolean getStorePcapTrace();

    void setStorePcapTrace(boolean val);

    String getSctpLocalHost();

    void setSctpLocalHost(String val);

    String getSctpLocalHost2();

    void setSctpLocalHost2(String val);

    int getSctpLocalPort();

    void setSctpLocalPort(int val);

    int getSctpLocalPort2();

    void setSctpLocalPort2(int val);

    String getSctpRemoteHost();

    void setSctpRemoteHost(String val);

    String getSctpRemoteHost2();

    void setSctpRemoteHost2(String val);

    int getSctpRemotePort();

    void setSctpRemotePort(int val);

    int getSctpRemotePort2();

    void setSctpRemotePort2(int val);

    String getSctpExtraHostAddresses();

    void setSctpExtraHostAddresses(String val);

    boolean isSctpIsServer();

    void setSctpIsServer(boolean val);

    BIpChannelType getSctpIPChannelType();

    void setSctpIPChannelType(BIpChannelType val);

    String getSctpIPChannelType_Value();

    M3uaFunctionality getM3uaFunctionality();

    void setM3uaFunctionality(M3uaFunctionality val);

    String getM3uaFunctionality_Value();

    M3uaIPSPType getM3uaIPSPType();

    void setM3uaIPSPType(M3uaIPSPType val);

    String getM3uaIPSPType_Value();

    M3uaRoutingLabelFormat getRoutingLabelFormat();

    void setRoutingLabelFormat(M3uaRoutingLabelFormat val);

    String getRoutingLabelFormat_Value();

    M3uaExchangeType getM3uaExchangeType();

    void setM3uaExchangeType(M3uaExchangeType val);

    String getM3uaExchangeType_Value();

    int getM3uaDpc();

    void setM3uaDpc(int val);

    int getM3uaDpc2();

    void setM3uaDpc2(int val);

    int getM3uaOpc();

    void setM3uaOpc(int val);

    int getM3uaOpc2();

    void setM3uaOpc2(int val);

    int getM3uaSi();

    void setM3uaSi(int val);

    long getM3uaRoutingContext();

    void setM3uaRoutingContext(long val);

    long getM3uaNetworkAppearance();

    void setM3uaNetworkAppearance(long val);

    M3uaTrafficModeType getM3uaTrafficModeType();

    void setM3uaTrafficModeType(M3uaTrafficModeType val);

    String getM3uaTrafficModeType_Value();


    void putSctpIPChannelType(String val);

    void putM3uaFunctionality(String val);

    void putM3uaIPSPType(String val);

    void putRoutingLabelFormat(String val);

    void putM3uaExchangeType(String val);

    void putM3uaTrafficModeType(String val);

}
