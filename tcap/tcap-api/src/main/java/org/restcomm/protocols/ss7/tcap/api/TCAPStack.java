
package org.restcomm.protocols.ss7.tcap.api;

import java.util.List;

import org.restcomm.protocols.ss7.sccp.SccpStack;

/**
 * @author baranowb
 */
public interface TCAPStack {

    /**
     * Returns the name of this stack
     * @return
     */
    String getName();

    /**
     * Set the persist directory to store the xml files
     * @return
     */
    String getPersistDir();

    /**
     * Returns stack provider.
     * @return
     */
    TCAPProvider getProvider();

    TCAPCounterProvider getCounterProvider();

    /**
     * Stops this stack and transport layer(SCCP)
     */
    void stop();

    /**
     * Start stack and transport layer(SCCP)
     *
     * @throws IllegalStateException - if stack is already running or not configured
     * @throws Exception
     */
    void start() throws Exception;

    boolean isStarted();

    /**
     * Sets millisecond value for dialog timeout. It specifies how long dialog can be idle - not receive/send any messages.
     *
     * @param dialogIdleTimeout
     */
    void setDialogIdleTimeout(long dialogIdleTimeout) throws Exception;

    long getDialogIdleTimeout();

    /**
     * Sets the Invoke timeout for this invoke. Peer should respond back within invoke timeout, else stack will callback
     * {@link TCListener#onInvokeTimeout(org.restcomm.protocols.ss7.tcap.asn.comp.Invoke)} for application to take corrective
     * action. InvokeTimeout should always be less than DialogIdleTimeout.
     *
     * @param invokeTimeout
     */
    void setInvokeTimeout(long invokeTimeout) throws Exception;

    long getInvokeTimeout();

    /**
     * Sets the maximum number of dialogs allowed to be alive at a given time. If not set, a default value of 5000 dialogs will
     * be used.
     *
     * Important a: Default value may vary depending on the future implementations or changes to current implementation.
     *
     * Important b: If stack ranges provided, maximum number dialogs naturally cannot be greater than the provided range, thus,
     * it will be normalized to range delta (end - start).
     *
     * @param maxDialogs number of dialogs
     */
    void setMaxDialogs(int maxDialogs) throws Exception;

    /**
     *
     * @return Maximum number of allowed concurrent dialogs.
     */
    int getMaxDialogs();

    /**
     * Sets the start of the range of the generated dialog ids.
     */
    void setDialogIdRangeStart(long dialogIdRangeStart) throws Exception;

    /**
     * Sets the end of the range of the generated dialog ids.
     */
    void setDialogIdRangeEnd(long dialogIdRangeEnd) throws Exception;

    /**
     *
     * @return starting dialog id within the range
     */
    long getDialogIdRangeStart();

    /**
     *
     * @return ending dialog id within the range
     */
    long getDialogIdRangeEnd();

    /**
     * previewMode is needed for special processing mode. When PreviewMode in TCAP level we have: - stack only listen's incoming
     * messages and sends nothing. send(), close(), sendComponent() and other such methods do nothing. - A TCAP Dialog is
     * temporary. TCAP Dialog is discarded after any incoming message like TC-BEGIN or TC-CONTINUE has been processed - for any
     * incoming messages (including TC-CONTINUE, TC-END, TC-ABORT) a new TCAP Dialog is created (end then deleted). - no timers
     * and timeouts
     *
     * default state: no previewMode
     */
    void setPreviewMode(boolean isPreviewMode) throws Exception;

    /**
     *
     * @return if previewMode is active
     */
    boolean getPreviewMode();

    void setExtraSsns(List<Integer> extraSsnsNew) throws Exception;

    List<Integer> getExtraSsns();

    boolean isExtraSsnPresent(int ssn);

    String getSubSystemNumberList();

    void setDoNotSendProtocolVersion(boolean isDoNotSendProtocolVersion) throws Exception;

    boolean getDoNotSendProtocolVersion();

    /**
     * Set to true to enable statistics.
     *
     * @param isStatisticsEnabled
     */
    void setStatisticsEnabled(boolean isStatisticsEnabled) throws Exception;

    /**
     * Returns true if this stack is gathering statistics
     *
     * @return
     */
    boolean getStatisticsEnabled();

    /**
     * Returns the Sub System Number (SSN) that this TCAP Stack is registered for
     *
     * @return
     */
    int getSubSystemNumber();

