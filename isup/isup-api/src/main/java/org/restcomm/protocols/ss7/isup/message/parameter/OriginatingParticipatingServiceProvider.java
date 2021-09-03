package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:12:52:06 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface OriginatingParticipatingServiceProvider extends Number, ISUPParameter {
    // FIXME: fill this!
    int _PARAMETER_CODE = 0;

    int getOpspLengthIndicator();
}
