/**
 * Start time:13:40:22 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:13:40:22 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface AbstractPointCodeInterface extends ISUPParameter {
    int getSignalingPointCode();

    void setSignalingPointCode(int signalingPointCode);
}
