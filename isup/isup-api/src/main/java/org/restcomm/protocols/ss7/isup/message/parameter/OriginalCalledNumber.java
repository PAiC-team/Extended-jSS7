package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:17:11:40 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface OriginalCalledNumber extends CalledNumber, ISUPParameter {
    int _PARAMETER_CODE = 0x28;
}
