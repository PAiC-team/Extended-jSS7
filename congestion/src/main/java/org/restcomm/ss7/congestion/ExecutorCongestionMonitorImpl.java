package org.restcomm.ss7.congestion;

import java.util.Date;
import java.util.concurrent.ExecutorService;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class ExecutorCongestionMonitorImpl extends BaseCongestionMonitor implements ExecutorCongestionMonitor {
    private static final String SOURCE = "EXECUTOR_";

    private String productName;
    private String source;
    private int currentAlarmLevel = 0;
    private ExecutorService[] executors;

    private double[] delayThreshold = new double[] { 1, 6, 12 };
    private double[] backToNormalDelayThreshold = new double[] { 0.5, 3, 8 };

    protected double calculatedMaxValue;

    public ExecutorCongestionMonitorImpl(String productName, ExecutorService[] executors) {
        this.productName = productName;
        this.executors = executors;

        this.source = SOURCE + productName;
    }

    @Override
    public void monitor() {
        TestMonitor testMonitor = new TestMonitor();
        testMonitor.startMonitor();
    }

    @Override
    public String getSource() {
        return this.source;
    }

    @Override
    protected CongestionTicketImpl generateTicket() {
        return new CongestionTicketImpl(this.source, currentAlarmLevel);
    }

    @Override
    public int getAlarmLevel() {
        return currentAlarmLevel;
    }

    @Override
    protected void setAlarmLevel(int val) {
        currentAlarmLevel = val;
    }

    @Override
    public double getDelayThreshold_1() {
        return delayThreshold[0];
    }

    @Override
    public double getDelayThreshold_2() {
        return delayThreshold[1];
    }

    @Override
    public double getDelayThreshold_3() {
        return delayThreshold[2];
    }

    @Override
    public double getBackToNormalDelayThreshold_1() {
        return backToNormalDelayThreshold[0];
    }

    @Override
    public double getBackToNormalDelayThreshold_2() {
        return backToNormalDelayThreshold[1];
    }

    @Override
    public double getBackToNormalDelayThreshold_3() {
        return backToNormalDelayThreshold[2];
    }

    @Override
    public void setDelayThreshold_1(double value) throws Exception {
        delayThreshold[0] = value;
    }

    @Override
    public void setDelayThreshold_2(double value) throws Exception {
        delayThreshold[1] = value;
    }

    @Override
    public void setDelayThreshold_3(double value) throws Exception {
        delayThreshold[2] = value;
    }

    @Override
    public void setBackToNormalDelayThreshold_1(double value) throws Exception {
        backToNormalDelayThreshold[0] = value;
    }

    @Override
    public void setBackToNormalDelayThreshold_2(double value) throws Exception {
        backToNormalDelayThreshold[1] = value;
    }

    @Override
    public void setBackToNormalDelayThreshold_3(double value) throws Exception {
        backToNormalDelayThreshold[2] = value;
    }

    private void registerResults(double maxValue) {
        calculatedMaxValue = maxValue;
        super.applyNewValue(currentAlarmLevel, maxValue, delayThreshold, backToNormalDelayThreshold, true);
    }

    @Override
    protected String getAlarmDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append("Last measured max delay between delivering an IP message for sending and a moment when the message was transferred via an IP channel (seconds): ");
        sb.append(calculatedMaxValue);
        return sb.toString();
    }

    public class TestMonitor {
        private ExecutorTestMonitor[] monitorList;
        private int cnt;
        private int finished = 0;

        public void startMonitor() {
            cnt = executors.length;
            monitorList = new ExecutorTestMonitor[cnt];
            for (int i1 = 0; i1 < cnt; i1++) {
                ExecutorService ex = executors[i1];
                monitorList[i1] = new ExecutorTestMonitor(this);
                ex.execute(monitorList[i1]);
            }
        }

        public synchronized void monitor() {
            if (++finished >= cnt - 1) {
                double maxValue = 0;
                for (ExecutorTestMonitor testMonitor : monitorList) {
                    if (testMonitor != null) {
                        if (maxValue < testMonitor.getDelay())
                            maxValue = testMonitor.getDelay();
                    }
                }
                registerResults(maxValue);
            }
        }
    }

    public class ExecutorTestMonitor implements Runnable {
        private TestMonitor testMonitor;
        private long startTime;
        private double delay;
        private boolean finished = false;

        public ExecutorTestMonitor(TestMonitor testMonitor) {
            this.testMonitor = testMonitor;
            this.startTime = (new Date()).getTime();
        }

        @Override
        public void run() {
            long endTime = (new Date()).getTime();
            delay = ((double) endTime - startTime) / 1000;
            finished = true;
            testMonitor.monitor();
        }

        public double getDelay() {
            return delay;
        }

        public boolean getFinished() {
            return finished;
        }
    }

}
