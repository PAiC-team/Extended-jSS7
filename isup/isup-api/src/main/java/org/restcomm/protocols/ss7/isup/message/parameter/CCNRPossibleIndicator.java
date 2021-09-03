package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:12:13:09 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface CCNRPossibleIndicator extends ISUPParameter {
    int _PARAMETER_CODE = 0x7A;

    /**
     * See Q.763 3.83 CCNR possible indicator : not possible
     */
    boolean _CCNR_PI_NOT_POSSIBLE = false;
    /**
     * See Q.763 3.83 CCNR possible indicator : possible
     */
    boolean _CCNR_PI_POSSIBLE = true;

    boolean isCcnrPossible();

    void setCcnrPossible(boolean ccnrPossible);
}
