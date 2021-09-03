package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.message.parameter.InformationType;
import org.restcomm.protocols.ss7.isup.message.parameter.PerformingRedirectIndicator;
import org.restcomm.protocols.ss7.isup.message.parameter.RedirectReason;

/**
 * @author baranowb
 *
 */
public class PerformingRedirectIndicatorImpl extends AbstractInformationImpl implements PerformingRedirectIndicator {

    private List<RedirectReason> reasons = new ArrayList<RedirectReason>();

    public PerformingRedirectIndicatorImpl() {
        super(InformationType.PerformingRedirectIndicator);
        super.tag = 0x03;
    }

    @Override
    public void setReason(RedirectReason... reasons) {
        this.reasons.clear();
        if(reasons == null){
            return;
        }
        for(RedirectReason pr: reasons){
            if(pr!=null){
                this.reasons.add(pr);
            }
        }
    }

    @Override
    public RedirectReason[] getReason() {
        return this.reasons.toArray(new RedirectReason[this.reasons.size()]);
    }

    @Override
    byte[] encode() throws ParameterException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for(RedirectReason pr:this.reasons){
            RedirectReasonImpl ai = (RedirectReasonImpl) pr;
            try {
                baos.write(ai.encode());
            } catch (IOException e) {
                throw new ParameterException(e);
            }
        }
        return baos.toByteArray();
    }

    @Override
    void decode(byte[] data) throws ParameterException {
        for(int index = 0;index<data.length;index++){
            byte b = data[index];
            RedirectReasonImpl pr = new RedirectReasonImpl();
            pr.setRedirectReason((byte) (b & 0x7F));
            if( (b & 0x80) == 0){
                if (index +1 == data.length){
                    throw new ParameterException("Extension bit incicates more bytes, but we ran out of them!");
                }
                b = data[++index];
                pr.setRedirectPossibleAtPerformingExchange((byte) (b & 0x07));
            }
            this.reasons.add(pr);
        }
    }

}
