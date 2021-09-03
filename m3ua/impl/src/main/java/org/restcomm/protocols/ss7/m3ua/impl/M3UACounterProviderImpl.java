package org.restcomm.protocols.ss7.m3ua.impl;

import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.m3ua.M3UACounterProvider;
import org.restcomm.protocols.ss7.statistics.StatDataCollectionImpl;
import org.restcomm.protocols.ss7.statistics.api.LongValue;
import org.restcomm.protocols.ss7.statistics.api.StatDataCollection;
import org.restcomm.protocols.ss7.statistics.api.StatDataCollectorType;
import org.restcomm.protocols.ss7.statistics.api.StatResult;

public class M3UACounterProviderImpl implements M3UACounterProvider{

    private static final Logger logger = Logger.getLogger(M3UACounterProviderImpl.class);

    private UUID sessionId = UUID.randomUUID();

    private StatDataCollection statDataCollection = new StatDataCollectionImpl();
    private M3UAManagementImpl m3uaManagementImpl;

    private static String PACKETS_PER_ASS_TX = "packetsPerAssTx";
    private static String ASP_UP_PER_ASS_TX = "aspUpPerAssTx";
    private static String ASP_UP_ACK_PER_ASS_TX = "aspUpAckPerAssTx";
    private static String ASP_DOWN_PER_ASS_TX = "aspDownPerAssTx";
    private static String ASP_DOWN_ACK_PER_ASS_TX = "aspDownAckPerAssTx";
    private static String ASP_ACTIVE_PER_ASS_TX = "aspActivePerAssTx";
    private static String ASP_ACTIVE_ACK_PER_ASS_TX = "aspActiveAckPerAssTx";
    private static String ASP_INACTIVE_PER_ASS_TX = "aspInactivePerAssTx";
    private static String ASP_INACTIVE_ACK_PER_ASS_TX = "aspInactiveAckPerAssTx";
    private static String ERROR_PER_ASS_TX = "errorPerAssTx";
    private static String NOTIFY_PER_ASS_TX = "notifyPerAssTx";
    private static String DUNA_PER_ASS_TX = "dunaPerAssTx";
    private static String DAVA_PER_ASS_TX = "davaPerAssTx";
    private static String DAUD_PER_ASS_TX = "daudPerAssTx";
    private static String SCON_PER_ASS_TX = "sconPerAssTx";
    private static String DUPU_PER_ASS_TX = "dupuPerAssTx";
    private static String DRST_PER_ASS_TX = "drstPerAssTx";
    private static String BEAT_PER_ASS_TX = "beatPerAssTx";
    private static String BEAT_ACK_PER_ASS_TX = "beatAckPerAssTx";

    private static String PACKETS_PER_ASS_RX = "packetsPerAssRx";
    private static String ASP_UP_PER_ASS_RX = "aspUpPerAssRx";
    private static String ASP_UP_ACK_PER_ASS_RX = "aspUpAckPerAssRx";
    private static String ASP_DOWN_PER_ASS_RX = "aspDownPerAssRx";
    private static String ASP_DOWN_ACK_PER_ASS_RX = "aspDownAckPerAssRx";
    private static String ASP_ACTIVE_PER_ASS_RX = "aspActivePerAssRx";
    private static String ASP_ACTIVE_ACK_PER_ASS_RX = "aspActiveAckPerAssRx";
    private static String ASP_INACTIVE_PER_ASS_RX = "aspInactivePerAssRx";
    private static String ASP_INACTIVE_ACK_PER_ASS_RX = "aspInactiveAckPerAssRx";
    private static String ERROR_PER_ASS_RX = "errorPerAssRx";
    private static String NOTIFY_PER_ASS_RX = "notifyPerAssRx";
    private static String DUNA_PER_ASS_RX = "dunaPerAssRx";
    private static String DAVA_PER_ASS_RX = "davaPerAssRx";
    private static String DAUD_PER_ASS_RX = "daudPerAssRx";
    private static String SCON_PER_ASS_RX = "sconPerAssRx";
    private static String DUPU_PER_ASS_RX = "dupuPerAssRx";
    private static String DRST_PER_ASS_RX = "drstPerAssRx";
    private static String BEAT_PER_ASS_RX = "beatPerAssRx";
    private static String BEAT_ACK_PER_ASS_RX = "beatAckPerAssRx";

