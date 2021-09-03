package org.restcomm.ss7.congestion;

/**
*
* @author sergey vetyutnev
*
*/
public interface MemoryCongestionMonitor {

    String getSource();

    int getAlarmLevel();

    double getMemoryThreshold_1();

    double getMemoryThreshold_2();

    double getMemoryThreshold_3();

    void setMemoryThreshold_1(double value) throws Exception;

    void setMemoryThreshold_2(double value) throws Exception;

    void setMemoryThreshold_3(double value) throws Exception;

    double getBackToNormalMemoryThreshold_1();

    double getBackToNormalMemoryThreshold_2();

    double getBackToNormalMemoryThreshold_3();

    void setBackToNormalMemoryThreshold_1(double value) throws Exception;

    void setBackToNormalMemoryThreshold_2(double value) throws Exception;

    void setBackToNormalMemoryThreshold_3(double value) throws Exception;

}
