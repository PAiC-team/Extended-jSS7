package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:13:55:48 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface RedirectCounter extends ISUPParameter {

    int _PARAMETER_CODE = 0x77;

    int getCounter();

    void setCounter(int counter);
}