    public M3UACounterProviderImpl(M3UAManagementImpl m3uaManagementImpl) {

        this.m3uaManagementImpl = m3uaManagementImpl;

        this.statDataCollection.registerStatCounterCollector(PACKETS_PER_ASS_TX, StatDataCollectorType.StringLongMap);
        this.statDataCollection.registerStatCounterCollector(ASP_UP_PER_ASS_TX, StatDataCollectorType.StringLongMap);
        this.statDataCollection.registerStatCounterCollector(ASP_UP_ACK_PER_ASS_TX, StatDataCollectorType.StringLongMap);
        this.statDataCollection.registerStatCounterCollector(ASP_DOWN_PER_ASS_TX, StatDataCollectorType.StringLongMap);
        this.statDataCollection.registerStatCounterCollector(ASP_DOWN_ACK_PER_ASS_TX, StatDataCollectorType.StringLongMap);
        this.statDataCollection.registerStatCounterCollector(ASP_ACTIVE_PER_ASS_TX, StatDataCollectorType.StringLongMap);
        this.statDataCollection.registerStatCounterCollector(ASP_ACTIVE_ACK_PER_ASS_TX, StatDataCollectorType.StringLongMap);
        this.statDataCollection.registerStatCounterCollector(ASP_INACTIVE_PER_ASS_TX, StatDataCollectorType.StringLongMap);
        this.statDataCollection.registerStatCounterCollector(ASP_INACTIVE_ACK_PER_ASS_TX, StatDataCollectorType.StringLongMap);
        this.statDataCollection.registerStatCounterCollector(ERROR_PER_ASS_TX, StatDataCollectorType.StringLongMap);
        this.statDataCollection.registerStatCounterCollector(NOTIFY_PER_ASS_TX, StatDataCollectorType.StringLongMap);
        this.statDataCollection.registerStatCounterCollector(DUNA_PER_ASS_TX, StatDataCollectorType.StringLongMap);
        this.statDataCollection.registerStatCounterCollector(DAVA_PER_ASS_TX, StatDataCollectorType.StringLongMap);
        this.statDataCollection.registerStatCounterCollector(DAUD_PER_ASS_TX, StatDataCollectorType.StringLongMap);
        this.statDataCollection.registerStatCounterCollector(SCON_PER_ASS_TX, StatDataCollectorType.StringLongMap);
        this.statDataCollection.registerStatCounterCollector(DUPU_PER_ASS_TX, StatDataCollectorType.StringLongMap);
        this.statDataCollection.registerStatCounterCollector(DRST_PER_ASS_TX, StatDataCollectorType.StringLongMap);
        this.statDataCollection.registerStatCounterCollector(BEAT_PER_ASS_TX, StatDataCollectorType.StringLongMap);
        this.statDataCollection.registerStatCounterCollector(BEAT_ACK_PER_ASS_TX, StatDataCollectorType.StringLongMap);

        this.statDataCollection.registerStatCounterCollector(PACKETS_PER_ASS_RX, StatDataCollectorType.StringLongMap);
        this.statDataCollection.registerStatCounterCollector(ASP_UP_PER_ASS_RX, StatDataCollectorType.StringLongMap);
        this.statDataCollection.registerStatCounterCollector(ASP_UP_ACK_PER_ASS_RX, StatDataCollectorType.StringLongMap);
        this.statDataCollection.registerStatCounterCollector(ASP_DOWN_PER_ASS_RX, StatDataCollectorType.StringLongMap);
        this.statDataCollection.registerStatCounterCollector(ASP_DOWN_ACK_PER_ASS_RX, StatDataCollectorType.StringLongMap);
        this.statDataCollection.registerStatCounterCollector(ASP_ACTIVE_PER_ASS_RX, StatDataCollectorType.StringLongMap);
        this.statDataCollection.registerStatCounterCollector(ASP_ACTIVE_ACK_PER_ASS_RX, StatDataCollectorType.StringLongMap);
        this.statDataCollection.registerStatCounterCollector(ASP_INACTIVE_PER_ASS_RX, StatDataCollectorType.StringLongMap);
        this.statDataCollection.registerStatCounterCollector(ASP_INACTIVE_ACK_PER_ASS_RX, StatDataCollectorType.StringLongMap);
        this.statDataCollection.registerStatCounterCollector(ERROR_PER_ASS_RX, StatDataCollectorType.StringLongMap);
        this.statDataCollection.registerStatCounterCollector(NOTIFY_PER_ASS_RX, StatDataCollectorType.StringLongMap);
        this.statDataCollection.registerStatCounterCollector(DUNA_PER_ASS_RX, StatDataCollectorType.StringLongMap);
        this.statDataCollection.registerStatCounterCollector(DAVA_PER_ASS_RX, StatDataCollectorType.StringLongMap);
        this.statDataCollection.registerStatCounterCollector(DAUD_PER_ASS_RX, StatDataCollectorType.StringLongMap);
        this.statDataCollection.registerStatCounterCollector(SCON_PER_ASS_RX, StatDataCollectorType.StringLongMap);
        this.statDataCollection.registerStatCounterCollector(DUPU_PER_ASS_RX, StatDataCollectorType.StringLongMap);
        this.statDataCollection.registerStatCounterCollector(DRST_PER_ASS_RX, StatDataCollectorType.StringLongMap);
        this.statDataCollection.registerStatCounterCollector(BEAT_PER_ASS_RX, StatDataCollectorType.StringLongMap);
        this.statDataCollection.registerStatCounterCollector(BEAT_ACK_PER_ASS_RX, StatDataCollectorType.StringLongMap);
    }

