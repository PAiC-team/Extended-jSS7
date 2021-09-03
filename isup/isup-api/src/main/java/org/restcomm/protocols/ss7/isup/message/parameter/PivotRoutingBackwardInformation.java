package org.restcomm.protocols.ss7.isup.message.parameter;

/**
 * Start time:13:47:23 2009-07-23<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
public interface PivotRoutingBackwardInformation extends ISUPParameter {
    // FIXME: fill this!
    int _PARAMETER_CODE = 0x89;

    int INFORMATION_RETURN_TO_INVOKING_EXCHANGE_DURATION = 0x01;
    int INFORMATION_RETURN_TO_INVOKING_EXCHANGE_CALL_ID = 0x02;
    int INFORMATION_INVOKING_PIVOT_REASON = 0x03;

    void setReturnToInvokingExchangeDuration(ReturnToInvokingExchangeDuration... duration);
    ReturnToInvokingExchangeDuration[] getReturnToInvokingExchangeDuration();

    void setReturnToInvokingExchangeCallIdentifier(ReturnToInvokingExchangeCallIdentifier... cid);
    ReturnToInvokingExchangeCallIdentifier[] getReturnToInvokingExchangeCallIdentifier();

    void setInvokingPivotReason(InvokingPivotReason... reason);
    InvokingPivotReason[] getInvokingPivotReason();
}
