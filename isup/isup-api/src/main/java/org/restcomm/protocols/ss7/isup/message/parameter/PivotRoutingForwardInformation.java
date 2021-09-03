package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:13:48:01 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface PivotRoutingForwardInformation extends ISUPParameter {
    // FIXME: fill this!
    int _PARAMETER_CODE = 0x88;

    int INFORMATION_RETURN_TO_INVOKING_EXCHANGE_POSSIBLE = 0x01;
    int INFORMATION_RETURN_TO_INVOKING_EXCHANGE_CALL_ID = 0x02;
    int INFORMATION_PERFORMING_PIVOT_INDICATOR = 0x03;
    int INFORMATION_INVOKING_PIVOT_REASON = 0x04;
    void setReturnToInvokingExchangePossible(ReturnToInvokingExchangePossible... duration);
    ReturnToInvokingExchangePossible[] getReturnToInvokingExchangePossible();

    void setReturnToInvokingExchangeCallIdentifier(ReturnToInvokingExchangeCallIdentifier... cid);
    ReturnToInvokingExchangeCallIdentifier[] getReturnToInvokingExchangeCallIdentifier();

    void setPerformingPivotIndicator(PerformingPivotIndicator... reason);
    PerformingPivotIndicator[] getPerformingPivotIndicator();

    void setInvokingPivotReason(InvokingPivotReason... reason);
    InvokingPivotReason[] getInvokingPivotReason();
}