    @Override
    public UUID getSessionId() {
        return sessionId;
    }

    public Map<String, LongValue> getPacketsPerAssTx(String campaignName) {
        StatResult res = this.statDataCollection.restartAndGet(PACKETS_PER_ASS_TX, campaignName);
        this.statDataCollection.updateData(PACKETS_PER_ASS_TX, m3uaManagementImpl.getAspfactories().size());
        if (res != null)
            return res.getStringLongValue();
        else
            return null;
    }

    public void updatePacketsPerAssTx(String associationName) {
        this.statDataCollection.updateData(PACKETS_PER_ASS_TX, associationName);
    }

    public Map<String, LongValue> getAspUpPerAssTx(String campaignName) {
        StatResult res = this.statDataCollection.restartAndGet(ASP_UP_PER_ASS_TX, campaignName);
        this.statDataCollection.updateData(ASP_UP_PER_ASS_TX, m3uaManagementImpl.getAspfactories().size());
        if (res != null)
            return res.getStringLongValue();
        else
            return null;
    }

    public void updateAspUpPerAssTx(String associationName) {
        this.statDataCollection.updateData(ASP_UP_PER_ASS_TX, associationName);
    }

    public Map<String, LongValue> getAspUpAckPerAssTx(String campaignName) {
        StatResult res = this.statDataCollection.restartAndGet(ASP_UP_ACK_PER_ASS_TX, campaignName);
        this.statDataCollection.updateData(ASP_UP_ACK_PER_ASS_TX, m3uaManagementImpl.getAspfactories().size());
        if (res != null)
            return res.getStringLongValue();
        else
            return null;
    }

    public void updateAspUpAckPerAssTx(String associationName) {
        this.statDataCollection.updateData(ASP_UP_ACK_PER_ASS_TX, associationName);
    }

    public Map<String, LongValue> getAspDownPerAssTx(String campaignName) {
        StatResult res = this.statDataCollection.restartAndGet(ASP_DOWN_PER_ASS_TX, campaignName);
        this.statDataCollection.updateData(ASP_DOWN_PER_ASS_TX, m3uaManagementImpl.getAspfactories().size());
        if (res != null)
            return res.getStringLongValue();
        else
            return null;
    }

