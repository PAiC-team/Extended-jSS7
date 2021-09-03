package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * @author baranowb
 *
 */
public interface ReturnToInvokingExchangeDuration extends Information {

    void setDuration(int seconds);

    int getDuration();
}
