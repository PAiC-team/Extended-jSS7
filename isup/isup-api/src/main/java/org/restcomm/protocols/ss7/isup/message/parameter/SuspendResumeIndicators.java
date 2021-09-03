package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:14:09:47 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface SuspendResumeIndicators extends ISUPParameter {
    int _PARAMETER_CODE = 0x22;

    // FIXME: add C defs

    /**
     * See Q.763 3.52 Suspend/resume indicator : ISDN subscriber initiated
     */
    boolean _SRI_ISDN_SI = false;

    /**
     * See Q.763 3.52 Suspend/resume indicator : network initiated
     */
    boolean _SRI_NI = true;

    boolean isSuspendResumeIndicator();

    void setSuspendResumeIndicator(boolean suspendResumeIndicator);

}
