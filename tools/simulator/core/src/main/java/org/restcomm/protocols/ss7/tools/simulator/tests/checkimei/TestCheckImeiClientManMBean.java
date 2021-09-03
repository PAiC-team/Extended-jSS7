
package org.restcomm.protocols.ss7.tools.simulator.tests.checkimei;

import org.restcomm.protocols.ss7.tools.simulator.level3.MapProtocolVersion;

/**
 * @author mnowa
 *
 */
public interface TestCheckImeiClientManMBean {
    String getImei();

    void setImei(String imei);

    MapProtocolVersion getMapProtocolVersion();

    String getMapProtocolVersion_Value();

    void setMapProtocolVersion(MapProtocolVersion val);

    void putMapProtocolVersion(String val);

    CheckImeiClientAction getCheckImeiClientAction();

    String getCheckImeiClientAction_Value();

    void setCheckImeiClientAction(CheckImeiClientAction val);

    void putCheckImeiClientAction(String val);

    int getMaxConcurrentDialogs();

    void setMaxConcurrentDialogs(int val);

    boolean isOneNotificationFor100Dialogs();

    void setOneNotificationFor100Dialogs(boolean val);

    String performCheckImeiRequest(String address);

    String getCurrentRequestDef();

    String closeCurrentDialog();

}
