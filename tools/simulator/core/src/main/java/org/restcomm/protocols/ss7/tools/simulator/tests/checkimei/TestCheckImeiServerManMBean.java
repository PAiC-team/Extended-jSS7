
package org.restcomm.protocols.ss7.tools.simulator.tests.checkimei;



/**
 * @author mnowa
 *
 */
public interface TestCheckImeiServerManMBean {

    boolean isOneNotificationFor100Dialogs();

    void setOneNotificationFor100Dialogs(boolean val);

    EquipmentStatusType getAutoEquipmentStatus();

    String getAutoEquipmentStatus_Value();

    void setAutoEquipmentStatus(EquipmentStatusType val);

    void putAutoEquipmentStatus(String val);

    String getCurrentRequestDef();

    String closeCurrentDialog();

}