    /**
     * @return if true incoming TCAP messages will be blocked (depending on congestion level, from level 2 - new incoming
     *         dialogs are rejected, from level 3 - all incoming messages are rejected)
     */
    boolean isCongControl_blockingIncomingTcapMessages();

    /**
     * @param value if true incoming TCAP messages will be blocked (depending on congestion level, from level 2 - new incoming
     *        dialogs are rejected, from level 3 - all incoming messages are rejected)
     */
    void setCongControl_blockingIncomingTcapMessages(boolean value) throws Exception;

    /**
     * @return ExecutorMonitor Threshold 1: delays in seconds (between the time when an incoming message has come from a peer
     *         and scheduled for execution and the time when the execution of the message starts) after which ExecutorMonitor
     *         becomes the congestion level 1
     */
    double getCongControl_ExecutorDelayThreshold_1();

    /**
     * @return ExecutorMonitor Threshold 2: delays in seconds (between the time when an incoming message has come from a peer
     *         and scheduled for execution and the time when the execution of the message starts) after which ExecutorMonitor
     *         becomes the congestion level 2
     */
    double getCongControl_ExecutorDelayThreshold_2();

    /**
     * @return ExecutorMonitor Threshold 3: delays in seconds (between the time when an incoming message has come from a peer
     *         and scheduled for execution and the time when the execution of the message starts) after which ExecutorMonitor
     *         becomes the congestion level 3
     */
    double getCongControl_ExecutorDelayThreshold_3();

    /**
     * Setting of ExecutorMonitor Threshold 1: delays in seconds (between the time when an incoming message has come from a peer
     * and scheduled for execution and the time when the execution of the message starts) after which ExecutorMonitor becomes
     * the congestion level 1
     *
     * @param threshold1
     */
    void setCongControl_ExecutorDelayThreshold_1(double threshold1) throws Exception;

    /**
     * Setting of ExecutorMonitor Threshold 2: delays in seconds (between the time when an incoming message has come from a peer
     * and scheduled for execution and the time when the execution of the message starts) after which ExecutorMonitor becomes
     * the congestion level 2
     *
     * @param threshold2
     */
    void setCongControl_ExecutorDelayThreshold_2(double threshold2) throws Exception;

    /**
     * Setting of ExecutorMonitor Threshold 3: delays in seconds (between the time when an incoming message has come from a peer
     * and scheduled for execution and the time when the execution of the message starts) after which ExecutorMonitor becomes
     * the congestion level 3
     *
     * @param threshold3
     */
    void setCongControl_ExecutorDelayThreshold_3(double threshold3) throws Exception;

    /**
     * @return ExecutorMonitor Threshold 1: delays in seconds (between the time when an incoming message has come from a peer
     *         and scheduled for execution and the time when the execution of the message starts) after which ExecutorMonitor
     *         resumes to the congestion level 0
     */
    double getCongControl_ExecutorBackToNormalDelayThreshold_1();

    /**
     * @return ExecutorMonitor Threshold 2: delays in seconds (between the time when an incoming message has come from a peer
     *         and scheduled for execution and the time when the execution of the message starts) after which ExecutorMonitor
     *         resumes to the congestion level 1
     */
    double getCongControl_ExecutorBackToNormalDelayThreshold_2();

    /**
     * @return ExecutorMonitor Threshold 3: delays in seconds (between the time when an incoming message has come from a peer
     *         and scheduled for execution and the time when the execution of the message starts) after which ExecutorMonitor
     *         resumes to the congestion level 2
     */
    double getCongControl_ExecutorBackToNormalDelayThreshold_3();

    /**
     * Setting of ExecutorMonitor Threshold 1: delays in seconds (between the time when an incoming message has come from a peer
     * and scheduled for execution and the time when the execution of the message starts) after which ExecutorMonitor resumes to
     * the congestion level 0
     *
     * @param threshold1
     */
    void setCongControl_ExecutorBackToNormalDelayThreshold_1(double threshold1) throws Exception;

    /**
     * Setting of ExecutorMonitor Threshold 2: delays in seconds (between the time when an incoming message has come from a peer
     * and scheduled for execution and the time when the execution of the message starts) after which ExecutorMonitor resumes to
     * the congestion level 1
     *
     * @param threshold2
     */
    void setCongControl_ExecutorBackToNormalDelayThreshold_2(double threshold2) throws Exception;

