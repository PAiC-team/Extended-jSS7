package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:12:47:23 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface EventInformation extends ISUPParameter {
    int _PARAMETER_CODE = 0x24;

    /**
     * See Q.763 3.21 Event indicator : ALERTING
     */
    int _EVENT_INDICATOR_ALERTING = 1;

    /**
     * See Q.763 3.21 Event indicator : PROGRESS
     */
    int _EVENT_INDICATOR_PROGRESS = 2;

    /**
     * See Q.763 3.21 Event indicator : in-band information or an appropriate pattern is now available
     */
    int _EVENT_INDICATOR_IIIOPA = 3;

    /**
     * See Q.763 3.21 Event indicator : call forwarded on busy (national use)
     */
    int _EVENT_INDICATOR_CFOB = 4;

    /**
     * See Q.763 3.21 Event indicator : call forwarded on no reply (national use)
     */
    int _EVENT_INDICATOR_CFONNR = 5;

    /**
     * See Q.763 3.21 Event indicator : call forwarded unconditional (national use)
     */
    int _EVENT_INDICATOR_CFOU = 6;

    /**
     * See Q.763 3.21 Event presentation restricted indicator (national use) : no indication
     */
    boolean _EVENT_PRESENTATION_INI = false;

    /**
     * See Q.763 3.21 Event presentation restricted indicator (national use) : presentation restricted
     */
    boolean _EVENT_PRESENTATION_IPR = true;

    int getEventIndicator();

    void setEventIndicator(int eventIndicator);

    boolean isEventPresentationRestrictedIndicator();

    void setEventPresentationRestrictedIndicator(boolean eventPresentationRestrictedIndicator);

}
