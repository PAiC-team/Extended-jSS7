package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.message.parameter.InformationType;
import org.restcomm.protocols.ss7.isup.message.parameter.InvokingRedirectReason;
import org.restcomm.protocols.ss7.isup.message.parameter.RedirectReason;

/**
 * @author baranowb
 *
 */
public class InvokingRedirectReasonImpl extends AbstractInformationImpl implements InvokingRedirectReason {

    private List<RedirectReason> reasons = new ArrayList<RedirectReason>();


    public InvokingRedirectReasonImpl() {
        super(InformationType.InvokingRedirectReason);
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
        for (int index = 0; index < this.reasons.size(); index++) {
            RedirectReasonImpl ai = (RedirectReasonImpl) this.reasons.get(index);
            ai.trim();

            byte b = (byte) (ai.encode()[0] & 0x7F);
            if (index + 1 == this.reasons.size()) {
                b |= 0x80;
            }
            baos.write(b);

        }
        return baos.toByteArray();
    }

    @Override
    void decode(byte[] data) throws ParameterException {
        for(int index = 0;index<data.length;index++){
            byte b = data[index];
            RedirectReasonImpl pr = new RedirectReasonImpl();
            pr.setRedirectReason((byte) (b & 0x7F));
            if( (b & 0x80) == 0 && index +1 == data.length){
                throw new ParameterException("Extension bit incicates more bytes, but we ran out of them!");
            }
            this.reasons.add(pr);
        }
    }

}
