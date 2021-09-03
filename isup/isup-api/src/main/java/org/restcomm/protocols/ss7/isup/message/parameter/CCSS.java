package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:12:16:04 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface CCSS extends ISUPParameter {
    int _PARAMETER_CODE = 0x4B;

    /**
     * See Q.763 3.83 CCNR possible indicator : not possible
     */
    boolean _NOT_CCSS_CALL = false;
    /**
     * See Q.763 3.83 CCNR possible indicator : possible
     */
    boolean _CCSS_CALL = true;

    boolean isCcssCall();

    void setCcssCall(boolean ccssCall);
}
