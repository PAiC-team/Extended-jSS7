package org.restcomm.protocols.ss7.m3ua;

import java.util.Map;
import java.util.UUID;

import org.restcomm.protocols.ss7.statistics.api.LongValue;

public interface M3UACounterProvider {

    /**
     * Return a unique sessionId.
     * After m3uaManagement/counters restart this value will be changed,
     * all counters will be set to zero and all active campaigns will be removed
     */
    UUID getSessionId();
    /**
     * return a number of data packets transmitted through association.
     */
    Map<String,LongValue> getPacketsPerAssTx(String campaignName);
    /**
     * return a number of ASP UP messages transmitted through association
     */
    Map<String,LongValue> getAspUpPerAssTx(String campaignName);
    /**
     * return a number of ASP UP ACK messages transmitted through association
     */
    Map<String,LongValue> getAspUpAckPerAssTx(String campaignName);
    /**
     * return a number of ASP DOWN messages transmitted through association
     */
    Map<String,LongValue> getAspDownPerAssTx(String campaignName);
    /**
     * return a number of ASP DOWN ACK messages transmitted through association
     */
    Map<String,LongValue> getAspDownAckPerAssTx(String campaignName);
    /**
     * return a number of ASP ACTIVE messages transmitted through association
     */
    Map<String,LongValue> getAspActivePerAssTx(String campaignName);
    /**
     * return a number of ASP ACTIVE ACK messages transmitted through association
     */
    Map<String,LongValue> getAspActiveAckPerAssTx(String campaignName);
    /**
     * return a number of ASP INACTIVE messages transmitted through association
     */
    Map<String,LongValue> getAspInactivePerAssTx(String campaignName);
    /**
     * return a number of ASP INACTIVE ACK messages transmitted through association
     */
    Map<String,LongValue> getAspInactiveAckPerAssTx(String campaignName);
    /**
     * return a number of ERROR messages transmitted through association
     */
    Map<String,LongValue> getErrorPerAssTx(String campaignName);
    /**
     * return a number of NOTIFY messages transmitted through association
     */
    Map<String,LongValue> getNotifyPerAssTx(String campaignName);
    /**
     * return a number of DUNA messages transmitted through association
     */
    Map<String,LongValue> getDunaPerAssTx(String campaignName);
    /**
     * return a number of DAVA messages transmitted through association
     */
    Map<String,LongValue> getDavaPerAssTx(String campaignName);
    /**
     * return a number of DAUD messages transmitted through association
     */
    Map<String,LongValue> getDaudPerAssTx(String campaignName);
    /**
     * return a number of SCON messages transmitted through association
     */
    Map<String,LongValue> getSconPerAssTx(String campaignName);
    /**
     * return a number of DUPU messages transmitted through association
     */
    Map<String,LongValue> getDupuPerAssTx(String campaignName);
    /**
     * return a number of DRST messages transmitted through association
     */
    Map<String,LongValue> getDrstPerAssTx(String campaignName);
    /**
     * return a number of BEAT messages transmitted through association
     */
    Map<String,LongValue> getBeatPerAssTx(String campaignName);
    /**
     * return a number of BEAT ACK messages transmitted through association
     */
    Map<String,LongValue> getBeatAckPerAssTx(String campaignName);
    /**
     * return a number of data packets received through association
     */
    Map<String,LongValue> getPacketsPerAssRx(String campaignName);
    /**
     * return a number of ASP UP messages received through association
     */
    Map<String,LongValue> getAspUpPerAssRx(String campaignName);
    /**
     * return a number of ASP UP ACK messages received through association
     */
    Map<String,LongValue> getAspUpAckPerAssRx(String campaignName);
    /**
     * return a number of ASP DOWN messages received through association
     */
    Map<String,LongValue> getAspDownPerAssRx(String campaignName);
    /**
     * return a number of ASP DOWN ACK messages received through association
     */
    Map<String,LongValue> getAspDownAckPerAssRx(String campaignName);
    /**
     * return a number of ASP ACTIVE messages received through association
     */
    Map<String,LongValue> getAspActivePerAssRx(String campaignName);
    /**
     * return a number of ASP ACTIVE ACK messages received through association
     */
    Map<String,LongValue> getAspActiveAckPerAssRx(String campaignName);
    /**
     * return a number of ASP ACTIVE ACK messages received through association
     */
    Map<String,LongValue> getAspInactivePerAssRx(String campaignName);
    /**
     * return a number of ASP INACTIVE ACK messages received through association
     */
    Map<String,LongValue> getAspInactiveAckPerAssRx(String campaignName);
    /**
     * return a number of ERROR messages received through association
     */
    Map<String,LongValue> getErrorPerAssRx(String campaignName);
    /**
     * return a number of NOTIFY messages received through association
     */
    Map<String,LongValue> getNotifyPerAssRx(String campaignName);
    /**
     * return a number of DUNA messages received through association
     */
    Map<String,LongValue> getDunaPerAssRx(String campaignName);
    /**
     * return a number of DAVA messages received through association
     */
    Map<String,LongValue> getDavaPerAssRx(String campaignName);
    /**
     * return a number of DAUD messages received through association
     */
    Map<String,LongValue> getDaudPerAssRx(String campaignName);
    /**
     * return a number of SCON messages received through association
     */
    Map<String,LongValue> getSconPerAssRx(String campaignName);
    /**
     * return a number of DUPU messages received through association
     */
    Map<String,LongValue> getDupuPerAssRx(String campaignName);
    /**
     * return a number of DRST messages received through association
     */
    Map<String,LongValue> getDrstPerAssRx(String campaignName);
    /**
     * return a number of BEAT messages received through association
     */
    Map<String,LongValue> getBeatPerAssRx(String campaignName);
    /**
     * return a number of BEAT ACK messages received through association
     */
    Map<String,LongValue> getBeatAckPerAssRx(String campaignName);

}
