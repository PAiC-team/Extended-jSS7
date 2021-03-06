
package org.restcomm.protocols.ss7.oam.common.alarm;

import java.util.List;

import javax.management.NotCompliantMBeanException;
import javax.management.NotificationBroadcasterSupport;

import org.restcomm.protocols.ss7.oam.common.jmx.MBeanHost;
import org.restcomm.protocols.ss7.oam.common.jmx.MBeanType;

import javolution.util.FastList;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class AlarmProvider extends NotificationBroadcasterSupport implements AlarmProviderMBean, AlarmListener {

    public static String ALARM_EVENT = "Alarm_Event";

    private final MBeanHost beanHost;
    private final AlarmMediator alarmMediator;

    private List<AlarmMediator> alarmMediators = new FastList<AlarmMediator>();
    private long sequenceNumber = 0;
    private String alarmProviderObjectPath;
    private String alarmProviderObjectPathWithSlash;
    private String name = "AlarmHost";

    public AlarmProvider(MBeanHost beanHost, AlarmMediator alarmMediator) {
        super();
        this.beanHost = beanHost;
        this.alarmMediator = alarmMediator;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String val) {
        this.name = val;
    }

    public void start() {
        synchronized (this) {
            try {
                AlarmProviderStandardMBean sbean = new AlarmProviderStandardMBean(this, AlarmProviderMBean.class, this);
                this.beanHost.registerMBean(AlarmLayer.ALARM, AlarmManagementType.MANAGEMENT, this.name, sbean);
            } catch (NotCompliantMBeanException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            alarmMediator.registerAlarmListener(this);
            this.registerAlarmMediator(alarmMediator);
        }
    }

    public void stop() {
        synchronized (this) {
            alarmMediator.unregisterAlarmListener(this);
            this.unregisterAlarmMediator(alarmMediator);
        }
    }

    public void registerAlarmMediator(AlarmMediator am) {
        synchronized (this.alarmMediators) {
            this.alarmMediators.add(am);
        }
    }

    public void unregisterAlarmMediator(AlarmMediator am) {
        synchronized (this.alarmMediators) {
            this.alarmMediators.remove(am);
        }
    }

    @Override
    public String getAlarmProviderObjectPath() {
        return alarmProviderObjectPath;
    }

    /**
     * Set a prefix that AlarmProvider will be add at the begin of AlarmMessage.ObjectPath field (without "/")
     */
    public void setAlarmProviderObjectPath(String value) {
        alarmProviderObjectPath = value;
        if (alarmProviderObjectPath != null && !alarmProviderObjectPath.equals(""))
            alarmProviderObjectPathWithSlash = "/" + alarmProviderObjectPath;
        else
            alarmProviderObjectPathWithSlash = null;
    }

    @Override
    public CurrentAlarmList getCurrentAlarmList() {

        CurrentAlarmListImpl al = new CurrentAlarmListImpl();
        synchronized (this.alarmMediators) {
            for (AlarmMediator amr : this.alarmMediators) {
                CurrentAlarmList temp = amr.getCurrentAlarmList();
                for (AlarmMessage alm : temp.getCurrentAlarmList()) {
                    if (alarmProviderObjectPath != null)
                        alm.addPrefixToAlarmSource(alarmProviderObjectPath);
                    al.addAlarm(alm);
                }
            }
        }

        al.sortAlarms();
        return al;
    }

    @Override
    public void onAlarm(AlarmMessage alarm) {
        if (alarmProviderObjectPath != null)
            alarm.addPrefixToAlarmSource(alarmProviderObjectPath);
        this.doSendNotif(alarm);
    }

    private synchronized void doSendNotif(AlarmMessage alarm) {
        AlarmNotification notif = new AlarmNotification(ALARM_EVENT + "-" + alarmProviderObjectPath, "AlarmProvider",
                ++sequenceNumber, System.currentTimeMillis(), null, alarm);
        // String type, Object source, long sequenceNumber, long timeStamp, String msg, AlarmMessage alarmMessage
        // notif.setUserData(userData);
        this.sendNotification(notif);
    }

    public enum AlarmManagementType implements MBeanType {
        MANAGEMENT("Management");

        private final String name;

        public static final String NAME_MANAGEMENT = "Management";

        private AlarmManagementType(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public static AlarmManagementType getInstance(String name) {
            if (NAME_MANAGEMENT.equals(name)) {
                return MANAGEMENT;
            }

            return null;
        }
    }
}