    public void updateAspDownPerAssTx(String associationName) {
        this.statDataCollection.updateData(ASP_DOWN_PER_ASS_TX, associationName);
    }

    public Map<String, LongValue> getAspDownAckPerAssTx(String campaignName) {
        StatResult res = this.statDataCollection.restartAndGet(ASP_DOWN_ACK_PER_ASS_TX, campaignName);
        this.statDataCollection.updateData(ASP_DOWN_ACK_PER_ASS_TX, m3uaManagementImpl.getAspfactories().size());
        if (res != null)
            return res.getStringLongValue();
        else
            return null;
    }

    public void updateAspDownAckPerAssTx(String associationName) {
        this.statDataCollection.updateData(ASP_DOWN_ACK_PER_ASS_TX, associationName);
    }

    public Map<String, LongValue> getAspActivePerAssTx(String campaignName) {
        StatResult res = this.statDataCollection.restartAndGet(ASP_ACTIVE_PER_ASS_TX, campaignName);
        this.statDataCollection.updateData(ASP_ACTIVE_PER_ASS_TX, m3uaManagementImpl.getAspfactories().size());
        if (res != null)
            return res.getStringLongValue();
        else
            return null;
    }

    public void updateAspActivePerAssTx(String associationName) {
        this.statDataCollection.updateData(ASP_ACTIVE_PER_ASS_TX, associationName);
    }

    public Map<String, LongValue> getAspActiveAckPerAssTx(String campaignName) {
        StatResult res = this.statDataCollection.restartAndGet(ASP_ACTIVE_ACK_PER_ASS_TX, campaignName);
        this.statDataCollection.updateData(ASP_ACTIVE_ACK_PER_ASS_TX, m3uaManagementImpl.getAspfactories().size());
        if (res != null)
            return res.getStringLongValue();
        else
            return null;
    }

    public void updateAspActiveAckPerAssTx(String associationName) {
        this.statDataCollection.updateData(ASP_ACTIVE_ACK_PER_ASS_TX, associationName);
    }

    public Map<String, LongValue> getAspInactivePerAssTx(String campaignName) {
        StatResult res = this.statDataCollection.restartAndGet(ASP_INACTIVE_PER_ASS_TX, campaignName);
        this.statDataCollection.updateData(ASP_INACTIVE_PER_ASS_TX, m3uaManagementImpl.getAspfactories().size());
        if (res != null)
            return res.getStringLongValue();
        else
            return null;
    }

    public void updateAspInactivePerAssTx(String associationName) {
        this.statDataCollection.updateData(ASP_INACTIVE_PER_ASS_TX, associationName);
    }

    public Map<String, LongValue> getAspInactiveAckPerAssTx(String campaignName) {
        StatResult res = this.statDataCollection.restartAndGet(ASP_INACTIVE_ACK_PER_ASS_TX, campaignName);
        this.statDataCollection.updateData(ASP_INACTIVE_ACK_PER_ASS_TX, m3uaManagementImpl.getAspfactories().size());
        if (res != null)
            return res.getStringLongValue();
        else
            return null;
    }

    public void updateAspInactiveAckPerAssTx(String associationName) {
        this.statDataCollection.updateData(ASP_INACTIVE_ACK_PER_ASS_TX, associationName);
    }

    public Map<String, LongValue> getErrorPerAssTx(String campaignName) {
        StatResult res = this.statDataCollection.restartAndGet(ERROR_PER_ASS_TX, campaignName);
        this.statDataCollection.updateData(ERROR_PER_ASS_TX, m3uaManagementImpl.getAspfactories().size());
        if (res != null)
            return res.getStringLongValue();
        else
            return null;
    }

    public void updateErrorPerAssTx(String associationName) {
        this.statDataCollection.updateData(ERROR_PER_ASS_TX, associationName);
    }