    /**
     * Setting of ExecutorMonitor Threshold 3: delays in seconds (between the time when an incoming message has come from a peer
     * and scheduled for execution and the time when the execution of the message starts) after which ExecutorMonitor resumes to
     * the congestion level 2
     *
     * @param threshold3
     */
    void setCongControl_ExecutorBackToNormalDelayThreshold_3(double threshold3) throws Exception;

    /**
     * @return MemoryMonitor Threshold 1: a percent of occupied memory after which MemoryMonitor becomes the congestion level 1
     */
    double getCongControl_MemoryThreshold_1();

    /**
     * @return MemoryMonitor Threshold 2: a percent of occupied memory after which MemoryMonitor becomes the congestion level 2
     */
    double getCongControl_MemoryThreshold_2();

    /**
     * @return MemoryMonitor Threshold 3: a percent of occupied memory after which MemoryMonitor becomes the congestion level 3
     */
    double getCongControl_MemoryThreshold_3();

    /**
     * Setting of MemoryMonitor Threshold 1: a percent of occupied memory after which MemoryMonitor becomes the congestion level
     * 1
     *
     * @param threshold1
     */
    void setCongControl_MemoryThreshold_1(double threshold1) throws Exception;

    /**
     * Setting of MemoryMonitor Threshold 2: a percent of occupied memory after which MemoryMonitor becomes the congestion level
     * 2
     *
     * @param threshold2
     */
    void setCongControl_MemoryThreshold_2(double threshold2) throws Exception;

    /**
     * Setting of MemoryMonitor Threshold 3: a percent of occupied memory after which MemoryMonitor becomes the congestion level
     * 3
     *
     * @param threshold3
     */
    void setCongControl_MemoryThreshold_3(double threshold3) throws Exception;

    /**
     * @return MemoryMonitor Threshold 1: a percent of occupied memory after which MemoryMonitor resumes to the congestion level
     *         0
     */
    double getCongControl_BackToNormalMemoryThreshold_1();

    /**
     * @return MemoryMonitor Threshold 2: a percent of occupied memory after which MemoryMonitor resumes to the congestion level
     *         1
     */
    double getCongControl_BackToNormalMemoryThreshold_2();

    /**
     * @return MemoryMonitor Threshold 3: a percent of occupied memory after which MemoryMonitor resumes to the congestion level
     *         2
     */
    double getCongControl_BackToNormalMemoryThreshold_3();

    /**
     * Setting of MemoryMonitor Threshold 1: a percent of occupied memory after which MemoryMonitor resumes to the congestion
     * level 0
     *
     * @param threshold1
     */
    void setCongControl_BackToNormalMemoryThreshold_1(double threshold1) throws Exception;

    /**
     * Setting of MemoryMonitor Threshold 2: a percent of occupied memory after which MemoryMonitor resumes to the congestion
     * level 1
     *
     * @param threshold2
     */
    void setCongControl_BackToNormalMemoryThreshold_2(double threshold2) throws Exception;

    /**
     * Setting of MemoryMonitor Threshold 3: a percent of occupied memory after which MemoryMonitor resumes to the congestion
     * level 2
     *
     * @param threshold3
     */
    void setCongControl_BackToNormalMemoryThreshold_3(double threshold3) throws Exception;

    /**
    *
    * @return the TCAPCounterEventsListener object or null it it isn't set (or set to null value)
    */
    TCAPCounterEventsListener getTCAPCounterEventsListener();

    /**
     * Sets the tcapCounterEventsListener attribute to the given value.
     *
     * @param tcapCounterEventsListener
     */
    void setTCAPCounterEventsListener(TCAPCounterEventsListener tcapCounterEventsListener);

    /** Set value for slsRange for this TCAP Stack.
     *
     * @param slsRange
     */
    void setSlsRange(String slsRange) throws Exception;

    /**
     * Returns the SlsRange that this TCAP Stack is registered for
     *
     * @return
     */
    String getSlsRange();

    /**
     * Returns the SCCP stack
     *
     * @return
     */
    SccpStack getSccpStack();

    /**
     * Returns the SCCP stack
     *
     * @return
     */
    boolean getSwapTcapIdBytes();

    /**
     * Set is there need to swap bytes for Txid
     *
     * @return
     */
    void setSwapTcapIdBytes(boolean isSwapTcapIdBytes);

    /**
     * Set Sub System Number (ssn)
     *
     * @return
     * **/

    void setSubSystemNumber(int setSubSystemNumber) throws Exception;
}
