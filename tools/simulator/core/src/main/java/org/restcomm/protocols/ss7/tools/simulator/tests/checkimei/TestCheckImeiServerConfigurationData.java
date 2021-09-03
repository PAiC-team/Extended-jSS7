

package org.restcomm.protocols.ss7.tools.simulator.tests.checkimei;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.restcomm.protocols.ss7.map.api.service.mobility.imei.EquipmentStatus;

/**
 * @author mnowa
 *
 */
public class TestCheckImeiServerConfigurationData {

    protected static final String AUTO_EQUIPMENT_STATUS = "autoEquipmentStatus";
    protected static final String ONE_NOTIFICATION_FOR_100_DIALOGS = "oneNotificationFor100Dialogs";

    protected EquipmentStatus autoEquipmentStatus = EquipmentStatus.whiteListed;

    protected boolean oneNotificationFor100Dialogs = false;

    public EquipmentStatus getAutoEquipmentStatus() {
        return autoEquipmentStatus;
    }

    public void setAutoEquipmentStatus(EquipmentStatus autoEquipmentStatus) {
        this.autoEquipmentStatus = autoEquipmentStatus;
    }

    public boolean isOneNotificationFor100Dialogs() {
        return oneNotificationFor100Dialogs;
    }

    public void setOneNotificationFor100Dialogs(boolean oneNotificationFor100Dialogs) {
        this.oneNotificationFor100Dialogs = oneNotificationFor100Dialogs;
    }

    protected static final XMLFormat<TestCheckImeiServerConfigurationData> XML = new XMLFormat<TestCheckImeiServerConfigurationData>(
            TestCheckImeiServerConfigurationData.class) {

        public void write(TestCheckImeiServerConfigurationData clt, OutputElement xml) throws XMLStreamException {

            xml.setAttribute(ONE_NOTIFICATION_FOR_100_DIALOGS, clt.oneNotificationFor100Dialogs);

            xml.add(clt.autoEquipmentStatus.toString(), AUTO_EQUIPMENT_STATUS, String.class);
        }

        public void read(InputElement xml, TestCheckImeiServerConfigurationData clt) throws XMLStreamException {

            clt.oneNotificationFor100Dialogs = xml.getAttribute(ONE_NOTIFICATION_FOR_100_DIALOGS).toBoolean();

            String aqs = (String) xml.get(AUTO_EQUIPMENT_STATUS, String.class);
            clt.autoEquipmentStatus = EquipmentStatus.valueOf(aqs);
        }
    };

}
