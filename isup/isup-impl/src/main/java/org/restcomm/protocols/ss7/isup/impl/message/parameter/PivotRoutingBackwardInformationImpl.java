package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.message.parameter.InvokingPivotReason;
import org.restcomm.protocols.ss7.isup.message.parameter.PivotRoutingBackwardInformation;
import org.restcomm.protocols.ss7.isup.message.parameter.ReturnToInvokingExchangeCallIdentifier;
import org.restcomm.protocols.ss7.isup.message.parameter.ReturnToInvokingExchangeDuration;

/**
 * Start time:16:16:18 2009-04-05<br>
 * Project: mobicents-isup-stack<br>
 *
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public class PivotRoutingBackwardInformationImpl extends AbstractInformationParameterBaseImpl implements
        PivotRoutingBackwardInformation {

    private static final Map<Integer, Class<? extends AbstractInformationImpl>> tagMapping;

    static{
        Map<Integer, Class<? extends AbstractInformationImpl>> tmp = new HashMap<Integer, Class<? extends AbstractInformationImpl>>();
        tmp.put(INFORMATION_RETURN_TO_INVOKING_EXCHANGE_DURATION,ReturnToInvokingExchangeDurationImpl.class);
        tmp.put(INFORMATION_RETURN_TO_INVOKING_EXCHANGE_CALL_ID,ReturnToInvokingExchangeCallIdentifierImpl.class);
        tmp.put(INFORMATION_INVOKING_PIVOT_REASON,InvokingPivotReasonImpl.class);

        tagMapping = Collections.unmodifiableMap(tmp);
    }
    public PivotRoutingBackwardInformationImpl(byte[] pivotRoutingIndicators) throws ParameterException {
        super();
        decode(pivotRoutingIndicators);
    }

    public PivotRoutingBackwardInformationImpl() {
        super();

    }

    public int getCode() {

        return _PARAMETER_CODE;
    }

    @Override
    protected Map<Integer, Class<? extends AbstractInformationImpl>> getTagMapping() {
        return tagMapping;
    }

    @Override
    public void setReturnToInvokingExchangeDuration(ReturnToInvokingExchangeDuration... duration) {
        super.setInformation(duration);
    }

    @Override
    public ReturnToInvokingExchangeDuration[] getReturnToInvokingExchangeDuration() {
        return (ReturnToInvokingExchangeDuration[]) super.getInformation(ReturnToInvokingExchangeDuration.class);
    }

    @Override
    public void setReturnToInvokingExchangeCallIdentifier(ReturnToInvokingExchangeCallIdentifier... cid) {
        super.setInformation(cid);
    }

    @Override
    public ReturnToInvokingExchangeCallIdentifier[] getReturnToInvokingExchangeCallIdentifier() {
        return (ReturnToInvokingExchangeCallIdentifier[]) super.getInformation(ReturnToInvokingExchangeCallIdentifier.class);
    }

    @Override
    public void setInvokingPivotReason(InvokingPivotReason... reason) {
        super.setInformation(reason);
    }

    @Override
    public InvokingPivotReason[] getInvokingPivotReason() {
        return (InvokingPivotReason[]) super.getInformation(InvokingPivotReason.class);
    }
}
