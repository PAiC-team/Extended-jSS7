
package org.restcomm.protocols.ss7.isup.impl.stack.timers;

import java.util.ArrayList;
import java.util.List;

import org.restcomm.protocols.ss7.isup.ISUPEvent;
import org.restcomm.protocols.ss7.isup.ISUPTimeoutEvent;
import org.restcomm.protocols.ss7.isup.message.ISUPMessage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * @author baranowb
 *
 */
public abstract class SingleTimers extends EventTestHarness {

    protected int tid;
    protected long timeout;

    protected ISUPMessage request; // message exchanged within
    protected ISUPMessage answer;

    @BeforeClass
    public void setUp() throws Exception {

        super.setUp();
        this.tid = getT_ID();
        this.timeout = getT();
        this.request = getRequest();
        this.answer = getAnswer();
    }

    @AfterClass
    public void tearDown() throws Exception {
        super.provider.removeListener(this);
        super.tearDown();
    }

    protected abstract long getT();

    // IDS
    protected abstract int getT_ID();

    protected ISUPMessage getAfterTRequest() {
        return this.provider.getMessageFactory().createREL(getRequest().getCircuitIdentificationCode().getCIC());
    }

    // @Test(groups = { "functional.timer","timer.timeout.timeout"})
    public void testWithTimeout() throws Exception {
        // add expected events on remote and local end
        List<EventReceived> expectedRemoteEventsReceived = new ArrayList<EventReceived>();
        List<EventReceived> expectedLocalEvents = new ArrayList<EventReceived>();

        long startTStamp = System.currentTimeMillis();
        super.provider.sendMessage(this.request, dpc);
        MessageEventReceived eventReceived = new MessageEventReceived(startTStamp, new ISUPEvent(super.provider, this.request,
                dpc));
        expectedRemoteEventsReceived.add(eventReceived);
        ISUPMessage afterTimeoutMessage = getAfterTRequest();
        if (afterTimeoutMessage != null) {
            eventReceived = new MessageEventReceived(startTStamp + getT(), new ISUPEvent(super.provider, afterTimeoutMessage,
                    dpc));
            expectedRemoteEventsReceived.add(eventReceived);
        }
        ISUPTimeoutEvent timeoutEvent = new ISUPTimeoutEvent(super.provider, this.request, tid, dpc);
        TimeoutEventReceived ter = new TimeoutEventReceived(startTStamp + timeout, timeoutEvent);
        expectedLocalEvents.add(ter);
        doWait(timeout + 1000);

        // stop stack
        stack.stop();

        // now make compare
        super.compareEvents(expectedLocalEvents, expectedRemoteEventsReceived);
    }

    // @Test(groups = { "functional.timer","timer.timeout.wotimeout"})
    public void testWithoutTimeout() throws Exception {
        // add expected events on remote and local end
        List<EventReceived> expectedRemoteEventsReceived = new ArrayList<EventReceived>();
        List<EventReceived> expectedLocalEvents = new ArrayList<EventReceived>();

        long startTStamp = System.currentTimeMillis();
        this.provider.sendMessage(this.request, dpc);
        MessageEventReceived eventReceived = new MessageEventReceived(startTStamp, new ISUPEvent(super.provider, this.request,
                dpc));
        expectedRemoteEventsReceived.add(eventReceived);

        doWait(timeout / 2); // 500 should be good even here.
        long tstamp = System.currentTimeMillis();
        doAnswer();
        ISUPEvent event = new ISUPEvent(super.provider, this.answer, dpc);
        eventReceived = new MessageEventReceived(tstamp, event);
        expectedLocalEvents.add(eventReceived);
        doWait(timeout); // wait more

        stack.stop();
        // now make compare
        super.compareEvents(expectedLocalEvents, expectedRemoteEventsReceived);
    }

}
