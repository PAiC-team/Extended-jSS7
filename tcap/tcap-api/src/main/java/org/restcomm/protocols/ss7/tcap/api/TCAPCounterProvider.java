
package org.restcomm.protocols.ss7.tcap.api;

import java.util.Map;
import java.util.UUID;

import org.restcomm.protocols.ss7.statistics.api.LongValue;

/**
 *
 * @author servey vetyutnev
 *
 */
public interface TCAPCounterProvider {

    /**
     * Return a unique sessionId.
     * After stack/counters restart this value will be changed,
     * all counters will be set to zero and all active campaigns will be removed
     */
    UUID getSessionId();

    /**
     * return a count of received since Stack restart TC-UNI messages
     */
    long getTcUniReceivedCount();

    /**
     * return a count of sent since Stack restart TC-UNI messages
     */
    long getTcUniSentCount();

    /**
     * return a count of received since Stack restart TC-BEGIN messages
     */
    long getTcBeginReceivedCount();

    /**
     * return a count of sent since Stack restart TC-BEGIN messages
     */
    long getTcBeginSentCount();

    /**
     * return a count of received since Stack restart TC-CONTINUE messages
     */
    long getTcContinueReceivedCount();

    /**
     * return a count of sent since Stack restart TC-CONTINUE messages
     */
    long getTcContinueSentCount();

    /**
     * return a count of received since Stack restart TC-END messages
     */
    long getTcEndReceivedCount();

    /**
     * return a count of sent since Stack restart TC-END messages
     */
    long getTcEndSentCount();

    /**
     * return a count of received since Stack restart TC-PROVIDER-ABORT messages
     */
    long getTcPAbortReceivedCount();

    /**
     * return a count of sent since Stack restart TC-PROVIDER-ABORT messages
     */
    long getTcPAbortSentCount();

    /**
     * return a count of received since Stack restart TC-USER-ABORT messages
     */
    long getTcUserAbortReceivedCount();

    /**
     * return a count of sent since Stack restart TC-USER-ABORT messages
     */
    long getTcUserAbortSentCount();

    /**
     * return a count of received since Stack restart Invoke components
     */
    long getInvokeReceivedCount();

    /**
     * return a count of sent since Stack restart Invoke components
     */
    long getInvokeSentCount();

    /**
     * return a count of received since Stack restart ReturtResult components
     */
    long getReturnResultReceivedCount();

    /**
     * return a count of sent since Stack restart ReturtResult components
     */
    long getReturnResultSentCount();

    /**
     * return a count of received since Stack restart ReturtResultLast components
     */
    long getReturnResultLastReceivedCount();

    /**
     * return a count of sent since Stack restart ReturtResultLast components
     */
    long getReturnResultLastSentCount();

    /**
     * return a count of received since Stack restart ReturnError components
     */
    long getReturnErrorReceivedCount();

    /**
     * return a count of sent since Stack restart ReturnError components
     */
    long getReturnErrorSentCount();

    /**
     * return a count of received since Stack restart Reject components
     */
    long getRejectReceivedCount();

    /**
     * return a count of sent since Stack restart Reject components
     */
    long getRejectSentCount();

    /**
     * return a count of received since Stack restart DialogTimeouts
     */
    long getDialogTimeoutCount();

    /**
     * return a count of received since Stack restart DialogReleases
     */
    long getDialogReleaseCount();

    /**
     * return a current count of established Dialogs
     */
    long getCurrentDialogsCount();

    /**
     * return a count of all established Dialogs since stack start
     */
    long getAllEstablishedDialogsCount();

    /**
     * return a count of all established local originated Dialogs since stack start
     */
    long getAllLocalEstablishedDialogsCount();

    /**
     * return a count of all established remote originated Dialogs since stack start
     */
    long getAllRemoteEstablishedDialogsCount();

    /**
     * return a min count of established Dialogs
     */
    Long getMinDialogsCount(String campaignName);

    /**
     * return a max current count of established Dialogs
     */
    Long getMaxDialogsCount(String campaignName);

    /**
     * return a total durations of all released Dialogs since stack start (in milliseconds)
     * this value is updated when a dialog is released
     */
    long getAllDialogsDuration();

    /**
     * return an outgoing Dialogs count per ApplicationContextNames (in string form)
     * all MAP V1 operations will be assigned into empty string group ("")
     */
    Map<String,LongValue> getOutgoingDialogsPerApplicationContextName(String campaignName);

    /**
     * return an incoming Dialogs count per ApplicationContextNames (in string form)
     */
    Map<String,LongValue> getIncomingDialogsPerApplicationContextName(String campaignName);

    /**
     * return an outgoing Invokes count per OperationCodes
     */
    Map<String,LongValue> getOutgoingInvokesPerOperationCode(String campaignName);

    /**
     * return an incoming Invokes count per OperationCodes
     */
    Map<String,LongValue> getIncomingInvokesPerOperationCode(String campaignName);

    /**
     * return an outgoing ReturtError count per ErrorCodes
     */
    Map<String,LongValue> getOutgoingErrorsPerErrorCode(String campaignName);

    /**
     * return an incoming ReturtError count per ErrorCodes
     */
    Map<String,LongValue> getIncomingErrorsPerErrorCode(String campaignName);

    /**
     * return an outgoing Reject count per Problem
     */
    Map<String,LongValue> getOutgoingRejectPerProblem(String campaignName);

    /**
     * return an incoming Reject count per Problem
     */
    Map<String,LongValue> getIncomingRejectPerProblem(String campaignName);


    /**
     * return A max count of networkID areas that are not available
     */
    Long getMaxNetworkIdAreasNotAvailable(String campaignName);

    /**
     * return A max count of networkID areas that are congested with level at least 1
     */
    Long getMaxNetworkIdAreasCongLevel_1(String campaignName);

    /**
     * return A max count of networkID areas that are congested with level at least 2
     */
    Long getMaxNetworkIdAreasCongLevel_2(String campaignName);

    /**
     * return A max count of networkID areas that are congested with level at least 3
     */
    Long getMaxNetworkIdAreasCongLevel_3(String campaignName);

    /**
     * return A max count of Executors that are congested with level at least 1
     */
    Long getMaxExecutorsCongLevel_1(String campaignName);

    /**
     * return A max count of Executors that are congested with level at least 2
     */
    Long getMaxExecutorsCongLevel_2(String campaignName);

    /**
     * return A max count of Executors that are congested with level at least 3
     */
    Long getMaxExecutorsCongLevel_3(String campaignName);

    /**
     * return A max memory congestion level
     */
    Long getMaxMemoryCongLevel(String campaignName);

    /**
     * return Max count of TcapUserParts that are congested with level at least 1
     */
    Long getMaxUserPartsCongLevel_1(String campaignName);

    /**
     * return Max count of TcapUserParts that are congested with level at least 2
     */
    Long getMaxUserPartsCongLevel_2(String campaignName);

    /**
     * return Max count of TcapUserParts that are congested with level at least 3
     */
    Long getMaxUserPartsCongLevel_3(String campaignName);

}

