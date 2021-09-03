package org.restcomm.ss7.congestion;

/**
*
* @author sergey vetyutnev
*
*/
public interface ExecutorCongestionMonitor {

    String getSource();

    int getAlarmLevel();

    double getDelayThreshold_1();

    double getDelayThreshold_2();

    double getDelayThreshold_3();

    double getBackToNormalDelayThreshold_1();

    double getBackToNormalDelayThreshold_2();

    double getBackToNormalDelayThreshold_3();

    void setDelayThreshold_1(double value) throws Exception;

    void setDelayThreshold_2(double value) throws Exception;

    void setDelayThreshold_3(double value) throws Exception;

    void setBackToNormalDelayThreshold_1(double value) throws Exception;

    void setBackToNormalDelayThreshold_2(double value) throws Exception;

    void setBackToNormalDelayThreshold_3(double value) throws Exception;

}
