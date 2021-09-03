
package org.restcomm.protocols.ss7.tools.simulator.level3;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface CapManMBean {

    // int getLocalSsn();
    //
    // void setLocalSsn(int val);
    //
    // int getRemoteSsn();
    //
    // void setRemoteSsn(int val);

    String getRemoteAddressDigits();

    void setRemoteAddressDigits(String val);

}