    public Map<String, LongValue> getNotifyPerAssTx(String campaignName) {
        StatResult res = this.statDataCollection.restartAndGet(NOTIFY_PER_ASS_TX, campaignName);
        this.statDataCollection.updateData(NOTIFY_PER_ASS_TX, m3uaManagementImpl.getAspfactories().size());
        if (res != null)
            return res.getStringLongValue();
        else
            return null;
    }

    public void updateNotifyPerAssTx(String associationName) {
        this.statDataCollection.updateData(NOTIFY_PER_ASS_TX, associationName);
    }

    public Map<String, LongValue> getDunaPerAssTx(String campaignName) {
        StatResult res = this.statDataCollection.restartAndGet(DUNA_PER_ASS_TX, campaignName);
        this.statDataCollection.updateData(DUNA_PER_ASS_TX, m3uaManagementImpl.getAspfactories().size());
        if (res != null)
            return res.getStringLongValue();
        else
            return null;
    }

    public void updateDunaPerAssTx(String associationName) {
        this.statDataCollection.updateData(DUNA_PER_ASS_TX, associationName);
    }

    public Map<String, LongValue> getDavaPerAssTx(String campaignName) {
        StatResult res = this.statDataCollection.restartAndGet(DAVA_PER_ASS_TX, campaignName);
        this.statDataCollection.updateData(DAVA_PER_ASS_TX, m3uaManagementImpl.getAspfactories().size());
        if (res != null)
            return res.getStringLongValue();
        else
            return null;
    }

    public void updateDavaPerAssTx(String associationName) {
        this.statDataCollection.updateData(DAVA_PER_ASS_TX, associationName);
    }

    public Map<String, LongValue> getDaudPerAssTx(String campaignName) {
        StatResult res = this.statDataCollection.restartAndGet(DAUD_PER_ASS_TX, campaignName);
        this.statDataCollection.updateData(DAUD_PER_ASS_TX, m3uaManagementImpl.getAspfactories().size());
        if (res != null)
            return res.getStringLongValue();
        else
            return null;
    }

    public void updateDaudPerAssTx(String associationName) {
        this.statDataCollection.updateData(DAUD_PER_ASS_TX, associationName);
    }

    public Map<String, LongValue> getSconPerAssTx(String campaignName) {
        StatResult res = this.statDataCollection.restartAndGet(SCON_PER_ASS_TX, campaignName);
        this.statDataCollection.updateData(SCON_PER_ASS_TX, m3uaManagementImpl.getAspfactories().size());
        if (res != null)
            return res.getStringLongValue();
        else
            return null;
    }

    public void updateSconPerAssTx(String associationName) {
        this.statDataCollection.updateData(SCON_PER_ASS_TX, associationName);
    }

    public Map<String, LongValue> getDupuPerAssTx(String campaignName) {
        StatResult res = this.statDataCollection.restartAndGet(DUPU_PER_ASS_TX, campaignName);
        this.statDataCollection.updateData(DUPU_PER_ASS_TX, m3uaManagementImpl.getAspfactories().size());
        if (res != null)
            return res.getStringLongValue();
        else
            return null;
    }

    public void updateDupuPerAssTx(String associationName) {
        this.statDataCollection.updateData(DUPU_PER_ASS_TX, associationName);
    }

    public Map<String, LongValue> getDrstPerAssTx(String campaignName) {
        StatResult res = this.statDataCollection.restartAndGet(DRST_PER_ASS_TX, campaignName);
        this.statDataCollection.updateData(DRST_PER_ASS_TX, m3uaManagementImpl.getAspfactories().size());
        if (res != null)
            return res.getStringLongValue();
        else
            return null;
    }

    public void updateDrstPerAssTx(String associationName) {
        this.statDataCollection.updateData(DRST_PER_ASS_TX, associationName);
    }

