package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.message.parameter.InvokingRedirectReason;
import org.restcomm.protocols.ss7.isup.message.parameter.PerformingRedirectIndicator;
import org.restcomm.protocols.ss7.isup.message.parameter.RedirectForwardInformation;
import org.restcomm.protocols.ss7.isup.message.parameter.ReturnToInvokingExchangeCallIdentifier;
import org.restcomm.protocols.ss7.isup.message.parameter.ReturnToInvokingExchangePossible;

/**
 * @author baranowb
 *
 */
public class RedirectForwardInformationImpl extends AbstractInformationParameterBaseImpl implements RedirectForwardInformation {

    private static final Map<Integer, Class<? extends AbstractInformationImpl>> tagMapping;

    static{
        Map<Integer, Class<? extends AbstractInformationImpl>> tmp = new HashMap<Integer, Class<? extends AbstractInformationImpl>>();
        tmp.put(INFORMATION_RETURN_TO_INVOKING_EXCHANGE_POSSIBLE,ReturnToInvokingExchangePossibleImpl.class);
        tmp.put(INFORMATION_RETURN_TO_INVOKING_EXCHANGE_CALL_ID,ReturnToInvokingExchangeCallIdentifierImpl.class);
        tmp.put(INFORMATION_PERFORMING_REDIRECT_INDICATOR,PerformingRedirectIndicatorImpl.class);
        tmp.put(INFORMATION_INVOKING_REDIRECT_REASON,InvokingRedirectReasonImpl.class);

        tagMapping = Collections.unmodifiableMap(tmp);
    }

    public RedirectForwardInformationImpl() {
        // TODO Auto-generated constructor stub
    }

    public RedirectForwardInformationImpl(byte[] data) throws ParameterException {
        decode(data);
    }

    @Override
    public int getCode() {
        return _PARAMETER_CODE;
    }

    @Override
    protected Map<Integer, Class<? extends AbstractInformationImpl>> getTagMapping() {
        return tagMapping;
    }

    @Override
    public void setReturnToInvokingExchangePossible(ReturnToInvokingExchangePossible... duration) {
        super.setInformation(duration);
    }

    @Override
    public ReturnToInvokingExchangePossible[] getReturnToInvokingExchangePossible() {
        return (ReturnToInvokingExchangePossible[])super.getInformation(ReturnToInvokingExchangePossible.class);
    }

    @Override
    public void setReturnToInvokingExchangeCallIdentifier(ReturnToInvokingExchangeCallIdentifier... cid) {
        super.setInformation(cid);
    }

    @Override
    public ReturnToInvokingExchangeCallIdentifier[] getReturnToInvokingExchangeCallIdentifier() {
        return (ReturnToInvokingExchangeCallIdentifier[])super.getInformation(ReturnToInvokingExchangeCallIdentifier.class);
    }

    @Override
    public void setPerformingRedirectIndicator(PerformingRedirectIndicator... reason) {
        super.setInformation(reason);
    }

    @Override
    public PerformingRedirectIndicator[] getPerformingRedirectIndicator() {
        return (PerformingRedirectIndicator[])super.getInformation(PerformingRedirectIndicator.class);
    }

    @Override
    public void setInvokingRedirectReason(InvokingRedirectReason... reason) {
        super.setInformation(reason);
    }

    @Override
    public InvokingRedirectReason[] getInvokingRedirectReason() {
        return (InvokingRedirectReason[])super.getInformation(InvokingRedirectReason.class);
    }

}
