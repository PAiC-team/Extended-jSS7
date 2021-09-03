package org.restcomm.ss7.congestion;

import org.apache.log4j.Logger;

import javolution.util.FastList;

/**
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public abstract class BaseCongestionMonitor implements CongestionMonitor {
    protected static final Logger logger = Logger.getLogger(BaseCongestionMonitor.class);

    private final FastList<CongestionListener> listeners = new FastList<CongestionListener>();

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.ss7.congestion.CongestionMonitor#addCongestionListener(
     * org.restcomm.ss7.congestion.CongestionListener)
     */
    @Override
    public void addCongestionListener(CongestionListener listener) {
        this.listeners.add(listener);

        CongestionTicket[] ticketsList = getCongestionTicketsList();
        if (ticketsList != null) {
            for (CongestionTicket ticket : ticketsList) {
                listener.onCongestionStart(ticket);
            }
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.ss7.congestion.CongestionMonitor#removeCongestionListener
     * (org.restcomm.ss7.congestion.CongestionListener)
     */
    @Override
    public void removeCongestionListener(CongestionListener listener) {
        this.listeners.remove(listener);
    }

    protected abstract CongestionTicketImpl generateTicket();

    protected void applyNewValue(int currentAlarmLevel, double newValue, double[] alarmThreshold, double[] clearThreshold,
            boolean reverse) {
        int sign = 1;
        if (!reverse)
            sign = -1;

        int newAlarmLevel = currentAlarmLevel;
        for (int i1 = currentAlarmLevel - 1; i1 >= 0; i1--) {
            if (newValue * sign <= clearThreshold[i1] * sign) {
                newAlarmLevel = i1;
            }
        }
        for (int i1 = currentAlarmLevel; i1 < 3; i1++) {
            if (newValue * sign >= alarmThreshold[i1] * sign) {
                newAlarmLevel = i1 + 1;
            }
        }

        if (newAlarmLevel != currentAlarmLevel)
            this.setAlarmLevel(newAlarmLevel);

        if (newAlarmLevel < currentAlarmLevel) {
            String msg = "CongestionMonitor-" + this.getSource() + ": congestion decrease down to level " + newAlarmLevel
                    + ", counter value=" + newValue + ", description=" + this.getAlarmDescription();
            if (newAlarmLevel == 3)
                logger.error(msg);
            else
                logger.warn(msg);

            // Lets notify the listeners
            CongestionTicketImpl ticket = generateTicket();
            for (FastList.Node<CongestionListener> n = listeners.head(), end = listeners.tail(); (n = n.getNext()) != end;) {
                CongestionListener listener = n.getValue();
                listener.onCongestionFinish(ticket);
            }
        }
        if (newAlarmLevel > currentAlarmLevel) {
            String msg = "CongestionMonitor-" + this.getSource() + ": congestion increase up to level " + newAlarmLevel
                    + ", counter value=" + newValue + ", description=" + this.getAlarmDescription();
            if (currentAlarmLevel == 3)
                logger.error(msg);
            else
                logger.warn(msg);

            // Lets notify the listeners
            CongestionTicketImpl ticket = generateTicket();
            for (FastList.Node<CongestionListener> n = listeners.head(), end = listeners.tail(); (n = n.getNext()) != end;) {
                CongestionListener listener = n.getValue();
                listener.onCongestionStart(ticket);
            }
        }
    }

    protected abstract int getAlarmLevel();

    protected abstract void setAlarmLevel(int val);

    protected abstract String getAlarmDescription();

    @Override
    public CongestionTicket[] getCongestionTicketsList() {
        if (getAlarmLevel() > 0)
            return new CongestionTicket[] { generateTicket() };
        else
            return null;
    }
}
