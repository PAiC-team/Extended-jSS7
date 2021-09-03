
package org.restcomm.protocols.ss7.tools.simulator.tests.checkimei;

import org.restcomm.protocols.ss7.tools.simulator.level3.MapProtocolVersion;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

/**
 * @author mnowa
 *
 */
public class TestCheckImeiClientConfigurationData {

    protected static final String IMEI = "imei";
    protected static final String MAP_PROTOCOL_VERSION = "mapProtocolVersion";
    protected static final String CHECK_IMEI_CLIENT_ACTION = "checkImeiClientAction";
    protected static final String MAX_CONCURENT_DIALOGS = "maxConcurrentDialogs";
    protected static final String ONE_NOTIFICATION_FOR_100_DIALOGS = "oneNotificationFor100Dialogs";

    protected String imei = "";

    protected MapProtocolVersion mapProtocolVersion = new MapProtocolVersion(MapProtocolVersion.VAL_MAP_V3);

    protected CheckImeiClientAction checkImeiClientAction = new CheckImeiClientAction(CheckImeiClientAction.VAL_MANUAL_OPERATION);
    protected int maxConcurrentDialogs = 10;
    protected boolean oneNotificationFor100Dialogs = false;

    public String getImei() {
        return imei;
    }
    public void setImei(String imei) {
        this.imei = imei;
    }
    public MapProtocolVersion getMapProtocolVersion() {
        return mapProtocolVersion;
    }
    public void setMapProtocolVersion(MapProtocolVersion mapProtocolVersion) {
        this.mapProtocolVersion = mapProtocolVersion;
    }
    public CheckImeiClientAction getCheckImeiClientAction() {
        return checkImeiClientAction;
    }
    public void setCheckImeiClientAction(CheckImeiClientAction checkImeiClientAction) {
        this.checkImeiClientAction = checkImeiClientAction;
    }
    public int getMaxConcurrentDialogs() {
        return maxConcurrentDialogs;
    }
    public void setMaxConcurrentDialogs(int maxConcurrentDialogs) {
        this.maxConcurrentDialogs = maxConcurrentDialogs;
    }

    public boolean isOneNotificationFor100Dialogs() {
        return oneNotificationFor100Dialogs;
    }

    public void setOneNotificationFor100Dialogs(boolean oneNotificationFor100Dialogs) {
        this.oneNotificationFor100Dialogs = oneNotificationFor100Dialogs;
    }

    protected static final XMLFormat<TestCheckImeiClientConfigurationData> XML = new XMLFormat<TestCheckImeiClientConfigurationData>(
            TestCheckImeiClientConfigurationData.class) {

        public void write(TestCheckImeiClientConfigurationData clt, OutputElement xml) throws XMLStreamException {

            xml.setAttribute(MAX_CONCURENT_DIALOGS, clt.maxConcurrentDialogs);
            xml.setAttribute(ONE_NOTIFICATION_FOR_100_DIALOGS, clt.oneNotificationFor100Dialogs);

            xml.add(clt.imei, IMEI, String.class);

            xml.add(clt.mapProtocolVersion.toString(), MAP_PROTOCOL_VERSION, String.class);

            xml.add(clt.checkImeiClientAction.toString(), CHECK_IMEI_CLIENT_ACTION, String.class);
        }

        public void read(InputElement xml, TestCheckImeiClientConfigurationData clt) throws XMLStreamException {
            clt.maxConcurrentDialogs = xml.getAttribute(MAX_CONCURENT_DIALOGS).toInt();
            clt.oneNotificationFor100Dialogs = xml.getAttribute(ONE_NOTIFICATION_FOR_100_DIALOGS).toBoolean();

            clt.imei = (String) xml.get(IMEI, String.class);

            String mpv = (String) xml.get(MAP_PROTOCOL_VERSION, String.class);
            clt.mapProtocolVersion = MapProtocolVersion.createInstance(mpv);

            String cica = (String) xml.get(CHECK_IMEI_CLIENT_ACTION, String.class);
            clt.checkImeiClientAction = CheckImeiClientAction.createInstance(cica);
        }
    };
}