    public Map<String, LongValue> getBeatPerAssTx(String campaignName) {
        StatResult res = this.statDataCollection.restartAndGet(BEAT_PER_ASS_TX, campaignName);
        this.statDataCollection.updateData(BEAT_PER_ASS_TX, m3uaManagementImpl.getAspfactories().size());
        if (res != null)
            return res.getStringLongValue();
        else
            return null;
    }

    public void updateBeatPerAssTx(String associationName) {
        this.statDataCollection.updateData(BEAT_PER_ASS_TX, associationName);
    }

    public Map<String, LongValue> getBeatAckPerAssTx(String campaignName) {
        StatResult res = this.statDataCollection.restartAndGet(BEAT_ACK_PER_ASS_TX, campaignName);
        this.statDataCollection.updateData(BEAT_ACK_PER_ASS_TX, m3uaManagementImpl.getAspfactories().size());
        if (res != null)
            return res.getStringLongValue();
        else
            return null;
    }

    public void updateBeatAckPerAssTx(String associationName) {
        this.statDataCollection.updateData(BEAT_ACK_PER_ASS_TX, associationName);
    }

    public Map<String, LongValue> getPacketsPerAssRx(String campaignName) {
        StatResult res = this.statDataCollection.restartAndGet(PACKETS_PER_ASS_RX, campaignName);
        this.statDataCollection.updateData(PACKETS_PER_ASS_RX, m3uaManagementImpl.getAspfactories().size());
        if (res != null)
            return res.getStringLongValue();
        else
            return null;
    }

    public void updatePacketsPerAssRx(String associationName) {
        this.statDataCollection.updateData(PACKETS_PER_ASS_RX, associationName);
    }

    public Map<String, LongValue> getAspUpPerAssRx(String campaignName) {
        StatResult res = this.statDataCollection.restartAndGet(ASP_UP_PER_ASS_RX, campaignName);
        logger.info("GET STAT RESULT : " + res);
        if(res != null)
            logger.info("RESULT VALUE : " + res.getStringLongValue());
        this.statDataCollection.updateData(ASP_UP_PER_ASS_RX, m3uaManagementImpl.getAspfactories().size());
        if (res != null)
            return res.getStringLongValue();
        else
            return null;
    }

    public void updateAspUpPerAssRx(String associationName) {
        this.statDataCollection.updateData(ASP_UP_PER_ASS_RX, associationName);
    }

    public Map<String, LongValue> getAspUpAckPerAssRx(String campaignName) {
        StatResult res = this.statDataCollection.restartAndGet(ASP_UP_ACK_PER_ASS_RX, campaignName);
        this.statDataCollection.updateData(ASP_UP_ACK_PER_ASS_RX, m3uaManagementImpl.getAspfactories().size());
        if (res != null)
            return res.getStringLongValue();
        else
            return null;
    }

    public void updateAspUpAckPerAssRx(String associationName) {
        this.statDataCollection.updateData(ASP_UP_ACK_PER_ASS_RX, associationName);
    }

    public Map<String, LongValue> getAspDownPerAssRx(String campaignName) {
        StatResult res = this.statDataCollection.restartAndGet(ASP_DOWN_PER_ASS_RX, campaignName);
        this.statDataCollection.updateData(ASP_DOWN_PER_ASS_RX, m3uaManagementImpl.getAspfactories().size());
        if (res != null)
            return res.getStringLongValue();
        else
            return null;
    }

    public void updateAspDownPerAssRx(String associationName) {
        this.statDataCollection.updateData(ASP_DOWN_PER_ASS_RX, associationName);
    }

    public Map<String, LongValue> getAspDownAckPerAssRx(String campaignName) {
        StatResult res = this.statDataCollection.restartAndGet(ASP_DOWN_ACK_PER_ASS_RX, campaignName);
        this.statDataCollection.updateData(ASP_DOWN_ACK_PER_ASS_RX, m3uaManagementImpl.getAspfactories().size());
        if (res != null)
            return res.getStringLongValue();
        else
            return null;
    }

    public void updateAspDownAckPerAssRx(String associationName) {
        this.statDataCollection.updateData(ASP_DOWN_ACK_PER_ASS_RX, associationName);
    }

