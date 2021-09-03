
package org.restcomm.protocols.ss7.m3ua.impl;

import java.util.Arrays;

/**
 *
 * @author amit bhayani
 *
 */
public class TestEvent {

    private TestEventType testEventType;
    private boolean sent;
    private long timestamp;
    private Object[] event;
    private int sequence;

    public TestEvent(TestEventType eventType, long timestamp, Object[] event, int sequence) {
        super();
        this.testEventType = eventType;
        this.timestamp = timestamp;
        this.event = event;
        this.sequence = sequence;
    }

    public TestEventType getTestEventType() {
        return testEventType;
    }

    public boolean isSent() {
        return sent;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Object[] getEvent() {
        return event;
    }

    public int getSequence() {
        return sequence;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(event);
        result = prime * result + (sent ? 1231 : 1237);
        result = prime * result + sequence;
        result = prime * result + ((testEventType == null) ? 0 : testEventType.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TestEvent other = (TestEvent) obj;
        if (!Arrays.equals(event, other.event))
            return false;
        if (sent != other.sent)
            return false;
        if (sequence != other.sequence)
            return false;
        if (testEventType != other.testEventType)
            return false;
        return true;
    }
}