    public Map<String, LongValue> getAspActivePerAssRx(String campaignName) {
        StatResult res = this.statDataCollection.restartAndGet(ASP_ACTIVE_PER_ASS_RX, campaignName);
        this.statDataCollection.updateData(ASP_ACTIVE_PER_ASS_RX, m3uaManagementImpl.getAspfactories().size());
        if (res != null)
            return res.getStringLongValue();
        else
            return null;
    }

    public void updateAspActivePerAssRx(String associationName) {
        this.statDataCollection.updateData(ASP_ACTIVE_PER_ASS_RX, associationName);
    }

    public Map<String, LongValue> getAspActiveAckPerAssRx(String campaignName) {
        StatResult res = this.statDataCollection.restartAndGet(ASP_ACTIVE_ACK_PER_ASS_RX, campaignName);
        this.statDataCollection.updateData(ASP_ACTIVE_ACK_PER_ASS_RX, m3uaManagementImpl.getAspfactories().size());
        if (res != null)
            return res.getStringLongValue();
        else
            return null;
    }

    public void updateAspActiveAckPerAssRx(String associationName) {
        this.statDataCollection.updateData(ASP_ACTIVE_ACK_PER_ASS_RX, associationName);
    }

    public Map<String, LongValue> getAspInactivePerAssRx(String campaignName) {
        StatResult res = this.statDataCollection.restartAndGet(ASP_INACTIVE_PER_ASS_RX, campaignName);
        this.statDataCollection.updateData(ASP_INACTIVE_PER_ASS_RX, m3uaManagementImpl.getAspfactories().size());
        if (res != null)
            return res.getStringLongValue();
        else
            return null;
    }

    public void updateAspInactivePerAssRx(String associationName) {
        this.statDataCollection.updateData(ASP_INACTIVE_PER_ASS_RX, associationName);
    }

    public Map<String, LongValue> getAspInactiveAckPerAssRx(String campaignName) {
        StatResult res = this.statDataCollection.restartAndGet(ASP_INACTIVE_ACK_PER_ASS_RX, campaignName);
        this.statDataCollection.updateData(ASP_INACTIVE_ACK_PER_ASS_RX, m3uaManagementImpl.getAspfactories().size());
        if (res != null)
            return res.getStringLongValue();
        else
            return null;
    }

    public void updateAspInactiveAckPerAssRx(String associationName) {
        this.statDataCollection.updateData(ASP_INACTIVE_ACK_PER_ASS_RX, associationName);
    }

    public Map<String, LongValue> getErrorPerAssRx(String campaignName) {
        StatResult res = this.statDataCollection.restartAndGet(ERROR_PER_ASS_RX, campaignName);
        this.statDataCollection.updateData(ERROR_PER_ASS_RX, m3uaManagementImpl.getAspfactories().size());
        if (res != null)
            return res.getStringLongValue();
        else
            return null;
    }

    public void updateErrorPerAssRx(String associationName) {
        this.statDataCollection.updateData(ERROR_PER_ASS_RX, associationName);
    }

    public Map<String, LongValue> getNotifyPerAssRx(String campaignName) {
        StatResult res = this.statDataCollection.restartAndGet(NOTIFY_PER_ASS_RX, campaignName);
        this.statDataCollection.updateData(NOTIFY_PER_ASS_RX, m3uaManagementImpl.getAspfactories().size());
        if (res != null)
            return res.getStringLongValue();
        else
            return null;
    }

    public void updateNotifyPerAssRx(String associationName) {
        this.statDataCollection.updateData(NOTIFY_PER_ASS_RX, associationName);
    }

    public Map<String, LongValue> getDunaPerAssRx(String campaignName) {
        StatResult res = this.statDataCollection.restartAndGet(DUNA_PER_ASS_RX, campaignName);
        this.statDataCollection.updateData(DUNA_PER_ASS_RX, m3uaManagementImpl.getAspfactories().size());
        if (res != null)
            return res.getStringLongValue();
        else
            return null;
    }

    public void updateDunaPerAssRx(String associationName) {
        this.statDataCollection.updateData(DUNA_PER_ASS_RX, associationName);
    }

    public Map<String, LongValue> getDavaPerAssRx(String campaignName) {
        StatResult res = this.statDataCollection.restartAndGet(DAVA_PER_ASS_RX, campaignName);
        this.statDataCollection.updateData(DAVA_PER_ASS_RX, m3uaManagementImpl.getAspfactories().size());
        if (res != null)
            return res.getStringLongValue();
        else
            return null;
    }

    public void updateDavaPerAssRx(String associationName) {
        this.statDataCollection.updateData(DAVA_PER_ASS_RX, associationName);
    }

    public Map<String, LongValue> getDaudPerAssRx(String campaignName) {
        StatResult res = this.statDataCollection.restartAndGet(DAUD_PER_ASS_RX, campaignName);
        this.statDataCollection.updateData(DAUD_PER_ASS_RX, m3uaManagementImpl.getAspfactories().size());
        if (res != null)
            return res.getStringLongValue();
        else
            return null;
    }

    public void updateDaudPerAssRx(String associationName) {
        this.statDataCollection.updateData(DAUD_PER_ASS_RX, associationName);
    }

    public Map<String, LongValue> getSconPerAssRx(String campaignName) {
        StatResult res = this.statDataCollection.restartAndGet(SCON_PER_ASS_RX, campaignName);
        this.statDataCollection.updateData(SCON_PER_ASS_RX, m3uaManagementImpl.getAspfactories().size());
        if (res != null)
            return res.getStringLongValue();
        else
            return null;
    }

    public void updateSconPerAssRx(String associationName) {
        this.statDataCollection.updateData(SCON_PER_ASS_RX, associationName);
    }

    public Map<String, LongValue> getDupuPerAssRx(String campaignName) {
        StatResult res = this.statDataCollection.restartAndGet(DUPU_PER_ASS_RX, campaignName);
        this.statDataCollection.updateData(DUPU_PER_ASS_RX, m3uaManagementImpl.getAspfactories().size());
        if (res != null)
            return res.getStringLongValue();
        else
            return null;
    }

    public void updateDupuPerAssRx(String associationName) {
        this.statDataCollection.updateData(DUPU_PER_ASS_RX, associationName);
    }

    public Map<String, LongValue> getDrstPerAssRx(String campaignName) {
        StatResult res = this.statDataCollection.restartAndGet(DRST_PER_ASS_RX, campaignName);
        this.statDataCollection.updateData(DRST_PER_ASS_RX, m3uaManagementImpl.getAspfactories().size());
        if (res != null)
            return res.getStringLongValue();
        else
            return null;
    }

    public void updateDrstPerAssRx(String associationName) {
        this.statDataCollection.updateData(DRST_PER_ASS_RX, associationName);
    }

    public Map<String, LongValue> getBeatPerAssRx(String campaignName) {
        StatResult res = this.statDataCollection.restartAndGet(BEAT_PER_ASS_RX, campaignName);
        this.statDataCollection.updateData(BEAT_PER_ASS_RX, m3uaManagementImpl.getAspfactories().size());
        if (res != null)
            return res.getStringLongValue();
        else
            return null;
    }

    public void updateBeatPerAssRx(String associationName) {
        this.statDataCollection.updateData(BEAT_PER_ASS_RX, associationName);
    }

    public Map<String, LongValue> getBeatAckPerAssRx(String campaignName) {
        StatResult res = this.statDataCollection.restartAndGet(BEAT_ACK_PER_ASS_RX, campaignName);
        this.statDataCollection.updateData(BEAT_ACK_PER_ASS_RX, m3uaManagementImpl.getAspfactories().size());
        if (res != null)
            return res.getStringLongValue();
        else
            return null;
    }

    public void updateBeatAckPerAssRx(String associationName) {
        this.statDataCollection.updateData(BEAT_ACK_PER_ASS_RX, associationName);
    }

}
